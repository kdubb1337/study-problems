package com.kdubb.studyproblems.problems.amazon;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeSet;

/**
 * The SpaceAssigner is responsible for assigning a space for an incoming car to
 * park in. This is done by calling the assignSpace() API.
 *
 * The SpaceAssigner responds to changes in space availability by implementing
 * the GarageStatusListener interface.
 */
public class SpaceAssigner implements GarageStatusListener {
	
	// Map of parking spaces reserved for a car
	private Map<Car, Space> reservedSpaces;
	
	// Sorted set of available spaces with the most desirable in the last position
	private TreeSet<Space> availableSpaces;
	
	/**
	 * Initiates the SpaceAssigner. This method is called only once per app
	 * start-up.
	 * 
	 * @param garage
	 *            The parking garage for which you are vending spaces. Cannot be null
	 *
	 *  Time Complexity (both avg and worst case):  O(n log n) [loglinear]. TreeSet has O(log n) for each add operation and we perform n of those.
	 *  Space Complexity (both avg and worst case): O(n) [linear]. For each value added into TreeSet, only a linear amount of memory is allocated (no exponential linking)
	 */
	public void initialize(ParkingGarage garage) {
		availableSpaces = new TreeSet<>(new SpaceComparator());
		reservedSpaces = new HashMap<>();
		
		if(garage == null) {
			throw new IllegalArgumentException("Cannot initialize a SpaceAssigner without a garage");
		}
		
		Iterator<Space> spaces = garage.getSpaces();
		
		if(spaces == null) {
			throw new IllegalArgumentException("Cannot initialize a SpaceAssigner without any parking spaces");
		}
		
		// Pre-populate all spaces from the garage to our sorted set so it'll be faster later
		while(spaces.hasNext()) {
			Space space = spaces.next();
			
			// Ensure only valid parking spaces are considered
			if(space != null) {
				availableSpaces.add(space);
			}
		}
	}

	/**
	 * Assigns a space to an incoming car and returns that space.
	 * 
	 * @param car
	 *            The incoming car that needs a space. Cannot be null
	 * @returns The space reserved for the incoming car, or null if there are none available
	 *
	 *  Time Complexity (both avg and worst case):  O(log n) [logarithmic]. TreeSet and Hashmap perform these operations (remove, add, put) in log n average & worst time.
	 *  		Since we aren't looping or doing something worse, this is reduced by big-O to O(log n)
	 *  Space Complexity (both avg and worst case): O(1) [constant]. Generally no extra heapspace is allocated here as we are removing from availableSpaces and adding
	 *  		to reservedSpaces. Worst case is still O(1) with a reservedSpace being returned to availableSpaces but is not O(n) because it doesn't rely on the
	 *  		total number of cars or spaces in the system
	 */
	public Space assignSpace(Car car) {
		if(car == null) {
			throw new IllegalArgumentException("Cannot assign Space to a null Car");
		}
		
		// From question requirements '#4 If no spaces are available, a null value should be returned from assignSpace()." 
		if(availableSpaces.isEmpty()) {
			return null;
		}
		
		// If car already has reserved space, remove it. Not sure how they would enter twice
		// but better to double check
		Space reservedSpace = reservedSpaces.remove(car);
		
		if(reservedSpace != null) {
			// Return the reservedSpace to the availableSpaces
			availableSpaces.add(reservedSpace);
		}
		
		Space space = availableSpaces.pollLast();
		reservedSpaces.put(car, space);
		return space;
	}
	
	/**
	 * {@inheritDoc}
	 *
	 *  Time Complexity (both avg and worst case):  O(log n) [logarithmic]. TreeSet performs these operations (remove, add) in log n average & worst time.
	 *  		Since we aren't looping or doing something worse, this is reduced by big-O to O(log n)
	 *  Space Complexity (both avg and worst case): O(1) [constant]. Worst case is either an exception thrown or one extra memory unit returned.
	 *  		(since we enforce either reserved or avilable space be freed)
	 */
	public void onSpaceTaken(Car car, Space space) {
		// Handle invalid values with exceptions
		if(car == null) {
			throw new IllegalArgumentException("Cannot take a Space with a null Car");
		}
		
		if(space == null) {
			throw new IllegalArgumentException("Cannot take a null Space");
		}
		
		// Remove any reserved Spaces this car has
		Space reservedSpace = reservedSpaces.remove(car);
		
		// A space was reserved, but they parked in the wrong Space
		if(reservedSpace != null && reservedSpace.getID() != space.getID()) {
			// Make their reserved spot available
			availableSpaces.add(reservedSpace);
			
			// Take the Space they parked in away from availableSpaces
			removeSpaceFromAvailable(space);
		}
		// Somehow they just parked in a Space without any being reserved.
		// They either re-parked or entered the lot illegally.
		else if(reservedSpace == null) {
			// Take the Space they parked in away from availableSpaces
			removeSpaceFromAvailable(space);
		}

		// If they parked in the right Space no action is required. We already removed the Space from being reserved
	}
	
	/**
	 * Wrapper for removing a Space from the availableSpaces.
	 * 
	 * @param space
	 *            The space we want to remove from the available spaces
	 *
	 *  Time Complexity (both avg and worst case):  O(log n) [logarithmic]. TreeSet performs remove in log n time.
	 *  Space Complexity (both avg and worst case): O(1) [constant]. Worst case is either an exception thrown or one extra memory unit returned.
	 */
	private void removeSpaceFromAvailable(Space space) {
		boolean spaceWasAvailable = availableSpaces.remove(space);
		
		// If the space wasn't wasn't available, it shouldn't be taken
		if(!spaceWasAvailable) {
			throw new IllegalArgumentException("Space [" + space.getID() + "] was not available. Cannot be taken");
		}
	}

	/**
	 * {@inheritDoc}
	 *
	 *  Time Complexity (both avg and worst case):  O(log n) [logarithmic]. TreeSet performs add in log n time.
	 *  Space Complexity (both avg and worst case): O(1) [constant]. Worst case is either an exception thrown or one extra memory unit used.
	 */
	public void onSpaceFreed(Car car, Space space) {
		if(space == null) {
			throw new IllegalArgumentException("Cannot free a null Space");
		}
		
		// Make the Space available
		availableSpaces.add(space);
	}

	/**
	 * {@inheritDoc}
	 * 
	 *  Time Complexity (both avg and worst case):  O(log n) [logarithmic]. TreeSet and Hashmap perform these operations (remove, add) in log n time.
	 *  Space Complexity (both avg and worst case): O(1) [constant]. Worst case is one unit returned from reserved and one used by availableSpaces.
	 *  		Does not depend on number of cars or spaces in system.
	 */
	public void onGarageExit(Car car) {
		// Remove any reserved Spaces this car has
		Space space = reservedSpaces.remove(car);
		
		if(space != null) {
			// Return the reserved Space to the availableSpaces
			availableSpaces.add(space);
		}
	}
}