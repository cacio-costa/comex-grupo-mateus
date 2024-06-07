package br.com.alura.comex.banco;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

    public Connection criaConexao() {
        String usuario = "root";
        String senha = "";

        try {
            return DriverManager.getConnection("jdbc:mysql://localhost:3307/comex", usuario, senha);
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao tentar se conectar no banco de dados.");
        }
    }
}
