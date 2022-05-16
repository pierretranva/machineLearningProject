import java.io.*;
import java.util.*;
//creates a neural network with one hidden layer containing 4 neurons
public class NN{
   
    private Matrix IH; //4x4 
    private Matrix HO; //3x4
    private Matrix IHBias; //4x1
    private Matrix HOBias; //3x1
    private double learningRate =0.0000001;
    private Matrix Hidden; //4x1
    private Matrix inputdata; //4x1
  
    //constructor takes in txt file to populate weights
    public NN(int numInputs, int numOutputs, String fileName){
  
            Double[][] IHdata = new Double[4][4];
            for(int i =0; i<4; i++){
                for(int j =0; j<4; j++){
                    IHdata[i][j]=Math.random();
                }
            }
            IH = new Matrix(4,4,IHdata);

           
            Double[][] HOdata = new Double[3][4];
            for(int i =0; i<3; i++){
                for(int j =0; j<4; j++){
                    HOdata[i][j]=Math.random();
                }
            }
            HO = new Matrix(3,4,HOdata);

          
            Double[][] IHBiasdata = new Double[4][1];
            for(int i =0; i<4; i++){
                for(int j =0; j<1; j++){
                    IHBiasdata[i][j]=Math.random();
                }
            }
            IHBias = new Matrix(4,1,IHBiasdata);

         
            Double[][] HOBiasdata = new Double[3][1];
            for(int i =0; i<3; i++){
                for(int j =0; j<1; j++){
                    HOBiasdata[i][j]=Math.random();
                }
            }
            HOBias = new Matrix(3,1,IHdata);

        

       Hidden = new Matrix(4,1);
       inputdata = new Matrix(4,1);
       System.out.println(IHBias);
        
    }
    //reads the weights.txt file and populates the weights/bias matracies
    public void populateWeights(){
        try{
            Scanner input = new Scanner("weights.txt");
            for (int i = 0; i < 4; i++){
                String line = input.next();    
                System.out.println(line);
                String[] split = line.split(",");

                if (i == 0){
                    for (int j = 0; j < 4; j++){
                        for (int k = 0; k < 4; k++){
                            IH.setVal(j,k, Double.parseDouble(split[j*4+k]));
                            System.out.println(Double.parseDouble(split[j*4+4]));
                            System.out.println("YOUOIUHSDH ");
                        }
                    }
                }
                else if (i == 1){
                    for (int j = 0; j < 3; j++){
                        for (int k = 0; k < 4; k++){
                           HO.setVal(j,k, Double.parseDouble(split[j*4+k]));
                        }
                    }
                }
                else if (i == 2){
                    for (int j = 0; j < 4; j++){
                        IHBias.setVal(j,0,Double.parseDouble(split[j]));

                    }
                }
                else if (i == 3){
                    for (int j = 0; j < 3; j++){
                        HOBias.setVal(j,0,Double.parseDouble(split[j]));
                          
                    }
                }
            }
            System.out.println("it is now populated");
        }
          catch(Exception e){
            System.out.println("Error: " + e);
        }
    }
    //feeds the data through the training function one at at time
    public void fit(int batch, File file){
        try{
            Scanner scan = new Scanner(file);
            for(int i =0; i<batch; i++){
                
                String line = scan.next();
                String[] feature= line.split(",");
                
                inputdata  = new Matrix(4,1);
                for(int j=0; j<feature.length-1; j++){
                    inputdata.data[j][0] = Double.parseDouble(feature[j]);
                }
            
                int flowerType =0;
                switch (feature[4]){
                case "Iris-setosa": flowerType =1;
                break;
                case "Iris-versicolor": flowerType =2;
                break;
                case "Iris-virginica": flowerType =3;
                break;
                }
                Matrix target = new Matrix(3,1);
                //populates target matrix with correct flower type
                for(int j =0; j<3; j++){
                    if(j==flowerType){
                        target.data[j][0] = 1.0;
                    }
                    else{
                        target.data[j][0] = 0.0;
                    }
                }
                train(target);
                saveWeights();
        }
        
    }
        
        catch(FileNotFoundException e){
            System.out.println(e);
        }

    }
    
    
    //trains the network given one data point and the expected output
    public void train(Matrix target){
        Matrix output = forwardProp();
        Matrix error = target.subtract(output);
        backProp(output, error);
        
    }

