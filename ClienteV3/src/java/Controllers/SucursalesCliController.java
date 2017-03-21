package Controllers;

import Entities.SucursalesCli;
import Controllers.util.JsfUtil;
import Controllers.util.JsfUtil.PersistAction;
import Facade.SucursalesCliFacade;

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

@Named("sucursalesCliController")
@SessionScoped
public class SucursalesCliController implements Serializable {

    @EJB
    private Facade.SucursalesCliFacade ejbFacade;
    private List<SucursalesCli> items = null;
    private SucursalesCli selected;

    public SucursalesCliController() {
    }

    public SucursalesCli getSelected() {
        return selected;
    }

    public void setSelected(SucursalesCli selected) {
        this.selected = selected;
    }

    private SucursalesCliFacade getFacade() {
        return ejbFacade;
    }

    public List<SucursalesCli> getItems() {
        if (items == null) {
            items = getFacade().findAll();
        }
        return items;
    }

    public SucursalesCli getSucursalesCli(java.lang.Long id) {
        return getFacade().find(id);
    }

    @FacesConverter(forClass = SucursalesCli.class)
    public static class SucursalesCliControllerConverter implements Converter {
        //<editor-fold desc="Converter" defaultstate="collapsed">
        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            SucursalesCliController controller = (SucursalesCliController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "sucursalesCliController");
            return controller.getSucursalesCli(getKey(value));
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
            if (object instanceof SucursalesCli) {
                SucursalesCli o = (SucursalesCli) object;
                return getStringKey(o.getIdSucursal());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), SucursalesCli.class.getName()});
                return null;
            }
        }
        //</editor-fold>
    }

}
