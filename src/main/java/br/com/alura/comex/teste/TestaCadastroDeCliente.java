package br.com.alura.comex.teste;

import br.com.alura.comex.model.Cliente;
import br.com.alura.comex.service.ClienteService;

public class TestaCadastroDeCliente {

    public static void main(String[] args) {
        Cliente novoCliente = new Cliente();
        novoCliente.setNome("Grupo Quem Disse que Acabou");
        novoCliente.setEmail("grupoqdqa@gmail.com");
        novoCliente.setTelefone("(61) 98448-1019");
        novoCliente.setCpf("666.777.888-99");
        novoCliente.setLogradouro("Deck Bar");
        novoCliente.setBairro("Vicente Pires");
        novoCliente.setCidade("Águas Claras");
        novoCliente.setEstado("DF");
        novoCliente.setCep("72000-000");

        ClienteService service = new ClienteService();
        service.efetuaCadastroDeCliente(novoCliente);

        System.out.println("Cliente cadastrado com sucesso!");
    }
}
