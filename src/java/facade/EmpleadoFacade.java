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
import model.Empleado;
import model.Empleado_;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import model.Cafeteria;
import model.Ventas;
import model.Tandalaboral;
import model.Facturacionarticulos;

/**
 *
 * @author asanchez
 */
@Stateless
public class EmpleadoFacade extends AbstractFacade<Empleado> {

    @PersistenceContext(unitName = "GestionCafeteriaPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EmpleadoFacade() {
        super(Empleado.class);
    }

    public boolean isCafeteriaCollectionEmpty(Empleado entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Empleado> empleado = cq.from(Empleado.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(empleado, entity), cb.isNotEmpty(empleado.get(Empleado_.cafeteriaCollection)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public Collection<Cafeteria> findCafeteriaCollection(Empleado entity) {
        Empleado mergedEntity = this.getMergedEntity(entity);
        Collection<Cafeteria> cafeteriaCollection = mergedEntity.getCafeteriaCollection();
        cafeteriaCollection.size();
        return cafeteriaCollection;
    }

    public boolean isVentasCollectionEmpty(Empleado entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Empleado> empleado = cq.from(Empleado.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(empleado, entity), cb.isNotEmpty(empleado.get(Empleado_.ventasCollection)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public Collection<Ventas> findVentasCollection(Empleado entity) {
        Empleado mergedEntity = this.getMergedEntity(entity);
        Collection<Ventas> ventasCollection = mergedEntity.getVentasCollection();
        ventasCollection.size();
        return ventasCollection;
    }

    public boolean isIdTandaLaboralEmpty(Empleado entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Empleado> empleado = cq.from(Empleado.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(empleado, entity), cb.isNotNull(empleado.get(Empleado_.idTandaLaboral)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public Tandalaboral findIdTandaLaboral(Empleado entity) {
        return this.getMergedEntity(entity).getIdTandaLaboral();
    }

    public boolean isFacturacionarticulosCollectionEmpty(Empleado entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Empleado> empleado = cq.from(Empleado.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(empleado, entity), cb.isNotEmpty(empleado.get(Empleado_.facturacionarticulosCollection)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public Collection<Facturacionarticulos> findFacturacionarticulosCollection(Empleado entity) {
        Empleado mergedEntity = this.getMergedEntity(entity);
        Collection<Facturacionarticulos> facturacionarticulosCollection = mergedEntity.getFacturacionarticulosCollection();
        facturacionarticulosCollection.size();
        return facturacionarticulosCollection;
    }
    
}
