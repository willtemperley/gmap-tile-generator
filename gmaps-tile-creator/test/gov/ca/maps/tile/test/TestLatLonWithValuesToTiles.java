package gov.ca.maps.tile.test;

import java.io.IOException;

import junit.framework.TestCase;
import gov.ca.maps.tile.LatLonWithValuesToTiles;

public class TestLatLonWithValuesToTiles extends TestCase{
	public void testSmallFile() throws IOException{
		String userHome = System.getProperty("user.home");
		String tmpDir = userHome+"/tmp";
		LatLonWithValuesToTiles tiler = new LatLonWithValuesToTiles(tmpDir+"/sample.csv");
		for(int i=16; i > 3; i--){
			System.out.println("Generating zoom level: "+i);
			tiler.generateForZoom(tmpDir+"/sample-tiles-colored", i);
		}
	}
}
