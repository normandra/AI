package machinelearning;
import java.util.Scanner;

/**
 *
 * @author Norman Mahendra
 */


public class Perceptron {

	static int theta = 1,max_iter = 100;
	static double learning_rate = 0.1;
	static int side,umfang,result;
	
	
    public static void main(String args[]){
    	Perceptron p = new Perceptron();
    	Scanner s = new Scanner(System.in);
    	
    	double wSide = 0,wUmfang = 0,bias = 0;
    	int output;
    	double sidenum,localError,globalError;
    	
    	int iteration = 0;
    	
    	// Training set-----------------------------------------------------------
    	do{
    	globalError = 0;
        iteration++;
    	for(int i = 0;i<100;i++){
    		p.GenerateFigure();
    		if(side == 3) sidenum = 1; else  sidenum = 0;

    		System.out.println("Current weights");
    		System.out.println("Sides: "+ wSide);
    		System.out.println("Umfang weight: "+ wUmfang);
    		System.out.println("Bias: "+ bias);

    		
    		System.out.print("Number of sides: ");
    		System.out.print(side);
    		
    		
    		
    		System.out.println();
    		System.out.print("Umfang: ");
    		System.out.print(umfang);

    		System.out.println();
    		result = p.calculateOutput(theta, wSide,sidenum, umfang, wUmfang, bias);
    		if (result == 1){
    			System.out.println("its a triangle");
    		}else{
    			System.out.println("Its not a triangle");
    		}
    		

    		
    		//read
    		System.out.print("true input value:");
    		//Automatic solve
    		
    		if(sidenum == 1) output = 1; else output = 0;
    		System.out.println(output);
    		
    		// Manual 
    		//output = Integer.parseInt(s.nextLine());
    		
    		localError = output - result;
    		globalError += localError*localError;
    		
    		wSide += learning_rate * localError * sidenum;
    		wUmfang += learning_rate * localError * umfang;
    		bias += learning_rate * localError;

    		System.out.println("iteration: "+iteration);
    		System.out.println();
    		
    		
    	}
    	}while (globalError != 0 && iteration <= max_iter);
    	
    	System.out.println("Final Function");
		System.out.println(wSide+"x"+ " + " +wUmfang +" + "+ bias);
    	
    	//Test set----------------------------------------------------------------
    	for(int i = 0; i < 10 ; i++){
    		p.GenerateFigure();
    		if(side == 3) sidenum = 1; else  sidenum = 0;

    		System.out.print("Number of sides: ");
    		System.out.print(side);	
    	    		
    		System.out.println();
    		System.out.print("Umfang: ");
    		System.out.print(umfang);

    		System.out.println();
    		result = p.calculateOutput(theta, wSide,sidenum, umfang, wUmfang, bias);
    		
    		if (result == 1){
    			System.out.println("its a triangle");
    		}else{
    			System.out.println("Its not a triangle");
    		}
    		
    		//read
    		System.out.print("true input value:");
    		if(sidenum == 1) output = 1; else output = 0;
    		System.out.println(output);
    		System.out.println();
    	}
    }
    
    private void GenerateFigure(){
    	side = 3 + (int)(Math.random() * ((4 - 3) + 1));
    	umfang = 30 + (int)(Math.random() * ((1000 - 30) + 1));
    }
    
    private int calculateOutput(int theta, double wSide, double sidenum , double umfang, double wUmfang, double bias){
    	double sum = sidenum * wSide + umfang * wUmfang + bias ;
    	if (sum >= theta) return 1; else return 0;
    }
    

}
