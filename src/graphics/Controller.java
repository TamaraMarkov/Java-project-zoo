package graphics;

import observer.Observable;
import observer.Observer;

/**
 * Class that observ animals
 *  @authors Leah Brodsky
 *   @author Ziv Gimani
 */

public class Controller extends Thread implements Observer {
	private boolean modified = false;

	public Controller(Runnable runnable) {
		super(runnable);
	}
	@Override
	//if there are changes set the flag to true
	public void notify(Observable observable) {
		modified = true;
	}
//if modified update changes
	public boolean isModified() {
		boolean tempModified = modified;
		modified = false;
		return tempModified;
	}
}
