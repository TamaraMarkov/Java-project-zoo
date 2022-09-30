package graphics;

import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import animals.Animal;
import animals.Bear;
import animals.Elephant;
import animals.Giraffe;
import animals.Lion;
import animals.Turtle;
import factories.AbstractFactory;
import factories.CarnivoreFactory;
import factories.HerbivoreFactory;
import factories.OmnivoreFactory;

/**Class that add animals according to chosen quelities
 *  @author - Leah Brodsky
 *  @author - Ziv Gimani
 *
 */
public class AddAnimalDialog extends JDialog {
	private JComboBox<String> animalsTypes = new JComboBox<String>();
	private JComboBox<String> dietTypes = new JComboBox<String>();
	private JTextField animalName = new JTextField("           ");
	private JTextField animalSize = new JTextField("           ");
	private JTextField horizontalVelocity = new JTextField("           ");
	private JTextField verticalVelocity = new JTextField("           ");
	private JComboBox<String> animalsColors = new JComboBox<String>();
	private Animal animal;

	public AddAnimalDialog(JFrame parent) {
		super(parent, "Add Animal");
		setLayout(new GridLayout(8, 1));
		setSize(300, 200);
		setModal(true);

		JPanel panel = new JPanel();
		add(panel);
		JLabel label = new JLabel("Diet: ");
		panel.add(label);
		panel.add(dietTypes);
		dietTypes.addItem("Carnivore");
		dietTypes.addItem("Herbivore");
		dietTypes.addItem("Omnivore");
		dietTypes.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String dietType = (String) dietTypes.getSelectedItem();
				animalsTypes.removeAllItems();
				if (dietType.equals("Carnivore")) {
					animalsTypes.addItem("Lion");
				}
				else
				if (dietType.equals("Herbivore")) {
					animalsTypes.addItem("Elephant");
					animalsTypes.addItem("Giraffe");
					animalsTypes.addItem("Turtle");
				}
				else { // Omnivore
					animalsTypes.addItem("Bear");
				}
			}
		});
		dietTypes.setSelectedIndex(0);

		panel = new JPanel();
		add(panel);
		label = new JLabel("Animal: ");
		panel.add(label);
		panel.add(animalsTypes);

		panel = new JPanel();
		add(panel);
		label = new JLabel("Animal Name: ");
		panel.add(label);
		panel.add(animalName);

		panel = new JPanel();
		add(panel);
		label = new JLabel("Animal Size: ");
		panel.add(label);
		panel.add(animalSize);

		panel = new JPanel();
		add(panel);
		label = new JLabel("Horizontal velocity: ");
		panel.add(label);
		panel.add(horizontalVelocity);

		panel = new JPanel();
		add(panel);
		label = new JLabel("Vertical velocity: ");
		panel.add(label);
		panel.add(verticalVelocity);

		panel = new JPanel();
		add(panel);
		//add button to change animal color
		label = new JLabel("Animals colors: ");
		panel.add(label);
		panel.add(animalsColors);
		//add the colors
		animalsColors.addItem("Red");
		animalsColors.addItem("Blue");
		animalsColors.addItem("Natural");

		panel = new JPanel();
		add(panel);
		JButton okButton = new JButton("OK");
		okButton.addActionListener(new ActionListener() {

			/**
			 * get the properties of the animals
			 * size,velocity,color,name,animal type
			 * @param e
			 */
			@Override
			public void actionPerformed(ActionEvent e) {
				int size = 0;
				//get size the user entered
				try {
					size = Integer.valueOf(animalSize.getText().trim());
				}//error if size is not a number
				catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog(null, "Size must be a number");
					return;
				}//size limit
				if ((size < 50) || (size > 300)) {
					JOptionPane.showMessageDialog(null, "Size is out of range (50-300)");
					return;
				}

				int hVelocity = 0;
				//get velocity
				try {
					hVelocity = Integer.valueOf(horizontalVelocity.getText().trim());
				}//error if horizontal velocity is not a number
				catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog(null, "Horizontal velocity must be a number");
					return;
				}//velocity limit
				if ((hVelocity < 1) || (hVelocity > 10)) {
					JOptionPane.showMessageDialog(null, "Horizontal velocity is out of range (1-10)");
					return;
				}

				int vVelocity = 0;
				//vertical velocity
				try {
					vVelocity = Integer.valueOf(verticalVelocity.getText().trim());
				}
				catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog(null, "Vertical velocity must be a number");
					return;
				}
				if ((vVelocity < 1) || (vVelocity > 10)) {
					JOptionPane.showMessageDialog(null, "Vertical velocity is out of range (1-10)");
					return;
				}
				//get animal name
				String dietType = (String) dietTypes.getSelectedItem();
				AbstractFactory factory = null;
				//get the factory according to the diet
				if (dietType.equals("Carnivore")) {
					factory = CarnivoreFactory.getCarnivoreFactory();
				}
				else
				if (dietType.equals("Herbivore")) {
					factory = HerbivoreFactory.getHerbivoreFactory();
				}
				else { // Omnivore
					factory = OmnivoreFactory.getOmnivoreFactory();
				}

				animal = factory.getAnimal((String) animalsTypes.getSelectedItem());
				String name = animalName.getText().trim();
				animal.setName(name);

				animal.setCol((String)animalsColors.getSelectedItem());
				animal.setVerSpeed(vVelocity);
				animal.setHorSpeed(hVelocity);
				animal.setSize(size);
				animal.setWeightAccordingToSize();
				setVisible(false);
			}
		});
		panel.add(okButton);

		JButton cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		panel.add(cancelButton);
		pack();
	}

	/**
	 * function that gets animal
	 * @return animal
	 */
	public Animal getAnimal() {
		return animal;
	}
}
