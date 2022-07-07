import java.io.File;
public class machineLearningDriver {
    public static void main(String[] args){
        String traindata = "IRIS.csv";
        String testdata = "IRIS_TEST.csv";
        File trainFile = new File(traindata);
        File testFile = new File(testdata);
        KNN kModel = new KNN();
        kModel.train(100, trainFile);
        //System.out.println(kModel.getDataset());
        Double acc= kModel.test(30,testFile);
        System.out.println("KNN accuracy: " + acc*100 + "%");


        NN nnModel = new NN(4,3,"weights.txt");
        nnModel.fit(100, trainFile);
        Double NNaccuracy = nnModel.test(30,testFile);
        System.out.println("NN accuracy: " + NNaccuracy*100 + "%");
    }  
    
    //test matrix mult method
   /*  public static void main(String[] args){
        Double[][] arr = {{1.0, 2.0,3.0, 4.0}, {3.0, 6.0, 2.0, 4.0}};
        Double[][] yarr = {{2.0, 3.0}, {5.0, 7.0},{5.0, 7.0}, {7.0, 1.0}};
        Matrix m = new Matrix(2,4, arr);
        Matrix n = new Matrix(4,2, yarr);
        Matrix thing = m.multiply(n);
        System.out.print(thing);
        
    } */
    
}
