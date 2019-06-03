package entity;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Diamond extends Object {

    private Image image;

    /*
		Here you can find every informations about the Diamonds coordinates. And also his sprite.
	}*/
    
     public Diamond(int x, int y) throws IOException {
        this.x = x;
        this.y = y;
        this.image = ImageIO.read(new File("../entity/Images/Diamond.png"));
    }

    public Diamond(int x, int y, String pathImage) throws IOException {
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
