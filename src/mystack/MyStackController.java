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
public class MyStackController implements Initializable, Observer {

    @FXML
    private Label label;
    @FXML
    private TextField numberToPush;

    ObservableList<Integer> items;

    @FXML
    private ListView<Integer> listView;

    private StackModel observable;
    private Integer top;
    private MyStackController observer;
    private Subject subject;
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

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        listView.setVisible(false);
        observable = new StackModel(new Integer[5]);
        //observer = new MyStackController();
        // observable.addObserver(observer);
        items = listView.getItems();
        disableBtn();
    }

    public void initialiseSubject(Subject subject) {
        this.subject = subject;
        this.subject.attach(this);
    }

    @FXML
    private void pushBtnClicked(MouseEvent event) throws Exception {

        try {
            observable.push(Integer.parseInt(numberToPush.getText().toString()));
            items.add(0, observable.top());
            //items.add(0,Integer.valueOf(numberToPush.getText().toString()));
        } catch (Exception e) {
            Dialogs.create().title("Message").message("Enter a number to push").showInformation();
        }

    }

    @FXML
    private void popBtnClicked(MouseEvent event) throws Exception {

        try {
            observable.pop();
            items.remove(0);
            numberToPush.setText(observable.top().toString());
        } catch (Exception e) {
            Dialogs.create().title("Message").message("Stack is Empty").showInformation();
            System.out.println(e.getMessage());
            numberToPush.clear();
        }

    }

    @FXML
    private void isEmptyBtnClicked(MouseEvent event) {

        if (observable.isEmpty()) {
            Dialogs.create().title("Message").message("Stack is Empty").showInformation();
        } else {
            Dialogs.create().title("Message").message("Stack is not Empty").showInformation();
        }
//        if (listView.getItems().size() == 0) {
//           Dialogs.create().title("Message").message("Stack is Empty").showInformation(); 
//        } else {
//            Dialogs.create().title("Message").message("Stack is not Empty").showInformation();
//        }

    }

    @FXML
    private void isFullBtnClicked(MouseEvent event) {

        if (observable.isFull()) {
            Dialogs.create().title("Message").message("Stack is Full").showInformation();
        } else {
            Dialogs.create().title("Message").message("Stack is not Full").showInformation();
        }
//        if (listView.getItems().size() == 10) {
//            Dialogs.create().title("Message").message("Stack is Full").showInformation();
//        } else {
//            Dialogs.create().title("Message").message("Stack is not Full").showInformation();
//        }

    }

    @FXML
    private void emptyBtnClicked(MouseEvent event) throws Exception {

        observable.empty();
        //items.add(0,stack.top());
        Dialogs.create().title("Message").message("Stack Emptied Successfully").showInformation();
        listView.getItems().removeAll(items);
        numberToPush.clear();

    }

    public void loadList() {

        listView.getItems().addAll(items);
    }

    @FXML
    private void createBtnCliked(MouseEvent event) {

        observable.creat();
        listView.setVisible(true);
        pushBtn.setDisable(false);
        popBtn.setDisable(false);
        topBtn.setDisable(false);
        isEmptyBtn.setDisable(false);
        isFullBtn.setDisable(false);
        emptyBtn.setDisable(false);
        numberToPush.setDisable(false);
    }

    @FXML
    private void topBtnClicked(MouseEvent event) throws Exception {

        try {
            top = observable.top();
            Dialogs.create().title("Message").message("top is " + top).showInformation();
        } catch (Exception e) {

            Dialogs.create().title("Message").message("Stack is Empty").showInformation();
            System.out.println(e.getMessage());

        }
//        if (listView.getItems().size() == 0 ) {
//                    Dialogs.create().title("Message").message("Stack is Empty").showInformation();
//
//        } else {
//                    Dialogs.create().title("Message").message("top is "+ items.get(0)).showInformation();
//
//        }
    }

    @Override
    public void update(Observable o, Object o1) {
        System.out.println("Update called with Array" + o.countObservers());
    }

    public void disableBtn(){
        pushBtn.setDisable(true);
        popBtn.setDisable(true);
        isEmptyBtn.setDisable(true);
        isFullBtn.setDisable(true);
        topBtn.setDisable(true);
        emptyBtn.setDisable(true);
        numberToPush.setDisable(true);
    }
}
