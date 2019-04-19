package client_server;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Worker {
    private ArrayList<Integer> list1 = new ArrayList<Integer>();
    private ArrayList<Integer> list2 = new ArrayList<Integer>();
    private Random random = new Random();
    private  Object lock1 = new Object(); 
    private  Object lock2 = new Object(); 
    private void part_one(){
        synchronized(lock1){
        try {
            Thread.sleep(1);
        } catch (InterruptedException ex) {
            Logger.getLogger(Worker.class.getName()).log(Level.SEVERE, null, ex);
        }
        list1.add(random.nextInt(100));
    }}
    
    private void part_two(){
        synchronized(lock2){
         try {
            Thread.sleep(1);
        } catch (InterruptedException ex) {
            Logger.getLogger(Worker.class.getName()).log(Level.SEVERE, null, ex);
        }
        list2.add(random.nextInt(100));
    
    }}  
    
    private  void proceed(){
        for(int i=0; i<1000; i++){
        part_one();
        part_two();
        }
    }
    
    public void start(){
        System.out.println("Start ");
        long starttime = System.currentTimeMillis();
        
        Thread t1 = new Thread (new Runnable(){
            @Override
            public void run() {
                proceed();
            }
        });
        Thread t2 = new Thread (new Runnable(){
            @Override
            public void run() {
                proceed();
            }
        });   
        
        t1.start();
        t2.start();
        
        try {
            t1.join();
            t2.join();
        } catch (InterruptedException ex) {
            Logger.getLogger(Worker.class.getName()).log(Level.SEVERE, null, ex);
        }
        long endtime = System.currentTimeMillis();
        System.out.println("Затраченное время: " +
                (endtime-starttime)+"\n" + 
                "Лист 1:" + list1.size() + "\n" + 
                "Лист 2:" + list2.size());
    }
    Worker() {
    }
    
}
