package view;

import entity.Rock;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.ImageObserver;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JPanel;

/**
 * The Class ViewPanel.
 *
 * @author Jean-Aymeric Diet
 */
class ViewPanel extends JPanel implements Observer {

    /**
     * The view frame.
     */
    private ViewFrame viewFrame;
    /**
     * The Constant serialVersionUID.
     */
    private static final long serialVersionUID = -998294702363713521L;
    
    /**
     * Tableau map
     */
    public int[][] map = new int[19][19];

    /**
     * Instantiates a new view panel.
     *
     * @param viewFrame the view frame
     */
    public ViewPanel(final ViewFrame viewFrame) {
        this.setViewFrame(viewFrame);
        viewFrame.getModel().getObservable().addObserver(this);
    }

    /**
     * Gets the view frame.
     *
     * @return the view frame
     */
    private ViewFrame getViewFrame() {
        return this.viewFrame;
    }

    /**
     * Sets the view frame.
     *
     * @param viewFrame the new view frame
     */
    private void setViewFrame(final ViewFrame viewFrame) {
        this.viewFrame = viewFrame;
    }

    /*
	 * (non-Javadoc)
	 *
	 * @see java.util.Observer#update(java.util.Observable, java.lang.Object)
     */
    public void update(final Observable arg0, final Object arg1) {
        this.repaint();
    }

    /*
	 * (non-Javadoc)
	 *
	 * @see javax.swing.JComponent#paintComponent(java.awt.Graphics)
     */
    @Override
    protected void paintComponent(final Graphics graphics) {
        graphics.clearRect(0, 0, this.getWidth(), this.getHeight());
        String[] message = this.getViewFrame().getModel().getHelloWorld().getMessage().split(";");
        int hauteur = 16;
        int y = 0;
        for (String msg : message) {
            //Split msg to get 1 element
            char[] splitMsg = msg.toCharArray();
            //boucle pour afficher
            for (int i = 0; i < splitMsg.length; i++) {
                map[i][y] = splitMsg[i];
                //En fonction du nb, on affiche telle ou telle img
                switch (splitMsg[i]) {
                    case 1:
                        try {
                            //i*16 pour les px
                            graphics.drawImage(new Rock(i * 16, hauteur).getImage(), i * 16, hauteur, new ImageObserver() {
                                @Override
                                public boolean imageUpdate(Image img, int infoflags, int x, int y, int width, int height) {
                                    // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                                    return false;
                                }
                            });
                        } catch (IOException ex) {
                            Logger.getLogger(ViewPanel.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        break;
                    default:
                        graphics.drawString("error", i*16, hauteur);
                }
            }
            // graphics.drawString(msg, 1,hauteur);
            hauteur += 16;
            y++;
        }

    }
}
