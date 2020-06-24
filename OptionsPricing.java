import java.util.Scanner;

class OptionsPricing {
	
	public static void main(String args[]) {
		double S, X, q, r, stdev;
		double t = 0;

		UserGui gui = new UserGui();
		
		// try (Scanner scan = new Scanner (System.in)) {
		// 	System.out.print("Enter option type (call/put): ");
		// 	String option = scan.nextLine();
			
		// 	System.out.print("Time to expration: "); // end with yr for years, or mo for months
		// 	String time = scan.nextLine();
		// 	String[] input = time.split(" ");
			
		// 	if (input[1].equalsIgnoreCase("yr")) {
		// 		t = Double.parseDouble(input[0]);
		// 	}
		// 	else if (input[1].equalsIgnoreCase("mo")) {
		// 		t = Double.parseDouble(input[0]) / 12;
		// 	}
				
			
		// 	System.out.print("Underlying Price: ");
		// 	S = scan.nextDouble();
			
		// 	System.out.print("Strike Price: ");
		// 	X = scan.nextDouble();
			
			
		// 	System.out.print("Dividend Yield: ");
		// 	q = scan.nextDouble();
			
		// 	System.out.print("Annual Risk Free Rate: ");
		// 	r = scan.nextDouble();
			
		// 	System.out.print("Annualized Volatility: "); // stdev of daily % change
		// 	stdev = scan.nextDouble();
			
		// 	System.out.println("----------");
			
			
		// 	if (option.equalsIgnoreCase("call")) {
		// 		double call_price = options_calculations.call_calc (S, X, t, q, r, stdev);
		// 		System.out.println("Option Price is: " + call_price);	
		// 		double call_delta = options_calculations.call_delta_calc (S, X, t, q, r, stdev);
		// 		System.out.print("Delta: " + call_delta);
		// 		double gamma = options_calculations.gamma_calc (S, X, t, q, r, stdev);
		// 		System.out.print("\tGamma: " + gamma);
		// 		double call_theta = options_calculations.call_theta_calc (S, X, t, q, r, stdev);
		// 		System.out.print("\tTheta: " + call_theta);
		// 	}
			
		// 	else if (option.equalsIgnoreCase("put")) {
		// 		double put_price = options_calculations.put_calc (S, X, t, q, r, stdev);
		// 		System.out.println("Option Price is: " + put_price);
		// 		double put_delta = options_calculations.put_delta_calc (S, X, t, q, r, stdev);
		// 		System.out.print("Delta: " + put_delta);
		// 		double gamma = options_calculations.gamma_calc (S, X, t, q, r, stdev);
		// 		System.out.print("\tGamma: " + gamma);
		// 		double put_theta = options_calculations.put_theta_calc (S, X, t, q, r, stdev);
		// 		System.out.print("\tTheta: " + put_theta);
		// 	}
		// }
	}
}