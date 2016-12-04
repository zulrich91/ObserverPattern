/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mystack;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

/**
 *
 * @author ulrich
 */
public class Stack extends Observable {
    
    private Integer[] myArray;
    private final int max;
    private static int top = -1;
    private static List<Observer> listOfObservers;

    public Stack(Integer[] stackArray) {

        
        myArray = stackArray;
        listOfObservers = new ArrayList<>();
        max = stackArray.length;
        hasChanged();
        notifyObservers(listOfObservers);
    }
    
    

    public Integer[] creat() {

        return myArray;

    }

    public void push(Integer value) {

        if (top == max - 1) {
            System.out.println("No memory");

        } else {
            top++;
            myArray[top] = value;
            System.out.println(value + " stored at position " + top);
            notifyObservers();
        }

    }

    public int pop() throws Exception{

        if (top < 0) {
            throw new Exception("Stack is Empty");
        } else {
            System.out.println("Deleting "+ myArray[top]);
            top--;
            notifyObservers();
        }
        return myArray[top];
    }

    public Integer top() throws Exception {

        try {
            return  myArray[top];
        } catch (Exception e) {
            throw new Exception("Stack is Empty");
        }
    }

    public void empty() {
        
        top = -1;
        notifyObservers();

    }

    public boolean isEmpty() {
        
        return top < 0;

    }

    public boolean isFull() {

        return top == max - 1;
    }

    public Integer[] elements(){
        try {
            return myArray;
        } catch (Exception e) {
            throw new ArrayIndexOutOfBoundsException("Array Empty");
        }
    }
    
    @Override
    public void addObserver(Observer observer){
     
        if (observer != null) {
            listOfObservers.add(observer);
        } else {
        }
    }
    
    public void setArray(Integer[] array){
       this.myArray = array;
    }
    
}
