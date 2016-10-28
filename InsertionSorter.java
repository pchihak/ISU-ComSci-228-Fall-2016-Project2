package edu.iastate.cs228.hw2;

import java.io.FileNotFoundException;
import java.lang.NumberFormatException;
import java.util.InputMismatchException;
import java.lang.IllegalArgumentException; 


/**
 *  
 * @author Paul Chihak
 *
 */

/**
 * 
 * This class implements insertion sort.   
 *
 */

public class InsertionSorter extends AbstractSorter 
{
	// Other private instance variables if you need ... 
	/**
	 * The two constructors below invoke their corresponding superclass constructors. They
	 * also set the instance variables algorithm and outputFileName in the superclass.
	 */

	/**
	 * Constructor takes an array of points. 
	 * 
	 * @param pts  
	 */
	public InsertionSorter(Point[] pts) 
	{
		super(pts);
		algorithm = "insertion sort";
		outputFileName = "insert.txt";
	}	

	
	/**
	 * Constructor reads points from a file. 
	 * 
	 * @param inputFileName  name of the input file
	 * @throws FileNotFoundException 
	 * @throws InputMismatchException 
	 */
	public InsertionSorter(String inputFileName) throws InputMismatchException, FileNotFoundException 
	{
		super(inputFileName);
		algorithm = "insertion sort";
		outputFileName = "insert.txt";
	}
	
	
	/** 
	 * Perform insertion sort on the array points[] of the parent class AbstractSorter.  
	 * 
	 * @param order  1   by x-coordinate 
	 * 			     2   by polar angle 
	 */
	@Override 
	public void sort(int order)
	{
		long startTime = System.nanoTime();
		setComparator(order);
		for(int i = 1; i < points.length; i++){
			Point temp = points[i];
			int j;
			for (j = i - 1; j > -1 && pointComparator.compare(points[j], temp) > 0; j--){
				points[j + 1] = points[j];
			}
			points[j + 1] = temp;
		}
		sortingTime = System.nanoTime() - startTime;
	}
}
