package Model;


import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.Path;


public class ArrowGeneralization extends Shape {

    double arrowHeadSize;// размер кончика стрелки

    public ArrowGeneralization(){

// определение пропорций кончика стрелки

        //arrowHeadSize=1.5*Math.sqrt((startX-endX)*(startX-endX)+(startX-endX)*(startX-endX));
        //  if (arrowHeadSize<2)arrowHeadSize=0.146*start.distance(end);
        //  if (arrowHeadSize<2)arrowHeadSize=0.236*start.distance(end);
        arrowHeadSize=12.5;
    }
    @Override
    public void draw(GraphicsContext gc) {
        drawArrowstart( gc);//начало стрелки

        drawLine(gc);//линия

        drawArrowend(gc);//коней стрелки

    }

    public String toString() {

        return "основная стрелка";   }

    public void drawArrowstart(GraphicsContext gc) {
        gc.beginPath();
        gc.setFill(color);
        gc.setLineDashes();
        gc.strokeOval(startX-width, startY-width, 2*width, 2*width);   }



    public void drawLine(GraphicsContext gc) {

        gc.setStroke(color);

        gc.setLineWidth(width);

        gc.moveTo(startX, startY);

        gc.lineTo(endX, endY);

        gc.stroke();// соединить точки

    }

    public void drawArrowend(GraphicsContext gc) {

        gc.moveTo(endX, endY);

        double angle = Math.atan2((endY - startY), (endX - startX)) - Math.PI / 2.0;

        double sin = Math.sin(angle);

        double cos = Math.cos(angle);

        //point1

        double x1 = (- 1.0 / 2.0 * cos + Math.sqrt(3) / 2 * sin) * arrowHeadSize + endX;

        double y1 = (- 1.0 / 2.0 * sin - Math.sqrt(3) / 2 * cos) * arrowHeadSize + endY;

        //point2

        double x2 = (1.0 / 2.0 * cos + Math.sqrt(3) / 2 * sin) * arrowHeadSize + endX;

        double y2 = (1.0 / 2.0 * sin - Math.sqrt(3) / 2 * cos) * arrowHeadSize + endY;

        gc.lineTo(x1, y1);
       // gc.lineTo(x1, y2);
        gc.lineTo(x2, y2);
        gc.lineTo(endX, endY);
        gc.setFill(color);
        gc.fill();
        gc.setStroke(color);
        gc.stroke();
        //
        gc.closePath();
    }

}

