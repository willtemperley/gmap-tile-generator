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

/**
 * Maps keys to filepath. Note: A major assumption is that all files are in the
 * same directory.
 * 
 * @author nsandhu
 * 
 */
public interface FilenameMap {
	/**
	 * Returns the filename for the key. Its as
	 * 
	 * @param key
	 * @return the filename for the given key
	 */
	String getFilenameForKey(String key);

	/**
	 * The directory in which all these files are to placed.
	 * 
	 * @return
	 */
	String getDirectory();

}
