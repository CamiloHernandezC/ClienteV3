package Converters;

import Entities.MovRemisiones;
import Facade.MovRemisionesFacade;

import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@Named("movRemisionesController")
@SessionScoped
public class MovRemisionesController implements Serializable {

    @EJB
    private Facade.MovRemisionesFacade ejbFacade;
    private List<MovRemisiones> items = null;
    private MovRemisiones selected;

    public MovRemisionesController() {
    }

    public MovRemisiones getSelected() {
        return selected;
    }

    public void setSelected(MovRemisiones selected) {
        this.selected = selected;
    }

    private MovRemisionesFacade getFacade() {
        return ejbFacade;
    }

    public List<MovRemisiones> getItems() {
        if (items == null) {
            items = getFacade().findAll();
        }
        return items;
    }

    public MovRemisiones getMovRemisiones(java.lang.Integer id) {
        return getFacade().find(id);
    }

    @FacesConverter(forClass = MovRemisiones.class)
    public static class MovRemisionesControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            MovRemisionesController controller = (MovRemisionesController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "movRemisionesController");
            return controller.getMovRemisiones(getKey(value));
        }

        java.lang.Integer getKey(String value) {
            java.lang.Integer key;
            key = Integer.valueOf(value);
            return key;
        }

        String getStringKey(java.lang.Integer value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof MovRemisiones) {
                MovRemisiones o = (MovRemisiones) object;
                return getStringKey(o.getIdMovimiento());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), MovRemisiones.class.getName()});
                return null;
            }
        }

    }

}
