/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.modelo.conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author rbreuer
 */
public class ConexionMysql {
    private Connection conn;
    public static ResultSet rs;
	public Connection getConn() {
		return conn;
	}

	public void setConn(Connection conn) {
		this.conn = conn;
	}
	
	public ConexionMysql conectar(){
		
		try{			
                    Class.forName("com.mysql.jdbc.Driver");
                    String urlBd="jdbc:mysql://localhost/miniodb?user=root&password=root";
                    setConn(DriverManager.getConnection(urlBd)); 
			
                    Statement stmt = conn.createStatement();
                    if (conn != null) {				
				
                    System.out.println("Conexion exitosa!");
			
                    String sql1 = "drop table if exists minio_pdf_copy";
		    stmt.executeUpdate(sql1);
		          
		    String sql2 ="create table if not exists minio_pdf_copy select idminio_pdf, key_name, fecha_subida from minio_pdf";
		    stmt.executeUpdate(sql2);
		           		            
		    String sql3 ="update minio_pdf_copy set key_name= replace(key_name,'pdf/',''); ";
		    stmt.executeUpdate(sql3);
                    getClass();
		    
		    conn.close();
				
		} else {
                     System.out.println("Conexion fallida!");
		}				
		}catch(Exception e){
                    e.printStackTrace();
		}
		return this;
	
       
        
        }
        
         public void selectTableMinio_pdf_copy(String nombreBucket) throws SQLException{
         
             DriverManager.registerDriver(new com.mysql.jdbc.Driver());
             Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/miniodb","root","root");
         
             Statement s = conexion.createStatement();
             rs = s.executeQuery("select * from minio_"+nombreBucket+"_copy order by key_name asc");
        }
         
         public void createTableBucket(String nombreBucketNuevo)throws SQLException{
         
             
             DriverManager.registerDriver(new com.mysql.jdbc.Driver());
             Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/miniodb","root","root");
         
             Statement s = conexion.createStatement();
             rs = s.executeQuery("create table if not exists 'minio_"+nombreBucketNuevo+"'"+" ( idminio_"+nombreBucketNuevo+"integer(11) auto_increment primary key, key_name varchar(2000) not null, value json not null, fecha_subida datetime not null");
             
         }
        
}
