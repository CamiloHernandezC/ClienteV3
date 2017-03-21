package Controllers;

import Entities.MovMaterialesCli;
import Controllers.util.JsfUtil;
import Controllers.util.JsfUtil.PersistAction;
import Facade.MovMaterialesCliFacade;

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

@Named("movMaterialesCliController")
@SessionScoped
public class MovMaterialesCliController implements Serializable {

    @EJB
    private Facade.MovMaterialesCliFacade ejbFacade;
    private List<MovMaterialesCli> items = null;
    private MovMaterialesCli selected;

    public MovMaterialesCliController() {
    }

    public MovMaterialesCli getSelected() {
        return selected;
    }

    public void setSelected(MovMaterialesCli selected) {
        this.selected = selected;
    }

    private MovMaterialesCliFacade getFacade() {
        return ejbFacade;
    }

    public List<MovMaterialesCli> getItems() {
        if (items == null) {
            items = getFacade().findAll();
        }
        return items;
    }

    public MovMaterialesCli getMovMaterialesCli(java.lang.Long id) {
        return getFacade().find(id);
    }

    @FacesConverter(forClass = MovMaterialesCli.class)
    public static class MovMaterialesCliControllerConverter implements Converter {
        //<editor-fold desc="Converter" defaultstate="collapsed">
        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            MovMaterialesCliController controller = (MovMaterialesCliController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "movMaterialesCliController");
            return controller.getMovMaterialesCli(getKey(value));
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
            if (object instanceof MovMaterialesCli) {
                MovMaterialesCli o = (MovMaterialesCli) object;
                return getStringKey(o.getIdMovimiento());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), MovMaterialesCli.class.getName()});
                return null;
            }
        }
        //</editor-fold>
    }

}
