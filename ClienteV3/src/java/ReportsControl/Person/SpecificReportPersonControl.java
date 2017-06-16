/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ReportsControl.Person;

import Converters.util.JsfUtil;
import Entities.MovPersonas;
import Entities.TiposDocumento;
import GeneralControl.GeneralControl;
import Querys.Querys;
import Utils.BundleUtils;
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
import org.primefaces.model.chart.ChartSeries;

/**
 *
 * @author chernandez
 */
@Named("specificReportPersonControl")
@SessionScoped
public class SpecificReportPersonControl implements Serializable {

    @EJB
    private Facade.MovPersonasFacade ejbFacade;
    private List<MovPersonas> items = null;
    private MovPersonas selected;
    private TiposDocumento docType;
    private String docNumber;

    private BarChartModel barModel = new BarChartModel();
    private Date startDate;
    private Date endDate;
    private Date firstDay;
    private Date lastDay;
    private HashMap workedHours;
    
    private boolean showBarModel;//First time and when aren't results to show in bar, this must be false, otherwise thw view will not working propertly

    public boolean isShowBarModel() {
        return showBarModel;
    }

    public BarChartModel getBarModel() {
        return barModel;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public TiposDocumento getDocType() {
        return docType;
    }

    public void setDocType(TiposDocumento docType) {
        this.docType = docType;
    }

    public String getDocNumber() {
        return docNumber;
    }

    public void setDocNumber(String docNumber) {
        this.docNumber = docNumber;
    }

    public MovPersonas getSelected() {
        return selected;
    }

    public void setSelected(MovPersonas selected) {
        this.selected = selected;
    }

    public String generateReport() {
        GeneralControl generalControl = JsfUtil.findBean("generalControl");
        if (generalControl.getSelectedBranchOffice() == null) {
            JsfUtil.addErrorMessage(BundleUtils.getBundleProperty("EditPersonasCliRequiredMessage_idSucursal"));
            return null;
        }
        initReport();
        getMovs();
        if(!items.isEmpty()){
            showBarModel = true;
            countWorkedHours();
            loadBarModel();
        }
        return null;
    }

    public void getMovs() {
        GeneralControl generalControl = JsfUtil.findBean("generalControl");
        if(generalControl.getSelectedBranchOffice()==null){
            return;
        }
        int branchOffice = generalControl.getSelectedBranchOffice().getIdSucursal();
        String squery = Querys.MOV_PERSONA_CLI_ALL + "WHERE" + Querys.MOV_PERSONA_CLI_SUCURSAL
                + branchOffice + "' AND" + Querys.MOV_PERSONA_CLI_SALIDA_FORZADA + "0' AND" + Querys.MOV_PERSONA_CLI_INGRESO_FORZADO + "0' AND" + Querys.MOV_PERSONA_CLI_TIPO_DOC
                + docType.getTipoDocumento()
                + "' AND" + Querys.MOV_PERSONA_CLI_NUM_DOC + docNumber
                + "' AND" + Querys.MOV_PERSONA_CLI_FECHA_SALIDA_NOT_NULL;//TODO dates filter
        Result result = ejbFacade.findByQueryArray(squery);
        if (result.errorCode == Constants.NO_RESULT_EXCEPTION) {//No matter if no result, items = result
            JsfUtil.addErrorMessage(BundleUtils.getBundleProperty("NoResult"));
        }
        items = (List<MovPersonas>) result.result;
    }

    public void countWorkedHours() {

        boolean firstTime = true;
        workedHours = new HashMap();
        for (MovPersonas mov : items) {
            if (mov.getFechaSalida().before(mov.getFechaEntrada())) {
                continue;
            }
            //ENCUENTRA EL PRIMER Y ÚLTIMO DÍA QUE SE TIENE REGISTRO
            if (firstTime || mov.getFechaEntrada().before(firstDay)) {
                firstDay = mov.getFechaEntrada();
            }
            if (firstTime || mov.getFechaSalida().after(lastDay)) {
                lastDay = mov.getFechaSalida();
            }

            Date entryDate = mov.getFechaEntrada();
            /*-------------------Make up for seconds---------------------------*/
            Calendar c = Calendar.getInstance();
            c.setTime(mov.getHoraEntrada());
            int second = c.get(Calendar.SECOND);
            Date entryHour = new Date(mov.getHoraEntrada().getTime() - second * 1000);

            c.setTime(mov.getHoraSalida());
            second = c.get(Calendar.SECOND);
            Date leaveHour = new Date(mov.getHoraSalida().getTime() - second * 1000);
            /*----------------------------------------------------------------*/
            Date leaveDate = mov.getFechaSalida();

            while (leaveDate.after(entryDate)) {
                long milisPerDay = 24 * 60 * 60 * 1000;

                workedHours = calculateHours(entryDate, (milisPerDay - entryHour.getTime()));
                entryHour.setTime(0L);
                entryDate.setTime(entryDate.getTime() + milisPerDay);

            }
            if (leaveDate.equals(entryDate)) {
                workedHours = calculateHours(entryDate, (leaveHour.getTime() - entryHour.getTime()));
            }
            firstTime = false;
        }
    }

    public HashMap calculateHours(Date entryDate, Long difference) {
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        String date = format.format(entryDate);
        int[] time = (int[]) workedHours.get(date);
        int hours = (int) (difference / (1000 * 60 * 60));//Hours
        int minutes = (int) (difference / (1000 * 60) % 60);//Minutes
        if (time != null) {
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

    private void loadBarModel() {
        barModel.setTitle(BundleUtils.getBundleProperty("ComparativeTime"));
        barModel.setLegendPosition("n");
        barModel.setShowPointLabels(false);//Show value always
        barModel.setShowDatatip(true);//Show value when mouse overlap
        barModel.setLegendPosition("e");
        barModel.setAnimate(true);

        ChartSeries chartSeries = createSerie();

        Calendar c = Calendar.getInstance();
        c.setTime(firstDay);
        int oldMonth = c.get(Calendar.MONTH);
        for (Date i = firstDay; i.before(lastDay) || i.equals(lastDay); i = new Date(i.getTime() + 24 * 60 * 60 * 1000)) {

            c.setTime(i);
            int day = c.get(Calendar.DAY_OF_MONTH);
            int month = c.get(Calendar.MONTH);
            if (month != oldMonth) {
                chartSeries.setLabel(BundleUtils.getBundleProperty("WorkedHours") + " " + Constants.getMonthName(oldMonth));
                barModel.addSeries(chartSeries);
                oldMonth = month;
                chartSeries = createSerie();
            }
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
            String date = format.format(i);
            int[] time = (int[]) workedHours.get(date);
            if (time == null) {
                chartSeries.set(day, 0);
                continue;
            }
            chartSeries.set(day, time[0] + (float) time[1] / 60);//Horas + minutos (en horas)
        }
        //Assign the last month serie, if only one month is available then it is assigned here too
        chartSeries.setLabel(BundleUtils.getBundleProperty("WorkedHours") + " " + Constants.getMonthName(oldMonth));
        barModel.addSeries(chartSeries);

    }

    /**
     * Init chart serie to load all days, otherwise it will not be shown in
     * graphic
     *
     * @return
     */
    private ChartSeries createSerie() {
        ChartSeries chartSerie = new ChartSeries();
        for (int day = 1; day <= 31; day++) {
            chartSerie.set(day, 0);
        }
        return chartSerie;
    }

    private void initReport() {
        items = new ArrayList<>();
        barModel = new BarChartModel();
        showBarModel = false;
    }
}
