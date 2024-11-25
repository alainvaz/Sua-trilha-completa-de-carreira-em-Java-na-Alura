package br.com.estudo.alain.screenmatch.principal;

import br.com.estudo.alain.screenmatch.excecao.ErroDeConversaoDeAnoException;
import br.com.estudo.alain.screenmatch.modelos.Titulo;
import br.com.estudo.alain.screenmatch.modelos.TituloOmdb;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GravacaoListaJson {
    public static void main(String[] args) throws IOException, InterruptedException {

        Scanner leitura = new Scanner(System.in);
        String busca = "";
        List<Titulo> titulos = new ArrayList<>();

        Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE)
                .setPrettyPrinting()
                .create();

        while (!busca.equalsIgnoreCase("sair")) {
            System.out.println("Digite um filme para busca: ");
            busca = leitura.nextLine();

            if(busca.equalsIgnoreCase("sair")){
                break;
            }

            String endereco = "https://www.omdbapi.com/?t=" + busca.replace(" ", "+") + "&apikey=bdb1b12c";
            System.out.println(endereco);
            try {
                HttpClient client = HttpClient.newHttpClient();
                HttpRequest request = HttpRequest.newBuilder()
                        .uri(URI.create(endereco))
                        .build();
                HttpResponse<String> response = client
                        .send(request, HttpResponse.BodyHandlers.ofString());

                String json = response.body();
                //System.out.println(json);

                TituloOmdb meuTituloOmdb = gson.fromJson(json, TituloOmdb.class);
                //System.out.println(meuTituloOmdb);

                Titulo meuTitulo = new Titulo(meuTituloOmdb);
                //System.out.println("Titulo já convertido");
                //System.out.println(meuTitulo);

                titulos.add(meuTitulo);

            } catch (ErroDeConversaoDeAnoException e) {
                System.out.println("Aconteceu um erro: " + e.getMessage());
            }catch (NumberFormatException e){
                System.out.println("Aconteceu algum erro: " + e);
            }catch (IllegalArgumentException e){
                System.out.println("Ocorreu erro: " + e);
            }

        }

        System.out.println(titulos);

        FileWriter escrita = new FileWriter("Filmes.json");
        escrita.write(gson.toJson(titulos));
        escrita.close();

        System.out.println("Fim programa");


    }

}
