package memento;

import java.util.ArrayList;
/** Class that contains a list of momento and return last momento
 *  @author - Leah Brodsky
 *   @author - Ziv Gimani
 */

public class Caretaker {
	static final private int MAX_SIZE = 3;
	private ArrayList<Memento> statesList = new ArrayList();

	/**
	 * add momento to the list
	 * @param m-momento
	 */
	public void addMemento(Memento m) {
		statesList.add(m);
		if (statesList.size() > MAX_SIZE) {
			statesList.remove(0);
		}
	}

	/**
	 * get last momento in the list
	 * @return momento
	 */
	public Memento getLastMemento() {
		if (statesList.isEmpty())
			return null;
		Memento memnto = statesList.get(statesList.size() - 1);
		statesList.remove(memnto);
		return memnto;
	}
}
