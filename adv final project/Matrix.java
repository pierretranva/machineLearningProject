import java.util.*;
public class Matrix{

    public Double[][] data;
    private int row;
    private int col;
    
    //constructor for matrix
    public Matrix(int r, int c){
        row = r;
        col =c;
        data = new Double[row][col];
    }
    public Matrix(int r, int c, Double[][] data){
        row = r; 
        col = c;
        this.data = data;
    }
    public int getRow(){
        return row;
    }
    public int getCol(){
        return col;
    }
    public void addScalar(Double scalar){
        for(int i= 0; i< row; i++){
            for(int j =0; j<col; j++){
                data[row][col] =data[row][col] +scalar;
            }
        }
    }
    public Matrix multiply(Matrix m){
       Matrix result = new Matrix(this.getRow(), m.getCol());
       for(int row = 0; row< result.getRow(); row++){
           for(int col = 0; col< result.getCol(); col++){
            double cell = 0;
            for (int k = 0; k < m.getRow(); k++) {
                cell += this.data[row][k] * m.data[k][col];
            }
            result.data[row][col] = cell;
               
               }
           }
           return result;
       }
 public Matrix add(Matrix m){
     Matrix result = new Matrix(this.getRow(), this.getCol());
     for(int i = 0; i< row; i++){
         for(int j =0; j<col; j++){
             result.data[i][j] = this.data[i][j] + m.data[i][j];
         }
     }
     return result;
    }
    public Matrix sigmoid(){
        Matrix result = new Matrix(this.getRow(), this.getCol());
        for(int i = 0; i< row; i++){
            for(int j =0; j<col; j++){
                result.data[i][j] = 1/(1+Math.exp(-1*data[i][j]));
            }
        }
        return result;
    }
    public Matrix transpose(){
        Matrix result = new Matrix(this.getCol(), this.getRow());
        for(int i = 0; i< row; i++){
            for(int j =0; j<col; j++){
                result.data[j][i] = data[i][j];
            }
        }
        return result;
    }
    
    public String toString(){
        String output = "";
        for (Double[] data : data) {
            output +=Arrays.toString(data) + "\n";
        }
        return output;
    }

    }


