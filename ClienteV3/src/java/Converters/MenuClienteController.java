package Converters;


import Entities.MenuCliente;
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

@Named("menuClienteController")
@SessionScoped
public class MenuClienteController implements Serializable {

    @EJB
    private Facade.MenuClienteFacade ejbFacade;
    
    public MenuClienteController() {
    }
    
    public MenuCliente getMenuCliente(java.lang.Integer id) {
        return ejbFacade.find(id);
    }

    @FacesConverter(forClass = MenuCliente.class)
    public static class MenuClienteControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            MenuClienteController controller = (MenuClienteController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "menuClienteController");
            return controller.getMenuCliente(getKey(value));
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
            if (object instanceof MenuCliente) {
                MenuCliente o = (MenuCliente) object;
                return getStringKey(o.getCodigo());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), MenuCliente.class.getName()});
                return null;
            }
        }

    }

}
