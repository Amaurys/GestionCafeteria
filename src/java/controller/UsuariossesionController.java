package controller;

import model.Usuariossesion;
import facade.UsuariossesionFacade;
import controller.util.MobilePageController;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;

@Named(value = "usuariossesionController")
@ViewScoped
public class UsuariossesionController extends AbstractController<Usuariossesion> {

    @Inject
    private MobilePageController mobilePageController;

    public UsuariossesionController() {
        // Inform the Abstract parent controller of the concrete Usuariossesion Entity
        super(Usuariossesion.class);
    }

}
