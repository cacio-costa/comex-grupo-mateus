package br.com.alura.comex.teste.produto;

import br.com.alura.comex.dao.CategoriaDao;
import br.com.alura.comex.exception.EntidadeNaoEncontradaException;
import br.com.alura.comex.model.Produto;
import br.com.alura.comex.model.Categoria;
import br.com.alura.comex.service.ProdutoService;

public class TestaProdutoService {

    public static void main(String[] args) throws EntidadeNaoEncontradaException {
        ProdutoService service = new ProdutoService();
        CategoriaDao categoriaDao = new CategoriaDao();

        // Buscando as categorias existentes no banco de dados
        // ATENÇÃO: VOCÊ DEVE TER CRIADO AS CATEGORIAS NO BANCO DE DADOS ANTES DE EXECUTAR ESTE CÓDIGO
        Categoria eletronicos = categoriaDao.buscaPorId(12L);
        Categoria livros = categoriaDao.buscaPorId(13L);
        Categoria roupas = categoriaDao.buscaPorId(14L);
        Categoria casaECozinha = categoriaDao.buscaPorId(15L);
        Categoria esportesELazer = categoriaDao.buscaPorId(16L);

        // Criando e cadastrando três produtos
        for (int i = 1; i <= 3; i++) {
            Produto produto = new Produto();
            produto.setNome("Produto " + i);
            produto.setDescricao("Descrição do Produto " + i);
            produto.setPreco(i * 10.0);

            // Adicionando as categorias ao produto
            produto.adicionaCategoria(eletronicos);
            produto.adicionaCategoria(livros);
            produto.adicionaCategoria(roupas);
            produto.adicionaCategoria(casaECozinha);
            produto.adicionaCategoria(esportesELazer);

            service.efetuaCadastroDeProduto(produto);
        }
        System.out.println("3 Produtos cadastrados com sucesso!");
        System.out.println("========================================\n");

        // Listando todos os produtos
        System.out.println("\nListando todos os produtos:");
        service.listaProdutos().forEach(System.out::println);
        System.out.println("========================================\n");

        // Alterando os produtos
        System.out.println("\nAlterando os produtos...");
        service.listaProdutos().forEach(produto -> {
            produto.setNome(produto.getNome() + " - alterado");
            service.efetuaAlteracaoDoProduto(produto);
        });
        System.out.println("Produtos alterados com sucesso!");
        System.out.println("========================================\n");

        // Listando todos os produtos novamente
        System.out.println("\nListando todos os produtos após alterações:");
        service.listaProdutos().forEach(System.out::println);

        // Excluindo os produtos
        System.out.println("\nExcluindo os produtos...");
        service.listaProdutos().forEach(produto -> {
            try {
                service.excluiProdutoPeloId(produto.getId());
            } catch (EntidadeNaoEncontradaException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Produto " + produto.getNome() + " excluído com sucesso!");
        });
    }
}