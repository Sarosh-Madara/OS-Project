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
public class SJF2 {
    
    
    static ArrayList<process> processlist;
    static ArrayList<process> temp;
    
    
    
    
    
//    public static void main(String args[]){
//        
//        System.out.println("******** Shortest Process Next Scheduling Algorithm ********\n\n");
//        System.out.print("Enter # of Processes: ");
//        
//        
//        Scanner no=new Scanner(System.in);
//        
//        int noofprocess=no.nextInt();
//        
//        processlist = new ArrayList<>();
//        
//        for(int i=0; i<noofprocess; i++){
//            Scanner scan = new Scanner(System.in);
//            process p = new process();
//            p.name = "P"+i;
//            System.out.print("Enter Arrival Time of Process "+p.name+" : ");
//            p.arrivalTime = scan.nextInt();
//            System.out.print("Enter Service Time of Process "+p.name+" : ");
//            p.burst = scan.nextInt();
//            processlist.add(p);    
//        }
//        
//        
//        // Sorting cirteria on the bases of Arrival time.
//        System.out.println("-----------------------------Before sorting------------------------------");
//        for(int i=0; i<processlist.size(); i++){
//            System.out.println(processlist.get(i).name+" "+processlist.get(i).arrivalTime+" "+processlist.get(i).burst);
//        }
//        
//        Collections.sort(processlist,new ArrivalTimeComparer());
//        
//        
//        System.out.println("--------------------------After sorting--------------------------");
//        System.out.println("processlist size"+processlist.size());
//        for(int i=0; i<processlist.size(); i++){
//         System.out.println(processlist.get(i).name+" "+processlist.get(i).arrivalTime+" "+processlist.get(i).burst);
//        }
//        
//        //System.err.println("processList: "+processlist);
//        
//        
//        
////        // Renaming the process
////        for(int i=0; i < processlist.size(); i++){
////            processlist.get(i).name = "P"+i;
////        }
////        
////        System.err.println("processList: "+processlist);
//        
//        temp = null;
//        
//        for(int i = 0; i<processlist.size(); i++){
//            
//            System.err.println("Currently running process: "+processlist.get(i));
//            
//            for(int j = 1;  j <= processlist.get(i).burst;   j++){
//                
//                
//                temp = iterateOverQueue( processlist , j , temp);
//                
//                System.err.println("temp; "+temp);
//            }
//        }
//        
//    }

//    private static ArrayList<process> iterateOverQueue(ArrayList<process> array, int ARRIVAL_AT,ArrayList<process> oldList) {
//       ArrayList<process> newlist = new ArrayList<>();
//       if( oldList != null && !oldList.isEmpty())
//            newlist.addAll(oldList);
//       
//       for(int i = 0; i<array.size(); i++){
//           if( ARRIVAL_AT == array.get(i).arrivalTime && !newlist.contains(array.get(i))){
//               newlist.add(array.get(i));
//               System.err.println("newList: "+newlist);
//           }
//       } 
//       
//       return newlist;
//    }
    
    
}

