package Controllers;

import Entities.TiposDocumento;
import Controllers.util.JsfUtil;
import Controllers.util.JsfUtil.PersistAction;
import Facade.TiposDocumentoFacade;

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

@Named("tiposDocumentoController")
@SessionScoped
public class TiposDocumentoController implements Serializable {

    @EJB
    private Facade.TiposDocumentoFacade ejbFacade;
    private List<TiposDocumento> items = null;
    private TiposDocumento selected;

    public TiposDocumentoController() {
    }

    public TiposDocumento getSelected() {
        return selected;
    }

    public void setSelected(TiposDocumento selected) {
        this.selected = selected;
    }

    private TiposDocumentoFacade getFacade() {
        return ejbFacade;
    }

    public List<TiposDocumento> getItems() {
        if (items == null) {
            items = getFacade().findAll();
        }
        return items;
    }

    public TiposDocumento getTiposDocumento(java.lang.Integer id) {
        return getFacade().find(id);
    }

    @FacesConverter(forClass = TiposDocumento.class)
    public static class TiposDocumentoControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            TiposDocumentoController controller = (TiposDocumentoController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "tiposDocumentoController");
            return controller.getTiposDocumento(getKey(value));
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
            if (object instanceof TiposDocumento) {
                TiposDocumento o = (TiposDocumento) object;
                return getStringKey(o.getTipoDocumento());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), TiposDocumento.class.getName()});
                return null;
            }
        }

    }

}