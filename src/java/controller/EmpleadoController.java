package controller;

import model.Empleado;
import model.Cafeteria;
import model.Ventas;
import model.Facturacionarticulos;
import java.util.Collection;
import facade.EmpleadoFacade;
import controller.util.MobilePageController;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

@Named(value = "empleadoController")
@ViewScoped
public class EmpleadoController extends AbstractController<Empleado> {

    @Inject
    private TandalaboralController idTandaLaboralController;
    @Inject
    private MobilePageController mobilePageController;

    // Flags to indicate if child collections are empty
    private boolean isCafeteriaCollectionEmpty;
    private boolean isVentasCollectionEmpty;
    private boolean isFacturacionarticulosCollectionEmpty;

    public EmpleadoController() {
        // Inform the Abstract parent controller of the concrete Empleado Entity
        super(Empleado.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        idTandaLaboralController.setSelected(null);
    }

    /**
     * Set the "is[ChildCollection]Empty" property for OneToMany fields.
     */
    @Override
    protected void setChildrenEmptyFlags() {
        this.setIsCafeteriaCollectionEmpty();
        this.setIsVentasCollectionEmpty();
        this.setIsFacturacionarticulosCollectionEmpty();
    }

    public boolean getIsCafeteriaCollectionEmpty() {
        return this.isCafeteriaCollectionEmpty;
    }

    private void setIsCafeteriaCollectionEmpty() {
        Empleado selected = this.getSelected();
        if (selected != null) {
            EmpleadoFacade ejbFacade = (EmpleadoFacade) this.getFacade();
            this.isCafeteriaCollectionEmpty = ejbFacade.isCafeteriaCollectionEmpty(selected);
        } else {
            this.isCafeteriaCollectionEmpty = true;
        }
    }

    /**
     * Sets the "items" attribute with a collection of Cafeteria entities that
     * are retrieved from Empleado and returns the navigation outcome.
     *
     * @return navigation outcome for Cafeteria page
     */
    public String navigateCafeteriaCollection() {
        Empleado selected = this.getSelected();
        if (selected != null) {
            EmpleadoFacade ejbFacade = (EmpleadoFacade) this.getFacade();
            Collection<Cafeteria> selectedCafeteriaCollection = ejbFacade.findCafeteriaCollection(selected);
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("Cafeteria_items", selectedCafeteriaCollection);
        }
        return this.mobilePageController.getMobilePagesPrefix() + "/app/cafeteria/index";
    }

    public boolean getIsVentasCollectionEmpty() {
        return this.isVentasCollectionEmpty;
    }

    private void setIsVentasCollectionEmpty() {
        Empleado selected = this.getSelected();
        if (selected != null) {
            EmpleadoFacade ejbFacade = (EmpleadoFacade) this.getFacade();
            this.isVentasCollectionEmpty = ejbFacade.isVentasCollectionEmpty(selected);
        } else {
            this.isVentasCollectionEmpty = true;
        }
    }

    /**
     * Sets the "items" attribute with a collection of Ventas entities that are
     * retrieved from Empleado and returns the navigation outcome.
     *
     * @return navigation outcome for Ventas page
     */
    public String navigateVentasCollection() {
        Empleado selected = this.getSelected();
        if (selected != null) {
            EmpleadoFacade ejbFacade = (EmpleadoFacade) this.getFacade();
            Collection<Ventas> selectedVentasCollection = ejbFacade.findVentasCollection(selected);
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("Ventas_items", selectedVentasCollection);
        }
        return this.mobilePageController.getMobilePagesPrefix() + "/app/ventas/index";
    }

    /**
     * Sets the "selected" attribute of the Tandalaboral controller in order to
     * display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareIdTandaLaboral(ActionEvent event) {
        Empleado selected = this.getSelected();
        if (selected != null && idTandaLaboralController.getSelected() == null) {
            idTandaLaboralController.setSelected(selected.getIdTandaLaboral());
        }
    }

    public boolean getIsFacturacionarticulosCollectionEmpty() {
        return this.isFacturacionarticulosCollectionEmpty;
    }

    private void setIsFacturacionarticulosCollectionEmpty() {
        Empleado selected = this.getSelected();
        if (selected != null) {
            EmpleadoFacade ejbFacade = (EmpleadoFacade) this.getFacade();
            this.isFacturacionarticulosCollectionEmpty = ejbFacade.isFacturacionarticulosCollectionEmpty(selected);
        } else {
            this.isFacturacionarticulosCollectionEmpty = true;
        }
    }

    /**
     * Sets the "items" attribute with a collection of Facturacionarticulos
     * entities that are retrieved from Empleado and returns the navigation
     * outcome.
     *
     * @return navigation outcome for Facturacionarticulos page
     */
    public String navigateFacturacionarticulosCollection() {
        Empleado selected = this.getSelected();
        if (selected != null) {
            EmpleadoFacade ejbFacade = (EmpleadoFacade) this.getFacade();
            Collection<Facturacionarticulos> selectedFacturacionarticulosCollection = ejbFacade.findFacturacionarticulosCollection(selected);
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("Facturacionarticulos_items", selectedFacturacionarticulosCollection);
        }
        return this.mobilePageController.getMobilePagesPrefix() + "/app/facturacionarticulos/index";
    }

}
