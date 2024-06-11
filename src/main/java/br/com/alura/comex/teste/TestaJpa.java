package br.com.alura.comex.teste;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class TestaJpa {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("comex");

        // EntityManager que representa, de fato, a conexão com o banco de dados
        EntityManager entityManager = emf.createEntityManager();
        entityManager.close();

        emf.close();
    }
}
