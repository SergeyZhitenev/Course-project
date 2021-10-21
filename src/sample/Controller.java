package sample;

import Model.*;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;


import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    public Canvas canvas;
    public ListView LV;
    public ColorPicker ColPic;
    private Shape currentarrow;
    public ChoiceBox<String> arrows;
    Image image;
    ArrayList<Shape> ls=new ArrayList<Shape>();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        ObservableList<Shape> content= FXCollections.observableArrayList(new FactoryLine().CreateArrow(canvas),new FactoryAssotioon().CreateArrow(canvas),
                new FactoryDependence().CreateArrow(canvas), new FactoryGeneralization().CreateArrow(canvas));
        LV.setItems(content);
        LV.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        LV.setCellFactory(new Callback<ListView<Shape>, ListCell<Shape>>() {
                                    @Override
                                    public ListCell<Shape> call(ListView<Shape> list) {
// здесь можно включить любую обработку
                                        ShapeCell sh= new ShapeCell();
                                        //sh.updateItem(new ArrowAssotioon(),true);
                                       // for (int i=0;i<content.size();i++) {
                                            //sh.updateItem(content.get(content.size()-1),true);
                                       // }
                                        return sh;
                                    }
                                }
        );
        currentarrow=content.get(0);
        LV.getSelectionModel().select(0);
        LV.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                System.out.println(LV.getItems().toString());
                currentarrow=content.get(LV.getSelectionModel().getSelectedIndex());
                arrows.setValue(currentarrow.toString());
            }
        });
        arrows.getItems().addAll("линия","сплошная стрелка", "пунктирная стрелка", "основная стрелка");
        arrows.setValue("линия");
        arrows.getSelectionModel().selectedIndexProperty().addListener( new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                currentarrow=content.get(arrows.getSelectionModel().getSelectedIndex());
                LV.getSelectionModel().select(arrows.getSelectionModel().getSelectedIndex());
            }
        });
    }
//    public void onBegin(MouseEvent mouseEvent) {
//        currentarrow.setStartX(mouseEvent.getX());
//        currentarrow.setStartY(mouseEvent.getY());
//        currentarrow.draw(canvas.getGraphicsContext2D());
//
//    }

    public void onRun(MouseEvent mouseEvent) {
        currentarrow.setStartX(mouseEvent.getX());
        currentarrow.setStartY(mouseEvent.getY());
    }

    public void onEnd(MouseEvent mouseEvent) {
        currentarrow.setEndX(mouseEvent.getX());
        currentarrow.setEndY(mouseEvent.getY());
        System.out.println(currentarrow.toString());
        ls.add(currentarrow.clone());
        Paint();
       // ls.add(currentarrow.clone());
    }

    private void Paint(){
        canvas.getGraphicsContext2D().clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        canvas.getGraphicsContext2D().drawImage(image,0,0,canvas.getWidth(),canvas.getHeight());
        for (Shape a:ls) {
            a.draw(canvas.getGraphicsContext2D());
        }
    }

    public void onBegin(MouseEvent mouseEvent) {
        Paint();
        currentarrow.setEndX(mouseEvent.getX());
        currentarrow.setEndY(mouseEvent.getY());
        currentarrow.draw(canvas.getGraphicsContext2D());
        currentarrow.setColor(ColPic.getValue());
//        canvas.getGraphicsContext2D().clearRect(1,1,1000,1000);
//        for (var a:ls) {
//            a.draw(canvas.getGraphicsContext2D());
//        }
//        currentarrow.draw(canvas.getGraphicsContext2D());
    }

    public void Open(ActionEvent actionEvent) {
        image=FileWorkSingleton.getInstance().OpenImg(canvas);
        Paint();
    }

    public void Save(ActionEvent actionEvent) {
        FileWorkSingleton.getInstance().exportImage(canvas);
    }

    public void Close(ActionEvent actionEvent) {
        Platform.exit();
    }

    public void DelLast(ActionEvent actionEvent) {
        if (ls.size()<=0)return;
        ls.remove(ls.size()-1);
        Paint();
    }

    public void Help(ActionEvent actionEvent) {
        Alert alert=new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText("Программа разработана в целях обучения \n Студент разработчик: Житенев Сергей \n Контакты:  89005553555");
        alert.setTitle("О программе");
        alert.show();
    }

}

