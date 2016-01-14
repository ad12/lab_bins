import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;

/**
 * Uses worst-fit in order heuristic model to add files to disk.
 * The methods were divided to avoid having a long main method, to differentiate unique tasks,
 * and to allow for inheritance to override the algorithm based on the type of heuristic model used
 */
public class Bins {
    protected String dataFile;;
    public List<Integer> data;
    protected PriorityQueue<Disk> queue;
    protected long totalSize; //value that is affected by the processing algorithm (ie. the processing
                              // algorithm is the only method that should affect this value)
    /**
     * Creates new bin and initializes data list with default dataFile
     */
    public Bins(){
    	data=new ArrayList<Integer>();
    	this.dataFile="data/example.txt";
    	setDataFile(this.dataFile);
    	queue=new PriorityQueue<Disk>();
    	totalSize=0;
    }
    
    /**
     * Creates new bin and initializes data list with specified dataFile
     */
    public Bins(String dataFile){
    	this();
    	this.dataFile="data/"+dataFile;
    	setDataFile(this.dataFile);
    }
    
    public long getTotalSize(){
    	return this.totalSize;
    }
    public List<Integer> getData(){
    	return this.data;
    }
    
    public PriorityQueue<Disk> getPQ(){
    	return this.queue;
    }
    public List<Integer> readData (Scanner input) {
        List<Integer> results=new ArrayList<Integer>();
        while (input.hasNextLine()) {
        	int val=Integer.parseInt(input.nextLine());
            results.add(val);
        }
        return results;
    }
    
    /**
     * Allows data list to be set from constructor or to be changed when user wants to use different
     * file
     * Increases code flexibility
     */
    public void setDataFile(String filename){
    	dataFile=filename;
    	Scanner input = null;
		try {
			input = new Scanner(new File(filename));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	setData(readData(input));
    }
    
    /**
     * Allows tester to set data to whatever he/she wants the list to be
     * ie. doesn't require a text file to read from- useful for testing accuracy of algorithm
     * @param data
     */
    public void setData(List<Integer> data){
    	this.data.clear();
    	this.data.addAll(data);
    }
    
    /**
     * Creating a separate method for the in-order algorithm simplifies the testing procedure for
     * the algorithm.
     * In this case, the algorithm itself can be run by passing any list of data.
     * This list can be manually generated and allows for hand-checking by the user
     */
    public PriorityQueue<Disk> processingAlgorithm(List<Integer> data){
    	if (data.isEmpty()) {
    		System.out.println("Dope");
    		return null;
    	}
    	this.totalSize=0;
    	PriorityQueue<Disk> pq=new PriorityQueue<Disk>();
        pq.add(new Disk(0));
        int diskId = 1;
    	for (Integer size : data) {
            Disk d = pq.peek();
            if (d.freeSpace() >= size) {
                pq.poll();
                d.add(size);
                pq.add(d);
            } else {
                Disk d2 = new Disk(diskId++);
                d2.add(size);
                pq.add(d2);
            }
            this.totalSize+=size;
        }
    	return pq;
    }
    
    /**
     * Creating a separate printing methods differentiates the task of printing and running the
     * algorithm.
     * By creating a new method, we can structure the code so it is more readable
     */
    public void printResultToConsole(String algorithm,long total, PriorityQueue<Disk> pq){
    	System.out.println("total size = " + total / 1000000.0 + "GB");
        System.out.println();
        System.out.println(algorithm+":");
        //changed to number of disks rather than number of priority queues (increased clarity)
        System.out.println("number of disks used: " + pq.size()); 
        System.out.println();
    }
    
    /**
     * Main method used to run algorithm when not using static method
     * Can be used when no static methods are used or if a bin object is created
     */
    public void runAlgorithm(){
    	if (this.data.isEmpty()) {
    		System.out.println("Data List is empty");
    		return;
    	}
    	PriorityQueue<Disk> pq=processingAlgorithm(data);
    	queue.addAll(pq);
    	printResultToConsole("worst-fit in-order model",this.totalSize,pq);
    }
    
    /**
     * Main method is cleaner and requires fewer lines than the prior implementation
     * The class itself is used, increasing the flexibility of the class and the main method as
     * fewer lines of code have to be altered
     */
    public static void main (String args[]) {
        Bins b = new Bins();
        b.runAlgorithm();
    }
}
