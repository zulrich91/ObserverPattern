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
public class MyObserverDemo {

    private static Integer[] state;

    
    public static void main(String[] args) {

        Subject subject = new Subject();
        
        new MyStackController().initialiseSubject(subject);
        MyStackDemo myStackDemo = new MyStackDemo(subject);
        
        
        MyStack.launch(MyStack.class, args);
        myStackDemo.main(args);

        state = new Integer[]{1, 2, 3, 4};
        subject.setState(state);
        state = new Integer[]{1, 2, 3, 4, 5};
        subject.setState(state);
    }

}
