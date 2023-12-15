package br.edu.ifsp.pep.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class AbstractDAO<T> {

    private EntityManagerFactory emf;

    public AbstractDAO() {
        this.emf = Persistence
                .createEntityManagerFactory("IFAPU");
    }
    
    protected EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void inserir(T entidade) throws Exception {

        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(entidade);
        em.getTransaction().commit();
        em.close();

    }

    public void alterar(T entidade) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.merge(entidade);
        em.getTransaction().commit();
        em.close();
    }

    public void excluir(T entidade) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.remove(em.merge(entidade));
        em.getTransaction().commit();
        em.close();
    }

}
