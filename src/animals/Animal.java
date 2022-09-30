package animals;


import java.awt.Graphics;
import java.awt.image.BufferedImage;

import diet.IDiet;
import food.EFoodType;
import food.Food;
import food.IEdible;
import graphics.IAnimalBehavior;
import graphics.IDrawable;
import graphics.ZooFrame;
import graphics.ZooPanel;
import mobility.Mobile;
import mobility.Point;
import observer.Observable;
import observer.Observer;
import utilities.MessageUtility;
import utilities.Utils;

/**
 * This class contains functions that manipulate different animals
 * and draw them on the zooFrame panel
 *
 * @author - Leah Brodsky
 * @author - Ziv Gimani
 *

 *
 * @see  food.IEdible
 * @see mobility.Mobile
 * @see mobility.Point
 * @see utilities.MessageUtility
 * @see graphics.IAnimalBehavior
 * @see graphics.IDrawable
 */

public abstract class Animal extends Mobile implements IEdible, IDrawable, IAnimalBehavior,Runnable, Observable {

	/**
	 * name of the animal
	 */
    private String name;
	/**
	 * weight of the animal
	 */
    private double weight;
	/**
	 * diet of the animal: carnivore,omnivore or herbivore
	 */
    private IDiet diet;
	private final int EAT_DISTANCE = 10;
	//size of the animal
	private int size;
	//animal color
	private String col;
	//horizontal speed
	private int horSpeed;
	//vertical speed
	private int verSpeed;
	//if changed coordinates
	private boolean coordChanged;
	protected boolean threadSuspended;
	//move direction x,y
	private int x_dir = 1;
	private int y_dir = 1;
	//number of objects eaten
	private int eatCount;
	private ZooPanel pan;
	//images right, left
	private BufferedImage img1, img2;
	private boolean stopped;
	private Observer observer;

	/**
	 * set the observer
	 * @param observer - who will observ the animals
	 */
	public void setObserver(Observer observer) {
		this.observer = observer;
	}

	/**
	 * Class constructor
	 * @param name
	 * @param location
	 * @see utilities.MessageUtility#logConstractor(String className, String name)
	 */

	public Animal(String name, Point location) {
    	super(location);
    	this.name = name;
		MessageUtility.logConstractor("Animal",getName());
		loadImages(PICTURE_PATH);
	}

	/**
	 * abstract function what sound animal makes
	 */
	
    public abstract void makeSound();

	/**
	 * 
	 * @param food
	 * @see IEdible#getFoodtype()
	 * @see utilities.MessageUtility#logSetter(String name, String funcName, Object food, boolean)
	 * @return boolean if the animal can eat the food
	 */
    public boolean eat(IEdible food) {
    	if ((diet != null) && diet.canEat(food.getFoodtype())){
    		MessageUtility.logBooleanFunction(getName(), "eat", food, true);
    		double w = getWeight();
			w += diet.eat(this,food);
			setWeight(w);
			this.makeSound();
			eatInc();
    		return true;
    	}
		MessageUtility.logBooleanFunction(getName(), "eat", food, false);
		return false;
    }

	/**
	 * Function that get the food type
	 * @see EFoodType
	 * @return EFoodType
	 */
    public  EFoodType getFoodtype(){
		MessageUtility.logGetter(getName(), "getFoodType", EFoodType.MEAT);
		return EFoodType.MEAT;
	};

	/**
	 * Function that get name of the animal
	 * @return name
	 */
	public String getName() {
		return name;
	}
	/**
	 * Function that get weight of the animal
	 * @see MessageUtility#logGetter(String name, String funcName, Object weight)
	 * @return weight
	 */
	public double getWeight() {
		MessageUtility.logGetter(getName(),"getWeight",weight);
		return weight;
	}

	/**
	 *
	 * @param weight  weight of the animal
	 * @see utilities.MessageUtility#logSetter(String name, String funcName, Object weight, boolean)
	 * @return boolean
	 */
	public boolean setWeight(double weight) {
		if (weight < 0)
		{
			MessageUtility.logSetter(getName(),"setWeight",weight,false);
			return false;
		}
		this.weight = weight;
		MessageUtility.logSetter(getName(),"setWeight",weight,true);
		return true;
	}

	/**
	 *
	 * @param p point
	 * @see MessageUtility#logBooleanFunction(String name, String funcName, Object p, boolean)
	 * @return distance that animal moved
	 */
    public double move(Point p) {
    	double distance = super.move(p);
    	double w = getWeight();
    	w = w - (distance * w * 0.00025);
    	setWeight(w);
    	MessageUtility.logBooleanFunction(getName(), "move", p, distance != 0);
    	coordChanged = true;
    	return distance;
    }

