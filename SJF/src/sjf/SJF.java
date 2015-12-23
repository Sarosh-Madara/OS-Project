/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sjf;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import sun.misc.Queue;

/**
 *
 * @author Asad
 */
public class SJF {

    /**
     * @param args the command line arguments
     */
//    public static void main(String[] args) throws InterruptedException {
//        System.out.println("******** Shortest Process Next Scheduling Algorithm ********\n\n");
//        System.out.print("Enter # of Processes: ");
//        Scanner no=new Scanner(System.in);
//        int noofprocess=no.nextInt();
//        ArrayList<process> processlist = new ArrayList<>();
//        for(int i=0; i<noofprocess; i++){
//            Scanner scan = new Scanner(System.in);
//            process p = new process();
//            p.name = "P"+i;
//            System.out.print("Enter Arrival Time of Process "+p.name+" : ");
//            p.arrivalTime = scan.nextInt();
//            System.out.print("Enter Service Time of Process "+p.name+" : ");
//            p.burst = scan.nextInt();
//            processlist.add(p);
//            
//        }
//        
//        System.out.println("-----------------------------Before sorting------------------------------");
//        for(int i=0; i<processlist.size(); i++){
//            System.out.println(processlist.get(i).name+" "+processlist.get(i).arrivalTime+" "+processlist.get(i).burst);
//        }
//        
//        Collections.sort(processlist,new ArrivalTimeComparer());
//        
//        
//        System.out.println("--------------------------After sorting--------------------------");
//        for(int i=0; i<processlist.size(); i++){
//            System.out.println(processlist.get(i));
//        }
//        
////          Renaming the process
//        for(int i=0; i < processlist.size(); i++){
//           processlist.get(i).name = "P"+i;
//        }
//        System.out.println("--------------------------After Name sorting--------------------------");
//        for(int i=0; i<processlist.size(); i++){
//            System.out.println(processlist.get(i));
//        }
//        
//  
//        int k;
//        ArrayList<process> arr = new ArrayList<>();
//        int m = 0;
//        
//        
//        for(int i=0; i< processlist.size(); i++){
//            Collections.sort( processlist ,new ServiceTimeComparer());
//            for ( int l = 1;    l<=processlist.get(i).burst;    l++)
//            {
//                System.out.println("Process " +processlist.get(i).name + " is runnng");
//                if(l!=0)
//                {
//                      for ( k = m+1 ;      k<processlist.size();   k++ )
//                      {
//                          if(!(k > processlist.size())){
//                            if ( l ==  processlist.get(k).arrivalTime){
//                                
//                                if(!(arr.contains(processlist.get(k)))){
//                                    
//                                    arr.add(processlist.get(k));
//                                    
//                                    Collections.sort(arr, new ServiceTimeComparer());
//                                    
//                                    for( int q = i+1 ; q <= arr.size(); q++){
//                                        
//                                        processlist.remove(q);
//                                        
//                                        
//                                        processlist.add(q, arr.get(q - 1));
//                                        System.out.println("ye dekh asad process list(processlist): "+processlist);
//                                        arr.remove(q-1);
//                                    }
//                                    
//                                    System.out.println("Temp Queue: "+arr);
//                                    System.out.println("process added to queue: "+processlist.get(k).name);
//                                }
//                            }
//                          }
//                      } 
//                }
//            }
//           
//        }
//        
//        
////        System.err.println("\n\n==============================================");
//        //      Sorting according to Service Time
//        for (process p:arr)
//        {
//            System.out.println("Service time of proceess: "+p.name+" is "+p.burst);
//        }
//    }
    
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