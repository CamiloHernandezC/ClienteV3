/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ReportsControl.Person;

import Controllers.util.JsfUtil;
import Entities.MovPersonasCli;
import Entities.TiposDocumentoCli;
import GeneralControl.GeneralControl;
import Querys.Querys;
import Utils.BundleUtils;
import Utils.Constants;
import Utils.Navigation;
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
import org.primefaces.event.ItemSelectEvent;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;

/**
 *
 * @author chernandez
 */
@Named("personResumeReportControl")
@SessionScoped
public class PersonResumeReportControl implements Serializable {

    @EJB
    private Facade.MovPersonasCliFacade ejbFacade;
    private List<MovPersonasCli> items = null;

    private BarChartModel barModel = new BarChartModel();
    private Date startDate;
    private Date endDate;

    private int yearStartDate;
    private int monthStartDate;
    private int dayStartDate;
    private int yearEndDate;
    private int monthEndDate;
    private int dayEndDate;
    private boolean allowDrillUpMonth;
    private boolean allowDrillUpYear;
    private int yearToCount;
    private int monthToCount;
    private int typeBar;
    private int numberOfYears;

    /*VARIABLES TO STORE TOTAL ENTRIES*/
    private int[] years;
    private int[] months = new int[12];//12 months
    private int[] days = new int[31];//31 days, max days in any months
    /*VARIABLES TO STORE KIND OF PERSON ENTRIES*/
    private HashMap entrysByTypePerson;

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

    public String generateReport() {
        initBar();
        return null;
    }

    public void getMovs() {
        GeneralControl generalControl = JsfUtil.findBean("generalControl");
        if (generalControl.getSelectedBranchOffice() == null) {
            return;
        }
        Long branchOffice = generalControl.getSelectedBranchOffice().getIdSucursal();
        java.sql.Date sqlStartDate = new java.sql.Date(startDate.getTime());
        java.sql.Date sqlEndDate = new java.sql.Date(endDate.getTime());
        String squery = Querys.MOV_PERSONA_CLI_ALL + "WHERE" + Querys.MOV_PERSONA_CLI_SUCURSAL + branchOffice
                + "' AND" + Querys.MOV_PERSONA_CLI_SALIDA_FORZADA + "0' AND" + Querys.MOV_PERSONA_CLI_INGRESO_FORZADO + "0'"
                + " AND" + Querys.MOV_PERSONA_CLI_FECHA_INGRESO_BETWEEN + sqlStartDate + "' AND '" + sqlEndDate + "'";

        Result result = ejbFacade.findByQueryArray(squery);
        items = (List<MovPersonasCli>) result.result;
    }

    private void initBar() {
        Calendar cal = Calendar.getInstance();

        cal.setTime(startDate);
        yearStartDate = cal.get(Calendar.YEAR);
        monthStartDate = cal.get(Calendar.MONTH) + 1;//Starts in 0 for january
        dayStartDate = cal.get(Calendar.DAY_OF_MONTH);

        cal.setTime(endDate);
        yearEndDate = cal.get(Calendar.YEAR);
        monthEndDate = cal.get(Calendar.MONTH) + 1;//Starts in 0 for january
        dayEndDate = cal.get(Calendar.DAY_OF_MONTH);
        //It search in order for the rigth period
        if (yearStartDate < yearEndDate) {
            allowDrillUpYear = true;
            numberOfYears = yearEndDate - yearStartDate;
            createBar(1);
            return;
        }
        if (monthStartDate < monthEndDate) {
            allowDrillUpYear = false;
            allowDrillUpMonth = true;
            yearToCount = yearStartDate;
            createBar(2);
            return;

        }
        if (dayStartDate < dayEndDate) {
            allowDrillUpYear = false;
            allowDrillUpMonth = false;
            yearToCount = yearStartDate;
            monthToCount = monthStartDate - 1;
            createBar(3);
            return;
        }
    }

