package Controllers;

import Entities.PersonasCli;
import Controllers.util.JsfUtil;
import Controllers.util.JsfUtil.PersistAction;
import Entities.EmpresaOrigenCli;
import Entities.PersonasSucursalCli;
import Entities.TiposDocumentoCli;
import Facade.PersonasCliFacade;
import Querys.Querys;
import Utils.BundleUtils;
import Utils.Constants;
import Utils.Navigation;
import Utils.Result;
import ViewControllers.PersonFormEntry;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.faces.event.ValueChangeEvent;
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

    public PersonasCliController() {
    }

    //<editor-fold desc="GETTER AND SETTER" defaultstate="collapsed">
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

    @Override
    protected String calculatePrimaryKey() {
        Result result = ejbFacade.findByQuery(Querys.PERSONA_CLI_PRIMARY_KEY, true);
        if (result.errorCode == Constants.NO_RESULT_EXCEPTION) {//First record will be created
            return "1";
        }
        PersonasCli lastPerson = (PersonasCli) ejbFacade.findByQuery(Querys.PERSONA_CLI_PRIMARY_KEY, true).result;
        Long lastPrimaryKey = Long.valueOf(lastPerson.getIdPersona());
        return String.valueOf(lastPrimaryKey + 1L);
    }

    @Override
    protected void persist(PersistAction persistAction, String successMessage) {
        if (selected != null) {
            selected.setUsuario("1");//TODO ASSIGN REAL USER
            selected.setFecha(new Date());
        }
        super.persist(persistAction, successMessage);
    }
    //</editor-fold>

    /**
     * 
     */
    @Override
    public void prepareCreate() {
        selected.setIdPersona(calculatePrimaryKey());
        prepareUpdate();
    }
    
    @Override
    protected void prepareUpdate() {
        selected.setUsuario(selected.getIdPersona());//TODO ASSIGN REAL USER HERE
        selected.setFecha(new Date());
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

    /**
     * Method used to search person and redirect to register form, verifying if
     * person is blocked
     *
     * @return page to redirect
     */
    public String manualEntry() {
        return redirectToRegisterForm(findPersonByDocument(), true);//Here cleans the entity to obligate rewrite id card number
    }

    /**
     *
     * @param result
     * @param cleanToCreate cleans when is not loaded from identification
     * document
     * @return
     */
    private String redirectToRegisterForm(Result result, boolean cleanToCreate) {
        ConfigFormCliController configFormCliController = JsfUtil.findBean("configFormCliController");
        configFormCliController.showFieldsPerson();
        if (result.errorCode == Constants.NO_RESULT_EXCEPTION) {
            JsfUtil.addErrorMessage(BundleUtils.getBundleProperty("Please_Register"));
            if(cleanToCreate){
               selected = new PersonasCli();
            }
            disableNoEditableFields(false);
        } else {
            selected = (PersonasCli) result.result;
            if (verifyBlockedPerson()) {
                return null;
            }
            disableNoEditableFields(true);
        }
        return Navigation.PAGE_PERSON_REGISTER;
    }

    private Result findPersonByDocument() {
        String squery = Querys.PERSONA_CLI_ALL + "WHERE" + Querys.PERSONA_CLI_DOC_TYPE + selected.getTipoDocumento().getTipodocumento() + "' AND"
                + Querys.PERSONA_CLI_DOC_NUMBER + selected.getNumDocumento() + "'";
        return ejbFacade.findByQuery(squery, false);//Only one person should have document type, an document number (It is unique in database)
    }
    
    /**
     * Method used to read barcode or id card (cedula)
     * The readed value is stored in selected.numDocum
     */
    public void completeEntryByCodeReader(){
        if(code==null){
            return;
        }
        String  pageToRedirect = null;
        Result result = findByCodeReader();
        if(result.errorCode== Constants.UNKNOWN_EXCEPTION){//unaccepted text format
            JsfUtil.addErrorMessage(BundleUtils.getBundleProperty("UNACCEPTED_FORMAT"));
            pageToRedirect = Navigation.PAGE_COMPLETE_ENTRY;
        }else{
            pageToRedirect = redirectToRegisterForm(result, false);//If person is not find with id card (cedula) or bar code, the field are not cleaned because it already has information
        }
        code = null;
        if(pageToRedirect==null){//This happend when person is blocked
            return;
        }
        JsfUtil.redirectTo(Navigation.PAGE_INDEX+pageToRedirect);
    }
    
    /**
     * Take the code readed and find a person to return
     * @return 
     */
    private Result findByCodeReader() {
        
        if(code.startsWith("C,")){//ID CARD (CEDULA)
            String[] separatedWords = separateWords();
            if (separatedWords != null) {
                //Se debe asignar el tipo de documento y número de documento para poder buscar
                selected.setTipoDocumento(new TiposDocumentoCli(Constants.DOCUMENT_TYPE_CEDULA));//Se asigna el tipo de documento como cedula
                selected.setNumDocumento(separatedWords[0]);//Se le asigna el numero de cedula que fue leido por el lector de cedulas
                //<editor-fold desc="Assign selected to info in id card" defaultstate="collapsed">
                selected.setApellido1(separatedWords[1]);
                selected.setApellido2(separatedWords[2]);
                selected.setNombre1(separatedWords[3]);
                selected.setNombre2(separatedWords[4]);
                selected.setSexo(separatedWords[5].equals("M"));
                DateFormat formatter = new SimpleDateFormat("yyyyMMdd");
                try {                
                    Date birthDate = formatter.parse(separatedWords[6]);
                    selected.setFechaNacimiento(birthDate);
                } catch (ParseException ex) {
                    System.out.println(Constants.MESSAGE_DATE_FORMAT_EXCEPTION);
                }
                String RH = "¡".equals(separatedWords[7].substring(1)) ? "+":"-";
                selected.setRh(separatedWords[7].substring(0, 1)+RH);
                //</editor-fold>
                return findPersonByDocument();                
            }
        }
        if(code.startsWith("B,")){//BAR CODE
            return findPersonByIdExterno(code.substring(2));
        }
        return new Result(null, Constants.UNKNOWN_EXCEPTION);//This should never happen
    }
    
    public Result findPersonByIdExterno(String code){
        PersonasSucursalCliController personasSucursalCliController = JsfUtil.findBean("personasSucursalCliController");
        Result result = personasSucursalCliController.findPersonByIdExterno(code);
        if(result.errorCode==Constants.OK){
            PersonasSucursalCli p = (PersonasSucursalCli) result.result;
            PersonasCli a = p.getPersonasCli();
            return new Result(a, Constants.OK);
        }
        return result;
    }
    
    private String[] separateWords() {
        int commaCounter = 0;
        String[] separatedWords = new String[10];
        int oldi = 1;
        for (int i = 2; i <code.length(); i++) {//Start in 2 to avoid "C,"
            char c = code.charAt(i);
            if (c == ',') {
                if (oldi + 1 != i) {
                    separatedWords[commaCounter] = code.substring(oldi + 1, i);    
                } else {
                    separatedWords[commaCounter] = "";
                }
                commaCounter++;
                oldi = i;
            }
        }
        if (commaCounter == 9) {
            separatedWords[0]= String.valueOf(Integer.parseInt(separatedWords[0]));//Las cedulas las completa con 0 a la izquierda, esta linea de codigo quita los 0
            return separatedWords;
        }
        return null;
    }

    /**
     * If person is blocked (registered in blocked table) will show a dialog
     *
     * @return true if the person is blocked, false otherwise
     */
    private boolean verifyBlockedPerson() {
        if(Objects.equals(selected.getEstado().getIdEstado(), Constants.STATUS_BLOCKED)){
            RequestContext context = RequestContext.getCurrentInstance();
            context.execute("PF('blockedDialog').show();");
            return true;
        }
        return false;
    }

    private void disableNoEditableFields(boolean enable) {
        PersonFormEntry personFormEntry = JsfUtil.findBean("personFormEntry");
        personFormEntry.setDisableNoEditableField(enable);
    }

    public String save() {
        MovPersonasCliController movPersonasCliController = JsfUtil.findBean("movPersonasCliController");
        PersonasSucursalCliController personasSucursalCliController = JsfUtil.findBean("personasSucursalCliController");
        PersonFormEntry personFormEntry = JsfUtil.findBean("personFormEntry");
        boolean existPerson = personFormEntry.isDisableNoEditableField();//If fields are disable that means person is in database
        if (existPerson) {
            update();
        }else{
            if(findPersonByDocument().errorCode!=Constants.NO_RESULT_EXCEPTION){//This person exist, so maybe was an error in identification number
                //We doesn't assign encoutered person. This way data is not lost
                JsfUtil.addErrorMessage("ESTA PERSONA YA ESTA REGISTRADA");//TODO CREATE BUNDLE PROPERTY
                return Navigation.PAGE_PERSON_REGISTER;
            }
            create();
        }
        Result result = personasSucursalCliController.findSpecificPerson();
        if (result.errorCode == Constants.OK) {
            PersonasSucursalCli specificPerson = (PersonasSucursalCli) result.result;
            specificPerson.setArea(personasSucursalCliController.getSelected().getArea());
            personasSucursalCliController.setSelected(specificPerson);
            //TODO VERIFY IF THIS PERSON IS BLOCKED FOR SPECIFIC BRANCH OFFICE
            personasSucursalCliController.update();
            movPersonasCliController.recordEntryMovement(Constants.UPDATE);
        }else{
            personasSucursalCliController.create();
            movPersonasCliController.recordEntryMovement(Constants.CREATE);
        }
        return Navigation.PAGE_SELECT_ENTRY;
    }

    public void valueChangeHandlerOriginEnterprise(ValueChangeEvent changeEvent) {
        PersonFormEntry personFormEntry = JsfUtil.findBean("personFormEntry");
        EmpresaOrigenCli selectedOriginEnterprise = (EmpresaOrigenCli) changeEvent.getNewValue();
        if (selectedOriginEnterprise.getIdEmorigen() != null && selectedOriginEnterprise.getIdEmorigen().equals(Constants.ORIGIN_ENTERPRISE_OTHER)) {
            personFormEntry.setDisableOtherEnterprise(false);
            return;
        }
        personFormEntry.setDisableOtherEnterprise(true);
    }

    public void cancel() {
        selected = new PersonasCli();
        //TODO CLEAN BRANCH AND AREA, MOV, EVERITHING
        JsfUtil.cancel();
    }
    
    /**
     * Method used to search person and redirect to register form, verifying if
     * person is blocked
     * @return page to redirect
     */
    public String manualExit(){
        return redirectToExitForm(findPersonByDocument());
    }
    
    /**
     * Method used to read barcode or id card (cedula)
     * The readed value is stored in selected.numDocum
     */
    public void completeExitByCodeReader(){
        if(code==null){
            return;
        }
        String  pageToRedirect = null;
        Result result = findByCodeReader();
        if(result.errorCode== Constants.UNKNOWN_EXCEPTION){//unaccepted text format
            JsfUtil.addErrorMessage(BundleUtils.getBundleProperty("UNACCEPTED_FORMAT"));
            pageToRedirect = Navigation.PAGE_COMPLETE_EXIT;
        }else{
            pageToRedirect = redirectToExitForm(result);
        }
        code = null;
        JsfUtil.redirectTo(Navigation.PAGE_INDEX+pageToRedirect);
        
    }
    
    /**
     * 
     * @param result
     * @return 
     */
     private String redirectToExitForm(Result result) {
        if(result.errorCode != 0 && result.errorCode != Constants.NO_RESULT_EXCEPTION){
            JsfUtil.addErrorMessage(BundleUtils.getBundleProperty("Tecnical_Failure"));
            return null;
        }
        
        if(result.errorCode==Constants.NO_RESULT_EXCEPTION){
            JsfUtil.addErrorMessage(BundleUtils.getBundleProperty("No_Entry_Register"));
            return Navigation.PAGE_COMPLETE_EXIT;
        }
        selected = (PersonasCli) result.result;
        result = loadEntry();
        if(result.errorCode!=Constants.OK){
            JsfUtil.addErrorMessage(BundleUtils.getBundleProperty("No_Entry_Register"));
            return Navigation.PAGE_COMPLETE_EXIT;
        }   
        if(verifyBlockedPerson()){//Onlye when person is registered, we can verify if is a blocked person
            return null;
        }
        return Navigation.PAGE_PERSON_EXIT;
    }
     
     public Result loadEntry(){
        MovPersonasCliController movPersonasCliController = JsfUtil.findBean("movPersonasCliController");
        return movPersonasCliController.loadEntry(selected.getIdPersona());
     }
     
     public String exit(){
        MovPersonasCliController movPersonasCliController = JsfUtil.findBean("movPersonasCliController");
        movPersonasCliController.recordOut();
        return Navigation.PAGE_INDEX;
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
