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

import animals.Animal;

/**
 * Class that changes the color of the animal the user choose
 *  @authors Leah Brodsky
 *  @author Ziv Gimani
 */
public class ChangeColorDialog extends JDialog {
	private ArrayList<Animal> animals;
	//choose animal
	JComboBox<String> animalsCombo = new JComboBox<String>();
	//choose color
	JComboBox<String> colorsCombo = new JComboBox<String>();

	/**
	 * constractor
	 * @param animals - list of animals
	 */
	public ChangeColorDialog(ArrayList<Animal> animals) {
		setTitle("Change Color");
		this.animals = animals;
		setLayout(new GridLayout(3, 1));
		setSize(300, 200);
		setModal(true);

		for (Animal animal: animals) {
			animalsCombo.addItem(animal.toString());
		}
// add colors to the list
		colorsCombo.addItem("Red");
		colorsCombo.addItem("Blue");
		colorsCombo.addItem("Natural");

		JPanel panel = new JPanel();
		add(panel);
		JLabel label = new JLabel("Animals: ");
		panel.add(label);
		panel.add(animalsCombo);

		panel = new JPanel();
		add(panel);
		label = new JLabel("Color: ");
		panel.add(label);
		panel.add(colorsCombo);

		panel = new JPanel();
		add(panel);
		JButton okButton = new JButton("OK");
		panel.add(okButton);
		okButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//ensure you choose an animal and a color
				int animalIndex = animalsCombo.getSelectedIndex();
				if (animalIndex < 0)
				{
					JOptionPane.showMessageDialog(null, "No animal selected");
					return;
				}
				int colorIndex = colorsCombo.getSelectedIndex();
				if (colorIndex < 0)
				{
					JOptionPane.showMessageDialog(null, "No color selected");
					return;
				}
				animals.get(animalIndex).setCol((String) colorsCombo.getSelectedItem());
				setVisible(false);
			}
		});
		JButton cancelButton = new JButton("Cancel");
		panel.add(cancelButton);
		cancelButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});

		pack();
	}
}
