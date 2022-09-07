package Objects;

import javax.imageio.ImageIO;

public class Obj_boots extends SuperObject{
    public Obj_boots(){
        name = "Boots";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/Objects/speed.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
