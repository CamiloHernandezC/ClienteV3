package Controllers;

import Entities.Personas;
import Controllers.util.JsfUtil;
import Entities.PersonasSucursal;
import Querys.Querys;
import Utils.BundleUtils;
import Utils.Constants;
import Utils.Navigation;
import Utils.Result;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;

@Named("personasController")
@SessionScoped
public class PersonasController extends Converters.PersonasController{

    private String otherOriginEnterpriseName;
    private boolean showError;//Variable to show error in load from file option
    private PersonasSucursalController personasSucursalController = JsfUtil.findBean("personasSucursalController");;

    public PersonasController() {
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
    
    @Override
    public Result create() {
        Result generalResult = super.create();
        if(generalResult.errorCode!=Constants.OK){
            return generalResult;
        }
        return personasSucursalController.create();
    }

    private Result findPersonByDocument() {
        String squery = Querys.PERSONA_CLI_ALL + "WHERE" + Querys.PERSONA_CLI_DOC_TYPE + selected.getTipoDocumento().getTipoDocumento() + "' AND"
                + Querys.PERSONA_CLI_DOC_NUMBER + selected.getNumeroDocumento() + "'";
        return ejbFacade.findByQuery(squery, false);//Only one person should have document type, an document number (It is unique in database)
    }

    public void cancel() {
        clean();
        personasSucursalController.clean();
        JsfUtil.redirectTo(Navigation.PAGE_MASTER_DATA_PERSON);
    }
    
    public String searchToCreate(){
        Result result = findPersonByDocument();
        if(result.errorCode==Constants.OK){//Person already exist, update persona, and create persona sucursal
            selected = (Personas) result.result;
            Result specificResult = personasSucursalController.findSpecificPerson();
            if(specificResult.errorCode==Constants.OK){
                PersonasSucursal specificPerson = (PersonasSucursal) specificResult.result;
                if(specificPerson.getEstado().getIdEstado().equals(Constants.STATUS_INACTIVE)){
                    personasSucursalController.setSelected(specificPerson);
                    JsfUtil.showModal("inactiveDialog");
                    return null;
                }
                JsfUtil.addErrorMessage(BundleUtils.getBundleProperty("RepeatedRecord"));
                return null;
            }
            JsfUtil.addSuccessMessage(BundleUtils.getBundleProperty("RepeatedRecord"));
        }
        return Navigation.PAGE_PERSONAS_CREATE;
    }
    
    public String createByForm(){
        Result result = null;
        if(selected.getIdPersona()==null){//person doesn't exist
            result = create();
        }else{//Person exist, we need to update persona and create persona sucursal
            update();
            result = personasSucursalController.create();
        }
        switch(result.errorCode){
            case Constants.OK:
                clean();
                personasSucursalController.clean();
                JsfUtil.addSuccessMessage(BundleUtils.getBundleProperty("SuccessfullyCreatedRegistry"));
                return Navigation.PAGE_MASTER_DATA_PERSON;
            case Constants.VALIDATION_ERROR:
                JsfUtil.addErrorMessage(validationErrorObservation);//VALIDATION ERROR MESSAGE
                return null;
            default://This should never happend
                JsfUtil.addErrorMessage(BundleUtils.getBundleProperty("Tecnical_Failure"));
                return null;
        }
    }
    
    public String updateFromForm(){
        update();
        personasSucursalController.update();
        clean();
        personasSucursalController.clean();
        JsfUtil.addSuccessMessage(BundleUtils.getBundleProperty("SuccessfullyUpdatedRegistry"));
        return Navigation.PAGE_MASTER_DATA_PERSON;
    }
    
    @Override
    public void clean(){
        super.clean();
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
    private Result updateProperties(Personas existingPerson) {
        selected.setIdPersona(existingPerson.getIdPersona());
        //<editor-fold desc="update properties if added" defaultstate="collapsed">
        if(selected.getEmpresaOrigen()!=null ){
            selected.setEmpresaOrigen(existingPerson.getEmpresaOrigen());
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
        if(selected.getPais()==null && existingPerson.getPais()!=null){
            selected.setPais(existingPerson.getPais());
        }
        if(selected.getDepartamento()==null && existingPerson.getDepartamento()!=null){
            selected.setDepartamento(existingPerson.getDepartamento());
        }
        if(selected.getMunicipio()==null && existingPerson.getMunicipio()!=null){
            selected.setMunicipio(existingPerson.getMunicipio());
        }
        if(selected.getEps()==null && existingPerson.getEps()!=null){
            selected.setEps(existingPerson.getEps());
        }
        if(selected.getFechaVigenciaSS()==null && existingPerson.getFechaVigenciaSS()!=null){
            selected.setFechaVigenciaSS(existingPerson.getFechaVigenciaSS());
        }
        if(selected.getArl()==null && existingPerson.getArl()!=null){
            selected.setArl(existingPerson.getArl());
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
}
