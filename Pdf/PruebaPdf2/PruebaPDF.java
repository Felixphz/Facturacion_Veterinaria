/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PruebaPdf2;


import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;

import java.io.FileOutputStream;
import javax.swing.JTable;
/**
 *
 * @author gabriel
 */
public class PruebaPDF {
    public static void crearBlog(String titulo, String texto){
        try{
        Document doc = new Document();
        PdfWriter.getInstance(doc, new FileOutputStream("post.pdf"));
        doc.open();
        
        Font titleFont = FontFactory.getFont(BaseFont.TIMES_BOLD, 21, BaseColor.RED);
        Font bodyFont = FontFactory.getFont(BaseFont.HELVETICA, 12, BaseColor.BLACK);
        
        Paragraph title = new Paragraph(titulo, titleFont);
        Paragraph body = new Paragraph(texto, bodyFont);
        
        doc.add(title);
        doc.add(body);
        
        doc.close();
        
        
        }catch(DocumentException | java.io.FileNotFoundException e){
            e.printStackTrace();
        }
    }
    
    public static void crearTabla(JTable swTable){
                try{
        Document doc = new Document();
        PdfWriter.getInstance(doc, new FileOutputStream("tabla.pdf"));
        doc.open();
        PdfPTable tabla = new PdfPTable(swTable.getColumnCount());
        for(int i = 0; i < swTable.getColumnCount(); i++)
        {
            tabla.addCell(swTable.getColumnName(i));
        }
        for(int x = 0; x < swTable.getRowCount(); x++)
        {
            for(int y = 0; y < swTable.getColumnCount(); y++)
            {
                String valor = (swTable.getValueAt(x, y) != null) ? swTable.getValueAt(x, y).toString() : "";
                tabla.addCell(valor);
            }
        }
        doc.add(tabla);
        doc.close();

        
        }catch(DocumentException | java.io.FileNotFoundException e){
            e.printStackTrace();
        }
    }

}
