package hrrn;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class HRRN {

    public static ArrayList<process> mainQueue;
    public static ArrayList<process> readyQueue;
    public static process running_process;
    public static int count,offset;
    public static int last_index = 0;
    
    
    public static void main(String args[]) throws InterruptedException{
        
        System.out.println("******** Highest Response Ratio Next Scheduling Algorithm ********\n\n");
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

        
        Collections.sort(mainQueue, new ArrivalTimeComparer());

        
        System.out.println("\n\n");
        
        count = 0;
        offset = 0;
        
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
                iterateOverMainQueue();
            }
            
            offset += running_process.burst;
            
            settingWaitOfReadyQueue();             
            
            running_process.wait = running_process.start - running_process.arrivalTime;
            
            System.out.println("Process "+running_process.name+" finshes Execution"+"\nTurnaround Time: "+(running_process.burst + running_process.wait));
            System.out.println("Waiting Time: " + running_process.wait +"\n\n");
            
            running_process = null;
           
            CalculatingResponseRatio();
           if (i==mainQueue.size()-1)
           {
               System.out.println("=========================================================");
               break;
           }
           
            System.out.println("Process Sorting on behalf of Response Ratio");
            for (process p : readyQueue) {
                System.out.println(p);
            }
            
            System.out.println("=========================================================");
            
            SortingRR();
        }  
    }
    
    private static void iterateOverMainQueue( ) {
        
       last_index = offset;
       
       for(int i = 0;  i < mainQueue.size();  i++){
           
           if( last_index <= mainQueue.get(i).arrivalTime && mainQueue.get(i).arrivalTime <= running_process.burst + offset 
                   && !readyQueue.contains(mainQueue.get(i)) && mainQueue.get(i) != running_process){
               readyQueue.add(mainQueue.get(i));
           }
       }
 
    }
    private static void CalculatingResponseRatio()
    {
        for (int i=0;i<readyQueue.size();i++)
        {
             readyQueue.get(i).ResponseRatio = ( readyQueue.get( i ).wait + readyQueue.get( i ).burst) / readyQueue.get( i ).burst;
        }
    }
    
    private static void SortingRR()
    {
        Object[] processes =  readyQueue.toArray();

        process temp;
        for (int i=0;i<readyQueue.size()-1;i++)
        {
            for ( int cal = i + 1; cal < readyQueue.size(); cal++ )
            {
                if (readyQueue.get(cal).ResponseRatio>readyQueue.get(i).ResponseRatio)
                {
                    temp = (process) processes[i];
                    processes[i] = processes[cal];
                    processes[cal] = temp;

                }
            }
        }

        readyQueue = new ArrayList<>();
       for (Object process : processes) {
           readyQueue.add((process) process);
       }
    }

    private static void settingWaitOfReadyQueue() {
        for (process p : readyQueue)
            p.wait = count - p.arrivalTime;
    }

    static class ArrivalTimeComparer implements Comparator<process>{

        @Override
        public int compare(process o1, process o2) {
            return o1.arrivalTime - o2.arrivalTime;
        }    
    }

    static class process{


        public process()
        {

        }
        public String name;
        public int arrivalTime;
        public double burst;
        public double wait;
        public int start;
        public int turnAround;
        public double ResponseRatio;

        @Override
        public String toString() {
            return "Process" + name+"   Arrival: "+ arrivalTime+" Burst: "+ burst+"   ResponseRatio " + ResponseRatio + "   Wait: " + wait;
        }
    }
}