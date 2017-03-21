package GeneralControl;

import Controllers.UsuariosCliController;
import Controllers.util.JsfUtil;
import Entities.UsuariosCli;
import Querys.Querys;
import Utils.BundleUtils;
import Utils.Constants;
import Utils.Navigation;
import Utils.Result;
import java.io.Serializable;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;

import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

@Named
@RequestScoped
public class LoginControl implements Serializable {

    @EJB
    private Facade.UsuariosCliFacade ejbFacade;
    private UsuariosCli selected;
    private UsuariosCliController usuariosCliController = JsfUtil.findBean("usuariosCliController");

    public LoginControl() {
    }

    public UsuariosCli getSelected() {
        if (selected == null) {
            selected = new UsuariosCli();
        }
        return selected;
    }

    public void setSelected(UsuariosCli selected) {
        this.selected = selected;
    }

    //TODO TENER BOTON DE ENVIAR EMAIL PARA RECORDAR LA CONTRASEÑA
    public void login() {

        //TODO CODIFICACION DE LA CLAVE
        //Codificacion passCodificacion;
        //passCodificacion = new Codificacion();
        //String password = passCodificacion.generarHashPassword(this.password.trim());        
        String squery = Querys.USUARIOS_ALL + " WHERE" + Querys.USUARIOS_ID + selected.getIdUsuario() + "' AND" + Querys.USUARIOS_PASSWORD + selected.getPassword() + "'";
        Result result = ejbFacade.findByQuery(squery, false);
        if (result.errorCode == Constants.OK) {
            selected = (UsuariosCli) result.result;
            successfulLogin();
        } else {//If no user was found
            selected = null;
            JsfUtil.addErrorMessage(BundleUtils.getBundleProperty("LoginError"));
        }
    }

    private void successfulLogin() {
        //IF MAC PROBLEMS SEE http://stackoverflow.com/questions/4467905/getting-mac-address-on-a-web-page-using-a-java-applet
        String IDSesion = String.valueOf(Math.random());
        selected.setIDSesion(IDSesion);
        selected.setSesion(true);
        usuariosCliController.setSelected(selected);
        usuariosCliController.update();
        HttpSession httpSession = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
        httpSession.setAttribute(Constants.SESSION_USER, selected);
        JsfUtil.redirectTo(Navigation.PAGE_INDEX);
    }
    
    public void validSession(){
        HttpSession httpSession = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
        UsuariosCli httpUser = (UsuariosCli) httpSession.getAttribute(Constants.SESSION_USER);
        String squery = Querys.USUARIOS_ALL + " WHERE" + Querys.USUARIOS_ID + httpUser.getIdUsuario() + "' AND" + Querys.USUARIOS_SESION + "true" + "' AND"+ Querys.USUARIOS_ID_SESION + httpUser.getIDSesion()+"'";
        Result result = ejbFacade.findByQuery(squery, false);
        if(result.errorCode==Constants.OK){//VALID SESSION
            return;
        }
        JsfUtil.redirectTo("");//Redirect to login
    }   

    //TODO LOGOUT
}
