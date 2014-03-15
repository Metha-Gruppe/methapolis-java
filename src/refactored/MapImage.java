package refactored;

import java.awt.Image;

public class MapImage {
	// CONSTANTS
	public static final short TINY = 0;
	public static final short SMALL = 1;
	public static final short MEDIUM = 2;
	public static final short BIG = 3;
	public static final short LARGE = 4;
	public static final short ZOOMS = 5; // implies array length;
	
	// ATTRIBUTES
	private short zoom;
	private Image[] images;
	
	
	// CONSTRUCTORS
	public MapImage(Image[] images)	{
		this(images, TINY);
	}
	public MapImage(Image[] images, short zoom)	{
		this.zoom = zoom;
		this.images = images;
	}
	
	// METHODS
	public Image getImage()	{
		return getImageForZoom(zoom);
	}
	
	public Image getImageForZoom(short zoom)	{
		if(zoom >= 0 && zoom < images.length)	{
			return images[zoom];			
		}
		else	{
			return null;
		}
	}

	// GETTERS & SETTERS
	public short getZoom() {
		return zoom;
	}

	public void setZoom(short zoom) {
		this.zoom = zoom;
	}

	public Image[] getImages() {
		return images;
	}

	public void setImages(Image[] images) {
		this.images = images;
	}
	
}
