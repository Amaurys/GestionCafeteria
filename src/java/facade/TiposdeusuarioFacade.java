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
import model.Tiposdeusuario;
import model.Tiposdeusuario_;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import model.Users;

/**
 *
 * @author asanchez
 */
@Stateless
public class TiposdeusuarioFacade extends AbstractFacade<Tiposdeusuario> {

    @PersistenceContext(unitName = "GestionCafeteriaPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TiposdeusuarioFacade() {
        super(Tiposdeusuario.class);
    }

    public boolean isUsersCollectionEmpty(Tiposdeusuario entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Tiposdeusuario> tiposdeusuario = cq.from(Tiposdeusuario.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(tiposdeusuario, entity), cb.isNotEmpty(tiposdeusuario.get(Tiposdeusuario_.usersCollection)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public Collection<Users> findUsersCollection(Tiposdeusuario entity) {
        Tiposdeusuario mergedEntity = this.getMergedEntity(entity);
        Collection<Users> usersCollection = mergedEntity.getUsersCollection();
        usersCollection.size();
        return usersCollection;
    }
    
}
