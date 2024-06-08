package br.com.alura.comex.teste.cliente;

import br.com.alura.comex.model.Cliente;
import br.com.alura.comex.service.ClienteService;

import java.sql.SQLException;
import java.util.List;

public class TestaConsultaDeCliente {

    public static void main(String[] args) throws SQLException {
        ClienteService service = new ClienteService();
        List<Cliente> clientes = service.listaClientes();

        for (Cliente cliente : clientes) {
            System.out.println("ID: " + cliente.getId());
            System.out.println("NOME: " + cliente.getNome());
            System.out.println("CPF: " + cliente.getCpf());
            System.out.println("EMAIL: " + cliente.getEmail());
            System.out.println("TELEFONE: " + cliente.getTelefone());
            System.out.println("LOGRADOURO: " + cliente.getLogradouro());
            System.out.println("BAIRRO: " + cliente.getBairro());
            System.out.println("CIDADE: " + cliente.getCidade());
            System.out.println("ESTADO: " + cliente.getEstado());
            System.out.println("CEP: " + cliente.getCep());
            System.out.println("========================================");
            System.out.println();
        }
    }
}
