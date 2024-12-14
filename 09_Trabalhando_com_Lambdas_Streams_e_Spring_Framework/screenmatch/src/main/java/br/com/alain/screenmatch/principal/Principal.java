package br.com.alain.screenmatch.principal;

import br.com.alain.screenmatch.model.DadosEpisodio;
import br.com.alain.screenmatch.model.DadosSerie;
import br.com.alain.screenmatch.model.DadosTemporada;
import br.com.alain.screenmatch.model.Episodio;
import br.com.alain.screenmatch.service.ConsumoApi;
import br.com.alain.screenmatch.service.ConverteDados;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

public class Principal {

    private Scanner leitura = new Scanner(System.in);
    private ConsumoApi consumo = new ConsumoApi();
    private ConverteDados conversor = new ConverteDados();
    private final String ENDERECO = "https://www.omdbapi.com/?t=";
    private final String API_KEY = "&apikey=bdb1b12c";

    public void exibeMenu(){
        System.out.println("Digite o nome da série para lista: ");
        var nomeSerie = leitura.nextLine();

        var json = consumo.obterDados(ENDERECO + nomeSerie.replace(" ", "+") + API_KEY);

        System.out.println(json);

        conversor = new ConverteDados();
        DadosSerie dados = conversor.obterDados(json, DadosSerie.class);
        System.out.println(dados);

        List<DadosTemporada> temporadas = new ArrayList<>();

		for(int i = 1; i<= dados.totalTemporadas(); i++){
            //System.out.println(ENDERECO + nomeSerie.replace(" ", "+") +"&season=" + i + API_KEY);
            json = consumo.obterDados(ENDERECO + nomeSerie.replace(" ", "+") +"&season=" + i + API_KEY);
			DadosTemporada dadosTemporada = conversor.obterDados(json, DadosTemporada.class);
			temporadas.add(dadosTemporada);
		}

		System.out.println("*********************************************");
		temporadas.forEach(System.out::println);
        System.out.println("*********************************************");

//        for (int i = 0; i < dados.totalTemporadas(); i++){
//            List<DadosEpisodio> episodiosTemporada = temporadas.get(i).episodios();
//            for(int j = 0; j < episodiosTemporada.size(); j++){
//                System.out.println(episodiosTemporada.get(j).titulo());
//            }
//        }
//
        temporadas.forEach(t -> t.episodios().forEach(e -> System.out.println(e.titulo()) ));

        System.out.println("<<< Teste Lista de Nomes >>>");
        // teste
//        List<String> nomes = Arrays.asList("Alain", "Julia", "Aline", "Graciele", "Abel", "Hector", "Nico", "Alice");
//        nomes.stream()
//                .sorted()
//                .limit(3)
//                .filter(n -> n.startsWith("A")).
//                map(n -> n.toUpperCase())
//                .forEach(System.out::println);

        // Aglutinando todos os episodios de todas as temporadas em uma única lista
        List<DadosEpisodio> dadosEpisodios = temporadas.stream()
                .flatMap(t -> t.episodios().stream())
                .collect(Collectors.toList());
                //.toList(); // dessa forma teremos uma lista imutável

        System.out.println("\nTop 10 Episodios:");
        dadosEpisodios
                .stream()
                .filter(a -> !a.avaliacao().equalsIgnoreCase("N/A"))
                //.peek(e -> System.out.println("Primeiro filtro(N/A): " + e)) // ajuda a debugar
                .sorted(Comparator.comparing(DadosEpisodio::avaliacao).reversed())
                //.peek(e -> System.out.println("Ordenação: " + e))
                .limit(10)
                //.peek(e -> System.out.println("Limitando... " + e))
                .map( e -> e.titulo().toUpperCase())
                //.peek(e -> System.out.println("Ordenação: " + e))
                .forEach(System.out::println);

        List<Episodio> episodios = temporadas.stream()
                .flatMap(t -> t.episodios().stream()
                        .map(d -> new Episodio(t.numero(), d))
                ).collect(Collectors.toList());

        episodios.forEach(System.out::println);

        System.out.println("A partir de qual ano você deseja ver os episódios?");
        var ano = leitura.nextInt();
        leitura.nextLine();

        LocalDate dataBusca = LocalDate.of(ano, 1, 1);

        DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        episodios.stream()
                .filter(e -> e.getDataLancamento() != null && e.getDataLancamento().isAfter(dataBusca))
                .forEach(e -> System.out.println(
                        "Temporada: " + e.getTemporada() +
                                " Episódio: " + e.getTitulo() +
                                " Data lançamento: " + e.getDataLancamento().format(formatador)
                ));

        System.out.println("Informe o episódio que será pesquisado: ");
        var trechoTitulo = leitura.nextLine();

        Optional<Episodio> episodioBuscado =  episodios.stream()
                .filter(e -> e.getTitulo().toUpperCase().contains(trechoTitulo.toUpperCase()))
                .findFirst();

        if(episodioBuscado.isPresent()){
            System.out.println("Episódio encontrado!");
            System.out.println("Temporada: " + episodioBuscado.get().getTemporada());
        }else{
            System.out.println("Episódio não localizado!");
        }

        Map<Integer, Double> avaliacoesPorTemporada = episodios.stream()
                .filter(e -> e.getAvaliacao() > 0.0)
                .collect(Collectors.groupingBy(Episodio::getTemporada,
                        Collectors.averagingDouble(Episodio::getAvaliacao)));

        System.out.println("Avaliacoes por temporada: " + avaliacoesPorTemporada);

        DoubleSummaryStatistics est = episodios.stream()
                .filter(e -> e.getAvaliacao() > 0.0)
                .collect(Collectors.summarizingDouble(Episodio::getAvaliacao));

        System.out.println("Estatísticas: " + est);
        System.out.println("Média: " + est.getAverage());
        System.out.println("Melhor média: " + est.getMax());
        System.out.println("Pior média: " + est.getMin());
        System.out.println("Quantidade: " + est.getCount());

    }
}
