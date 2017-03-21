package Controllers;

import Entities.Porterias;
import Controllers.util.JsfUtil;
import Controllers.util.JsfUtil.PersistAction;
import Facade.PorteriasFacade;

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

@Named("porteriasController")
@SessionScoped
public class PorteriasController implements Serializable {

    @EJB
    private Facade.PorteriasFacade ejbFacade;
    private List<Porterias> items = null;
    private Porterias selected;

    public PorteriasController() {
    }

    public Porterias getSelected() {
        return selected;
    }

    public void setSelected(Porterias selected) {
        this.selected = selected;
    }
    
    private PorteriasFacade getFacade() {
        return ejbFacade;
    }

    public List<Porterias> getItems() {
        if (items == null) {
            items = getFacade().findAll();
        }
        return items;
    }

    public Porterias getPorterias(java.lang.Long id) {
        return getFacade().find(id);
    }  

    @FacesConverter(forClass = Porterias.class)
    public static class PorteriasControllerConverter implements Converter {
        //<editor-fold desc="Converter" defaultstate="collapsed">
        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            PorteriasController controller = (PorteriasController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "porteriasController");
            return controller.getPorterias(getKey(value));
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
            if (object instanceof Porterias) {
                Porterias o = (Porterias) object;
                return getStringKey(o.getIdPorteria());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Porterias.class.getName()});
                return null;
            }
        }
        //</editor-fold>
    }

}
