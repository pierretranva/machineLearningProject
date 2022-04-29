import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
//K-Nearest Neighbor Machine learning algorithm 
public class KNN {
   private ArrayList<datapoint> dataset;
    int batchSize=100;
    int k=5;
    String traindata = "IRIS.csv";
    String testdata = "IRIS_TEST.csv";
    File trainFile = new File(traindata);
    File testFile = new File(testdata);
    
    public KNN(){
      dataset = new ArrayList<datapoint>();
    }
    public ArrayList<datapoint> getDataset(){
      return dataset;
    }
    public void train(int batch, File file){
      try {
          Scanner input = new Scanner(file);
        for(int i =0; i<batch; i++){
            
            String line = input.next();
            String[] feature= line.split(",");
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
            dataset.add(new datapoint(Double.parseDouble(feature[0]), Double.parseDouble(feature[1]), Double.parseDouble(feature[2]), Double.parseDouble(feature[3]), flowerType));
         }
     }
     input.close();
      }
      catch(FileNotFoundException e){
          System.out.print(e);
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
          if(classify(Double.parseDouble(feature[0]), Double.parseDouble(feature[1]), Double.parseDouble(feature[2]), Double.parseDouble(feature[3]))==flowerType){
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
    public int classify(double SL, double SW, double PL, double PW){
      HashMap<Double, datapoint> distances = new HashMap<Double, datapoint>();
      int one=0;
      int two=0;
      int three=0;
      for(int i =0; i< dataset.size(); i++){
        distances.put(distance(dataset.get(i), SL, SW, PL, PW), dataset.get(i));
      }
      List<Double> sortedDistances = new ArrayList<>(distances.keySet());
      Collections.sort(sortedDistances);
      for(int i=0; i<k; i++){
        int stuff = distances.get(sortedDistances.get(i)).flowerType();
       // System.out.println(distances.get(sortedDistances.get(i)));
      if(stuff==1){
        one++;
      }
      else if(stuff==2){
        two++;
      }
      else if(stuff==3){
        three++;
      }
    }
    if(one>two && one>three){
      return 1;
    }
    if(two>three && two>one){
      return 2;
    }
      
      return 3;
  }
    public double distance(datapoint point,double SL, double SW, double PL, double PW ){
     
     return Math.sqrt(Math.pow((point.getSL()-SL),2)+Math.pow((point.getSW()-SW),2)+Math.pow((point.getPL()-PL),2)+Math.pow((point.getPW()-PW),2));
      
    }

}
