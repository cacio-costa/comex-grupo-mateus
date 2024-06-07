package br.com.alura.comex.teste;

import br.com.alura.comex.model.Cliente;
import br.com.alura.comex.service.ClienteService;

public class TestaAlteracaoDeCliente {

    public static void main(String[] args) {
        Cliente novoCliente = new Cliente();
        novoCliente.setId(4L);
        novoCliente.setNome("Grupo Quem Disse que Acabou");
        novoCliente.setEmail("grupoqdqa@gmail.com");
        novoCliente.setTelefone("(61) 99999-9999");
        novoCliente.setCpf("666.777.888-99");
        novoCliente.setLogradouro("EPTG - Deck Bar");
        novoCliente.setBairro("Vicente Pires");
        novoCliente.setCidade("Águas Claras");
        novoCliente.setEstado("DF");
        novoCliente.setCep("72000-000");

        ClienteService service = new ClienteService();
        service.efetuaAlteracaoDoCliente(novoCliente);

        System.out.println("Cliente cadastrado com sucesso!");
    }
}
