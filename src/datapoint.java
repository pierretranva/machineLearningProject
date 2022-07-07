public class datapoint{
double sepal_length;
double sepal_width;
double petal_length;
double petal_width;
int flowerType; //number is the type of flower 1=Iris-setosa 2=Iris-versicolor 3=Iris-virginica

public datapoint(double sl, double sw, double pl, double pw, int flower){
sepal_length = sl;
sepal_width = sw;
petal_length = pl;
petal_width = pw;
flowerType = flower;
}
//return sepal_length
public double getSL(){
    return sepal_length;
}
//return sepal_width
public double getSW(){
    return sepal_width;
}
//returns petal_length
public double getPL(){
    return petal_length;
}
//returns petal_width
public double getPW(){
    return petal_width;
}
//returns type of flower
public int flowerType(){
    return flowerType;
}
public String toString(){
return "["+ sepal_length + ","+ sepal_width+","+ petal_length +","+petal_width  + "]";

}


}