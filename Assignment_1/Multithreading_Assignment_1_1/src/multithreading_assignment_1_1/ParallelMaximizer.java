/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package multithreading_assignment_1_1;

/**
 *
 * @author Deivakumaran
 */
//import java.util.LinkedList;
import java.util.*;

/**
 * This class runs <code>numThreads</code> instances of
 * <code>ParallelMaximizerWorker</code> in parallel to find the maximum
 * <code>Integer</code> in a <code>LinkedList</code>.
 */
public class ParallelMaximizer {

    int numThreads;
    ArrayList<ParallelMaximizerWorker> workers; // = new ArrayList<ParallelMaximizerWorker>(numThreads);

    public ParallelMaximizer(int numThreads) {

        workers = new ArrayList<ParallelMaximizerWorker>(numThreads);
        this.numThreads = numThreads;//

    }

    public static void main(String[] args) {

        int numThreads = 4;
        // number of threads for the maximizer

        ParallelMaximizer maximizer;
        LinkedList<Integer> mainList = new LinkedList<Integer>();
        LinkedList<Integer> inputList = new LinkedList<Integer>();

        // populate the list
        // TODO: change this implementation to test accordingly
        int size = 10000; // size of list
        Random rand = new Random();

        // populate list with random elements
        for (int i = 0; i < size; i++) {
            int next = rand.nextInt();
            mainList.add(next);
        }

        //Execute the same linked list 10 times
        for (int j = 0; j < 10; j++) {
            try {

                maximizer = new ParallelMaximizer(numThreads);
                //To execute the same list 10 times. Copy the mainlist into inputlist
                for (int k = 0; k < size; k++) {

                    inputList.add(mainList.get(k));
                }
                System.out.println("The maximum is :" + maximizer.max(inputList) + '\n');

            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

    /**
     * Finds the maximum by using <code>numThreads</code> instances of
     * <code>ParallelMaximizerWorker</code> to find partial maximums and then
     * combining the results.
     *
     * @param list <code>LinkedList</code> containing <code>Integers</code>
     * @return Maximum element in the <code>LinkedList</code>
     * @throws InterruptedException
     */
    public int max(LinkedList<Integer> list) throws InterruptedException {
        int max = Integer.MIN_VALUE; // initialize max as lowest value

        // run numThreads instances of ParallelMaximizerWorker
        for (int i = 0; i < numThreads; i++) {
            ParallelMaximizerWorker parallelMaxi = new ParallelMaximizerWorker(list);
            workers.add(parallelMaxi);
            workers.get(i).start();
        }

        // wait for threads to finish
        for (int i = 0; i < workers.size(); i++) {
            workers.get(i).join();
        }

        // take the highest of the partial maximums
        // TODO: IMPLEMENT CODE HERE
        for (int i = 0; i < workers.size(); i++) {
            System.out.println("The partial Maximum of Thread " + " :"
                    + workers.get(i).getPartialMax());
            if (workers.get(i).getPartialMax() > max) {

                max = workers.get(i).getPartialMax();

            }

        }

        return max;
    }
}
