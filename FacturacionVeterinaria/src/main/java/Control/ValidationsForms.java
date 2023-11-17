/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Control;

/**
 *
 * @author argen
 */


public class ValidationsForms {
    
    // el método se encarga de verificar que el usuario si haya ingresado
    // algun valor en los JTextField
    
    public static boolean isCorrect(java.awt.TextField... texts){
        boolean confirmation = false;
        for(java.awt.TextField text:texts){
            confirmation= text.getText().isEmpty();
            if (confirmation){
                return confirmation;
            }
        }
        return confirmation;
    }
     
    public static boolean isEmpty(String text){
        text.replaceAll(" ", "");
        
        if (text.length() == 0) {
            return true;
        }else{
            return false;
        }
    }
}
