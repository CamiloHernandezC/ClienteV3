package Controllers;

import Entities.MarcasCli;
import Controllers.util.JsfUtil;
import Controllers.util.JsfUtil.PersistAction;
import Facade.MarcasCliFacade;

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

@Named("marcasCliController")
@SessionScoped
public class MarcasCliController implements Serializable {

    @EJB
    private Facade.MarcasCliFacade ejbFacade;
    private List<MarcasCli> items = null;
    private MarcasCli selected;

    public MarcasCliController() {
    }

    public MarcasCli getSelected() {
        return selected;
    }

    public void setSelected(MarcasCli selected) {
        this.selected = selected;
    }

    private MarcasCliFacade getFacade() {
        return ejbFacade;
    }

    public List<MarcasCli> getItems() {
        if (items == null) {
            items = getFacade().findAll();
        }
        return items;
    }

    public MarcasCli getMarcasCli(java.lang.String id) {
        return getFacade().find(id);
    }

    @FacesConverter(forClass = MarcasCli.class)
    public static class MarcasCliControllerConverter implements Converter {
        //<editor-fold desc="Converter" defaultstate="collapsed">
        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            MarcasCliController controller = (MarcasCliController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "marcasCliController");
            return controller.getMarcasCli(getKey(value));
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
            if (object instanceof MarcasCli) {
                MarcasCli o = (MarcasCli) object;
                return getStringKey(o.getIdMarca());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), MarcasCli.class.getName()});
                return null;
            }
        }
        //</editor-fold>
    }

}
