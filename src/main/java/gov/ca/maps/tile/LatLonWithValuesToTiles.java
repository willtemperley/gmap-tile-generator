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
package gov.ca.maps.tile;

import gov.ca.maps.tile.renderer.ShapeColoredAndAlphaedByValueRenderer;
import gov.ca.maps.tile.renderer.TileRenderer;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Reads a csv file with lat, lon and two values and converts the stream being
 * read into a tiles compatible with use with google maps
 * 
 * @author karajdaar
 * 
 */
public class LatLonWithValuesToTiles {
	private final String file;

	/**
	 * @throws IOException
	 * 
	 */
	public LatLonWithValuesToTiles(String file) {
		this.file = file;
	}

	public void generateForZoom(String directory, int zoomLevel)
			throws IOException {
		// FIXME: check for sane min/max zoom levels, existence of file
		TileCreator tileCreator = new TileCreator(
				directory,
				zoomLevel,
				new TileRenderer[] { new ShapeColoredAndAlphaedByValueRenderer() {
				} });
		BufferedReader reader = new BufferedReader(new FileReader(file));
		String line = reader.readLine();
		String[] values = new String[2];
		while ((line = reader.readLine()) != null) {
			String[] fields = line.split(",");
			// FIXME: check for sane lat,lon,color,alpha values
			if (fields.length <= 4) {
				System.err
						.println("Line: "
								+ line
								+ " has less than required 4 fields: lat, lon, color, alpha");
				continue;
			}
			double lat = Double.parseDouble(fields[0]);
			double lon = Double.parseDouble(fields[1]);
			values[0] = fields[2];
			values[1] = fields[3];
			tileCreator.renderData(lat, lon, values);
		}
		reader.close();
		tileCreator.saveAll();
	}
}
