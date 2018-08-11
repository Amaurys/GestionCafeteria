package controller;

import model.Users;
import model.Ventas;
import model.Facturacionarticulos;
import java.util.Collection;
import facade.UsersFacade;
import controller.util.MobilePageController;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

@Named(value = "usersController")
@ViewScoped
public class UsersController extends AbstractController<Users> {

    @Inject
    private TiposdeusuarioController idTipoDeUsuarioController;
    @Inject
    private MobilePageController mobilePageController;

    // Flags to indicate if child collections are empty
    private boolean isVentasCollectionEmpty;
    private boolean isFacturacionarticulosCollectionEmpty;

    public UsersController() {
        // Inform the Abstract parent controller of the concrete Users Entity
        super(Users.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        idTipoDeUsuarioController.setSelected(null);
    }

    /**
     * Set the "is[ChildCollection]Empty" property for OneToMany fields.
     */
    @Override
    protected void setChildrenEmptyFlags() {
        this.setIsVentasCollectionEmpty();
        this.setIsFacturacionarticulosCollectionEmpty();
    }

    public boolean getIsVentasCollectionEmpty() {
        return this.isVentasCollectionEmpty;
    }

    private void setIsVentasCollectionEmpty() {
        Users selected = this.getSelected();
        if (selected != null) {
            UsersFacade ejbFacade = (UsersFacade) this.getFacade();
            this.isVentasCollectionEmpty = ejbFacade.isVentasCollectionEmpty(selected);
        } else {
            this.isVentasCollectionEmpty = true;
        }
    }

    /**
     * Sets the "items" attribute with a collection of Ventas entities that are
     * retrieved from Users and returns the navigation outcome.
     *
     * @return navigation outcome for Ventas page
     */
    public String navigateVentasCollection() {
        Users selected = this.getSelected();
        if (selected != null) {
            UsersFacade ejbFacade = (UsersFacade) this.getFacade();
            Collection<Ventas> selectedVentasCollection = ejbFacade.findVentasCollection(selected);
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("Ventas_items", selectedVentasCollection);
        }
        return this.mobilePageController.getMobilePagesPrefix() + "/app/ventas/index";
    }

    public boolean getIsFacturacionarticulosCollectionEmpty() {
        return this.isFacturacionarticulosCollectionEmpty;
    }

    private void setIsFacturacionarticulosCollectionEmpty() {
        Users selected = this.getSelected();
        if (selected != null) {
            UsersFacade ejbFacade = (UsersFacade) this.getFacade();
            this.isFacturacionarticulosCollectionEmpty = ejbFacade.isFacturacionarticulosCollectionEmpty(selected);
        } else {
            this.isFacturacionarticulosCollectionEmpty = true;
        }
    }

    /**
     * Sets the "items" attribute with a collection of Facturacionarticulos
     * entities that are retrieved from Users and returns the navigation
     * outcome.
     *
     * @return navigation outcome for Facturacionarticulos page
     */
    public String navigateFacturacionarticulosCollection() {
        Users selected = this.getSelected();
        if (selected != null) {
            UsersFacade ejbFacade = (UsersFacade) this.getFacade();
            Collection<Facturacionarticulos> selectedFacturacionarticulosCollection = ejbFacade.findFacturacionarticulosCollection(selected);
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("Facturacionarticulos_items", selectedFacturacionarticulosCollection);
        }
        return this.mobilePageController.getMobilePagesPrefix() + "/app/facturacionarticulos/index";
    }

    /**
     * Sets the "selected" attribute of the Tiposdeusuario controller in order
     * to display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareIdTipoDeUsuario(ActionEvent event) {
        Users selected = this.getSelected();
        if (selected != null && idTipoDeUsuarioController.getSelected() == null) {
            idTipoDeUsuarioController.setSelected(selected.getIdTipoDeUsuario());
        }
    }

}
