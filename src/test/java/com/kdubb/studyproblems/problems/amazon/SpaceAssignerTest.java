package com.kdubb.studyproblems.problems.amazon;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class SpaceAssignerTest {

	private int numParkingSpaces;
	private Car[] cars;
	private SpaceAssigner spaceAssigner;
	
	@Before
	public void canInitialize() {
		numParkingSpaces = 10;
		spaceAssigner = new SpaceAssigner();
		spaceAssigner.initialize(new ParkingGarageImpl(numParkingSpaces));
		
		// Create some test cars
		cars = new Car[]{
				new CarImpl("AZ", "ABC 1234"),
				new CarImpl("NY", "CBA 4321"),
				new CarImpl("NJ", "BZKT1"),
				new CarImpl("AZ", "EEEE 111")
		};
	}
	
	@Test
	public void canAssignSpace() {
		Space space1 = spaceAssigner.assignSpace(cars[0]);
		assertEquals(9, space1.getDesirability());
		
		Space space2 = spaceAssigner.assignSpace(cars[1]);
		assertEquals(8, space2.getDesirability());
		
		Space space3 = spaceAssigner.assignSpace(cars[2]);
		assertEquals(7, space3.getDesirability());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void canAssignSpaceNegative() {
		// Ensure we handle invalid input with an exception
		spaceAssigner.assignSpace(null);
	}
	
	@Test
	public void canFreeAssignedSpace() {
		Space space1 = spaceAssigner.assignSpace(cars[0]);
		assertEquals(9, space1.getDesirability());
		
		Space space2 = spaceAssigner.assignSpace(cars[1]);
		assertEquals(8, space2.getDesirability());
		
		// Free space1 by exiting instead of parking
		spaceAssigner.onGarageExit(cars[0]);
		
		Space space3 = spaceAssigner.assignSpace(cars[2]);
		assertEquals(9, space3.getDesirability());
		
		// Car 0 returns but gets assigned a less desirable space
		Space space4 = spaceAssigner.assignSpace(cars[0]);
		assertEquals(7, space4.getDesirability());
	}
	
	@Test
	public void canConsumeAllFreeSpaces() {
		// Ensure we can't reserve many spaces for the same car
		for(int i = 0; i < 5; i++) {
			Space space = spaceAssigner.assignSpace(cars[0]);
			assertEquals(9, space.getDesirability());
		}
		
		// Consume remaining spaces
		for(int i = 0; i < numParkingSpaces - 1; i++) {
			String uniqueStr = Integer.toString(i);
			Space space = spaceAssigner.assignSpace(new CarImpl(uniqueStr, uniqueStr));
			assertEquals(numParkingSpaces - i - 2, space.getDesirability());
		}
		
		// Ensure when the lot is full we get a null space back
		Space space = spaceAssigner.assignSpace(cars[1]);
		assertNull(space);
	}
	
	@Test
	public void canHandleCarParkingInTheWrongSpace() {
		Space space1 = spaceAssigner.assignSpace(cars[0]);
		assertEquals(9, space1.getDesirability());

		// First car takes the wrong space
		spaceAssigner.onSpaceTaken(cars[0], new SpaceImpl(8));
		
		Space space2 = spaceAssigner.assignSpace(cars[1]);
		assertEquals(9, space2.getDesirability());
		
		// Ensure the wrong space taken above is removed from availableSpaces
		Space space3 = spaceAssigner.assignSpace(cars[2]);
		assertEquals(7, space3.getDesirability());
	}
	
	@Test
	public void canHandleCarParkingWithoutReservation() {
		// Car takes a space without a one assigned
		spaceAssigner.onSpaceTaken(cars[0], new SpaceImpl(8));
		
		Space space2 = spaceAssigner.assignSpace(cars[1]);
		assertEquals(9, space2.getDesirability());
		
		// Ensure the wrong space taken above is removed from availableSpaces
		Space space3 = spaceAssigner.assignSpace(cars[2]);
		assertEquals(7, space3.getDesirability());
	}
	
	@Test
	public void canHandleCarsReparking() {
		Space space1 = spaceAssigner.assignSpace(cars[0]);
		assertEquals(9, space1.getDesirability());

		// First car takes the right space
		spaceAssigner.onSpaceTaken(cars[0], space1);
		
		// Second car enters
		Space space2 = spaceAssigner.assignSpace(cars[1]);
		assertEquals(8, space2.getDesirability());
		
		// First car reparks
		spaceAssigner.onSpaceFreed(cars[0], space1);
		spaceAssigner.onSpaceTaken(cars[0], new SpaceImpl(7));
		
		// Third car enters and first's original spot is now available
		Space space3 = spaceAssigner.assignSpace(cars[2]);
		assertEquals(9, space3.getDesirability());
		
		// Fourth car enters and the reparked space is not available
		Space space4 = spaceAssigner.assignSpace(cars[3]);
		assertEquals(6, space4.getDesirability());
	}
	
	private class CarImpl implements Car {

		private String licensePlateState;
		private String licensePlateNumber;
		
		private CarImpl(String licensePlateState, String licensePlateNumber) {
			this.licensePlateState = licensePlateState;
			this.licensePlateNumber = licensePlateNumber;
		}
		
		@Override
		public String getLicensePlateState() {
			return licensePlateState;
		}

		@Override
		public String getLicensePlateNumber() {
			return licensePlateNumber;
		}
		
		@Override
		public int hashCode() {
		    final int prime = 31;
		    int result = 1;
		    result = prime * result + (licensePlateState == null ? 0 : licensePlateState.hashCode());
		    result = prime * result + (licensePlateNumber == null ? 0 : licensePlateNumber.hashCode());
		    return result;
		}
		
		@Override
		public boolean equals(Object obj) {
			if(obj == null || !obj.getClass().equals(CarImpl.class)) {
				return false;
			}
			
			CarImpl otherCar = (CarImpl)obj;
			
			// Protect against nulls
			if(licensePlateState == null) {
				if(otherCar.licensePlateState != null) {
					return false;
				}
			}
			else if(!licensePlateState.equals(otherCar.licensePlateState)) {
				return false;
			}
			
			if(licensePlateNumber == null) {
				if(otherCar.licensePlateNumber != null) {
					return false;
				}
			}
			else if(!licensePlateNumber.equals(otherCar.licensePlateNumber)) {
				return false;
			}
			
			return true;
		}
	}
	
	private class ParkingGarageImpl implements ParkingGarage {

		private int numSpaces;
		
		private ParkingGarageImpl(int numSpaces) {
			this.numSpaces = numSpaces;
		}
		
		@Override
		public void register(GarageStatusListener assigner) {
			// Not used within SpaceComparator. Real version would have an implmentation here
		}

		@Override
		public Iterator<Space> getSpaces() {
			List<Space> spaces = new ArrayList<>();
			
			for(int i = 0; i < numSpaces; i++) {
				spaces.add(new SpaceImpl(i));
			}
			
			return spaces.iterator();
		}
	}
	
	private class SpaceImpl implements Space {

		private int id;
		private int desirability;
		
		private SpaceImpl(int desirability) {
			this.id = desirability;
			this.desirability = desirability;
		}
		
		@Override
		public int getID() {
			return id;
		}

		@Override
		public int getDesirability() {
			return desirability;
		}

		@Override
		public boolean isOccupied() {
			// Not used within SpaceComparator. Real version would have an implmentation here
			return false;
		}

		@Override
		public Car getOccupyingCar() {
			// Not used within SpaceComparator. Real version would have an implmentation here
			return null;
		}
		
		@Override
		public boolean equals(Object obj) {
			if(obj == null || !obj.getClass().equals(SpaceImpl.class)) {
				return false;
			}
			
			SpaceImpl otherSpace = (SpaceImpl)obj;
			return id == otherSpace.id;
		}
	}
}