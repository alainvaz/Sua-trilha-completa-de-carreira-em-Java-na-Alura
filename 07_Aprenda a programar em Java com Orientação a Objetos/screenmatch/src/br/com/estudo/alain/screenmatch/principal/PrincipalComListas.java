package br.com.estudo.alain.screenmatch.principal;

import br.com.estudo.alain.screenmatch.modelos.Filme;
import br.com.estudo.alain.screenmatch.modelos.Serie;
import br.com.estudo.alain.screenmatch.modelos.Titulo;

import java.util.*;

public class PrincipalComListas {
    public static void main(String args[]){
        Filme meuFilme = new Filme("O poderoso chefão", 1970);
        meuFilme.avalia(9);
        Filme outroFilme = new Filme("Avatar", 2023);
        outroFilme.avalia(6);
        var filme3 = new Filme("Dogville", 2003);
        filme3.avalia(10);
        Serie lost = new Serie("Lost", 2000);

        //ArrayList<Titulo> lista = new ArrayList<>(); // Parametrizando a lista, utilizando Generics
        //List<Titulo> lista = new ArrayList<>();
        List<Titulo> lista = new LinkedList<>();
        lista.add(filme3);
        lista.add(meuFilme);
        lista.add(outroFilme);
        lista.add(lost);

        for (Titulo item: lista){
            System.out.println(item);
        }

        System.out.println("**********************");

        for (Titulo item: lista){
            System.out.println(item.getNome());
            if (item instanceof Filme filme && filme.getClassificacao() > 2){
                System.out.println("Classificação: " + filme.getClassificacao());
            }
        }

        ArrayList<String> buscaPorArtista = new ArrayList<>();
        buscaPorArtista.add("Leonardo de Caprio");
        buscaPorArtista.add("Júlia Pio");
        buscaPorArtista.add("Alain Nascimento");

        System.out.println(buscaPorArtista);

        Collections.sort(buscaPorArtista);
        System.out.println(buscaPorArtista);

        Collections.sort(lista);

        System.out.println("Lista de titulos ordenadas: ");
        System.out.println(lista);

        lista.sort(Comparator.comparing(Titulo::getAnoLancamento));
        System.out.println(lista);
    }
}
