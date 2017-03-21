package Controllers;

import Entities.MovVehiculosCli;
import Controllers.util.JsfUtil;
import Controllers.util.JsfUtil.PersistAction;
import Facade.MovVehiculosCliFacade;

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

@Named("movVehiculosCliController")
@SessionScoped
public class MovVehiculosCliController implements Serializable {

    @EJB
    private Facade.MovVehiculosCliFacade ejbFacade;
    private List<MovVehiculosCli> items = null;
    private MovVehiculosCli selected;

    public MovVehiculosCliController() {
    }

    public MovVehiculosCli getSelected() {
        return selected;
    }

    public void setSelected(MovVehiculosCli selected) {
        this.selected = selected;
    }

    private MovVehiculosCliFacade getFacade() {
        return ejbFacade;
    }

    public List<MovVehiculosCli> getItems() {
        if (items == null) {
            items = getFacade().findAll();
        }
        return items;
    }

    public MovVehiculosCli getMovVehiculosCli(java.lang.Long id) {
        return getFacade().find(id);
    }

    @FacesConverter(forClass = MovVehiculosCli.class)
    public static class MovVehiculosCliControllerConverter implements Converter {
        //<editor-fold desc="Converter" defaultstate="collapsed">
        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            MovVehiculosCliController controller = (MovVehiculosCliController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "movVehiculosCliController");
            return controller.getMovVehiculosCli(getKey(value));
        }

        java.lang.Long getKey(String value) {
            java.lang.Long key;
            key = Long.valueOf(value);
            return key;
        }

        String getStringKey(java.lang.Long value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof MovVehiculosCli) {
                MovVehiculosCli o = (MovVehiculosCli) object;
                return getStringKey(o.getIdMovimiento());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), MovVehiculosCli.class.getName()});
                return null;
            }
        }
        //</editor-fold>
    }

}
