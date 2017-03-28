package Controllers;

import Entities.PersonasCli;
import Controllers.util.JsfUtil;
import Entities.EstadosCli;
import Facade.PersonasCliFacade;
import Querys.Querys;
import Utils.BundleUtils;
import Utils.Constants;
import Utils.Navigation;
import Utils.Result;

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

@Named("personasCliController")
@SessionScoped
public class PersonasCliController extends AbstractPersistenceController<PersonasCli> {

    @EJB
    private Facade.PersonasCliFacade ejbFacade;
    private List<PersonasCli> items = null;
    private PersonasCli selected;
    private String otherOriginEnterpriseName;
    private boolean showError;
    private PersonasSucursalCliController personasSucursalCliController = JsfUtil.findBean("personasSucursalCliController");

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
        selected.setEstado(new EstadosCli(Constants.STATUS_ACTIVE));
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
        clean();
        JsfUtil.goToIndex();
    }
    
    public String searchToCreate(){
        Result result = findPersonByDocument();
        if(result.errorCode==Constants.OK){//Person already exist, update persona, and create persona sucursal
            selected = (PersonasCli) result.result;
            Result specificResult = personasSucursalCliController.findSpecificPerson();
            if(specificResult.errorCode==Constants.OK){
                JsfUtil.addErrorMessage(BundleUtils.getBundleProperty("RepeatedRecord"));
                return null;
            }
            JsfUtil.addSuccessMessage(BundleUtils.getBundleProperty("RepeatedRecord"));
        }
        return Navigation.PAGE_PERSONAS_CREATE;
    }

    /**
     * Create persona and persona sucursal
     * @return 
     */
    @Override
    public Result create() {
        Result generalResult = super.create();
        if(generalResult.errorCode!=Constants.OK){
            return generalResult;
        }
        return personasSucursalCliController.create();
        
        /*
        Result result = findPersonByDocument();
        PersonasSucursalCliController personasSucursalCliController = JsfUtil.findBean("personasSucursalCliController");
        if(result.errorCode==Constants.NO_RESULT_EXCEPTION){
            Result generalResult = super.create();
            if(generalResult.errorCode!=Constants.OK){
                return generalResult;
            }
        }
        if(result.errorCode==Constants.OK){//PERSON ALREADY EXIST, this check its not necessary but prevent errors when other exceptions are added in findByQuery method
            
            updateProperties((PersonasCli) result.result);//We didn't get erroCode because it always must be OK
            //In update properties also load idpersona field yo search specific person
            Result specificResult = personasSucursalCliController.findSpecificPerson();
            if(specificResult.errorCode==Constants.OK){
                return new Result(null, Constants.REPEATED_RECORD);
            }
            
        }
        //Only exist other exeption catched by findByQuery (at date 27/03/2017), the no unique result exception
        //but it should never happend because ID (document type, and document number) are unique in database
        return personasSucursalCliController.create();
        */
    }
    
    public String createByForm(){
        Result result = null;
        if(selected.getIdPersona()==null || selected.getIdPersona().isEmpty()){//person doesn't exist
            result = create();
        }else{//Person exist, we need to update persona and create persona sucursal
            update();
            result = personasSucursalCliController.create();
        }
        switch(result.errorCode){
            case Constants.OK:
                clean();
                personasSucursalCliController.clean();
                JsfUtil.addSuccessMessage(BundleUtils.getBundleProperty("SuccessfullyCreatedRegistry"));
                return Navigation.PAGE_MASTER_DATA_PERSON;
            case Constants.VALIDATION_ERROR:
                JsfUtil.addErrorMessage(validationErrorObservation);
                return null;
            default://This should never happend
                JsfUtil.addErrorMessage(BundleUtils.getBundleProperty("Tecnical_Failure"));
                return null;
        }
    }
    
    @Override
    public void clean(){
        selected = null;
        items = null;
        otherOriginEnterpriseName = null;
        validationErrorObservation = null;
        showError = false;
    }
    
    public String changeViewToCreate(){
         return Navigation.PAGE_SEARCH_PERSON_TO_CREATE;
    }

    /**
     * Update person's properties from file
     * @param existingPerson 
     */
    private Result updateProperties(PersonasCli existingPerson) {
        selected.setIdPersona(existingPerson.getIdPersona());
        //<editor-fold desc="update properties if added" defaultstate="collapsed">
        if(selected.getIdEmpresaOrigen()!=null ){
            selected.setIdEmpresaOrigen(existingPerson.getIdEmpresaOrigen());
        }
        if(selected.getNombre2().isEmpty() && !existingPerson.getNombre2().isEmpty()){
            selected.setNombre2(existingPerson.getNombre2());
        }
        if(selected.getApellido2().isEmpty() && !existingPerson.getApellido2().isEmpty()){
            selected.setApellido2(existingPerson.getApellido2());
        }
        if(selected.getDireccion().isEmpty() && !existingPerson.getDireccion().isEmpty()){
            selected.setDireccion(existingPerson.getDireccion());
        }
        if(selected.getTelefono().isEmpty() && !existingPerson.getTelefono().isEmpty()){
            selected.setTelefono(existingPerson.getTelefono());
        }
        if(selected.getCelular().isEmpty() && !existingPerson.getCelular().isEmpty()){
            selected.setCelular(existingPerson.getCelular());
        }
        if(selected.getMail().isEmpty() && !existingPerson.getMail().isEmpty()){
            selected.setMail(existingPerson.getMail());
        }
        if(selected.getPersonaContacto().isEmpty() && !existingPerson.getPersonaContacto().isEmpty()){
            selected.setPersonaContacto(existingPerson.getPersonaContacto());
        }
        if(selected.getTelPersonaContacto().isEmpty() && !existingPerson.getTelPersonaContacto().isEmpty()){
            selected.setTelPersonaContacto(existingPerson.getPersonaContacto());
        }
        if(selected.getIdPais()==null && existingPerson.getIdPais()!=null){
            selected.setIdPais(existingPerson.getIdPais());
        }
        if(selected.getIdDepartamento()==null && existingPerson.getIdDepartamento()!=null){
            selected.setIdDepartamento(existingPerson.getIdDepartamento());
        }
        if(selected.getIdMunicipio()==null && existingPerson.getIdMunicipio()!=null){
            selected.setIdMunicipio(existingPerson.getIdMunicipio());
        }
        if(selected.getEps()==null && existingPerson.getEps()!=null){
            selected.setEps(existingPerson.getEps());
        }
        if(selected.getFechavigenciaEPS()==null && existingPerson.getFechavigenciaEPS()!=null){
            selected.setFechavigenciaEPS(existingPerson.getFechavigenciaEPS());
        }
        if(selected.getArl()==null && existingPerson.getArl()!=null){
            selected.setArl(existingPerson.getArl());
        }
        if(selected.getFechavigenciaARL()==null && existingPerson.getFechavigenciaARL()!=null){
            selected.setFechavigenciaARL(existingPerson.getFechavigenciaARL());
        }
        if(selected.getSexo()==null && existingPerson.getSexo()!=null){
            selected.setSexo(existingPerson.getSexo());
        }
        if(selected.getRh()==null && existingPerson.getRh()!=null){
            selected.setRh(existingPerson.getRh());
        }
        if(selected.getFechaNacimiento()==null && existingPerson.getFechaNacimiento()!=null){
            selected.setFechaNacimiento(existingPerson.getFechaNacimiento());
        }
        if(selected.getEstado()==null && existingPerson.getEstado()!=null){
            selected.setEstado(existingPerson.getEstado());
        }
        return update();
        //</editor-fold>
        
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
