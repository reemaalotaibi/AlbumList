
package gallery;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import java.util.Date;
import static gallery.AlbumList.ListofAlbums;

public class GUI extends Application {

    AlbumList aLb = new AlbumList();
    TextField photoNa, photoYe, albumNa;
    CheckBox tagged;
    ComboBox albumComboBox;
    TextArea ta;
    Button addAlbum, addPhoto;

    @Override
    public void start(Stage myStage)  {

        
        GridPane pane = new GridPane();
        pane.setPadding(new Insets(10, 10, 10, 10));
        pane.setAlignment(Pos.TOP_CENTER);
        pane.add(new Label("Enter Album's name"), 0, 1);
        albumNa = new TextField("");
        albumNa.setPromptText("Album'S Name");
        pane.add(albumNa, 0, 2);
        pane.setVgap(4);
        pane.setHgap(-76);
        addAlbum = new Button("Add Album");
        pane.add(addAlbum, 1, 3);

        photoNa = new TextField("");
        photoNa.setPromptText("Photo's Name");
        photoYe = new TextField("");
        photoYe.setPromptText("Photo's Year");
        tagged = new CheckBox();
        pane.add(new Label("Photo's Name"), 0, 4);
        pane.add(photoNa, 0, 5);
        pane.add(new Label("Photo's Year"), 0, 6);
        pane.add(photoYe, 0, 7);
        pane.add(new Label("Is the photo tagged?"), 0, 8);
        pane.add(tagged, 0, 9);
        pane.add(new Label("Album's Name?"), 0, 10);
        albumComboBox = new ComboBox();
        pane.add(albumComboBox, 0, 11);
        albumComboBox.setPromptText("Select Album");
        addPhoto = new Button("Add Photo");
        pane.add(addPhoto, 1, 11);
        ta = new TextArea();
        pane.add(ta, 0,0);
        ta.setPrefColumnCount(30);
        ta.setPrefRowCount(35);

        Scene s = new Scene(pane, 410,600);
        myStage.setScene(s);
        myStage.setTitle("Gallery");
        myStage.show();

        aLb.ReadFromFile("TMA_M251_ReemaAlOtaibi_101816598.txt");

        albumComboBox.getItems().addAll(aLb.displayAlbumsNames());

        ta.clear();
      
            ta.appendText(aLb.displayAlbumLists());
        addAlbum.setOnAction(new Handler());
        addPhoto.setOnAction(new Handler());
    }

    class Handler implements EventHandler<ActionEvent> {

        public void handle(ActionEvent e) {

            if (e.getSource().equals(addAlbum)) {

                boolean flag = false;
                for (int i = 0; i < aLb.ListofAlbums.size(); i++) {
                    if (aLb.ListofAlbums.get(i).getTitle().equals(albumNa.getText())) {
                        flag = true;
                        break;
                    }
                }
                if (albumNa.getText().equals("")) {
                    showDialogBoxError("Empty fields aren't allowed.");
                } 
                else if (flag == true) {

                    try {
                        throw new DuplicateException("Duplicate");

                    } catch (DuplicateException ex) {
                        showDialogBoxError("The Album is already added");
                    }
                }

                else if (flag == false) {
                    aLb.createAlbum(albumNa.getText());
                    albumComboBox.getItems().add(albumNa.getText());
                    showDialogBox("The Album Added");

                    aLb.saveAlbum("TMA_M251_ReemaAlOtaibi_101816598.txt");
                    ta.clear();
                   
                    ta.appendText(aLb.displayAlbumLists());
                }

            } else if (e.getSource().equals(addPhoto)) {
                boolean flag = false;
                //checking if the photo is duplicated in the same album
                //by useing for loop
                String combo = "";
                try{
                 combo= albumComboBox.getValue().toString();
                }catch(NullPointerException ex){
                      
                  }
                for (Album r : ListofAlbums) {
                    if (r.getTitle().equals(combo)) {
                        for (Photo s : r.getPhotoalbum()) {
                            if (s.getFileName().equals(photoNa.getText())) {

                                flag = true;
                                break;
                            }
                        }
                    }
                }
                Date date = new Date();
                //if the photo has been found in the album it will throw exception of the user
                if (flag == true) {
                    try {
                        throw new DuplicateException("Duplicate");

                    } catch (DuplicateException ex) {
                        showDialogBoxError("photo already added");
                    }
                } else if (photoNa.getText().equals("") || photoYe.getText().equals("")) {
                    showDialogBoxError("Empty fields aren't allowed.");
                } else if (Integer.parseInt(photoYe.getText()) < 1 || Integer.parseInt(photoYe.getText()) > date.getYear() + 1900) {
                    showDialogBoxError("Wrong Year!!");
                } else {
                    String tagString;
                    if (tagged.isSelected()) {
                        tagString = "Tagged";
                    } else {
                        tagString = "Not-Tagged";
                    }
                    Photo ph = new Photo(photoNa.getText(), Integer.parseInt(photoYe.getText()), tagString);

                  try{
                    aLb.addPhoto(ph, albumComboBox.getValue().toString());
                  }catch(NullPointerException ex){
                      showDialogBoxError("You need to choose album");
                  }
                    aLb.saveAlbum("TMA_M251_ReemaAlOtaibi_101816598.txt");
                    ta.clear();
                    ta.appendText(aLb.displayAlbumLists());
                }
            }
        }
    }

    public static void main(String[] args) throws Exception {
        GUI.launch(args);

    }

    public void displayAll() {
        aLb.ReadFromFile("TMA_M251_ReemaAlOtaibi_101816598.txt");
        for (Album a : ListofAlbums) {
            System.out.println(a.toString());
        }
    }

    public void showDialogBox(String s) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setContentText(s);
        alert.showAndWait();
    }
     public void showDialogBoxError(String s) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setContentText(s);
        alert.showAndWait();
    }

}