    //multiplies input with weight layers and gets output
    public Matrix forwardProp(){
        Matrix output = new Matrix(3,1);
        inputdata = inputdata.sigmoid();
        Hidden =IH.multiply(inputdata);
        Hidden = Hidden.add(IHBias);
        Hidden = Hidden.sigmoid();
        output = HO.multiply(Hidden);
        output = output.add(HOBias);    
        output = output.sigmoid();
        return output;
        
        
    }
    //runs through the forward propogration algoritm to predict the type of flower
    public Matrix getOutput(Matrix inputlayer){
        Matrix output = new Matrix(3,1);
        inputdata = inputdata.sigmoid();
        Hidden =IH.multiply(inputdata);
        Hidden = Hidden.add(IHBias);
        Hidden = Hidden.sigmoid();
        output = HO.multiply(Hidden);
        output = output.add(HOBias);    
        output = output.sigmoid();
        return output;

    }
    //updates compares the forward propvalues results and changes the values of weights/bias to make NN more acurate
    public void backProp(Matrix output, Matrix error){
        Matrix gradient = new Matrix(3,1);
        gradient =output.dsigmoid();
        gradient = gradient.multiply(error);
        gradient = gradient.multiply(learningRate);

        Matrix HiddenTranspose = Hidden.transpose();
        Matrix who_delta = gradient.multiply(HiddenTranspose);

        HO = HO.add(who_delta);
        HOBias = HOBias.add(gradient);

        Matrix whoTranspose = HO.transpose();
        Matrix hidden_error = whoTranspose.multiply(error);

        Matrix hiddenGradient = Hidden.dsigmoid();
        hiddenGradient = hiddenGradient.multiply(hidden_error);
        hiddenGradient = hiddenGradient.multiply(learningRate);

        Matrix inputTranspose = inputdata.transpose();
        Matrix ih_delta = hiddenGradient.multiply(inputTranspose);

        IH = IH.add(ih_delta);
        IHBias = IHBias.add(hiddenGradient);

      
    }

    public void saveWeights(){
        try{
            PrintWriter output = new PrintWriter("weights.txt");
            output.print(IH.toString());
            output.print(HO.toString());
            output.print(IHBias.toString());
            output.print(HOBias.toString());
        }
    catch(Exception e){
    System.out.println("Error: " + e);
    }
}
public double test(int batch, File file){
    double accuracy =0;
    double correct =0;
    try {
      Scanner input = new Scanner(file);
      for(int i =0; i<batch; i++){
          
        String line = input.next();
        //System.out.println("Testing: " + line);
        String[] feature = line.split(",");
        Matrix testdata = new Matrix(3,1);
        for(int j=0; j<testdata.getRow(); j++){
            testdata.data[j][0] = Double.parseDouble(feature[j]);
        }
        if(feature.length<4){
            System.out.println("less than 4");
        }
        else{
        int flowerType =0;
        switch (feature[4]){
         case "Iris-setosa": flowerType =1;
         break;
         case "Iris-versicolor": flowerType =2;
         break;
         case "Iris-virginica": flowerType =3;
         break;

        }

        if(predict(testdata)==flowerType){
          correct +=1.0;
        }
        else
        System.out.println("this one is wrong");
     }
    
}
input.close();
      }
      catch(FileNotFoundException e){
        System.out.println(e);
      }
      accuracy = correct/batch;

      return accuracy;
    }
    
//takes the output matrix and converts numbers to the type of flower
public int predict(Matrix input){
    Matrix prediction = getOutput(input);
    
        if(prediction.data[0][0]>prediction.data[1][0] && prediction.data[0][0]>prediction.data[2][0]){
            return 1;
        }
        else if(prediction.data[1][0]>prediction.data[0][0] && prediction.data[1][0]>prediction.data[2][0]){
        return 2;
        }
        else
        return 3;
  
}
}