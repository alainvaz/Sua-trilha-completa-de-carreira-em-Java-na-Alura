package br.com.estudo.alain.screenmatch.modelos;

import br.com.estudo.alain.screenmatch.calculo.Classificavel;

public class Filme extends Titulo implements Classificavel {

    private String Diretor;

    public Filme(){

    }

    public Filme(String nome){
        super.setNome(nome);
    }

    public Filme(String nome, int anoDeLancamento){
        super(nome,anoDeLancamento);
    }

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

    @Override
    public String toString() {
        return "Filme: " + this.getNome() + " (" + this.getAnoLancamento() + ")" ;
    }
}
