package Controllers;

import Entities.EPSCli;
import Controllers.util.JsfUtil;
import Controllers.util.JsfUtil.PersistAction;
import Facade.EPSCliFacade;

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

@Named("ePSCliController")
@SessionScoped
public class EPSCliController implements Serializable {

    @EJB
    private Facade.EPSCliFacade ejbFacade;
    private List<EPSCli> items = null;

    public EPSCliController() {
    }

   
    public List<EPSCli> getItems() {
        if (items == null) {
            items = (List<EPSCli>) ejbFacade.findAll().result;
        }
        return items;
    }

    // <editor-fold desc="CONVERTER" defaultstate="collapsed">
    @FacesConverter(forClass = EPSCli.class)
    public static class EPSCliControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            EPSCliController controller = (EPSCliController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "ePSCliController");
            return controller.ejbFacade.find(getKey(value));
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
            if (object instanceof EPSCli) {
                EPSCli o = (EPSCli) object;
                return getStringKey(o.getEps());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), EPSCli.class.getName()});
                return null;
            }
        }

    }
    //</editor-fold>
}
