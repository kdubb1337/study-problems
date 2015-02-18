package com.kdubb.studyproblems.problems.amazon;

import java.util.Comparator;

public class SpaceComparator implements Comparator<Space> {
	
	@Override
	public int compare(Space spaceA, Space spaceB) {
		// Consider edge cases first
		if(spaceA == null && spaceB == null) {
			return 0;
		}
		
		if(spaceA == null) {
			return -1;
		}
		
		if(spaceB == null) {
			return 1;
		}
		
		// We sort spaces entirely based on desirability
		return spaceA.getDesirability() - spaceB.getDesirability();
	}
}
