/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Querys;

import java.sql.Date;

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
    public static final String PERSONA_CLI_LAST_PRIMARY_KEY= "SELECT a FROM PersonasCli a ORDER BY a.idPersona DESC";
    public static final String PERSONA_CLI_IN_SUCURSAL= " a.idSucursal IN ";
    //</editor-fold>
    //<editor-fold desc="MOV PERSONAS CLI QUERY" defaultstate="collapsed">
    public static final String MOV_PERSONA_CLI_ALL= "SELECT a FROM MovPersonasCli a ";
    public static final String MOV_PERSONA_CLI_PERSONA= " a.personasSucursalCli.personasCli.idPersona = '";
    public static String MOV_PERSONA_CLI_SUCURSAL=" a.personasSucursalCli.sucursalesCli.idSucursal = '";
    public static final String MOV_PERSONA_CLI_FECHA_SALIDA_NULL= " a.fechaSalida IS NULL";
    public static final String MOV_PERSONA_CLI_FECHA_SALIDA_NOT_NULL= " a.fechaSalida IS NOT NULL";
    public static final String MOV_PERSONA_CLI_PRIMARY_KEY= "SELECT a FROM MovPersonasCli a ORDER BY a.idMovimiento DESC";
    public static String MOV_PERSONA_CLI_ORDER_BY_ID = " ORDER BY a.idMovimiento DESC";
    public static final String MOV_PERSONA_CLI_TIPO_DOC = " a.personasSucursalCli.personasCli.tipoDocumento.tipodocumento = '";
    public static final String MOV_PERSONA_CLI_NUM_DOC = " a.personasSucursalCli.personasCli.numDocumento = '";
    public static final String MOV_PERSONA_CLI_SALIDA_FORZADA=" a.salidaForzosa = '";
    public static final String MOV_PERSONA_CLI_INGRESO_FORZADO=" a.ingresoForzado = '";
    public static String MOV_PERSONA_CLI_FECHA_INGRESO_BETWEEN=" a.fechaEntrada BETWEEN '";
    //</editor-fold>
    //<editor-fold desc="MUNICIPIOS CLI QUERY" defaultstate="collapsed">
    public static final String MUNICIPIOS_CLI_DEPARTAMENTO= "SELECT a FROM MunicipiosCli a where a.idDepartamento.idDepartamento = '";
    //</editor-fold>
    //<editor-fold desc="PORTERIA SUCURSAL CLI QUERY" defaultstate="collapsed">
    public static final String PORTERIA_SUCURSAL_CLI_PORTERIA= "SELECT a FROM PorteriaSucursalCli a WHERE a.porteriaSucursalCliPK.porteria =";
    //</editor-fold>
    
    public static final String AREAS_EMPRESA_ALL="SELECT a FROM AreasEmpresaCli a";
    public static final String AREAS_EMPRESA_SUCURSAL=" a.idSucursal.idSucursal = '";
    
    public static final String USUARIOS_ALL = "SELECT a FROM UsuariosCli a";
    public static final String USUARIOS_ID=" a.idUsuario = '";
    public static final String USUARIOS_PASSWORD=" a.password = '";
    public static final String USUARIOS_SESION= " a.sesion = '";
    public static final String USUARIOS_ID_SESION= " a.iDSesion = '";
    
    public static final String ACCESO_USUARIO_ALL ="SELECT a FROM AccesoUsuario a";
    public static final String ACCESO_USUARIO_USUARIO=" a.usuario.idUsuario = '";
    
    public static final String AREAS_EMPRESA_LAST_PRIMARY_KEY="SELECT a FROM AreasEmpresaCli a ORDER BY a.idareaemp DESC";
    public static final String EMPRESA_ORIGEN_LAST_PRIMARY_KEY="SELECT e FROM EmpresaOrigenCli e ORDER BY e.idEmorigen DESC";
    public static final String MATERIALES_LAST_PRIMARY_KEY="SELECT m FROM MaterialesCli m ORDER BY m.idMaterial DESC";
    public static final String NOTIFICACIONES_LAST_PRIMARY_KEY="SELECT n FROM NotificacionesCli n ORDER BY n.idNotificacion DESC";
    public static final String OBJETOS_LAST_PRIMARY_KEY="SELECT o FROM ObjetosCli o ORDER BY o.idObjeto DESC";
    public static final String ENTIDADES_ALL="SELECT e FROM EntidadesCli e";
    public static final String ENTIDADES_CATEGORIA=" e.idCategoria.idCategoria = '";
    
    
    
    
    
    
}
