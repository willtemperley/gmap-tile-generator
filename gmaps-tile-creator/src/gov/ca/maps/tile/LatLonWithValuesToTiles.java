package gov.ca.maps.tile;

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
	private String file;

	/**
	 * @throws IOException
	 * 
	 */
	public LatLonWithValuesToTiles(String file) {
		this.file = file;
	}

	public void generateForZoom(String directory, int zoomLevel) throws IOException {
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
