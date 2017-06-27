/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import java.io.File;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import net.sourceforge.barbecue.BarcodeFactory;
import net.sourceforge.barbecue.BarcodeImageHandler;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDDocumentInformation;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

/**
 *
 * @author Kmilo
 */
@Named("remisionesController")
@SessionScoped
public class Remisiones implements Serializable {

    /*private Font titleFont = new Font(Font.TIMES_ROMAN, 22, Font.BOLD);
    private Font boldFont = new Font(Font.TIMES_ROMAN, 16, Font.BOLD);
    private Font normalFont = new Font(Font.TIMES_ROMAN, 16, Font.NORMAL);*/
    public Remisiones() {
    }

    public void seeRemission(Entities.Remisiones remission) {
        try {
            //Creating PDF document object 
            PDDocument document = new PDDocument();

            //Creating a blank page 
            PDPage page = new PDPage();

            //Adding the blank page to the document
            document.addPage(page);

            //Creating the PDDocumentInformation object 
            PDDocumentInformation pdd = document.getDocumentInformation();

            //Setting the author of the document
            pdd.setAuthor("Tutorialspoint");

            // Setting the title of the document
            pdd.setTitle("Sample document");

            //Setting the creator of the document 
            pdd.setCreator("PDF Examples");

            //Setting the subject of the document 
            pdd.setSubject("Example document");

            //Setting the created date of the document
            Calendar c = Calendar.getInstance();
            c.setTime(new Date());
            pdd.setCreationDate(c);

            PDPageContentStream contentStream = new PDPageContentStream(document, page);

            PDFont font = PDType1Font.HELVETICA_BOLD; // Or whatever font you want.

            int titleSize = 22; // Or whatever font size you want.
            String title = "REMISION " + remission.getIdRemision();
            float titleWidth = font.getStringWidth(title) / 1000 * titleSize;
            float titleHeight = font.getFontDescriptor().getFontBoundingBox().getHeight() / 1000 * titleSize;

            //Begin the Content stream 
            contentStream.beginText();

            //Setting the font to the Content stream  
            contentStream.setFont(PDType1Font.TIMES_BOLD, titleSize);

            //Setting the position for the line 
            contentStream.newLineAtOffset((page.getMediaBox().getWidth() - titleWidth) / 2, page.getMediaBox().getHeight() - 10 - titleHeight);

            //Adding text in the form of string 
            contentStream.showText(title);
            
            contentStream.endText();
            
            contentStream.beginText();

            int normalSize = 16;// Or whatever font size you want.
            

            //Setting the position for the line 
            int marginLeft = 25;
            int startTop = 700;
            contentStream.newLineAtOffset(marginLeft, startTop);
            
            //Setting the font to the Content stream  
            contentStream.setFont(PDType1Font.TIMES_BOLD, normalSize);
            //Setting the leading
            contentStream.setLeading(normalSize+3);//This is needed for new line
            
            String text = "Sucursal: ";
            //Adding text in the form of string 
            contentStream.showText(text);
            
            contentStream.setFont(PDType1Font.TIMES_ROMAN, normalSize);
            text = remission.getSucursal().getNombre();
            contentStream.showText(text);
            
            contentStream.newLine();
            
            contentStream.setFont(PDType1Font.TIMES_BOLD, normalSize);
            text = "Almacen: ";
            //Adding text in the form of string 
            contentStream.showText(text);
            
            contentStream.setFont(PDType1Font.TIMES_ROMAN, normalSize);
            text = remission.getAlmacen().getDescripcion();
            contentStream.showText(text);
            
            contentStream.newLine();
            
            contentStream.setFont(PDType1Font.TIMES_BOLD, normalSize);
            text = "Almacenista: ";
            //Adding text in the form of string 
            contentStream.showText(text);
            
            contentStream.setFont(PDType1Font.TIMES_ROMAN, normalSize);
            text = remission.getAlmacenista().getNombre1() + " " + remission.getAlmacenista().getApellido1();
            contentStream.showText(text);
            
            contentStream.newLine();
            
            contentStream.setFont(PDType1Font.TIMES_BOLD, normalSize);
            text = "Tipo de movimiento: ";
            //Adding text in the form of string 
            contentStream.showText(text);
            
            contentStream.setFont(PDType1Font.TIMES_ROMAN, normalSize);
            text = remission.movementType();
            contentStream.showText(text);
            
            contentStream.newLine();
            
            contentStream.setFont(PDType1Font.TIMES_BOLD, normalSize);
            text = "Fecha: ";
            //Adding text in the form of string 
            contentStream.showText(text);
            
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm dd/MM/yyyy");
            contentStream.setFont(PDType1Font.TIMES_ROMAN, normalSize);
            contentStream.showText(sdf.format(remission.getFechaFin()));
            
            contentStream.newLine();
            
            //Ending the content stream
            contentStream.endText();

            //Closing the content stream
            contentStream.close();
            //Saving the document
            document.save("c:/temp/" + String.valueOf(remission.getIdRemision()) + ".pdf");

            //Closing the document  
            document.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
/*
            PdfPTable table = new PdfPTable(2);//Number of columns

            //Table tittle
            table.addCell(new Phrase("Material", boldFont));
            table.addCell(new Phrase("Cantidad", boldFont));

            for (Cardex material : remission.getCardexList()) {
                table.addCell(material.getMaterialesSucursal().getMateriales().getDescripcion());
                table.addCell(String.valueOf(material.getCantida()));
            }

            table.setKeepTogether(true);
            table.setSpacingAfter(30);

            document.add(table);

            File barcodeFile = new File("c:/temp/dynamicbarcode.png");
            BarcodeImageHandler.savePNG(BarcodeFactory.createCode128(String.valueOf(remission.getIdRemision())), barcodeFile);
            Image barcode = Image.getInstance("c:/temp/dynamicbarcode.png");
            barcode.setAlignment(Element.ALIGN_RIGHT);
            //document.add(barcode);
            //DefaultStreamedContent barcode = new DefaultStreamedContent(new FileInputStream(barcodeFile), remission.getIdRemision()+".png");
            //document.close();
         */
    }

    /*private void //addEmptyLine(Paragraph paragraph, int number) {
        for (int i = 0; i < number; i++) {
            paragraph.add(new Paragraph(" "));
        }
    }*/
}
