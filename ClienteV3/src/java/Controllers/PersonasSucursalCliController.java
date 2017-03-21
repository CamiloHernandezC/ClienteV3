package Controllers;

import Entities.PersonasSucursalCli;
import Controllers.util.JsfUtil;
import Controllers.util.JsfUtil.PersistAction;
import Entities.EntidadesCli;
import Entities.EstadosCli;
import Entities.PersonasCli;
import Entities.PersonasSucursalCliPK;
import Entities.SucursalesCli;
import Facade.PersonasSucursalCliFacade;
import GeneralControl.GeneralControl;
import Querys.Querys;
import Utils.Constants;
import Utils.Result;

import java.io.Serializable;
import java.util.Date;
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

@Named("personasSucursalCliController")
@SessionScoped
public class PersonasSucursalCliController extends AbstractPersistenceController<PersonasSucursalCli> {

    @EJB
    private Facade.PersonasSucursalCliFacade ejbFacade;
    private List<PersonasSucursalCli> items = null;
    private PersonasSucursalCli selected;

    public PersonasSucursalCliController() {
    }

    @Override
    public PersonasSucursalCli getSelected() {
        if(selected==null){
            selected = new PersonasSucursalCli();
        }
        return selected;
    }

    @Override
    public void setSelected(PersonasSucursalCli selected) {
        this.selected = selected;
    }

    @Override
    protected void setEmbeddableKeys() {
        selected.getPersonasSucursalCliPK().setSucursal(selected.getSucursalesCli().getIdSucursal());
        selected.getPersonasSucursalCliPK().setIdPersona(selected.getPersonasCli().getIdPersona());
    }

    @Override
    protected void initializeEmbeddableKey() {
        selected.setPersonasSucursalCliPK(new Entities.PersonasSucursalCliPK());
    }

    @Override
    protected PersonasSucursalCliFacade getFacade() {
        return ejbFacade;
    }

    @Override
    public void prepareCreate() {
        assignPrimaryKey();
        selected.setEntidad(new EntidadesCli(Constants.ENTITY_VISITANT));
        selected.setEstado(new EstadosCli(Constants.STATUS_ENTRY));
        prepareUpdate();
    }
    
    @Override
    protected void prepareUpdate() {
        initializeEmbeddableKey();
        selected.setUsuario(JsfUtil.getSessionUser().getIdPersona());
        selected.setFecha(new Date());
    }
    
    @Override
    protected void setItems(List<PersonasSucursalCli> items) {
        this.items = items;
    }

    public List<PersonasSucursalCli> getItems() {
        if (items == null) {
            items = getFacade().findAll();
        }
        return items;
    }
    
    public List<PersonasSucursalCli> getItemsByBranchOffice() {
        GeneralControl generalControl = JsfUtil.findBean("generalControl");
        if(generalControl.getSelectedBranchOffice()!=null){
            String squery = Querys.PERSONAS_SUCURSAL_CLI_ALL+"WHERE"+Querys.PERSONAS_SUCURSAL_CLI_SUCURSAL+generalControl.getSelectedBranchOffice().getIdSucursal()+"'";
            items = (List<PersonasSucursalCli>) getFacade().findByQueryArray(squery).result;
        }
        return items;
    }

    public PersonasSucursalCli getPersonasSucursalCli(Entities.PersonasSucursalCliPK id) {
        return getFacade().find(id);
    }

    Result findSpecificPerson() {
        assignPrimaryKey();
        String squery = Querys.PERSONAS_SUCURSAL_CLI_ALL + "WHERE" + Querys.PERSONAS_SUCURSAL_CLI_PERSONA+ selected.getPersonasCli().getIdPersona()+
                "' AND"+Querys.PERSONAS_SUCURSAL_CLI_SUCURSAL+selected.getSucursalesCli().getIdSucursal()+
                "' AND" + Querys.PERSONAS_SUCURSAL_CLI_NO_ESTADO + Constants.STATUS_INACTIVE+"'";
        return ejbFacade.findByQuery(squery, false);//False because only one person should appear*/
    }

    private void assignPrimaryKey() {
        PersonasCliController personasCliController = JsfUtil.findBean("personasCliController");
        selected.setPersonasCli(personasCliController.getSelected());
        //TODO ASSIGN BRANCH OFFICE
    }
        
    public void preEdit(PersonasSucursalCli person){
        setSelected(person);
        //TODO SHOW EDIT DIALOG
    }
    
    public void blockPerson(PersonasSucursalCli person){
        setSelected(person);
        //TODO BLOCK PERSON AND RELOAD
    }
    
    @FacesConverter(forClass = PersonasSucursalCli.class)
    public static class PersonasSucursalCliControllerConverter implements Converter {
        //<editor-fold desc="Converter" defaultstate="collapsed">
        private static final String SEPARATOR = "#";
        private static final String SEPARATOR_ESCAPED = "\\#";

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            PersonasSucursalCliController controller = (PersonasSucursalCliController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "personasSucursalCliController");
            return controller.getPersonasSucursalCli(getKey(value));
        }

        Entities.PersonasSucursalCliPK getKey(String value) {
            Entities.PersonasSucursalCliPK key;
            String values[] = value.split(SEPARATOR_ESCAPED);
            key = new Entities.PersonasSucursalCliPK();
            key.setIdPersona(values[0]);
            key.setSucursal(Long.parseLong(values[1]));
            return key;
        }

        String getStringKey(Entities.PersonasSucursalCliPK value) {
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
            if (object instanceof PersonasSucursalCli) {
                PersonasSucursalCli o = (PersonasSucursalCli) object;
                return getStringKey(o.getPersonasSucursalCliPK());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), PersonasSucursalCli.class.getName()});
                return null;
            }
        }
        //</editor-fold>
    }

}
