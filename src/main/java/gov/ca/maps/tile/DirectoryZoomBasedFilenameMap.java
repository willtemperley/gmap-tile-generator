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

public class DirectoryZoomBasedFilenameMap implements FilenameMap {

	private final String directory;
	private final int zoomLevel;

	public DirectoryZoomBasedFilenameMap(String directory, int zoomLevel) {
		this.directory = directory;
		this.zoomLevel = zoomLevel;
	}

	public String getFilenameForKey(String key) {
		return directory + "/tile" + key + "_" + zoomLevel + ".png";
	}

	public String getDirectory() {
		return directory;
	}

}
