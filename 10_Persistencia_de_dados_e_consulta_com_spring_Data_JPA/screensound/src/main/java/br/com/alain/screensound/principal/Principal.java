package br.com.alain.screensound.principal;

import br.com.alain.screensound.model.Artista;
import br.com.alain.screensound.model.Musica;
import br.com.alain.screensound.model.TipoArtista;
import br.com.alain.screensound.repository.ArtistaRepository;
import br.com.alain.screensound.service.ConsultaChatGPT;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Principal {

    private final ArtistaRepository repositorio;
    private Scanner leitura = new Scanner(System.in);

    public Principal(ArtistaRepository respositorio) {
        this.repositorio = respositorio;
    }

    public void exibeMenu() {
        var opcao = -1;

        while (opcao!= 9) {
            var menu = """
                    *** Screen Sound Músicas ***                    
                                        
                    1- Cadastrar artistas
                    2- Cadastrar músicas
                    3- Listar músicas
                    4- Buscar músicas por artistas
                    5- Pesquisar dados sobre um artista
                                    
                    9 - Sair
                    """;

            System.out.println(menu);
            opcao = leitura.nextInt();
            leitura.nextLine();

            switch (opcao) {
                case 1:
                    cadastrarArtistas();
                    break;
                case 2:
                    cadastrarMusicas();
                    break;
                case 3:
                    listarMusicas();
                    break;
                case 4:
                    buscarMusicasPorArtista();
                    break;
                case 5:
                    pesquisarDadosDoArtista();
                    break;
                case 9:
                    System.out.println("Encerrando a aplicação!");
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        }
    }

    private void cadastrarArtistas() {

        var novo = "s";

        while(novo.equalsIgnoreCase("s")) {


            System.out.println("Informe o nome do artista:");
            var nome = leitura.nextLine();

            System.out.println("Informe o tipo do artista: (solo, dupla ou banda)");
            var tipo = leitura.nextLine();

            TipoArtista tipoArtista = TipoArtista.valueOf(tipo.toUpperCase());

            Artista artista = new Artista(nome, tipoArtista);

            repositorio.save(artista);

            System.out.println("Deseja cadastrar um novo artista? (S/N)");
            novo = leitura.nextLine();
        }
    }

    private void cadastrarMusicas(){
        System.out.println("Deseja cadastrar música de qual artista? ");
        var nome = leitura.nextLine();

        Optional<Artista> artistaCadastrado = repositorio.findByNomeContainingIgnoreCase(nome);

        if(artistaCadastrado.isPresent()){
            System.out.println("Informe o título da música: ");
            var nomeMusica = leitura.nextLine();

            Musica musica = new Musica(nomeMusica);
            musica.setArtista(artistaCadastrado.get());
            artistaCadastrado.get().getMusicas().add(musica);
            repositorio.save(artistaCadastrado.get());


        }else{
            System.out.println("Artista não cadastrado!");
        }

    }

    private void listarMusicas() {
        List<Artista> artistas = repositorio.findAll();
        artistas.forEach(a -> a.getMusicas().forEach(System.out::println));
    }

    private void buscarMusicasPorArtista() {
        System.out.println("Buscar músicas de que artista?");
        var nome = leitura.nextLine();
        List<Musica> musicas = repositorio.buscaMusicasPorArtista(nome);
        musicas.forEach(System.out::println);
    }

    private void pesquisarDadosDoArtista(){
        System.out.println("Pesquisar dados sobre qual artista?");
        var nome = leitura.nextLine();

        var resposta = ConsultaChatGPT.obterInformacao(nome);

        System.out.println(resposta.trim());

    }

}
