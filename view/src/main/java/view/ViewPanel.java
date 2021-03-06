package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.imageio.ImageIO;
import javax.swing.JPanel;


import entity.Diamond;
import entity.Dirt;
import entity.Enter;
import entity.Heros;
import entity.Mob;
import entity.Out;
import entity.Path;
import entity.Rock;
import entity.Wall;

/**
 * The Class ViewPanel.
 *
 * @author Groupe 3
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

    void displayScore() {
    	//Display the score for the game
        Graphics score = this.getGraphics();
        score.clearRect(330, 20, 250,50);
        score.setColor(Color.WHITE);
        score.fillRect(330, 20, 250, 50);
        Font font = new Font("Courier", Font.BOLD, 12);
        score.setFont(font);
        score.setColor(Color.BLACK);
        score.drawString(" Your curent score is " + (Score * 10), 330, 30); 
    }
    void displayDiamondLeft() {
    	//Display the amount of diamonds left
        Graphics scoreLeft = this.getGraphics();
        scoreLeft.clearRect(330, 50, 250,50);
        scoreLeft.setColor(Color.WHITE);
        scoreLeft.fillRect(330, 50, 250, 50);
        Font font = new Font("Courier", Font.BOLD, 12);
        scoreLeft.setFont(font);
        scoreLeft.setColor(Color.BLACK);
        if(Score < 10) {
        	scoreLeft.drawString(" Diamond left to finish : " + (10 - Score), 330, 50); 
        }else{
            //score.drawString(" Diamond left to finish : " + 0, 330, 50);
        	scoreLeft.drawString(" Exit Open ", 330, 50);
        }
    }
    
    public void displayPlayer(Graphics g, int X, int Y) throws IOException {
		displayDiamondLeft();
    	if(X == 16 & Y == 16) {
    		 Xorg = 16;
    	     Yorg = 16;
    	}
    	else {
    		
    		
    	
    	//ImageIO.read(new File("C:\\Users\\Vincent\\git\\Boulder_Dash_Java\\Boulder_Dash_Java\\entity\\Images\\down.png"));
        //g.drawImage(img, X, Y, null);
        Image img2 = ImageIO.read(new File("../entity/Images/path.png"));
        
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
	         g.drawImage(img2, X, Y, null);
	         g.drawImage(Heros.image(), X, Y, null);
	    	}
        
        if( map[X/16][Y/16] == 51) {	// Count number of Diamond
        	Score ++;
        	System.out.println(Score);
        	displayScore();
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
        	viewFrame.printMessage("CONGRATS !! Total diamonds : " + Score);
        	System.exit(0);
        }
        if( map[X/16][Y/16] == 55) {	// If player hit a monster he lose
        	viewFrame.printMessage("GAME OVER !!");
        	System.exit(0);
        }
        
        
        }
        
    }
    
    public  void rockfall(Graphics g) throws IOException {	
    	
    	for(int x = 1; x < 20; x++){
    		
    		for(int y = 1; y < 20; y++) {

    			if(map[x][y] == 50) {//for each case in map[][] check if it is a rock GOOD
    				if(map[x][y+1] == 52) {// if the block under the rock is a path
    					g.drawImage(new Rock(x * 16, y*16).getImage(), x * 16, y*16+16,null);
    					g.drawImage(new Path(x * 16, y*16).getImage(), x * 16, y*16,null);
    					map[x][y+1] = 50;// down the rock
    					map[x][y] = 52;// place a path at the old place of the rock
    				}
    				else if (map[x][y+1] == 55) {// if the bloc under is a mob
    					g.drawImage(new Diamond(x * 16, y*16).getImage(), x * 16, y*16+16,null);
    					g.drawImage(new Path(x * 16, y*16).getImage(), x * 16, y*16,null);
    					map[x][y+1] = 51;// down the rock
    					map[x][y] = 52;// place a path at the old place of the rock
    				}
    				else if (x*16 == Heros.X && ((y*16)+16) == Heros.Y) {// if the bloc under is the player
    					g.drawImage(new Path(x * 16, y*16).getImage(), x * 16, y*16,null);
    					g.drawImage(new Rock(x * 16, y*16).getImage(), x * 16, y*16+16,null);
    		        	viewFrame.printMessage("GAME OVER !!");
    		        	System.exit(0);
    							
    				}
    				else if(map[x][y+1] == 50) {// if the block under the rock is a rock
		    				if(map[x-1][y] == 52 ) {
		    					if(map[x-1][y+1] == 52) {// check if left side of rocks is free
		    				
		    					g.drawImage(new Rock(x * 16, y * 16).getImage(), (x-1) * 16, (y+1) * 16,null);
		    					g.drawImage(new Path(x * 16, y * 16).getImage(), x * 16, y * 16,null);
		    					map[x-1][y+1] = 50;// down the rock
		    					map[x][y] = 52;//put path instead
		    					}
		    					else if(map[x-1][y+1] == 55) {// check if left side of rocks is a mob
				    				g.drawImage(new Diamond(x * 16, y * 16).getImage(), (x-1) * 16, (y+1) * 16,null);
			    					g.drawImage(new Path(x * 16, y * 16).getImage(), x * 16, y * 16,null);
			    					map[x-1][y+1] = 51;// down the rock
			    					map[x][y] = 52;//put path instead
			    					}
		    				}
			    			else if(map[x+1][y] == 52) {
			    					if(map[x+1][y+1] == 52) {// check if right side is free
			    				
			    					g.drawImage(new Rock(x * 16, y * 16).getImage(), (x+1) * 16, (y+1) * 16,null);
			    					g.drawImage(new Path(x * 16, y * 16).getImage(), x * 16, y * 16,null);
			    					map[x+1][y+1] = 50;// down the rock
			    					map[x][y] = 52;//put path instead
		
			    					}
			    					else if(map[x+1][y+1] == 55) {// check if right side is free
					    				
				    					g.drawImage(new Diamond(x * 16, y * 16).getImage(), (x+1) * 16, (y+1) * 16,null);
				    					g.drawImage(new Path(x * 16, y * 16).getImage(), x * 16, y * 16,null);
				    					map[x+1][y+1] = 51;// down the rock
				    					map[x][y] = 52;//put path instead
			
				    					}
			    				} else {
			    					g.drawImage(new Rock(x * 16, y * 16).getImage(), x * 16, y * 16,null);
			    			}
    				}
    			}
    			if(map[x][y] == 51) {//for each case in map[][] check if it is a diamond
    				if(map[x][y+1] == 52) {// if the block under the diamond is a path
    					if(Heros.X == x*16 && Heros.Y == y*16) {
    						g.drawImage(new Path(x * 16, y*16).getImage(), x * 16, y*16,null);
        					map[x][y] = 52;// place a path at the old place of the diamond
        			    	g.drawImage(Heros.image(), Heros.X, Heros.Y, null);

    					}else {
    					g.drawImage(new Diamond(x * 16, y*16).getImage(), x * 16, y*16+16,null);
    					g.drawImage(new Path(x * 16, y*16).getImage(), x * 16, y*16,null);
    					map[x][y+1] = 51;// down the Diamond
    					map[x][y] = 52;// place a path at the old place of the diamond
    					}
    				}
    			}
    			if(map[x][y] == 49) {
    				g.drawImage(new Wall(x * 16, y*16).getImage(), x * 16, y*16,null);
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
    
   

   
    /*
	 * (non-Javadoc)
	 *
	 * @see javax.swing.JComponent#paintComponent(java.awt.Graphics)
     */
    @Override
    protected void paintComponent(final Graphics graphics){
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
                //En fonction du nb, on affiche telle ou telle img
                switch (splitMsg[i]) {
                case 48:
                    try {
                        //i*16 pour les px
                        graphics.drawImage(new Dirt(i * 16, hauteur).getImage(), i * 16, hauteur,null);
                    } catch (IOException ex) {
                        Logger.getLogger(ViewPanel.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    break;
                    case 49:
                        try {
                            //i*16 pour les px
                            graphics.drawImage(new Wall(i * 16, hauteur).getImage(), i * 16, hauteur,null);
                        } catch (IOException ex) {
                            Logger.getLogger(ViewPanel.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        break;
                    case 50:
                        try {
                            //i*16 pour les px
                            graphics.drawImage(new Rock(i * 16, hauteur).getImage(), i * 16, hauteur,null);
                        } catch (IOException ex) {
                            Logger.getLogger(ViewPanel.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        break;
                    case 51:
                        try {
                            //i*16 pour les px
                            graphics.drawImage(new Diamond(i * 16, hauteur).getImage(), i * 16, hauteur,null);
                        } catch (IOException ex) {
                            Logger.getLogger(ViewPanel.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        break;
                    case 53:
                        try {
                            //i*16 pour les px
                            graphics.drawImage(new Enter(i * 16, hauteur).getImage(), i * 16, hauteur,null);
                        } catch (IOException ex) {
                            Logger.getLogger(ViewPanel.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        break;
                    case 54:
                        try {
                            //i*16 pour les px
                            graphics.drawImage(new Out(i * 16, hauteur).getImage(), i * 16, hauteur,null);
                        } catch (IOException ex) {
                            Logger.getLogger(ViewPanel.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        break;
                    case 55:	//Mob generation
                        try {
                            //i*16 pour les px
                            graphics.drawImage(new Mob(i * 16, hauteur).getImage(), i * 16, hauteur,null);
                        } catch (IOException ex) {
                            Logger.getLogger(ViewPanel.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        break;
                    default:
                        graphics.drawString("error", i*16, hauteur);
                }
            }
            hauteur += 16;
            y++;
           
        }

    
    
    }
          
    	
   
}
