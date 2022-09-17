package Objects;

import javax.imageio.ImageIO;

import main.GamePanel;

public class Obj_Chest extends SuperObject{
        
    GamePanel gp;

    public Obj_Chest(GamePanel gp){

        this.gp = gp;
        
        name = "Chest";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/Objects/Box.png"));
            uTool.scaleImage(image, gp.tileSize, gp.tileSize);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
