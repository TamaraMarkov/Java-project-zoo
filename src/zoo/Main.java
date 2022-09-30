package zoo;

import java.util.ArrayList;

import animals.*;

public class Main {

	public static void main(String[] args) {
		Lion lion = new Lion("L");
		Bear bear = new Bear("B", "BLUE");
		Giraffe gir = new Giraffe("G", 3.4);
		Turtle tur = new Turtle("T", 450);
		Elephant el = new Elephant("E", 4.5);
		ArrayList<Animal> animals = new ArrayList<Animal>();
		animals.add(lion);
		animals.add(bear);
		animals.add(gir);
		animals.add(tur);
		animals.add(el);
		for (Animal pred : animals) {
			for (Animal prey : animals) {
				if (pred.getDiet().canEat(prey.getFoodtype())) {
					System.out.println(pred + " can eat " + prey);
				}
				else {
					System.out.println(pred + " cannot eat " + prey);
				}
			}
		}
		boolean b = bear.getDiet().canEat(bear.getFoodtype());
		b = gir.getDiet().canEat(bear.getFoodtype());

	}

}
