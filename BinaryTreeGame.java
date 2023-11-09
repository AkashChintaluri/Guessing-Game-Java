import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.Scanner; // Import the Scanner class to read text files
import javafx.scene.Node;

public class BinaryTreeGame
{
    static class Node
    {
        Node left;
        Node right;
        int value;
        String data;
        public Node(int value,String d)
        {
            this.value = value;
            this.data=d;
        }
    }

    static Node rootnode;
    public static void main(String[] args)
    {
        while(true)
        {
            Scanner in=new Scanner(System.in);
            BinaryTreeGame bg=new BinaryTreeGame();
            System.out.println("P - Play the game");
            System.out.println("L - Load another game");
            System.out.println("D - Display the binary tree");
            System.out.println("H - Help Information");
            System.out.println("X - Exit the program");
            System.out.println("Enter Choice");
            char ch;
            ch=in.next().charAt(0);

            switch(ch)
            {
                case 'P':
                case 'p':
                    bg.createTree("");
                    bg.play(rootnode);
                    break;
                case 'L':
                case 'l':
                    bg.createTree("Game 2.txt");
                    bg.play(rootnode);
                    break;
                case 'D':
                case 'd':
                    System.out.println("1 - Traverse Game 1");
                    System.out.println("2 - Traverse Game 2");
                    int c=in.nextInt();
                    if(c == 1)
                        bg.createTree("");
                    else
                        bg.createTree("Game 2.txt");
                    while(true)
                    {
                        System.out.println("1 - Inorder");
                        System.out.println("2 - Preorder");
                        System.out.println("3 - Postorder");
                        System.out.println("4 - Return to Main Menu");

                        c=in.nextInt();
                        if(c == 4)
                            break;
                        switch(c)
                        {
                            case 1:
                                bg.inOrder(rootnode);
                                break;
                            case 2:
                                bg.preOrder(rootnode);
                                break;
                            case 3:
                                bg.postOrder(rootnode);
                                break;
                            default:System.out.println("Invalid choice");
                                break;
                        }
                    }

                    break;
                case 'H':
                case 'h':
                    break;
                case 'X':
                case 'x':
                    System.exit(0);
                default:System.out.println("Invalid choice");
            }

        }
    }
    public void createTree(String fname)
    {
        try
        {
            //Open the file that contains the game
            File myObj;
            if(fname == ""){
                myObj = new File("Game 1.txt");
            }
            else
                myObj=new File(fname);
            Scanner myReader = new Scanner(myObj);
            //Read the game name from the file
            String data = myReader.nextLine();
            //Display the game
            System.out.println(data);
            //Read the Game Description from the file
            data = myReader.nextLine();
            //Display the Game Description
            System.out.println(data);
            //Read the Root Node
            data = myReader.nextLine();
            //add the root node
            String nValue=data.substring(0,3);
            int v=Integer.parseInt(nValue);
            //  System.out.println(nValue);
            String d=data.substring(4);
            // System.out.println(d);
            rootnode = new Node(v,d);
            while (myReader.hasNextLine())
            {
                data = myReader.nextLine();
                nValue=data.substring(0,3);
                v=Integer.parseInt(nValue);
                //      System.out.println(nValue);
                d=data.substring(4);
                //    System.out.println(d);
                insert(rootnode, v,d);

            }
            myReader.close();

            //binary tree created

        }
        catch (FileNotFoundException e)
        {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }


    }

    public void insert(Node node, int value,String d)
    {
        if (value < node.value)
        {
            if (node.left != null)
            {
                insert(node.left, value,d);
            } else
            {
                //  System.out.println("  Inserted " + value + " " + d + " to left of Node " + node.value + " " + node.data);
                node.left = new Node(value,d);
            }
        }
        else if (value > node.value)
        {
            if (node.right != null)
            {
                insert(node.right, value,d);
            } else
            {
                //  System.out.println("  Inserted " + value + " " + d + " to right of Node " + node.value + " " + node.data);
                node.right = new Node(value,d);
            }
        }
    }

    public void play(Node node) {
        Node curNode = rootnode; // Initialize curNode with the root node

        while (curNode != null) { // Check if curNode is not null
            Scanner in = new Scanner(System.in);
            System.out.println(curNode.data);
            if (curNode.left == null && curNode.right == null)
                break;

            char ans;
            ans = in.next().charAt(0);
            if (ans == 'Y' || ans == 'y')
                curNode = curNode.left;
            else if (ans == 'N' || ans == 'n')
                curNode = curNode.right;
            else
                System.out.println("Invalid Node");
        }
    }


    public void inOrder(Node node)
    {
        //code to traverse the tree - inorder traversal
        if(node != null)
        {
            inOrder(node.left);
            System.out.println(node.data);
            inOrder(node.right);
        }

    }

    public void preOrder(Node node)
    {
        //code to traverse the tree - preorder traversal
        if(node != null)
        {
            System.out.println(node.data);
            preOrder(node.left);
            preOrder(node.right);
        }
    }

    public void postOrder(Node node)
    {
        //code to traverse the tree - postorder traversal
        if(node != null)
        {
            postOrder(node.left);
            postOrder(node.right);
            System.out.println(node.data);
        }

    }
}