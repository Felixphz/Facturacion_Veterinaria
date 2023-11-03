/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PasoaPaso;

import model.Persona;
import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

/**
 *
 * @author margot.lopez
 */
public class LecturaJson {
    public static void main(String[] args) throws IOException {   
        Gson gson = new Gson();     
        String fileJson = "C:\\Users\\Juan Felipe\\Documents\\NetBeansProjects\\Json\\src\\main\\java\\Datos\\datosJson.json";        
        String infomia = "";
        
        try (BufferedReader br = new BufferedReader(new FileReader(fileJson))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                infomia += linea;  
            }
        } catch (FileNotFoundException ex) {
            System.out.println(ex.getMessage());
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        //  propiedades del archivo Json
        // estracciòn de la informaciòn que String  bajo  json
        Properties properties = gson.fromJson(infomia, Properties.class);
        System.out.println(properties.get("nombre"));
        System.out.println(properties.get("apellidos"));
        System.out.println(properties.get("edad"));
                         
        //  gestión del Json al bean
        Persona persona = gson.fromJson(infomia, Persona.class);
        System.out.println(persona);        
        
   }
}

