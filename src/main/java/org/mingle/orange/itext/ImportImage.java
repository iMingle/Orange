/**
 * @author Mingle 2014年6月25日
 * @author mingle
 */
package org.mingle.orange.itext;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

/**
 * 
 * @author Mingle
 */
public class ImportImage {

    /**
     * @param args
     */
    public static void main(String[] args) {
        Document document = new Document(PageSize.A4, 50, 50, 50, 50);
        try {
            try {
                PdfWriter.getInstance(document, new FileOutputStream(new File(ImportImage.class.getResource("/documents/roadster.pdf").toURI())));
            } catch (URISyntaxException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            document.addAuthor("JinMinglei");
            document.open();
            document.add(new Paragraph("Super Car", FontFactory.getFont(FontFactory.HELVETICA, 18, Font.BOLDITALIC, new BaseColor(0, 0, 0))));
            Image image = Image.getInstance(ImportImage.class.getResource("/images/roadster.jpg"));
            document.add(image);
            document.add(image);
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (DocumentException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            if (document != null) {
                document.close();
            }
        }
        
    }

}
