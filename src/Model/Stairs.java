package Model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Stairs extends Shape{

    @Override
    public void draw(GraphicsContext gc) {
        Image image = new Image("Img/Лестницы.png");
        gc.drawImage(image,startX,startY,endX-startX,endY-startY);
    }

    @Override
    public String toString() {
        return "лестницы";
    }
}