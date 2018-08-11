/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import model.Facturacionarticulos;
import model.Facturacionarticulos_;
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
public class FacturacionarticulosFacade extends AbstractFacade<Facturacionarticulos> {

    @PersistenceContext(unitName = "GestionCafeteriaPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public FacturacionarticulosFacade() {
        super(Facturacionarticulos.class);
    }

    public boolean isIdEmpleadoEmpty(Facturacionarticulos entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Facturacionarticulos> facturacionarticulos = cq.from(Facturacionarticulos.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(facturacionarticulos, entity), cb.isNotNull(facturacionarticulos.get(Facturacionarticulos_.idEmpleado)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public Empleado findIdEmpleado(Facturacionarticulos entity) {
        return this.getMergedEntity(entity).getIdEmpleado();
    }

    public boolean isIdArticuloEmpty(Facturacionarticulos entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Facturacionarticulos> facturacionarticulos = cq.from(Facturacionarticulos.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(facturacionarticulos, entity), cb.isNotNull(facturacionarticulos.get(Facturacionarticulos_.idArticulo)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public Articulos findIdArticulo(Facturacionarticulos entity) {
        return this.getMergedEntity(entity).getIdArticulo();
    }

    public boolean isIdUsuarioEmpty(Facturacionarticulos entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Facturacionarticulos> facturacionarticulos = cq.from(Facturacionarticulos.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(facturacionarticulos, entity), cb.isNotNull(facturacionarticulos.get(Facturacionarticulos_.idUsuario)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public Users findIdUsuario(Facturacionarticulos entity) {
        return this.getMergedEntity(entity).getIdUsuario();
    }
    
}
