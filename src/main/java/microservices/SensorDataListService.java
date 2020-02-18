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
import java.util.List;
import models.Sensor;
import spark.Request;
import spark.Response;
import spark.Route;

/**
 *
 * @author luyisimiger
 */
public class SensorDataListService implements Route {
    
    private SensorManager manager;
    
    public SensorDataListService(SensorManager manager) {
        this.manager = manager;
    }
    
    @Override
    public Object handle(Request req, Response resp) throws Exception {
        
        List<Sensor> sensors = manager.objects();
        
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd HH:mm:ss").create();
        
        String json = gson.toJson(sensors);
        
        resp.type("application/json");
        return json;
    }
}
