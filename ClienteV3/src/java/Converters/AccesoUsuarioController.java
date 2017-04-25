package Converters;


import Entities.AccesoUsuario;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@Named("accesoUsuarioController")
@SessionScoped
public class AccesoUsuarioController implements Serializable {

    @EJB
    private Facade.AccesoUsuarioFacade ejbFacade;
    
    public AccesoUsuarioController() {
    }

    public AccesoUsuario getAccesoUsuario(java.lang.Integer id) {
        return ejbFacade.find(id);
    }

    @FacesConverter(forClass = AccesoUsuario.class)
    public static class AccesoUsuarioControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            AccesoUsuarioController controller = (AccesoUsuarioController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "accesoUsuarioController");
            return controller.getAccesoUsuario(getKey(value));
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
            if (object instanceof AccesoUsuario) {
                AccesoUsuario o = (AccesoUsuario) object;
                return getStringKey(o.getIDAccesoUsuario());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), AccesoUsuario.class.getName()});
                return null;
            }
        }

    }
}
