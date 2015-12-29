/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sjf;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

/**
 *
 * @author Sarosh Madara
 */
public class SJF3 {
    
    public static ArrayList<process> mainQueue;
    public static ArrayList<process> readyQueue;
    public static process running_process;
    public static int count,offset;
    public static ArrayList<process> new_readyQueue;
    public static int last_index = 0;
    
    
    public static void main(String args[]) throws InterruptedException{
        
        System.out.println("******** Shortest Process Next Scheduling Algorithm ********\n\n");
        System.out.print("Enter # of Processes: ");
        
        
        Scanner no=new Scanner(System.in);
        
        int noofprocess = no.nextInt();
        
        mainQueue = new ArrayList<>();
        readyQueue = new ArrayList<>();
        
        for(int i=0; i<noofprocess; i++){
            Scanner scan = new Scanner(System.in);
            process p = new process();
            p.name = "P"+i;
            System.out.print("Enter Arrival Time of Process "+p.name+" : ");
            p.arrivalTime = scan.nextInt();
            System.out.print("Enter Service Time of Process "+p.name+" : ");
            p.burst = scan.nextInt();
            mainQueue.add(p);    
        }

//        
        Collections.sort(mainQueue, new ArrivalTimeComparer());

        
        System.out.println("\n\n");
        
        count = 0;
        offset = 0;
        int totalBurst = 0;
        
        for(int i=0; i < mainQueue.size(); i++){
            
            if( running_process == null && !readyQueue.isEmpty()){
                running_process = readyQueue.get(0);
                readyQueue.remove(0);
            }
            else
                running_process = mainQueue.get(0);
            
            running_process.start = count;
            
            System.out.println("Process: "+running_process.name+" currently executing");
            
            for(int service = 0; service != running_process.burst; service++){
                
                count++;
                readyQueue = iterateOverMainQueue();
            }
            offset += running_process.burst;
            
            totalBurst += running_process.burst;
            
            running_process.wait = running_process.start - running_process.arrivalTime;
            
            incWaitOfReadyQueue();
            System.out.println("Process "+running_process.name+" finshes Execution"+"\nTurnaround Time: "+(running_process.burst + running_process.wait));
            System.out.println("Waiting Time: " + running_process.wait +"\n\n");
            
            running_process = null;
            Collections.sort(readyQueue, new ServiceTimeComparer());
        }  
    }
    
   
    private static ArrayList<process> iterateOverMainQueue( ) {
        
       new_readyQueue = new ArrayList<>();
       
       if( readyQueue != null && !readyQueue.isEmpty())
            new_readyQueue.addAll(readyQueue);
       
       last_index = offset;
       
       for(int i = 0;  i < mainQueue.size();  i++){
           
           if( last_index <= mainQueue.get(i).arrivalTime && mainQueue.get(i).arrivalTime <= running_process.burst + offset 
                   && !new_readyQueue.contains(mainQueue.get(i)) && mainQueue.get(i) != running_process){
               
               new_readyQueue.add(mainQueue.get(i));
           }
       }
       return new_readyQueue;
    }

    private static void incWaitOfReadyQueue() {
        for (process p : readyQueue)
            p.wait = count - p.arrivalTime;
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