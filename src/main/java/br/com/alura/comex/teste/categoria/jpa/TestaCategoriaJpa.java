package br.com.alura.comex.teste.categoria.jpa;

import br.com.alura.comex.dao.jpa.JpaCategoriaDao;
import br.com.alura.comex.model.Categoria;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class TestaCategoriaJpa {

    public static void main(String[] args) {
        EntityManagerFactory comex = Persistence.createEntityManagerFactory("comex");
        EntityManager entityManager = comex.createEntityManager();

        Categoria automotiva = new Categoria();
        automotiva.setNome("Automotiva");
        automotiva.setDescricao("Utensílios para seu automóvel!");

        JpaCategoriaDao dao = new JpaCategoriaDao(entityManager);
        dao.cadastra(automotiva);

        entityManager.close();
        comex.close();
    }
}
