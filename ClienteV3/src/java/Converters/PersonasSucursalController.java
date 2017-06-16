package Converters;

import Converters.util.JsfUtil;
import Entities.Estados;
import Entities.PersonasSucursal;
import Facade.PersonasSucursalFacade;
import GeneralControl.GeneralControl;
import Querys.Querys;
import Utils.Constants;
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

//MANAGE BEAN PROPERTIES ARE COMMENTED BECAUSE PERSONAS SUCURSAL CONTROLLER IN CONTROLLERS PAKAGE EXTENDS THIS CLASS
//@Named("personasSucursalController")
//@SessionScoped
public class PersonasSucursalController extends AbstractPersistenceController<PersonasSucursal>{

    @EJB
    protected Facade.PersonasSucursalFacade ejbFacade;
    protected List<PersonasSucursal> items = null;
    protected PersonasSucursal selected;
    private PersonasController personasController;//This should be private because if extends this class could need othe personasController
    
    public PersonasSucursalController(){
    }
    
    //<editor-fold desc="INHERITED METHODS" defaultstate="collapsed">
    @Override
    public PersonasSucursal getSelected() {
        if(selected==null){
            selected = new PersonasSucursal();
        }
        return selected;
    }

    @Override
    public void setSelected(PersonasSucursal selected) {
        this.selected = selected;
    }

    @Override
    protected void setEmbeddableKeys() {
        selected.getPersonasSucursalPK().setSucursal(selected.getSucursales().getIdSucursal());
        selected.getPersonasSucursalPK().setIdPersona(selected.getPersonas().getIdPersona());
    }

    @Override
    protected void initializeEmbeddableKey() {
        selected.setPersonasSucursalPK(new Entities.PersonasSucursalPK());
    }

    @Override
    protected PersonasSucursalFacade getFacade() {
        return ejbFacade;
    }

    @Override
    public void prepareCreate() {
        initializeEmbeddableKey();
        assignPrimaryKey();
        selected.setEstado(new Estados(Constants.STATUS_ACTIVE));
        prepareUpdate();
    }
    
    @Override
    protected void prepareUpdate() {
        selected.setUsuario(JsfUtil.getSessionUser().getPersona());
        selected.setFecha(new Date());
    }
    
    @Override
    protected void setItems(List<PersonasSucursal> items) {
        this.items = items;
    }
    
    @Override
    public void clean() {
        items = null;
        selected = null;
    }
    
    //</editor-fold>
    public Result findSpecificPerson() {
        assignPrimaryKey();
        String squery = Querys.PERSONAS_SUCURSAL_CLI_ALL + "WHERE" + Querys.PERSONAS_SUCURSAL_CLI_PERSONA+ selected.getPersonas().getIdPersona()+
                "' AND"+Querys.PERSONAS_SUCURSAL_CLI_SUCURSAL+selected.getSucursales().getIdSucursal()+"'";//Here we doesn't filter by status, because this method is used when search to create
        return ejbFacade.findByQuery(squery, false);//False because only one person should appear*/
    }
    
    public void assignPrimaryKey() {
        
        if(selected==null){
            selected = new PersonasSucursal();
        }
        personasController = JsfUtil.findBean("personasController");
        selected.setPersonas(personasController.getSelected());
        GeneralControl generalControl = JsfUtil.findBean("generalControl");
        selected.setSucursales(generalControl.getSelectedBranchOffice());
    }
    
    public PersonasSucursal getPersonasSucursal(Entities.PersonasSucursalPK id) {
        return ejbFacade.find(id);
    }

    @FacesConverter(forClass = PersonasSucursal.class)
    public static class PersonasSucursalControllerConverter implements Converter {

        private static final String SEPARATOR = "#";
        private static final String SEPARATOR_ESCAPED = "\\#";

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            PersonasSucursalController controller = (PersonasSucursalController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "personasSucursalController");
            return controller.getPersonasSucursal(getKey(value));
        }

        Entities.PersonasSucursalPK getKey(String value) {
            Entities.PersonasSucursalPK key;
            String values[] = value.split(SEPARATOR_ESCAPED);
            key = new Entities.PersonasSucursalPK();
            key.setIdPersona(Integer.parseInt(values[0]));
            key.setSucursal(Integer.parseInt(values[1]));
            return key;
        }

        String getStringKey(Entities.PersonasSucursalPK value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value.getIdPersona());
            sb.append(SEPARATOR);
            sb.append(value.getSucursal());
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof PersonasSucursal) {
                PersonasSucursal o = (PersonasSucursal) object;
                return getStringKey(o.getPersonasSucursalPK());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), PersonasSucursal.class.getName()});
                return null;
            }
        }

    }

}
