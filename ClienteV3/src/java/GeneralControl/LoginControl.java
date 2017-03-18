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
    //TODO VERIFY IF SESSION IS STARTED IN TEMPLATE.XHTML
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
        String Mac = getMac();
        selected.setIDSesion(Mac);
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
    
    public String getMac() {
        String macAddr = "";
        InetAddress addr;
        try {
            addr = InetAddress.getLocalHost();

            NetworkInterface dir = NetworkInterface.getByInetAddress(addr);
            byte[] dirMac = dir.getHardwareAddress();

            int count = 0;
            for (int b : dirMac) {
                if (b < 0) {
                    b = 256 + b;
                }
                if (b == 0) {
                    macAddr = macAddr.concat("00");
                }
                if (b > 0) {

                    int a = b / 16;
                    if (a == 10) {
                        macAddr = macAddr.concat("A");
                    } else if (a == 11) {
                        macAddr = macAddr.concat("B");
                    } else if (a == 12) {
                        macAddr = macAddr.concat("C");
                    } else if (a == 13) {
                        macAddr = macAddr.concat("D");
                    } else if (a == 14) {
                        macAddr = macAddr.concat("E");
                    } else if (a == 15) {
                        macAddr = macAddr.concat("F");
                    } else {
                        macAddr = macAddr.concat(String.valueOf(a));
                    }
                    a = (b % 16);
                    if (a == 10) {
                        macAddr = macAddr.concat("A");
                    } else if (a == 11) {
                        macAddr = macAddr.concat("B");
                    } else if (a == 12) {
                        macAddr = macAddr.concat("C");
                    } else if (a == 13) {
                        macAddr = macAddr.concat("D");
                    } else if (a == 14) {
                        macAddr = macAddr.concat("E");
                    } else if (a == 15) {
                        macAddr = macAddr.concat("F");
                    } else {
                        macAddr = macAddr.concat(String.valueOf(a));
                    }
                }
                if (count < dirMac.length - 1) {
                    macAddr = macAddr.concat("-");
                }
                count++;
            }

        } catch (UnknownHostException e) {
            // TODO Auto-generated catch block
            macAddr = e.getMessage();
        } catch (SocketException e) {
            // TODO Auto-generated catch block
            macAddr = e.getMessage();
        }
        return macAddr;
    }

    //TODO LOGOUT
}
