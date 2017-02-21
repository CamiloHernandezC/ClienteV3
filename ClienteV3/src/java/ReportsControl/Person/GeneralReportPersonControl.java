/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ReportsControl.Person;

import Controllers.MovPersonasCliController;
import Controllers.util.JsfUtil;
import Entities.MovPersonasCli;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import org.primefaces.model.chart.BarChartModel;

/**
 *
 * @author chernandez
 */
@Named("generalReportPersonControl")
@SessionScoped
public class GeneralReportPersonControl implements Serializable{
    
    @EJB
    private Facade.MovPersonasCliFacade ejbFacade;
    private List<MovPersonasCli> items = null;
    private MovPersonasCli selected;
    
    private void generateReport(){
        
    }
}
