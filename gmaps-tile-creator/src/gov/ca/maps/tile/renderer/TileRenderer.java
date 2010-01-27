/**
 *    Copyright (C) 2009, 2010 
 *    State of California,
 *    Department of Water Resources.
 *    This file is part of DSM2 Grid Map
 *    The DSM2 Grid Map is free software: 
 *    you can redistribute it and/or modify
 *    it under the terms of the GNU General Public License as published by
 *    the Free Software Foundation, either version 3 of the License, or
 *    (at your option) any later version.
 *    DSM2 Grid Map is distributed in the hope that it will be useful,
 *    but WITHOUT ANY WARRANTY; without even the implied warranty of
 *    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *    GNU General Public License for more details. [http://www.gnu.org/licenses]
 *    
 *    @author Nicky Sandhu
 *    
 */
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
