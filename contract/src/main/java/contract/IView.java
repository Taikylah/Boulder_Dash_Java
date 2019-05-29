package contract;

import java.io.IOException;
import java.util.Observable;

/**
 * The Interface IView.
 *
 * @author Jean-Aymeric Diet
 */
public interface IView {

	/**
	 * Prints the message.
	 *
	 * @param message
	 *          the message
	 */
	void printMessage(final String message);
	void update();
	public void displayPlayer();
	public void rockFall() throws IOException;
}
