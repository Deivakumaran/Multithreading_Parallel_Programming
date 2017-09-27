/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mulltithreading_assignment_1_2;

import java.util.LinkedList;
import java.util.Random;

/**
 *
 * @author Deivakumaran
 */
public class Mulltithreading_Assignment_1_2 {

    // Linked list declaration to store results
    LinkedList<Integer> oddList;
    LinkedList<Integer> evenList;
    LinkedList<Integer> orderList;
    LinkedList<Integer> jackList;

    public Mulltithreading_Assignment_1_2() {
        oddList = new LinkedList<>();
        evenList = new LinkedList<>();
        orderList = new LinkedList<>();
        jackList = new LinkedList<>();

    }

    public LinkedList<Integer> getOddList() {
        return oddList;
    }

    public void setOddList(LinkedList<Integer> oddList) {
        this.oddList = oddList;
    }

    public LinkedList<Integer> getEvenList() {
        return evenList;
    }

    public void setEvenList(LinkedList<Integer> evenList) {
        this.evenList = evenList;
    }

    public LinkedList<Integer> getOrderList() {
        return orderList;
    }

    public void setOrderList(LinkedList<Integer> orderList) {
        this.orderList = orderList;
    }

    public LinkedList<Integer> getJackList() {
        return jackList;
    }

    public void setJackList(LinkedList<Integer> jackList) {
        this.jackList = jackList;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws InterruptedException {
        // TODO code application logic here
        LinkedList<Integer> mainList = new LinkedList<>();
        LinkedList<Integer> inputList = new LinkedList<>();
      

        int size = 1000; // size of list
        Random rand = new Random();

        // populate list with random elements
        for (int i = 0; i < size; i++) {

            // int next = rand.nextInt(10000 - 10 + 1) + 10;//Generate Random Number in range min=10, max=1000
            int next = rand.nextInt();
            mainList.add(next);
        }

        //Run the same list 10 times 
        for (int j = 0; j < 10; j++) {
            Mulltithreading_Assignment_1_2 assignment = new Mulltithreading_Assignment_1_2();

            //To execute the same list 10 times. Copy the mainlist into inputlist
            for (int k = 0; k < mainList.size(); k++) {

                inputList.add(mainList.get(k));
            }

            EvenInspector evenInspector = new EvenInspector(inputList, assignment.getEvenList());
            OddInspector oddInspector = new OddInspector(inputList, assignment.getOddList());
            OrderInspector orderInspector = new OrderInspector(inputList, assignment.getOrderList());
            JackInspector jackInspector = new JackInspector(inputList, assignment.getJackList());

            //Starting all the inspector thread;
            evenInspector.start();
            oddInspector.start();
            orderInspector.start();
            jackInspector.start();

            // wait for threads to finish
            evenInspector.join();
            oddInspector.join();
            orderInspector.join();
            jackInspector.join();

            System.out.print("The Even List is :");
            for (int even : evenInspector.getEvenList()) {
                System.out.print(even + "  ");
            }

            if (evenInspector.getEvenList().isEmpty()) {
                System.out.println("Failed");
            }

            System.out.println('\n');

            System.out.print("The Odd List is :");
            for (int odd : oddInspector.getOddList()) {
                System.out.print(odd + "  ");
            }
            if (oddInspector.getOddList().isEmpty()) {
                System.out.println("Failed");
            }

            System.out.println('\n');
            System.out.print("The Order List is :");
            for (int order : orderInspector.getOrderList()) {
                System.out.print(order + "  ");
            }

            if (orderInspector.getOrderList().isEmpty()) {

                System.out.println("Failed");
            }

            System.out.println('\n');

            System.out.print("The Jack List is :");
            for (int jack : jackInspector.getJackList()) {
                System.out.print(jack + "  ");
            }

            if (jackInspector.getJackList().isEmpty()) {
                System.out.println("Failed");
            }

            System.out.println('\n');
            System.out.println("------------------------------------------------------------------------------------------------------------------");

        }
    }
}
