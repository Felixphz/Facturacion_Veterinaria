/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PasoaPaso;

import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
/**
 *
 * @author margot.lopez
 */
//Adefecio
public class UsoLibreria {
    
    public static void main(String[] args) throws IOException {
        // Crear un objeto tipo Gson
        Gson gson = new Gson();  //  la funcionalidad de la libreria
        System.out.println("Libreria usandose");

       // leer el archivo,  Direcciòn o path. Rutas absolutas
       //Adefecio 
       String fileJson = "C:\\Users\\Juan Felipe\\Documents\\NetBeansProjects\\Json\\src\\main\\java\\Datos\\datosJson.json";                 
       String infomia = "";       
        try (BufferedReader br = new BufferedReader(new FileReader(fileJson))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                System.out.println("Recorrido archivo línea a línea");
                infomia += linea;  //  queda toda la estructura del json
                System.out.println("Dentro del mientras "+ linea);                
            }
            System.out.println("Toda la inforamción "+infomia);

        } catch (FileNotFoundException ex) {
            System.out.println(ex.getMessage());
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }     
   }
}

