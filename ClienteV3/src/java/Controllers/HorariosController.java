/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Controllers.util.JsfUtil;
import Entities.Horarios;
import GeneralControl.GeneralControl;
import Utils.BundleUtils;
import Utils.Constants;
import Utils.Navigation;
import Utils.Result;
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
    private ScheduleEvent event = new DefaultScheduleEvent();
    

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
        //TODO VERIFY IF OTHER EVENT HAS THE SAME START DATE AND SHOW ERROR MESSAGE
        Date endDate = new Date(((Date) selectEvent.getObject()).getTime() + 60 * 60 * 1000);
        event = new DefaultScheduleEvent("", (Date) selectEvent.getObject(), endDate);
        eventModel.addEvent(event);
    }

    public void onEventResize(ScheduleEntryResizeEvent event) {
        //Nothing to do here
    }

    //TODO WHEN SAVE GET DAY OF WEEK FOR HORARIOS TABLE (IN OTHER METHOD
    public String changeViewToCreate() {
        return Navigation.PAGE_CREATE_SCHEDULE;
    }

    public String save() {
        List<ScheduleEvent> eventsList = eventModel.getEvents();
        Calendar calendar = Calendar.getInstance();
        selected = new Horarios();
        GeneralControl generalControl = JsfUtil.findBean("generalControl");
        selected.setSucursal(generalControl.getSelectedBranchOffice());
        for (ScheduleEvent event : eventsList) {
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
    }
    
    public void deleteEvent(ActionEvent actionEvent){
        eventModel.deleteEvent(event);
    }
}
