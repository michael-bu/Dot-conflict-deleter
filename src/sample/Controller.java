package sample;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import java.nio.file.*;

import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.scene.control.TextArea;

public class Controller {

    @FXML
    public TextArea dirBox;

    @FXML
    public TextArea outputBox;


    @FXML
    public void dirButtonAction(ActionEvent event) {

        DirectoryChooser directoryChooser = new DirectoryChooser();
        File selectedDirectory =
                directoryChooser.showDialog(Main.primaryStage);

        if (selectedDirectory == null) {
            dirBox.setText("No Directory selected");
        } else {
            dirBox.setText(selectedDirectory.getAbsolutePath());


            //           System.out.println("You clicked me!");
            //           dirBox.setText("Hello World!");

        }
    }

    public void removeConflictButtonAction(ActionEvent actionEvent) {
//        outputBox.appendText("Remove Conflict button pressed \n");

        File f = new File(dirBox.getText() + "\\@acre2\\acre.dll");
        if(f.exists() && !f.isDirectory()) {
            outputBox.appendText("This is the correct folder, deleting system32 \n");

            Path path= Paths.get(dirBox.getText());
            final List<Path> files=new ArrayList<>();
            try {
                Files.walkFileTree(path, new SimpleFileVisitor<Path>(){
                    @Override
                    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {

                        if (file.toString().endsWith(".conflict")) {
                            System.out.println(file.getFileName());
                            outputBox.appendText(file.toString() + " deleted \n");
                            Files.delete(file);
                        }
                        return FileVisitResult.CONTINUE;
                    }
                });
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
        else
        {
            outputBox.appendText("This isn't the correct folder \n");
        }
    }

    public void renameButtonAction(ActionEvent actionEvent) {
        outputBox.appendText("Rename Conflict button pressed (this doesn't do anything yet) \n");

/**        Path path= Paths.get(dirBox.getText());
        final List<Path> files=new ArrayList<>();
        try {
            Files.walkFileTree(path, new SimpleFileVisitor<Path>(){
                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
//                    if(!attrs.isDirectory()){
//                        files.add(file);

                        if (file.toString().endsWith(".conflict")) {
                            System.out.println(file.getFileName());
                            outputBox.appendText(file.toString() + " found \n");
                        }
//                    }
                    return FileVisitResult.CONTINUE;
                }
            });
        }
        catch (IOException e) {
            e.printStackTrace();
        }
/**        for (Path entry: files) {
                System.out.println(entry.toString());
        }
*/    }


    public void clearlogButtonAction(ActionEvent actionEvent) {
        outputBox.setText("");
    }

    public void savelogButtonAction(ActionEvent actionEvent) {

        outputBox.appendText("Log saved \n");

        //Set extension filter
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilter =
                new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
        fileChooser.getExtensionFilters().add(extFilter);

        //Show save file dialog
        File file = fileChooser.showSaveDialog(Main.primaryStage);

        if (file != null) {
            SaveFile(outputBox.getText(), file);


        }
    }
    public void SaveFile(String content, File file) {
        try {
            FileWriter fileWriter;

            fileWriter = new FileWriter(file);
            fileWriter.write(content);
            fileWriter.close();
        } catch (IOException ex) {
            Logger.getLogger(Controller.class
                    .getName()).log(Level.SEVERE, null, ex);

        }
    }
}