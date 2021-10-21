package Model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

abstract public class Shape implements Cloneable{
    Color color;
    double width;
    double startX;
    double startY;
    double endX;
    double endY;
    public void setColor(Color c){color=c;}
    public abstract void draw(GraphicsContext gc);
    public void setStartX(double d){this.startX=d;}
    public void setStartY(double d){this.startY=d;}
    public void setEndX(double d){this.endX=d;}
    public void setEndY(double d){this.endY=d;}
    @Override
    public Shape clone() {
        Object clone = null;
        try {
            clone = super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();   }
        return (Shape) clone;//
    }
}

