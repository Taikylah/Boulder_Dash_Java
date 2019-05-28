package controller;

import contract.ControllerOrder;
import contract.IController;
import contract.IModel;
import contract.IView;
import entity.Heros;

/**
 * The Class Controller.
 */
public final class Controller implements IController {

	/** The view. */
	private IView		view;

	/** The model. */
	private IModel	model;

	/**
	 * Instantiates a new controller.
	 *
	 * @param view
	 *          the view
	 * @param model
	 *          the model
	 */
	public Controller(final IView view, final IModel model) {
		this.setView(view);
		this.setModel(model);
	}

	/**
     * Control.
     */
	/*
	 * (non-Javadoc)
	 *
	 * @see contract.IController#control()
	 */
	public void control() {
		this.view.printMessage("Bienvenue dans BoulderDash");
	}

	/**
     * Sets the view.
     *
     * @param pview
     *            the new view
     */
	private void setView(final IView pview) {
		this.view = pview;
	}

	/**
	 * Sets the model.
	 *
	 * @param model
	 *          the new model
	 */
	private void setModel(final IModel model) {
		this.model = model;
	}

	/**
     * Order perform.
     *
     * @param controllerOrder
     *            the controller order
     */
	/*
	 * (non-Javadoc)
	 *
	 * @see contract.IController#orderPerform(contract.ControllerOrder)
	 */
	public void orderPerform(final ControllerOrder controllerOrder) {
		switch (controllerOrder) {
			case map1:
				this.model.loadHelloWorld("UP");
				break;
			case map2:
				this.model.loadHelloWorld("DW");
				break;
			case map3:
				this.model.loadHelloWorld("RI");
				break;
			case map4:
				this.model.loadHelloWorld("LE");
				break;
			case map5:
				this.model.loadHelloWorld("PP");
				break;
			case keyup:
				System.out.println("up");
				
				Heros.joueur = 2;
				Heros.Y=Heros.Y-16;
                
                if (Heros.Y==0) {
                	Heros.Y=16;
                    }
                this.view.displayPlayer();
                System.out.println("Y = " + Heros.Y);
				break;
			case keydown:
				System.out.println("down");
				
				Heros.joueur = 1;
				Heros.Y=Heros.Y+16;
                
                if (Heros.Y==336) {
                	Heros.Y=320;
                    }
                this.view.displayPlayer();
                System.out.println("Y = " + Heros.Y);
				break;
			case keyright:
				System.out.println("right");
				
				Heros.joueur = 4;
				Heros.X=Heros.X+16;
                
                if (Heros.X==336) {
                	Heros.X=320;
                    }
                this.view.displayPlayer();
                System.out.println("X = " + Heros.X);
               	break;
			case keyleft:
				System.out.println("left");
				
				Heros.joueur = 3;
				Heros.X=Heros.X-16;
                
                if (Heros.X==0) {
                	Heros.X=16;
                    }
                this.view.displayPlayer();
                System.out.println("X = " + Heros.X);
				break;
			default:
				break;
		}
	}

}
