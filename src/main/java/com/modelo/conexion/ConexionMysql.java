/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.modelo.conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

/**
 *
 * @author rbreuer
 */
public class ConexionMysql {
    private Connection conn;

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
			
                    String sql1 = "drop  table if exists minio_pdf_copy";
		    stmt.executeUpdate(sql1);
		          
		    String sql2 ="create table if not exists minio_pdf_copy select * from minio_pdf";
		    stmt.executeUpdate(sql2);
		           
		            
		    String sql3 ="update minio_pdf_copy set key_name= replace(key_name,'pdf/',''); ";
		    stmt.executeUpdate(sql3);getClass();
		    
		    conn.close();
				
		} else {
                     System.out.println("Conexion fallida!");
		}				
		}catch(Exception e){
                    e.printStackTrace();
		}
		return this;
	}
}
