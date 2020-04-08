package TreePackage;
import java.util.Scanner;

public class MainProgram {
	public static void main(String[] args)
	{
		Scanner in = new Scanner(System.in); //Scanner for input
	       Scanner kb = new Scanner(System.in);//Scanner for kb
	       BinarySearchTree<Integer> myTree = new BinarySearchTree<>(); //Object of the BST
	       
	       boolean select = true;//user clicking on the selection
	       while(select){ 
	           System.out.println("Please enter values:");
	           String input=in.nextLine();
	          
	           String[] array = input.split(" "); //splits the integers with a space. 

	           
	           int[] numbers = new int[array.length]; //converts the string into an integer. 
	           
	           for(int i = 0; i<array.length; i++)
	           {
	           
	        	   	  numbers[i]=Integer.parseInt(array[i]);
	                   if(myTree.contains(numbers[i]))
	                   {
	                       System.out.print(numbers[i]+" already exists! ");
	                       select = true;
	                       myTree.clear();
	                       System.exit(0); //exits the program
	                       break;
	                   }
	                   else
	                   {
	                       myTree.add(numbers[i]); //adds the integers into the tree
	                       select = false;
	                   }
	           }
	       } //end for loop
	       System.out.println("\nPreOrder: "); //displays preorder
	       myTree.preorderTraverse();
	       System.out.println("\nInOrder: "); //displays inorder
	       myTree.inorderTraverse();
	       System.out.println("\nPostOrder: "); //displays PostOrder
	       myTree.postorderTraverse();
	       
	       boolean running = true; //Selection
	       while(running) //while the program is running 
	       {
	           System.out.println("Main Menu");
	           System.out.println("A: Add a value");
	           System.out.println("B: Remove a value");
	           System.out.println("C: Exit a program");
	           System.out.print("What command would you like to run: ");
	           String choice = kb.next().toUpperCase();
	           char options = choice.charAt(0); 
	           switch(options)
	           {
	               case 'A': //if the user wants to add a value 
	                   System.out.print("Please enter the value you would like to add: ");
	                   int add = kb.nextInt(); //user puts a value 
	                   if(myTree.contains(add))
	                       System.out.println(add + " already exists! Duplicated values are not allowed.\n");
	                   else 
	                   {
	                       myTree.add(add);
	                       System.out.print("In-order: ");
	                       myTree.inorderTraverse();
	                       System.out.println("");
	                   }
	                   break;
	               case 'B':
	                   System.out.print("Please enter a value you would like to remove: ");
	                   int remove = kb.nextInt();
	                   if(myTree.contains(remove)) {
	                       myTree.remove(remove);
	                       System.out.println("In-order");
	                       myTree.inorderTraverse();
	                       System.out.println("");
	                       myTree.getInorderIterator();
	                   }
	                   else
	                       System.out.println(remove + " Doesn't exist!");
	                   break;
	               case 'C':
	                   System.out.println("Exit!");	                  
	                   running = false;
	                   break;
	           }
	       }
	   } //end main
	} // end Main.java
