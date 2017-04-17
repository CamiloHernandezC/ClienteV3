package Controllers;

import Entities.Usuarios;
import Controllers.util.JsfUtil;
import Controllers.util.JsfUtil.PersistAction;
import Facade.UsuariosFacade;

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

@Named("usuariosController")
@SessionScoped
public class UsuariosController extends AbstractPersistenceController<Usuarios>{

    @EJB
    private Facade.UsuariosFacade ejbFacade;
    private List<Usuarios> items = null;
    private Usuarios selected;

    public UsuariosController() {
    }

    @Override
    public Usuarios getSelected() {
        return selected;
    }

    @Override
    public void setSelected(Usuarios selected) {
        this.selected = selected;
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
    protected UsuariosFacade getFacade() {
        return ejbFacade;
    }

    @Override
    public void prepareCreate() {
        prepareUpdate();
    }
    
    @Override
    protected void setItems(List<Usuarios> items) {
        this.items = items;
    }

    @Override
    protected void prepareUpdate() {
        //Here we didn't use assign parameters to update method because session user could be null
        Usuarios actualUser = JsfUtil.getSessionUser();
        if(actualUser!=null){
            selected.setUsuarioModifica(actualUser.getPersona());
        }else{
            selected.setUsuarioModifica(selected.getUsuarioModifica());
        }
        selected.setFecha(new Date());
    }

    public List<Usuarios> getItems() {
        if (items == null) {
            items = getFacade().findAll();
        }
        return items;
    }

    public Usuarios getUsuarios(java.lang.String id) {
        return getFacade().find(id);
    }

    @Override
    protected void clean() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @FacesConverter(forClass = Usuarios.class)
    public static class UsuariosControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            UsuariosController controller = (UsuariosController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "usuariosController");
            return controller.getUsuarios(getKey(value));
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
            if (object instanceof Usuarios) {
                Usuarios o = (Usuarios) object;
                return getStringKey(o.getIdUsuario());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Usuarios.class.getName()});
                return null;
            }
        }

    }

}
