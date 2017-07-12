package Converters;


import Entities.Notificaciones;
import Facade.NotificacionesFacade;
import Querys.Querys;
import Utils.Navigation;
import java.io.Serializable;
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

@Named("notificacionesController")
@SessionScoped
public class NotificacionesController extends AbstractPersistenceController<Notificaciones> {

    @EJB
    private Facade.NotificacionesFacade ejbFacade;
    
    public NotificacionesController() {
    }
    
    private List<Notificaciones> items = null;
    private Notificaciones selected;

    

    @Override
    public Notificaciones getSelected() {
        if(selected==null){
            selected = new Notificaciones();
        }
        return selected;
    }

    @Override
    public void setSelected(Notificaciones selected) {
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
    protected NotificacionesFacade getFacade() {
        return ejbFacade;
    }

    @Override
    public void prepareCreate() {
        super.calculatePrimaryKey(Querys.NOTIFICACIONES_LAST_PRIMARY_KEY);
        prepareUpdate();
    }

    public List<Notificaciones> getItems() {
        if (items == null) {
            items = getFacade().findAll();
        }
        return items;
    }
    
    public Notificaciones getNotificaciones(java.lang.Integer id) {
        return ejbFacade.find(id);
    }

    @Override
    protected void setItems(List<Notificaciones> items) {
        this.items = items;
    }

    @Override
    protected void prepareUpdate() {
        super.assignParametersToUpdate();
    }

    @Override
    public void clean() {
        selected = null;
        items = null;
    }

    @FacesConverter(forClass = Notificaciones.class)
    public static class NotificacionesControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            NotificacionesController controller = (NotificacionesController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "notificacionesController");
            return controller.getNotificaciones(getKey(value));
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
            if (object instanceof Notificaciones) {
                Notificaciones o = (Notificaciones) object;
                return getStringKey(o.getIdNotificacion());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Notificaciones.class.getName()});
                return null;
            }
        }

    }
    
    public String goToCreate(){
        return Navigation.PAGE_NOTIFICATION_CREATE;
    }

}
