/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import Model.Product;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import Control.BDcontrol;
import DataBase.OperationsBD;
import Model.Customer;
import java.util.List;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.swing.JTable;

/**
 *
 * @author gabriel
 */
public class GeneratePdf {

    public static void genPDF(int numFact, String datee, int customerId, List<Product> product, List<Integer> listAmount) throws IOException {
        try {
            BDcontrol BD = new OperationsBD();
            int idCustomer = BD.getIdCustomerByidentification(customerId);
            Customer customer =  BD.getPersonById(idCustomer);

            
            float subtotal = OperationBills.calculateSubtotal(product, listAmount);
            float iva = OperationBills.calculateIVA(subtotal, (float) 0.19);
            float total = OperationBills.calculateTotal(subtotal, iva);
            String id, name, amount, price, subtotalP;
            
            Document doc = new Document();       
            PdfWriter writer = PdfWriter.getInstance(doc, new FileOutputStream("src\\main\\java\\Bill\\Factura#" + numFact + ".pdf"));
            doc.open();
     
            Font titleFont = FontFactory.getFont(BaseFont.TIMES_BOLD, 37, BaseColor.BLACK);
            Font specialFont = FontFactory.getFont(BaseFont.HELVETICA_BOLD, 14, BaseColor.WHITE);
            Font subtitleFont = FontFactory.getFont(BaseFont.TIMES_BOLD, 17, BaseColor.BLACK);
            Font subsubtitleFont = FontFactory.getFont(BaseFont.TIMES_BOLD, 15, BaseColor.BLACK);
            Font bodyFont = FontFactory.getFont(BaseFont.TIMES_ROMAN, 12, BaseColor.BLACK);
            Font bodyFont2 = FontFactory.getFont(BaseFont.TIMES_ROMAN, 15, new BaseColor(97, 92, 230));
            Font bodyFont3 = FontFactory.getFont(BaseFont.TIMES_ROMAN, 12, new BaseColor(92, 156, 230));
            Font logofont = FontFactory.getFont(BaseFont.TIMES_BOLD, 15, BaseColor.WHITE);
            Font nitfont = FontFactory.getFont(BaseFont.HELVETICA, 11, BaseColor.WHITE);
            PdfPTable tabla = new PdfPTable(5);
            

            Paragraph title = new Paragraph("FACTURA #" + numFact, titleFont);
            Phrase date = new Phrase(datee, specialFont);
            Paragraph space = new Paragraph(" ");
            Paragraph client = new Paragraph("CLIENTE", subtitleFont);
            Phrase logo = new Phrase("LA ESPERANZA", logofont);
            Phrase nit = new Phrase("Nit: 123456789", nitfont);
            Paragraph clientName = new Paragraph("Número de documento: " +  customerId , bodyFont);
            Paragraph clientEmail = new Paragraph("Nombre: "  + customer.getName() + " " + customer.getLastName(), bodyFont);
            Paragraph clientCel = new Paragraph("Número de celular: " + customer.getPhone(), bodyFont);
            Paragraph subT = new Paragraph("                                                                                             SUBTOTAL :             $" + subtotal, bodyFont);
            Paragraph taxes = new Paragraph("                                                                                             IVA (19%) :                $" + iva, bodyFont);
            Paragraph tot = new Paragraph("                                                                                       TOTAL : $" + total, bodyFont2);
            Paragraph detail = new Paragraph("DETALLES DE PAGO:", subsubtitleFont);
            Paragraph detail2 = new Paragraph("Ensigna Web", bodyFont);
            Paragraph detail3 = new Paragraph("David", bodyFont);
            Paragraph detail4 = new Paragraph("Número de cuenta: 0123 4567 8901", bodyFont);
            Paragraph tyc = new Paragraph("TÉRMINOS Y CONDICIONES:", subsubtitleFont);
            Paragraph tyc2 = new Paragraph("\u2022 No se aceptan devoluciones de productos una vez que se hayan usado.", bodyFont);
            Paragraph tyc3 = new Paragraph("\u2022 Los servicios proporcionados por el centro veterinario se cobran de la siguiente manera: 50% antes del "
                    + "procedimiento y 50% después del mismo.", bodyFont);
            Paragraph contact = new Paragraph("Tel: (55) 1234-5678          c.veterinarioLaEsperanza@correo.com          http://www.centvetlaesperanza.com/", bodyFont3);
            
            
            PdfContentByte cb = writer.getDirectContent();
            cb.setColorFill(new BaseColor(92, 225, 230));
            cb.circle(535, 775, 90);
            cb.ellipse(38, 745, 158, 720);
            cb.fill();
            cb.setColorFill(BaseColor.BLACK);
            Image img = Image.getInstance("src\\main\\java\\img\\logo.png");
            img.setAbsolutePosition(465, 730);
            img.scaleAbsolute(110, 70);
            cb.addImage(img);
            ColumnText.showTextAligned(cb, Element.ALIGN_CENTER, logo, 525, 805, 0);
            ColumnText.showTextAligned(cb, Element.ALIGN_CENTER, nit, 525, 715, 0);
            ColumnText.showTextAligned(cb, Element.ALIGN_LEFT, date, 65, 728, 0);
            cb.setColorStroke(new BaseColor(92, 225, 230));
            cb.setLineWidth(3);
            cb.moveTo(38, 570);
            cb.lineTo(560, 570);
            
            cb.stroke();
            
            tabla.addCell("Id");
            tabla.addCell("Descripción");
            tabla.addCell("Cantidad");
            tabla.addCell("Precio");
            tabla.addCell("Subtotal");
            
            for (int x = 0; x < listAmount.size(); x++) {
                id = String.valueOf(product.get(x).getIdProduct());
                name = product.get(x).getName();
                amount = String.valueOf(listAmount.get(x));
                price =  String.valueOf(product.get(x).getPrice());
                subtotalP = String.valueOf(OperationBills.calculateSubtotalP(product.get(x).getPrice(), listAmount.get(x)));
                tabla.addCell(id);
                tabla.addCell(name);
                tabla.addCell(amount);
                tabla.addCell(price);
                tabla.addCell(subtotalP);
            }

            doc.add(title);
            doc.add(space);
            doc.add(space);
            doc.add(space);
            doc.add(space);
            doc.add(client);
            doc.add(clientName);
            doc.add(clientEmail);
            doc.add(clientCel);
            doc.add(space);
            doc.add(space);
            doc.add(space);
            doc.add(tabla);
            doc.add(space);
            doc.add(subT);
            doc.add(taxes);
            doc.add(tot);
            doc.add(space);
            doc.add(detail);
            doc.add(detail2);
            doc.add(detail3);
            doc.add(detail4);
            doc.add(space);
            doc.add(tyc);
            doc.add(tyc2);
            doc.add(tyc3);
            doc.add(space);
            doc.add(contact);
            doc.add(img);
            doc.close();

        } catch (DocumentException | java.io.FileNotFoundException e) {
            e.printStackTrace();
        }
        
    }
}
