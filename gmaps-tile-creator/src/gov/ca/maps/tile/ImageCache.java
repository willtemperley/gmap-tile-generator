package gov.ca.maps.tile;

import gov.ca.maps.tile.geom.GlobalMercator;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import javax.imageio.ImageIO;

/**
 * uses a simple image cache that holds buffered images upto a maximum size.
 * When that size is reached, it clears the buffer to disk Also when an image is
 * requested it checks the disk and loads the image from the disk into cache
 * 
 * @author nsandhu
 * 
 */
public class ImageCache {
	private final HashMap<String, BufferedImage> imageMap;
	private int cacheSize = 1000;
	private final FilenameMap filenameMap;

	/**
	 * @param directory
	 *            Uses this to hold the disk cache
	 * @param zoom
	 */
	public ImageCache(FilenameMap filenameMap) {
		this.filenameMap = filenameMap;
		imageMap = new HashMap<String, BufferedImage>(cacheSize);
	}

	/**
	 * 
	 * @param tx
	 * @param ty
	 * @return
	 */
	public BufferedImage getImage(int tx, int ty) {
		String key = tx + "_" + ty;
		if (imageMap.size() > cacheSize) {
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

	/**
	 * ensures that filename parent directory exists.
	 */
	protected void ensureDirectory() {
		File dir = new File(filenameMap.getDirectory());
		if (!dir.exists()) {
			dir.mkdirs();
		}
	}

	protected String getFilename(String key) {
		return filenameMap.getFilenameForKey(key);
	}

	public void saveImage(String key) {
		BufferedImage image = imageMap.get(key);
		ensureDirectory();
		String filename = getFilename(key);
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
			saveImage(key);
		}
		imageMap.clear();
	}

	public int getCacheSize() {
		return cacheSize;
	}

	public void setCacheSize(int cacheSize) {
		this.cacheSize = cacheSize;
	}

	public FilenameMap getFilenameMap() {
		return filenameMap;
	}
}
