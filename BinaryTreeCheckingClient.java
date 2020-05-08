import lk2.Date_;
import lk5.BST;
import lk5.PeopleGenerator;

public class BinaryTreeCheckingClient {
    public static int MAX = 500;
    public static void main(String[] args) {
        System.out.println("IS BST?");
        checkingCase1();
        checkingCase2();
        combinedChecking();
    }

    public static void checkingCase1() {
        System.out.println("****************");
        System.out.println("CASE 1 - checking by method isBST");
        System.out.println("****************");
        CustomBinaryTree<Date_, String> bt = new CustomBinaryTree<>();
        BST<Date_, String> bst = new BST<>();
        PeopleGenerator generator = new PeopleGenerator();

        // generate random data to tree
        for (int i = 0; i < MAX; i++) {
            Date_ date = new Date_();
            String person = generator.generatePerson();
            bt.put(date, person);
            bst.put(date, person);
        }
        System.out.println("(class BST): " + bst.isBst());
        System.out.println("(class CustomBinaryTree): " + bt.isBst());


        bt.customPut(new Date_(), generator.generatePerson());
        System.out.println("(class CustomBinaryTree) after one random put: " + bt.isBst());
        // add data to CustomBinaryTree to random place
        for (int i = 0; i < MAX; i++) {
            Date_ date = new Date_();
            String person = generator.generatePerson();
            bt.customPut(date, person);
        }
        System.out.println("(class CustomBinaryTree) after many random put: " + bt.isBst());
    }

    public static void checkingCase2() {
        System.out.println("****************");
        System.out.println("CASE 2 - checking by isSorted");
        System.out.println("****************");

        CustomBinaryTree<Date_, String> bt = new CustomBinaryTree<>();
        BST<Date_, String> bst = new BST<>();
        PeopleGenerator generator = new PeopleGenerator();

        // generate random data to tree
        for (int i = 0; i < MAX; i++) {
            Date_ date = new Date_();
            String person = generator.generatePerson();
            bt.put(date, person);
            bst.put(date, person);
        }

        System.out.println("(class BST): " + isSorted(bst.keys()));
        System.out.println("(class CustomBinaryTree): " + isSorted(bt.keys()));

        bt.customPut(new Date_(), generator.generatePerson());
        System.out.println("(class CustomBinaryTree) after one random put: " + isSorted(bt.keys()));

        // add data to CustomBinaryTree to random place
        for (int i = 0; i < MAX; i++) {
            Date_ date = new Date_();
            String person = generator.generatePerson();
            bt.customPut(date, person);
        }
        System.out.println("(class CustomBinaryTree) after many random put: " + isSorted(bt.keys()));
    }

    public static void combinedChecking() {
        System.out.println("****************");
        System.out.println("CASE 3 - combined checking");
        System.out.println("****************");

        CustomBinaryTree<Date_, String> bt = new CustomBinaryTree<>();
        BST<Date_, String> bst = new BST<>();
        PeopleGenerator generator = new PeopleGenerator();

        // generate random data to tree
        for (int i = 0; i < MAX; i++) {
            Date_ date = new Date_();
            String person = generator.generatePerson();
            bt.put(date, person);
            bst.put(date, person);
        }

        System.out.println("-- (class BST) --");
        printSuccess(bt.isBst(), isSorted(bt.keys()));

        System.out.println("-- (class CustomBinaryTree) --");
        printSuccess(bt.isBst(), isSorted(bt.keys()));
        bt.customPut(new Date_(), generator.generatePerson());
        System.out.println("-- (class CustomBinaryTree) after one random put --");
        printSuccess(bt.isBst(), isSorted(bt.keys()));

        // add data to CustomBinaryTree to random place
        for (int i = 0; i < MAX; i++) {
            Date_ date = new Date_();
            String person = generator.generatePerson();
            bt.customPut(date, person);
        }
        System.out.println("-- (class CustomBinaryTree) after many random puts --");
        printSuccess(bt.isBst(), isSorted(bt.keys()));
    }

    public static boolean isSorted(Iterable<Date_> a) {
        Date_ prevDate = null;
        for (Date_ currentDate: a) {
            if (prevDate == null) {
                prevDate = currentDate;
                continue;
            }
            if (prevDate.compareTo(currentDate) >= 0) {
                return false;
            }
            prevDate = currentDate;
        }
        return true;
    }


    public static void printSuccess(boolean isBstResult, boolean isSortedResult) {
        if (isBstResult == isSortedResult)
            System.out.println("SUCCESS");
        else {
            System.out.println("ERROR!");
            System.out.println("isBstResult: " + isBstResult);
            System.out.println("isSortedResult: " + isSortedResult);
        }

    }
}
