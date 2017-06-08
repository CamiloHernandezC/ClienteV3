package Converters;

import Entities.Cardex;
import Facade.CardexFacade;

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

@Named("cardexController")
@SessionScoped
public class CardexController implements Serializable {

    @EJB
    private Facade.CardexFacade ejbFacade;
    private List<Cardex> items = null;
    private Cardex selected;

    public CardexController() {
    }

    public Cardex getSelected() {
        return selected;
    }

    public void setSelected(Cardex selected) {
        this.selected = selected;
    }

    private CardexFacade getFacade() {
        return ejbFacade;
    }

    public List<Cardex> getItems() {
        if (items == null) {
            items = getFacade().findAll();
        }
        return items;
    }

    public Cardex getCardex(java.lang.Integer id) {
        return getFacade().find(id);
    }

    @FacesConverter(forClass = Cardex.class)
    public static class CardexControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            CardexController controller = (CardexController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "cardexController");
            return controller.getCardex(getKey(value));
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
            if (object instanceof Cardex) {
                Cardex o = (Cardex) object;
                return getStringKey(o.getIdMovimientoMaterial());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Cardex.class.getName()});
                return null;
            }
        }

    }

}
