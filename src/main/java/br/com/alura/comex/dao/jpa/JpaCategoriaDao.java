package br.com.alura.comex.dao.jpa;

import br.com.alura.comex.model.Categoria;

import javax.persistence.EntityManager;

public class JpaCategoriaDao {

    private EntityManager manager;

    public JpaCategoriaDao(EntityManager manager) {
        this.manager = manager;
    }

    public void cadastra(Categoria categoria) {
        manager.getTransaction().begin();
        manager.persist(categoria);
        manager.getTransaction().commit();
    }
}
