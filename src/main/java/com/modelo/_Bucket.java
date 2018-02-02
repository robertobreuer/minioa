/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.modelo;

import com.modelo.conexion.ConexionMinio;
import javax.swing.JOptionPane;

/**
 *5626165
 * @author rbreuer
 */
public class _Bucket {
    
    public void crearBucket(String nombreBucketNuevo){            
        try {
            // crea bucket si no existe
            boolean found = ConexionMinio.minioClient.bucketExists(nombreBucketNuevo);
            if (found) {
                JOptionPane.showMessageDialog(null, "Un bucket ya existe con el nombre "+nombreBucketNuevo);
                System.out.println(nombreBucketNuevo+" un bucket ya existe con ese nombre");
            } else {
                // Create bucket 'my-bucketname'.
                ConexionMinio.minioClient.makeBucket(nombreBucketNuevo);
                JOptionPane.showMessageDialog(null, "Bucket "+nombreBucketNuevo+ "  creado correctamente!");
            }
        } catch (Exception e) {
            System.out.println("print error: " + e);
        }
    }
    
    public void mostrarListaBuckets(){
        
    }
    
    
}
