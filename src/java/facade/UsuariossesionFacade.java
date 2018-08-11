/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import model.Usuariossesion;

/**
 *
 * @author asanchez
 */
@Stateless
public class UsuariossesionFacade extends AbstractFacade<Usuariossesion> {

    @PersistenceContext(unitName = "GestionCafeteriaPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsuariossesionFacade() {
        super(Usuariossesion.class);
    }
    
}
