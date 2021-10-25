package Model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class EmergencyExit extends Shape{

    @Override
    public void draw(GraphicsContext gc) {
        Image image = new Image("Img/Аварийный выход.png");
        gc.drawImage(image,startX,startY,endX-startX,endY-startY);
    }

    @Override
    public String toString() {
        return "аварийный выход";
    }
}

