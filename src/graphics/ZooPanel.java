package graphics;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.imageio.ImageIO;
import javax.swing.*;

import animals.Animal;
import animals.Bear;
import food.Food;
import meat.Meat;
import memento.Caretaker;
import memento.Memento;
import mobility.Point;
import plants.Cabbage;
import plants.Lettuce;
import plants.Plant;
import utilities.Utils;

public class ZooPanel extends JPanel implements Runnable {
	/**
	 * buffer image, array of animals, food
	 */
	private BufferedImage img = null;
	private ArrayList<Animal> animals = new ArrayList<Animal>();
	private boolean coordChanged = false;
	private Food food = null;
	private Controller controller;
	private boolean running = true;
	private static ZooPanel zooPanel;
	private ExecutorService pool;
	private Map<Animal, Caretaker> caretakers = new HashMap<Animal, Caretaker>();
	public static ZooPanel getZooPanel() {
		if (zooPanel == null)
			zooPanel = new ZooPanel();
		return zooPanel;
	}

	/**
	 * constructor
	 * building zoo panel
	 * buttons in the bottom of the frame:
	 * Add Animal - choose animal from the list: geraffe,lion,bear,turtle,elephant
	 * by animal dialog
	 * Move Animal - move animal to another location by move dialog
	 *
	 * @see graphics.MoveAnimalDialog
	 * @see graphics.AddAnimalDialog
	 */
	private ZooPanel() {
		//thread pool
		pool = Executors.newFixedThreadPool(10);

		setLayout(new BorderLayout());
		controller = new Controller(this);
		manageZoo();
		JPanel buttonsPanel = new JPanel();
		add(buttonsPanel, BorderLayout.SOUTH);

		{
			final JButton button = new JButton("Add Animal");
			buttonsPanel.setBackground(Color.BLUE);
			buttonsPanel.add(button);
			button.addActionListener(new ActionListener() {
				/**
				 * add animals not more than 10
				 * @param e - event
				 */
				@Override
				public void actionPerformed(ActionEvent e) {
					if (animals.size() == 10) {
						JOptionPane.showMessageDialog(null, "You cannot add more than 10 animals");
						return;
					}
					JFrame frame = (JFrame) button.getParent().getParent().getParent().getParent().getParent().getParent();
					AddAnimalDialog dialog = new AddAnimalDialog(frame);
					dialog.setVisible(true);
					Animal animal = dialog.getAnimal();
					if (animal != null) {
						//controller observs animals
						animal.setObserver(controller);
						animals.add(animal);
						animal.setPan((ZooPanel) button.getParent().getParent());
						//save momento
						caretakers.put(animal, new Caretaker());
						pool.execute(animal);
						repaint();
					}
				}
			});
		}

		JButton button = new JButton("Sleep");
		buttonsPanel.add(button);
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				for (Animal animal : animals) {
					animal.setSuspended();
				}
				manageZoo();
			}
		});

		button = new JButton("Wake up");
		buttonsPanel.add(button);
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				for (Animal animal : animals) {
					animal.setResumed();
				}
				manageZoo();
			}
		});

		button = new JButton("Clear All");
		buttonsPanel.add(button);
		button.addActionListener(new ActionListener() {

			/**
			 * clear all animals
			 * @param e - event
			 */
			
			@Override
			public void actionPerformed(ActionEvent e) {
				for (Animal animal : animals) {
					animal.stop();
					animal.setObserver(null);
				}
				animals.clear();
				//clear all the momento
				caretakers.clear();
				food = null;
				repaint();
			}
		});

		{
			final JButton foodButton = new JButton("Food");
			buttonsPanel.add(foodButton);
			foodButton.addActionListener(new ActionListener() {

				/**
				 * add food to the panel, cabbage,lettuce or meat and make animal eat it
				 * @param e
				 */
				
				@Override
				public void actionPerformed(ActionEvent e) {
					Object[] options = {"Lettuce",
		                    "Cabbage",
		                    "Meat"};
					int n = JOptionPane.showOptionDialog(null,//parent container of JOptionPane
					    "Please choose food",
					    "Food for animals",
					    JOptionPane.YES_NO_CANCEL_OPTION,
					    JOptionPane.QUESTION_MESSAGE,
					    null,//do not use a custom Icon
					    options,//the titles of buttons
					    options[2]);//default button title
					//JFrame frame = (JFrame) foodButton.getParent().getParent().getParent().getParent().getParent().getParent();
					JPanel panel = (JPanel) foodButton.getParent().getParent().getParent();
					//get food
					switch (n) {
					case 0:
						food = Lettuce.getLettuce();
						break;
					case 1:
						food = Cabbage.getCabbage();
						break;
					case 2:
						food = Meat.getMeat();
						break;
					}
					food.setLocation(new Point(panel.getWidth() / 2, panel.getHeight() / 2));
					food.setPan((ZooPanel) foodButton.getParent().getParent());
					for (Animal animal : animals) {
						if (animal.getDiet().canEat(food.getFoodtype())) {
							animal.moveTo(food);
						}
					}
					repaint();
				}
			});
		}

		button = new JButton("Info");
		buttonsPanel.add(button);
		button.addActionListener(new ActionListener() {
			/**
			 * get information about the animals
			 * @param e - event
			 */
			@Override
			public void actionPerformed(ActionEvent e) {
				InfoDialog infoDialog = new InfoDialog(animals);
				infoDialog.setVisible(true);
			}
		});

		button = new JButton("Change Color");
		buttonsPanel.add(button);
		button.addActionListener(new ActionListener() {
			/**
			 * get information about the animals
			 * @param e - event
			 */
			@Override
			public void actionPerformed(ActionEvent e) {
				if (animals.isEmpty()) {
					JOptionPane.showMessageDialog(null, "No animals");
					return;
				}
				//call the change color dialog
				ChangeColorDialog changeColorDialog = new ChangeColorDialog(animals);
				changeColorDialog.setVisible(true);
			}
		});

		button = new JButton("Save State");
		buttonsPanel.add(button);
		button.addActionListener(new ActionListener() {
			/**
			 * get information about the animals
			 * @param e - event
			 */
			@Override
			public void actionPerformed(ActionEvent e) {
				//map of animals and its location
				for (Entry<Animal, Caretaker> entry : caretakers.entrySet()) {
					Animal animal = entry.getKey();
					//create momento
					Memento memento = animal.createMemento();
					//create caretaker for the momento
					Caretaker caretaker = entry.getValue();
					//save momento into caretakcer
					caretaker.addMemento(memento);
				}      
			}
		});
