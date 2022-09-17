package Objects;

import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import main.GamePanel;

public class Obj_Key extends SuperObject {
    
    GamePanel gp;

    public Obj_Key(GamePanel gp){

        this.gp = gp;

        name = "Key";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/Objects/Key.png"));
            uTool.scaleImage(image, gp.tileSize, gp.tileSize);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public BufferedImage getIcon(GamePanel gp){
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/Objects/Key1.png"));
            uTool.scaleImage(image, gp.tileSize, gp.tileSize);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return image;
    }
}
