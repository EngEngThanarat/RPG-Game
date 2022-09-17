package Objects;

import javax.imageio.ImageIO;
import main.GamePanel;

public class Obj_Door extends SuperObject {
        
    GamePanel gp;

    public Obj_Door(GamePanel gp){

        this.gp = gp;
        
        name = "Door";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/Objects/door.png"));
            uTool.scaleImage(image, gp.tileSize, gp.tileSize);
        } catch (Exception e) {
            e.printStackTrace();
        }
        collision = true;
    }
}
