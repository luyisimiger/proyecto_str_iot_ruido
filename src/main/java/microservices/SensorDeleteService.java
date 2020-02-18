/*
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package microservices;

import com.google.gson.Gson;
import data.managers.SensorManager;
import java.util.Properties;
import models.Sensor;
import spark.Request;
import spark.Response;
import spark.Route;

/**
 *
 * @author luyisimiger
 */
public class SensorDeleteService implements Route {
    
    private SensorManager manager;
    
    public SensorDeleteService(SensorManager manager) {
        this.manager = manager;
    }
    
    @Override
    public Object handle(Request req, Response resp) throws Exception {
        
        long sensorid = Long.parseLong(req.params(":id"));        
        long count = manager.delete(sensorid);
        
        Properties prop = new Properties();
        prop.setProperty("message", "ok");
        prop.setProperty("count", String.valueOf(count));
        
        Gson gson = new Gson();
        String json = gson.toJson(prop);
        
        resp.type("application/json");
        return json;
        
    }
}
