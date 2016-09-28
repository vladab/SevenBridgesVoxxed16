
import java.io.*;
import java.util.Arrays;

public class SevenBridges {

    public static int pIndex=0;
    public static String finalString;
    public Node makeBTree(int [] inOrder, int [] preOrder, int iStart, int iEnd ){
        if(iStart>iEnd){
            return null;
        }
        Node root = new Node(preOrder[pIndex]);pIndex++;

        if(iStart==iEnd){
            return root;
        }
        int index = getInorderIndex(inOrder, iStart, iEnd, root.data);
        root.left = makeBTree(inOrder,preOrder,iStart, index-1);
        root.right = makeBTree(inOrder,preOrder,index+1, iEnd);
        //}
        return root;
    }
    public int getInorderIndex(int [] inOrder, int start, int end, int data){
        for(int i=start;i<=end;i++){
            if(inOrder[i]==data){
                return i;
            }
        }
        return -1;
    }
    public void printINORDER(Node root){
        if(root!=null){
            printINORDER(root.left);
            if(root.left == null && root.right == null){
                //System.out.print(root.data);
                finalString += root.data;
            }
            printINORDER(root.right);
        }
    }
    public String MD5(String md5) {
        try {
            java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
            byte[] array = md.digest(md5.getBytes());
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < array.length; ++i) {
                sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1,3));
            }
            return sb.toString();
        } catch (java.security.NoSuchAlgorithmException e) {
        }
        return null;
    }
    public String readFile(String fileName) {
        BufferedReader bufferedReader = null;

        try {

            //Construct the BufferedReader object
            bufferedReader = new BufferedReader(new FileReader(fileName));

            String line = null;

            while ((line = bufferedReader.readLine()) != null) {
                //Process the data, here we just print it out
                return line;
            }

        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            //Close the BufferedReader
            try {
                if (bufferedReader != null)
                    bufferedReader.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return "ERROR";
    }
    public int[] generateElements(String foo) {
        String[] split = foo.split(" ");
        int[] array = Arrays.stream(split).mapToInt(Integer::parseInt).toArray();
        return array;
    }
    public static void main (String[] args) throws java.lang.Exception
    {
        SevenBridges  i = new SevenBridges();
        finalString = "";
        int [] inOrder = i.generateElements(i.readFile("/Users/vladab/IdeaProjects/sb/src/inorder.txt"));
        int [] preOrder = i.generateElements(i.readFile("/Users/vladab/IdeaProjects/sb/src/preorder.txt"));

//        int [] inOrder = {11,0,1,13,7,12,10,2,9,3,8,1,4};
//        int [] preOrder = {2,1,0,11,7,13,10,12,3,9,1,8,4};

        Node x = i.makeBTree(inOrder, preOrder, 0, inOrder.length-1);
        System.out.println("Constructed Tree : ");
        i.printINORDER(x);
        System.out.println();
        //System.out.println("MD5 Tree string: " + finalString);
        System.out.println(i.MD5(finalString));
    }
}
class Node{
    int data;
    Node left;
    Node right;
    public Node (int data){
        this.data = data;
        left = null;
        right = null;
    }
}