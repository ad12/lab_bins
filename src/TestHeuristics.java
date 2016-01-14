import java.util.List;

/**
 * This class is a test class for the heuristic algorithms
 * Different methods can be added to test different parts of the program
 * Example test methods are written below
 * A separate test class allows the use of the other class as a model with accessible fields. This
 * helps the tester cross check expected results with actual results
 * @author arjunaashna
 *
 */
public class TestHeuristics {
	private static final String FILE_NAME="example.txt";
	/**
	 * Determine if both algorithms result in same total size count
	 */
	public static void testSameSizeCount(Bins a, Bins b){
		if (a.getTotalSize()==b.getTotalSize()){
			System.out.println("Same size");
		} else {
			System.out.println("Error: Different sizes");
		}
	}
	
	/**
	 * Determine if data is empty list
	 */
	public static void emptyDataSet(Bins a){
		List<Integer> data = null;
		if (a.processingAlgorithm(data)==null) System.out.println("Check for empty data set works");
	}
	
	public static void main(String args[]){
		Bins binInOrder=new Bins(FILE_NAME);
		Bins binDecreasingOrder=new BinsDecreasingOrder(FILE_NAME);
		binInOrder.runAlgorithm();
		binDecreasingOrder.runAlgorithm();
		//testSameSizeCount(binInOrder,binDecreasingOrder);
		//emptyDataSet(binInOrder);
	}
}
