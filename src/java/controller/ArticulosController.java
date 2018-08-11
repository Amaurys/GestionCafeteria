package controller;

import model.Articulos;
import model.Ventas;
import model.Facturacionarticulos;
import java.util.Collection;
import facade.ArticulosFacade;
import controller.util.MobilePageController;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

@Named(value = "articulosController")
@ViewScoped
public class ArticulosController extends AbstractController<Articulos> {

    @Inject
    private MarcasController idMarcaController;
    @Inject
    private ProveedoresController idProveedorController;
    @Inject
    private MobilePageController mobilePageController;

    // Flags to indicate if child collections are empty
    private boolean isVentasCollectionEmpty;
    private boolean isFacturacionarticulosCollectionEmpty;

    public ArticulosController() {
        // Inform the Abstract parent controller of the concrete Articulos Entity
        super(Articulos.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        idMarcaController.setSelected(null);
        idProveedorController.setSelected(null);
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
        Articulos selected = this.getSelected();
        if (selected != null) {
            ArticulosFacade ejbFacade = (ArticulosFacade) this.getFacade();
            this.isVentasCollectionEmpty = ejbFacade.isVentasCollectionEmpty(selected);
        } else {
            this.isVentasCollectionEmpty = true;
        }
    }

    /**
     * Sets the "items" attribute with a collection of Ventas entities that are
     * retrieved from Articulos and returns the navigation outcome.
     *
     * @return navigation outcome for Ventas page
     */
    public String navigateVentasCollection() {
        Articulos selected = this.getSelected();
        if (selected != null) {
            ArticulosFacade ejbFacade = (ArticulosFacade) this.getFacade();
            Collection<Ventas> selectedVentasCollection = ejbFacade.findVentasCollection(selected);
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("Ventas_items", selectedVentasCollection);
        }
        return this.mobilePageController.getMobilePagesPrefix() + "/app/ventas/index";
    }

    public boolean getIsFacturacionarticulosCollectionEmpty() {
        return this.isFacturacionarticulosCollectionEmpty;
    }

    private void setIsFacturacionarticulosCollectionEmpty() {
        Articulos selected = this.getSelected();
        if (selected != null) {
            ArticulosFacade ejbFacade = (ArticulosFacade) this.getFacade();
            this.isFacturacionarticulosCollectionEmpty = ejbFacade.isFacturacionarticulosCollectionEmpty(selected);
        } else {
            this.isFacturacionarticulosCollectionEmpty = true;
        }
    }

    /**
     * Sets the "items" attribute with a collection of Facturacionarticulos
     * entities that are retrieved from Articulos and returns the navigation
     * outcome.
     *
     * @return navigation outcome for Facturacionarticulos page
     */
    public String navigateFacturacionarticulosCollection() {
        Articulos selected = this.getSelected();
        if (selected != null) {
            ArticulosFacade ejbFacade = (ArticulosFacade) this.getFacade();
            Collection<Facturacionarticulos> selectedFacturacionarticulosCollection = ejbFacade.findFacturacionarticulosCollection(selected);
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("Facturacionarticulos_items", selectedFacturacionarticulosCollection);
        }
        return this.mobilePageController.getMobilePagesPrefix() + "/app/facturacionarticulos/index";
    }

    /**
     * Sets the "selected" attribute of the Marcas controller in order to
     * display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareIdMarca(ActionEvent event) {
        Articulos selected = this.getSelected();
        if (selected != null && idMarcaController.getSelected() == null) {
            idMarcaController.setSelected(selected.getIdMarca());
        }
    }

    /**
     * Sets the "selected" attribute of the Proveedores controller in order to
     * display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareIdProveedor(ActionEvent event) {
        Articulos selected = this.getSelected();
        if (selected != null && idProveedorController.getSelected() == null) {
            idProveedorController.setSelected(selected.getIdProveedor());
        }
    }

}
