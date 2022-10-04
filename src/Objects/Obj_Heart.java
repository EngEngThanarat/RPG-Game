package Objects;

import javax.imageio.ImageIO;
import main.GamePanel;

public class Obj_Heart extends SuperObject {
        
    GamePanel gp;

    public Obj_Heart(GamePanel gp){

        this.gp = gp;

        name = "Heart";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/Objects/heart_full.png"));
            image2 = ImageIO.read(getClass().getResourceAsStream("/Objects/heart_half.png"));
            image3 = ImageIO.read(getClass().getResourceAsStream("/Objects/heart_null.png"));
            image = uTool.scaleImage(image, gp.tileSize, gp.tileSize);
            image2 =uTool.scaleImage(image2, gp.tileSize, gp.tileSize);
            image3 =uTool.scaleImage(image3, gp.tileSize, gp.tileSize);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
