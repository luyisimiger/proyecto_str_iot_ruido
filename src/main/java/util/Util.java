/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

/**
 *
 * @author luyisimiger
 */
public class Util {
    
    public static double random(double min, double max) {
        return Math.floor( Math.random() * (max - min) ) + min;
    }
    
    public static int randint(int min, int max) {
        return (int) random(min, max);
    }
}
