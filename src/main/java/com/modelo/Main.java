/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.modelo;

import com.modelo.conexion.ConexionMysql;
import com.vista.MainFrame;
import io.minio.errors.ErrorResponseException;
import io.minio.errors.InsufficientDataException;
import io.minio.errors.InternalException;
import io.minio.errors.InvalidBucketNameException;
import io.minio.errors.NoResponseException;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import org.xmlpull.v1.XmlPullParserException;

/**
 *
 * @author rbreuer
 */
public class Main {
    public static void main (String[]args) throws InvalidBucketNameException, NoSuchAlgorithmException, InsufficientDataException, IOException, InvalidKeyException, NoResponseException, XmlPullParserException, ErrorResponseException, InternalException{
//            
//        ConexionMinio.ACCESS_KEY= JOptionPane.showInputDialog(null,"INGRESE ACCESS KEY");
//        ConexionMinio.SECRET_KEY= JOptionPane.showInputDialog(null,"INGRESE SECRET KEY");
   
       
        MainFrame mf;
        mf = new MainFrame();

        
    }
}
