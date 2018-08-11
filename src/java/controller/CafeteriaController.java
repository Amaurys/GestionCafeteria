package controller;

import model.Cafeteria;
import facade.CafeteriaFacade;
import controller.util.MobilePageController;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

@Named(value = "cafeteriaController")
@ViewScoped
public class CafeteriaController extends AbstractController<Cafeteria> {

    @Inject
    private CampusController idCampusController;
    @Inject
    private EmpleadoController idEmpleadoController;
    @Inject
    private MobilePageController mobilePageController;

    public CafeteriaController() {
        // Inform the Abstract parent controller of the concrete Cafeteria Entity
        super(Cafeteria.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        idCampusController.setSelected(null);
        idEmpleadoController.setSelected(null);
    }

    /**
     * Sets the "selected" attribute of the Campus controller in order to
     * display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareIdCampus(ActionEvent event) {
        Cafeteria selected = this.getSelected();
        if (selected != null && idCampusController.getSelected() == null) {
            idCampusController.setSelected(selected.getIdCampus());
        }
    }

    /**
     * Sets the "selected" attribute of the Empleado controller in order to
     * display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareIdEmpleado(ActionEvent event) {
        Cafeteria selected = this.getSelected();
        if (selected != null && idEmpleadoController.getSelected() == null) {
            idEmpleadoController.setSelected(selected.getIdEmpleado());
        }
    }

}
