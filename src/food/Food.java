package food;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Random;

import graphics.IDrawable;
import graphics.ZooPanel;
import mobility.Ilocatable;
import mobility.Point;
import utilities.MessageUtility;
import utilities.Utils;

public abstract class Food implements IEdible, Ilocatable, IDrawable {
	/**
	 * height
	 */
	private double height;
	/**
	 * location
	 */
	private Point location;
	/**
	 * weight
	 */
	private double weight;
	private BufferedImage img;
	private ZooPanel pan;

	public Food() {
		Random rand = new Random();
		int x = rand.nextInt(30);
		int y = rand.nextInt(12);
		this.location = new Point(x, y);
		this.height = rand.nextInt(30);
		this.weight = rand.nextInt(12);
		loadImages(PICTURE_PATH);
	}
	/**
	 * @return
	 */
	public double getHeight() {
		MessageUtility.logGetter(this.getClass().getSimpleName(), "getHeight", this.height);
		return this.height;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see mobility.ILocatable#getLocation()
	 */
	@Override
	public Point getLocation() {
		MessageUtility.logGetter(this.getClass().getSimpleName(), "getLocation", this.location);
		return this.location;
	}

	/**
	 * @return weight
	 */
	public double getWeight() {
		MessageUtility.logGetter(this.getClass().getSimpleName(), "getWeight", this.weight);
		return weight;
	}

	/**
	 * @param height
	 * @return heght
	 */
	public boolean setHeight(double height) {

		boolean isSuccess = (height >= 0);
		if (isSuccess) {
			this.height = height;
		} else {
			this.height = 0;
		}
		MessageUtility.logSetter(this.getClass().getSimpleName(), "setHeight", height, isSuccess);
		return isSuccess;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see mobility.ILocatable#setLocation(mobility.Point)
	 */
	@Override
	public boolean setLocation(Point newLocation) {
		boolean isSuccess = Point.checkBoundaries(newLocation.getX(), newLocation.getY());
		if (isSuccess) {
			this.location = newLocation;
		}
		MessageUtility.logSetter(this.getClass().getSimpleName(), "setLocation", newLocation, isSuccess);
		return isSuccess;
	}

	/**
	 * @param weight
	 * @return weight
	 */
	public boolean setWeight(double weight) {
		boolean isSuccess = (weight >= 0);
		if (isSuccess) {
			this.weight = weight;
		} else {
			this.weight = 0;
		}
		MessageUtility.logSetter(this.getClass().getSimpleName(), "setWeight", weight, isSuccess);

		return isSuccess;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "[" + this.getClass().getSimpleName() + "] ";
	}

	/**
	 * load images
	 * @param nm - name of the picture
	 */
	@Override
	public void loadImages(String nm) {
		String imgFileName = getImageName();
		img = Utils.getImage(nm + imgFileName);
	}

	protected abstract String getImageName();

	@Override
	public void drawObject(Graphics g) {
		Point location = getLocation();
		int size = 50; // the size of the food
		g.drawImage(img, location.getX()-size/2, location.getY()-size/10, size/2, size, pan);
	}

	public void setPan(ZooPanel pan) {
		this.pan = pan;
	}

	@Override
	public String getColor() {
		return ""; // plant has no color
	}
}
