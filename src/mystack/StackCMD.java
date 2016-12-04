/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mystack;

import java.util.Observable;
import java.util.Observer;
import java.util.Scanner;

/**
 *
 * @author ulrich
 */
public class StackCMD extends Thread implements Observer {

    Stack stack;
    Scanner input = new Scanner(System.in);

    public StackCMD(Stack stack) {
        this.stack = stack;
    }

    @Override
    public void run() {
        try {
            System.out.println("************* :::: Menu :::: **************** ");
            System.out.println("Enter The Number Corresponding To The Operation You Want to Perform");
            System.out.println(" 1- Push To Stack\n 2- Pop From Stack\n 3- Top Of The Stack\n 4- Empty The Stack\n 5- Check if Stack isEmpty\n 6- Check if Stack isFull");
            System.out.println("Make a Choice : ");
            int choice = input.nextInt();
            
            while (choice != 0) {                
                
            
            switch (choice) {
                case 1:
                    System.out.println("Enter item to be pushed ");
                    this.stack.push(input.nextInt());
                    break;
                case 2:
                    System.out.println("Item poped is " + this.stack.pop());
                    break;
                case 3:
                    System.out.println("Top Item is " + this.stack.top());
                    break;
                case 4:
                    this.stack.empty();
                    System.out.println("Stack has been emptied successfully");
                    break;
                case 5:
                    System.out.println(this.stack.isEmpty());
                    break;
                case 6:
                    System.out.println(this.stack.isFull());
                    break;
            }}
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void update(Observable o, Object o1) {
        try {
            System.out.println("Stack Updated");
            (new StackCMD(this.stack)).start();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}


