package org.pawars.algotrading.profile;

import java.util.ArrayList;
import java.util.List;

import org.pawars.algotrading.dto.Position;

public class Portfolio {
	private List<Position> positions = new ArrayList<Position>();
	
	public List<Position> getPositions() {
		return positions;
	}
	
}
