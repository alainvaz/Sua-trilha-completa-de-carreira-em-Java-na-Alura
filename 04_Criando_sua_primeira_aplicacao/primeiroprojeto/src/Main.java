public class Main {
    public static void main(String[] args) {
        System.out.println("Esse é o Screen Match");

        String nomeFillme = "Filme: Top Gun: Maverick";
        System.out.println(nomeFillme);
        
        int anoDeLancamento = 2022;
        System.out.println("Ano de lançamento do filme: " + anoDeLancamento);

        boolean incluidoNoPlano = true;
        System.out.println("O Filme está no plano? " + incluidoNoPlano);

        double notaFilme = 8.1;
        System.out.println("Nota do filme: " + notaFilme);

        // média calculada pelas notas do Alain, Graciele e Júlia
        double media  = (9.8 + 6.3 + 8) / 3;
        System.out.println("Média do filme: " + media);

        String sinopse;

        sinopse = """
                Filme Top Gun
                Filme de aventura do galã dos anos 80
                Muito bom!
                Ano de Lançamento:
                """ + anoDeLancamento;

        System.out.println(sinopse);

        int classificacao = (int)media; // cast para conversão do valor, mas pode ocorrer de perder informação
        System.out.println(classificacao);

    }
}