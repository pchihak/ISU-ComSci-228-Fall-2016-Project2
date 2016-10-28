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
 * This class implements the mergesort algorithm.   
 *
 */

public class MergeSorter extends AbstractSorter
{
	// Other private instance variables if you need ... 
	
	/**
	 * The two constructors below invoke their corresponding superclass constructors. They
	 * also set the instance variables algorithm and outputFileName in the superclass.
	 */

	/** 
	 * Constructor accepts an input array of points. 
	 * in the array. 
	 *  
	 * @param pts   input array of integers
	 */
	public MergeSorter(Point[] pts) 
	{
		super(pts);
		algorithm = "mergesort";
		outputFileName = "merge.txt";
	}
	
	
	/**
	 * Constructor reads points from a file. 
	 * 
	 * @param inputFileName  name of the input file
	 * @throws FileNotFoundException 
	 * @throws InputMismatchException 
	 */
	public MergeSorter(String inputFileName) throws InputMismatchException, FileNotFoundException 
	{
		super(inputFileName);
		algorithm = "mergesort";
		outputFileName = "merge.txt";
	}


	/**
	 * Perform mergesort on the array points[] of the parent class AbstractSorter. 
	 * 
	 * @param order  1   by x-coordinate 
	 * 			     2   by polar angle 
	 *
	 */
	@Override 
	public void sort(int order)
	{
		long startTime = System.nanoTime();
		setComparator(order);
		mergeSortRec(points);
		sortingTime = System.nanoTime() - startTime;
	}

	
	/**
	 * This is a recursive method that carries out mergesort on an array pts[] of points. One 
	 * way is to make copies of the two halves of pts[], recursively call mergeSort on them, 
	 * and merge the two sorted subarrays into pts[].   
	 * 
	 * @param pts	point array 
	 */
	private void mergeSortRec(Point[] pts)
	{
		if (pts.length == 1){
			return;
		}
		
		Point[] first = new Point[pts.length / 2];
		int i;
		Point[] second = new Point[pts.length - first.length];
		
		for (i = 0; i < first.length; i++){
			first[i] = pts[i];
		}
		for (int j = 0; j < second.length; j++){
			second[j] = pts[i];
			i++;
		}
		mergeSortRec(first);
		mergeSortRec(second);
		
		Point [] temp = merge(first, second);
		int k = 0;
		for (k = 0; k < temp.length; k++){
			pts[k] = temp[k];
		}
	}

	private Point[] merge (Point[] first, Point[] second){
		int firstIndex = 0, secondIndex = 0, mergeIndex = 0;
		Point[] result = new Point[first.length + second.length];
		while(firstIndex < first.length && secondIndex < second.length){
			if(pointComparator.compare(first[firstIndex], second[secondIndex]) <= 0){
				result[mergeIndex] = first[firstIndex];
				firstIndex++;
			}
			else{
				result[mergeIndex] = second[secondIndex];
				secondIndex++;
			}
			mergeIndex++;
		}
		if (firstIndex < first.length){
			for(int i = firstIndex; i < first.length; i++){
				result[mergeIndex] = first[i];
				mergeIndex++;
			}
		}
		else if (secondIndex < second.length){
			for(int i = secondIndex; i < second.length; i++){
				result[mergeIndex] = second[i];
				mergeIndex++;
			}
		}
		return result;
	}
	// Other private methods in case you need ...

}
