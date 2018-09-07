package alura.com.br.agenda;

import java.io.IOException;
import java.io.PrintStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

/**
 * Created by Pedro Paulo on 29/12/2017.
 */

// Classe reposnável por fazer a conexão com o servidor.

public class WebClient {


    public String post(String json){
        try {
            URL url = new URL("https://www.caelum.com.br/mobile"); // Endereço do servidor
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestProperty("Content-type", "application/json"); // Configura o tipo de dado que é enviado
            connection.setRequestProperty("Accept", "application/json"); // Configura o tipo de dado que é aceito como resposta

            // Configura o envio de dados no corpo da requisição
            connection.setDoOutput(true);
            PrintStream output = new PrintStream(connection.getOutputStream());
            output.println(json);

            connection.connect(); // Tenta estabelecer uma conexão com o servidor

            Scanner scanner = new Scanner(connection.getInputStream());
            String resposta = scanner.next(); // Método next() lê a resposta do servidor

            return resposta;

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }


}
