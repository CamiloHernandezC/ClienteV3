/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

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
    public static final Long STATUS_ENTRY = 3L;
    public static final Long STATUS_BLOCKED= 2L;
    //</editor-fold>
    
    // <editor-fold desc="DOCUMENT_TYPE" defaultstate="collapsed">
    public static final String DOCUMENT_TYPE_CEDULA = "13";
    //</editor-fold>
    
    // <editor-fold desc="ORIGIN ENTERPRISE" defaultstate="collapsed">
    public static final String ORIGIN_ENTERPRISE_OTHER = "1";
    //</editor-fold>
    //</editor-fold>
    
    
    
}
