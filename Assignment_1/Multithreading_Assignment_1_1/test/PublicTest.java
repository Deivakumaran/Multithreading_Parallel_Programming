
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.util.LinkedList;
import java.util.Random;
import multithreading_assignment_1_1.ParallelMaximizer;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Deivakumaran
 */
public class PublicTest {

    private int threadCount = 10; // number of threads to run
    private ParallelMaximizer maximizer;

    @Test
    public void compareMax() {

        int size = 10000; // size of list
        LinkedList<Integer> mainList = new LinkedList<Integer>();

        LinkedList<Integer> inputList = new LinkedList<Integer>();
        Random rand = new Random();
        int serialMax = Integer.MIN_VALUE;
        int parallelMax = 0;
        // populate list with random elements
        for (int i = 0; i < size; i++) {
            int next = rand.nextInt();
            mainList.add(next);
            serialMax = Math.max(serialMax, next); // compute serialMax
        }
        // try to find parallelMax

        for (int i = 0; i < 10; i++) {
            try {
                
            //To execute the same list 10 times. Copy the mainlist into inputlist
                for (int k = 0; k < size; k++) {

                    inputList.add(mainList.get(k));
                }
                maximizer = new ParallelMaximizer(threadCount);
                parallelMax = maximizer.max(inputList);
                System.out.println("The Maximum of all Thread :" + parallelMax);
            } catch (InterruptedException e) {
                e.printStackTrace();
                fail("The test failed because the max procedure was interrupted unexpectedly.");
            } catch (Exception e) {
                e.printStackTrace();
                fail("The test failed because the max procedure encountered a runtime error: " + e.getMessage());
            }

            assertEquals("The serial max doesn't match the parallel max", serialMax, parallelMax);
        }
    }
}
