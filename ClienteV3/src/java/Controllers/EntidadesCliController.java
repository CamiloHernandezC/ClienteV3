package Controllers;

import Entities.EntidadesCli;
import Controllers.util.JsfUtil;
import Controllers.util.JsfUtil.PersistAction;
import Facade.EntidadesCliFacade;
import Querys.Querys;

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

@Named("entidadesCliController")
@SessionScoped
public class EntidadesCliController implements Serializable {

    @EJB
    private Facade.EntidadesCliFacade ejbFacade;
    private List<EntidadesCli> items = null;
    private EntidadesCli selected;

    public EntidadesCliController() {
    }

    public EntidadesCli getSelected() {
        return selected;
    }

    public void setSelected(EntidadesCli selected) {
        this.selected = selected;
    }
    
    private EntidadesCliFacade getFacade() {
        return ejbFacade;
    }

    public List<EntidadesCli> getItems() {
        if (items == null) {
            items = getFacade().findAll();
        }
        return items;
    }
    
    public List<EntidadesCli> getItemsByCategory(String category) {
        if (items == null) {
            String squery = Querys.ENTIDADES_ALL+" WHERE"+Querys.ENTIDADES_CATEGORIA+category+"'";
            items = (List<EntidadesCli>) getFacade().findByQueryArray(squery).result;
        }
        return items;
    }

    public EntidadesCli getEntidadesCli(java.lang.String id) {
        return getFacade().find(id);
    }

    @FacesConverter(forClass = EntidadesCli.class)
    public static class EntidadesCliControllerConverter implements Converter {
        //<editor-fold desc="Converter" defaultstate="collapsed">
        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            EntidadesCliController controller = (EntidadesCliController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "entidadesCliController");
            return controller.getEntidadesCli(getKey(value));
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
            if (object instanceof EntidadesCli) {
                EntidadesCli o = (EntidadesCli) object;
                return getStringKey(o.getIdEntidad());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), EntidadesCli.class.getName()});
                return null;
            }
        }
        //</editor-fold>
    }

}
