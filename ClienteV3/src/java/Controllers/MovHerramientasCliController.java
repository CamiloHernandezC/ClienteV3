package Controllers;

import Entities.MovHerramientasCli;
import Controllers.util.JsfUtil;
import Controllers.util.JsfUtil.PersistAction;
import Facade.MovHerramientasCliFacade;

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

@Named("movHerramientasCliController")
@SessionScoped
public class MovHerramientasCliController implements Serializable {

    @EJB
    private Facade.MovHerramientasCliFacade ejbFacade;
    private List<MovHerramientasCli> items = null;
    private MovHerramientasCli selected;

    public MovHerramientasCliController() {
    }

    public MovHerramientasCli getSelected() {
        return selected;
    }

    public void setSelected(MovHerramientasCli selected) {
        this.selected = selected;
    }

    private MovHerramientasCliFacade getFacade() {
        return ejbFacade;
    }

    public List<MovHerramientasCli> getItems() {
        if (items == null) {
            items = getFacade().findAll();
        }
        return items;
    }

    public MovHerramientasCli getMovHerramientasCli(java.lang.Long id) {
        return getFacade().find(id);
    }

    @FacesConverter(forClass = MovHerramientasCli.class)
    public static class MovHerramientasCliControllerConverter implements Converter {
        //<editor-fold desc="Converter" defaultstate="collapsed">
        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            MovHerramientasCliController controller = (MovHerramientasCliController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "movHerramientasCliController");
            return controller.getMovHerramientasCli(getKey(value));
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
            if (object instanceof MovHerramientasCli) {
                MovHerramientasCli o = (MovHerramientasCli) object;
                return getStringKey(o.getIdMovimiento());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), MovHerramientasCli.class.getName()});
                return null;
            }
        }
        //</editor-fold>
    }

}
