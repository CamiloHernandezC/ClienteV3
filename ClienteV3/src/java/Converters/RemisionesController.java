package Converters;

import Entities.Remisiones;
import Facade.RemisionesFacade;

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

//MANAGE BEAN PROPERTIES ARE COMMENTED BECAUSE PERSONAS SUCURSAL CONTROLLER IN CONTROLLERS PAKAGE EXTENDS THIS CLASS
/*@Named("remisionesController")
@SessionScoped*/
public class RemisionesController implements Serializable {

    @EJB
    protected Facade.RemisionesFacade ejbFacade;
    protected List<Remisiones> items = null;
    protected Remisiones selected;

    public RemisionesController() {
    }

    public Remisiones getSelected() {
        return selected;
    }

    public void setSelected(Remisiones selected) {
        this.selected = selected;
    }

    private RemisionesFacade getFacade() {
        return ejbFacade;
    }

    public List<Remisiones> getItems() {
        if (items == null) {
            items = getFacade().findAll();
        }
        return items;
    }

    public Remisiones getRemisiones(java.lang.Integer id) {
        return getFacade().find(id);
    }

    @FacesConverter(forClass = Remisiones.class)
    public static class RemisionesControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            RemisionesController controller = (RemisionesController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "remisionesController");
            return controller.getRemisiones(getKey(value));
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
            if (object instanceof Remisiones) {
                Remisiones o = (Remisiones) object;
                return getStringKey(o.getIdRemision());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Remisiones.class.getName()});
                return null;
            }
        }

    }

}
