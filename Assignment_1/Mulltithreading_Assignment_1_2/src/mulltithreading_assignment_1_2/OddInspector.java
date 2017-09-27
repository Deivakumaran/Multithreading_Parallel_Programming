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
public class OddInspector extends Thread {

    protected LinkedList<Integer> list;
    protected LinkedList<Integer> oddList;

    public OddInspector(LinkedList<Integer> list, LinkedList<Integer> oddList) {
        this.list = list;
        this.oddList = oddList;

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

            
            //checking odd number and add them in oddlist
            if (number % 2 != 0) {

                oddList.add(number);
            }

        }

    }

    public LinkedList<Integer> getOddList() {
        return oddList;
    }

}
