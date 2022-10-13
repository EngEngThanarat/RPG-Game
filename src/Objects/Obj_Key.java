package Objects;

import Entity.Entity;
import main.GamePanel;

public class Obj_Key extends Entity {
    
    GamePanel gp;

    public Obj_Key(GamePanel gp){
        super(gp);

        name = "Key";
        down1= setup("/objects/Key.png");

    }

    //public BufferedImage getIcon(GamePanel gp){
    //    try {
    //        image = ImageIO.read(getClass().getResourceAsStream("/Objects/Key1.png"));
    //        uTool.scaleImage(image, gp.tileSize, gp.tileSize);
    //    } catch (Exception e) {
    //        e.printStackTrace();
    //    }
    //    return image;
    //}
}
