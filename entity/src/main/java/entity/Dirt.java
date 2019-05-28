package entity;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Dirt extends Object {

    private Image image;
    
   // private final String PATH_IMAGE = ;

    /*public Rock() {
		notify.controleur();
	}*/
    
     public Dirt(int x, int y) throws IOException {
        this.x = x;
        this.y = y;
        this.image = ImageIO.read(new File("C:\\Users\\Vincent\\git\\Boulder_Dash_Java\\Boulder_Dash_Java\\entity\\Images\\dirt.png"));
    }

    public Dirt(int x, int y, String pathImage) throws IOException {
        this.x = x;
        this.y = y;
        this.image = ImageIO.read(new File(pathImage));
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }
    
    
}
