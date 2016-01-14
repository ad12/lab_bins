import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;

/**
 * Uses worst-fit decreasing order heuristic model to add files to disk.
 * This class extends the Bins class as the only difference is the algorithm and the print method
 */
public class BinsDecreasingOrder extends Bins{
	public BinsDecreasingOrder(){
		super();
	}
	public BinsDecreasingOrder(String filename){
		super(filename);
	}
	
	 /**
     * The decreasing Order algorithm is like the in-order algorithm, but has the data sorted
     * in decreasing order. Therefore, making a call to inOrder algorithm, which processes the data
     * as it is passed to the method. In this case, the data is sorted beforehand. 
     * 
     * Ideally, if many heuristic models were being used, creating subclasses of the Bins class
     * would prove beneficial, as the subclasses can be differentiated by the algorithm they use
     * to process the data. However, there are only 2 cases considered here, so it is not necessary
     * to create a subclass, as it would only require an extra method in the other class, 
     * but I did to increase the organization of the code and to differentiate the different 
     * algorithms structurally in the code. It also allows for other developers to see how the
     * code is structured
     * 
     */
	public PriorityQueue<Disk> processingAlgorithm(List<Integer> data){
		Collections.sort(data, Collections.reverseOrder());
    	return super.processingAlgorithm(data);
	}
	
	/**
     * Code is duplicated but it is more reasonable to duplicate 3 lines of code rather than
     * create new method to call printResultToConsole
     * Though this structure can be considered a Code Smells error, it is more effective than creating
     * a new method to execute one line of code (ie. middle man)
     */
    public void runAlgorithm(){
    	PriorityQueue<Disk> pq=processingAlgorithm(data);
    	queue.addAll(pq);
    	printResultToConsole("worst-fit decreasing order model",this.totalSize,pq);
    }
}