    public void countMovements(int typeBar) {

        switch (typeBar) {
            case 1:
                entrysByTypePerson = new HashMap();
                years = new int[numberOfYears + 1];
                for (MovPersonasCli mov : items) {
                    Calendar cal = Calendar.getInstance();
                    Date date = mov.getFechaEntrada();
                    cal.setTime(date);
                    int year = cal.get(Calendar.YEAR) - yearStartDate;
                    years[year]++;

                    //COUNTER TO DIFERENCE TYPES OF PERSON 
                    String personType = mov.getPersonasSucursalCli().getEntidad().getIdEntidad();
                    int[] arrayForSpecificType = (int[]) entrysByTypePerson.get(personType);
                    if (arrayForSpecificType == null) {
                        arrayForSpecificType = new int[numberOfYears + 1];
                    }
                    arrayForSpecificType[year]++;
                    entrysByTypePerson.put(personType, arrayForSpecificType);
                }
                break;
            case 2:
                entrysByTypePerson = new HashMap();
                months = new int[12];
                for (MovPersonasCli mov : items) {
                    Calendar cal = Calendar.getInstance();
                    Date date = mov.getFechaEntrada();
                    cal.setTime(date);
                    if (cal.get(Calendar.YEAR) != yearToCount) {
                        continue;
                    }
                    int month = cal.get(Calendar.MONTH);//Starts in 0 for january
                    months[month]++;

                    //COUNTER TO DIFERENCE TYPES OF PERSON 
                    String personType = mov.getPersonasSucursalCli().getEntidad().getIdEntidad();
                    int[] arrayForSpecificType = (int[]) entrysByTypePerson.get(personType);
                    if (arrayForSpecificType == null) {
                        arrayForSpecificType = new int[12];
                    }
                    arrayForSpecificType[month]++;
                    entrysByTypePerson.put(personType, arrayForSpecificType);
                }
                break;
            case 3:
                entrysByTypePerson = new HashMap();
                days = new int[31];
                for (MovPersonasCli mov : items) {
                    Calendar cal = Calendar.getInstance();
                    Date date = mov.getFechaEntrada();
                    cal.setTime(date);
                    if (cal.get(Calendar.YEAR) != yearToCount || cal.get(Calendar.MONTH) != monthToCount) {
                        continue;
                    }
                    int day = cal.get(Calendar.DAY_OF_MONTH);
                    days[day - 1]++;//Calendar.DAY_OF_MONTH start in 1

                    //COUNTER TO DIFERENCE TYPES OF PERSON 
                    String personType = mov.getPersonasSucursalCli().getEntidad().getIdEntidad();
                    int[] arrayForSpecificType = (int[]) entrysByTypePerson.get(personType);
                    if (arrayForSpecificType == null) {
                        arrayForSpecificType = new int[31];
                    }
                    arrayForSpecificType[day - 1]++;
                    entrysByTypePerson.put(personType, arrayForSpecificType);
                }
                break;
        }

    }

    private BarChartModel loadBarModel(int typeBar) {
        BarChartModel model = new BarChartModel();
        model.setTitle(BundleUtils.getBundleProperty("IncomeSummary"));
        model.setLegendPosition("n");
        model.setShowPointLabels(false);//Show value always
        model.setShowDatatip(true);//Show value when mouse overlap
        model.setLegendPosition("e");
        model.setAnimate(true);

        ChartSeries chartSeries = new ChartSeries();
        
        HashMap mapCharts = new HashMap();
        for(int i=1; i<Constants.NUMBER_OF_PERSON_TYPES;i++){//TODO ADD OTHERS ENTITIES HERE
            ChartSeries chartSeriesPersonType = null;
            switch(i){
                case 1://Employees
                    chartSeriesPersonType = new ChartSeries(BundleUtils.getBundleProperty("EmployeesIncome"));
                    break;
                case 2://Visitors
                    chartSeriesPersonType = new ChartSeries(BundleUtils.getBundleProperty("VisitorsIncome"));
                    break;
            }
            mapCharts.put(i, chartSeriesPersonType);
        }

        
        switch (typeBar) {
            case 1:
                chartSeries.setLabel(BundleUtils.getBundleProperty("TotalAnnualIncome"));

                for (int i = yearStartDate; i <= yearEndDate; i++) {
                    chartSeries.set(i, years[i - yearStartDate]);
                    for(int j=1;j<Constants.NUMBER_OF_PERSON_TYPES;j++){
                        int[] arrayPersonType = (int[]) entrysByTypePerson.get(String.valueOf(j));
                        if(arrayPersonType!=null){
                            ChartSeries chartSeriesPersonType = (ChartSeries) mapCharts.get(j);
                            chartSeriesPersonType.set(i, arrayPersonType[i-yearStartDate]);
                            mapCharts.put(j, chartSeriesPersonType);
                        }
                    }
                }
                break;
            case 2:
                chartSeries.setLabel(BundleUtils.getBundleProperty("TotalMonthlyIncome"));
                if (yearStartDate == yearEndDate) {
                    for (int i = monthStartDate - 1; i < monthEndDate; i++) {
                        chartSeries.set(Constants.getMonthName(i), months[i]);
                        for(int j=1;j<Constants.NUMBER_OF_PERSON_TYPES;j++){
                        int[] arrayPersonType = (int[]) entrysByTypePerson.get(String.valueOf(j));
                        if(arrayPersonType!=null){
                            ChartSeries chartSeriesPersonType = (ChartSeries) mapCharts.get(j);
                            chartSeriesPersonType.set(Constants.getMonthName(i), months[i]);
                            mapCharts.put(j, chartSeriesPersonType);
                        }
                    }
                    }
                    break;
                }
                if (yearToCount == yearEndDate) {
                    for (int i = 0; i < monthEndDate; i++) {
                        chartSeries.set(Constants.getMonthName(i), months[i]);
                        for(int j=1;j<Constants.NUMBER_OF_PERSON_TYPES;j++){
                        int[] arrayPersonType = (int[]) entrysByTypePerson.get(String.valueOf(j));
                        if(arrayPersonType!=null){
                            ChartSeries chartSeriesPersonType = (ChartSeries) mapCharts.get(j);
                            chartSeriesPersonType.set(Constants.getMonthName(i), months[i]);
                            mapCharts.put(j, chartSeriesPersonType);
                        }
                    }
                    }
                    break;
                }
                if (yearToCount == yearStartDate) {
                    for (int i = monthStartDate - 1; i < 12; i++) {
                        chartSeries.set(Constants.getMonthName(i), months[i]);
                        for(int j=1;j<Constants.NUMBER_OF_PERSON_TYPES;j++){
                        int[] arrayPersonType = (int[]) entrysByTypePerson.get(String.valueOf(j));
                        if(arrayPersonType!=null){
                            ChartSeries chartSeriesPersonType = (ChartSeries) mapCharts.get(j);
                            chartSeriesPersonType.set(Constants.getMonthName(i), months[i]);
                            mapCharts.put(j, chartSeriesPersonType);
                        }
                    }
                    }
                    break;
                }
                for (int i = 0; i < 12; i++) {
                    chartSeries.set(Constants.getMonthName(i), months[i]);
                    for(int j=1;j<Constants.NUMBER_OF_PERSON_TYPES;j++){
                        int[] arrayPersonType = (int[]) entrysByTypePerson.get(String.valueOf(j));
                        if(arrayPersonType!=null){
                            ChartSeries chartSeriesPersonType = (ChartSeries) mapCharts.get(j);
                            chartSeriesPersonType.set(Constants.getMonthName(i), months[i]);
                            mapCharts.put(j, chartSeriesPersonType);
                        }
                    }
                }
                break;
            case 3:
                chartSeries.setLabel(BundleUtils.getBundleProperty("TotalDailyIncome"));
                for (int i = 1; i <= 31; i++) {
                    chartSeries.set(i, days[i - 1]);
                    for(int j=1;j<Constants.NUMBER_OF_PERSON_TYPES;j++){
                        int[] arrayPersonType = (int[]) entrysByTypePerson.get(String.valueOf(j));
                        if(arrayPersonType!=null){
                            ChartSeries chartSeriesPersonType = (ChartSeries) mapCharts.get(j);
                            chartSeriesPersonType.set(i, days[i - 1]);
                            mapCharts.put(j, chartSeriesPersonType);
                        }
                    }
                }
                break;
        }
        model.addSeries(chartSeries);
        for(int i=1;i<Constants.NUMBER_OF_PERSON_TYPES;i++){
            if(entrysByTypePerson.get(String.valueOf(i))!=null){
                model.addSeries((ChartSeries) mapCharts.get(i));
            }
        }
        return model;
    }

