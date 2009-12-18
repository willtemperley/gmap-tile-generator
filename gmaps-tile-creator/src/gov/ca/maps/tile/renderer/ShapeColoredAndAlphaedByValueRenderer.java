package gov.ca.maps.tile.renderer;


import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.util.HashMap;

public class ShapeColoredAndAlphaedByValueRenderer implements TileRenderer {
	// FIXME: specific to 38 degrees latitude...
	public static final double DEGREE_LAT_IN_FEET = 364160.86;
	public static final double DEGREE_LON_IN_FEET = 288163.56;
	public static final double LAT_WIDTH = 1000.0 / DEGREE_LAT_IN_FEET;
	public static final double LON_WIDTH = 1000.0 / DEGREE_LON_IN_FEET;
	private HashMap<Object, Object> hints;
	// value and colors in ascending order
	private double[] value1ColorBoundaries = new double[] { -100.0, -40.0,
			-30.0, -15.0, -10.0, -5.0, 0.0 };
	private Color[] value1Colors = new Color[] { Color.black, Color.blue,
			Color.cyan, Color.green, Color.yellow, Color.orange, Color.red };
	private double[] value2AlphaBoundaries = new double[] { 1925, 2010 };

	public ShapeColoredAndAlphaedByValueRenderer() {
		hints = new HashMap<Object, Object>();
		hints.put(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		hints.put(RenderingHints.KEY_ALPHA_INTERPOLATION,
				RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
		hints.put(RenderingHints.KEY_COLOR_RENDERING,
				RenderingHints.VALUE_COLOR_RENDER_QUALITY);
	}

	public void renderData(BufferedImage image, double[] latLonBounds,
			double lat, double lon, String[] valuesAtLatLon) {
		Graphics2D graphics = (Graphics2D) image.getGraphics();
		graphics.addRenderingHints(hints);
		double latOrigin = latLonBounds[0];
		double lonOrigin = latLonBounds[1];
		double latExtent = latLonBounds[2] - latLonBounds[0];
		double lonExtent = latLonBounds[3] - latLonBounds[1];
		double ly = 256 - (256 * (lat - latOrigin)) / latExtent;
		double lx = (256 * (lon - lonOrigin)) / lonExtent;
		double height = Math.max(3, LAT_WIDTH / latExtent);
		double width = Math.max(3, LON_WIDTH / lonExtent);
		Color colorForValue1 = getColorForValue1(Double
				.parseDouble(valuesAtLatLon[0]));
		int alpha = constrainToValidValues(getAlphaForValue2(Double
				.parseDouble(valuesAtLatLon[1])));
		Color color = new Color(colorForValue1.getRed(), colorForValue1
				.getGreen(), colorForValue1.getBlue(), alpha);
		graphics.setColor(color);
		graphics.fill(new Ellipse2D.Double(lx, ly, width, height));
	}

	private Color getColorForValue1(double value) {
		Color color = value1Colors[0];
		int i = 0;
		for (i = 0; i < value1ColorBoundaries.length; i++) {
			if (value < value1ColorBoundaries[i]) {
				break;
			}
		}
		if (i == 0) {
			color = value1Colors[0];
		} else if (i == value1ColorBoundaries.length) {
			color = value1Colors[i - 1];
		} else {
			Color color0 = value1Colors[i - 1];
			double value0 = value1ColorBoundaries[i - 1];
			Color color1 = value1Colors[i];
			double value1 = value1ColorBoundaries[i];
			double red = color0.getRed() + (color1.getRed() - color0.getRed())
					/ (value1 - value0) * (value - value0);
			double green = color0.getGreen()
					+ (color1.getGreen() - color0.getGreen())
					/ (value1 - value0) * (value - value0);
			double blue = color0.getBlue()
					+ (color1.getBlue() - color0.getBlue()) / (value1 - value0)
					* (value - value0);
			color = new Color(constrainToValidValues(red),
					constrainToValidValues(green), constrainToValidValues(blue));
		}
		return color;
	}

	private double getAlphaForValue2(double value) {
		return (255 - 0) * (value-value2AlphaBoundaries[0])
				/ (value2AlphaBoundaries[1] - value2AlphaBoundaries[0]);
	}

	private int constrainToValidValues(double d) {
		if (d < 0) {
			return 0;
		} else if (d > 255) {
			return 255;
		} else {
			return (int) Math.round(d);
		}
	}
}
