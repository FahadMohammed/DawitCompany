import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;


public class Company {
    
   
    public static class Node{
        int salary;
        String name;
        Node left, right;
        
        Node(String name, int salary){
            this.salary = salary;
            this.name = name;
            left = null;
            right= null;
        }       
    }
    
    public static class BinarySearchTree{
        
        private Node root;
        
        public BinarySearchTree(){
            root = null;
        }
                
        public void insert(String amount, int staff){
            root = insert(amount, staff, root);
        }
        
        public void remove(int x){
            root = remove(x, root);
        }
        
        public boolean search(int x){
            return searchT (x, root);
        }
        
        public boolean isEmpty(){
            return root == null;
        }
        
        public void printInOrder(){
            if(isEmpty())
                System.out.println("Empty tree");
            else
                printOrder(root);
        }
        
        private Node insert(String staff, int amount, Node t){
            if (t == null)
                return new Node(staff,amount);
            
            if (amount > 3000){
                if (amount < t.salary)
                    t.left = insert(staff, amount, t.left);
                else if (amount > t.salary)
                    t.right = insert(staff, amount, t.right);
            }else{
                System.out.println("Sorry Salary Too low! Cannot add!");
            }
            
            return t;
        }
        
        private Node remove(int x, Node t){
            if (t ==null){
                return t;
            }
            if(x < t.salary)
                t.left = remove(x, t.left);
            else if(x > t.salary)
                t.right = remove(x, t.right);
            else if (t.left != null && t.right !=null){
                t.salary = findMin(t.right).salary;
                t.right = remove(t.salary, t.right);
            } else
                t = (t.left != null) ? t.left : t.right;
            return t;
        }
    
        private boolean searchT(int x, Node t){
            if(t == null)
                return false;
            if(x == t.salary){
                printNode(t.name, t.salary);
                return true;
            }
            if(x < t.salary)
                return searchT(x, t.left);
            else if (x > t.salary)
                return searchT(x, t.right);
            return true;
        }
        
        private void printNode(String staff, int amount){
            System.out.println("Amount matched");
            System.out.println("Employee: " + staff + " with salary: " + amount);
        }

        private Node findMin(Node t){
            if(t == null)
                return null;
            else if (t.left == null)
                return t;
            return findMin(t.left);
        }

        private void printOrder(Node t){
            if(t != null){
                printOrder(t.left);
                System.out.println(t.name + " " +t.salary);
                printOrder(t.right);
            }
        }
        
    }
    

            
    public static void main(String[] args) throws FileNotFoundException, IOException {

        BinarySearchTree employee = new BinarySearchTree();
        BufferedReader buf = new BufferedReader(new FileReader("D:\\Courses\\HCC_COSC2436\\Project 2\\employee.txt"));

        String lineRead;
        String[] wordsArray;

        while(true){

            lineRead = buf.readLine();
            if(lineRead == null){  
                break; 
            }else{
                wordsArray = lineRead.split("\t");
                String nameRead = wordsArray[0];
                int salaryRead = Integer.parseInt(wordsArray[1]);

                employee.insert(nameRead,salaryRead);
            }
        }
        
        // Search for an employee with the salary
        if(!employee.search(91000))
            System.out.println("Employee not found for this salary");
        // Remove an employee by entering specific salary for the eployee
        //employee.remove(150000);
        // Add an employee to the database by inserting their name and salary
        employee.insert("Fahad", 2500);
        //print all of the existing employees and their salaries in the database (tree)
        employee.printInOrder();
        buf.close();
    }  
    
}
