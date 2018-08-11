package controller;

import model.Proveedores;
import model.Articulos;
import java.util.Collection;
import facade.ProveedoresFacade;
import controller.util.MobilePageController;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

@Named(value = "proveedoresController")
@ViewScoped
public class ProveedoresController extends AbstractController<Proveedores> {

    @Inject
    private MobilePageController mobilePageController;

    // Flags to indicate if child collections are empty
    private boolean isArticulosCollectionEmpty;

    public ProveedoresController() {
        // Inform the Abstract parent controller of the concrete Proveedores Entity
        super(Proveedores.class);
    }

    /**
     * Set the "is[ChildCollection]Empty" property for OneToMany fields.
     */
    @Override
    protected void setChildrenEmptyFlags() {
        this.setIsArticulosCollectionEmpty();
    }

    public boolean getIsArticulosCollectionEmpty() {
        return this.isArticulosCollectionEmpty;
    }

    private void setIsArticulosCollectionEmpty() {
        Proveedores selected = this.getSelected();
        if (selected != null) {
            ProveedoresFacade ejbFacade = (ProveedoresFacade) this.getFacade();
            this.isArticulosCollectionEmpty = ejbFacade.isArticulosCollectionEmpty(selected);
        } else {
            this.isArticulosCollectionEmpty = true;
        }
    }

    /**
     * Sets the "items" attribute with a collection of Articulos entities that
     * are retrieved from Proveedores and returns the navigation outcome.
     *
     * @return navigation outcome for Articulos page
     */
    public String navigateArticulosCollection() {
        Proveedores selected = this.getSelected();
        if (selected != null) {
            ProveedoresFacade ejbFacade = (ProveedoresFacade) this.getFacade();
            Collection<Articulos> selectedArticulosCollection = ejbFacade.findArticulosCollection(selected);
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("Articulos_items", selectedArticulosCollection);
        }
        return this.mobilePageController.getMobilePagesPrefix() + "/app/articulos/index";
    }

}
