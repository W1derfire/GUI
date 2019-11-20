import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Paint;
import javafx.scene.Node;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;

import javax.swing.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;


public class Main extends Application {

    static int clicks = 0;
    static String pass = "";
    static int scrollSize = 0;
    static ArrayList<Image> images = new ArrayList<>();
    static ImageView view = new ImageView();

    public void start(Stage stage){

        Label label = new Label("ImageMania!");
        Label otherLabel = new Label("The password is \"password\"");


        RadioButton age = new RadioButton("YES, I am above the age of 13.");

        ChoiceBox box = new ChoiceBox();

        //box.

        Label text = new Label("How do you like\nImageMania so far?");

        box.getItems().add("I love it!");
        box.getItems().add("It's OK.");
        box.getItems().add("I don't like it.");


        FileInputStream input = null;
        try {
             input = new FileInputStream("res/images/Abstract.jpeg");
        } catch (FileNotFoundException ie){
            System.out.println("There was an error. Oh well.");
        }

        if (input != null) {
            Image image = new Image(input);
            view.setImage(image);
            //view.setY(-200);
            //view.setFitWidth(500);
            //view.setFitHeight(333);
        }


        ScrollPane scroll = new ScrollPane();
        HBox scrollHBox = new HBox();

        scrollHBox.setMaxHeight(255);
        //scrollHBox.

        scroll.setContent(scrollHBox);

        Button button = new Button("Put in an image!");
        Button checker =  new Button("Done");
        Button next = new Button("Next image!");

        PasswordField password = new PasswordField();

        StackPane pane = new StackPane();

        FileChooser choice = new FileChooser();
        choice.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.png","*.jpeg","*.jpg")
                //,new FileChooser.ExtensionFilter("JPEG Files", "*.jpeg")
                //,new FileChooser.ExtensionFilter("JPG Files", "*.jpg")
        );


        stage.setTitle("ImageMania");

        scroll.setFitToHeight(true);
        scroll.setTranslateY(500);

        checker.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                pass = password.getText();
                System.out.println(password.getText());

                if (pass.equals("password") && age.isSelected()) {
                    System.out.println("Yay!");
                    pane.getChildren().remove(password);
                    pane.getChildren().remove(checker);
                    pane.getChildren().remove(age);
                    otherLabel.setText("Import away!");

                    label.setTextFill(Paint.valueOf("#ffffff"));
                    otherLabel.setTextFill(Paint.valueOf("#ffffff"));
                    otherLabel.setTranslateY(100);

                    pane.getChildren().add(button);
                    pane.getChildren().add(0, view);
                    pane.getChildren().add(scroll);



                    view.setTranslateY(-100);


                }

            }
        });

        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override

            public void handle(ActionEvent actionEvent) {
                //This is where you left off!
                File yourPicture = choice.showOpenDialog(stage);

                Image newImage = new Image(yourPicture.toURI().toString());
                clicks ++;
                ImageView v = new ImageView();

                v.setFitHeight(250);
                v.setPreserveRatio(true);

                v.setImage(newImage);

                v.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent mouseEvent) {


                        Image holder = view.getImage();
                        view.setImage(v.getImage());
                        v.setImage(holder);
                        view.setFitWidth(1000);
                        view.setPreserveRatio(true);

                        //System.out.println(v.getImage().toString());
                    }
                });

                scrollHBox.getChildren().add(v);


                if(clicks == 2){
                    pane.getChildren().add(text);
                    text.setTranslateX(200);
                    text.setTextFill(Paint.valueOf("#ffffff"));
                    pane.getChildren().add(box);
                    box.setTranslateY(50);
                    box.setTranslateX(200);
                }else if(clicks > 2 || !(((String) (box.getValue())).equals(""))){
                    pane.getChildren().remove(text);
                    pane.getChildren().remove(box);
                }

                //scroll.getChildren().add(v);

//                images.add(new Image(yourPicture.toURI().toString()));
//                //scrollSize = 1000 * images.size();
//
//                //System.out.println("We've made it this far.");
//                //view.setImage(yourPicture);
//
//                for(int i = 0; i < images.size(); i ++) {
//                    ImageView v = new ImageView();
//                    v.setImage(images.get(i));
//                    v.setPreserveRatio(true);
//                    v.setFitHeight(250);
//                    scroll.getChildrenUnmodifiable().add(v);
//                    //v.setTranslateY(350);
//                    ///pane.getChildren().add(v);
//                }

            }
        });



        otherLabel.setTranslateY(200);
        label.setTranslateY(-100);

        password.setMaxWidth(200);
        checker.setTranslateY(100);

        age.setTranslateY(50);

        pane.getChildren().add(label);
        pane.getChildren().add(otherLabel);
        pane.getChildren().add(password);
        pane.getChildren().add(checker);
        pane.getChildren().add(age);
        //pane.getChildren().add(scroll);


        Scene scene = new Scene(pane, 1000, 755);


        stage.setScene(scene);
        stage.show();




    }

    public static void main(String[] args) {


        System.out.println("I should be doing my homework!");

        Application.launch(args);



    }
}
