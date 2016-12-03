/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mystack;

import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.Scanner;

/**
 *
 * @author ulrich
 */
public class MyStackDemo implements Observer {
    
    private static StackModel observable;
    private static MyStackDemo observer;
    private MyStack myStack;
    private List<Observer> listOfObservers;
    private Subject subject;
    
    
    public MyStackDemo(Subject subject) {
        
        observer = new MyStackDemo(subject);
        this.subject = subject;
        this.subject.attach(observer);
    }
    
    public MyStackDemo() {
        
    }
    
    
    public void initialiseSubject(Subject subject) {
        this.subject = subject;
        this.subject.attach(this);
    }
    
    public static void main(String[] args) {
        
        Scanner input = new Scanner(System.in);
        observable = new StackModel(new Integer[4]);
        observable.addObserver(observer);
        
        
        for (int i = 0; i < 4; i++) {
            System.out.println("Enter a number ");
            observable.push(input.nextInt());
            System.out.println(" The Stack has "+observable.countObservers() +" Observers");
        }

    }


    @Override
    public void update(Observable o, Object o1) {
        System.out.println("Update called with Aray"+ o.countObservers());
    }
    
   
    
}
