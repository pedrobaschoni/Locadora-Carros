
package br.edu.ifsp.pep.dao;

import br.edu.ifsp.pep.model.Cliente;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;


public class ClienteDAO extends AbstractDAO<Cliente>  {

    public List<Cliente> buscarTodosClientes() {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<Cliente> query = em.createNamedQuery("Cliente.findAll", Cliente.class);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

 
    public Cliente buscarClientePorCPF(String cpf) {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<Cliente> query = em.createNamedQuery("Cliente.findByCpf", Cliente.class);
            query.setParameter("cpf", cpf);
            List<Cliente> resultList = query.getResultList();
            
            return resultList.isEmpty() ? null : resultList.get(0);
        } finally {
            em.close();
        }
    }
}
