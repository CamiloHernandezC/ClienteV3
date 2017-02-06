package Controllers;

import Entities.LineasCli;
import Controllers.util.JsfUtil;
import Controllers.util.JsfUtil.PersistAction;
import Facade.LineasCliFacade;

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

@Named("lineasCliController")
@SessionScoped
public class LineasCliController implements Serializable {

    @EJB
    private Facade.LineasCliFacade ejbFacade;
    private List<LineasCli> items = null;

    public LineasCliController() {
    }

    public List<LineasCli> getItems() {
        if (items == null) {
            items = (List<LineasCli>) ejbFacade.findAll().result;
        }
        return items;
    }

    // <editor-fold desc="CONVERTER" defaultstate="collapsed">
    @FacesConverter(forClass = LineasCli.class)
    public static class LineasCliControllerConverter implements Converter {

        private static final String SEPARATOR = "#";
        private static final String SEPARATOR_ESCAPED = "\\#";

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            LineasCliController controller = (LineasCliController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "lineasCliController");
            return controller.ejbFacade.find(getKey(value));
        }

        Entities.LineasCliPK getKey(String value) {
            Entities.LineasCliPK key;
            String values[] = value.split(SEPARATOR_ESCAPED);
            key = new Entities.LineasCliPK();
            key.setIdMarca(values[0]);
            key.setIdLinea(values[1]);
            return key;
        }

        String getStringKey(Entities.LineasCliPK value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value.getIdMarca());
            sb.append(SEPARATOR);
            sb.append(value.getIdLinea());
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof LineasCli) {
                LineasCli o = (LineasCli) object;
                return getStringKey(o.getLineasCliPK());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), LineasCli.class.getName()});
                return null;
            }
        }

    }
    //</editor-fold>
}
