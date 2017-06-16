package GeneralControl;

import Converters.util.JsfUtil;
import Converters.UsuariosController;
import Entities.Usuarios;
import Querys.Querys;
import Themes.ThemeController;
import Utils.BundleUtils;
import Utils.Constants;
import Utils.Email;
import Utils.Navigation;
import Utils.Result;
import java.io.Serializable;
import java.sql.Date;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;

import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

@Named
@RequestScoped
public class LoginControl implements Serializable {

    @EJB
    private Facade.UsuariosFacade ejbFacade;
    private Usuarios selected;
    private UsuariosController usuariosController = JsfUtil.findBean("usuariosController");

    public LoginControl() {
    }

    public Usuarios getSelected() {
        if (selected == null) {
            selected = new Usuarios();
        }
        return selected;
    }

    public void setSelected(Usuarios selected) {
        this.selected = selected;
    }

    public void login() {

        //TODO CODIFICACION DE LA CLAVE
        //Codificacion passCodificacion;
        //passCodificacion = new Codificacion();
        //String password = passCodificacion.generarHashPassword(this.password.trim());
        java.util.Date jd = new java.util.Date();
        Date d = new Date(jd.getTime());
        String squery = Querys.USUARIOS_ALL + " WHERE" + Querys.USUARIOS_ID + selected.getIdUsuario() + "' AND" + Querys.USUARIOS_PASSWORD + selected.getPassword()+"'";
        Result result = ejbFacade.findByQuery(squery, false);
        if (result.errorCode == Constants.OK) {
            selected = (Usuarios) result.result;
            Long milisPerDay = 24*60*60*1000L;
            if(selected.getFechaDesde().getTime()<=jd.getTime()  && (selected.getFechaHasta().getTime()+milisPerDay)>=jd.getTime()){
                successfulLogin();
                return;
            }
            JsfUtil.addErrorMessage(BundleUtils.getBundleProperty("ExpiredError"));//Unreachable if successful login
        } else {//If no user was found
            JsfUtil.addErrorMessage(BundleUtils.getBundleProperty("LoginError"));
        }
        selected = null;
    }

    private void successfulLogin() {
        String IDSesion = String.valueOf(Math.random());
        selected.setIDSesion(IDSesion);
        selected.setSesion(true);
        ThemeController themeController = JsfUtil.findBean("themeController");
        themeController.setSelectedLogin(selected.getTema());
        usuariosController.setSelected(selected);
        usuariosController.update();
        HttpSession httpSession = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
        httpSession.setAttribute(Constants.SESSION_USER, selected);
        JsfUtil.redirectTo(Navigation.PAGE_INDEX);
        selected = null;
    }
    
    public void validSession(){
        HttpSession httpSession = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
        Usuarios httpUser = (Usuarios) httpSession.getAttribute(Constants.SESSION_USER);
        if(httpUser==null){
            JsfUtil.redirectTo("");//Redirect to login
            return;
        }
        String squery = Querys.USUARIOS_ALL + " WHERE" + Querys.USUARIOS_ID + httpUser.getIdUsuario() + "' AND" + Querys.USUARIOS_SESION + "true" + "' AND"+ Querys.USUARIOS_ID_SESION + httpUser.getIDSesion()+"'";
        Result result = ejbFacade.findByQuery(squery, false);
        if(result.errorCode==Constants.OK){//VALID SESSION
            return;
        }
        JsfUtil.redirectTo("");//Redirect to login
    }
    
    public String recoverPassword(){
        String squery = Querys.USUARIOS_ALL + " WHERE" + Querys.USUARIOS_ID + selected.getIdUsuario()+"'";
        Result result = ejbFacade.findByQuery(squery, false);
        if(result.errorCode==Constants.OK){//VALID USER
            Usuarios user = (Usuarios) result.result;
            Email.crearEmail(user);
            JsfUtil.addSuccessMessage("REVISE SU CORREO ELECTRÓNICO: "+user.getMail());//TODO CREATE BUNDLE PROPERTIE HERE
            return Navigation.PAGE_LOGIN;
        }
        JsfUtil.addErrorMessage("EL ID DE USUARIO NO SE ENCUENTRA");
        return "";
    }

    //TODO LOGOUT
}
