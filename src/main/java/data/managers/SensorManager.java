/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data.managers;

import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.result.DeleteResult;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import models.Sensor;

/**
 *
 * @author luyisimiger
 */
public class SensorManager {
    
    private static String collection_name = "sensores";    
    private MongoDatabase conexion;

    public SensorManager(MongoDatabase conexion) {
        this.conexion = conexion;
    }
    
    public void create(String portcom, String name, int min, int max) {
        
        long estampa = System.currentTimeMillis();
        
        BasicDBObject doc = new BasicDBObject();
        doc.append("_id", estampa);
        doc.append("name", name);
        doc.append("portcom", portcom);
        doc.append("min", min);
        doc.append("max", max);
                
        MongoCollection<BasicDBObject> coll = conexion.getCollection(collection_name, BasicDBObject.class);
        coll.insertOne(doc);
    }
    
    public Sensor get(long sensorid) {
        
        MongoCollection<BasicDBObject> coll = conexion.getCollection(collection_name, BasicDBObject.class);
        List<BasicDBObject> foundDocument = coll.find(Filters.eq("_id", sensorid)).into(new ArrayList<BasicDBObject>());
        
        Sensor sensor = null;
        Iterator<BasicDBObject> iterator = foundDocument.iterator();
        
        if ( iterator.hasNext() ) {
            BasicDBObject next = iterator.next();
            sensor = new Sensor(next);
        }
        
        return sensor;
    }
    
    public MongoDatabase getConexion() {
        return this.conexion;
    }
    
    public List<Sensor> objects() {
        
        MongoCollection<BasicDBObject> coll = conexion.getCollection(collection_name, BasicDBObject.class);
        List<BasicDBObject> foundDocument = coll.find().into(new ArrayList<BasicDBObject>());
        
        List<Sensor> objects = new ArrayList<Sensor>();
        
        for (Iterator<BasicDBObject> iterator = foundDocument.iterator(); iterator.hasNext();) {
            BasicDBObject next = iterator.next();
            Sensor sensor = new Sensor(next);
            objects.add(sensor);            
        }
        
        return objects;
    }
    
    public long delete(long sensorid) {
        
        MongoCollection<BasicDBObject> coll = conexion.getCollection(collection_name, BasicDBObject.class);
        DeleteResult result = coll.deleteOne(Filters.eq("_id", sensorid));
        return result.getDeletedCount();
    }
}
