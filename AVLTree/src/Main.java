import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        System.out.println("Pride and prejudice");

        ArrayList<String> words = new ArrayList<>();
        if (FileOperation.readFile("Pride and prejudice.txt", words)) {
            System.out.println("Total words:" + words.size());

            //Test BST
            long startTime = System.nanoTime();

            BST<String, Integer> bst = new BST<String, Integer>();
            for (String word : words) {
                if (bst.contains(word))
                    bst.set(word, bst.get(word) + 1);
                else
                    bst.add(word, 1);
            }

            for (String word : words)
                bst.contains(word);

            long endTime = System.nanoTime();

            double time = (endTime - startTime) / 1000000000.0;
            System.out.println("BST: " + time + " s");

            //Test AVLTree
            startTime = System.nanoTime();

            AVLTree<String, Integer> avl = new AVLTree<>();
            for (String word : words) {
                if (avl.contains(word))
                    avl.set(word, avl.get(word) + 1);
                else
                    avl.add(word, 1);
            }

            for (String word : words)
                avl.contains(word);

            endTime = System.nanoTime();

            time = (endTime - startTime) / 1000000000.0;
            System.out.println("AVLTree: " + time + " s");

        }
        System.out.println();
    }
}
