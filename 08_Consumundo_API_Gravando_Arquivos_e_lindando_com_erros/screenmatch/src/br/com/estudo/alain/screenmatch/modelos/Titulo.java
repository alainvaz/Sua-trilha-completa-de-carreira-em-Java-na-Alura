package br.com.estudo.alain.screenmatch.modelos;

import br.com.estudo.alain.screenmatch.excecao.ErroDeConversaoDeAnoException;
import com.google.gson.annotations.SerializedName;

public class Titulo implements Comparable<Titulo>{

    @SerializedName("Title")
    private String nome;
    @SerializedName("Year")
    private int anoLancamento;
    private boolean incluidoNoPlano;
    private double somaAvaliacoes;
    private int totalAvaliacoes;
    private int duracaoEmMinutos;

    public Titulo(){

    }

    public Titulo(String nome, int anoLancamento) {
        this.nome = nome;
        this.anoLancamento = anoLancamento;
    }

    public Titulo(TituloOmdb tituloOmdb) {
        this.nome = tituloOmdb.title();

        if(tituloOmdb.year().length() > 4){
            throw new ErroDeConversaoDeAnoException("Nao consegui converter o ano, pois o mesmo possui mais de quatro caracteres");
        }

        this.anoLancamento = Integer.valueOf(tituloOmdb.year());
        this.duracaoEmMinutos = Integer.valueOf(tituloOmdb.runtime().substring(0,2));
    }

    public String getNome(){
        return this.nome;
    }

    public void setNome(String nome){
        this.nome = nome;
    }

    public int getAnoLancamento(){
        return this.anoLancamento;
    }

    public void setAnoLancamento(int anoLancamento){
        this.anoLancamento = anoLancamento;
    }

    public boolean getIncluidoNoPlano(){
        return this.incluidoNoPlano;
    }

    public double getSomaAvaliacoes(){
        return this.somaAvaliacoes;
    }

    public void setIncluidoNoPlano(boolean incluidoNoPlano){
        this.incluidoNoPlano = incluidoNoPlano;
    }

    public int getDuracaoEmMinutos() {
        return duracaoEmMinutos;
    }

    public void setDuracaoEmMinutos(int duracaoEmMinutos) {
        this.duracaoEmMinutos = duracaoEmMinutos;
    }

    public void exibeFichaTecnica(){

        System.out.println("Nome do br.com.estudo.alain.screenmatch.modelos: " + this.nome);
        System.out.println("Ano Lançamento: " + this.anoLancamento);
        System.out.println("Duração em minutos:" + this.duracaoEmMinutos);
        System.out.println("Soma das avaliações: " + this.somaAvaliacoes);
        System.out.println("Total de votos: " + this.totalAvaliacoes);
    }

    public void avalia(double nota){
        this.somaAvaliacoes += nota;
        this.totalAvaliacoes++;
    }

    @Override
    public String toString() {
        return "(nome = " + getNome() + '\'' +
                ", anoDeLancamento = " + getAnoLancamento() + ", Duração em minutos = " +
                getDuracaoEmMinutos() + ")";
    }

    public double pegaMedia(){
        return this.somaAvaliacoes / this.totalAvaliacoes;
    }

    @Override
    public int compareTo(Titulo outroTitulo) {
        return this.getNome().compareTo(outroTitulo.getNome());
    }
}

