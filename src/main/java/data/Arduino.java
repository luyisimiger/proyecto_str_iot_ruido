/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import models.Sensor;
import util.Util;

/**
 *
 * @author luyisimiger
 */
public class Arduino {

    private Sensor sensor;

    public Arduino(Sensor sensor) {
        this.sensor = sensor;
    }
    
    public double readdata() {
        double data = Util.random(sensor.getMin(), sensor.getMax());
        return data;
    }
    
}
