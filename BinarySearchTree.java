import java.util.ArrayList;

public class BinarySearchTree {
    static  class Node{
        int data;
        Node left;
        Node right;
        Node(int data){
            this.data = data;
        }
    }

//    Insert Node
    public static Node insert(Node root, int val){
        if (root == null){
            root = new Node(val);
             return root;
        }
//        Left Subtree
        if (root.data> val){
            root.left = insert(root.left, val);
        }
//        Right Subtree
        else {
            root.right = insert(root.left, val);
        }
        return root;
    }

    public static void inoder(Node root){
        if (root == null){
            return;
        }
        inoder(root.left);
        System.out.print(root.data + " ");
        inoder(root.right);
    }

//         Search
    public static boolean search(Node root , int key){
        if (root == null){
            return false;
        }
        if (root.data > key){
            return search(root.left, key);
        }
        else if (root.data == key){
            return true;
        }
        else {
            return search(root.right, key);
        }
    }

//    Delete a Node
    public static Node delete(Node root, int val){
        if (root.data > val){
            root.left = delete(root.left, val);
        }
        else if (root.data <val){
            root.right = delete(root.right,val);
        }
        else {
            if (root.left == null && root.right == null){
                return null;
            }
            if (root.left == null){
                return root.right;
            }
            else if (root.right == null ){
                return root.left;
            }
            Node is = inoderSuccessor(root.right);
            root.data = is.data;
            root.right = delete(root.right, is.data);
        }
        return root;
    }

//    Inoder Successor : Left Most in Right Subtree
    public static Node inoderSuccessor(Node root){
        while (root.left != null){
            root = root.left;
        }
        return root;
    }

//    Print in Range :
    public static void printinRange(Node root, int x, int y){
        if (root == null){
            return;
        }
        else if (root.data >= y){
            printinRange(root,x,y);
        }
        else {
            printinRange(root.right,x,y);
        }
    }

//    Print All Path from Root to Leaf :
    public static void printPath(ArrayList<Integer> path){
        for (Integer integer : path) {
            System.out.print(integer + "-->");
        }
        System.out.println();
    }

    public static void printRoot2Leaf(Node root, ArrayList<Integer> path){
        if (root == null){
            return;
        }

        path.add(root.data);

        if (root.left == null && root.right == null){
            printPath(path);
        }
        else {
            printRoot2Leaf(root.left , path);
            printRoot2Leaf(root.right, path);
        }
        path.remove(path.size()-1);
    }

    public static void main(String[] args) {
        int[] value = {5,1,3,4,2,7};
        Node root = null;
        for (int j : value) {
            root = insert(root, j);
            inoder(root);

            if (search(root, 7)) {
                System.out.println("Found");
            } else
                System.out.println("Not Found");
        }
        System.out.println("Delete Node 7 :");
        System.out.println("Before");
        delete(root,7);
        System.out.println("After");
        inoder(root);
        System.out.print("Range : ");
        printinRange(root, 3, 12);
        System.out.print("Root to Leaf : ");
        printRoot2Leaf(root, new ArrayList<>());

    }
}
