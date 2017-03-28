package Controllers;

import Entities.VisitasEsperadasCli;
import Controllers.util.JsfUtil;
import Controllers.util.JsfUtil.PersistAction;
import Facade.VisitasEsperadasCliFacade;
import Utils.Result;

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

@Named("visitasEsperadasCliController")
@SessionScoped
public class VisitasEsperadasCliController extends AbstractPersistenceController<VisitasEsperadasCli> {

    @EJB
    private Facade.VisitasEsperadasCliFacade ejbFacade;
    private List<VisitasEsperadasCli> items = null;
    private VisitasEsperadasCli selected;

    public VisitasEsperadasCliController() {
    }

    @Override
    public VisitasEsperadasCli getSelected() {
        return selected;
    }

    @Override
    public void setSelected(VisitasEsperadasCli selected) {
        this.selected = selected;
    }

    @Override
    protected void setEmbeddableKeys() {
        selected.getVisitasEsperadasCliPK().setIdPersona(selected.getPersonasCli().getIdPersona());
        selected.getVisitasEsperadasCliPK().setIdSucursal(selected.getSucursalesCli().getIdSucursal());
    }

    @Override
    protected void initializeEmbeddableKey() {
        selected.setVisitasEsperadasCliPK(new Entities.VisitasEsperadasCliPK());
    }

    @Override
    protected VisitasEsperadasCliFacade getFacade() {
        return ejbFacade;
    }

    @Override
    public void prepareCreate() {
        prepareUpdate();
    }

    public List<VisitasEsperadasCli> getItems() {
        if (items == null) {
            items = getFacade().findAll();
        }
        return items;
    }

    public VisitasEsperadasCli getVisitasEsperadasCli(Entities.VisitasEsperadasCliPK id) {
        return getFacade().find(id);
    }

    @Override
    protected void setItems(List<VisitasEsperadasCli> items) {
        this.items = items;
    }

    @Override
    protected void prepareUpdate() {
        selected.setFecha(new Date());
        selected.setUsuario(JsfUtil.getSessionUser().getIdPersona());
    }

    @Override
    public Result create() {
        return super.create(); //TODO create notif too and autorization if it's a special day
    }

    @Override
    protected void clean() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    @FacesConverter(forClass = VisitasEsperadasCli.class)
    public static class VisitasEsperadasCliControllerConverter implements Converter {
        //<editor-fold desc="Converter" defaultstate="collapsed">
        private static final String SEPARATOR = "#";
        private static final String SEPARATOR_ESCAPED = "\\#";

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            VisitasEsperadasCliController controller = (VisitasEsperadasCliController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "visitasEsperadasCliController");
            return controller.getVisitasEsperadasCli(getKey(value));
        }

        Entities.VisitasEsperadasCliPK getKey(String value) {
            Entities.VisitasEsperadasCliPK key;
            String values[] = value.split(SEPARATOR_ESCAPED);
            key = new Entities.VisitasEsperadasCliPK();
            key.setIdPersona(values[0]);
            key.setIdSucursal(Long.parseLong(values[1]));
            key.setFechaVisita(java.sql.Date.valueOf(values[2]));
            return key;
        }

        String getStringKey(Entities.VisitasEsperadasCliPK value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value.getIdPersona());
            sb.append(SEPARATOR);
            sb.append(value.getIdSucursal());
            sb.append(SEPARATOR);
            sb.append(value.getFechaVisita());
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof VisitasEsperadasCli) {
                VisitasEsperadasCli o = (VisitasEsperadasCli) object;
                return getStringKey(o.getVisitasEsperadasCliPK());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), VisitasEsperadasCli.class.getName()});
                return null;
            }
        }
        //</editor-fold>
    }

}
