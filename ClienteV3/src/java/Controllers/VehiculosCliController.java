package Controllers;

import Entities.VehiculosCli;
import Controllers.util.JsfUtil;
import Controllers.util.JsfUtil.PersistAction;
import Facade.VehiculosCliFacade;

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

@Named("vehiculosCliController")
@SessionScoped
public class VehiculosCliController extends AbstractPersistenceController<VehiculosCli> {

    @EJB
    private Facade.VehiculosCliFacade ejbFacade;
    private List<VehiculosCli> items = null;
    private VehiculosCli selected;

    public VehiculosCliController() {
    }

    @Override
    public VehiculosCli getSelected() {
        return selected;
    }

    @Override
    public void setSelected(VehiculosCli selected) {
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
    protected VehiculosCliFacade getFacade() {
        return ejbFacade;
    }

    @Override
    public void prepareCreate() {
        prepareUpdate();
    }

    public List<VehiculosCli> getItems() {
        if (items == null) {
            items = getFacade().findAll();
        }
        return items;
    }

    public VehiculosCli getVehiculosCli(java.lang.String id) {
        return getFacade().find(id);
    }

    @Override
    protected void setItems(List<VehiculosCli> items) {
        this.items = items;
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

    @FacesConverter(forClass = VehiculosCli.class)
    public static class VehiculosCliControllerConverter implements Converter {
        //<editor-fold desc="Converter" defaultstate="collapsed">
        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            VehiculosCliController controller = (VehiculosCliController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "vehiculosCliController");
            return controller.getVehiculosCli(getKey(value));
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
            if (object instanceof VehiculosCli) {
                VehiculosCli o = (VehiculosCli) object;
                return getStringKey(o.getPlaca());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), VehiculosCli.class.getName()});
                return null;
            }
        }
        //</editor-fold>
    }

}
