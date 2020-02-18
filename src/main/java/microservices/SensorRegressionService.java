/*
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package microservices;

import com.google.gson.Gson;
import com.mongodb.BasicDBObject;
import data.managers.SensorManager;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import models.Sensor;
import spark.Request;
import spark.Response;
import spark.Route;
import weka.classifiers.functions.LinearRegression;
import weka.core.Attribute;
import weka.core.FastVector;
import weka.core.Instance;
import weka.core.Instances;

/**
 *
 * @author luyisimiger
 */
public class SensorRegressionService implements Route {
    
    private SensorManager manager;
    
    public SensorRegressionService(SensorManager manager) {
        this.manager = manager;
    }
    
    @Override
    public Object handle(Request req, Response resp) throws Exception {        
        
        long sensorid = Long.parseLong(req.params(":id"));        
        Sensor sensor = manager.get(sensorid);
        List<BasicDBObject> meditions = (List<BasicDBObject>) sensor.getDocument().get("meditions");
        System.out.println("MEDICIONES:" + meditions.size());
        
        FastVector attInfo = new FastVector(2);
        attInfo.addElement( new Attribute("x") );
        attInfo.addElement( new Attribute("y") );
        
        
        Instances data = new Instances("dataset", attInfo, meditions.size());
        data.setClassIndex(data.numAttributes() - 1);
        
        int i = 1;
        for (Iterator<BasicDBObject> iterator = meditions.iterator(); iterator.hasNext(); i++) {
            BasicDBObject document = iterator.next();
            long time = document.getDate("fechahora").getTime();
            int valor = document.getInt("valor");
            
            System.out.println("MEDICION /// " + time + ", " + i + ", " + valor);
            
            Instance medition = new Instance(2);
            medition.setValue((Attribute) attInfo.elementAt(0), time);
            //medition.setValue((Attribute) attInfo.elementAt(0), i);
            medition.setValue((Attribute) attInfo.elementAt(1), valor);
            data.add(medition);
        }
        
        
        LinearRegression lr = new LinearRegression();
        lr.buildClassifier(data);
        
        System.out.println(lr);
                
        double[] coefficients = lr.coefficients();
        System.out.println(coefficients.length);
        System.out.println( coefficients[0] );
        System.out.println( coefficients[2] );
        
        Properties prop = new Properties();
        prop.setProperty("pendiente", String.valueOf(coefficients[0]));
        prop.setProperty("constante", String.valueOf(coefficients[2]));
        
        Gson gson = new Gson();
        String json = gson.toJson(prop);
        
        resp.type("application/json");
        return json;
    }
}
