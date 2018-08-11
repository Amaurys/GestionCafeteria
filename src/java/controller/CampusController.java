package controller;

import model.Campus;
import model.Cafeteria;
import java.util.Collection;
import facade.CampusFacade;
import controller.util.MobilePageController;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

@Named(value = "campusController")
@ViewScoped
public class CampusController extends AbstractController<Campus> {

    @Inject
    private MobilePageController mobilePageController;

    // Flags to indicate if child collections are empty
    private boolean isCafeteriaCollectionEmpty;

    public CampusController() {
        // Inform the Abstract parent controller of the concrete Campus Entity
        super(Campus.class);
    }

    /**
     * Set the "is[ChildCollection]Empty" property for OneToMany fields.
     */
    @Override
    protected void setChildrenEmptyFlags() {
        this.setIsCafeteriaCollectionEmpty();
    }

    public boolean getIsCafeteriaCollectionEmpty() {
        return this.isCafeteriaCollectionEmpty;
    }

    private void setIsCafeteriaCollectionEmpty() {
        Campus selected = this.getSelected();
        if (selected != null) {
            CampusFacade ejbFacade = (CampusFacade) this.getFacade();
            this.isCafeteriaCollectionEmpty = ejbFacade.isCafeteriaCollectionEmpty(selected);
        } else {
            this.isCafeteriaCollectionEmpty = true;
        }
    }

    /**
     * Sets the "items" attribute with a collection of Cafeteria entities that
     * are retrieved from Campus and returns the navigation outcome.
     *
     * @return navigation outcome for Cafeteria page
     */
    public String navigateCafeteriaCollection() {
        Campus selected = this.getSelected();
        if (selected != null) {
            CampusFacade ejbFacade = (CampusFacade) this.getFacade();
            Collection<Cafeteria> selectedCafeteriaCollection = ejbFacade.findCafeteriaCollection(selected);
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("Cafeteria_items", selectedCafeteriaCollection);
        }
        return this.mobilePageController.getMobilePagesPrefix() + "/app/cafeteria/index";
    }

}
