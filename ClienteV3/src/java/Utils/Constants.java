/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import Converters.util.JsfUtil;

/**
 *
 * @author MAURICIO
 */
public class Constants {
    
    // <editor-fold desc="PERSISTENCE ACTIONS" defaultstate="collapsed">
    public static final int CREATE = 1;
    public static final int UPDATE = 2;
    //</editor-fold>
    
    // <editor-fold desc="ERRORS" defaultstate="collapsed">
    public static final int OK = 0;
    public static final int UNKNOWN_EXCEPTION = 1;
    public static final int UNKNOWN_EXCEPTION_AT_FINALLY = 2;
    public static final int NO_RESULT_EXCEPTION = 3;
    public static final int NO_UNIQUE_RESULT_EXCEPTION = 4;
    public static final int VALIDATION_ERROR = 5;
    public static final int PERSISTANCE_EXCEPTION= 6;
    public static final int REPEATED_RECORD=7;
    //</editor-fold>
    
    //THIS ERRORS MESSAGES ARE ONLY FOR INTERNAL USE
    // <editor-fold desc="ERROR MESSAGES" defaultstate="collapsed">
    public static final String MESSAGE_VALIDATION_ERROR = "APP ERROR: VALIDATION ERROR ";
    public static final String MESSAGE_UNKNOWN_EXCEPTION = "APP EXCEPTION: UNKNOWN EXCEPTION ";
    public static final String MESSAGE_UNKNOWN_EXCEPTION_AT_FINALLY = "APP EXCEPTION: UNKNOWN EXCEPTION AT FINALLY ";
    public static final String MESSAGE_NO_RESULT_EXCEPTION = "APP EXCEPTION: NO RESULT EXCEPTION";
    public static final String MESSAGE_NO_UNIQUE_RESULT_EXCEPTION = "APP EXCEPTION: NO UNIQUE RESULT EXCEPTION";
    public static final String MESSAGE_DATE_FORMAT_EXCEPTION = "APP EXCEPTION: DATE FORMAT EXCEPTION";
    //</editor-fold>
    
    // <editor-fold desc="DATA BASE CONSTANTS" defaultstate="collapsed">
    
    //NOMBRE DEL CAMPO FORMULARIO EN LA TABLA CONFIG DE LA BASE DE DATOS
    // <editor-fold desc="FORMULARIO CONFIGURACION" defaultstate="collapsed">
    public static String CONFIGPERSONSFORM ="PERSONA";
    //</editor-fold>
    
    // <editor-fold desc="ENTITIES" defaultstate="collapsed">
    public static final String ENTITY_VISITANT = "1";
    //</editor-fold>
    
    // <editor-fold desc="STATUS" defaultstate="collapsed">
    public static final Integer STATUS_ENTRY = 3;
    public static final Integer STATUS_BLOCKED= 2;
    public static final Integer STATUS_INACTIVE= 4;
    public static final Integer STATUS_ACTIVE=1;
    //</editor-fold>
    
    // <editor-fold desc="DOCUMENT_TYPE" defaultstate="collapsed">
    public static final String DOCUMENT_TYPE_CEDULA = "13";
    //</editor-fold>
    
    // <editor-fold desc="ORIGIN ENTERPRISE" defaultstate="collapsed">
    public static final String ORIGIN_ENTERPRISE_OTHER = "1";
    //</editor-fold>
    //</editor-fold>
    
    // <editor-fold desc="HTTP SESSION" defaultstate="collapsed">
    public static final String SESSION_USER="USER";
    //</editor-fold>
    public static int NUMBER_OF_PERSON_TYPES=3;
    public static int MENU_TYPE_FATHER = 1;
    public static int MENU_TYPE_CHILDREN = 2;
    
    
    
    public static String getMonthName(int monthNumber){
        switch(monthNumber){
            case 0:
                return BundleUtils.getBundleProperty("January");
            case 1:
                return BundleUtils.getBundleProperty("February");
            case 2:
                return BundleUtils.getBundleProperty("March");
            case 3:
                return BundleUtils.getBundleProperty("April");
            case 4:
                return BundleUtils.getBundleProperty("May");
            case 5:
                return BundleUtils.getBundleProperty("June");
            case 6:
                return BundleUtils.getBundleProperty("July");
            case 7:
                return BundleUtils.getBundleProperty("August");
            case 8:
                return BundleUtils.getBundleProperty("September");
            case 9:
                return BundleUtils.getBundleProperty("October");
            case 10:
                return BundleUtils.getBundleProperty("November");
            case 11:
                return BundleUtils.getBundleProperty("December");
        }
        return "";//This should never happend
        
    }
    
}
