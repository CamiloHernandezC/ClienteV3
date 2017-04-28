package Controllers;

import Entities.PersonasSucursal;
import Controllers.util.JsfUtil;
import Converters.AreasEmpresaController;
import Entities.AreasEmpresa;
import Entities.Arl;
import Entities.Departamentos;
import Entities.Eps;
import Entities.Estados;
import Entities.Municipios;
import Entities.Paises;
import Entities.Personas;
import Entities.TiposDocumento;
import GeneralControl.GeneralControl;
import Models.AbstractMasterDataModel;
import Models.PersonasSucursalModel;
import Querys.Querys;
import Utils.BundleUtils;
import Utils.Constants;
import Utils.Navigation;
import Utils.Result;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.primefaces.context.RequestContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;
import org.primefaces.util.BeanUtils;

@Named("personasSucursalController")
@SessionScoped
public class PersonasSucursalController extends Converters.PersonasSucursalController {

    private int numberOfColums = 23;//Only the number of colums defined in constants are taken in consideration
    protected ArrayList<AbstractMasterDataModel> arrayError = new ArrayList<>();
    private PersonasController personasController;//Can not find bean here because throws an exception

    public PersonasSucursalController() {
    }

    /**
     * Load person where branch office is equal to selected and status is
     * different of inactive
     *
     * @return List of people belonging to selected branch office
     *
     */
    public List<PersonasSucursal> getItemsByBranchOffice() {//TODO LOAD ITEMS ONCE BECAUSE WHEN CHANGE PAGE TO SEE MORE PERSONS IT WILL RELOAD, SO MAKE A BUTTON TO RELOAD OR TRY WITH C:IF WHEN PAGE IS REALOADED, MAKE C:IF ACTION SET BOOLEAN VALUE TO FALSE AND USE THAT BOOLEAN TO ASK IF RELOAD IS NEEDED LIKE VALID SESSION METHOD
        GeneralControl generalControl = JsfUtil.findBean("generalControl");
        if (generalControl.getSelectedBranchOffice() != null) {
            String squery = Querys.PERSONAS_SUCURSAL_CLI_ALL + "WHERE" + Querys.PERSONAS_SUCURSAL_CLI_SUCURSAL + generalControl.getSelectedBranchOffice().getIdSucursal()
                    + "' AND" + Querys.PERSONAS_SUCURSAL_CLI_NO_ESTADO + Constants.STATUS_INACTIVE + "'";
            items = (List<PersonasSucursal>) getFacade().findByQueryArray(squery).result;
        }
        return items;
    }

    public void preEdit(PersonasSucursal person) {
        setSelected(person);
        personasController = JsfUtil.findBean("personasController");
        personasController.setSelected(person.getPersonas());
        JsfUtil.redirectTo(Navigation.PAGE_PERSONAS_EDIT);
    }

    public void blockPerson(PersonasSucursal person) {
        person.setEstado(new Estados(Constants.STATUS_BLOCKED));
        setSelected(person);
        update();
        JsfUtil.addSuccessMessage(BundleUtils.getBundleProperty("RecordBlocked"));
        //TODO SHOW DIALOG TO BLOCK PERSON FOR OTHER BRANCH OFICCES WHERE USER HAS ACCESS
    }

    /**
     * unlock person from marter data list
     *
     * @param person
     */
    public void activePerson(PersonasSucursal person) {
        selected = person;
        selected.setEstado(new Estados(Constants.STATUS_ACTIVE));
        update();
        JsfUtil.addSuccessMessage(BundleUtils.getBundleProperty("RecordUnblocked"));
    }

    /**
     * unlock Person from dialog when trying to create
     *
     * @return
     */
    public String activePerson() {
        activePerson(selected);
        return Navigation.PAGE_MASTER_DATA_PERSON;
    }

