package Converters;


import Entities.ConfigForm;
import Facade.AbstractFacade;
import Querys.Querys;
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

//MANAGE BEAN PROPERTIES ARE COMMENTED BECAUSE PERSONAS CONTROLLER IN CONTROLLERS PAKAGE EXTENDS THIS CLASS
/*@Named("configFormController")
@SessionScoped*/
public class ConfigFormController extends AbstractPersistenceController<ConfigForm> {

    @EJB
    protected Facade.ConfigFormFacade ejbFacade;
    protected List<ConfigForm> items = null;
    protected ConfigForm selected;
   
    public ConfigFormController() {
    }  
    
    @Override
    public ConfigForm getSelected() {
        return selected;
    }

    @Override
    public void setSelected(ConfigForm selected) {
        this.selected = selected;
    }

    public List<ConfigForm> getItems() {
        if (items == null) {
            items = getFacade().findAll();
        }
        return items;
    }
    
    public ConfigForm getConfigForm(java.lang.Integer id) {
        return ejbFacade.find(id);
    }

    @Override
    protected AbstractFacade getFacade() {
        return ejbFacade;
    }

    @Override
    protected void setItems(List<ConfigForm> items) {
        this.items = items;
    }

    @Override
    protected void setEmbeddableKeys() {
        //Nothing to do here
    }

    @Override
    protected void initializeEmbeddableKey() {
        //Nothing to do here
    }

    @Override
    protected void prepareUpdate() {
        //Nothing to do here
    }

    @Override
    public void clean() {
        selected = null;
        items = null;
    }

    @Override
    protected void prepareCreate() {
        calculatePrimaryKey(Querys.CONFIG_FORM_CONTROLLER_PRIMARY_KEY);
    }

    @FacesConverter(forClass = ConfigForm.class)
    public static class ConfigFormControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            ConfigFormController controller = (ConfigFormController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "configFormController");
            return controller.getConfigForm(getKey(value));
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
            if (object instanceof ConfigForm) {
                ConfigForm o = (ConfigForm) object;
                return getStringKey(o.getIdConfigForm());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), ConfigForm.class.getName()});
                return null;
            }
        }

    }

}
