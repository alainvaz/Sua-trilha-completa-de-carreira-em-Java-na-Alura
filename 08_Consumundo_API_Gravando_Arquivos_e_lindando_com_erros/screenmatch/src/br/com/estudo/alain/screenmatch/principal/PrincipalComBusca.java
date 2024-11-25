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
import java.util.Scanner;

public class PrincipalComBusca {
    public static void main(String[] args) throws IOException, InterruptedException {


        Scanner leitura = new Scanner(System.in);

        // bdb1b12c
        System.out.println("Informe a chave para pesquisar: ");
        String key = leitura.next();

        System.out.println("Digite o filme para busca: " );
        String filme = leitura.next();

        String endereco = "http://www.omdbapi.com/?t=" + filme.replace(" ","+") + "&apikey=" + key;

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(endereco))
                .build();
        HttpResponse<String> response = client
                .send(request, HttpResponse.BodyHandlers.ofString());

        String json = response.body();

        System.out.println(json);

        //Gson gson = new Gson();

        try{

            Gson gson = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE).create();

            //Titulo meuTitulo = gson.fromJson(json, Titulo.class);
            //System.out.println("Meu Titulo: " + meuTitulo);

            TituloOmdb tituloOmdb = gson.fromJson(json, TituloOmdb.class);
            System.out.println("Titulo Omdb: " + tituloOmdb);

            Titulo meuTitulo2 = new Titulo(tituloOmdb);

            System.out.println("Meu titulo convertido: " + meuTitulo2);

            FileWriter escrita = new FileWriter("filmes.txt");
            escrita.write(meuTitulo2.toString());
            escrita.close();

        } catch (ErroDeConversaoDeAnoException e) {
            System.out.println("Aconteceu um erro: " + e.getMessage());
        }finally {
            System.out.println("Fim programa");
        }

    }
}
