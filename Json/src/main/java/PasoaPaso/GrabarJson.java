/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PasoaPaso;

//import archivosjson.ArchivosJSON;
//import archivosjson.Persona;
//import com.google.gson.Gson;
//import com.google.gson.JsonArray;
//import com.google.gson.JsonElement;
//import com.google.gson.JsonObject;
//import com.google.gson.JsonParser;
//import java.io.BufferedReader;
//import java.io.BufferedWriter;
//import java.io.FileNotFoundException;
//import java.io.FileReader;
//import java.io.FileWriter;
//import java.io.IOException;
//import java.util.Properties;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//
///**
// *
// * @author margot.lopez
// */
//public class GrabarJson {
//
//    public static void main(String[] args) throws IOException {
//      
//        Gson gson = new Gson(); 
//        String fileJson2 = "C:\\Users\\Usuario\\Documents\\NetBeansProjects\\ArchivosJSON\\src\\Datos\\datosJson1.json";
//           
//     
//        //  de objeto a JSON
//        Persona p3 = new Persona();
//        Persona p2 = new Persona("Gloria", "García",20);
//        Persona p1 = new Persona("Luis", "Davila", 39);
//        
//        gson.toJson(p1);             
//        
//        
//        // lea el archivo  o
//        String cabeza = "Los pedidos del 9 de febrero";
//        String infomia1 = gson.toJson(p1);         
//
//        //Guardar
//        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileJson2))) {
//            bw.write(cabeza);
//            bw.newLine();
//            bw.write(infomia1);
//            System.out.println("Archivo creado y primera persona registrada");
//        } catch (IOException ex) {
//            Logger.getLogger(ArchivosJSON.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        // adicionar
//        p3.setNombre("JUAN");
//        p3.setApellidos("Ruiz");
//        p3.setEdad(60);
//
//        infomia1 = gson.toJson(p3);
//       try (BufferedWriter bw2 = new BufferedWriter(new FileWriter(fileJson2,true))) {
//            bw2.append(",");
//            bw2.newLine();
//            bw2.write(infomia1);
//            System.out.println("Archivo Abierto y Segunda persona registrada");
//        } catch (IOException ex) {
//            Logger.getLogger(ArchivosJSON.class.getName()).log(Level.SEVERE, null, ex);
//        }
//   }
//}
