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

import gov.ca.maps.tile.geom.GlobalMercator;
import gov.ca.maps.tile.renderer.TileRenderer;

import java.awt.image.BufferedImage;

public class TileCreator {

	private final int zoomLevel;
	private final GlobalMercator mercator;
	private final ImageCache cache;
	private final TileRenderer[] renderer;

	public TileCreator(String directory, int zoomLevel, TileRenderer[] renderer) {
		this.zoomLevel = zoomLevel;
		mercator = new GlobalMercator();
		cache = new ImageCache(new DirectoryZoomBasedFilenameMap(directory,
				zoomLevel));
		this.renderer = renderer;
	}

	public BufferedImage getImageFor(double lat, double lon) {
		int[] googleTile = mercator.GoogleTile(lat, lon, zoomLevel);
		return getImageForTileCoordinates(googleTile[0], googleTile[1]);
	}

	private double[] getLatLonBounds(double lat, double lon) {
		double[] meters = mercator.LatLonToMeters(lat, lon);
		int[] tile = mercator.MetersToTile(meters[0], meters[1], zoomLevel);
		return mercator.TileLatLonBounds(tile[0], tile[1], zoomLevel);
	}

	private BufferedImage getImageForTileCoordinates(int tx, int ty) {
		return cache.getImage(tx, ty);
	}

	public void renderData(double lat, double lon, String[] values) {
		for (TileRenderer element : renderer) {
			element.renderData(getImageFor(lat, lon),
					getLatLonBounds(lat, lon), lat, lon, values);
		}
	}

	public void saveAll() {
		cache.saveAllImages();
	}

}
