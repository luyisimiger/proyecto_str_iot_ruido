/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data.managers;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.result.DeleteResult;
import data.Conexion;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import models.Sensor;

/**
 *
 * @author luyisimiger
 */
public class MeditionManager {

    private static String collection_name = "sensores";

    private MongoDatabase conexion;
    private Sensor sensor;

    public MeditionManager(MongoDatabase conexion, Sensor sensor) {
        this.conexion = conexion;
        this.sensor = sensor;
    }

    public void create(double valor) {

        MongoCollection<BasicDBObject> coll = conexion.getCollection(collection_name, BasicDBObject.class);
        
        long estampa = System.currentTimeMillis();
        Date fechahora = new Date(estampa);
        
        BasicDBObject medition = new BasicDBObject();
        medition.append("_id", estampa);
        medition.append("fechahora", fechahora);
        medition.append("valor", valor);

        BasicDBObject update = new BasicDBObject();
        update.put("$push", new BasicDBObject("meditions", medition));

        BasicDBObject finddoc = new BasicDBObject("_id", sensor.getId());
        coll.updateOne(finddoc, update);
    }
/*
    public Sensor get(long sensorid) {

        MongoCollection<BasicDBObject> coll = conexion.getCollection(collection_name, BasicDBObject.class);
        List<BasicDBObject> foundDocument = coll.find(Filters.eq("_id", sensorid)).into(new ArrayList<BasicDBObject>());

        Sensor sensor = null;
        Iterator<BasicDBObject> iterator = foundDocument.iterator();

        if (iterator.hasNext()) {
            BasicDBObject next = iterator.next();
            sensor = new Sensor(next);
        }

        return sensor;
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
    */
}
