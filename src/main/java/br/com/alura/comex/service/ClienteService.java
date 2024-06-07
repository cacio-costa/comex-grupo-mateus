package br.com.alura.comex.service;

import br.com.alura.comex.dao.ClienteDao;
import br.com.alura.comex.exception.EntidadeNaoEncontradaException;
import br.com.alura.comex.model.Cliente;

import java.util.List;

public class ClienteService {

    private ClienteDao clienteDao;

    public ClienteService() {
        this.clienteDao = new ClienteDao();
    }

    public void efetuaCadastroDeCliente(Cliente novoCliente) {
        System.out.println("Validando dados de cliente...");

        this.clienteDao.cadastra(novoCliente);
        System.out.println("Enviando email para o cliente...");
    }

    public List<Cliente> listaClientes() {
        return this.clienteDao.listaTodos();
    }

    public void excluiClientePeloId(long id) throws EntidadeNaoEncontradaException {
        Cliente clienteParaExcluir = clienteDao.pesquisaPeloId(id);
        if (clienteParaExcluir == null) {
            throw new EntidadeNaoEncontradaException("Cliente não está cadastrado: " + id);
        }

        this.clienteDao.exclui(clienteParaExcluir);
    }

    public void efetuaAlteracaoDoCliente(Cliente cliente) {
        System.out.println("Tem que validar o objeto na atualização também...");

        this.clienteDao.cadastra(cliente);
        System.out.println("Email: se não foi você que solicitou a alteração, sua conta pode ter sido rackeada...");
    }
}
