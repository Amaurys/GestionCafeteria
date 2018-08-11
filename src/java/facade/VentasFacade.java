/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import model.Ventas;
import model.Ventas_;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import model.Empleado;
import model.Articulos;
import model.Users;

/**
 *
 * @author asanchez
 */
@Stateless
public class VentasFacade extends AbstractFacade<Ventas> {

    @PersistenceContext(unitName = "GestionCafeteriaPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public VentasFacade() {
        super(Ventas.class);
    }

    public boolean isIdEmpleadoEmpty(Ventas entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Ventas> ventas = cq.from(Ventas.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(ventas, entity), cb.isNotNull(ventas.get(Ventas_.idEmpleado)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public Empleado findIdEmpleado(Ventas entity) {
        return this.getMergedEntity(entity).getIdEmpleado();
    }

    public boolean isIdArticuloEmpty(Ventas entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Ventas> ventas = cq.from(Ventas.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(ventas, entity), cb.isNotNull(ventas.get(Ventas_.idArticulo)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public Articulos findIdArticulo(Ventas entity) {
        return this.getMergedEntity(entity).getIdArticulo();
    }

    public boolean isIdUsuarioEmpty(Ventas entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Ventas> ventas = cq.from(Ventas.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(ventas, entity), cb.isNotNull(ventas.get(Ventas_.idUsuario)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public Users findIdUsuario(Ventas entity) {
        return this.getMergedEntity(entity).getIdUsuario();
    }
    
}
