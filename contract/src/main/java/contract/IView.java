package contract;

import java.io.IOException;

/**
 * The Interface IView.
 *
 * @author Groupe 3
 */
public interface IView {

	/**
	 * Prints the message.
	 *
	 * @param print the message
	 * 		and all the necessary methods.
	 *          
	 */
	void printMessage(final String message);
	void update();
	public void displayPlayer();
	public void rockFall() throws IOException;
	public void startThread();
}
