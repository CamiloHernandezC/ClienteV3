/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Querys;

/**
 *
 * @author MAURICIO
 */
public class Querys {
    //<editor-fold desc="PERSONAS SUCURSAL CLI QUERY" defaultstate="collapsed">
    public static final String PERSONAS_SUCURSAL_CLI_ALL= "SELECT a FROM PersonasSucursalCli a ";
    public static final String PERSONAS_SUCURSAL_CLI_PERSONA= " a.personasSucursalCliPK.idPersona = '";
    public static final String PERSONAS_SUCURSAL_CLI_SUCURSAL= " a.personasSucursalCliPK.sucursal = '";
    public static final String PERSONAS_SUCURSAL_CLI_ESTADO= " a.estado.idEstado = '";
    public static final String PERSONAS_SUCURSAL_CLI_NO_ESTADO= " a.estado.idEstado != '";
        public static String PERSONAS_SUCURSAL_ID_EXTERNO=" a.idExterno = '";
    //</editor-fold>
    //<editor-fold desc="PERSONAS CLI QUERY" defaultstate="collapsed">
    public static final String PERSONA_CLI_ALL= "SELECT a FROM PersonasCli a ";
    public static final String PERSONA_CLI_DOC_TYPE= " a.tipoDocumento.tipodocumento = '";
    public static final String PERSONA_CLI_DOC_NUMBER= " a.numDocumento = '";
    public static final String PERSONA_CLI_SUCURSAL= " a.idSucursal.idSucursal = '";
    public static final String PERSONA_CLI_ESTADO= " a.idEstado.idEstado = '";
    public static final String PERSONA_CLI_ESTADO_N= " a.idEstado.idEstado != '";
    public static final String PERSONA_CLI_PRIMARY_KEY= "SELECT a FROM PersonasCli a ORDER BY a.idPersona DESC";
    public static final String PERSONA_CLI_IN_SUCURSAL= " a.idSucursal IN ";
    //</editor-fold>
    //<editor-fold desc="MOV PERSONAS CLI QUERY" defaultstate="collapsed">
    public static final String MOV_PERSONA_CLI_ALL= "SELECT a FROM MovPersonasCli a ";
    public static final String MOV_PERSONA_CLI_PERSONA= " a.idPersona.idPersona = '";
    public static String MOV_PERSONA_CLI_SUCURSAL=" a.idSucursal.idSucursal = '";
    public static final String MOV_PERSONA_CLI_FECHA_SALIDA_NULL= " a.fechaSalida IS NULL";
    public static final String MOV_PERSONA_CLI_PRIMARY_KEY= "SELECT a FROM MovPersonasCli a ORDER BY a.idMovimiento DESC";
    public static String MOV_PERSONA_CLI_ORDER_BY_ID = " ORDER BY a.idMovimiento DESC";
    //</editor-fold>
    //<editor-fold desc="MUNICIPIOS CLI QUERY" defaultstate="collapsed">
    public static final String MUNICIPIOS_CLI_DEPARTAMENTO= "SELECT a FROM MunicipiosCli a where a.idDepartamento.idDepartamento = '";
    //</editor-fold>
    //<editor-fold desc="PORTERIA SUCURSAL CLI QUERY" defaultstate="collapsed">
    public static String PORTERIA_SUCURSAL_CLI_PORTERIA= "SELECT a FROM PorteriaSucursalCli a WHERE a.porteriaSucursalCliPK.porteria =";
    //</editor-fold>

    
    
}
