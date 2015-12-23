/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hrrn;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

/**
 *
 * @author Sarosh Madara
 */
public class HRRN {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.out.println("******** Shortest Process Next Scheduling Algorithm ********\n\n");
        System.out.print("Enter # of Processes: ");
        Scanner no=new Scanner(System.in);
        int noofprocess=no.nextInt();
        ArrayList<process> processlist = new ArrayList<>();
        for(int i=0; i<noofprocess; i++){
            Scanner scan = new Scanner(System.in);
            process p = new process();
            p.name = "P"+i;
            System.out.print("Enter Arrival Time of Process "+p.name+" : ");
            p.arrivalTime = scan.nextInt();
            System.out.print("Enter Service Time of Process "+p.name+" : ");
            p.burst = scan.nextInt();
            processlist.add(p);
            
        }
        
        System.out.println("-----------------------------Before sorting------------------------------");
        for(int i=0; i<processlist.size(); i++){
            System.out.println(processlist.get(i).name+" "+processlist.get(i).arrivalTime+" "+processlist.get(i).burst);
        }
        
        Collections.sort(processlist,new ArrivalTimeComparer());
        
        
        System.out.println("--------------------------After sorting--------------------------");
        for(int i=0; i<processlist.size(); i++){
            System.out.println(processlist.get(i));
        }
    }
    
}


class ServiceTimeComparer implements Comparator<process>{

    @Override
    public int compare(process o1, process o2) {
        return o1.burst - o2.burst;
    }
}

class ArrivalTimeComparer implements Comparator<process>{

    @Override
    public int compare(process o1, process o2) {
        return o1.arrivalTime - o2.arrivalTime;
    }    
}

class process{
    
    public process(String name, int arrivalTime, int brust, int priority)
    {
        this.name = name;
        this.arrivalTime = arrivalTime;
        this.burst = brust;
        this.priority = priority;
    }
    public process()
    {

    }
    public String name;
    public int arrivalTime;
    public int burst;
    public int priority;
    public int wait;
    public int end;
    public int start;
    public int turnAround;

    @Override
    public String toString() {
        return name+" "+ arrivalTime+" "+ burst;
    }
}