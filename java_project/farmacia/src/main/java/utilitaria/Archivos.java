/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utilitaria;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author Milton Barrera
 */
public class Archivos {
    public static String leer(String ruta){
        File archivo = new File(ruta); // Reemplaza "ruta_del_archivo" con la ubicación del archivo que deseas leer
        String texto = "";
        try {
            FileReader lector = new FileReader(archivo);
            BufferedReader buffer = new BufferedReader(lector);
            String linea;
            while ((linea = buffer.readLine()) != null) {
                texto += linea;
            }
            buffer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        return texto;
    }
}
