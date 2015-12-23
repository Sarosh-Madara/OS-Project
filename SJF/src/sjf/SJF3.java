/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sjf;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
 *
 * @author Sarosh Madara
 */
public class SJF3 {
    
    public static ArrayList<process> mainQueue;
    public static ArrayList<process> readyQueue;
    public static process current_exe_process;
    public static int count,offset;
    
    public static void main(String args[]) throws InterruptedException{
        
        System.out.println("******** Shortest Process Next Scheduling Algorithm 3 ********\n\n");
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
        
        
        // Sorting cirteria on the bases of Arrival time.
        System.out.println("-----------------------------Before sorting------------------------------");
        for(int i=0; i<mainQueue.size(); i++){
            System.out.println(mainQueue.get(i).name+" "+mainQueue.get(i).arrivalTime+" "+mainQueue.get(i).burst);
        }
        
        Collections.sort(mainQueue, new ArrivalTimeComparer());
        
        
        System.out.println("--------------------------After sorting--------------------------");
        for(int i=0; i<mainQueue.size(); i++){
            System.out.println(mainQueue.get(i).name+" "+mainQueue.get(i).arrivalTime+" "+mainQueue.get(i).burst);
        }
        
        
        
        current_exe_process = mainQueue.get(0);
        
        int length = mainQueue.size();
        
        for(int i=0; i < length; i++){
            
            if(mainQueue.contains(current_exe_process))
                mainQueue.remove(current_exe_process);
            
            if( current_exe_process == null)
                current_exe_process = readyQueue.get(0);
            
            for(int j=0; j != current_exe_process.burst; j++){
                System.out.println("currently_exe: "+current_exe_process);
                readyQueue = iterateOverQueue(mainQueue, j, readyQueue);
                
                // increment turnaround time of the current process
                incrementTurnaroundTime(mainQueue,current_exe_process);
//                incWaitOfReadyQueue(readyQueue);
            }
            
            current_exe_process = null;
            if(readyQueue != null && !readyQueue.isEmpty())
                current_exe_process = readyQueue.get(0);
            // sort here
            Collections.sort(readyQueue, new ServiceTimeComparer());
        }
        
        for(process p : readyQueue)
            System.out.println("Process: "+p.name+" wait: "+p.wait+" turnaround: "+p.turnAround);
        
        for(process p: mainQueue){
            System.out.println("printing main Process: "+p.name+" wait: "+p.wait+" turnaround: "+p.turnAround);
        }
        
        System.out.println("mainQ: "+mainQueue);
        System.out.println("readyQ: "+readyQueue);
    }
    
    public static ArrayList<process> new_readyQueue;
    public static int last_index = 0;
    private static ArrayList<process> iterateOverQueue(ArrayList<process> mainQueue, int ARRIVAL_AT,ArrayList<process> old_readyQueue) {
        
       new_readyQueue = new ArrayList<>();
       
       if( old_readyQueue != null && !old_readyQueue.isEmpty())
            new_readyQueue.addAll(old_readyQueue);
       
       for(int i = 0;  i < mainQueue.size();  i++){
           
           if( last_index == mainQueue.get(i).arrivalTime && !new_readyQueue.contains(mainQueue.get(i))){
               // last index has to be updated
               new_readyQueue.add(mainQueue.get(i));
               
               System.out.println("newList: "+new_readyQueue);
               
               mainQueue.get(i).wait++;
               
           }else if(new_readyQueue.contains(mainQueue.get(i))){
               new_readyQueue.get(i).wait++;
               System.out.println("process already in readyQueue"+mainQueue.get(i));
           }
           
       }
       
       return new_readyQueue;
    }

    static int index;
    
    private static void incrementTurnaroundTime(ArrayList<process> mainQueue, process current_exe_process) {
        if(mainQueue.contains(current_exe_process)){
            index = mainQueue.indexOf(current_exe_process);
            mainQueue.get(index).turnAround++;
        }
    }

    private static void incWaitOfReadyQueue(ArrayList<process> readyQueue) {
        for (process p : readyQueue) {
            p.wait++;
        }
    }
}





