import java.util.Collections;
import java.util.Scanner;

public class Principal {
    public static void main(String[] args) {
        Scanner leitura = new Scanner(System.in);
        System.out.println("Informe o limite do cartão: ");
        double limite = leitura.nextDouble();

        CartaoDeCredito cartaoDeCredito = new CartaoDeCredito(limite);

        int sair = 1;

        while (sair !=0){
            System.out.println("Digite a descricao da compra: ");
            String descricao = leitura.next();


            System.out.println("Digite o valor da compra: ");
            double valor = leitura.nextDouble();

            Compra compra = new Compra(descricao, valor);
            boolean compraRealizada = cartaoDeCredito.lancaCompra(compra);

            if(compraRealizada){
                System.out.println("Compra aprovada!");
                System.out.println("Informe 0 para sair ou 1 para continuar");
                sair = leitura.nextInt();
            }else{
                System.out.println("Saldo insuficiente");
                System.out.println("Informe 0 para sair ou 1 para continuar");
                sair = leitura.nextInt();
            }
        }

        System.out.println("**********************");
        System.out.println("Compras realizadas:\n");

        Collections.sort(cartaoDeCredito.getCompras());
        for (Compra c: cartaoDeCredito.getCompras()) {
            System.out.println(c.getDescricao() + " - " + c.getValor());
        }

        System.out.println("**********************\n");

        System.out.println("\nSaldo do cartão: " + cartaoDeCredito.getSaldo());
    }
}
