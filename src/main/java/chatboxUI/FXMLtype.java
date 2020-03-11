/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chatboxUI;

import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import mm.BoxMade;

/**
 *
 * @author islane
 */
public class FXMLtype implements Initializable{
    private BoxMade bot;
    @FXML
    private AnchorPane background;
    @FXML
    private AnchorPane body;
    @FXML
    private AnchorPane header;
    @FXML
    private ImageView powerButton;
    @FXML
    private ImageView returnbutton;
    @FXML
    private ImageView menu;
    @FXML
    private Button submitbutton;
    @FXML
    private JFXTextArea responsearea;
    @FXML
    private JFXTextField inputarea;

    /**
     * ClickPowerButton will react will the user press the power button, and exit the
     * program
     * @param event 
     */
    @FXML
    private void ClickPowerButton(MouseEvent event) {
        System.exit(0);
    }
    /**
     * ClickMenu will load the Choice page and show the user if being clicked
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
     * the overwritten initialize method instantiate the chat box for use
     * @param location
     * @param resources 
     */
    public void initialize(URL location, ResourceBundle resources) {
        setBot(new BoxMade());
	getBot().createBot();
    }
    /**
     * the clickSubmit method will implement talk method from Boxmade object 
     * to get the chatting response, and display it in field for user when being clicked
     * @param event 
     */
    @FXML
    private void clickSubmit(ActionEvent event) {
        String question = getInputarea().getText();
        String answer = getBot().talk(question);
        int truncateIndex = answer.length();
        truncateIndex = answer.lastIndexOf("clientId", truncateIndex - 1);
        answer = answer.substring(0, truncateIndex); //cut off unnecessary information
        getResponsearea().setText(answer);
        getInputarea().clear();
    }
    /**
     * the keyReleased method will implement talk method from Boxmade object 
     * to get the chatting response, and display it in field for user when the button ENTER
     * being pressed
     * @param event 
     */
    @FXML
    private void keyReleased(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER){ //check if enter being pressed
            String question = getInputarea().getText();
        String answer = getBot().talk(question);
        int truncateIndex = answer.length();
        truncateIndex = answer.lastIndexOf("clientId", truncateIndex - 1);
        answer = answer.substring(0, truncateIndex);
            getResponsearea().setText(answer);
            getInputarea().clear();
        }
    }

    
    
    //the rests are getters and setters for JavaFX components in the scene
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
     * @return the returnbutton
     */
    public ImageView getReturnbutton() {
        return returnbutton;
    }

    /**
     * @param returnbutton the returnbutton to set
     */
    public void setReturnbutton(ImageView returnbutton) {
        this.returnbutton = returnbutton;
    }

    /**
     * @return the menu
     */
    public ImageView getMenu() {
        return menu;
    }

    /**
     * @param menu the menu to set
     */
    public void setMenu(ImageView menu) {
        this.menu = menu;
    }

    /**
     * @return the submitbutton
     */
    public Button getSubmitbutton() {
        return submitbutton;
    }

    /**
     * @param submitbutton the submitbutton to set
     */
    public void setSubmitbutton(Button submitbutton) {
        this.submitbutton = submitbutton;
    }

    /**
     * @return the responsearea
     */
    public JFXTextArea getResponsearea() {
        return responsearea;
    }

    /**
     * @param responsearea the responsearea to set
     */
    public void setResponsearea(JFXTextArea responsearea) {
        this.responsearea = responsearea;
    }

    /**
     * @return the inputarea
     */
    public JFXTextField getInputarea() {
        return inputarea;
    }

    /**
     * @param inputarea the inputarea to set
     */
    public void setInputarea(JFXTextField inputarea) {
        this.inputarea = inputarea;
    }
    
}
