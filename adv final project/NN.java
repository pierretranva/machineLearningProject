import java.io.*;
import java.util.*;
//creates a neural network with one hidden layer containing 4 neurons
public class NN{
   
    private Matrix IH; //4x4 
    private Matrix HO; //3x4
    private Matrix IHBias; //4x1
    private Matrix HOBias; //3x1
    //constructor takes in txt file to populate weights
    public NN(int numInputs, int numOutputs, String fileName){
        IH = new Matrix(numInputs,1);
        HO = new Matrix(numInputs, 1);
        IHBias = new Matrix(numInputs, 1);
        HOBias = new Matrix(numInputs, 1);
        populateWeights();
    }
    public void populateWeights(){
        try{
            Scanner input = new Scanner("weights.txt");
            for (int i = 0; i < 4; i++){
                String line = input.nextLine();    
                String[] split = line.split(",");
                if (i == 0){
                    for (int j = 0; j < 4; j++){
                        for (int k = 0; k < 4; k++){
                            IH.data[j][k] = Double.parseDouble(split[j*4+k]);
                        }
                    }
                }
                else if (i == 1){
                    for (int j = 0; j < 3; j++){
                        for (int k = 0; k < 4; k++){
                            HO.data[j][k] = Double.parseDouble(split[j*4+k]);
                        }
                    }
                }
                else if (i == 2){
                    for (int j = 0; j < 4; j++){
                            IHBias.data[j][0] = Double.parseDouble(split[j]);
                    }
                }
                else if (i == 3){
                    for (int j = 0; j < 3; j++){
                            HOBias.data[j][0] = Double.parseDouble(split[j]);
                    }
                }
            }
            
        }
          catch(Exception e){
            System.out.println("Error: " + e);
        }
    }
     //populate weight matrix by reading in from weights txt file
     public void popWeights(){

     }
    //trains using all of the data 
    public void train(int batch,File file){
        try{
            Scanner scan = new Scanner(file);
            for(int i =0; i<batch; i++){
                
                String line = scan.next();
                String[] feature= line.split(",");
                
                Matrix input  = new Matrix(4,1);
                for(int j=0; j<feature.length; j++){
                    input.data[j][0] = Double.parseDouble(feature[j]);
                }
                Matrix output = forwardProp(input);
                int flowerType =0;
                switch (feature[4]){
                case "Iris-setosa": flowerType =1;
                break;
                case "Iris-versicolor": flowerType =2;
                break;
                case "Iris-virginica": flowerType =3;
                break;
                backProp(output,flowerType);
               
                }
        }
    }
        
        catch(FileNotFoundException e){
            System.out.println(e);
        }

    }

    //multiplies input with weight layers and gets output
    public Matrix forwardProp(Matrix input){
       Matrix output = new Matrix(3,1);
       Matrix inputNormalize = input.sigmoid();
        Matrix Hidden =IH.multiply(inputNormalize);
        Hidden = Hidden.add(IHBias);
        Hidden = Hidden.sigmoid();
        output = HO.multiply(Hidden);
        output = output.add(HOBias);    
        output = output.sigmoid();
        return output;
        
        
    }
    //updates compares the forward propvalues results and changes the values of weights/bias to make NN more acurate
    public void backProp(Matrix output, int flowerType){

                
        
    }
    
}