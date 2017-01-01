package org.pawars.algotrading.profile;

import java.util.ArrayList;
import java.util.List;

import org.pawars.algotrading.dto.Position;

public class Portfolio {
	private List<Position> positions = new ArrayList<Position>();
	private List<Position> rejectedPositions = new ArrayList<Position>();
	private List<Integer> lstPosIds = new ArrayList<Integer>();
	
	public Portfolio() {
		loadLstOfPosIds();
	}
	private void loadLstOfPosIds() {
		
	}
	public List<Position> getPositions() {
		return positions;
	}
	public boolean addPosition(Position posToBeAdded){
		if(validatePositionID(posToBeAdded.getPositionID())){
			positions.add(posToBeAdded);
			return true;
		}
		else{
			rejectedPositions.add(posToBeAdded);
			return false;
		}
	}
	private boolean validatePositionID(Integer posToBeAdded) {
		return !(lstPosIds.contains(posToBeAdded));
		
	}
	
}
