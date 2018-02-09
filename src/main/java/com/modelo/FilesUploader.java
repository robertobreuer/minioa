/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.modelo;

import com.modelo.conexion.ConexionMinio;
import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author rbreuer
 */
public class FilesUploader {

        private File[] archivoArray;
        private String BUCKET="pdf";
    
   public void cargarArchivosEnBucket(String nombreBucket){
           try{
            JFileChooser jfc = new JFileChooser();
            FileFilter filterPDF = new FileNameExtensionFilter("PDF file", "pdf", "pdf");
            FileFilter filterJPG = new FileNameExtensionFilter("Image file", "jpg");
            
               switch (BUCKET) {
                   case "pdf":
                       jfc.setFileFilter(filterPDF);
                       break;
                   case "jpg":	
                       jfc.setFileFilter(filterJPG);
                       break;
                   default:
                       break;
               }
             
            jfc.setMultiSelectionEnabled(true);
            jfc.addChoosableFileFilter(filterJPG);
        
            int opcion = jfc.showSaveDialog(jfc);
               
			    if(opcion==JFileChooser.APPROVE_OPTION ){
			    	 
			     archivoArray = jfc.getSelectedFiles();    
			    
			    	 //recorrer array archivoArray
			     	for(int i =0;i<archivoArray.length;i++){
			    	 		
			    	 	//String rutaArray =  archivoArray[i].getName();
			    	 	//System.out.println("\n "+archivoArray[i]);
			    	 	//System.out.println("\n "+rutaArray);
			    	 	//File archivo = fc.getSelectedFile();
			    	 	//minioClient.putObject(BUCKET, fc.getName() , ruta);
			     		ConexionMinio.minioClient.putObject(nombreBucket, archivoArray[i].getName(), archivoArray[i].getPath());
			    	 		
			     		System.out.println(archivoArray[i].getPath()+" subido correctamente a bucket: "+nombreBucket);
			     				     		//DesktopNotify.showDesktopMessage(archivoArray[i].getName(),"Subido correctamente a bucket "+nombreBucket,  DesktopNotify.SUCCESS ,3600);			     		
			     			
			    	 
			     		JOptionPane.showMessageDialog(null, "Hecho! "+
			     				"\n"+"Archivo: "+archivoArray[i].getName()+
			     				"\n"+"Bucket: "+nombreBucket
			     				);
			     	
			     	}	 	 
			    }else if (opcion==JFileChooser.CANCEL_OPTION){
                              
			    } 
           }catch(Exception e){
           } 
        }
    }
    
    
    
    
    

