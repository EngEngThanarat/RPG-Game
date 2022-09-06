package Objects;

import javax.imageio.ImageIO;

public class Obj_Key extends SuperObject {
    
    public Obj_Key(){
        name = "Key";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/Objects/Key.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
