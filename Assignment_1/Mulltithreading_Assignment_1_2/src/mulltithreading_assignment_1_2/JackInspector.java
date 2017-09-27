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
public class JackInspector extends Thread {

    protected LinkedList<Integer> list;
    protected LinkedList<Integer> jackList;
    int sum;
 
    LinkedList<Integer>  sumList=new LinkedList<>();
    public JackInspector(LinkedList<Integer> list,LinkedList<Integer> jackList) {
        this.list = list;
        this.jackList=jackList;
        sum=0;
    }

    /**
     * Update <code>partialMax</code until the list is exhausted.
     */
    @Override
    public void run() {
        while (true) {
            int number=0;
            
            // check if list is not empty and removes the head
            // synchronization needed to avoid atomicity violation
            synchronized (list) {
                if (list.isEmpty()) {
                    return; // list is empty
                }
                number = list.remove();
            }

            sum=sum+number;

            //check if the number adds up to less than or eqal to 21 and add them in list
            if(sum<=21)
            {
                sumList.add(number);
            }
            
            //if sum adds up to 21 add them to jackList for printing in output
            if(sum==21){
                jackList=sumList;
              
            }
           
        }

    }

    public LinkedList<Integer> getJackList() {
        return jackList;
    }

}
