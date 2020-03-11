/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chatboxUI;

import LiveRecognition.Recognizer;
import LiveRecognition.TextToSpeech;
import com.jfoenix.controls.JFXSlider;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXToggleButton;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TouchEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import mm.BoxMade;

/**
 * The FMXLsound contains methods to implement a voice conversation with users
 * by using methods in BoxMade class (for chatting) and Google Api(to recognize
 * voice input and convert response from text to voice)
 *
 * @author islane
 */
public class FXMLsound implements Initializable {

    @FXML
    private AnchorPane background;
    @FXML
    private AnchorPane body;
    @FXML
    private Button create;
    @FXML
    private AnchorPane header;
    @FXML
    private ImageView powerButton;
    @FXML
    private ImageView menubutton;
    @FXML
    private ImageView returnButton;
    private BoxMade bot;

    /**
     * the ClickCruiseButton method will react when use click the button to
     * speak it implement methods from google speech recognition and talk method
     * from BoxMade to generate response, convert the answer from text to voice
     * and play out
     *
     * @param event
     */
    @FXML
    private void ClickCruiseButton(ActionEvent event) {
        try {
            String sentence = Recognizer.streamingMicRecognize();//recognize sound
            String answer = getBot().talk(sentence); //get response
            int truncateIndex = answer.length();
            truncateIndex = answer.lastIndexOf("clientId", truncateIndex - 1);
            answer = answer.substring(0, truncateIndex);//cut the part of clientID and other distractive information
            TextToSpeech.toSpeech(answer);
            Media hit = new Media(new File("output.mp3").toURI().toString());
            MediaPlayer mediaPlayer = new MediaPlayer(hit);
            mediaPlayer.play();

        } catch (Exception ex) {
            Logger.getLogger(FXMLsound.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * ClickPowerButton will react will the user press the power button, and
     * exit the program
     *
     * @param event
     */
    @FXML
    private void ClickPowerButton(MouseEvent event) {
        System.exit(0);
    }

    /**
     * ClickMenu will load the Choice page and show the user if being clicked
     *
     * @param event
     */
    @FXML
    private void clickMenu(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/FXMLChoice.fxml"));
        Scene scene = new Scene(root);
        Stage playstage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        playstage.setScene(scene);
        playstage.show();
    }

    /**
     * the clickReturn Method will load the Choice page and show the user if
     * being clicked
     *
     * @param event
     * @throws IOException
     */
    private void clickReturn(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/FXMLChoice.fxml"));
        Scene scene = new Scene(root);
        Stage playstage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        playstage.show();
    }

    /**
     * the overwritten initialize method instantiate the chat box for use
     *
     * @param location
     * @param resources
     */
    public void initialize(URL location, ResourceBundle resources) {
        setBot(new BoxMade());
        getBot().createBot();
    }

    //the rest are getters and setters for JavaFX componenets used in the scene
    /**
     * @return the background
     */
    public AnchorPane getBackground() {
        return background;
    }

    /**
     * @param background the background to set
     */
    public void setBackground(AnchorPane background) {
        this.background = background;
    }

    /**
     * @return the body
     */
    public AnchorPane getBody() {
        return body;
    }

    /**
     * @param body the body to set
     */
    public void setBody(AnchorPane body) {
        this.body = body;
    }

    /**
     * @return the create
     */
    public Button getCreate() {
        return create;
    }

    /**
     * @param create the create to set
     */
    public void setCreate(Button create) {
        this.create = create;
    }

    /**
     * @return the header
     */
    public AnchorPane getHeader() {
        return header;
    }

    /**
     * @param header the header to set
     */
    public void setHeader(AnchorPane header) {
        this.header = header;
    }

    /**
     * @return the powerButton
     */
    public ImageView getPowerButton() {
        return powerButton;
    }

    /**
     * @param powerButton the powerButton to set
     */
    public void setPowerButton(ImageView powerButton) {
        this.powerButton = powerButton;
    }

    /**
     * @return the menubutton
     */
    public ImageView getMenubutton() {
        return menubutton;
    }

    /**
     * @param menubutton the menubutton to set
     */
    public void setMenubutton(ImageView menubutton) {
        this.menubutton = menubutton;
    }

    /**
     * @return the returnButton
     */
    public ImageView getReturnButton() {
        return returnButton;
    }

    /**
     * @param returnButton the returnButton to set
     */
    public void setReturnButton(ImageView returnButton) {
        this.returnButton = returnButton;
    }

    /**
     * @return the bot
     */
    public BoxMade getBot() {
        return bot;
    }

    /**
     * @param bot the bot to set
     */
    public void setBot(BoxMade bot) {
        this.bot = bot;
    }

}
