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
import model.Users;
import model.Users_;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import model.Ventas;
import model.Facturacionarticulos;
import model.Tiposdeusuario;

/**
 *
 * @author asanchez
 */
@Stateless
public class UsersFacade extends AbstractFacade<Users> {

    @PersistenceContext(unitName = "GestionCafeteriaPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsersFacade() {
        super(Users.class);
    }

    public boolean isVentasCollectionEmpty(Users entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Users> users = cq.from(Users.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(users, entity), cb.isNotEmpty(users.get(Users_.ventasCollection)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public Collection<Ventas> findVentasCollection(Users entity) {
        Users mergedEntity = this.getMergedEntity(entity);
        Collection<Ventas> ventasCollection = mergedEntity.getVentasCollection();
        ventasCollection.size();
        return ventasCollection;
    }

    public boolean isFacturacionarticulosCollectionEmpty(Users entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Users> users = cq.from(Users.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(users, entity), cb.isNotEmpty(users.get(Users_.facturacionarticulosCollection)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public Collection<Facturacionarticulos> findFacturacionarticulosCollection(Users entity) {
        Users mergedEntity = this.getMergedEntity(entity);
        Collection<Facturacionarticulos> facturacionarticulosCollection = mergedEntity.getFacturacionarticulosCollection();
        facturacionarticulosCollection.size();
        return facturacionarticulosCollection;
    }

    public boolean isIdTipoDeUsuarioEmpty(Users entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Users> users = cq.from(Users.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(users, entity), cb.isNotNull(users.get(Users_.idTipoDeUsuario)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public Tiposdeusuario findIdTipoDeUsuario(Users entity) {
        return this.getMergedEntity(entity).getIdTipoDeUsuario();
    }
    
}
