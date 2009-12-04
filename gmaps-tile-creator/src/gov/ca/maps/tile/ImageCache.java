package gov.ca.maps.tile;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import javax.imageio.ImageIO;

public class ImageCache {
	private HashMap<String, BufferedImage> imageMap;
	private int zoom;

	public ImageCache(int zoom) {
		this.zoom = zoom;
		imageMap = new HashMap<String, BufferedImage>(1000);
	}

	public BufferedImage getImage(int tx, int ty) {
		String key = tx + "_" + ty;
		BufferedImage bufferedImage = imageMap.get(key);
		if (bufferedImage == null) {
			bufferedImage = new BufferedImage(GlobalMercator.TILE_SIZE,
					GlobalMercator.TILE_SIZE, BufferedImage.TYPE_INT_ARGB);
			imageMap.put(key, bufferedImage);
		}
		return bufferedImage;
	}

	public void saveImage(String directory, String key) {
		BufferedImage image = imageMap.get(key);
		File dir = new File(directory);
		if (!dir.exists()){
			dir.mkdirs();
		}
		String filename = directory + "/tile" + key + "_" + this.zoom + ".png";
		try {
			boolean write = ImageIO.write(image, "png", new File(filename));
			if (!write) {
				System.err.println("Could not save image: " + filename
						+ "! It seems a png writer doesn't exist");
				throw new RuntimeException(
						"Could not save image as png writer doesn't exist?");
			}
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	public void saveAllImages(String directory) {
		for (String key : imageMap.keySet()) {
			saveImage(directory, key);
		}
	}
}
