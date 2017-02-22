/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ReportsControl.Person;

import Controllers.util.JsfUtil;
import Entities.MovPersonasCli;
import Entities.TiposDocumentoCli;
import Querys.Querys;
import Utils.Constants;
import Utils.Result;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import org.primefaces.model.chart.BarChartModel;

/**
 *
 * @author chernandez
 */
@Named("specificReportPersonControl")
@SessionScoped
public class SpecificReportPersonControl implements Serializable{
    @EJB
    private Facade.MovPersonasCliFacade ejbFacade;
    private List<MovPersonasCli> items = null;
    private MovPersonasCli selected;
    private TiposDocumentoCli docType;
    private String docNumber;
    
    private BarChartModel barModel = new BarChartModel();
    private Date startDate;
    private Date endDate;

    public TiposDocumentoCli getDocType() {
        return docType;
    }

    public void setDocType(TiposDocumentoCli docType) {
        this.docType = docType;
    }

    public String getDocNumber() {
        return docNumber;
    }

    public void setDocNumber(String docNumber) {
        this.docNumber = docNumber;
    }

    
    public MovPersonasCli getSelected() {
        return selected;
    }

    public void setSelected(MovPersonasCli selected) {
        this.selected = selected;
    }
    
    
    public void generateReport(){
        getMovs();
        countWorkedHours();
    }
    
    public void getMovs(){
            String branchOffice = "1";
            String squery = Querys.MOV_PERSONA_CLI_ALL+"WHERE"+Querys.MOV_PERSONA_CLI_SUCURSAL+
                    branchOffice+"' AND"+Querys.MOV_PERSONA_CLI_SALIDA_FORZADA+"0' AND"+Querys.MOV_PERSONA_CLI_TIPO_DOC+
                    docType.getTipodocumento()+
                    "' AND"+Querys.MOV_PERSONA_CLI_NUM_DOC+docNumber+
                    "' AND"+Querys.MOV_PERSONA_CLI_FECHA_SALIDA_NOT_NULL;//TODO ASSIGN REAL BRANCH OFFICE HERE
           Result result =  ejbFacade.findByQueryArray(squery);
           if(result.errorCode==Constants.NO_RESULT_EXCEPTION){
               JsfUtil.addErrorMessage("NO RESULT");//TODO CREATE BUNDLE PROPERTIE
               //TODO RELOAD
           }
           items = (List<MovPersonasCli>) result.result;
    }
    
    public void countWorkedHours(){
        Map workedHours = new HashMap();
        for(MovPersonasCli mov:items){
            if(mov.getFechaSalida().before(mov.getFechaEntrada())){
                continue;
            }
            Date entryDate = mov.getFechaEntrada();
            Date entryHour = mov.getHoraEntrada();
            Date leaveDate = mov.getFechaSalida();
            Date leaveHour = mov.getHoraSalida();
            
            while (leaveDate.after(entryDate)) {
                long milisPerDay = 24*60*60*1000;
                
                workedHours = calculateHours(entryDate, workedHours, (milisPerDay-entryHour.getTime()));
                entryHour.setTime(0L);
                entryDate.setTime(entryDate.getTime()+milisPerDay);
                
            }
            if(leaveDate.equals(entryDate)){
                workedHours = calculateHours(entryDate, workedHours, (leaveHour.getTime()-entryHour.getTime()));
            }
        }
    }
    
    public Map calculateHours(Date entryDate, Map workedHours, Long difference){
        SimpleDateFormat format = new SimpleDateFormat("ddMMyyyy");
            String date = format.format(entryDate);
            int[] time = (int[]) workedHours.get(date);
            int hours = (int) (difference / (1000 * 60 * 60));//Hours
            int minutes = (int) (difference / (1000 * 60) % 60);//Minutes
            if(time!=null){
                time[0] += hours;
                time[1] += minutes;
                workedHours.put(date, time);
                return workedHours;
            }
            time = new int[2];
            time[0] = hours;
            time[1] = minutes;
            workedHours.put(date, time);
            return workedHours;
    }
}
