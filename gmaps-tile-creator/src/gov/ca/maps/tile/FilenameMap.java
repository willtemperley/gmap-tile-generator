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
