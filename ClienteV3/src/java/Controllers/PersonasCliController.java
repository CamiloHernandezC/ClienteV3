package Controllers;

import Entities.PersonasCli;
import Controllers.util.JsfUtil;
import Facade.PersonasCliFacade;
import Querys.Querys;
import Utils.Constants;
import Utils.Navigation;
import Utils.Result;

import java.util.Date;
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
import org.primefaces.context.RequestContext;

@Named("personasCliController")
@SessionScoped
public class PersonasCliController extends AbstractPersistenceController<PersonasCli> {

    @EJB
    private Facade.PersonasCliFacade ejbFacade;
    private List<PersonasCli> items = null;
    private PersonasCli selected;
    private String code;//Store code reader value
    private String otherOriginEnterpriseName;
    private boolean showError;

    public PersonasCliController() {
    }

    //<editor-fold desc="GETTER AND SETTER" defaultstate="collapsed">
    
    public boolean isShowError() {
        return showError;
    }

    public String getOtherOriginEnterpriseName() {
        return otherOriginEnterpriseName;
    }

    public void setOtherOriginEnterpriseName(String otherOriginEnterpriseName) {
        this.otherOriginEnterpriseName = otherOriginEnterpriseName;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
    //</editor-fold>

    //<editor-fold desc="INHERITED METHODS" defaultstate="collapsed">
    @Override
    public PersonasCli getSelected() {
        if (selected == null) {
            selected = new PersonasCli();
        }
        return selected;
    }

    @Override
    public void setSelected(PersonasCli selected) {
        this.selected = selected;
    }

    @Override
    protected PersonasCliFacade getFacade() {
        return ejbFacade;
    }

    @Override
    protected void setItems(List<PersonasCli> items) {
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
    //</editor-fold>

    /**
     * 
     */
    @Override
    public void prepareCreate() {
        calculatePrimaryKey(Querys.PERSONA_CLI_LAST_PRIMARY_KEY);
        prepareUpdate();
    }
    
    @Override
    protected void prepareUpdate() {
        assignParametersToUpdate();
    }

    public List<PersonasCli> getItems() {
        if (items == null) {
            items = getFacade().findAll();
        }
        return items;
    }

    public PersonasCli getPersonasCli(java.lang.String id) {
        return getFacade().find(id);
    }

    private Result findPersonByDocument() {
        String squery = Querys.PERSONA_CLI_ALL + "WHERE" + Querys.PERSONA_CLI_DOC_TYPE + selected.getTipoDocumento().getTipodocumento() + "' AND"
                + Querys.PERSONA_CLI_DOC_NUMBER + selected.getNumDocumento() + "'";
        return ejbFacade.findByQuery(squery, false);//Only one person should have document type, an document number (It is unique in database)
    }

    public void cancel() {
        selected = new PersonasCli();
        //TODO CLEAN BRANCH AND AREA, MOV, EVERITHING
        JsfUtil.cancel();
    }

    @Override
    public void create() {
        super.create(); //TODO CREATE PERSONA SUCURSAL TOO
    }
    
    public String changeViewToCreate(){
         return Navigation.PAGE_PERSONAS_CREATE;
    }

    // <editor-fold desc="CONVERTER" defaultstate="collapsed">
    @FacesConverter(forClass = PersonasCli.class)
    public static class PersonasCliControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            PersonasCliController controller = (PersonasCliController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "personasCliController");
            return controller.getPersonasCli(getKey(value));
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
            if (object instanceof PersonasCli) {
                PersonasCli o = (PersonasCli) object;
                return getStringKey(o.getIdPersona());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), PersonasCli.class.getName()});
                return null;
            }
        }

    }
    //</editor-fold>
}
