public class Principal {
    public static void main(String[] args){

        ConsultaCep consultaCep = new ConsultaCep();

        try {


            Endereco endereco = consultaCep.buscaEndereco("31540440");
            System.out.println(endereco);

            Endereco endereco2 = consultaCep.buscaEndereco("01001000");
            System.out.println(endereco2);

            Endereco endereco3 = consultaCep.buscaEndereco("1");
            System.out.println(endereco3);
        }catch (RuntimeException e){
            System.out.println(e);
        }finally {
            System.out.println("finalizando a aplicação");
        }
    }
}
