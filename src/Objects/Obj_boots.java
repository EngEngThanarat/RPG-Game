package Objects;

import javax.imageio.ImageIO;

import main.GamePanel;

public class Obj_boots extends SuperObject{

    GamePanel gp;

    public Obj_boots(GamePanel gp){

        this.gp = gp;
        
        name = "Boots";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/Objects/speed.png"));
            uTool.scaleImage(image, gp.tileSize, gp.tileSize);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
