/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import com.mongodb.BasicDBObject;
import java.util.Date;

/**
 *
 * @author luyisimiger
 */
public class Medition {
    
    private BasicDBObject document;

    public Medition(BasicDBObject document) {
        this.document = document;
    }

    public Date getFechaHora() {
        return document.getDate("fechahora");
    }
    
    public double getValor() {
        return document.getDouble("valor");
    }
}
