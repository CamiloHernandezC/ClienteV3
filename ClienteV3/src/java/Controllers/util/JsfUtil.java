package Controllers.util;

import Entities.Usuarios;
import Utils.Constants;
import Utils.Navigation;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpSession;
import org.primefaces.context.RequestContext;

public class JsfUtil {

    public static SelectItem[] getSelectItems(List<?> entities, boolean selectOne) {
        int size = selectOne ? entities.size() + 1 : entities.size();
        SelectItem[] items = new SelectItem[size];
        int i = 0;
        if (selectOne) {
            items[0] = new SelectItem("", "---");
            i++;
        }
        for (Object x : entities) {
            items[i++] = new SelectItem(x, x.toString());
        }
        return items;
    }

    public static boolean isValidationFailed() {
        return FacesContext.getCurrentInstance().isValidationFailed();
    }

    public static void addErrorMessage(Exception ex, String defaultMsg) {
        String msg = ex.getLocalizedMessage();
        if (msg != null && msg.length() > 0) {
            addErrorMessage(msg);
        } else {
            addErrorMessage(defaultMsg);
        }
    }

    public static void addErrorMessages(List<String> messages) {
        for (String message : messages) {
            addErrorMessage(message);
        }
    }

    public static void addErrorMessage(String msg) {
        FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
        FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, msg);
        FacesContext.getCurrentInstance().addMessage(null, facesMsg);
    }

    public static void addSuccessMessage(String msg) {
        FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
        FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, msg);
        FacesContext.getCurrentInstance().addMessage("successInfo", facesMsg);
    }

    public static String getRequestParameter(String key) {
        return FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get(key);
    }

    public static Object getObjectFromRequestParameter(String requestParameterName, Converter converter, UIComponent component) {
        String theId = JsfUtil.getRequestParameter(requestParameterName);
        return converter.getAsObject(FacesContext.getCurrentInstance(), component, theId);
    }

    public static Usuarios getSessionUser() {
        HttpSession httpSession = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
        return (Usuarios) httpSession.getAttribute(Constants.SESSION_USER);
    }

    public static enum PersistAction {
        CREATE,
        DELETE,
        UPDATE
    }

    @SuppressWarnings("unchecked")
    public static <T> T findBean(String beanName) {
        FacesContext context = FacesContext.getCurrentInstance();
        return (T) context.getApplication().evaluateExpressionGet(context, "#{" + beanName + "}", Object.class);
    }

    public static String quitaEspacios(String texto) {
        texto = texto.replaceAll(" ", "");
        texto = texto.trim();
        texto = texto.replaceAll("\u00A0", "");
        return texto;
    }

    public static void goToIndex() {
        redirectTo(Navigation.PAGE_INDEX);
    }

    public static void redirectTo(String page) {

        try {
            FacesContext contex = FacesContext.getCurrentInstance();
            contex.getExternalContext().redirect(Navigation.PAGE_REDIRECT_TO + page);
        } catch (Exception e) {
            System.out.println("Exception cancel " + e);
        }
    }

    public static void showModal(String nombreModal) {
        RequestContext context = RequestContext.getCurrentInstance();
        context.execute("PF('" + nombreModal + "').show();");
    }
    
    public static void hideModal(String nombreModal) {
        RequestContext context = RequestContext.getCurrentInstance();
        context.execute("PF('" + nombreModal + "').hide();");
    }
    
    public static String formatNumber(String texto) {
        texto= texto.replaceAll("\\.", "");//Replace all dots (.) for space
        return texto;
    }
}
