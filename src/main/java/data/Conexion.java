/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;

/**
 *
 * @author luyisimiger
 */
public class Conexion {
    
    private static String DBNAME = "aeropuerto";
    private static String HOSTNAME = "localhost";
    private static int PORT = 27017;
    
    public static MongoDatabase getConexion() {
        MongoClient mongoClient = new MongoClient(HOSTNAME , PORT);
        MongoDatabase db = mongoClient.getDatabase(DBNAME);
        return db; 
    }
    
}
