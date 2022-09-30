package plants;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Random;

import food.EFoodType;
import food.Food;
import food.IEdible;
import graphics.IDrawable;
import graphics.ZooPanel;
import mobility.Ilocatable;
import mobility.Point;
import utilities.MessageUtility;
import utilities.Utils;

/**
 * @author baroh
 *
 */
public abstract class Plant extends Food {
	/**
	 * 
	 */
	public Plant() {
		MessageUtility.logConstractor("Plant", "Plant");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see food.IFood#getFoodtype()
	 */
	@Override
	public EFoodType getFoodtype() {
		MessageUtility.logGetter(this.getClass().getSimpleName(), "getFoodType", EFoodType.VEGETABLE);
		return EFoodType.VEGETABLE;
	}

}
