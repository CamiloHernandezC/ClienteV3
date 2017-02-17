package Controllers;

import Entities.MovPersonasCli;
import Controllers.util.JsfUtil;
import Controllers.util.JsfUtil.PersistAction;
import Entities.PersonasCli;
import Entities.PersonasSucursalCli;
import Facade.MovPersonasCliFacade;
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

@Named("movPersonasCliController")
@SessionScoped
public class MovPersonasCliController extends AbstractPersistenceController<MovPersonasCli> {

    @EJB
    private Facade.MovPersonasCliFacade ejbFacade;
    private List<MovPersonasCli> items = null;
    private MovPersonasCli selected;

    public MovPersonasCliController() {
    }

    @Override
    public MovPersonasCli getSelected() {
        return selected;
    }

    @Override
    public void setSelected(MovPersonasCli selected) {
        this.selected = selected;
    }

    @Override
    protected void setEmbeddableKeys() {
    }

    @Override
    protected void initializeEmbeddableKey() {
    }

    @Override
    protected MovPersonasCliFacade getFacade() {
        return ejbFacade;
    }

    @Override
    public void prepareCreate() {
        selected.setIdMovimiento(calculatePrimaryKey());
    }

    public List<MovPersonasCli> getItems() {
        if (items == null) {
            items = getFacade().findAll();
        }
        return items;
    }

    public MovPersonasCli getMovPersonasCli(java.lang.Long id) {
        return getFacade().find(id);
    }

    public List<MovPersonasCli> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<MovPersonasCli> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }
    
    public void recordEntryMovement(int persistAction) {
        PersonasSucursalCliController personasSucursalCli = JsfUtil.findBean("personasSucursalCliController");
        PersonasSucursalCli specificPerson = personasSucursalCli.getSelected();
        if(persistAction == Constants.UPDATE){
            if(verifyEntry(specificPerson)){
                recordForcedOut();
            }
        }
        recordEntry(specificPerson);
    }
    public void recordEntry(PersonasSucursalCli specificPerson){
        prepareEntityToCreate(specificPerson);
        create();
    }
    
    public void recordOut(){
        Date actualDate = new Date();
        selected.setFechaSalida(actualDate);
        selected.setHoraSalida(actualDate);
        selected.setFechaSalida(actualDate);
        update();
    }
    
    private void prepareEntityToCreate(PersonasSucursalCli specificPerson) {
        Date actualDate = new Date();
        selected = new MovPersonasCli();
        selected.setIdPersona(specificPerson.getPersonasCli());
        selected.setIdSucursal(specificPerson.getSucursalesCli());
        selected.setIdArea(specificPerson.getArea());
        selected.setFechaEntrada(actualDate);
        selected.setHoraEntrada(actualDate);
        //TODO PERSONA QUE AUTORIZA
        selected.setUsuario(specificPerson.getPersonasCli());//TODO ASSIGN REAL USER
        selected.setFecha(new Date());
        selected.setSalidaForzosa(false);
    }

    public boolean verifyEntry(PersonasSucursalCli specificPerson){
        String squery = Querys.MOV_PERSONA_CLI_ALL+"WHERE"+Querys.MOV_PERSONA_CLI_PERSONA+specificPerson.getPersonasCli().getIdPersona()+"' AND"+ 
                Querys.MOV_PERSONA_CLI_SUCURSAL+specificPerson.getSucursalesCli().getIdSucursal()+"' AND"+Querys.MOV_PERSONA_CLI_FECHA_SALIDA_NULL;
        Result result = ejbFacade.findByQuery(squery,false);
        if(result.errorCode == Constants.NO_RESULT_EXCEPTION){
            return false;
        }
        selected = (MovPersonasCli) result.result;
        return true;
    }
    
    public void recordForcedOut(){
        selected.setFechaSalida(selected.getFechaEntrada());
        selected.setHoraSalida(selected.getHoraEntrada());
        selected.setSalidaForzosa(true);
        update();
        selected =  null;
    }

    @Override
    protected void setItems(List<MovPersonasCli> items) {
        this.items = items;
    }

    @Override
    protected Long calculatePrimaryKey() {
        Result result = ejbFacade.findByQuery(Querys.MOV_PERSONA_CLI_PRIMARY_KEY, true);
        if (result.errorCode == Constants.NO_RESULT_EXCEPTION) {//First record will be created
            return 1L;
        }
        MovPersonasCli lastPerson = (MovPersonasCli) result.result;
        return lastPerson.getIdMovimiento()+1L;
    }

    @Override
    protected void prepareUpdate() {
        selected.setUsuario(new PersonasCli("1"));//TODO ASSIGN REAL USER HERE
        selected.setFecha(new Date());
    }

    public Result loadEntry(String idPersona) {
        String squery = Querys.MOV_PERSONA_CLI_ALL+"WHERE"+Querys.MOV_PERSONA_CLI_PERSONA+idPersona+
                "' AND"+Querys.MOV_PERSONA_CLI_FECHA_SALIDA_NULL+Querys.MOV_PERSONA_CLI_ORDER_BY_ID;
        Result result = ejbFacade.findByQuery(squery,true);
        if(result.errorCode == Constants.OK){
            selected = (MovPersonasCli) result.result;
        }
        return result;
    }

    @FacesConverter(forClass = MovPersonasCli.class)
    public static class MovPersonasCliControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            MovPersonasCliController controller = (MovPersonasCliController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "movPersonasCliController");
            return controller.getMovPersonasCli(getKey(value));
        }

        java.lang.Long getKey(String value) {
            java.lang.Long key;
            key = Long.valueOf(value);
            return key;
        }

        String getStringKey(java.lang.Long value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof MovPersonasCli) {
                MovPersonasCli o = (MovPersonasCli) object;
                return getStringKey(o.getIdMovimiento());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), MovPersonasCli.class.getName()});
                return null;
            }
        }

    }

}
