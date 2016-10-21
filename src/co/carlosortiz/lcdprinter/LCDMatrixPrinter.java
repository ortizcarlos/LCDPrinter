package co.carlosortiz.lcdprinter;


import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

//size+2 columnas y 2*size + 3 filas

/**
 * 
 * Interfaz de entrada para imprimir una cadena de digitos en formato
 * LCD de tamaño variable
 * 
 * @author Carlos
 *
 */


public class LCDMatrixPrinter {

	Map<Integer, int[][]> digitMatrixMap = new HashMap();
	LCDConfigurer lcdConfigurer = new LCDConfigurer();
	
	private static final int DEFAULT_SIZE = 1;
	

	public static void main(String[] args) {
		/*
		String s = "2134567890";
		int size = 6;
		new LCDMatrixPrinter().printDigits(s,size);
		*/
		new LCDMatrixPrinter().renderMenu();
	}
	
	void renderMenu() {
		Scanner scanner = new Scanner(System.in);
		int size = DEFAULT_SIZE;
		while (true) {
			System.out.print("Por favor ingrese cadena de digitos:\r\n");
            String digitos = scanner.nextLine();
            System.out.print("Por favor ingrese tamano entre [1..10] o Cero (0) para terminar :\r\n");
            String ssize = scanner.nextLine();
            
            try {
            	size = Integer.parseInt(ssize);
            } catch (Exception e) {
            	size = DEFAULT_SIZE; 
            }
            
            try {
            	printDigits(digitos,size);
            } catch (Exception e) {
            	System.out.println("Error: " + e.getMessage() ); 
            }
		}

	}

	void printDigits(String digits,int size) {
		if (size==0) {
			System.out.println("Finalizando...");
			System.exit(0);
		}
		
		if (size<0 || size>10) {
			size = 1;
		}
		
		int cols = (size + 2);
		int rows = (2 * size) + 3;
		this.validateDigitString(digits);
		printLCDMatrix( digits , rows, cols);
	}

	/**
	 * Valida que sea una cadena formada por solo digitos
	 * @param s
	 */
	void validateDigitString(String s) {
		for (int i = 0; i < s.length(); i++) {
			if (!Character.isDigit(s.charAt(i))) {
				throw new IllegalArgumentException(
						"Se espera una cadena de numeros");
			}
		}
	}

	/**
	 * redimensiona una fila de caracteres de la plantilla por defecto de un digito
	 * 
	 * @param row
	 * @param newLen
	 * @return
	 */
	String resizeRow(String row, int newLen) {
		StringBuilder newRow = new StringBuilder();
		newRow.append(row.charAt(0));
		for (int i = 0; i < newLen - 2; i++) {
			newRow.append(row.charAt(1));
		}
		newRow.append(row.charAt(2));
		return newRow.toString();
	}

	/**
	 * Redimensiona la plantilla por defecto de un digito al nuevo tamaño
	 * calculado a partir de la variable size
	 *  
	 * 
	 * @param dTemplate
	 * @param rows
	 * @param cols
	 * @return
	 */
	private String[] expandDigitMatrix(String[] dTemplate, int rows, int cols) {		
		
		String[] rTemplate = new String[rows];		
		int center = (rows/2) ;				
		
		String top    = dTemplate[0];
		String middle = dTemplate[2];
		String bottom = dTemplate[4];
		rTemplate[0] = resizeRow(top, cols);
		
		// resize middle-top
		for (int i = 0; i < center - 1; i++) {
			rTemplate[i + 1] = resizeRow(dTemplate[1], cols);
		}		
		rTemplate[center] = resizeRow( middle , cols );		
		// resize middle-bottom
		for (int i = 0 ; i < ( (rows-1) - center) - 1 ; i++) {
			rTemplate[ center + i +  1 ] = resizeRow(dTemplate[3], cols);
		}
		
		rTemplate[rows - 1] = resizeRow(bottom, cols);		
		return rTemplate;
	}

	
	/**
	 * Punto de entrada para imprimir una cadena de digitos
	 * 
	 * @param s
	 * @param rows
	 * @param cols
	 */
	public void printLCDMatrix(String s, int rows, int cols) {		
		
		int characterLen = s.length();
		Map<Integer, String[] > digitMatrixMap = new HashMap();		
		
		for (int i = 0; i < characterLen; i++) {
			int digit = Character.getNumericValue(s.charAt(i));
			//Expande la plantilla por defecto de un digito al nuevo tamaño definido
			//por size ( rows, cols)
			digitMatrixMap.put(digit,  
					expandDigitMatrix( lcdConfigurer.getDigitTemplate(digit), rows, cols) );
		}
		
		for (int r = 0; r < rows; r++) {
			for (int i = 0; i < characterLen; i++) {
				String[] dTemplate = digitMatrixMap.get(Character
						.getNumericValue(s.charAt(i)));
				System.out.print(dTemplate[r]);
				System.out.print(" ");
			}
			System.out.print("\n");
		}
		System.out.print("\n");
	}
	
}
