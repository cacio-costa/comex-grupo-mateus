package br.com.alura.comex.teste.cliente;

import br.com.alura.comex.exception.EntidadeNaoEncontradaException;
import br.com.alura.comex.service.ClienteService;

public class TestaExclusaoDeCliente {

    public static void main(String[] args) {
        ClienteService service = new ClienteService();
        try {
            service.excluiClientePeloId(1L);
            System.out.println("Cliente excluído com sucesso!");
        } catch (EntidadeNaoEncontradaException e) {
            System.out.println(e.getMessage());
        }

    }
}
