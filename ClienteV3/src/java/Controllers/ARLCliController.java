package Controllers;

import Entities.ARLCli;
import Controllers.util.JsfUtil;
import Controllers.util.JsfUtil.PersistAction;
import Facade.ARLCliFacade;

import java.io.Serializable;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@Named("aRLCliController")
@SessionScoped
public class ARLCliController implements Serializable {

    @EJB
    private Facade.ARLCliFacade ejbFacade;
    private List<ARLCli> items = null;
    private ARLCli selected;

    public ARLCliController() {
    }

    public ARLCli getSelected() {
        return selected;
    }

    public void setSelected(ARLCli selected) {
        this.selected = selected;
    }

    private ARLCliFacade getFacade() {
        return ejbFacade;
    }

    public ARLCli prepareCreate() {
        selected = new ARLCli();
        return selected;
    }

    public List<ARLCli> getItems() {
        if (items == null) {
            items = getFacade().findAll();
        }
        return items;
    }

    public ARLCli getARLCli(java.lang.String id) {
        return getFacade().find(id);
    }

    @FacesConverter(forClass = ARLCli.class)
    public static class ARLCliControllerConverter implements Converter {
        //<editor-fold desc="Converter" defaultstate="collapsed">
        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            ARLCliController controller = (ARLCliController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "aRLCliController");
            return controller.getARLCli(getKey(value));
        }

        java.lang.String getKey(String value) {
            java.lang.String key;
            key = value;
            return key;
        }

        String getStringKey(java.lang.String value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof ARLCli) {
                ARLCli o = (ARLCli) object;
                return getStringKey(o.getArl());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), ARLCli.class.getName()});
                return null;
            }
        }
        //</editor-fold>

    }

}
