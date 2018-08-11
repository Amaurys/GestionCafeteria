package controller;

import model.Marcas;
import model.Articulos;
import java.util.Collection;
import facade.MarcasFacade;
import controller.util.MobilePageController;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

@Named(value = "marcasController")
@ViewScoped
public class MarcasController extends AbstractController<Marcas> {

    @Inject
    private MobilePageController mobilePageController;

    // Flags to indicate if child collections are empty
    private boolean isArticulosCollectionEmpty;

    public MarcasController() {
        // Inform the Abstract parent controller of the concrete Marcas Entity
        super(Marcas.class);
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
        Marcas selected = this.getSelected();
        if (selected != null) {
            MarcasFacade ejbFacade = (MarcasFacade) this.getFacade();
            this.isArticulosCollectionEmpty = ejbFacade.isArticulosCollectionEmpty(selected);
        } else {
            this.isArticulosCollectionEmpty = true;
        }
    }

    /**
     * Sets the "items" attribute with a collection of Articulos entities that
     * are retrieved from Marcas and returns the navigation outcome.
     *
     * @return navigation outcome for Articulos page
     */
    public String navigateArticulosCollection() {
        Marcas selected = this.getSelected();
        if (selected != null) {
            MarcasFacade ejbFacade = (MarcasFacade) this.getFacade();
            Collection<Articulos> selectedArticulosCollection = ejbFacade.findArticulosCollection(selected);
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("Articulos_items", selectedArticulosCollection);
        }
        return this.mobilePageController.getMobilePagesPrefix() + "/app/articulos/index";
    }

}
