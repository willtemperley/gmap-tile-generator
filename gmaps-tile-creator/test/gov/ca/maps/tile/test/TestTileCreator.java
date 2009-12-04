package gov.ca.maps.tile.test;

import java.awt.Color;

import junit.framework.TestCase;

import gov.ca.maps.tile.TileCreator;
import gov.ca.maps.tile.TileRenderer;
import gov.ca.maps.tile.ValueAsColoredShapeRenderer;

public class TestTileCreator extends TestCase {
	public void testSimple(){
		TileRenderer[] renderer = new TileRenderer[]{new ValueAsColoredShapeRenderer(Color.blue)};
		TileCreator tileCreator = new TileCreator(19, renderer);
		//POINT(-121.8693691690693 38.03132044904403),-14.63,1950,NOAA
		//POINT(-121.86956919269622 38.03122044206013),-0.63,1950,NOAA
		//POINT(-121.8692692435647 38.03152046504538),-24.63,1950,NOAA
		tileCreator.renderData(38.03132044904403, -121.8693691690693, new String[]{"10", "1950"});
		tileCreator.renderData(38.03122044206013, -121.86956919269622, new String[]{"15", "2000"});
		tileCreator.renderData(38.03152046504538, -121.8692692435647, new String[]{"25", "1980"});
		tileCreator.saveAll();
	}
}
