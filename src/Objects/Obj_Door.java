package Objects;

import javax.imageio.ImageIO;

public class Obj_Door extends SuperObject {
        
    public Obj_Door(){
        name = "Door";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/Objects/door.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        collision = true;
    }
}
