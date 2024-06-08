package br.com.alura.comex.service;

import br.com.alura.comex.dao.CategoriaDao;
import br.com.alura.comex.exception.EntidadeNaoEncontradaException;
import br.com.alura.comex.model.Categoria;

import java.util.List;

public class CategoriaService {

    private CategoriaDao categoriaDao;

    public CategoriaService() {
        this.categoriaDao = new CategoriaDao();
    }

    public void efetuaCadastroDeCategoria(Categoria novaCategoria) {
        System.out.println("Validando dados de categoria...");

        this.categoriaDao.cadastra(novaCategoria);
    }

    public List<Categoria> listaCategorias() {
        return this.categoriaDao.listaTodos();
    }

    public void excluiCategoriaPeloId(long id) throws EntidadeNaoEncontradaException {
        Categoria categoriaParaExcluir = categoriaDao.pesquisaPeloId(id);
        if (categoriaParaExcluir == null) {
            throw new EntidadeNaoEncontradaException("Categoria não está cadastrada: " + id);
        }

        this.categoriaDao.exclui(categoriaParaExcluir);
    }

    public void efetuaAlteracaoDaCategoria(Categoria categoria) {
        System.out.println("Validando dados da categoria...");

        this.categoriaDao.atualiza(categoria);
    }
}