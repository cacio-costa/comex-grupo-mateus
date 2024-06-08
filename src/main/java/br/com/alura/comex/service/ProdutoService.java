package br.com.alura.comex.service;

import br.com.alura.comex.dao.ProdutoDao;
import br.com.alura.comex.exception.EntidadeNaoEncontradaException;
import br.com.alura.comex.model.Produto;

import java.util.List;

public class ProdutoService {

    private ProdutoDao produtoDao;

    public ProdutoService() {
        this.produtoDao = new ProdutoDao();
    }

    public void efetuaCadastroDeProduto(Produto novoProduto) {
        System.out.println("Validando dados de produto...");

        this.produtoDao.cadastra(novoProduto);
    }

    public List<Produto> listaProdutos() {
        return this.produtoDao.listaTodos();
    }

    public void excluiProdutoPeloId(long id) throws EntidadeNaoEncontradaException {
        Produto produtoParaExcluir = produtoDao.consulta(id);
        if (produtoParaExcluir == null) {
            throw new EntidadeNaoEncontradaException("Produto não está cadastrado: " + id);
        }

        this.produtoDao.exclui(produtoParaExcluir);
    }

    public void efetuaAlteracaoDoProduto(Produto produto) {
        System.out.println("Validando dados do produto...");
        this.produtoDao.atualiza(produto);
    }
}