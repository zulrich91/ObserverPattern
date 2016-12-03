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
public class StackModel extends Observable {
    
    private Integer[] stack;
    private final int max;
    private static int top = -1;
    private static List<Observer> listOfObservers;

    public StackModel(Integer[] stackArray) {

        stack = stackArray;
        listOfObservers = new ArrayList<>();
        max = stackArray.length;
        hasChanged();
        notifyObservers(listOfObservers);
    }
    
    

    public Integer[] creat() {

        return stack;

    }

    public void push(Integer value) {

        if (top == max - 1) {
            System.out.println("No memory");

        } else {
            top++;
            stack[top] = value;
            System.out.println(value + " stored at position " + top);
        }

    }

    public void pop() throws Exception{

        if (top < 0) {
            throw new Exception("Stack is Empty");
        } else {
            System.out.println("Deleting "+ stack[top]);
            top--;
        }

    }

    public Integer top() throws Exception {

        try {
            return  stack[top];
        } catch (Exception e) {
            throw new Exception("Stack is Empty");
        }
    }

    public void empty() {
        
        top = -1;

    }

    public boolean isEmpty() {
        
        return top < 0;

    }

    public boolean isFull() {

        return top == max - 1;
    }

    public void elements(){
        
        if (top >= 0) {
            for (int i = 0; i <= top; i++) {
                System.out.println(stack[i]);
            }
 
        } else {
            System.out.println("Array is empty");
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
       this.stack = array;
    }
    
}
