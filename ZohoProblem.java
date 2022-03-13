import java.util.*;
public class Main{
    public static String deletedUserName = "";
    static class Node{
        String roleName;
        Vector<Node> child = new Vector<>();
        Vector<String> userName = new Vector<>();
        int height;
    };
    static Node newNode(String roleName){
        //System.out.println("New node");
        Node temp = new Node();
        temp.roleName = roleName;
        return temp;
    }
    static Node newRoot(String roleName){
        //System.out.println("New node");
        Node temp = new Node();
        temp.roleName = roleName;
        temp.height = 1;
        return temp;
    }
    static Node addNode(Node root){
        Scanner in = new Scanner(System.in);
        System.out.print("Enter sub role name: ");
        Node subRole = newNode(in.nextLine());
        System.out.print("Enter reporting to role name: ");
        String reportingTo = in.nextLine();
        //System.out.println("Into addNode funtion");
        Queue<Node> q = new LinkedList<>();
        q.add(root);
        boolean flag = false;
        while(!q.isEmpty()){
            //System.out.println("Into the while loop");
            int n = q.size();
            //System.out.println("Size = " + n);
            while(n>0){
                //System.out.println("Into the inner while loop");
                Node p=q.peek();
                //System.out.println("Node = "+ p);
                Node removed = q.remove();
                //System.out.println("Removed = "+removed);
                if(p.roleName.equals(reportingTo)){
                    //p.parent.add(reportingTo);
                    p.child.add(subRole);
                    subRole.height=p.height+1;
                    flag=true;
                    //System.out.println("Child role added");
                }
                else{
                    //System.out.println("Else");
                    for(int i=0; i<p.child.size(); i++){
                        q.add(p.child.get(i));
                    }
                
                }
                n--;
            }
        }
        if(flag==false)
        {
            System.out.println("Reporting role not found");
        }
        return root;
    }
    static void displayRole(Node root){
        if (root == null)
            return;
        Queue<Node > q = new LinkedList<>();
        q.add(root);
        while (!q.isEmpty())
        {
            int n = q.size();
            while (n > 0)
            {
                Node p = q.peek();
                q.remove();
                System.out.print(p.roleName + " ");
                //System.out.print(p.child.size() + " ");
                for (int i = 0; i < p.child.size(); i++)
                    q.add(p.child.get(i));
                n--;
            }
            System.out.println();
        }
    }
    static Node deleteRole(Node root){
        Scanner in = new Scanner(System.in);
        System.out.print("Enter the role to be deleted: ");
        String delete = in.nextLine();
        System.out.print("Enter the role to be transferred: ");
        String roleToTransfer = in.nextLine();
        if(root == null)
            return root;
        Queue<Node> q = new LinkedList<>();
        q.add(root);
        while(!q.isEmpty()){
            int n = q.size();
            while(n>0){
                Node p = q.peek();
                q.remove();
                if(p.roleName.equals(delete)){
                    boolean flag=false;
                    for(int i=0;i<p.child.size();i++)
                    {
                        if(p.child.get(i).roleName.equals(roleToTransfer))
                        {
                            p.child.addAll(p.child.get(i).child);
                            p.child.remove(i);
                            p.roleName = roleToTransfer;
                            p.userName.set(i,deletedUserName);
                            flag=true;
                        }
                    }
                    if(flag==false){
                        p.roleName = roleToTransfer;
                    }
                }
                else{
                    for(int i=0; i<p.child.size(); i++){
                        q.add(p.child.get(i));
                    }
                }
                n--;
            }
        }
        return root;
    }
    static Node addUser(Node root){
        Scanner in = new Scanner(System.in);
        System.out.print("Enter user name: ");
        String newUserName = in.nextLine();
        System.out.print("Enter Role: ");
        String addToRoleName = in.nextLine();
        Queue<Node> q = new LinkedList<>();
        q.add(root);
        boolean flag = false;
        while(!q.isEmpty()){
            //System.out.println("Into the while loop");
            int n = q.size();
            //System.out.println("Size = " + n);
            while(n>0){
                //System.out.println("Into the inner while loop");
                Node p=q.peek();
                //System.out.println("Node = "+ p);
                Node removed = q.remove();
                //System.out.println("Removed = "+removed);
                if(p.roleName.equals(addToRoleName)){
                    p.userName.add(newUserName);
                    flag=true;
                    //System.out.println("Child added");
                }
                else{
                    //System.out.println("Else");
                    for(int i=0; i<p.child.size(); i++){
                        q.add(p.child.get(i));
                    }
                
                }
                n--;
            }
        }
        if(flag==false){
            System.out.println("Role name not found");
        }
        return root;
    }
    static void displayUser(Node root){
        if (root == null)
        return;
        Queue<Node > q = new LinkedList<>();
        q.add(root);
        while (!q.isEmpty())
        {
            int n = q.size();
            while (n > 0)
            {
                Node p = q.peek();
                q.remove();
                for(int i=0;i<p.userName.size(); i++){
                    if(p.userName.get(i)!=null){
                        System.out.print(p.userName.get(i) + " - "+ p.roleName + "\n");    
                    }
                }
                //System.out.print(p.child.size() + " ");
                for (int i = 0; i < p.child.size(); i++)
                    q.add(p.child.get(i));
                n--;
            }
        }
    }
    static void displaySubUser(Node root){
        if (root == null)
        return;
        Queue<Node> q = new LinkedList<>();
        q.add(root);
        while (!q.isEmpty())
        {
            int n = q.size();
            while (n > 0)
            {
                Node p = q.peek();
                q.remove();
                
            
                    for(int j=0; j < p.child.size(); j++){
                        for(int k=0; k < p.child.get(j).userName.size(); k++){
                            System.out.print(p.child.get(j).userName.get(k)+", ");
                        }
                    }
                   
                
                for(int l=0; l < p.child.size(); l++){
                    q.add(p.child.get(l));
                }
                n--;
                //System.out.print(p.child.size() + " ");
                
            }
        }
    }
    
