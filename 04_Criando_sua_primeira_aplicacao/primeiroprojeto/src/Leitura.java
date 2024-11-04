import java.util.Scanner;

public class Leitura {
    public static void main(String args[]){
        Scanner leitura = new Scanner(System.in);

        System.out.println("Digite seu filme favorito: ");
        String filme = leitura.nextLine();

        System.out.println("Qual o ano de lancamento? ");
        int anoDeLancamento = leitura.nextInt();

        System.out.println("Qual sua avaliação sobre o filme?");
        double avaliacao = leitura.nextDouble();

        System.out.println("Seu filme favorito é o: " + filme);
        System.out.println("O filme foi lançado em: " + anoDeLancamento);
        System.out.println("Sua avaliação desse filme: " + avaliacao);
    }
}
