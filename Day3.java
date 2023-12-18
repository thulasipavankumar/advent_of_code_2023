import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;
import java.util.*;
import java.math.*;
class Day3{
char input[][];
List<String> lines;

// boolean isTopRightSymbol(int i,int j,char input[][]){
    
//     if(i<0 || j<0 ||i>=input.length || j>=input[0].length){
//         return false;
//     }
//     if(isSysmboleInPosition(--i,++j,input)){
//         return true;
//     }
    
//     return false;
// }
// boolean isTopLeftSymbol(int i,int j,char input[][]){
//     if(i<0 || j<0 ||i>=input.length || j>=input[0].length){
//         return false;
//     }
//     if(isSysmboleInPosition(--i,--j,input)){
//         return true;
//     }
//     return false;
// }
// boolean isBottonLeftSymbol(int i,int j,char input[][]){
//     if(i<0 || j<0 ||i>=input.length || j>=input[0].length){
//         return false;
//     }
//     if(isSysmboleInPosition(++i,--j,input)){
//         return true;
//     }
//     return false;
// }
// boolean isBottomRightSymbol(int i,int j,char input[][]){
//     if(i<0 || j<0 ||i>=input.length || j>=input[0].length){
//         return false;
//     }
//     if(isSysmboleInPosition(++i,++j,input)){
//         return true;
//     }
// return false;
// }
// boolean isLeftSymbol(int i,int j,char input[][]){
//     if(i<0 || j<0 ||i>=input.length || j>=input[0].length){
//         return false;
//     }
//     if(isSysmboleInPosition(i,--j,input)){
//         return true;
//     }
// return false;
// }
// boolean isRightSymbol(int i,int j,char input[][]){
//     if(i<0 || j<0 ||i>=input.length || j>=input[0].length){
//         return false;
//     }
//     if(isSysmboleInPosition(i,++j,input)){
//         return true;
//     }
// return false;
// }
boolean isSysmboleInPosition(int i,int j,char input[][]){
    if(i<0 || j<0 ||i>=input.length || j>=input[0].length){
        return false;
    }
    if(!isNumberInthePosition(i, j, input)&&input[i][j]!=46){
        return true;
    }
    return false;
}
int getNumberInthePosition(int i,int j,char input[][]){
    return 0;
}

boolean isNumberInthePosition(int i,int j,char input[][]){
    if(i<0 || j<0 ||i>=input.length || j>=input[0].length){
        return false;
    }
    if(input[i][j]>=48 && input[i][j]<=57){
        return true;
    }
    return false;
}
void readAndSaveInput(){
    BufferedReader reader;

		try {
			reader = new BufferedReader(new FileReader("day_3.txt"));
			String line = reader.readLine();
                if(line!=null)
                lines.add(line);
			while (line != null) {
				// read next line
				line = reader.readLine();
                if(line!=null)
                lines.add(line);

			}

			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
}
Grid constructInnerMatrix(int row,int startingColPosition,int endingColPosition,char[][] input){
    int colLength = input[0].length;
    int rowLength = input.length;
    Grid grid = new Grid();
    
    if(row >0 && row < rowLength-1){
        grid.setTopLeftI(row-1);
        grid.setTopRightI(row-1);

        grid.setBottomLeftI(row+1);
        grid.setBottomRightI(row+1);

    }else {
        if(row == 0){
            grid.setTopLeftI(0);
            grid.setTopRightI(0);
            grid.setBottomLeftI(1);
            grid.setBottomRightI(1);
        }
        if(row == rowLength-1){
            grid.setTopLeftI(rowLength-2);
            grid.setTopRightI(rowLength-2);
            grid.setBottomLeftI(rowLength-1);
            grid.setBottomRightI(rowLength-1);
        }
    }
    if (startingColPosition >= 1 && endingColPosition <= colLength-2){
        grid.setTopLeftJ(startingColPosition-1);
        grid.setBottomLeftJ(startingColPosition-1);

        grid.setTopRightJ(endingColPosition+1);
        grid.setBottomRightJ(endingColPosition+1);
    }else{
        if(startingColPosition==0){
            grid.setTopLeftJ(0);
            grid.setBottomLeftJ(0);
             grid.setTopRightJ(endingColPosition+1);
            grid.setBottomRightJ(endingColPosition+1);
        }
        if(endingColPosition==colLength-1){
            grid.setTopLeftJ(startingColPosition-1);
            grid.setBottomLeftJ(startingColPosition-1);
            grid.setTopRightJ(colLength-1);
            grid.setBottomRightJ(colLength-1);
        }

    }
    return grid;
}
boolean isThereSymbolInGrid(Grid grid,char[][] givenMatrix){
    String toPrint="";
    for (int i=grid.getTopLeftI();i<=grid.getBottomLeftI();i++){
        for(int j=grid.getTopLeftJ();j<=grid.getBottomRightJ();j++){
            if(isSysmboleInPosition(i,j,givenMatrix)){
                return true;
            }else{
                toPrint+=givenMatrix[i][j];
            }
        }
        toPrint+='\n';
    }
    System.out.println(toPrint);
    return false;
}
public static void main(String args[]){
    BigInteger sum = new BigInteger("0");
    int rows,cols;
    Day3 object = new Day3();
    int count =0,numbers=0;
    object.lines = new ArrayList<String>();
    object.readAndSaveInput();
    rows= object.lines.size();
    cols = object.lines.get(0).length();
    object.input = new char[rows][cols];
    //System.out.println(rows+" : "+cols);
    for (int i=0;i<rows;i++){ 
       
        object.input[i]=object.lines.get(i).toCharArray();
    }
    for (int i=0;i<rows;i++){ 
        for(int j=0;j<cols;j++){
            
            String number="";
            if(object.isNumberInthePosition(i, j, object.input)){
                int endingColPosition =j,startColPosition =j;
                while (j<cols && object.isNumberInthePosition(i, j, object.input)){
                    number=number+object.input[i][j];
                    j++;
                }
                endingColPosition=j-1;
                Grid g = object.constructInnerMatrix(i,startColPosition,endingColPosition,object.input);
               // System.out.println(number);
               numbers++;
                if(object.isThereSymbolInGrid(g, object.input)){
                    sum = sum.add(new BigInteger(number));
                    count++;
                  //  System.out.println(" Pass");
                }else{
                     System.out.println(number+" fail "+g.toString());
                }
            }
        }
       
    }
    System.out.println(count+": out of : "+numbers);
    System.out.println("Sum:"+sum);
}
class Grid{
   private  int topLeftI,topRightI,bottomLeftI,bottomRightI;
   private  int topLeftJ,topRightJ,bottomLeftJ,bottomRightJ;
    Grid(){

    }
    // Top left
    void setTopLeftI(int givenValue){
        topLeftI=givenValue;
    }
    void setTopLeftJ(int givenValue){
        topLeftJ=givenValue;
    }
     int getTopLeftI(){
        return topLeftI;
    }
    int getTopLeftJ(){
        return topLeftJ;
    }

    // Bottom left
    void setBottomLeftI(int givenValue){
        bottomLeftI=givenValue;
    }
    void setBottomLeftJ(int givenValue){
        bottomLeftJ=givenValue;
    }
    int getBottomLeftI(){
        return bottomLeftI;
    }
    int getBottomLeftJ(){
        return bottomLeftJ;
    }

   
    // Top right
    void setTopRightI(int givenValue){
        topRightI=givenValue;
    }
    void setTopRightJ(int givenValue){
        topRightJ=givenValue;
    }

    int getTopRightI(){
        return topRightI;
    }
    int getTopRightJ(){
        return topRightJ;
    }

    // Bottom right
    void setBottomRightI(int givenValue){
        bottomRightI=givenValue;
    }
    void setBottomRightJ(int givenValue){
        bottomRightJ=givenValue;
    }
    int getBottomRightI(){
        return bottomRightI;
    }
    int getBottomRightJ(){
        return bottomRightJ;
    }
    
    public String toString(){
        return "\n"+topLeftI+":"+topLeftJ+"  "+topRightI+":"+topRightJ+"\n"+bottomLeftI+":"+bottomLeftJ+"  "+bottomRightI+":"+bottomRightJ;
    }
    
}
}