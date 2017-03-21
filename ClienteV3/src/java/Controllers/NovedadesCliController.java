package Controllers;

import Entities.NovedadesCli;
import Controllers.util.JsfUtil;
import Controllers.util.JsfUtil.PersistAction;
import Facade.NovedadesCliFacade;

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

@Named("novedadesCliController")
@SessionScoped
public class NovedadesCliController implements Serializable {

    @EJB
    private Facade.NovedadesCliFacade ejbFacade;
    private List<NovedadesCli> items = null;
    private NovedadesCli selected;

    public NovedadesCliController() {
    }

    public NovedadesCli getSelected() {
        return selected;
    }

    public void setSelected(NovedadesCli selected) {
        this.selected = selected;
    }

    private NovedadesCliFacade getFacade() {
        return ejbFacade;
    }

    public List<NovedadesCli> getItems() {
        if (items == null) {
            items = getFacade().findAll();
        }
        return items;
    }

    public NovedadesCli getNovedadesCli(java.lang.String id) {
        return getFacade().find(id);
    }

    @FacesConverter(forClass = NovedadesCli.class)
    public static class NovedadesCliControllerConverter implements Converter {
        //<editor-fold desc="Converter" defaultstate="collapsed">
        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            NovedadesCliController controller = (NovedadesCliController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "novedadesCliController");
            return controller.getNovedadesCli(getKey(value));
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
            if (object instanceof NovedadesCli) {
                NovedadesCli o = (NovedadesCli) object;
                return getStringKey(o.getIdnovedadporteria());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), NovedadesCli.class.getName()});
                return null;
            }
        }
        //</editor-fold>
    }

}
