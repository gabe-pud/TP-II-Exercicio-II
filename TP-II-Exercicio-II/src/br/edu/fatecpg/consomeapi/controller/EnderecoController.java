package br.edu.fatecpg.consomeapi.controller;

import br.edu.fatecpg.consomeapi.db.Banco;
import br.edu.fatecpg.consomeapi.model.Endereco;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EnderecoController {
    List<Endereco> enderecos = new ArrayList<>();

    public void salvarEndereco(Endereco end){
        String query = "INSERT INTO endereco VALUES (?,?,?,?,?)";
        try (var connection = Banco.connect()){
            assert connection != null;
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setString(1,end.getCep());
            stmt.setString(2,end.getRua());
            stmt.setString(3,end.getBairro());
            stmt.setString(4,end.getLocalidade());
            stmt.setString(5,end.getEstado());
            stmt.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void listEnderecos(){
        String query = "SELECT * FROM endereco";
        try (var connection = Banco.connect()){
            assert connection != null;
            PreparedStatement stmt = connection.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            enderecos.clear();
            while (rs.next()){
                enderecos.add(new Endereco(rs.getString("cep"),
                        rs.getString("rua"),
                        rs.getString("bairro"),
                        rs.getString("localidade"),
                        rs.getString("estado")));
            }
            for (Endereco e: enderecos){
                System.out.println(e.toString());
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
