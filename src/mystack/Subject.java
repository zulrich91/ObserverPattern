/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mystack;

/**
 *
 * @author ulrich
 */
import java.util.ArrayList;
import java.util.List;
import java.util.Observer;

public class Subject {

    private final List<Observer> observers = new ArrayList<Observer>();
    private Integer[] state;
    private StackModel stackModel;

    public Integer[] getState() {
        return state;
    }

    public void setState(Integer[] state) {
        this.state = state;
        initialiseState();
        notifyAllObservers();
    }

    public void attach(java.util.Observer observer) {
        observers.add(observer);

    }

    public void initialiseState() {
        stackModel = new StackModel(state);

    }

    public void notifyAllObservers() {
        observers.stream().map((observer) -> {
            stackModel.addObserver(observer);
            return observer;
        }).forEach((observer) -> {
            observer.update(stackModel, this.getState());
        });
    }
}
