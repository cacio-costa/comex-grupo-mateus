package br.com.alura.comex.dao;

import br.com.alura.comex.banco.ConnectionFactory;
import br.com.alura.comex.banco.DatabaseUtils;
import br.com.alura.comex.model.Categoria;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoriaDao {

    public List<Categoria> listaTodos() {
        String sql = "select * from categoria";

        ConnectionFactory connectionFactory = new ConnectionFactory();
        try (Connection conexao = connectionFactory.criaConexao()) {
            PreparedStatement preparoDoComando = conexao.prepareStatement(sql);
            ResultSet resultSet = preparoDoComando.executeQuery();

            List<Categoria> lista = new ArrayList<>();
            while (resultSet.next()) {
                lista.add(montaCategoria(resultSet));
            }

            preparoDoComando.close();
            resultSet.close();

            return lista;
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao consultar todas as categorias", e);
        }
    }

    private Categoria montaCategoria(ResultSet resultSet) throws SQLException {
        Categoria categoria = new Categoria();
        categoria.setId(resultSet.getLong("id"));
        categoria.setNome(resultSet.getString("nome"));

        return categoria;
    }

    public void cadastra(Categoria categoria) {
        String sql = "insert into categoria (nome, descricao) values (?, ?)";

        try (Connection conexao = new ConnectionFactory().criaConexao()) {
            PreparedStatement comando = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            comando.setString(1, categoria.getNome());
            comando.setString(2, categoria.getDescricao());

            comando.execute();

            Long idGerado = DatabaseUtils.recuperaIdGerado(comando);
            categoria.setId(idGerado);

            comando.close();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao salvar categoria.", e);
        }
    }

    public void exclui(Categoria categoria) {
        String sql = "delete from categoria where id = ?";

        try (Connection conexao = new ConnectionFactory().criaConexao()) {
            PreparedStatement comando = conexao.prepareStatement(sql);
            comando.setLong(1, categoria.getId());

            comando.execute();
            comando.close();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao excluir categoria.", e);
        }
    }

    public void atualiza(Categoria categoria) {
        String sql = "update categoria set nome = ? where id = ?";

        try (Connection conexao = new ConnectionFactory().criaConexao()) {
            PreparedStatement comando = conexao.prepareStatement(sql);
            comando.setString(1, categoria.getNome());
            comando.setLong(2, categoria.getId());

            comando.execute();
            comando.close();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar categoria.", e);
        }
    }

    public Categoria pesquisaPeloId(long id) {
        String sql = "select * from categoria where id = ?";

        ConnectionFactory connectionFactory = new ConnectionFactory();
        try (Connection conexao = connectionFactory.criaConexao()) {
            PreparedStatement preparoDoComando = conexao.prepareStatement(sql);
            preparoDoComando.setLong(1, id);

            ResultSet resultSet = preparoDoComando.executeQuery();

            Categoria possivelCategoria = null;
            if (resultSet.next()) {
                possivelCategoria = montaCategoria(resultSet);
            }

            preparoDoComando.close();
            resultSet.close();

            return possivelCategoria;
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao pesquisar categoria por ID", e);
        }
    }

    public Categoria buscaPorId(long id) {
        String sql = "select * from categoria where id = ?";

        ConnectionFactory connectionFactory = new ConnectionFactory();
        try (Connection conexao = connectionFactory.criaConexao()) {
            PreparedStatement preparoDoComando = conexao.prepareStatement(sql);
            preparoDoComando.setLong(1, id);

            ResultSet resultSet = preparoDoComando.executeQuery();

            Categoria possivelCategoria = null;
            if (resultSet.next()) {
                possivelCategoria = montaCategoria(resultSet);
            }

            preparoDoComando.close();
            resultSet.close();

            return possivelCategoria;
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao pesquisar categoria por ID", e);
        }
    }
}