package Controllers;

import Entities.UnidadesCli;
import Controllers.util.JsfUtil;
import Controllers.util.JsfUtil.PersistAction;
import Facade.UnidadesCliFacade;

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

@Named("unidadesCliController")
@SessionScoped
public class UnidadesCliController implements Serializable {

    @EJB
    private Facade.UnidadesCliFacade ejbFacade;
    private List<UnidadesCli> items = null;
    private UnidadesCli selected;

    public UnidadesCliController() {
    }

    public UnidadesCli getSelected() {
        return selected;
    }

    public void setSelected(UnidadesCli selected) {
        this.selected = selected;
    }

    private UnidadesCliFacade getFacade() {
        return ejbFacade;
    }

    public List<UnidadesCli> getItems() {
        if (items == null) {
            items = getFacade().findAll();
        }
        return items;
    }

    public UnidadesCli getUnidadesCli(java.lang.Integer id) {
        return getFacade().find(id);
    }

    @FacesConverter(forClass = UnidadesCli.class)
    public static class UnidadesCliControllerConverter implements Converter {
        //<editor-fold desc="Converter" defaultstate="collapsed">
        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            UnidadesCliController controller = (UnidadesCliController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "unidadesCliController");
            return controller.getUnidadesCli(getKey(value));
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
            if (object instanceof UnidadesCli) {
                UnidadesCli o = (UnidadesCli) object;
                return getStringKey(o.getIdUnidad());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), UnidadesCli.class.getName()});
                return null;
            }
        }
        //</editor-fold>
    }

}
