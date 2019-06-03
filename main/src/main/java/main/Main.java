/**
 * @author Jean-Aymeric DIET jadiet@cesi.fr
 * @version 1.0
 */
package main;

import contract.ControllerOrder;
import controller.Controller;
import model.Model;
import view.View;


/**
 * The Class Main.toti
 *
 * @author Groupe 3
 */
public abstract class Main {

    /**
     * The main method.
     *	Here we call every method that are needs to make the code starts well. 
     *
     *	There is also the choice for the map.
     * @param args
     *            the arguments
     */

	
    public static void main(final String[] args) {
    	

        
        final Model model = new Model();	
        final View view = new View(model);
        final Controller controller = new Controller(view, model);
        view.setController(controller);
        view.startThread();
        controller.control();
        controller.orderPerform(ControllerOrder.map5);
        
    }
}
