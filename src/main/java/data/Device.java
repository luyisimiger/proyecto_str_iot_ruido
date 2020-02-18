/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import com.fazecast.jSerialComm.SerialPort;
import data.managers.MeditionManager;
import data.managers.SensorManager;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.Sensor;

/**
 *
 * @author luyisimiger
 */
public class Device {
    
    class TaskReceiveData implements Runnable {
        
        private Sensor sensor;
        private MeditionManager mmanager;
                
        TaskReceiveData(Sensor sensor, MeditionManager mmanager) {
            this.sensor = sensor;
            this.mmanager = mmanager;
        }

        @Override
        public void run() {
            
            System.out.println(sensor.getName());
            
            SerialPort serial = SerialPort.getCommPort(sensor.getPortRead());
            serial.setComPortParameters(9600, 8, 1, 0);
            serial.setComPortTimeouts(serial.TIMEOUT_WRITE_BLOCKING, 0, 0);
            
            boolean portOpen;
            
            do {
            
                System.out.println(sensor.getName() + " " + sensor.getPortRead() + " opening...");
                portOpen = serial.openPort();
                
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {}
            
            } while (!portOpen);
            
            
            
            System.out.println(sensor.getName() + " " + sensor.getPortRead() + " opened! ");
            
            BufferedReader buffer = new BufferedReader( new InputStreamReader( serial.getInputStream() ) );
            
            while(true) {
                
                if(serial.bytesAvailable() > 0) {                                    
                    try {
                        String num = buffer.readLine();
                        System.out.println(sensor.getName() + ", receive: " + num);
                        mmanager.create( Double.parseDouble(num) );
                        Thread.sleep(3000);
                    } catch (Exception ex) {
                        Logger.getLogger(Device.class.getName()).log(Level.SEVERE, null, ex);
                    } finally {}                    
                }
            }
        }
    }
    
    public void start(SensorManager smanager) {
        
        Sensor s1=null, s2=null, s3=null, s4=null, s5=null;
        
        List<Sensor> sensores = smanager.objects();
        
        for (Sensor s : sensores) {
            if (s.getName().equals("Sensor Sala 1")) s1 = s;
            if (s.getName().equals("Sensor Sala 2")) s2 = s;
            if (s.getName().equals("Sensor Sala 3")) s3 = s;
            if (s.getName().equals("Sensor Sala 4")) s4 = s;
            if (s.getName().equals("Sensor Sala 5")) s5 = s;
        }
                
        //TODO: relacionar los sensores
        Thread h1 = new Thread( new TaskReceiveData(s1, new MeditionManager(smanager.getConexion(), s1) ) );
        Thread h2 = new Thread( new TaskReceiveData(s2, new MeditionManager(smanager.getConexion(), s2) ) );
        Thread h3 = new Thread( new TaskReceiveData(s3, new MeditionManager(smanager.getConexion(), s3) ) );
        Thread h4 = new Thread( new TaskReceiveData(s4, new MeditionManager(smanager.getConexion(), s4) ) );
        Thread h5 = new Thread( new TaskReceiveData(s5, new MeditionManager(smanager.getConexion(), s5) ) );
        
        System.out.println("INICIO DE HILOS");
        
        h1.start();
        h2.start();
        h3.start();
        h4.start();
        h5.start();
        
    }
}
