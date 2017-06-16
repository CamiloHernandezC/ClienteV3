/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Converters.util.JsfUtil;
import Entities.Horarios;
import Entities.PersonasSucursal;
import GeneralControl.GeneralControl;
import Querys.Querys;
import Utils.BundleUtils;
import Utils.Constants;
import Utils.Navigation;
import Utils.Result;
import java.sql.Time;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.event.ActionEvent;
import javax.inject.Named;
import org.primefaces.event.ScheduleEntryResizeEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.DefaultScheduleEvent;
import org.primefaces.model.DefaultScheduleModel;
import org.primefaces.model.ScheduleEvent;
import org.primefaces.model.ScheduleModel;

/**
 *
 * @author Kmilo
 */
@Named("horariosController")
@SessionScoped
public class HorariosController extends Converters.HorariosController {

    private ScheduleModel eventModel;
    private ScheduleEvent event;

    public ScheduleEvent getEvent() {
        return event;
    }
    
    public ScheduleModel getEventModel() {
        if (eventModel == null) {
            eventModel = new DefaultScheduleModel();
        }
        return eventModel;
    }

    public void setEventModel(ScheduleModel eventModel) {
        this.eventModel = eventModel;
    }

    public void onDateSelect(SelectEvent selectEvent) {
        for(ScheduleEvent sEvent : eventModel.getEvents()){
            if(sEvent.getStartDate().equals(selectEvent.getObject())){
                return;
            }
        }
        Date endDate = new Date(((Date) selectEvent.getObject()).getTime() + 60 * 60 * 1000);
        event = new DefaultScheduleEvent("", (Date) selectEvent.getObject(), endDate);
        eventModel.addEvent(event);
    }

    public void onEventResize(ScheduleEntryResizeEvent event) {
        //Nothing to do here
    }

    public String changeViewToCreate() {
        return Navigation.PAGE_CREATE_SCHEDULE;
    }

    public String save() {
        
        selected = new Horarios();
        loadEvents();
        Result result = create();
        if(result.errorCode == Constants.OK){
            JsfUtil.addSuccessMessage(BundleUtils.getBundleProperty("SuccessfullyCreatedRegistry"));
            selected = null;
            return Navigation.PAGE_MASTER_DATA_SCHEDULE;
        }
        JsfUtil.addErrorMessage(validationErrorObservation);
        return null;
    }

    public void cancel() {
        clean();
        JsfUtil.redirectTo(Navigation.PAGE_MASTER_DATA_SCHEDULE);
    }

    @Override
    public void clean() {
        super.clean();
        eventModel = null;
        event = null;
    }
    
    public void deleteEvent(){
        eventModel.deleteEvent(event);
        event = null;
    }
    
    public void onEventSelect(SelectEvent selectEvent){
        event = (ScheduleEvent) selectEvent.getObject();
    }
    
