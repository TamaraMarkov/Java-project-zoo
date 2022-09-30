package diet;

import animals.Animal;
import food.EFoodType;
import food.IEdible;

/**
 * This interface contain methods that check the ability eat specific food (diet)
 * @author Leah Brodsky
 * @author Ziv Gimani
 */
public interface IDiet {
    public boolean canEat(EFoodType food);
    public double eat(Animal animal, IEdible food);
    public String toString();
}
