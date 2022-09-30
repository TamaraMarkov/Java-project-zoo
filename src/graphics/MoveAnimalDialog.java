package graphics;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import animals.Animal;
import animals.Bear;
import animals.Elephant;
import animals.Giraffe;
import animals.Lion;
import animals.Turtle;
import mobility.Point;

/**
 * Class that build dialog that responsible for moving animals
 *  @author - Leah Brodsky
 *  @author - Ziv Gimani
 *
 */
public class MoveAnimalDialog extends JDialog {
	//animal list
	private ArrayList<Animal> animals;
	private JComboBox<Animal> animalsCombo = new JComboBox<Animal>();
	//coordinates to enter
	private JTextField xCoordinate = new JTextField("           ");
	private JTextField yCoordinate = new JTextField("           ");
	//moved animal
	private Animal movedAnimal;

	/**
	 * constructor of dialog
	 * @param animals - list of animals
	 */
	public MoveAnimalDialog(ArrayList<Animal> animals) {
		setTitle("Move Animal");
		this.animals = animals;
		setLayout(new GridLayout(7, 1));
		setSize(300, 200);
		setModal(true);
		JPanel panel = new JPanel();

		add(panel);
		JLabel label = new JLabel("Animal: ");
		panel.add(label);
		panel.add(animalsCombo);
		for (Animal animal : animals) {
			animalsCombo.addItem(animal);
		}

		panel = new JPanel();
		add(panel);
		//enter x and y coordinates
		label = new JLabel("X Coordinate: ");
		panel.add(label);
		panel.add(xCoordinate);

		panel = new JPanel();
		add(panel);
		label = new JLabel("Y Coordinate: ");
		panel.add(label);
		panel.add(yCoordinate);

		panel = new JPanel();
		add(panel);
		JButton okButton = new JButton("OK");
		okButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int x = 0;
				try {//get x
					x = Integer.valueOf(xCoordinate.getText().trim());
				}//x must be a number
				catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog(null, "X Coordinate must be a number");
					return;
				}
				if ((x < 0) || (x > 800)) {
					JOptionPane.showMessageDialog(null, "X Coordinate is out of range (0-800)");
					return;
				}

				int y = 0;
				try {//get y
					y = Integer.valueOf(yCoordinate.getText().trim());
				}//y must be a number
				catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog(null, "Y Coordinate must be a number");
					return;
				}
				if ((y < 0) || (y > 600)) {
					JOptionPane.showMessageDialog(null, "Y Coordinate is out of range (0-600)");
					return;
				}
				Animal animal = (Animal) animalsCombo.getSelectedItem();
				//move animal
				animal.move(new Point(x, y));
				movedAnimal = animal;
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
	}

	public Animal getMovedAnimal() {
		return movedAnimal;
	}
}
