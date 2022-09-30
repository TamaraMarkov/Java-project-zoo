package graphics;

import java.util.ArrayList;

import javax.swing.JDialog;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;

import animals.Animal;

/**
 * Class that build the information table about animals
 *  @author - Leah Brodsky
 *  @author - Ziv Gimani
 *
 */
public class InfoDialog extends JDialog {
	private ArrayList<Animal> animals;

	public InfoDialog(ArrayList<Animal> animals) {
		setModal(true);
		this.animals = animals;
		String[] columns = new String[] {
				"Animal", "Color", "Weight", "Hor. Speed", "Ver. Speed", "Eat counter"
	        };
		//actual data for the table in a 2d array
		Object[][] data = new Object[animals.size() + 1][];
		int eatCount = 0;
		for (int i = 0; i < animals.size(); i++) {
			Animal animal = animals.get(i);
			data[i] = new Object[] {animal.getClass().getSimpleName(), animal.getColor(), animal.getWeight(),
					animal.getHorSpeed(), animal.getVerSpeed(), animal.getEatCount()};
			eatCount += animal.getEatCount();
		}
		data[animals.size()] = new Object[] {"Total", "", "", "", "", eatCount};
		JTable table = new JTable(data, columns);
		// make the table not editable
		table.setDefaultEditor(Object.class, null);
		add(new JScrollPane(table));
		pack();
	}
}
