/*
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package microservices;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import data.managers.SensorManager;
import models.Sensor;
import spark.Request;
import spark.Response;
import spark.Route;

/**
 *
 * @author luyisimiger
 */
public class SensorDataService implements Route {
    
    private SensorManager manager;
    
    public SensorDataService(SensorManager manager) {
        this.manager = manager;
    }
    
    @Override
    public Object handle(Request req, Response resp) throws Exception {
        
        resp.type("application/json");
        
        long sensorid = Long.parseLong(req.params(":id"));        
        Sensor sensor = manager.get(sensorid);
        
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd HH:mm:ss").create();
        
        String json = gson.toJson(sensor);
        return json;
    }
}
