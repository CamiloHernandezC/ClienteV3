package Controllers;

import Entities.MunicipiosCli;
import Controllers.util.JsfUtil;
import Controllers.util.JsfUtil.PersistAction;
import Facade.MunicipiosCliFacade;

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

@Named("municipiosCliController")
@SessionScoped
public class MunicipiosCliController implements Serializable {

    @EJB
    private Facade.MunicipiosCliFacade ejbFacade;
    private List<MunicipiosCli> items = null;

    public MunicipiosCliController() {
    }

    public List<MunicipiosCli> getItems() {
        if (items == null) {
            items = (List<MunicipiosCli>) ejbFacade.findAll().result;
        }
        return items;
    }

    // <editor-fold desc="CONVERTER" defaultstate="collapsed">
    @FacesConverter(forClass = MunicipiosCli.class)
    public static class MunicipiosCliControllerConverter implements Converter {

        private static final String SEPARATOR = "#";
        private static final String SEPARATOR_ESCAPED = "\\#";

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            MunicipiosCliController controller = (MunicipiosCliController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "municipiosCliController");
            return controller.ejbFacade.find(getKey(value));
        }

        Entities.MunicipiosCliPK getKey(String value) {
            Entities.MunicipiosCliPK key;
            String values[] = value.split(SEPARATOR_ESCAPED);
            key = new Entities.MunicipiosCliPK();
            key.setIdDepartamento(values[0]);
            key.setIdMunicipio(values[1]);
            return key;
        }

        String getStringKey(Entities.MunicipiosCliPK value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value.getIdDepartamento());
            sb.append(SEPARATOR);
            sb.append(value.getIdMunicipio());
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof MunicipiosCli) {
                MunicipiosCli o = (MunicipiosCli) object;
                return getStringKey(o.getMunicipiosCliPK());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), MunicipiosCli.class.getName()});
                return null;
            }
        }

    }
    //</editor-fold>

}
