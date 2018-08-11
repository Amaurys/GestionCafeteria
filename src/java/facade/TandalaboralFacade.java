/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import java.util.Collection;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import model.Tandalaboral;
import model.Tandalaboral_;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import model.Empleado;

/**
 *
 * @author asanchez
 */
@Stateless
public class TandalaboralFacade extends AbstractFacade<Tandalaboral> {

    @PersistenceContext(unitName = "GestionCafeteriaPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TandalaboralFacade() {
        super(Tandalaboral.class);
    }

    public boolean isEmpleadoCollectionEmpty(Tandalaboral entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Tandalaboral> tandalaboral = cq.from(Tandalaboral.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(tandalaboral, entity), cb.isNotEmpty(tandalaboral.get(Tandalaboral_.empleadoCollection)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public Collection<Empleado> findEmpleadoCollection(Tandalaboral entity) {
        Tandalaboral mergedEntity = this.getMergedEntity(entity);
        Collection<Empleado> empleadoCollection = mergedEntity.getEmpleadoCollection();
        empleadoCollection.size();
        return empleadoCollection;
    }
    
}
