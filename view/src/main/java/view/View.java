package view;

import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.SwingUtilities;

import contract.ControllerOrder;
import contract.IController;
import contract.IModel;
import contract.IView;
import entity.Heros;

/**
 * The Class View.
 *
 * @author Jean-Aymeric Diet
 */
public final class View implements IView, Runnable {

	/** The frame. */
	private final ViewFrame viewFrame;
	int launchthread = 0;
	/**
	 * Instantiates a new view.
	 *
	 * @param model
	 *          the model
	 */
	public View(final IModel model) {
		this.viewFrame = new ViewFrame(model);
		SwingUtilities.invokeLater(this);
	}

	/**
	 * Key code to controller order.
	 *
	 * @param keyCode
	 *          the key code
	 * @return the controller order
	 */
	
	protected static ControllerOrder keyCodeToControllerOrder(final int keyCode) {
		switch (keyCode) {
			case KeyEvent.VK_UP:
				return ControllerOrder.keyup;
			case KeyEvent.VK_DOWN:
				return ControllerOrder.keydown;
			case KeyEvent.VK_RIGHT:
				return ControllerOrder.keyright;
			case KeyEvent.VK_LEFT:
				return ControllerOrder.keyleft;
			default:
				return null;
		}
	} 

	/*
	 * (non-Javadoc)
	 *
	 * @see contract.IView#printMessage(java.lang.String)
	 */
	public void printMessage(final String message) {
		this.viewFrame.printMessage(message);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Runnable#run()
	 */
	public void run() {
		this.viewFrame.setVisible(true);
	}
	

	/**
	 * Sets the controller.
	 *
	 * @param controller
	 *          the new controller
	 */
	public void setController(final IController controller) {
		this.viewFrame.setController(controller);
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}
	
	public void displayPlayer(){
        try {
            ((ViewPanel) this.viewFrame.getContentPane()).displayPlayer(this.viewFrame.getContentPane().getGraphics(), Heros.X, Heros.Y);
        } catch (IOException ex) {
            Logger.getLogger(View.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

	public void rockFall() throws IOException{
		// TODO Auto-generated method stub
		((ViewPanel) this.viewFrame.getContentPane()).rockfall(this.viewFrame.getContentPane().getGraphics());
	}

	@Override
	public void startThread() {
		// TODO Auto-generated method stub
		if(launchthread != 1) {
	    	Thread t = new Thread(new threadview());
	        t.start();
	        launchthread = 1;
	        
	    }
	}
public class threadview implements Runnable{

    	
    	@Override
    	public void run(){     
    		int i = 1 ;
    		while(i>0) {
    	      System.out.println("TEST");
    	      try {
    			rockFall();
    		} catch (IOException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
    		try {	
    			TimeUnit.MILLISECONDS.sleep(200);
    		} catch (InterruptedException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
    		}
    		}
    	  } 
	
	
}
