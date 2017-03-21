package Controllers;

import Entities.PaisesCli;
import Controllers.util.JsfUtil;
import Controllers.util.JsfUtil.PersistAction;
import Facade.PaisesCliFacade;

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

@Named("paisesCliController")
@SessionScoped
public class PaisesCliController implements Serializable {

    @EJB
    private Facade.PaisesCliFacade ejbFacade;
    private List<PaisesCli> items = null;
    private PaisesCli selected;

    public PaisesCliController() {
    }

    public PaisesCli getSelected() {
        return selected;
    }

    public void setSelected(PaisesCli selected) {
        this.selected = selected;
    }

    private PaisesCliFacade getFacade() {
        return ejbFacade;
    }

    public List<PaisesCli> getItems() {
        if (items == null) {
            items = getFacade().findAll();
        }
        return items;
    }

    public PaisesCli getPaisesCli(java.lang.String id) {
        return getFacade().find(id);
    }

    @FacesConverter(forClass = PaisesCli.class)
    public static class PaisesCliControllerConverter implements Converter {
        //<editor-fold desc="Converter" defaultstate="collapsed">
        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            PaisesCliController controller = (PaisesCliController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "paisesCliController");
            return controller.getPaisesCli(getKey(value));
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
            if (object instanceof PaisesCli) {
                PaisesCli o = (PaisesCli) object;
                return getStringKey(o.getIdPais());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), PaisesCli.class.getName()});
                return null;
            }
        }
        //</editor-fold>
    }

}
