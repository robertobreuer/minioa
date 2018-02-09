/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.modelo.conexion;

import io.minio.MinioClient;

/**
 *
 * @author rbreuer
 */
public class ConexionMinio {
    
    private final String ACCESS_KEY  =   "T0A1K0W59MUNQLBK1L78";
    private final String SECRET_KEY  =   "xAn4aOaLjQLt96T84iyWon2gkq+aeRmbw3/M31jj";    
    private final String URL_SERVER  =    "http://localhost:9000";
    
    public static  MinioClient minioClient;
    
    public String getAccessKey(){
        return ACCESS_KEY;
    }
    
    public String getSecretKey(){
        return SECRET_KEY;
    }

    public String getUrlServer(){
        return URL_SERVER;
    }
}
