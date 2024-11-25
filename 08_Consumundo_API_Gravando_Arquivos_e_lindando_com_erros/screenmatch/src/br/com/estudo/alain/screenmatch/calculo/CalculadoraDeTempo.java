package br.com.estudo.alain.screenmatch.calculo;

import br.com.estudo.alain.screenmatch.modelos.Filme;
import br.com.estudo.alain.screenmatch.modelos.Serie;
import br.com.estudo.alain.screenmatch.modelos.Titulo;

public class CalculadoraDeTempo {
    private int tempoTotal;

    public int getTempoTotal(){
        return this.tempoTotal;
    }
// Exemolo de sobrecarga de métodos:
//    public void inclui(Filme filme){
//        this.tempoTotal += filme.getDuracaoEmMinutos();
//    }
//
//    public void inclui(Serie serie){
//        this.tempoTotal += serie.getDuracaoEmMinutos();
//    }

    //Evitando duplicidade de código utilizando a classe mãe, o getDuracaoEmMinutos será utilizado de acordo com a classe filha (Será utilizado Polimorfismo)
    public void inclui(Titulo titulo){
        System.out.println("Adicionando duração em minutos de: " + titulo);
        this.tempoTotal += titulo.getDuracaoEmMinutos();
    }

}
