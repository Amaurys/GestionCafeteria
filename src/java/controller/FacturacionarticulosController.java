package controller;

import model.Facturacionarticulos;
import facade.FacturacionarticulosFacade;
import controller.util.MobilePageController;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

@Named(value = "facturacionarticulosController")
@ViewScoped
public class FacturacionarticulosController extends AbstractController<Facturacionarticulos> {

    @Inject
    private EmpleadoController idEmpleadoController;
    @Inject
    private ArticulosController idArticuloController;
    @Inject
    private UsersController idUsuarioController;
    @Inject
    private MobilePageController mobilePageController;

    public FacturacionarticulosController() {
        // Inform the Abstract parent controller of the concrete Facturacionarticulos Entity
        super(Facturacionarticulos.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        idEmpleadoController.setSelected(null);
        idArticuloController.setSelected(null);
        idUsuarioController.setSelected(null);
    }

    /**
     * Sets the "selected" attribute of the Empleado controller in order to
     * display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareIdEmpleado(ActionEvent event) {
        Facturacionarticulos selected = this.getSelected();
        if (selected != null && idEmpleadoController.getSelected() == null) {
            idEmpleadoController.setSelected(selected.getIdEmpleado());
        }
    }

    /**
     * Sets the "selected" attribute of the Articulos controller in order to
     * display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareIdArticulo(ActionEvent event) {
        Facturacionarticulos selected = this.getSelected();
        if (selected != null && idArticuloController.getSelected() == null) {
            idArticuloController.setSelected(selected.getIdArticulo());
        }
    }

    /**
     * Sets the "selected" attribute of the Users controller in order to display
     * its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareIdUsuario(ActionEvent event) {
        Facturacionarticulos selected = this.getSelected();
        if (selected != null && idUsuarioController.getSelected() == null) {
            idUsuarioController.setSelected(selected.getIdUsuario());
        }
    }

}
