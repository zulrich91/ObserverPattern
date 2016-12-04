/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mystack;

import java.net.URL;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import org.controlsfx.dialog.Dialogs;

/**
 *
 * @author ulrich
 */
public class StackController implements Initializable, Observer {

    @FXML
    private Label label;
    @FXML
    private TextField numberToPush;

    ObservableList<Integer> items;

    @FXML
    private ListView<Integer> listView;

    private Stack stack;
    private Integer top;
    private StackController observer;
    @FXML
    private Button pushBtn;
    @FXML
    private Button popBtn;
    @FXML
    private Button isEmptyBtn;
    @FXML
    private Button isFullBtn;
    @FXML
    private Button emptyBtn;
    @FXML
    private Button topBtn;
    Integer[] sampleArray = new Integer[5];
    StackCMD stackCmd;
    @FXML
    private Button refreshBtn;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        this.listView.setVisible(false);
        this.stack = new Stack(new Integer[5]);
        this.items = this.listView.getItems();
        disableBtn();
    }

    @FXML
    private void pushBtnClicked(MouseEvent event) throws Exception {

        try {
            this.stack.push(Integer.parseInt(this.numberToPush.getText().toString()));
            this.items.add(0, this.stack.top());
        } catch (Exception e) {
            Dialogs.create().title("Message").message("Enter a number to push").showInformation();
        }

    }

    @FXML
    private void popBtnClicked(MouseEvent event) throws Exception {

        try {
            this.stack.pop();
            this.items.remove(0);
            this.numberToPush.setText(this.stack.top().toString());
        } catch (Exception e) {
            Dialogs.create().title("Message").message("Stack is Empty").showInformation();
            System.out.println(e.getMessage());
            this.numberToPush.clear();
        }

    }

    @FXML
    private void isEmptyBtnClicked(MouseEvent event) {

        if (this.stack.isEmpty()) {
            Dialogs.create().title("Message").message("Stack is Empty").showInformation();
        } else {
            Dialogs.create().title("Message").message("Stack is not Empty").showInformation();
        }
    }

    @FXML
    private void isFullBtnClicked(MouseEvent event) {

        if (this.stack.isFull()) {
            Dialogs.create().title("Message").message("Stack is Full").showInformation();
        } else {
            Dialogs.create().title("Message").message("Stack is not Full").showInformation();
        }

    }

    @FXML
    private void emptyBtnClicked(MouseEvent event) throws Exception {

        this.stack.empty();
        Dialogs.create().title("Message").message("Stack Emptied Successfully").showInformation();
        this.listView.getItems().removeAll(this.items);
        this.numberToPush.clear();

    }

    public void loadList() {

        this.listView.getItems().addAll(this.items);
    }

    @FXML
    private void createBtnCliked(MouseEvent event) {

        this.listView.setVisible(true);
        this.pushBtn.setDisable(false);
        this.popBtn.setDisable(false);
        this.topBtn.setDisable(false);
        this.isEmptyBtn.setDisable(false);
        this.isFullBtn.setDisable(false);
        this.emptyBtn.setDisable(false);
        this.numberToPush.setDisable(false);

        this.stack = new Stack(sampleArray);
        this.stackCmd = new StackCMD(stack);
        this.stack.addObserver(this);
        this.stack.addObserver(stackCmd);
        this.stackCmd.start();

    }

    @FXML
    private void topBtnClicked(MouseEvent event) throws Exception {

        try {
            top = this.stack.top();
            Dialogs.create().title("Message").message("top is " + top).showInformation();
        } catch (Exception e) {

            Dialogs.create().title("Message").message("Stack is Empty").showInformation();
            System.out.println(e.getMessage());

        }

    }

    @Override
    public void update(Observable o, Object o1) {
        Platform.runLater(() -> {
            System.out.println("Something happened in the Stack class " + o1);
            if (Integer.valueOf(o1.toString()) == 0) {
                try {
                    items.add(0, this.stack.top());
                } catch (Exception ex) {
                    Logger.getLogger(StackController.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (Integer.valueOf(o1.toString()) == 1) {
                items.remove(0);
            } else {
                items.clear();
            }
        });

    }

    public void disableBtn() {
        this.pushBtn.setDisable(true);
        this.popBtn.setDisable(true);
        this.isEmptyBtn.setDisable(true);
        this.isFullBtn.setDisable(true);
        this.topBtn.setDisable(true);
        this.emptyBtn.setDisable(true);
        this.numberToPush.setDisable(true);
    }

    @FXML
    private void refreshBtnClicked(MouseEvent event) {
        try {
            for (int i =0; i<this.stack.elements().length; i++) {
                if (this.stack.elements()[i] != null) {
                    this.items.add(0, this.stack.elements()[i]);
                }
            }

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}
