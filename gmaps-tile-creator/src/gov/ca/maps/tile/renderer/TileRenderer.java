package gov.ca.maps.tile.renderer;

import java.awt.image.BufferedImage;

public interface TileRenderer {
	/**
	 * Gives an opportunity for a renderer to render data at a lat, lon position
	 * onto the given image ( already selected to be appropriate tile image )
	 * 
	 * @param lat
	 * @param lon
	 * @param imageBounds
	 * @param latLonBounds
	 * @param data
	 */
	public void renderData(BufferedImage image, double[] latLonBounds,
			double lat, double lon, String[] valuesAtLatLon);
}
