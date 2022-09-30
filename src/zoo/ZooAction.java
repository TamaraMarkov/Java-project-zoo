package zoo;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import animals.*;
import diet.Carnivore;
import food.EFoodType;
import food.IEdible;
import mobility.Point;
import plants.Cabbage;
import utilities.MessageUtility;

import static java.lang.System.exit;

/**
 * @author Leah Brodsky - 318191764,
 * @author Ziv Gimani - 207354580
 * Campus Ashdod
 * Class that include main and finishes the program
 * @see food.EFoodType
 * @see food.IEdible
 * @see Animal
 * @see diet.Carnivore
 * @see mobility.Point
 * @see utilities.MessageUtility
 */
public class ZooAction {
	/**
	 * runs the eat function, if animal eats the food
	 * @param animal - which animal you get
	 * @param food - food the animal supposed to eat
	 * @return boolean
	 */
	static public boolean eat(Object animal, IEdible food) {
		if (!(animal instanceof Animal))
			return false;
		Animal temp = (Animal) animal;

		return temp.eat(food);
	}

	/**
	 * Function run move if animal can reach the point
	 * @param animal - animal that move
	 * @param point - point to which animal moves
	 * @see MessageUtility#logBooleanFunction(String name , String funcName, Object point, boolean)
	 * @return boolean
	 */
	static public boolean move(Object animal, Point point) {
		if (!(animal instanceof Animal))
			return false;
		Animal temp = (Animal) animal;
		if (!Point.checkBoundaries(point.getX(), point.getY())) {
	    	MessageUtility.logBooleanFunction(temp.getName(), "move", point, false);
			return false;
		}
		if (temp.move(point) != 0)
			return true;
		else
			return false;
	}


}

