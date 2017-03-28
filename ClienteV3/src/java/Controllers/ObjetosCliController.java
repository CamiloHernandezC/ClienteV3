package Controllers;

import Entities.ObjetosCli;
import Controllers.util.JsfUtil;
import Controllers.util.JsfUtil.PersistAction;
import Facade.ObjetosCliFacade;
import Querys.Querys;

import java.io.Serializable;
import java.util.Date;
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

@Named("objetosCliController")
@SessionScoped
public class ObjetosCliController extends AbstractPersistenceController<ObjetosCli> {

    @EJB
    private Facade.ObjetosCliFacade ejbFacade;
    private List<ObjetosCli> items = null;
    private ObjetosCli selected;

    public ObjetosCliController() {
    }

    @Override
    public ObjetosCli getSelected() {
        return selected;
    }

    @Override
    public void setSelected(ObjetosCli selected) {
        this.selected = selected;
    }

    @Override
    protected ObjetosCliFacade getFacade() {
        return ejbFacade;
    }

    @Override
    public void prepareCreate() {
        calculatePrimaryKey(Querys.OBJETOS_LAST_PRIMARY_KEY);
        prepareUpdate();
    }

    public List<ObjetosCli> getItems() {
        if (items == null) {
            items = getFacade().findAll();
        }
        return items;
    }

    public ObjetosCli getObjetosCli(java.lang.String id) {
        return getFacade().find(id);
    }

    @Override
    protected void setItems(List<ObjetosCli> items) {
        this.items = items;
    }

    @Override
    protected void setEmbeddableKeys() {
        //Nothing to do here
    }

    @Override
    protected void initializeEmbeddableKey() {
        //nothing to do here
    }

    @Override
    protected void prepareUpdate() {
        selected.setFecha(new Date());
        selected.setUsuario(JsfUtil.getSessionUser().getIdPersona());
    }

    @Override
    protected void clean() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @FacesConverter(forClass = ObjetosCli.class)
    public static class ObjetosCliControllerConverter implements Converter {
        //<editor-fold desc="Converter" defaultstate="collapsed">
        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            ObjetosCliController controller = (ObjetosCliController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "objetosCliController");
            return controller.getObjetosCli(getKey(value));
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
            if (object instanceof ObjetosCli) {
                ObjetosCli o = (ObjetosCli) object;
                return getStringKey(o.getIdObjeto());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), ObjetosCli.class.getName()});
                return null;
            }
        }
        //</editor-fold>
    }

}
