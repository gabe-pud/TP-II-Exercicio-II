package br.edu.fatecpg.consomeapi.view;

import br.edu.fatecpg.consomeapi.controller.EnderecoController;
import br.edu.fatecpg.consomeapi.model.Endereco;
import br.edu.fatecpg.consomeapi.service.BuscaEndereco;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        BuscaEndereco be = new BuscaEndereco();
        EnderecoController ec = new EnderecoController();
        Scanner sc = new Scanner(System.in);
        Gson gson = new Gson();

        String menu = """
                1 - buscar endereco
                2 - mostar enderecos salvos
                3 - sair""";

        String salvarMenu = """
                endereco no banco
                1 - salvar
                2 - não salvar""";
        boolean run = true;
        while (run){

            System.out.println(menu);
            String escolha = sc.next();
            switch (escolha){
                case "1":
                    System.out.println("digite o cep: ");
                    String cep = sc.next();
                    String end = be.obterEndereco(cep);
                    Endereco novoEndereco = gson.fromJson(end,Endereco.class);
                    System.out.println(novoEndereco.toString());
                    System.out.println(salvarMenu);
                    switch (sc.next()){
                        case "1":
                            ec.salvarEndereco(novoEndereco);
                            break;
                        case "2":
                            break;
                    }
                    break;
                case "2":
                    ec.listEnderecos();
                    break;
                case "3":
                    run = false;
                    break;
            }

        }
    }
}
