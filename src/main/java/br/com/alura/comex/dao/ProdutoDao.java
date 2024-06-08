package br.com.alura.comex.dao;

import br.com.alura.comex.banco.ConnectionFactory;
import br.com.alura.comex.banco.DatabaseUtils;
import br.com.alura.comex.model.Produto;
import br.com.alura.comex.model.Categoria;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProdutoDao {

    public void cadastra(Produto produto) {
        String sql = "insert into produto (nome, descricao, preco) values (?, ?, ?)";

        try (Connection conexao = new ConnectionFactory().criaConexao()) {
            PreparedStatement comando = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            comando.setString(1, produto.getNome());
            comando.setString(2, produto.getDescricao());
            comando.setDouble(3, produto.getPreco());

            comando.execute();
            Long idGerado = DatabaseUtils.recuperaIdGerado(comando);
            produto.setId(idGerado);

            insereCategoriasProduto(produto);

            comando.close();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao salvar produto.", e);
        }
    }

    public void atualiza(Produto produto) {
        String sql = "update produto set nome = ?, descricao = ?, preco = ? where id = ?";

        try (Connection conexao = new ConnectionFactory().criaConexao()) {
            PreparedStatement comando = conexao.prepareStatement(sql);
            comando.setString(1, produto.getNome());
            comando.setString(2, produto.getDescricao());
            comando.setDouble(3, produto.getPreco());
            comando.setLong(4, produto.getId());

            comando.execute();

            // Atualiza as categorias associadas ao produto
            excluiCategoriasProduto(produto);
            insereCategoriasProduto(produto);

            comando.close();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar produto.", e);
        }
    }

    public void exclui(Produto produto) {
        String sql = "delete from produto where id = ?";

        try (Connection conexao = new ConnectionFactory().criaConexao()) {
            // Exclui as categorias associadas ao produto
            excluiCategoriasProduto(produto);

            PreparedStatement comando = conexao.prepareStatement(sql);
            comando.setLong(1, produto.getId());

            comando.execute();
            comando.close();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao excluir produto.", e);
        }
    }

    public List<Produto> listaTodos() {
        String sql = "select p.*, c.* from produto p " +
                "left join categoria_produto cp on p.id = cp.produto_id " +
                "left join categoria c on cp.categoria_id = c.id " +
                "order by p.id";

        try (Connection conexao = new ConnectionFactory().criaConexao()) {
            PreparedStatement comando = conexao.prepareStatement(sql);
            ResultSet resultSet = comando.executeQuery();

            List<Produto> produtos = new ArrayList<>();
            Produto produto = null;

            while (resultSet.next()) {
                Long produtoId = resultSet.getLong("p.id");

                if (produto == null || !produto.getId().equals(produtoId)) {
                    produto = new Produto();
                    produto.setId(produtoId);
                    produto.setNome(resultSet.getString("p.nome"));
                    produto.setDescricao(resultSet.getString("p.descricao"));
                    produto.setPreco(resultSet.getDouble("p.preco"));

                    produtos.add(produto);
                }

                Long categoriaId = resultSet.getLong("c.id");
                if (!resultSet.wasNull()) {
                    Categoria categoria = new Categoria();
                    categoria.setId(categoriaId);
                    categoria.setNome(resultSet.getString("c.nome"));

                    produto.adicionaCategoria(categoria);
                }
            }

            comando.close();
            return produtos;
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar todos os produtos.", e);
        }
    }

    private void excluiCategoriasProduto(Produto produto) {
        String sql = "delete from categoria_produto where produto_id = ?";

        try (Connection conexao = new ConnectionFactory().criaConexao()) {
            PreparedStatement comando = conexao.prepareStatement(sql);
            comando.setLong(1, produto.getId());

            comando.execute();
            comando.close();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao excluir categorias do produto.", e);
        }
    }

    private void insereCategoriasProduto(Produto produto) {
        String sql = "insert into categoria_produto (produto_id, categoria_id) values (?, ?)";

        try (Connection conexao = new ConnectionFactory().criaConexao()) {
            PreparedStatement comando = conexao.prepareStatement(sql);

            for (Categoria categoria : produto.getCategorias()) {
                comando.setLong(1, produto.getId());
                comando.setLong(2, categoria.getId());
                comando.execute();
            }

            comando.close();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao salvar categorias do produto.", e);
        }
    }

    public Produto consulta(long id) {
        String sql = "select p.*, c.* from produto p " +
                "left join categoria_produto cp on p.id = cp.produto_id " +
                "left join categoria c on cp.categoria_id = c.id " +
                "where p.id = ?";

        try (Connection conexao = new ConnectionFactory().criaConexao()) {
            PreparedStatement comando = conexao.prepareStatement(sql);
            comando.setLong(1, id);
            ResultSet resultSet = comando.executeQuery();

            Produto produto = null;

            while (resultSet.next()) {
                if (produto == null) {
                    produto = new Produto();
                    produto.setId(resultSet.getLong("p.id"));
                    produto.setNome(resultSet.getString("p.nome"));
                    produto.setDescricao(resultSet.getString("p.descricao"));
                    produto.setPreco(resultSet.getDouble("p.preco"));
                }

                Long categoriaId = resultSet.getLong("c.id");
                if (!resultSet.wasNull()) {
                    Categoria categoria = new Categoria();
                    categoria.setId(categoriaId);
                    categoria.setNome(resultSet.getString("c.nome"));

                    produto.adicionaCategoria(categoria);
                }
            }

            comando.close();
            return produto;
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao consultar produto.", e);
        }
    }
}