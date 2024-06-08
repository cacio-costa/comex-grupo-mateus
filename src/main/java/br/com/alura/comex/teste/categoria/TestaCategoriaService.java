package br.com.alura.comex.teste.categoria;

import br.com.alura.comex.exception.EntidadeNaoEncontradaException;
import br.com.alura.comex.model.Categoria;
import br.com.alura.comex.service.CategoriaService;

public class TestaCategoriaService {

    public static void main(String[] args) throws EntidadeNaoEncontradaException {
        CategoriaService service = new CategoriaService();

        // Criando e cadastrando três categorias
        Categoria categoria1 = new Categoria();
        categoria1.setNome("Categoria 1");
        service.efetuaCadastroDeCategoria(categoria1);

        Categoria categoria2 = new Categoria();
        categoria2.setNome("Categoria 2");
        service.efetuaCadastroDeCategoria(categoria2);

        Categoria categoria3 = new Categoria();
        categoria3.setNome("Categoria 3");
        service.efetuaCadastroDeCategoria(categoria3);
        System.out.println("3 Categorias cadastradas com sucesso!");
        System.out.println("========================================\n");

        // Listando todas as categorias
        System.out.println("\nListando todas as categorias:");
        service.listaCategorias().forEach(System.out::println);
        System.out.println("========================================\n");

        // Alterando as categorias
        categoria1.setNome("Categoria 1 Alterada");
        service.efetuaAlteracaoDaCategoria(categoria1);

        categoria2.setNome("Categoria 2 Alterada");
        service.efetuaAlteracaoDaCategoria(categoria2);

        categoria3.setNome("Categoria 3 Alterada");
        service.efetuaAlteracaoDaCategoria(categoria3);
        System.out.println("Categorias alteradas com sucesso!");
        System.out.println("========================================\n");

        // Listando todas as categorias novamente
        System.out.println("\nListando todas as categorias após alterações:");
        service.listaCategorias().forEach(System.out::println);

        // Excluindo as categorias
        System.out.println("\nExcluindo as categorias...");
        service.excluiCategoriaPeloId(categoria1.getId());
        System.out.println("Categoria 1 excluída com sucesso!");

        service.excluiCategoriaPeloId(categoria2.getId());
        System.out.println("Categoria 2 excluída com sucesso!");

        service.excluiCategoriaPeloId(categoria3.getId());
        System.out.println("Categoria 3 excluída com sucesso!");
    }
}