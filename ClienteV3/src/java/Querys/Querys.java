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
    public static final String PERSONAS_SUCURSAL_CLI_ALL= "SELECT a FROM PersonasSucursal a ";
    public static final String PERSONAS_SUCURSAL_CLI_PERSONA= " a.personasSucursalPK.idPersona = '";
    public static final String PERSONAS_SUCURSAL_CLI_SUCURSAL= " a.personasSucursalPK.sucursal = '";
    public static final String PERSONAS_SUCURSAL_CLI_ESTADO= " a.estado.idEstado = '";
    public static final String PERSONAS_SUCURSAL_CLI_NO_ESTADO= " a.estado.idEstado != '";
        public static String PERSONAS_SUCURSAL_ID_EXTERNO=" a.idExterno = '";
    //</editor-fold>
    //<editor-fold desc="PERSONAS CLI QUERY" defaultstate="collapsed">
    public static final String PERSONA_CLI_ALL= "SELECT a FROM Personas a ";
    public static final String PERSONA_CLI_DOC_TYPE= " a.tipoDocumento.tipoDocumento = '";
    public static final String PERSONA_CLI_DOC_NUMBER= " a.numeroDocumento = '";
    public static final String PERSONA_CLI_SUCURSAL= " a.idSucursal.idSucursal = '";
    public static final String PERSONA_CLI_ESTADO= " a.idEstado.idEstado = '";
    public static final String PERSONA_CLI_ESTADO_N= " a.idEstado.idEstado != '";
    public static final String PERSONA_CLI_LAST_PRIMARY_KEY= "SELECT a FROM Personas a ORDER BY a.idPersona DESC";
    public static final String PERSONA_CLI_IN_SUCURSAL= " a.idSucursal IN ";
    //</editor-fold>
    //<editor-fold desc="MOV PERSONAS CLI QUERY" defaultstate="collapsed">
    public static final String MOV_PERSONA_CLI_ALL= "SELECT a FROM MovPersonas a ";
    public static final String MOV_PERSONA_CLI_PERSONA= " a.personasSucursal.personas.idPersona = '";
    public static String MOV_PERSONA_CLI_SUCURSAL=" a.personasSucursal.sucursales.idSucursal = '";
    public static final String MOV_PERSONA_CLI_FECHA_SALIDA_NULL= " a.fechaSalida IS NULL";
    public static final String MOV_PERSONA_CLI_FECHA_SALIDA_NOT_NULL= " a.fechaSalida IS NOT NULL";
    public static final String MOV_PERSONA_CLI_PRIMARY_KEY= "SELECT a FROM MovPersonas a ORDER BY a.idMovimiento DESC";
    public static String MOV_PERSONA_CLI_ORDER_BY_ID = " ORDER BY a.idMovimiento DESC";
    public static final String MOV_PERSONA_CLI_TIPO_DOC = " a.personasSucursal.personas.tipoDocumento.tipoDocumento = '";
    public static final String MOV_PERSONA_CLI_NUM_DOC = " a.personasSucursal.personas.numeroDocumento = '";
    public static final String MOV_PERSONA_CLI_SALIDA_FORZADA=" a.salidaForzosa = '";
    public static final String MOV_PERSONA_CLI_INGRESO_FORZADO=" a.ingresoForzado = '";
    public static String MOV_PERSONA_CLI_FECHA_INGRESO_BETWEEN=" a.fechaEntrada BETWEEN '";
    //</editor-fold>
    //<editor-fold desc="MUNICIPIOS CLI QUERY" defaultstate="collapsed">
    public static final String MUNICIPIOS_CLI_DEPARTAMENTO= "SELECT a FROM Municipios a where a.departamento.departamento = '";
    //</editor-fold>
    //<editor-fold desc="PORTERIA SUCURSAL CLI QUERY" defaultstate="collapsed">
    public static final String PORTERIA_SUCURSAL_CLI_PORTERIA= "SELECT a FROM PorteriaSucursal a WHERE a.porteriaSucursalPK.porteria =";
    //</editor-fold>
    
    public static final String AREAS_EMPRESA_ALL="SELECT a FROM AreasEmpresa a";
    public static final String AREAS_EMPRESA_SUCURSAL=" a.sucursal.idSucursal = '";
    
    public static final String USUARIOS_ALL = "SELECT a FROM Usuarios a";
    public static final String USUARIOS_ID=" a.idUsuario = '";
    public static final String USUARIOS_PASSWORD=" a.password = '";
    public static final String USUARIOS_SESION= " a.sesion = '";
    public static final String USUARIOS_ID_SESION= " a.iDSesion = '";
    public static final String USUARIOS_FECHA_DESDE= " a.fechaDesde <= '";
    public static final String USUARIOS_FECHA_HASTA= " a.fechaHasta >= '";
    
    
    public static final String ACCESO_USUARIO_ALL ="SELECT a FROM AccesoUsuario a";
    public static final String ACCESO_USUARIO_USUARIO=" a.usuario.idUsuario = '";
    
    public static final String AREAS_EMPRESA_LAST_PRIMARY_KEY="SELECT a FROM AreasEmpresa a ORDER BY a.idareaemp DESC";
    public static final String EMPRESA_ORIGEN_LAST_PRIMARY_KEY="SELECT e FROM EmpresaOrigen e ORDER BY e.idEmorigen DESC";
    public static final String MATERIALES_LAST_PRIMARY_KEY="SELECT m FROM Materiales m ORDER BY m.idMaterial DESC";
    public static final String NOTIFICACIONES_LAST_PRIMARY_KEY="SELECT n FROM Notificaciones n ORDER BY n.idNotificacion DESC";
    public static final String OBJETOS_LAST_PRIMARY_KEY="SELECT o FROM Objetos o ORDER BY o.idObjeto DESC";
    public static final String ENTIDADES_ALL="SELECT e FROM Entidades e";
    public static final String ENTIDADES_CATEGORIA=" e.idCategoria.idCategoria = '";
    public static final String HORARIOS_ALL="SELECT a FROM Horarios a";
    public static final String HORARIOS_PRIMARY_KEY="SELECT a FROM Horarios a ORDER BY a.idHorario DESC";
    public static final String ASSISTANCE_SELECT = "SELECT p.nombre1, p.apellido1, m.fechaEntrada, m.horaEntrada, h.horaIngreso";
    public static final String ASSISTANCE_TABLES = "  from PersonasSucursal ps LEFT JOIN  MovPersonas m on m.personasSucursal = ps and m.fechaSalida is null INNER JOIN Personas p on p.idPersona = ps.personasSucursalPK.idPersona INNER JOIN Horarios h on h =  ps.horario WHERE h.";
    //public static final String ASSISTANCE_SELECT = "SELECT p.nombre1, m.fechaEntrada, m.horaEntrada, m.fechaSalida, m.horaSalida, h.horaIngreso";
    //public static final String ASSISTANCE_TABLES = " from (((PersonasSucursal ps inner join ps.horario h) inner join ps.idPersona p), MovPersonas m) WHERE m.idPersona = ps.idPersona AND h.";
    public static final String HORARIOS_HORA_INGRESO=" h.horaIngreso";
    public static final String HORARIOS_HORA_SALIDA=" h.horaSalida";
    public static final String PRIVILEGIOS_CLIENTE_ALL="SELECT p FROM PrivilegiosCliente p";
    public static final String MENU_CLIENTE_ALL="SELECT m FROM MenuCliente m";
    public static final String MENU_CLIENTE_NIVEL_MORE_EQUAL="m.nivel <= '";
    public static final String MENU_CLIENTE_CODIGO="m.codigo = '";
    public static String MENU_CLIENTE_JOIN_PRIVILEGIOS="SELECT m FROM MenuCliente m LEFT JOIN PrivilegiosCliente p on (m != p.codigoMenu and p.ver = 'false')";
    public static String MENU_CLIENTE_HAS_PRIVILEGE=" OR m in (SELECT p2.codigoMenu FROM PrivilegiosCliente p2 WHERE p2.ver = 'true')";
    
    
}
