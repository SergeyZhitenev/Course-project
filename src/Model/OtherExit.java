package Model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class OtherExit extends Shape{

    @Override
    public void draw(GraphicsContext gc) {
        Image image = new Image("Img/ЭвакуационныйВыход.png");
        gc.drawImage(image,startX,startY,endX-startX,endY-startY);
    }

    @Override
    public String toString() {
        return "эвакуационный выход";
    }
}

