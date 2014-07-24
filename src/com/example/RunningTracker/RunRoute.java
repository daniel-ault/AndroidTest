/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.example.RunningTracker;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Daniel
 */
public class RunRoute {
    private List<Checkpoint> routeList = new ArrayList<>();
    private double paceGoal = 0;
    private double paceActual;
    private double routeLength = 0;
    
    RunRoute() { }
    
    public void setStart(String intersection) {
        if (routeList.isEmpty()) {
            routeList.add(new Checkpoint(intersection, 0, 0));
        }
        else {
            //ERROR: can't set start if route isn't empty
        }
    }// setStart
    
    
    public void addCheckpoint(String intersection, double distanceFromStart) {
        if (routeList.isEmpty()) {
            //ERROR: need to add start point first
            System.out.println("ERROR: need to add start point first");
        }
        else if (routeList.get(routeList.size()-1).getDistanceFromStart() > distanceFromStart) {
            //ERROR: distance must be larger than previous checkpoint
            System.out.println("ERROR: distance must be larger than previous checkpoint");
        }
        else {
            Checkpoint c = new Checkpoint(intersection, distanceFromStart, 0);
            if (paceGoal != 0)
                c.setGoalTime((c.getDistanceFromStart()/paceGoal)*60);
            else
                c.setGoalTime(0);
            routeList.add(c);
            routeLength = distanceFromStart;
        }
    }// addCheckpoint
    
    public void setGoalPace(double pace) {
        this.paceGoal = pace;
        
        if (routeList.isEmpty())
            return;
        
        for (Checkpoint c : routeList) {
            double goalTime = (c.getDistanceFromStart()/pace)*60;
            c.setGoalTime(goalTime);
        }
    }
    
    public String[] getStringList() {
    	String s[] = new String[routeList.size()];
    	
    	for (int i=0; i<s.length; i++) {
    		s[i] = routeList.get(i).getIntersection();
    	}
    	
    	return s;
    }
    
    public ArrayList<Checkpoint> getCheckpointArrayList() { return (ArrayList<Checkpoint>)routeList; }
    
    public String toString() {
        String s = "";
        for (Checkpoint c : routeList) {
            s += c.toString() + "\n";
        }
        
        return s;
    }
}
