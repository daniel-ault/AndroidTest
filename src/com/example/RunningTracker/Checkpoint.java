/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.example.RunningTracker;

/**
 *
 * @author Daniel
 */
public class Checkpoint {
    private String intersection;
    private double distanceFromStart;
    private double goalTime;
    private double time;
    
    Checkpoint(String intersection, double distanceFromStart, double time) {
        this.intersection = intersection;
        this.distanceFromStart = distanceFromStart;
    }
    
    public void setGoalTime(double time) { goalTime = time; }
    
    public String getIntersection() { return this.intersection; }
    public double getDistanceFromStart() { return this.distanceFromStart; }
    public double getGoalTime() { return this.goalTime; }
    
    public String getGoalTimeString() {
    	String s = "" + (int)Math.floor(goalTime);
    	double seconds = goalTime - Math.floor(goalTime);
    	seconds = Math.floor(seconds*60);
    	
    	s += ":";
    	if (seconds<10)
    		s += "0";
    	
    	s += (int)seconds;
    	
    	return s;
    }
    
    @Override
    public String toString() {
        String s = intersection + ":  " + Double.toString(distanceFromStart);
        s += ",  " + Double.toString(goalTime);
        return s;
    }
}
