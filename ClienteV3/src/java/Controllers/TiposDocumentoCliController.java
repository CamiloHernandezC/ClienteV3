package Controllers;

import Entities.TiposDocumentoCli;
import Controllers.util.JsfUtil;
import Controllers.util.JsfUtil.PersistAction;
import Facade.TiposDocumentoCliFacade;

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

@Named("tiposDocumentoCliController")
@SessionScoped
public class TiposDocumentoCliController implements Serializable {

    @EJB
    private Facade.TiposDocumentoCliFacade ejbFacade;
    private List<TiposDocumentoCli> items = null;

    public TiposDocumentoCliController() {
    }

    public List<TiposDocumentoCli> getItems() {
        if (items == null) {
            items = (List<TiposDocumentoCli>) ejbFacade.findAll().result;
        }
        return items;
    }

    // <editor-fold desc="CONVERTER" defaultstate="collapsed">
    @FacesConverter(forClass = TiposDocumentoCli.class)
    public static class TiposDocumentoCliControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            TiposDocumentoCliController controller = (TiposDocumentoCliController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "tiposDocumentoCliController");
            return controller.ejbFacade.find(getKey(value));
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
            if (object instanceof TiposDocumentoCli) {
                TiposDocumentoCli o = (TiposDocumentoCli) object;
                return getStringKey(o.getTipodocumento());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), TiposDocumentoCli.class.getName()});
                return null;
            }
        }

    }
    //</editor-fold>

}