//restore state of the animal
		button = new JButton("Restore State");
		buttonsPanel.add(button);
		button.addActionListener(new ActionListener() {
			/**
			 * get information about the animals
			 * @param e - event
			 */
			@Override
			public void actionPerformed(ActionEvent e) {
				for (Entry<Animal, Caretaker> entry : caretakers.entrySet()) {
					Animal animal = entry.getKey();
					Caretaker caretaker = entry.getValue();
					//get previous step of the animal
					Memento memento = caretaker.getLastMemento();
					if (memento == null) {
						JOptionPane.showMessageDialog(null, "No saved states");
						return;
					}
					animal.setMemento(memento);
				}
			}
		});

		{
			JButton exitButton = new JButton("Exit");
			buttonsPanel.add(exitButton);
			exitButton.addActionListener(new ActionListener() {
				/**
				 * close the window
				 * @param e - event
				 */
				@Override
				public void actionPerformed(ActionEvent e) {
					JPanel panel = (JPanel) exitButton.getParent().getParent().getParent();
					synchronized (panel) {
						running = false;
					}
					try {
						controller.join();
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					pool.shutdown();
					System.exit(0);
				}
			});
		}
		controller.start();
	}

	/**
	 * if the location of the animal is changed, repaint the animal
	 */
	public void manageZoo() {
		ArrayList<Animal> eaten = new ArrayList<Animal>();
		for (Animal predetor : animals) {
			for (Animal prey : animals) {
				if (prey == predetor) // cannot eat itself
					continue;
				if (eaten.contains(prey))
					continue;
				if (predetor.getDiet().canEat(prey.getFoodtype()) &&
						(predetor.getWeight() >= prey.getWeight()) &&
						(predetor.calcDistance(prey.getLocation()) < prey.getSize())) {
					eaten.add(prey);
				}
			}
			if (food == null)
				continue;
			if (eaten.contains(food))
				continue;
			if (! predetor.getDiet().canEat(food.getFoodtype()))
				continue;
			Point animalLocation = predetor.getLocation();
			Point plantLocation = food.getLocation();
			if ((Math.abs(animalLocation.getX() - plantLocation.getX()) < 10) &&
					(Math.abs(animalLocation.getY() - plantLocation.getY()) < 10)) {
				if (predetor.eat(food))
					food = null;
			}
		}
		for (Animal prey : eaten) {
			animals.remove(prey);
			prey.stop();
			prey.setObserver(null);
			caretakers.remove(prey);
		}
		if (! eaten.isEmpty())
			coordChanged = true;
		if (isChange())
			repaint();
	}
	/**
	 * if the location of the animal is changed, return new coordinates
	 */
	private boolean isChange() {
		return controller.isModified() || coordChanged;
	}

	/**
	 * set background green,none
	 */
	public void setBackgroundGreen() {
		img = null;
		setBackground(Color.GREEN);
	}

	public void setBackgroundNone() {
		img = null;
		setBackground(null);
	}

	/**
	 * draw object, food or animal
	 * @param g - graphic object
	 */
	protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (img != null)
        	g.drawImage(img,0,0,getWidth(),getHeight(), this);
        for (Animal animal : animals) {
        	animal.drawObject(g);
		}
        if (food != null)
        	food.drawObject(g);
    }

	/**
	 * set image background
	 * @param filename - file name
	 */
	public void setBackgroundImage(String filename) {
		img = Utils.getImage(filename);
		repaint();
	}

	@Override
	public void run() {
		while (true) {
			synchronized (this) {
				if (!running)
					break;
			}
			try {
				Thread.sleep(300);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			manageZoo();
		}
	}
}
