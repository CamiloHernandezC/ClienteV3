package Converters;

import Converters.util.JsfUtil;
import Entities.Theme;
import Entities.Usuarios;
import Facade.UsuariosFacade;

import java.util.Date;
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

@Named("usuariosController")
@SessionScoped
public class UsuariosController extends AbstractPersistenceController<Usuarios>{

    @EJB
    private Facade.UsuariosFacade ejbFacade;
    private List<Usuarios> items = null;
    private Usuarios selected;
    
    public UsuariosController() {
    }
    
    //<editor-fold desc="INHERITED METHODS" defaultstate="collapsed">
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
    
    @Override
    public void clean() {
        selected = null;
        items = null;
    }
    //</editor-fold>
    
    public Usuarios getUsuarios(java.lang.String id) {
        return ejbFacade.find(id);
    }
    
    public void saveTheme(Theme tema) {
        selected.setTema(tema);
        update();
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
