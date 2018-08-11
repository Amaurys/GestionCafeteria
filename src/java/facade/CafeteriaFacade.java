/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import model.Cafeteria;
import model.Cafeteria_;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import model.Campus;
import model.Empleado;

/**
 *
 * @author asanchez
 */
@Stateless
public class CafeteriaFacade extends AbstractFacade<Cafeteria> {

    @PersistenceContext(unitName = "GestionCafeteriaPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CafeteriaFacade() {
        super(Cafeteria.class);
    }

    public boolean isIdCampusEmpty(Cafeteria entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Cafeteria> cafeteria = cq.from(Cafeteria.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(cafeteria, entity), cb.isNotNull(cafeteria.get(Cafeteria_.idCampus)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public Campus findIdCampus(Cafeteria entity) {
        return this.getMergedEntity(entity).getIdCampus();
    }

    public boolean isIdEmpleadoEmpty(Cafeteria entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Cafeteria> cafeteria = cq.from(Cafeteria.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(cafeteria, entity), cb.isNotNull(cafeteria.get(Cafeteria_.idEmpleado)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public Empleado findIdEmpleado(Cafeteria entity) {
        return this.getMergedEntity(entity).getIdEmpleado();
    }
    
}