    /**
     * Load schedules where branch office is equal to selected and status 
     *
     * @return List of schedules belonging to selected branch office
     *
     */
    public List<Horarios> getItemsByBranchOffice() {//TODO LOAD ITEMS ONCE BECAUSE WHEN CHANGE PAGE TO SEE MORE PERSONS IT WILL RELOAD, SO MAKE A BUTTON TO RELOAD OR TRY WITH C:IF WHEN PAGE IS REALOADED, MAKE C:IF ACTION SET BOOLEAN VALUE TO FALSE AND USE THAT BOOLEAN TO ASK IF RELOAD IS NEEDED LIKE VALID SESSION METHOD
        
        GeneralControl generalControl = JsfUtil.findBean("generalControl");
        if (generalControl.getSelectedBranchOffice() != null) {
            String squery = Querys.HORARIOS_ALL + " WHERE"+Querys.HORARIOS_SUCURSAL+generalControl.getSelectedBranchOffice().getIdSucursal()+"'";
            items = (List<Horarios>) getFacade().findByQueryArray(squery).result;
        }
        return items;
    }
    
    
    public void preEdit(Horarios schedule) {
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        Long actualDate = JsfUtil.dateOnly(new Date()).getTime()-1000*60*60*24*(c.get(Calendar.DAY_OF_WEEK)-2);//minus day of week because start in sunday, and we need to start at monday
        
        eventModel = new DefaultScheduleModel();
        selected = new Horarios();
        selected.setIdHorario(schedule.getIdHorario());
        ScheduleEvent event = new DefaultScheduleEvent();
        if(schedule.getLunes()){
            event = new DefaultScheduleEvent("", new Date(actualDate+schedule.getHoraIngresoL().getTime()), new Date(actualDate+schedule.getHoraSalidaL().getTime()));
            eventModel.addEvent(event);
        }
        if(schedule.getMartes()){
            event = new DefaultScheduleEvent("", new Date(actualDate+JsfUtil.getMilisPerDay(1L)+schedule.getHoraIngresoM().getTime()), new Date(actualDate+JsfUtil.getMilisPerDay(1L)+schedule.getHoraSalidaM().getTime()));
            eventModel.addEvent(event);
        }
        if(schedule.getMiercoles()){
            event = new DefaultScheduleEvent("", new Date(actualDate+JsfUtil.getMilisPerDay(2L)+schedule.getHoraIngresoW().getTime()), new Date(actualDate+JsfUtil.getMilisPerDay(2L)+schedule.getHoraSalidaW().getTime()));
            eventModel.addEvent(event);
        }
        if(schedule.getJueves()){
            event = new DefaultScheduleEvent("", new Date(actualDate+JsfUtil.getMilisPerDay(3L)+schedule.getHoraIngresoJ().getTime()), new Date(actualDate+JsfUtil.getMilisPerDay(3L)+schedule.getHoraSalidaJ().getTime()));
            eventModel.addEvent(event);
        }
        if(schedule.getViernes()){
            event = new DefaultScheduleEvent("", new Date(actualDate+JsfUtil.getMilisPerDay(4L)+schedule.getHoraIngresoV().getTime()), new Date(actualDate+JsfUtil.getMilisPerDay(4L)+schedule.getHoraSalidaV().getTime()));
            eventModel.addEvent(event);
        }
        if(schedule.getSabado()){
            event = new DefaultScheduleEvent("", new Date(actualDate+JsfUtil.getMilisPerDay(5L)+schedule.getHoraIngresoS().getTime()), new Date(actualDate+JsfUtil.getMilisPerDay(5L)+schedule.getHoraSalidaS().getTime()));
            eventModel.addEvent(event);
        }
        if(schedule.getDomingo()){
            event = new DefaultScheduleEvent("", new Date(actualDate+JsfUtil.getMilisPerDay(6L)+schedule.getHoraIngresoD().getTime()), new Date(actualDate+JsfUtil.getMilisPerDay(6L)+schedule.getHoraSalidaD().getTime()));
            eventModel.addEvent(event);
        }
        JsfUtil.redirectTo(Navigation.PAGE_SCHEDULE_EDIT);
    }
    
    public String updateFromForm(){
        loadEvents();
        update();
        clean();
        JsfUtil.addSuccessMessage(BundleUtils.getBundleProperty("SuccessfullyUpdatedRegistry"));
        return Navigation.PAGE_MASTER_DATA_SCHEDULE;
    }

    private void loadEvents() {
        
        Calendar calendar = Calendar.getInstance();
         GeneralControl generalControl = JsfUtil.findBean("generalControl");
        selected.setSucursal(generalControl.getSelectedBranchOffice());
        for (ScheduleEvent event : eventModel.getEvents()){
            calendar.setTime(event.getStartDate());
            int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
            switch (dayOfWeek) {
                case 1://Sunday
                    selected.setDomingo(true);
                    selected.setHoraIngresoD(event.getStartDate());
                    selected.setHoraSalidaD(event.getEndDate());
                    break;
                case 2://Monday
                    selected.setLunes(true);
                    selected.setHoraIngresoL(event.getStartDate());
                    selected.setHoraSalidaL(event.getEndDate());
                    break;
                case 3://Tuesday
                    selected.setMartes(true);
                    selected.setHoraIngresoM(event.getStartDate());
                    selected.setHoraSalidaM(event.getEndDate());
                    break;
                case 4://Wednesday
                    selected.setMiercoles(true);
                    selected.setHoraIngresoW(event.getStartDate());
                    selected.setHoraSalidaW(event.getEndDate());
                    break;
                case 5://Thursday
                    selected.setJueves(true);
                    selected.setHoraIngresoJ(event.getStartDate());
                    selected.setHoraSalidaJ(event.getEndDate());
                    break;
                case 6://Friday
                    selected.setViernes(true);
                    selected.setHoraIngresoV(event.getStartDate());
                    selected.setHoraSalidaV(event.getEndDate());
                    break;
                case 7://Saturday
                    selected.setSabado(true);
                    selected.setHoraIngresoS(event.getStartDate());
                    selected.setHoraSalidaS(event.getEndDate());
                    break;
            }
        }
    }
}
