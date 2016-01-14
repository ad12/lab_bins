import java.util.ArrayList;
import java.util.List;

/**
 * Represents a collection of files; how many it can hold is limited by its capacity.
 * 
 * I have deleted the comments that were unnecessary because they are indicated by the method name
 */
public class Disk implements Comparable<Disk> {
    private int myId;
    private int mySize;
    private int myCapacity;
    private List<Integer> myFiles;

    /**
     * Create an empty Disk with capacity of 1GB
     */
    public Disk () {
        mySize = 0;
        myCapacity = 1000000;
        myFiles = new ArrayList<Integer>();
    }

    /**
     * Create an empty Disk with the given ID.
     */
    public Disk (int id) {
    	this(); //removed duplicated code
        myId = id;
    }

    public int freeSpace () {
        return myCapacity - mySize;
    }

    public void add (int filesize) {
        myFiles.add(filesize);
        mySize += filesize;
    }

    @Override
    public String toString () {
        String result = myId + "\t" + freeSpace() + ":\t";
        for (int k = 0; k < myFiles.size(); k++) {
            result += " " + myFiles.get(k);
        }
        return result;
    }
    
    /**
     * Removed redundancy of return false statements--> cleaner and more readable code
     */
    @Override
    public boolean equals (Object other) {
        if (other != null && other instanceof Disk) {
            if (myId == ((Disk) other).myId) {
                return true;
            } 
        }
        return false;
    }

    @Override
    public int compareTo (Disk other) {
        if (other != null) {
            int result = other.freeSpace() - freeSpace();
            if (result == 0) {
                return myId - other.myId;
            } else {
                return result;
            }
        } else {
            return -1;
        }
    }
    
}
