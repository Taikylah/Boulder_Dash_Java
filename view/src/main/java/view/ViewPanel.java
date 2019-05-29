package view;

import entity.*;


import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 * The Class ViewPanel.
 *
 * @author Jean-Aymeric Diet
 */
class ViewPanel extends JPanel implements Observer {

	
	public int Xorg = 16;
    public int Yorg = 16;
    public int Score = 0;
    
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
    public int[][] map = new int[20][20];

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

    public void displayPlayer(Graphics g, int X, int Y) throws IOException {
    	
    	if(X == 16 & Y == 16) {
    		 Xorg = 16;
    	     Yorg = 16;
    	}
    	else {
    		
    		
    	
    	//ImageIO.read(new File("C:\\Users\\Vincent\\git\\Boulder_Dash_Java\\Boulder_Dash_Java\\entity\\Images\\down.png"));
        //g.drawImage(img, X, Y, null);
        Image img2 = ImageIO.read(new File("C:\\Users\\Vincent\\git\\Boulder_Dash_Java\\Boulder_Dash_Java\\entity\\Images\\path.png"));
        
        //if player mange le mur
        
        if (map[X/16][Y/16] == 48 || map[X/16][Y/16] == 51 || map[X/16][Y/16] == 52 || map[X/16][Y/16] == 53) {	// Player can only break the dirt, grab diamond and walk on the path
        	System.out.println(X/16);
        	System.out.println(Y/16);
        	System.out.println(map[X/16][Y/16]);
        	if(X < Xorg) {
	        g.drawImage(img2, Xorg, Yorg, null);// print the path Sprite
	        map[Xorg/16][Yorg/16] = 52;//set the ID of the Path in the array
	        }
	 
	        if(X > Xorg) {
	        g.drawImage(img2, Xorg, Yorg, null);
	        map[Xorg/16][Yorg/16] = 52;
	        }
	        
	        if(Y < Yorg) {
	        g.drawImage(img2, Xorg, Yorg, null);
	        map[Xorg/16][Yorg/16] = 52;
	
	        }
	        
	        if(Y > Yorg) {
	        g.drawImage(img2, Xorg, Yorg, null);
	        map[Xorg/16][Yorg/16] = 52;
	
	        }
	        //System.out.println(Xorg);
	        //System.out.println(Yorg);
	         Xorg = X;
	         Yorg = Y;
	         g.drawImage(Heros.image(), X, Y, null);
	    	}
        
        if( map[X/16][Y/16] == 51) {	// Count number of Diamond
        	Score ++;
        	System.out.println(Score);
        }
        
        
        if (map[X/16][Y/16] == 49 || map[X/16][Y/16] == 50) { // if the player try to step on a wall or a rock he is send back to his previous position
        	System.out.println(X/16);
        	System.out.println(Y/16);
        	System.out.println(map[X/16][Y/16]);
        	g.drawImage(Heros.image(), Xorg, Yorg, null);
	         Heros.X = Xorg;
	         Heros.Y = Yorg;
        }
        if (X== 288 && Y== 288 && Score >= 10) {	//end the game when player get 10 Diamond and is on the exit sprite
        	System.out.println("GG!!");
        	System.exit(0);
        }
        
        
        }
    }
    
    public void rockfall() {	//A voir car ne rentre pas dans boucles FOR
    	System.out.println("rockfall");
    	for(int x = 0; x <= 20; x++){
    		System.out.println("x");
    		for(int y = 0; y <= 20; y++) {
    			System.out.println("y");
    			if(map[x][y] == 50) {//for each case in map[][] check if it is a rock
    				System.out.println("Cailloux");
    				if(map[x-1][y] == 52) {// if the block under the rock is a path
    					map[x-1][y] = 50;// down the rock
    					map[x][y] = 52;// place a path at the old place of the rock
    					this.refresh(null, x, y);
    					System.out.println("rock felt");
    				}
    			}
    		}
    	}
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
    public void refresh(final Graphics graphics, int x, int y) {
    	int hauteur = 0;
    	switch (map[x][y]) {
        case 48:	//Dirt generation
            try {
                //i*16 pour les px
                graphics.drawImage(new Dirt(x * 16, hauteur).getImage(), x * 16, hauteur, new ImageObserver() {
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
            case 49:	//Wall generation
                try {
                    //i*16 pour les px
                    graphics.drawImage(new Wall(x * 16, hauteur).getImage(), x * 16, hauteur, new ImageObserver() {
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
            case 50:	//Rock generation
                try {
                    //i*16 pour les px
                    graphics.drawImage(new Rock(x * 16, hauteur).getImage(), x * 16, hauteur, new ImageObserver() {
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
            case 51:	//Diamond generation
                try {
                    //i*16 pour les px
                    graphics.drawImage(new Diamond(x * 16, hauteur).getImage(), x * 16, hauteur, new ImageObserver() {
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
            case 53:	//Enter generation
                try {
                    //i*16 pour les px
                    graphics.drawImage(new Enter(x * 16, hauteur).getImage(), x * 16, hauteur, new ImageObserver() {
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
            case 54:	//Exit generation
                try {
                    //i*16 pour les px
                    graphics.drawImage(new Out(x * 16, hauteur).getImage(), x * 16, hauteur, new ImageObserver() {
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
            case 55:	//Mob generation
                try {
                    //i*16 pour les px
                    graphics.drawImage(new Mob(x * 16, hauteur).getImage(), x * 16, hauteur, new ImageObserver() {
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
                graphics.drawString("error", x*16, hauteur);
        }
    

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
        int hauteur = 0;
        int y = 0;
        for (String msg : message) {
            //Split msg to get 1 element
        	
            char[] splitMsg = msg.toCharArray();
           
            //boucle pour afficher
            for (int i = 0; i < splitMsg.length; i++) {
                map[i][y] = splitMsg[i];
                System.out.println(map[i][y]);
                //En fonction du nb, on affiche telle ou telle img
                switch (splitMsg[i]) {
                case 48:
                    try {
                        //i*16 pour les px
                        graphics.drawImage(new Dirt(i * 16, hauteur).getImage(), i * 16, hauteur, new ImageObserver() {
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
                    case 49:
                        try {
                            //i*16 pour les px
                            graphics.drawImage(new Wall(i * 16, hauteur).getImage(), i * 16, hauteur, new ImageObserver() {
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
                    case 50:
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
                    case 51:
                        try {
                            //i*16 pour les px
                            graphics.drawImage(new Diamond(i * 16, hauteur).getImage(), i * 16, hauteur, new ImageObserver() {
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
                    case 53:
                        try {
                            //i*16 pour les px
                            graphics.drawImage(new Enter(i * 16, hauteur).getImage(), i * 16, hauteur, new ImageObserver() {
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
                    case 54:
                        try {
                            //i*16 pour les px
                            graphics.drawImage(new Out(i * 16, hauteur).getImage(), i * 16, hauteur, new ImageObserver() {
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
                    case 55:	//Mob generation
                        try {
                            //i*16 pour les px
                            graphics.drawImage(new Mob(i * 16, hauteur).getImage(), i * 16, hauteur, new ImageObserver() {
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
