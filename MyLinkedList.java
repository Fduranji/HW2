import java.util.Scanner;
import java.io.*;

class Node
{
   public String data;
   public Node next;
   
   public Node(String data)
   {
      this.data = data;
   }
   
   public Node() { }
   
   public void displayNode()
   {
      System.out.println(data);
   }
}//Node Class

class LinkList
{
   private Node first;
   
   public LinkList()
   {
      first = null;
   }
   
   public boolean isEmpty()
   {
      return (first == null);
   }
   
   public void insert(String input)
   {//Adds a new node at the beginning
   
      Node insNode = new Node(input);
      insNode.next = first;
      first = insNode;
   }
   
   public void combine(Node result)
   {//Recieves node from mergeSorr(), first will then point to it
   
      first = result;
   }
   
   public void display()
   {//Displays the linked list
   
      System.out.println("Lists data from First --> Last: ");
      Node current = first;
      while(current != null)
      {
         current.displayNode();
         current = current.next;
      }
      
      System.out.println();
   }
   
   public Node getFirst()
   {//returns the first Node
   
      return first;
   }
   
    public Node mergeSort(Node head) 
    {//mergeSort method, is recursive
    
      if (head == null || head.next == null)
      {
        return head; 
      }  
      
      //Middle of the linked list found here
      Node a = head;
      Node b = head.next; 
      
      while ((b != null) && (b.next != null)) 
      { 
         head = head.next; 
         b = b.next.next; 
      } 
      
      b = head.next; 
      head.next = null;
       
      //recursion done here to find single node and go from there
      return merge(mergeSort(a), mergeSort(b));      
    }
   
   public Node merge(Node a, Node b)
   {//merge() merges the sorted a and b halves of the linked list
   
      Node fakeHead = new Node();
      Node current = fakeHead;
      
      while((a != null) && (b != null))
      {
         if(a.data.compareTo(b.data) < 0)
         {
            current.next = a;
            current = a;
            a = a.next;
         }
         else
         {
            current.next = b;
            current = b;
            b = b.next;
         }
      }
      
      //This says, if a equals null return b, if false return a.
      //c.next will then equal what returned, b or a. 
      current.next = (a == null) ? b : a;
      return fakeHead.next;
   }
}//LinkList Class

/**
   Problem 2.2.17 Linked List Sort from Algorithms book by Sedgewick and Wayne
   
   MyLinkedList Class
   Goes through a linked list, whose filled with data from a txt file, then sorted by city name.
    
   This version will read from a file called AreaCodes.txt which is included
   in the same folder. The first line of the file is 500,Portland-Auburn,ME. 
   Code can be manipulatedd to read from a different file. 
   Those lines of code have been left in, commented. 
   
   HOWEVER, in order to work from a different file, this file must be in the form of:
      AREACODE#,CITY,STATE 
   of course city and state are Strings and areacode# is an int. 
   
   Output will be in the form of:
      CITY,AREACODE#,STATE 
   sorted in alphabetical order.
*/
public class MyLinkedList
{
   public static void main(String[] args) throws IOException
   {
      Scanner keyboard = new Scanner(System.in);
      //System.out.print("Enter filename: ");
      String filename = "AreaCodes.txt"; //keyboard.nextLine();
      
      File file = new File(filename);
      Scanner readFile = new Scanner(file).useDelimiter("[,\n]");
   
      LinkList theList = new LinkList();
      
      String number = "";
      String city = "";
      String state = "";
      
      while(readFile.hasNext())
      {
         number = readFile.next();
         city = readFile.next();
         state = readFile.next();
         theList.insert(city + "," + number + "," + state); 
      }
        theList.display();
        System.out.println("The list after merge sort O(nlog(n))");
        theList.combine(theList.mergeSort(theList.getFirst()));
        theList.display();
    }//Main     
}//MyLinkedList Class