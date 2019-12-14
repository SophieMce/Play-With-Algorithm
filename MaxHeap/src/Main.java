import java.util.Random;

public class Main {

    private static double testHeap(Integer[] testData, boolean isHeapify){
        long startTime = System.nanoTime();

        MaxHeap<Integer> maxHeap;
        if (isHeapify)
            maxHeap = new MaxHeap<>(testData);
        else
        {
            maxHeap = new MaxHeap<>();
            for (int num: testData)
                maxHeap.add(num);
        }

        int[] arr = new int[testData.length];
        for (int i = 0; i < testData.length; i++) {
            arr[i] = maxHeap.extractMax();
        }
        for (int i = 1; i < testData.length; i++) {
            if (arr[i - 1] < arr[i])
                throw new IllegalArgumentException("Error");
        }
        System.out.println("maxHeap has completed");

        long endTime = System.nanoTime();

        return (endTime - startTime) / 1000000000.0;
    }

    public static void main(String[] args) {
	// write your code here
        int n = 100000;
        Random m = new Random();

        Integer[] testData = new Integer[n];
        for (int i = 0; i < n; i++) {
            testData[i] = m.nextInt(Integer.MAX_VALUE);
        }
        double time1 = testHeap(testData, false);
        System.out.println("Without isHeapify:" + time1 + " s");

        double time2 = testHeap(testData, true);
        System.out.println("With isHeapify:" + time2 + " s");
    }
}
