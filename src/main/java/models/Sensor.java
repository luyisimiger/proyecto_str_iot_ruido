/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import com.mongodb.BasicDBObject;

/**
 *
 * @author luyisimiger
 */
public class Sensor {
    
    private BasicDBObject document;
    
    public Sensor(BasicDBObject doc) {
        document = doc;        
    }
    
    public BasicDBObject getDocument() {
        return document;
    }

    public long getId() {
        return document.getLong("_id");
    }

    public String getName() {
        return document.getString("name");
    }
    
    public String getPortWrite() {
        return document.getString("port_write");
    }
    
    public String getPortRead() {
        return document.getString("port_read");
    }

    public int getMin() {
        return document.getInt("min");
    }

    public int getMax() {
        return document.getInt("max");
    }
        
}
