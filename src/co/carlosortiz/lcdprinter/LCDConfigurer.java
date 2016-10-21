package co.carlosortiz.lcdprinter;


import java.util.HashMap;
import java.util.Map;


/**
 * 
 * Clase utilitaria para configurar las plantillas de cada digito del cero al 9
 * 
 * @author Carlos
 *
 */

public class LCDConfigurer {
	
	Map<Integer, String[] > digitMatrixMap = new HashMap();
	
	private String EEE    = "   ";
	
	private String VEE    = "|  ";
	
	private String EEV    = "  |";
	
	private String EHE    = " _ ";
	
	private String EHV    = " _|";
	
	private String VHE    = "|_ ";
	
	private String VHV    = "|_|";
	
	private String VEV    = "| |";
	
	LCDConfigurer() {
		
		String[] ZERO   = { EHE , VEV, VEV, VEV, VHV };
		String[] ONE    = { EEE , EEV, EEV, EEV, EEV };
		String[] TWO    = { EHE , EEV, EHV, VEE, VHE };
		String[] THREE  = { EHE , EEV, EHV, EEV, EHV };
		String[] FOUR   = { EEE , VEV, VHV, EEV, EEV };
		String[] FIVE   = { EHE , VEE, VHE, EEV, EHV };
		
		String[] SIX     = { EHE , VEE, VHE, VEV, VHV };
		String[] SEVEN   = { EHE , EEV, EEV, EEV, EEV };
		String[] EIGHT   = { EHE , VEV, VHV, VEV, VHV };
		String[] NINE    = { EHE , VEV, VHV, EEV, EEV };
		
		
		digitMatrixMap.put(0, ZERO);
		digitMatrixMap.put(1, ONE);
		digitMatrixMap.put(2, TWO);	
		digitMatrixMap.put(3, THREE);
		digitMatrixMap.put(4, FOUR);
		digitMatrixMap.put(5, FIVE);
		
		digitMatrixMap.put(6, SIX);
		digitMatrixMap.put(7, SEVEN);
		digitMatrixMap.put(8, EIGHT);
		digitMatrixMap.put(9, NINE);
	}
	
	public final String[] getDigitTemplate(int digit) {
		return digitMatrixMap.get(digit);
	}
	
}
