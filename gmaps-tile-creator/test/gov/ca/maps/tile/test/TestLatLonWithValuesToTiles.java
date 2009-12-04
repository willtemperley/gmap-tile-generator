package gov.ca.maps.tile.test;

import java.io.IOException;

import junit.framework.TestCase;
import gov.ca.maps.tile.LatLonWithValuesToTiles;

public class TestLatLonWithValuesToTiles extends TestCase{
	public void testSmallFile() throws IOException{
		String userHome = System.getProperty("user.home");
		String tmpDir = userHome+"/tmp";
		LatLonWithValuesToTiles tiler = new LatLonWithValuesToTiles(tmpDir+"/sample.csv");
		for(int i=0; i <= 16; i++){
			tiler.generateForZoom(tmpDir+"/sample-tiles", i);
		}
		/*
		for(int i=0; i< 10; i++){
			tiler.generateForZoom(i);
		}
		*/
	}
}
