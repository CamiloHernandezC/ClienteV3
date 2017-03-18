package Controllers;

import Entities.UsuariosCli;
import Facade.UsuariosCliFacade;

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

@Named("usuariosCliController")
@SessionScoped
public class UsuariosCliController extends AbstractPersistenceController<UsuariosCli>{

    @EJB
    private Facade.UsuariosCliFacade ejbFacade;
    private List<UsuariosCli> items = null;
    private UsuariosCli selected;

    public UsuariosCliController() {
    }

    @Override
    public UsuariosCli getSelected() {
        return selected;
    }

    @Override
    public void setSelected(UsuariosCli selected) {
        this.selected = selected;
    }

    @Override
    protected void setEmbeddableKeys() {
        //Nothing to do here
    }

    @Override
    protected void initializeEmbeddableKey() {
    }

    @Override
    protected UsuariosCliFacade getFacade() {
        return ejbFacade;
    }

    @Override
    public void prepareCreate() {
        selected = new UsuariosCli();
        initializeEmbeddableKey();
    }
    
    @Override
    protected void setItems(List<UsuariosCli> items) {
        this.items = items;
    }

    @Override
    protected Object calculatePrimaryKey() {
        //TODO calculate primary key
        return null;
    }

    @Override
    protected void prepareUpdate() {
        //Nothing to do here
    }

    public List<UsuariosCli> getItems() {
        if (items == null) {
            items = getFacade().findAll();
        }
        return items;
    }

    public UsuariosCli getUsuariosCli(java.lang.String id) {
        return getFacade().find(id);
    }

    public List<UsuariosCli> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<UsuariosCli> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = UsuariosCli.class)
    public static class UsuariosCliControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            UsuariosCliController controller = (UsuariosCliController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "usuariosCliController");
            return controller.getUsuariosCli(getKey(value));
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
            if (object instanceof UsuariosCli) {
                UsuariosCli o = (UsuariosCli) object;
                return getStringKey(o.getIdUsuario());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), UsuariosCli.class.getName()});
                return null;
            }
        }

    }

}
