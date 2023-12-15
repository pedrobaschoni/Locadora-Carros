package br.edu.ifsp.pep.dao;

import br.edu.ifsp.pep.model.Veiculo;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

public class VeiculoDAO extends AbstractDAO<Veiculo> {

    @Override
    public void inserir(Veiculo veiculo) throws Exception {

        EntityManager em =  getEntityManager();
        
        TypedQuery<Long> query = em.createQuery("SELECT COUNT(v) FROM Veiculo v WHERE v.placa = :placa AND v.cidade = :cidade", Long.class);
        query.setParameter("placa", veiculo.getPlaca());
        query.setParameter("cidade", veiculo.getCidade());

        try {
            Long count = query.getSingleResult();
            if (count > 0) {
                throw new RuntimeException("Já existe um veículo com a mesma placa na cidade especificada.");
            } else {
                super.inserir(veiculo);
            }
        } catch (NoResultException e) {
            super.inserir(veiculo);
        }
    }
    
      public List<Veiculo> buscarVeiculosDisponiveis() {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<Veiculo> query = em.createQuery("SELECT v FROM Veiculo v WHERE v.isLocado = false", Veiculo.class);
            return query.getResultList();
        } finally {
            em.close();
        }
    }
}
