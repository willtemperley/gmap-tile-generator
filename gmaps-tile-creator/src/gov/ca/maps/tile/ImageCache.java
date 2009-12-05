package gov.ca.maps.tile;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import javax.imageio.ImageIO;

public class ImageCache {
	private HashMap<String, BufferedImage> imageMap;
	private int zoom;
	private String directory;

	public ImageCache(String directory, int zoom) {
	this.directory = directory;
		this.zoom = zoom;
		imageMap = new HashMap<String, BufferedImage>(1000);
	}

	public BufferedImage getImage(int tx, int ty) {
		String key = tx + "_" + ty;
		if (imageMap.size() > 1000){
			System.out.println("Clearing the image cache");
			saveAllImages();
		}
		BufferedImage bufferedImage = imageMap.get(key);
		if (bufferedImage == null) {
			bufferedImage = createOrLoadImage(key);
			imageMap.put(key, bufferedImage);
		}
		return bufferedImage;
	}

	private BufferedImage createOrLoadImage(String key) {
		BufferedImage bufferedImage = loadImage(key);
		if (bufferedImage == null) {
			bufferedImage = new BufferedImage(GlobalMercator.TILE_SIZE,
					GlobalMercator.TILE_SIZE, BufferedImage.TYPE_INT_ARGB);
		}
		return bufferedImage;
	}

	protected void ensureDirectory() {
		File dir = new File(directory);
		if (!dir.exists()) {
			dir.mkdirs();
		}
	}

	protected String getFilename(String key) {
		return directory + "/tile" + key + "_" + this.zoom + ".png";
	}

	public void saveImage(String directory, String key) {
		BufferedImage image = imageMap.get(key);
		File dir = new File(directory);
		if (!dir.exists()) {
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

	protected BufferedImage loadImage(String key) {
		ensureDirectory();
		try {
			String filename = getFilename(key);
			return ImageIO.read(new File(filename));
		} catch (IOException e) {
			return null;
		}
	}

	public void saveAllImages() {
		for (String key : imageMap.keySet()) {
			saveImage(directory, key);
		}
		imageMap.clear();
	}
}
