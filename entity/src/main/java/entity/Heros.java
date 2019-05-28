package entity;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;


@SuppressWarnings("serial")
public class Heros extends Canvas{
	
public static BufferedImage up, down, right, left;
    
    public static int X = 16;
    public static int Y = 32;
    public static int joueur = 1;
    
    public void player() {
    	try {
    		up = ImageIO.read(new File("C:\\Users\\Vincent\\git\\Boulder_Dash_Java\\Boulder_Dash_Java\\entity\\Images\\up.png"));
    		down = ImageIO.read(new File("C:\\Users\\Vincent\\git\\Boulder_Dash_Java\\Boulder_Dash_Java\\entity\\Images\\down.png"));
    		right = ImageIO.read(new File("C:\\Users\\Vincent\\git\\Boulder_Dash_Java\\Boulder_Dash_Java\\entity\\Images\\right.png"));
    		left = ImageIO.read(new File("C:\\Users\\Vincent\\git\\Boulder_Dash_Java\\Boulder_Dash_Java\\entity\\Images\\left.png"));
    	}catch(IOException ex){
    	ex.printStackTrace();	
    	}
    }
    
    @Override
    public void paint(Graphics g) {
    	super.paint(g);
        
        if (joueur == 1) {
            g.drawImage(down, X, Y, 16,16, this);
         
        }
        else if (joueur == 2) {
            g.drawImage(up, X, Y, 16,16, this);
    
        }
        else if (joueur == 3) {
            g.drawImage(left, X, Y, 16,16, this);
        
        }
        else if (joueur == 4) {
            g.drawImage(right, X, Y, 16,16, this);
          
        } 
        
    }
    
    
    
    
    
}
