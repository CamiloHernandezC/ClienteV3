package Converters;


import Converters.util.JsfUtil;
import Entities.Notificaciones;
import Facade.NotificacionesFacade;
import Querys.Querys;
import Utils.BundleUtils;
import Utils.Constants;
import Utils.Navigation;
import Utils.Result;
import java.io.Serializable;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import org.primefaces.event.DragDropEvent;

@Named("notificacionesController")
@SessionScoped
public class NotificacionesController extends AbstractPersistenceController<Notificaciones> {

    @EJB
    private Facade.NotificacionesFacade ejbFacade;
    private int activeStep;
    private List<String> availableMessages;
    
    public NotificacionesController() {
    }
    
    private List<Notificaciones> items = null;
    private Notificaciones selected;

    public int getActiveStep() {
        return activeStep;
    }

    @Override
    public Notificaciones getSelected() {
        if(selected==null){
            selected = new Notificaciones();
        }
        return selected;
    }

    @Override
    public void setSelected(Notificaciones selected) {
        this.selected = selected;
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
    protected NotificacionesFacade getFacade() {
        return ejbFacade;
    }

    @Override
    public void prepareCreate() {
        super.calculatePrimaryKey(Querys.NOTIFICACIONES_LAST_PRIMARY_KEY);
        prepareUpdate();
    }

    public List<Notificaciones> getItems() {
        if (items == null) {
            items = getFacade().findAll();
        }
        return items;
    }
    
    public Notificaciones getNotificaciones(java.lang.Integer id) {
        return ejbFacade.find(id);
    }

    @Override
    protected void setItems(List<Notificaciones> items) {
        this.items = items;
    }

    @Override
    protected void prepareUpdate() {
        super.assignParametersToUpdate();
    }

    @Override
    public void clean() {
        selected = null;
        items = null;
        activeStep = 0;
        init();
    }

    @FacesConverter(forClass = Notificaciones.class)
    public static class NotificacionesControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            NotificacionesController controller = (NotificacionesController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "notificacionesController");
            return controller.getNotificaciones(getKey(value));
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
            if (object instanceof Notificaciones) {
                Notificaciones o = (Notificaciones) object;
                return getStringKey(o.getIdNotificacion());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Notificaciones.class.getName()});
                return null;
            }
        }

    }
    
    public String goToCreate(){
        return Navigation.PAGE_NOTIFICATION_CREATE;
    }
    
    public void nextStep(){
        if(activeStep<1){//Different from last step
            activeStep +=1;
        }
    }
    
     public void showCancelDialog() {
        JsfUtil.showModal("dialogConfirmCancel");
    }
     
    public String cancel(){
        clean();
        return Navigation.PAGE_INDEX;
    }
    
    public boolean isEmptyDropArea(){
        return !(getSelected().getMostrarEmpresaOrigen() || getSelected().getMostrarEnte()
                || getSelected().getMostrarEntidad() || getSelected().getMostrarPorteria() || getSelected().getMostrarSucursal());
    }
    
    public List<String> getAvailableMessages(){
        return availableMessages;
    }
    
    public void onDrop(DragDropEvent ddEvent){
        String messageToAdd = ((String) ddEvent.getData());
        String newMessage="Acaba de llegar/salir";
        if(selected.getMensaje()!=null){
            newMessage = selected.getMensaje();
        }
        switch(messageToAdd){
            case "Sucursal":
                selected.setMostrarSucursal(true);
                newMessage +=" a la sucursal: sucursal de ejemplo";
                selected.setMensaje(newMessage);
                break;
            case "Porteria":
                selected.setMostrarPorteria(true);
                newMessage +=" por la porteria: porteria de ejemplo";
                selected.setMensaje(newMessage);
                break;
            /*case "Entidad":
                selected.setMostrarEntidad(true);
                newMessage +=" el visitante/ el automovil";
                selected.setMensaje(newMessage);
                break;*/
            case "Tipo de Objeto":
                selected.setMostrarEnte(true);
                newMessage +=" la persona/ el vehículo";
                selected.setMensaje(newMessage);
                break;
            case "Empresa":
                selected.setMostrarEmpresaOrigen(true);
                newMessage +=" de la empresa: empresa de ejemplo";
                selected.setMensaje(newMessage);
                break;
        }
        availableMessages.remove(messageToAdd);
    }
    
    @PostConstruct
    public void init(){
        availableMessages = new ArrayList<>();
        if(!getSelected().getMostrarSucursal()){
            availableMessages.add("Sucursal");
        }
        if(!getSelected().getMostrarPorteria()){
            availableMessages.add("Porteria");
        }
        /*if(!getSelected().getMostrarEntidad()){
            availableMessages.add("Entidad");
        }*/
        if(!getSelected().getMostrarEnte()){
            availableMessages.add("Tipo de Objeto");
        }
        if(!getSelected().getMostrarEmpresaOrigen()){
            availableMessages.add("Empresa");
        }
    }
    
    public String save(){
        Result result = create();
        if(result.errorCode == Constants.OK){
            JsfUtil.addSuccessMessage(BundleUtils.getBundleProperty("SuccessfullyCreatedRegistry"));
            selected = null;
            return Navigation.PAGE_MASTER_DATA_NOTIFICATION;
        }
        JsfUtil.addErrorMessage(validationErrorObservation);
        return null;
    }
    
}
