package Objects;

import javax.imageio.ImageIO;

public class Obj_Chest extends SuperObject{
        
    public Obj_Chest(){
        name = "Chest";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/Objects/Box.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
