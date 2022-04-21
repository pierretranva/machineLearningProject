import java.io.*;
import java.util.*;
//creates a neural network with one hidden layer containing 4 neurons
public class NN{
   
    private Matrix IH;
    private Matrix HO;
    private Matrix IHBias;
    private Matrix HOBias;
    
    public NN(File file){
        try{
        Scanner sc = new Scanner(file);
        
        }
        catch(FileNotFoundException e){
            System.out.println(e);
        }
    }
    public void train(){

    }
}