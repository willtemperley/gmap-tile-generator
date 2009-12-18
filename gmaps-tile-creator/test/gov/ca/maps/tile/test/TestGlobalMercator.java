package gov.ca.maps.tile.test;

import gov.ca.maps.tile.geom.GlobalMercator;
import junit.framework.TestCase;

public class TestGlobalMercator extends TestCase{
	
	public void testPixelExtents(){
		GlobalMercator mercator = new GlobalMercator();
		double [] meters = mercator.LatLonToMeters(-85, -180);
		int[] pixels = mercator.MetersToPixels(meters[0], meters[1], 0);
		assertEquals(0, pixels[0]);
		assertEquals(0, pixels[1]);
		meters = mercator.LatLonToMeters(+85, +180);
		pixels = mercator.MetersToPixels(meters[0], meters[1], 0);
		assertEquals(256, pixels[0]);
		assertEquals(256, pixels[1]);
	}
	
	public void testTMSToGoogleCoordinates(){
		GlobalMercator mercator = new GlobalMercator();
		int[] googleTile = mercator.GoogleTile(0, 0, 0);
		assertEquals(0, googleTile[0]);
		assertEquals(0, googleTile[1]);
		googleTile = mercator.GoogleTile(0, 0, 1);
		assertEquals(0, googleTile[0]);
		assertEquals(1, googleTile[1]);
		googleTile = mercator.GoogleTile(1, 1, 1);
		assertEquals(1, googleTile[0]);
		assertEquals(0, googleTile[1]);
	}
}