    public static void temp(Node root)
    {
        if (root == null)
        return;
        Queue<Node> q = new LinkedList<>();
        q.add(root);
        while (!q.isEmpty())
        {
            int n = q.size();
            while (n > 0)
            {
                Node p = q.peek();
                
                q.remove();
                
                for(int i=0; i<p.userName.size(); i++){
                    System.out.print(p.userName.get(i) + " - ");
                    displaySubUser(p);
                }
                for(int l=0; l < p.child.size(); l++){
                    q.add(p.child.get(l));
                }
                
                n--;
                //System.out.print(p.child.size() + " ");
                
                System.out.println();
            }
        }
    }
    static Node deleteUser(Node root){
        Scanner in = new Scanner(System.in);
        System.out.println("Enter username to be deleted : ");
        String deleteUserName = in.nextLine();
        if(root == null)
            return root;
        Queue<Node> q = new LinkedList<>();
        q.add(root);
        while(!q.isEmpty()){
            int n = q.size();
            while(n>0){
                Node p = q.peek();
                q.remove();
                for(int i=0; i<p.userName.size(); i++){
                    if(p.userName.get(i).equals(deleteUserName)){
                        deletedUserName = p.userName.get(i);
                        p.userName.remove(i);
                        return root;
                    }
                }
                for(int i=0; i<p.child.size(); i++){
                    q.add(p.child.get(i));
                }
                n--;
            }
        }
        return root;
    }
    static int noOfUsersFromTop(Node root){
        Scanner in = new Scanner(System.in);
        System.out.print("Enter user name: ");
        String inputUserName = in.nextLine();
        int count=-1;
        
        if(root == null)
            return 0;
        Queue<Node> q = new LinkedList<>();
        q.add(root);
        while(!q.isEmpty()){
            int n = q.size();
            while(n>0){
                Node p = q.peek();
                q.remove();
                for(int i=0; i<p.userName.size(); i++){
                    if(p.userName.get(i).equals(inputUserName)){
                        count = p.height-1;
                        return count;
                    }
                }
                for(int j=0; j<p.child.size(); j++){
                    q.add(p.child.get(j));
                }
                n--;
            }     
        }
        return count;
    }
    static int heightOfRoleHeirarchy(Node root){
        int height = 0;
        if(root == null)
            return 0;
        Queue<Node> q = new LinkedList<>();
        q.add(root);
        while(!q.isEmpty()){
            int n = q.size();
            while(n>0){
                Node p = q.peek();
                q.remove();
                for(int i=0; i<p.child.size(); i++){
                    if(height < p.height){
                        height = p.height;
                    }
                }
                for(int j=0; j<p.child.size(); j++){
                    q.add(p.child.get(j));
                }
                n--;
            }     
        }
        return height+1;
    }
    static int commonBoss(Node root){
        Scanner in = new Scanner(System.in);
        System.out.print("Enter user 1 : ");
        String user1 = in.nextLine();
        System.out.print("Enter user 2 : ");
        String user2 = in.nextLine();
        int count = 0;
        if(root == null)
            return count;
        Queue<Node> q = new LinkedList<>();
        q.add(root);
        while(!q.isEmpty()){
            int n = q.size();
            while(n>0){
                Node p = q.peek();
                q.remove();
                if(p.userName.equals(user1)){
                    count++;
                }
                else if(p.userName.equals(user2)){
                    count++;
                }
                n--;
            }
        }
        return count;
    }
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        int input=1;
        System.out.print("Enter the root role name: ");
        String roleName = in.nextLine();
        Node root = newRoot(roleName);
        do{
            System.out.println("Operations:\n\t1. Add Sub Role.\n\t2. Display Roles.\n\t3. Delete Role.\n\t4. Add User.\n\t5. Display Users.\n\t6. Display Users and display Sub Users.\n\t7. Delete User.\n\t8. Number of users from top.\n\t9. Height of role hierarchy.\n\t10. Common boss of users");
            System.out.print("Operation to be performed: ");
            int option = in.nextInt();
            in.nextLine();
            switch(option){
            case 1:
                root = addNode(root);
                break; 
            case 2:
                displayRole(root);
                break;
            case 3:
                root = deleteRole(root);
                break;
            case 4:
                root = addUser(root);
                break;
            case 5:
                displayUser(root);
                break;
            case 6:
                temp(root);
                break;
            case 7:
                root = deleteUser(root);
                break;
            case 8:
                int count = noOfUsersFromTop(root);
                if(count == -1)
                    System.out.println("No heirarchy");
                else{
                    System.out.println("Number of users from top: "+ count);
                }
                break;
            case 9:
                int height = heightOfRoleHeirarchy(root);
                if(height == 0){
                    System.out.println("No heirarchy");
                }
                else{
                    System.out.println("height - "+ height);
                }
            case 10:
                int number = commonBoss(root);
                if(number == 2){
                    System.out.println(root.userName);
                }
                else{
                    System.out.println("No common boss found");
                }
            }
            System.out.print("Do you want to continue?(1 for yes/ 0 for no) : ");
            input = in.nextInt();
        }while(input!=0);
    }
}