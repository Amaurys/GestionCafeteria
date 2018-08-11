package controller;

import model.Tandalaboral;
import model.Empleado;
import java.util.Collection;
import facade.TandalaboralFacade;
import controller.util.MobilePageController;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

@Named(value = "tandalaboralController")
@ViewScoped
public class TandalaboralController extends AbstractController<Tandalaboral> {

    @Inject
    private MobilePageController mobilePageController;

    // Flags to indicate if child collections are empty
    private boolean isEmpleadoCollectionEmpty;

    public TandalaboralController() {
        // Inform the Abstract parent controller of the concrete Tandalaboral Entity
        super(Tandalaboral.class);
    }

    /**
     * Set the "is[ChildCollection]Empty" property for OneToMany fields.
     */
    @Override
    protected void setChildrenEmptyFlags() {
        this.setIsEmpleadoCollectionEmpty();
    }

    public boolean getIsEmpleadoCollectionEmpty() {
        return this.isEmpleadoCollectionEmpty;
    }

    private void setIsEmpleadoCollectionEmpty() {
        Tandalaboral selected = this.getSelected();
        if (selected != null) {
            TandalaboralFacade ejbFacade = (TandalaboralFacade) this.getFacade();
            this.isEmpleadoCollectionEmpty = ejbFacade.isEmpleadoCollectionEmpty(selected);
        } else {
            this.isEmpleadoCollectionEmpty = true;
        }
    }

    /**
     * Sets the "items" attribute with a collection of Empleado entities that
     * are retrieved from Tandalaboral and returns the navigation outcome.
     *
     * @return navigation outcome for Empleado page
     */
    public String navigateEmpleadoCollection() {
        Tandalaboral selected = this.getSelected();
        if (selected != null) {
            TandalaboralFacade ejbFacade = (TandalaboralFacade) this.getFacade();
            Collection<Empleado> selectedEmpleadoCollection = ejbFacade.findEmpleadoCollection(selected);
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("Empleado_items", selectedEmpleadoCollection);
        }
        return this.mobilePageController.getMobilePagesPrefix() + "/app/empleado/index";
    }

}
