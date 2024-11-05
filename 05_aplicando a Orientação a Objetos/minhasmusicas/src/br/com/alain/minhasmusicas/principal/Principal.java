package br.com.alain.minhasmusicas.principal;

import br.com.alain.minhasmusicas.modelos.MinhasPreferidas;
import br.com.alain.minhasmusicas.modelos.Musica;
import br.com.alain.minhasmusicas.modelos.Podcast;

public class Principal {
    public static void main(String args[]){
        Musica minhaMusica = new Musica();
        minhaMusica.setTitulo("Comfortably numb");
        minhaMusica.setCantor("Pink Floyd");

        for (int i = 0; i < 1001; i++) {
            minhaMusica.reproduz();
        }

        for (int i = 0; i < 50 ; i++) {
            minhaMusica.curte();
        }

        Podcast meuPodcast = new Podcast();
        meuPodcast.setApresentador("Sergio Sacani");
        meuPodcast.setTitulo("Urano e Neturo, os planetas esquecidos");
        meuPodcast.setDescricao("Vamos falar mais sobre os planetas esquecidos pelos seres humanos, visitados apenas uma vez na história!");

        for (int i = 0; i < 350; i++) {
            meuPodcast.reproduz();
        }

        for (int i = 0; i < 100; i++) {
            meuPodcast.curte();
        }

        MinhasPreferidas minhasPreferidas = new MinhasPreferidas();
        minhasPreferidas.inclui(minhaMusica);
        minhasPreferidas.inclui(meuPodcast);

    }
}
