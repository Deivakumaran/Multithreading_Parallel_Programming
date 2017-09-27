/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mulltithreading_assignment_1_2;

/**
 *
 * @author Deivakumaran
 */
import java.util.LinkedList;

/**
 * Given a <code>LinkedList</code>, this class will find the maximum over a
 * subset of its <code>Integers</code>.
 */
public class OrderInspector extends Thread {

    protected LinkedList<Integer> list;

    protected LinkedList<Integer> orderList;
    //total number of element in linked list

    int previous = Integer.MIN_VALUE;

    public OrderInspector(LinkedList<Integer> list, LinkedList<Integer> orderList) {
        this.list = list;
        this.orderList = orderList;

    }

    /**
     * Update <code>partialMax</code until the list is exhausted.
     */
    @Override
    public void run() {
        while (true) {
            int number = 0;
            // check if list is not empty and removes the head
            // synchronization needed to avoid atomicity violation
            synchronized (list) {
                if (list.isEmpty()) {
                    return; // list is empty
                }
                number = list.remove();

            }

            //checking oder number which is greter than previous number and add them in order list
            if (number > previous) {
                previous = number;
                orderList.add(previous);
            }

        }

    }

    public LinkedList<Integer> getOrderList() {
        return orderList;
    }

}
