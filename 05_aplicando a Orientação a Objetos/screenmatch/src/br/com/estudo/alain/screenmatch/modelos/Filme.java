package br.com.estudo.alain.screenmatch.modelos;

import br.com.estudo.alain.screenmatch.calculo.Classificavel;

public class Filme extends Titulo implements Classificavel {

    private String Diretor;

    public String getDiretor() {
        return Diretor;
    }

    public void setDiretor(String diretor) {
        Diretor = diretor;
    }


    @Override
    public int getClassificacao() {
        return (int) pegaMedia() / 2 ;
    }
}
