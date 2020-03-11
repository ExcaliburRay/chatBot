/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chatboxUI;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import mm.BoxMade;

/**
 * The FXMLChoice contains methods to interact with user, load and show
 * different interactive scene based on user choice
 *
 * @author islane
 */
public class FXMLChoice implements Initializable {

    private BoxMade bot; //the chatting bot
    @FXML
    private AnchorPane background;
    @FXML
    private AnchorPane body;
    @FXML
    private AnchorPane header;
    @FXML
    private ImageView powerButton;
    @FXML
    private Button type;
    @FXML
    private Button sound;

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

    public void initialize(URL location, ResourceBundle resources) {

    }

    /**
     * the clickType method will react when user click the button of text
     * chatting it will load the scene of type chatting interface, put it on the
     * stage it get, and display the interface
     *
     * @param event
     * @throws IOException
     */
    @FXML
    private void clickType(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/FXMLtype.fxml"));
        Scene scene = new Scene(root);
        Stage playstage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        playstage.setScene(scene);
        playstage.show();
    }

    /**
     * the clickSound method will react when user click the button of sound
     * chatting it will load the scene of voice chatting interface, put it on
     * the stage it get, and display the interface
     *
     * @param event
     * @throws IOException
     */
    @FXML
    private void clickSound(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/FXMLsound.fxml"));
        Scene scene = new Scene(root);
        Stage playstage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        playstage.setScene(scene);
        playstage.show();
    }

    /**
     * getBot() Method return the chatting bot
     *
     * @return the bot
     */
    public BoxMade getBot() {
        return bot;
    }

    /**
     * getBot() Method set the chatting bot
     *
     * @param bot the bot to set
     */
    public void setBot(BoxMade bot) {
        this.bot = bot;
    }

    //the following are getters and setters for element the JavaFX 
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
     * @return the type
     */
    public Button getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(Button type) {
        this.type = type;
    }

    /**
     * @return the sound
     */
    public Button getSound() {
        return sound;
    }

    /**
     * @param sound the sound to set
     */
    public void setSound(Button sound) {
        this.sound = sound;
    }

}
