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
    public void setVal(int row, int col, Double val){
        data[row][col]=val;
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
    
       public Matrix multiply(Matrix a) {
		Matrix result = new Matrix(this.getRow(), a.getCol());
        for(int i=0;i<a.getRow();i++)
		{
			for(int j=0;j<a.getCol();j++)
			{
				result.data[i][j]= this.data[i][j]*a.data[i][j];
			}
		}
        return result;
		
	}
       public Matrix multiply(double a){
            for(int i =0; i< row; i++){
                for(int j =0; j<col; j++){
                    this.data[row][col] =this.data[row][col] *a;
                }
            }
            return this;
       }
 public Matrix add(Matrix m){
     Matrix result = new Matrix(row, col);
     if(col!=m.col || row!=m.row) {
			System.out.println("Shape Mismatch");
			return null;
		}
		
		for(int i=0;i<row;i++)
		{
			for(int j=0;j<col;j++)
			{
				result.data[i][j]=this.data[i][j]+m.data[i][j];
			}
		}
        return result;
    }
    public Matrix subtract(Matrix m){
        Matrix result = new Matrix(this.getRow(), this.getCol());
        for(int i=0; i< row; i++){
            for(int j =0; j<col; j++){
                result.data[i][j] = this.data[i][j] - m.data[i][j];
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
    public Matrix dsigmoid(){
        Matrix temp = new Matrix(this.row, this.col);
        for(int i =0;i<row; i++){
            for(int j=0; i<col; j++){
                temp.data[i][j] = data[i][j]*(1-data[i][j]);
            }
        }
        return temp;
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
            output +=Arrays.toString(data) + ",";
        }
        return output;
    }
    public int getNumElements(){
        return row*col;
    }

    }


