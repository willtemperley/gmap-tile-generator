package gov.ca.maps.tile.test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import junit.framework.TestCase;
import gov.ca.maps.tile.LatLonWithValuesToTiles;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

public class TestLatLonWithValuesToTiles {


	@Rule
	public TemporaryFolder folder = new TemporaryFolder();

	@Test
	public void testSmallFile() throws IOException{
		LatLonWithValuesToTiles tiler = new LatLonWithValuesToTiles("src/test/resources/sample.csv");
		for(int i=16; i > 3; i--){
			System.out.println("Generating zoom level: "+i);
			tiler.generateForZoom(folder+"/sample-tiles-colored", i);
		}
	}
}
