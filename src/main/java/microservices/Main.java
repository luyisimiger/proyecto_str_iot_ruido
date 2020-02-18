/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package microservices;

import data.Conexion;
import static spark.Spark.*;

import data.Device;
import data.managers.SensorManager;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.Version;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;
import org.apache.log4j.BasicConfigurator;
import util.Util;


/**
 *
 * @author luyisimiger
 */

public class Main {

    public static void main(String[] args) {
        
        SensorManager manager = new SensorManager(Conexion.getConexion());
        
        /* configure freemarker */
        final Configuration conf=new Configuration(new Version(2,3,0));
	conf.setClassForTemplateLoading(Main.class, "/");
        conf.setNumberFormat("0.######");
        
        /* configure log4j */
        BasicConfigurator.configure();
        
        get("/", (req, resp) -> {
            
            StringWriter writer = new StringWriter(); 
            Template template = conf.getTemplate("templates/index.html"); 
            template.process(null, writer);
            return writer;
            
        });
        
        get("/api/sensors", new SensorDataListService(manager));        
        get("/api/sensors/:id", new SensorDataService(manager));
        get("/api/sensors/:id/delete", new SensorDeleteService(manager));
        get("/api/sensors/:id/regression", new SensorRegressionService(manager));
        
        get("/sensors", (req, resp) -> {
            
            Map<String, Object> root = new HashMap<>();
            root.put("sensors", manager.objects());
            
            StringWriter writer = new StringWriter(); 
            Template template = conf.getTemplate("templates/sensors/index.html"); 
            template.process(root, writer);
            return writer;
            
        });
        
        get("/sensors/new", (req, resp) -> {
            int min = Util.randint(0, 199);
            int max = Util.randint(min+1, 200);
            manager.create("/portcom/xyz", "Sensor de Sala AB", min, max);
            return "Crear sensor, (" + min + ", " + max + ")";
        });
        
        get("/sensors/:id", (req, resp) -> {
            
            long id = Long.parseLong(req.params(":id"));
            
            Map<String, Object> root = new HashMap<>();
            root.put("sensor", manager.get(id));
            
            StringWriter writer = new StringWriter(); 
            Template template = conf.getTemplate("templates/sensors/detail.html"); 
            template.process(root, writer);
            return writer;
            
        });        
        
        // TODO Auto-generated method stub
        Device device = new Device();
        device.start(manager);
    }
}

