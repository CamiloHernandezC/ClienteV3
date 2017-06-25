/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import java.io.File;
import java.io.Serializable;
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
            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream("c:/temp/" + String.valueOf(remission.getIdRemision()) + ".pdf"));
            document.open();
            //Todo se tiene que agregar aquí porque si se crean métodos a los que se le pase document no deja hacer el build
            document.addTitle("REMISION" + remission.getIdRemision());
            document.addAuthor("Mailo Innovation");
            document.addCreator("Mailo Innovation");

            Paragraph preface = new Paragraph();
            // We add one empty line
            //addEmptyLine(preface, 1);
            // Lets write a big header
            preface.add(new Paragraph(BundleUtils.getBundleProperty("Remission") + " " + remission.getIdRemision(), titleFont));
            preface.setSpacingAfter(20);
            preface.setAlignment(1); // Center
            ////addEmptyLine(preface, 3);

            document.add(preface);

            Paragraph p = new Paragraph();
            p.add(new Phrase("Sucursal: ", boldFont));
            p.add(new Phrase(remission.getSucursal().getNombre(), normalFont));
            document.add(p);

            p = new Paragraph();
            p.add(new Phrase("Almacen: ", boldFont));
            p.add(new Phrase(remission.getAlmacen().getDescripcion(), normalFont));
            document.add(p);

            p = new Paragraph();
            p.add(new Phrase("Almacenista: ", boldFont));
            p.add(new Phrase(remission.getAlmacenista().getNombre1() + " " + remission.getAlmacenista().getApellido1(), normalFont));
            document.add(p);

            p = new Paragraph();
            p.add(new Phrase("Tipo de movimiento: ", boldFont));
            p.add(new Phrase(remission.movementType(), normalFont));
            document.add(p);

            p = new Paragraph();//not need of null verification because only finished remissions will be created
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm dd/MM/yyyy");
            p.add(new Phrase("Fecha: ", boldFont));
            p.add(new Phrase(sdf.format(remission.getFechaFin()), normalFont));

            //Last line
            //addEmptyLine(p, 3);
            document.add(p);

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
