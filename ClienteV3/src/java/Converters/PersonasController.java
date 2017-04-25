package Converters;


import Entities.Estados;
import Entities.Personas;
import Facade.PersonasFacade;
import Querys.Querys;
import Utils.Constants;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Named;

//@Named("personasController")
//@SessionScoped
public class PersonasController extends AbstractPersistenceController<Personas>{

     @EJB
    protected Facade.PersonasFacade ejbFacade;
    protected List<Personas> items = null;
    protected Personas selected;
    
    public PersonasController() {
    }
    
    //<editor-fold desc="INHERITED METHODS" defaultstate="collapsed">
    @Override
    public Personas getSelected() {
        if (selected == null) {
            selected = new Personas();
        }
        return selected;
    }

    @Override
    public void setSelected(Personas selected) {
        this.selected = selected;
    }

    @Override
    protected PersonasFacade getFacade() {
        return ejbFacade;
    }

    @Override
    protected void setItems(List<Personas> items) {
        this.items = items;
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
    public void prepareCreate() {
        calculatePrimaryKey(Querys.PERSONA_CLI_LAST_PRIMARY_KEY);
        selected.setEstado(new Estados(Constants.STATUS_ACTIVE));
        prepareUpdate();
    }
    
    @Override
    protected void prepareUpdate() {
        assignParametersToUpdate();
    }
    
    @Override
    public void clean() {
        selected = null;
        items = null;
    }
    //</editor-fold>
    
    public Personas getPersonas(java.lang.Integer id) {
        return ejbFacade.find(id);
    }

    @FacesConverter(forClass = Personas.class)
    public static class PersonasControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            PersonasController controller = (PersonasController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "personasController");
            return controller.getPersonas(getKey(value));
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
            if (object instanceof Personas) {
                Personas o = (Personas) object;
                return getStringKey(o.getIdPersona());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Personas.class.getName()});
                return null;
            }
        }

    }

}