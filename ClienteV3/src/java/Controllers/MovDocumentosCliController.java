package Controllers;

import Entities.MovDocumentosCli;
import Controllers.util.JsfUtil;
import Controllers.util.JsfUtil.PersistAction;
import Facade.MovDocumentosCliFacade;

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

@Named("movDocumentosCliController")
@SessionScoped
public class MovDocumentosCliController implements Serializable {

    @EJB
    private Facade.MovDocumentosCliFacade ejbFacade;
    private List<MovDocumentosCli> items = null;
    private MovDocumentosCli selected;

    public MovDocumentosCliController() {
    }

    public MovDocumentosCli getSelected() {
        return selected;
    }

    public void setSelected(MovDocumentosCli selected) {
        this.selected = selected;
    }

    private MovDocumentosCliFacade getFacade() {
        return ejbFacade;
    }

    public List<MovDocumentosCli> getItems() {
        if (items == null) {
            items = getFacade().findAll();
        }
        return items;
    }

    public MovDocumentosCli getMovDocumentosCli(java.lang.Long id) {
        return getFacade().find(id);
    }

    @FacesConverter(forClass = MovDocumentosCli.class)
    public static class MovDocumentosCliControllerConverter implements Converter {
        //<editor-fold desc="Converter" defaultstate="collapsed">
        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            MovDocumentosCliController controller = (MovDocumentosCliController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "movDocumentosCliController");
            return controller.getMovDocumentosCli(getKey(value));
        }

        java.lang.Long getKey(String value) {
            java.lang.Long key;
            key = Long.valueOf(value);
            return key;
        }

        String getStringKey(java.lang.Long value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof MovDocumentosCli) {
                MovDocumentosCli o = (MovDocumentosCli) object;
                return getStringKey(o.getIdMovDocumento());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), MovDocumentosCli.class.getName()});
                return null;
            }
        }
        //</editor-fold>
    }

}
