package edu.iastate.cs228.hw2;

/**
 *  
 * @author Paul Chihak
 *
 */

/**
 * 
 * This class executes four sorting algorithms: selection sort, insertion sort, mergesort, and
 * quicksort, over randomly generated integers as well integers from a file input. It compares the 
 * execution times of these algorithms on the same input. 
 *
 */

import java.io.IOException;
import java.util.Scanner;
import java.util.InputMismatchException;
import java.util.Random; 


public class CompareSorters 
{
	/**
	 * Repeatedly take integer sequences either randomly generated or read from files. 
	 * Perform the four sorting algorithms over each sequence of integers, comparing 
	 * points by x-coordinate or by polar angle with respect to the lowest point.  
	 * 
	 * @param args
	 * @throws InputMismatchException 
	 * @throws IOException 
	 **/
	public static void main(String[] args) throws InputMismatchException, IOException 
	{		
		// TODO 
		// 
		// Conducts multiple sorting rounds. In each round, performs the following: 
		// 
		//    a) If asked to sort random points, calls generateRandomPoints() to initialize an array 
		//       of random points. 
		//    b) Reassigns to elements in the array sorters[] (declared below) the references to the 
		//       four newly created objects of SelectionSort, InsertionSort, MergeSort and QuickSort. 
		//    c) Based on the input point order, carries out the four sorting algorithms in one for 
		//       loop that iterates over the array sorters[], to sort the randomly generated points
		//       or points from an input file.  
		//    d) Meanwhile, prints out the table of runtime statistics.
		// 
		// A sample scenario is given in Section 2 of the project description. 
		// 	
		AbstractSorter[] sorters = new AbstractSorter[4]; 
		Scanner scanner = new Scanner(System.in);
		Point[] points;
		int keys = 0, order = 0, trial = 1, numRand = 0;
		String fileInput;
		System.out.println("keys: 1 (random integers) 2 (file input) 3 (exit)");
		System.out.println("order: 1 (by x-coordinate) 2 (by polar angle)\n");
		while(keys != 3){
			System.out.print("\nTrial " + trial + ": ");
			keys = scanner.nextInt();
			if (keys == 1){
				System.out.print("Enter number of random points: ");
				numRand = scanner.nextInt();
				points = new Point[numRand];
				points = generateRandomPoints(numRand, new Random());
				sorters[0] = new SelectionSorter(points);
				sorters[1] = new InsertionSorter(points);
				sorters[2] = new MergeSorter(points);
				sorters[3] = new QuickSorter(points);
			}
			else if (keys == 2){
				System.out.println("Points from a file");
				System.out.print("File Name: ");
				fileInput = scanner.next();
				sorters[0] = new SelectionSorter(fileInput);
				sorters[1] = new InsertionSorter(fileInput);
				sorters[2] = new MergeSorter(fileInput);
				sorters[3] = new QuickSorter(fileInput);
			}
			else{
				break;
			}
			System.out.print("Order used in sorting: ");
			order = Integer.parseInt(scanner.next());
			System.out.println();
			System.out.println("algorithm\t" + "size\t" + "time (ns)\t");
			System.out.println("------------------------------------\n");
			
			for(int i = 0; i < 4; i++){
				sorters[i].sort(order);
				sorters[i].draw();
				sorters[i].writePointsToFile();
				System.out.println(sorters[i].stats());
			}
			System.out.println("------------------------------------\n");
			// Within a sorting round, have each sorter object call the sort and draw() methods
			// in the AbstractSorter class.  You can visualize the result of each sort. (Windows 
			// have to be closed manually before rerun.)  Also, print out the statistics table 
			// (cf. Section 2).
			trial++;
		}
		scanner.close();
	}

	
	/**
	 * This method generates a given number of random points to initialize randomPoints[].
	 * The coordinates of these points are pseudo-random numbers within the range 
	 * [-50,50] � [-50,50]. Please refer to Section 3 on how such points can be generated.
	 * 
	 * Ought to be private. Made public for testing. 
	 * 
	 * @param numPts  	number of points
	 * @param rand      Random object to allow seeding of the random number generator
	 * @throws IllegalArgumentException if numPts < 1
	 */
	public static Point[] generateRandomPoints(int numPts, Random rand) throws IllegalArgumentException
	{ 
		try{
			Point [] pts = new Point[numPts];
			int count = 0;
			while(count < numPts){
				pts[count] = new Point(rand.nextInt(101) - 50, rand.nextInt(101) - 50);
				count++;
			}
			return pts; 
		}
		catch(IllegalArgumentException e){
			throw e;
		}
	}
}
