import java.util.Scanner;

public class Desafio {
    public static void main(String args[]){
            String nomeCliente = "Alain Vaz Nascimento";
            String tipoConta = "Corrente";
            double saldo = 2500.00;

        System.out.println("Dados iniciais do cliente");
        System.out.println("**************************************");
        System.out.println("Nome:           " + nomeCliente);
        System.out.println("Tipo de Conta:  " + tipoConta);
        System.out.println("Saldo na conta: " + saldo);
        System.out.println("**************************************");

        System.out.println();
        String menu = """
                ** Digite sua opção **
                1 - Consultar saldo
                2 - Transferir valor
                3 - Receber valor
                4 - Sair

                """;
        System.out.println(menu);

        Scanner leitura = new Scanner(System.in);

        int opcao = 0;
        while (opcao != 4){
            System.out.println("Digite uma opção: ");
            opcao = leitura.nextInt();

            if(opcao == 1){
                System.out.println("O saldo é de R$ " + saldo);
            } else if (opcao == 2) {
                System.out.println("Informe o valor recebido:");
                double valorTransacao = leitura.nextDouble();
                if(valorTransacao > 0){
                    saldo += valorTransacao;
                }
            }else{
                if(opcao == 3){
                    System.out.println("Informe o valor transferido:");
                    double valorTransacao = leitura.nextDouble();
                    if(valorTransacao > 0){
                        if(saldo >= valorTransacao){
                            saldo -= valorTransacao;
                        }else{
                            System.out.println("Saldo insuficiente");
                        }
                    }
                }else{
                    System.out.println("Opção inválida!");
                }
            }
        }
    }
}