    public void handleFileUpload(FileUploadEvent event) {
        //uploadedFile = 

        String[][] values;//here just valid data will be stored
        Result result = loadFile(event.getFile());
        //String[][] tmp = loadFile(uploadedFile);
        switch (result.errorCode) {
            case 0:
                break;
            default://if an error happens
                JsfUtil.addErrorMessage("FAIL LOADIGN FILE");//TODO create bundle  property
                RequestContext.getCurrentInstance().execute("PF('dialogLoad').hide();");
                return;
        }
        values = deleteEmptyData((String[][]) result.result);
        ArrayList<PersonasSucursal> array = (ArrayList<PersonasSucursal>) castFileToEntity(values);
        /*int codeSaveFile = saveFile(array);
        if(codeSaveFile == 0){
            JsfUtil.addSuccessMessage("LOAD SUCCESSFULL");//TODO CREATE BUNDLE PROPERTY HERE
            RequestContext.getCurrentInstance().execute("PF('dialogLoad').hide();");
            return;
        }
        JsfUtil.addErrorMessage("PARTIAL_SUCCESSFUL_OPERATION");//TODO CREATE BUNDLE PROPERTY HERE
        RequestContext.getCurrentInstance().execute("PF('dialogLoad').hide();");*/
    }

    //<editor-fold desc="castFileToEntity" defaultstate="collapsed">
    private ArrayList<PersonasSucursal> castFileToEntity(String[][] values) {
        ArrayList<PersonasSucursal> entities = new ArrayList<>();
        for (int x = 1; x < values.length; x++) {//x=1 is where data begin, x=0 fields name are defined
            PersonasSucursal entity = new PersonasSucursal();
            Personas person = new Personas();
            switch (values[x][0]) {
                case "CC"://TODO CREATE CONSTANST HERE
                    person.setTipoDocumento(new TiposDocumento(13));//TODO CREATE CONSTANST HERE
                    break;
                case "CE"://TODO CREATE CONSTANST HERE
                    person.setTipoDocumento(new TiposDocumento(1));//TODO CREATE CONSTANST HERE
                    break;
            }

            person.setNumeroDocumento(values[x][1]);
            person.setNombre1(values[x][2]);
            person.setNombre2(values[x][3]);
            person.setApellido1(values[x][4]);
            person.setApellido2(values[x][5]);

            if (values[x][6] != null) {
                switch (values[x][6]) {
                    case "M":
                        person.setSexo(true);
                        break;
                    case "F":
                        person.setSexo(false);
                        break;
                }
            }

            person.setRh(values[x][7]);

            if (values[x][8] != null) {
                DateFormat format = new SimpleDateFormat("dd-MM-yyyy");
                Date date;
                try {
                    date = format.parse(values[x][8]);
                    person.setFechaNacimiento(date);
                } catch (ParseException ex) {
                    System.out.println("Seracis cliente exception PersonaCliControl: castFileToModel fecha de nacimiento");
                }
            }

            person.setDireccion(values[x][9]);
            person.setTelefono(values[x][10]);
            person.setCelular(values[x][11]);
            person.setMail(values[x][12]);
            person.setPersonaContacto(values[x][13]);
            person.setTelPersonaContacto(values[x][14]);
            if (values[x][15] != null) {
                switch (values[x][15]) {
                    case "COLOMBIA"://TODO CREATE CONSTANT PROPERTY HERE
                        person.setPais(new Paises(1));//TODO ASSIGN REAL CASE HERE
                        break;
                }
            }
            if (values[x][16] != null) {
                switch (values[x][16]) {
                    case "BOGOTA"://TODO CREATE CONSTANT PROPERTY HERE
                        person.setDepartamento(new Departamentos(1));//TODO ASSIGN REAL CASE HERE
                        break;
                }
            }
            if (values[x][17] != null) {
                switch (values[x][17]) {
                    case "BOGOTA"://TODO CREATE CONSTANT PROPERTY HERE
                        person.setMunicipio(new Municipios(1));//TODO ASSIGN REAL CASE HERE
                        break;
                }
            }
            if (values[x][18] != null) {
                switch (values[x][18]) {
                    case "EPS CARGA"://TODO CREATE CONSTANT PROPERTY HERE
                        person.setEps(new Eps(1));//TODO ASSIGN REAL CASE HERE
                        break;
                }
            }
            if (values[x][19] != null) {
                switch (values[x][19]) {
                    case "ARL CARGA"://TODO CREATE CONSTANT PROPERTY HERE
                        person.setArl(new Arl(1));//TODO ASSIGN REAL CASE HERE
                        break;
                }
            }

            entity.setPersonas(person);
            entities.add(entity);
        }
        return entities;
    }
    //</editor-fold>

