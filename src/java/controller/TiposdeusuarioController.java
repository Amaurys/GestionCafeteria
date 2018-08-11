package controller;

import model.Tiposdeusuario;
import model.Users;
import java.util.Collection;
import facade.TiposdeusuarioFacade;
import controller.util.MobilePageController;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

@Named(value = "tiposdeusuarioController")
@ViewScoped
public class TiposdeusuarioController extends AbstractController<Tiposdeusuario> {

    @Inject
    private MobilePageController mobilePageController;

    // Flags to indicate if child collections are empty
    private boolean isUsersCollectionEmpty;

    public TiposdeusuarioController() {
        // Inform the Abstract parent controller of the concrete Tiposdeusuario Entity
        super(Tiposdeusuario.class);
    }

    /**
     * Set the "is[ChildCollection]Empty" property for OneToMany fields.
     */
    @Override
    protected void setChildrenEmptyFlags() {
        this.setIsUsersCollectionEmpty();
    }

    public boolean getIsUsersCollectionEmpty() {
        return this.isUsersCollectionEmpty;
    }

    private void setIsUsersCollectionEmpty() {
        Tiposdeusuario selected = this.getSelected();
        if (selected != null) {
            TiposdeusuarioFacade ejbFacade = (TiposdeusuarioFacade) this.getFacade();
            this.isUsersCollectionEmpty = ejbFacade.isUsersCollectionEmpty(selected);
        } else {
            this.isUsersCollectionEmpty = true;
        }
    }

    /**
     * Sets the "items" attribute with a collection of Users entities that are
     * retrieved from Tiposdeusuario and returns the navigation outcome.
     *
     * @return navigation outcome for Users page
     */
    public String navigateUsersCollection() {
        Tiposdeusuario selected = this.getSelected();
        if (selected != null) {
            TiposdeusuarioFacade ejbFacade = (TiposdeusuarioFacade) this.getFacade();
            Collection<Users> selectedUsersCollection = ejbFacade.findUsersCollection(selected);
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("Users_items", selectedUsersCollection);
        }
        return this.mobilePageController.getMobilePagesPrefix() + "/app/users/index";
    }

}
