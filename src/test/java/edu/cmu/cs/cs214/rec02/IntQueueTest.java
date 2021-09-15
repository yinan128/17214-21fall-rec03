package edu.cmu.cs.cs214.rec02;

import org.junit.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static org.junit.Assert.*;


/**
 * TODO: Write detailed unit tests for the {@link LinkedIntQueue} and
 * {@link ArrayIntQueue} classes, as described in the handout. The
 * {@link ArrayIntQueue} class contains a few bugs. Use the tests you wrote for
 * the {@link LinkedIntQueue} class to test the {@link ArrayIntQueue}
 * implementation. Refine your tests to achieve 100% line coverage and use them
 * to determine and correct the bugs!
 *
 * @author Alex Lockwood
 */
public class IntQueueTest {

    private IntQueue mQueue;

    /**
     * Called before each test.
     */
    @Before
    public void setUp() {
        // comment/uncomment these lines to test each class
//        mQueue = new LinkedIntQueue();
         mQueue = new ArrayIntQueue();
    }

    @Test
    public void testIsEmpty() {
        assertTrue(mQueue.isEmpty());
    }

    @Test
    public void testPeekEmptyQueue() {
        assertNull(mQueue.peek());
    }

    @Test
    public void testContent() throws IOException {
        InputStream in = new FileInputStream("src/test/resources/data.txt");
        try (Scanner scanner = new Scanner(in)) {
            scanner.useDelimiter("\\s*fish\\s*");

            List<Integer> correctResult = new ArrayList<>();
            while (scanner.hasNextInt()) {
                int input = scanner.nextInt();
                correctResult.add(input);
                System.out.println(input);
                mQueue.enqueue(input);
            }

            // Used boxed type to pacify assertEquals overload resolution
            for (Integer result : correctResult) {
                assertEquals(mQueue.dequeue(), result);
            }
        }
    }

    @Test
    public void testDequeueNonEmpty() {
        for (int i = 0; i < 5; i++) {
            mQueue.enqueue(i);
        }
        mQueue.dequeue();
        assertEquals(Integer.valueOf(1), mQueue.peek());
    }

    @Test
    public void testDequeueEmpty() {
        assertNull(mQueue.dequeue());
    }



    @Test
    public void testSize() {
        int size = 20;
        for (int i = 0; i < size; i++) {
            mQueue.enqueue(i);
        }
        assertEquals(size, mQueue.size());
    }

    @Test
    public void testClear() {
        int size = 20;
        for (int i = 0; i < size; i++) {
            mQueue.enqueue(i);
        }
        mQueue.clear();
        assertEquals(0, mQueue.size());
    }

    @Test
    public void testEnsureCapacity() {
        for (int i = 0; i < 5; i++) {
            mQueue.enqueue(i);
            // 0,1,2,3,4
        }
        mQueue.dequeue();
        for (int i = 5; i < 20; i++) {
            mQueue.enqueue(i);
            // 1,2,3,4,5,6,...,19
        }
        for (int i = 1; i < 20; i++) {
            assertEquals(Integer.valueOf(i), mQueue.dequeue());
        }

    }



}