    //<editor-fold desc="loadFile" defaultstate="collapsed">
    /**
     * Return matriz with data storage in excel file
     *
     * @param uploadedFile
     * @return
     */
    private Result loadFile(UploadedFile uploadedFile) {
        String[][] tmp;//here even empty cells will be stored
        try {
            InputStream file = uploadedFile.getInputstream();
            XSSFWorkbook workbook = new XSSFWorkbook(file);
            XSSFSheet sheet = workbook.getSheetAt(0);
            String cellValue;
            // Iterate through each rows from first sheet
            Iterator<Row> rowIterator = sheet.rowIterator();
            //Initialize values
            int numberOfRows = sheet.getPhysicalNumberOfRows();
            tmp = new String[numberOfRows][numberOfColums];
            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                // For each row, iterate through each columns
                Iterator<Cell> cellIterator = row.cellIterator();
                int columsCount = 0;
                while (cellIterator.hasNext() && columsCount < numberOfColums) {//Only the number of colums defined in constants are taken in consideration
                    columsCount++;
                    cellValue = null;
                    Cell cell = cellIterator.next();
                    switch (cell.getCellType()) {

                        case Cell.CELL_TYPE_BOOLEAN:
                            cellValue = String.valueOf(cell.getBooleanCellValue());

                            break;
                        case Cell.CELL_TYPE_NUMERIC:
                            if (DateUtil.isCellDateFormatted(cell)) {
                                cellValue = new SimpleDateFormat("dd-MM-yyyy").format(cell.getDateCellValue());
                            } else {
                                cellValue = String.valueOf(cell.getNumericCellValue());
                                cellValue = cellValue.substring(0, cellValue.length() - 2);
                                cellValue = JsfUtil.formatNumber(cellValue);
                            }
                            break;
                        case Cell.CELL_TYPE_STRING:
                            cellValue = cell.getStringCellValue().trim();
                            break;
                    }
                    if (cellValue != null) {
                        tmp[cell.getRowIndex()][cell.getColumnIndex()] = cellValue;
                    }

                }
            }
            file.close();
            workbook.close();
            return new Result(tmp, 0);
        } catch (FileNotFoundException e) {
            System.out.println("Seracis cliente exception abstract controller handleFileUpload: error code 1");
            return new Result("", 1);
        } catch (IOException ex) {
            System.out.println("Seracis cliente exception abstract controller handleFileUpload: error code 2 " + ex);
            return new Result("", 2);
        } catch (Exception ex) {
            System.out.println("Seracis cliente exception abstract controller handleFileUpload: error code 3");
            return new Result("", 3);
        }
    }
    //</editor-fold>

    //<editor-fold desc="deleteEmptyData" defaultstate="collapsed">
    /**
     * Delete Empty data and first row (where name of file is defined)
     *
     * @param tmp String matrix where excel data is storage
     * @return String matrix without empty row and without first row
     */
    private String[][] deleteEmptyData(String[][] tmp) {
        int realRows = 0;
        for (int i = 0; i < tmp.length; i++) {
            String[] cell = tmp[i];
            int validRow = 0;
            for (int j = 0; j < cell.length; j++) {
                if (cell[j] != null || (cell[j] instanceof String && !"-".equals(cell[j]))) {
                    validRow++;//if at least one colum in the row has data its a valid row
                }
            }
            if (validRow != 0) {//if validRow != 0 means that at least one colum in the row has data
                realRows++;
            }
        }
        String[][] values = new String[realRows - 1][numberOfColums];//realRows-1 to avoid row where names are defined
        System.arraycopy(tmp, 1, values, 0, realRows - 1);//From 1 to avoid row where names are defined
        return values;
    }
    //</editor-fold>

    /**
     * Save from excel file
     *
     * @param array
     * @return errorCode
     */
    private int saveFile(ArrayList<PersonasSucursal> array) {
        arrayError = new ArrayList<>();
        personasController = JsfUtil.findBean("personasController");
        for (PersonasSucursal t : array) {
            selected = t;
            personasController.setSelected(selected.getPersonas());
            Result existPerson = personasController.findPersonByDocument();
            if (existPerson.errorCode == Constants.OK) {//Person already register in personas table
                Result existSpecificPerson = findSpecificPerson();
                if (existSpecificPerson.errorCode == Constants.OK) {//Person already register in personas sucursal table
                    updateFromFile((PersonasSucursal) existPerson.result);
                }
                personasController.updateFromFile(selected.getPersonas());
            }
            Result result = personasController.create();
            if (result.errorCode != Constants.OK) {
                PersonasSucursalModel model = new PersonasSucursalModel();
                model.setEntity(getSelected());
                model.setErrorObservation((String) result.result);
                arrayError.add(model);
            }
        }
        if (arrayError.isEmpty()) {
            return Constants.OK;
        } else {
            return Constants.VALIDATION_ERROR;//TODO MAKE ERROR CONSTANT HERE
        }
    }

    private void updateFromFile(PersonasSucursal person) {
        //TODO FINISH MMETHOD
    }

    public void selectBranchOfficeToDownload() {
        GeneralControl generalControl = JsfUtil.findBean("generalControl");
        if (generalControl.isShowBranchOffice()) {
            JsfUtil.showModal("dialogChooseBranchOffice");
        } else {
            downloadTemplate();
        }
    }

    /*public void showLoadDialog() {
        GeneralControl generalControl = JsfUtil.findBean("generalControl");
        if (generalControl.getSelectedBranchOffice() != null) {
            JsfUtil.showModal("dialogLoad");
            JsfUtil.hideModal("dialogChooseBranchOffice");
        }
    }*/

    public void downloadTemplate() {
        try {
            XSSFWorkbook workbook = new XSSFWorkbook(new FileInputStream("C:\\Program Files\\ACTIV\\Excel Template\\Plantilla de carga personas.xlsx"));

            AreasEmpresaController areasEmpresaController = JsfUtil.findBean("areasEmpresaController");
            List<AreasEmpresa> areas = areasEmpresaController.getItemsByBranchOffice();
            Sheet areasSheet = workbook.getSheetAt(10);//WE HAVE TO GET SHEET BECAUSE IF WE CREATE SHEET THE REFERENCE FROM DROP DOWN LIST WILL LOST
            for (int i = 1; i <= areas.size(); i++) {//Start in 1 because cell A1 in excel is sheet's name
                Row row = areasSheet.createRow(i);
                Cell cell = row.createCell(0);
                cell.setCellValue(areas.get(i-1).getDescripcion());
            }
            FileOutputStream out = new FileOutputStream("C:\\ACTIV\\Plantilla de carga personas.xlsx");
            workbook.write(out);
            workbook.close();
            JsfUtil.addSuccessMessage("SE HA DESCARGADO LA PLANTILLA DE PERSONAS EN LA DIRECCION C:\\ACTIV\\Plantilla de carga personas.xlsx");//TODO CREATE PROPERTY HERE
        } catch (FileNotFoundException ex) {
            JsfUtil.addErrorMessage("NO SE HA ENCONTRADO LA PLANTILLA POR FAVOR CONTACTE AL SERVICIO TÉCNICO");//TODO CREATE PROPERTY HERE
        } catch (IOException ex) {
            JsfUtil.addErrorMessage("NO SE HA PODIDO DESCARGAR LA PLANTILLA, INTENTE DE NUEVO MÁS TARDE");//TODO CREATE PROPERTY HERE
        }
    }
}