	/**
	 *
	 * @return diet
	 */
	public IDiet getDiet() {
		return diet;
	}

	/**
	 *
	 * @param diet
	 * @see utilities.MessageUtility#logSetter(String name, String funcName, Object diet, boolean)
	 * @return boolean
	 */

	public boolean setDiet(IDiet diet) {
		this.diet = diet;
		MessageUtility.logSetter(getName(),"setDiet",diet,true);
		return true;
	}

	/**
	 * @param name
	 * @see utilities.MessageUtility#logSetter(String name, String funcName, Object name, boolean)
	 * @return
	 */
	public boolean setName(String name) {

		this.name = name;
		MessageUtility.logSetter(getName(),"setName",name,true);
		return true;

	}
	//get images right and left
	protected abstract String getRightMoveImageName();
	protected abstract String getLeftMoveImageName();
	public void loadImages(String nm) {
		String img1FileName = getRightMoveImageName();
		String img2FileName = getLeftMoveImageName();
		img1 = Utils.getImage(nm + img1FileName);
		img2 = Utils.getImage(nm + img2FileName);
	}
	public void drawObject (Graphics g) {
		Point location = getLocation();
		if(x_dir==1) // animal goes to the right side
			g.drawImage(img1, location.getX()-size/2, location.getY()-size/10, size/2, size, pan);
		else // animal goes to the left side
			g.drawImage(img2, location.getX(), location.getY()-size/10, size/2, size, pan);
	}

	/**
	 *
	 * @return animal color
	 */
	public String getColor() {
		return col;
	}

	/**
	 * set animal color, if it is changed load other image
	 * @param col - animal color
	 */
	public void setCol(String col) {
		boolean changed = !col.equals(this.col);
		this.col = col;
		if (changed)
			loadImages(PICTURE_PATH);
	}

	/**
	 *
	 * @return string
	 */
	public String toString() {
		return "[" + this.getClass().getSimpleName() + "] " + getName();
	}
	
    public void setPan(ZooPanel pan) {
		this.pan = pan;
	}

	/**
	 *
	 * @return animal name
	 */
	public String getAnimalName() {
    	return getName();
    }

	/**
	 *
	 * @return animal size
	 */
    public int getSize() {
    	return size;
    }
	//increase eaten objects
    public void eatInc() {
    	eatCount++;
    }
	//get number of eaten objects
    public int getEatCount() {
    	return eatCount;
    }
	//if location changed
    public synchronized boolean getChanges () {
    	return coordChanged;
    }
	//set location changes
    public synchronized void setChanges (boolean state) {
    	if (observer != null)
    		observer.notify(this);
    	coordChanged = state;
    }
	//set animal size
	public void setSize(int size) {
		this.size = size;
		setWeightAccordingToSize();
	}
	//get horizontal speed
	public int getHorSpeed() {
		return horSpeed;
	}
	//set horizontal speed

	public void setHorSpeed(int horSpeed) {
		this.horSpeed = horSpeed;
	}
	//get vertical speed

	public int getVerSpeed() {
		return verSpeed;
	}
	//set vertical speed

	public void setVerSpeed(int verSpeed) {
		this.verSpeed = verSpeed;
	}
    public synchronized void setSuspended() {
    	threadSuspended = true;
    }
    //out of waiting state
    public synchronized void setResumed() {
    	threadSuspended = false;
    }

	@Override
	public void run() {
		while (true) {
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			synchronized (this) {
				if (stopped)
					break;
				if (threadSuspended)
					continue;
			}
			// do the move
			Point location = getLocation();
			int new_x = location.getX() + horSpeed * x_dir;
			if (new_x < 0) {
				new_x = 0;
				x_dir *= -1;
			}
			else if (new_x > ZooFrame.FRAME_WIDTH) {
				new_x = ZooFrame.FRAME_WIDTH;
				x_dir *= -1;
			}
			int new_y = location.getY() + verSpeed * y_dir;
			if (new_y < 0) {
				new_y = 0;
				y_dir *= -1;
			}
			else if (new_y > ZooFrame.FRAME_HEIGHT) {
				new_y = ZooFrame.FRAME_HEIGHT;
				y_dir *= -1;
			}
			setLocation(new Point(new_x, new_y));
			setChanges(true);
		}
	}
	public abstract void setWeightAccordingToSize();

	public synchronized void stop() {
		stopped = true;
	}

	public void moveTo(Food food) {
		Point foodLocation = food.getLocation();
		Point animalLocation = getLocation();
		if (foodLocation.getX() < animalLocation.getX()) {
			x_dir = -1;
		}
		else {
			x_dir = 1;
		}
		if (foodLocation.getY() < animalLocation.getY()) {
			y_dir = -1;
		}
		else {
			y_dir = 1;
		}
	}
}
