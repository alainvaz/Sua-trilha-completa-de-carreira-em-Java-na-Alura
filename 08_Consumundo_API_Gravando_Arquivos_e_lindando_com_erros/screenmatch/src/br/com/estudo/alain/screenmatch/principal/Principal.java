package br.com.estudo.alain.screenmatch.principal;

import br.com.estudo.alain.screenmatch.calculo.FiltroRecomendacao;
import br.com.estudo.alain.screenmatch.modelos.Episodio;
import br.com.estudo.alain.screenmatch.modelos.Filme;
import br.com.estudo.alain.screenmatch.modelos.Serie;
import br.com.estudo.alain.screenmatch.calculo.CalculadoraDeTempo;

import java.util.ArrayList;

public class Principal {
    public static void main(String args[]){
        Filme meuFilme = new Filme();
        FiltroRecomendacao filtro = new FiltroRecomendacao();

        meuFilme.setNome("divertida mente");
        meuFilme.setAnoLancamento(2015);
        meuFilme.setDuracaoEmMinutos(130);
        meuFilme.setDiretor("Peter Docter e Ronnie del Carmen");
        System.out.println(meuFilme);

        meuFilme.avalia(5);
        meuFilme.avalia(4.7);
        meuFilme.avalia(3.3);
        meuFilme.avalia(4.1);

        meuFilme.exibeFichaTecnica();
        System.out.println(meuFilme.pegaMedia());
        System.out.println("Duracao em minutos: "+ meuFilme.getDuracaoEmMinutos());

        CalculadoraDeTempo calculadora = new CalculadoraDeTempo();
        calculadora.inclui(meuFilme);
        System.out.println("Resultado da calculadora: " + calculadora.getTempoTotal());
        filtro.filtra(meuFilme);

        Filme outroFilme = new Filme("Avatar", 2023);
        outroFilme.setDuracaoEmMinutos(200);
        outroFilme.setDiretor("N/A");

        outroFilme.avalia(5);
        outroFilme.avalia(4.4);
        outroFilme.avalia(3.1);
        outroFilme.avalia(4.9);
        outroFilme.exibeFichaTecnica();

        calculadora.inclui(outroFilme);
        System.out.println("Resultado da calculadora: " + calculadora.getTempoTotal());

        Serie onePiece = new Serie();
        onePiece.setNome("One Piece");
        onePiece.setAnoLancamento(1999);
        onePiece.setTemporadas(22);
        onePiece.setEpisodiosPorTemporada(20);
        onePiece.setDuracaoEmMinutos(20);
        onePiece.setMinutosPorEpisodio(18);
        onePiece.avalia(5);
        onePiece.avalia(4.9);
        onePiece.avalia(4.8);
        onePiece.avalia(5);
        onePiece.avalia(4.7);
        onePiece.avalia(5);
        onePiece.avalia(4.3);

        onePiece.exibeFichaTecnica();
        System.out.println(onePiece.pegaMedia());
        System.out.println( "Duração para maratonar: " + onePiece.getDuracaoEmMinutos());
        calculadora.inclui(onePiece);
        System.out.println("Calculo tempo Filme + Série: " + calculadora.getTempoTotal());

        Episodio episodio = new Episodio();
        episodio.setNumero(1);
        episodio.setTotalVisualizacoes(500);
        episodio.setSerie(onePiece);
        filtro.filtra(episodio);

        Episodio episodio2 = new Episodio();
        episodio2.setNumero(2);
        episodio2.setTotalVisualizacoes(1);
        episodio2.setSerie(onePiece);
        filtro.filtra(episodio2);

        var filmeAlain = new Filme("Poeira em alto mar"); // fazendo inferência
        //filmeAlain.setNome("Poeira em alto mar");
        filmeAlain.setDuracaoEmMinutos(250);
        filmeAlain.setAnoLancamento(1910);
        filmeAlain.avalia(10);

        ArrayList<Filme> listaDeFilmes = new ArrayList<>();

        listaDeFilmes.add(meuFilme);
        listaDeFilmes.add(outroFilme);
        listaDeFilmes.add(filmeAlain);

        System.out.println("Tamanho da lista: " + listaDeFilmes.size());
        System.out.println("Primeiro filme: " + listaDeFilmes.getFirst().getNome());
        System.out.println("Pegando segundo registro: " + listaDeFilmes.get(1).getNome());

        System.out.println(listaDeFilmes); // estamos usando toString na Classe Filme, dessa forma apresentará o nome dos filmes e não o "endereço" de cada classe

    }
}