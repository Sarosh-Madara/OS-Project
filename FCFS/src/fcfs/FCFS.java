/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fcfs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Asad
 */
public class FCFS {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.err.println("******** First Come First Serve Process Scheduling Algorithm ********\n\n");
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
        
        System.err.println("\n-----------------------------Before sorting------------------------------");
        for(int i=0; i<processlist.size(); i++){
            System.out.println(processlist.get(i).name+" "+processlist.get(i).arrivalTime+" "+processlist.get(i).burst);
        }
        
        Collections.sort(processlist);
        
        
        System.err.println("--------------------------After sorting----------------------------------");
        for(int i=0; i<processlist.size(); i++){
            System.out.println(processlist.get(i));
        }
        
        int[] arrivals = new int[processlist.size()];
        
        for(int i=0; i<processlist.size();i++){
            arrivals[i] = processlist.get(i).burst;
        }
        
        int waiting = 0;
        for(int i=0; i<processlist.size(); i++){
            System.err.println("-------------------------------------------------------------------------");
            System.out.println("Process "+processlist.get(i).name+"  is executing for "+processlist.get(i).burst+"sec");
            try {
                Thread.sleep(1000 * processlist.get(i).burst);
                
                
                    
                    
                    
                if(processlist.get(i).arrivalTime != 0){
                    waiting += processlist.get(i-1).burst;
                processlist.get(i).wait =  waiting - processlist.get(i).arrivalTime;
                processlist.get(i).turnAround=processlist.get(i).wait+processlist.get(i).burst;
                System.out.println("Process Waiting Time: "+processlist.get(i).wait+"sec");
                 System.out.println("Process Turn Around Time: "+processlist.get(i).turnAround+"sec");
                }
                else{
                System.out.println("Process Waiting Time: "+0+"sec");
                processlist.get(i).turnAround=processlist.get(i).burst;
                 System.out.println("Process Turn Around Time: "+processlist.get(i).turnAround+"sec");
                }
                
                System.out.println("Process "+processlist.get(i).name+"  finish executing....");
                  System.err.println("-------------------------------------------------------------------------\n");
                
                
            

            } catch (InterruptedException ex) {
                Logger.getLogger(FCFS.class.getName()).log(Level.SEVERE, null, ex);
            }
                        
        }
     
    }
    
}

class process implements Comparable<process>{
    
    public process(String name, int arrivalTime, int brust)
    {
        this.name = name;
        this.arrivalTime = arrivalTime;
        this.burst = brust;
       
    }
    public process()
    {

    }
    public String name;
    public int arrivalTime;
    public int burst;
    public int wait;
    public int turnAround;

    @Override
    public String toString() {
        return name+" "+ arrivalTime+" "+ burst;
    }
 
    @Override
    public int compareTo(process o) {
        int tempArrival = o.arrivalTime;
        return this.arrivalTime - tempArrival;
    }
    
    
}