package machinelearning;

import java.io.*;

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
    	
		String Directory = System.getProperty("user.dir");//get local pathing
    	
    	double wSide = 0,wUmfang = 0,bias = 0;
    	int output;
    	double sidenum,localError,globalError;
    	
    	int iteration = 0;
    	try(PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("PerceptronResult.txt", false)))) {
    			
	    	
	    	// Training set-----------------------------------------------------------
	    	do{
	    	globalError = 0;
	        iteration++;
	    	for(int i = 0;i<100;i++){
	    		p.GenerateFigure();
	    		if(side == 3) sidenum = 1; else  sidenum = 0;
	    		result = p.calculateOutput(theta, wSide,sidenum, umfang, wUmfang, bias);
	    		if(sidenum == 1) output = 1; else output = 0;
	    		
	
	    		out.println("Current weights");
	    		out.println("Sides: "+ wSide);
	    		out.println("Umfang weight: "+ wUmfang);
	    		out.println("Bias: "+ bias);
	
	    		
	    		out.print("Number of sides: ");
	    		out.print(side);
	    		
	    		
	    		
	    		out.println();
	    		out.print("Umfang: ");
	    		out.print(umfang);
	
	    		out.println();
	    		if (result == 1){
	    			out.println("its a triangle");
	    		}else{
	    			out.println("Its not a triangle");
	    		}
	    		
	
	    		out.print("true input value:");
	    		out.println(output);
	    		
	       		out.println("iteration: "+iteration);
	    		out.println();
	
	    		
	    		localError = output - result;
	    		globalError += localError*localError;
	    		
	    		wSide += learning_rate * localError * sidenum;
	    		wUmfang += learning_rate * localError * umfang;
	    		bias += learning_rate * localError;
	
	 
	    		
	    		
	    	}
	    	}while (globalError != 0 && iteration <= max_iter);
	    	
	    	out.println("Final Function");
			out.println(wSide+"x"+ " + " +wUmfang +" + "+ bias);
	    	
	    	//Test set----------------------------------------------------------------
	    	for(int i = 0; i < 10 ; i++){
	    		p.GenerateFigure();
	    		if(side == 3) sidenum = 1; else  sidenum = 0;
	
	    		out.print("Number of sides: ");
	    		out.print(side);	
	    	    		
	    		out.println();
	    		out.print("Umfang: ");
	    		out.print(umfang);
	
	    		out.println();
	    		result = p.calculateOutput(theta, wSide,sidenum, umfang, wUmfang, bias);
	    		
	    		if (result == 1){
	    			out.println("its a triangle");
	    		}else{
	    			out.println("Its not a triangle");
	    		}
	    		
	    		//read
	    		out.print("true input value:");
	    		if(sidenum == 1) output = 1; else output = 0;
	    		out.println(output);
	    		out.println();
	    	}
    	}catch (Exception e) {
		    System.out.println("Eror file not created");
		}
    	
    	try
		{
		     Runtime.getRuntime().exec("notepad "+ Directory +"\\PerceptronResult.txt");
		} 
		catch (Exception ex){
		     System.out.println("Error opening file");
		}
    }
    
    private void GenerateFigure(){
    	side = 3 + (int)(Math.random() * ((4 - 3) + 1));
    	umfang = 30 + (int)(Math.random() * ((100 - 30) + 1));
    }
    
    private int calculateOutput(int theta, double wSide, double sidenum , double umfang, double wUmfang, double bias){
    	double sum = sidenum * wSide + umfang * wUmfang + bias ;
    	if (sum >= theta) return 1; else return 0;
    }
    

}
