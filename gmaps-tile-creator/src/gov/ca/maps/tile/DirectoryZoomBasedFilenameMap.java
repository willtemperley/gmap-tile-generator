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