    private void createBar(int typeBar) {
        initVariables();
        getMovs();
        if (items.isEmpty()) {
            JsfUtil.addErrorMessage(BundleUtils.getBundleProperty("NoResult"));
            return;
        }
        countMovements(typeBar);
        barModel = loadBarModel(typeBar);
        setAxis(typeBar);
        this.typeBar = typeBar;
    }

    private void setAxis(int axisType) {

        switch (axisType) {
            case 1:
                Axis xAxis1 = barModel.getAxis(AxisType.X);
                xAxis1.setLabel("Año");
                break;
            case 2:
                Axis xAxis2 = barModel.getAxis(AxisType.X);
                xAxis2.setLabel("Meses del " + yearToCount);
                break;
            case 3:
                Axis xAxis3 = barModel.getAxis(AxisType.X);
                xAxis3.setLabel("Dias de " + Constants.getMonthName(monthToCount) + " del " + yearToCount);
                break;
        }
    }

    public void drillDown(ItemSelectEvent event) {

        switch (typeBar) {
            case 1:
                yearToCount = event.getItemIndex() + yearStartDate;
                createBar(2);
                reload();
                break;
            case 2:
                if (yearToCount == yearStartDate) {
                    monthToCount = event.getItemIndex() + monthStartDate - 1;
                } else {
                    monthToCount = event.getItemIndex();
                }
                createBar(3);
                reload();
                break;
            case 3:
                //Nothing to do here
                break;
        }

    }

    public void drillUp() {
        switch (typeBar) {
            case 1:
                //Nothing to do here
                break;
            case 2:
                if (allowDrillUpYear) {
                    createBar(1);
                } else {
                    JsfUtil.addErrorMessage("El periodo seleccionado no muestra datos anuales");
                }
                reload();
                break;
            case 3:
                if (allowDrillUpYear || allowDrillUpMonth) {
                    createBar(2);
                } else {
                    JsfUtil.addErrorMessage("El periodo seleccionado no muestra datos mensuales");
                }
                reload();
                break;
        }
    }

    private void initVariables() {
        items = new ArrayList<>();
        barModel = new BarChartModel();
    }

    private void reload() {
        JsfUtil.redirectTo(Navigation.PAGE_REPORT_RESUME);
    }
}
