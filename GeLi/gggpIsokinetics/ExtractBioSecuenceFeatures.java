package gggpIsokinetics;

/**
 * 
 * 
 * @author Jorge Couchet
 * 
 */

import java.io.IOException;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.Iterator;
import java.util.Locale;

import cern.colt.matrix.DoubleMatrix2D;
import cern.colt.matrix.impl.DenseDoubleMatrix2D;
import cern.colt.matrix.DoubleMatrix1D;
import cern.colt.matrix.impl.DenseDoubleMatrix1D;
import util.io.file.GenericFile;





 /**
 * @author Jorge Couchet
 */

public class ExtractBioSecuenceFeatures {

	protected DoubleMatrix2D res;
	
	
	public static void main(String[] args) {
		
		
		ExtractBioSecuenceFeatures.BuildBioData("C:/etc/Final/Iso/Original3/Train/RN-Train.info", 
										 	    "C:/etc/Final/Iso/Genetic/RNormEje-Train18.sec",
										 	    "C:/etc/Final/Iso/Genetic/RNormEje-Train26.sec",
										 	    0);
		
		ExtractBioSecuenceFeatures.BuildBioData("C:/etc/Final/Iso/Original3/Train/LN-Train.info", 
				 						 	    "C:/etc/Final/Iso/Genetic/LNormEje-Train18.sec",
				 						 	    "C:/etc/Final/Iso/Genetic/LNormEje-Train26.sec",
				 						 	    0);
		
		ExtractBioSecuenceFeatures.BuildBioData("C:/etc/Final/Iso/Original3/Train/RC-Train.info", 
				 						 	    "C:/etc/Final/Iso/Genetic/RCondEje-Train18.sec",
				 						 	    "C:/etc/Final/Iso/Genetic/RCondEje-Train26.sec",
				 						 	    1);

		ExtractBioSecuenceFeatures.BuildBioData("C:/etc/Final/Iso/Original3/Train/LC-Train.info", 
				 						 		"C:/etc/Final/Iso/Genetic/LCondEje-Train18.sec",
				 						 		"C:/etc/Final/Iso/Genetic/LCondEje-Train26.sec",
				 						 		1);
		
		
		
		
		ExtractBioSecuenceFeatures.BuildBioData("C:/etc/Final/Iso/Original3/Test/RN-Test.info", 
												"C:/etc/Final/Iso/Genetic/RNormEje-Test18.sec",
												"C:/etc/Final/Iso/Genetic/RNormEje-Test26.sec",
												0);

		ExtractBioSecuenceFeatures.BuildBioData("C:/etc/Final/Iso/Original3/Test/LN-Test.info", 
												"C:/etc/Final/Iso/Genetic/LNormEje-Test18.sec",
												"C:/etc/Final/Iso/Genetic/LNormEje-Test26.sec",
												0);

		ExtractBioSecuenceFeatures.BuildBioData("C:/etc/Final/Iso/Original3/Test/RC-Test.info", 
												"C:/etc/Final/Iso/Genetic/RCondEje-Test18.sec",
												"C:/etc/Final/Iso/Genetic/RCondEje-Test26.sec",
												1);

		ExtractBioSecuenceFeatures.BuildBioData("C:/etc/Final/Iso/Original3/Test/LC-Test.info", 
												"C:/etc/Final/Iso/Genetic/LCondEje-Test18.sec",
												"C:/etc/Final/Iso/Genetic/LCondEje-Test26.sec",
												1);
		
	}
	

	/**
	 * 
	 * @return
	 * 			Returns the first 72 primes.			
	 * 
	 */
	static public double[] Prime() {
	      
		int[] prime = new int[72];
		double[] logPrime= new double[72];
	      
		prime[0] = 2;
	    prime[1] = 3;
	    prime[2] = 5;
	    prime[3] = 7;
	    prime[4] = 11;
	    prime[5] = 13;
	    prime[6] = 17;
	    prime[7] = 19;
	    prime[8] = 23;
	    prime[9] = 29;
	    prime[10] = 31;
	    prime[11] = 37;
	    prime[12] = 41;
	    prime[13] = 43;
	    prime[14] = 47;
	    prime[15] = 53;
	    prime[16] = 59;
	    prime[17] = 61;
	    prime[18] = 67;
	    prime[19] = 71;
	    prime[20] = 73;
	    prime[21] = 79;
	    prime[22] = 83;
	    prime[23] = 89;
	    prime[24] = 97;
	    prime[25] = 101;
	    prime[26] = 103;
	    prime[27] = 107;
	    prime[28] = 109;
	    prime[29] = 113;
	    prime[30] = 127;
	    prime[31] = 131;
	    prime[32] = 137;
	    prime[33] = 139;
	    prime[34] = 149;
	    prime[35] = 151;
	    prime[36] = 157;
	    prime[37] = 163;
	    prime[38] = 167;
	    prime[39] = 173;
	    prime[40] = 179;
	    prime[41] = 181;
	    prime[42] = 191;
	    prime[43] = 193;
	    prime[44] = 197;
	    prime[45] = 199;
	    prime[46] = 211;
	    prime[47] = 223;
	    prime[48] = 227;
	    prime[49] = 229;
	    prime[50] = 233;
	    prime[51] = 239;
	    prime[52] = 241;
	    prime[53] = 251;
	    prime[54] = 257;
	    prime[55] = 263;
	    prime[56] = 269;
	    prime[57] = 271;
	    prime[58] = 277;
	    prime[59] = 281;
	    prime[60] = 283;
	    prime[61] = 293;
	    prime[62] = 307;
	    prime[63] = 311;
	    prime[64] = 313;
	    prime[65] = 317;
	    prime[66] = 331;
	    prime[67] = 337;
	    prime[68] = 347;
	    prime[69] = 349;
	    prime[70] = 353;
	    prime[71] = 359;
	    
	    for (int i=0;i<prime.length; i++){
            logPrime[i]= Math.log10(prime[i]);
	    }
	    
	    return logPrime;

	}
	
	
	/**
	 * 
	 * The function extracts the features from a Isokinetics Time Series corresponding to an excercise.
	 * 
	 * @param fileInfo
	 * 					The file with the Isokinetics Time Series of several excercises.
	 * 
	 * @param fileSecfeatureVec18
	 * 							  The name of the file where the 18 features extracted from each excercise will
	 *                            be saved.
	 * 
	 * @param fileSecfeatureVec26
	 * 							  The name of the file where the 26 features extracted from each excercise will
	 *                            be saved.
	 * 
	 * @param label
	 * 				The label corresponding to "fileInfo", it is NORMAL ( = 0) or INJURY ( = 1). That is, all the 
	 * 				excercises holded in the file are of the same class.
	 * 
	 * 
	 */
	static public void BuildBioData(String fileInfo, String fileSecfeatureVec18, 
			                        String fileSecfeatureVec26, int label){
		
		/** ESTADISTICAS EN LA REPETICION */
		
		// El torque máximo en una repetición
		double max_torque_rep;
		// El ángulo del torque máximo en la repetición
		double max_torque_ang_rep;
		// El tiempo del torque máximo
		double max_t_tor_rep;
		// El torque mínimo en una repetición
		double min_torque_rep;
		// El ángulo del torque mínimo en la repetición
		double min_torque_ang_rep;
		// El tiempo del torque mínimo
		double min_t_tor_rep;
		// El tiempo de la extensión en la repetición
		double t_ext_rep;
		double old_t_ext_rep;
		// El tiempo de la flexión en la repetición
		double t_flex_rep;
		double old_t_flex_rep;
		// Cantidad de elementos en la extensión
		int tot_elem_ext_rep;
		// Suma de los torques de una extensión
		double tot_torque_ext_rep;
		// Torque medio en la extensión
		double torque_medio_ext_rep;
		// Desviación torque medio en la extensión
		double torque_desv_medio_ext_rep;
		// Cantidad de elementos en la flexión
		int tot_elem_flex_rep;
		// Suma de los torques de una flexión
		double tot_torque_flex_rep;
		// Torque medio en la flexión
		double torque_medio_flex_rep;
		// Desviación torque medio en la flexión
		double torque_desv_medio_flex_rep;
		// Tiempo total de una repetición
		double t_total_rep;
		
		/** ESTADISTICAS EN EL EJERCICIO */
		
		// Cantidad de repeticiones del ejercicio
		int tot_rep_eje;
		// Números primos para ser usados para codificar la secuencias de diferencias entre máximos en
		// las distintas repeticiones (también se codifican los mínimos y los correspondientes ángulos)
		double[] primes = ExtractBioSecuenceFeatures.Prime();
		// Secuencia de diferencias entre máximos de las repeticiones de un ejercicio
		double sec_dif_max;
		double old_sec_max;
		// Secuencia de diferencias entre ángulos correspondientes a los máximos de las repeticiones de un ejercicio
		double sec_dif_max_ang;
		double old_sec_ang_max;
		// Secuencia de diferencias entre mínimos de las repeticiones de un ejercicio
		double sec_dif_min;
		double old_sec_min;
        // Secuencia de diferencias entre ángulos correspondientes a los mínimos de las repeticiones de un ejercicio
		double sec_dif_min_ang;
		double old_sec_ang_min;
		// El tiempo del ejercicio
		double t_eje;
		// El tiempo en llegar al máximo en el ejercicio
		double t_max_eje;
		// El tiempo en llegar al mínimo en el ejercicio
		double t_min_eje;
		// El torque máximo en un ejercicio
		double max_torque;
		// El ángulo del torque máximo en el ejercicio
		double max_torque_ang;
		// El torque mínimo en un ejercicio
		double min_torque;
		// El ángulo del torque mínimo en ejercicio
		double min_torque_ang;
		// Se usa para calcular el tiempo medio para llegar al máximo en las extensiones
		double tot_t_max;
		double tot_t_med_max;
		double tot_t_desv_max;
		// Se usa para calcular el tiempo medio para llegar al mínimo en las flexiones
		double tot_t_min;
		double tot_t_med_min;
		double tot_t_desv_min;
		//Se usa para calcular el tiempo medio de las extensiones
		double tot_t_ext;
		double tot_t_med_ext;
		double tot_t_desv_ext;
		// Se usa para calcular el tiempo medio de las flexiones
		double tot_t_flex;
		double tot_t_med_flex;
		double tot_t_desv_flex;
		// Se usa para calcular el tiempo medio de las repeticiones
		double tot_t_rep;
		double tot_t_med_rep;
		double tot_t_desv_rep;
		// Cantidad de elementos en las extensiones
		int tot_elem_ext;
		// Suma de los torques de las extensiones
		double tot_torque_ext;
		double tot_torque_med_ext;
		double tot_torque_desv_ext;
		// Cantidad de elementos en las flexión
		int tot_elem_flex;
		// Suma de los torques de las flexiones
		double tot_torque_flex;
		double tot_torque_med_flex;
		double tot_torque_desv_flex;
		
		GenericFile gen;
		gen = new GenericFile();
		
		try{
			
			gen.load(fileInfo);
			
			DoubleMatrix2D d = gen.getData();
			
			int rowsDLrn = 0;
			int colsDLrn = 4;
			
			int rowsDSecRep = 0;
			int colsDSecRep = 17;
			
			int rowsDSecEje = 0;
			int colsDSecEje = 32;
			
			tot_rep_eje = 0;
			tot_elem_ext = 0;
			tot_elem_flex = 0;
			tot_torque_flex = 0;
			tot_torque_ext = 0;
			max_torque = Integer.MIN_VALUE;
			max_torque_ang = Integer.MIN_VALUE;
			min_torque = Integer.MAX_VALUE;
			min_torque_ang = Integer.MAX_VALUE;
			sec_dif_max = 1;
			sec_dif_max_ang = 1;
			sec_dif_min = 1;
			sec_dif_min_ang = 1;
			old_sec_max = 0;
			old_sec_ang_max = 0;
			old_sec_min = 0;
			old_sec_ang_min = 0;
			t_eje = 0;
			t_max_eje = 0;
			t_min_eje = 0;
			tot_t_max = 0;
			tot_t_min = 0;
			tot_t_ext = 0;
			tot_t_flex = 0;
			tot_t_rep = 0;
			
			DoubleMatrix2D dLrnRep = new DenseDoubleMatrix2D(d.rows(), colsDLrn);
			DoubleMatrix2D dSecRep = new DenseDoubleMatrix2D(d.rows(), colsDSecRep);
			DoubleMatrix2D dSecEje = new DenseDoubleMatrix2D(d.rows(), colsDSecEje);
			
			DoubleMatrix2D auxExt = new DenseDoubleMatrix2D(d.rows(),1);
			DoubleMatrix2D auxFlex = new DenseDoubleMatrix2D(d.rows(),1);
			
			int i=0;
			
			while(i < d.rows()){
				
				/** Aquí comienza un ejercicio */
				if(d.get(i, 0) == -3535){
					
					rowsDSecEje++;
					
					tot_rep_eje =0;
					
					//Key
					dSecEje.set(rowsDSecEje-1, 0, rowsDSecEje);
					// Comienzo de la secuencia de repeticiones de un ejercicio
					dSecEje.set(rowsDSecEje-1, 1, (rowsDSecRep + 1));
					//Label
					dSecEje.set(rowsDSecEje-1, 3, label);
					//Id de la persona
					dSecEje.set(rowsDSecEje-1, 4, d.get(i, 1));
					//Sexo de la persona
					dSecEje.set(rowsDSecEje-1, 5, d.get(i, 2));
					
					max_torque = Integer.MIN_VALUE;
					max_torque_ang = Integer.MIN_VALUE;
					min_torque = Integer.MAX_VALUE;
					min_torque_ang = Integer.MAX_VALUE;
					
					tot_elem_ext = 0;
					tot_torque_ext = 0;
					tot_elem_flex = 0;
					tot_torque_flex = 0;
					
					sec_dif_max = 1;
					sec_dif_max_ang = 1;
					sec_dif_min = 1;
					sec_dif_min_ang = 1;
					
					t_eje = 0;
					t_max_eje = 0;
					t_min_eje = 0;
					
					tot_t_max = 0;
					tot_t_min = 0;
					
					tot_t_ext = 0;
					tot_t_flex = 0;
					
					tot_t_rep = 0;
					
					i++;
				}
				
				int start_rep = rowsDSecRep;
				
				/** Aquí estoy dentro de un ejercicio */
								
				while((i<d.rows()) && (d.get(i, 0) != -3535)){
				
					/** Aquí comienza una repetición */
					
					rowsDSecRep++;
					
					tot_rep_eje++;
					
					max_torque_rep = Integer.MIN_VALUE;
					max_torque_ang_rep = Integer.MIN_VALUE;
					max_t_tor_rep = 0;
					min_torque_rep = Integer.MAX_VALUE;
					min_torque_ang_rep = Integer.MAX_VALUE;
					min_t_tor_rep = 0;
					
					// Key
					dSecRep.set(rowsDSecRep-1, 0, rowsDSecRep);
					// Comienzo de secuencia de valores de una repetición
					dSecRep.set(rowsDSecRep-1, 1, (rowsDLrn + 1));
					
					/** Aquí estoy dentro de la extensión de la repetición */
					
					tot_elem_ext_rep = 0;
					tot_torque_ext_rep = 0;
					t_ext_rep = 0;
					old_t_ext_rep = 0;
					
					int start_ext = rowsDLrn;
					
					while((i<d.rows()) && (d.get(i, 2) >= 0) && (d.get(i, 0) != -3535)){
						
						tot_elem_ext_rep++;
						tot_elem_ext++;
						rowsDLrn++;
						
						// Key
						dLrnRep.set(rowsDLrn-1, 0, rowsDLrn);
						// Valor absoluto del tiempo
						dLrnRep.set(rowsDLrn-1, 1, Math.abs(d.get(i, 0)));
						// Valor absoluto del angulo
						dLrnRep.set(rowsDLrn-1, 2, Math.abs(d.get(i, 1)));
						// Torque
						dLrnRep.set(rowsDLrn-1, 3, d.get(i, 2));
						
						//Necesito los torques de todas las extensiones para calcular su desviación global
						auxExt.set(tot_elem_ext-1, 0, d.get(i, 2));
						
						tot_torque_ext_rep = tot_torque_ext_rep + d.get(i, 2);
						tot_torque_ext = tot_torque_ext + d.get(i, 2);
						
						if(tot_elem_ext_rep != 1){
							
							t_ext_rep = t_ext_rep + Math.abs(Math.abs(d.get(i, 0)) - old_t_ext_rep);
							t_eje = t_eje + Math.abs(Math.abs(d.get(i, 0)) - old_t_ext_rep);
							old_t_ext_rep = Math.abs(d.get(i, 0));
							
							
						}else{
							old_t_ext_rep = Math.abs(d.get(i, 0));
						}
						
						if(d.get(i, 2) > max_torque_rep){
							
							max_torque_rep = d.get(i, 2);
							max_torque_ang_rep = Math.abs(d.get(i, 1));
							max_t_tor_rep = t_ext_rep;
						}
						
						if(d.get(i, 2) > max_torque){
							
							max_torque = d.get(i, 2);
							t_max_eje = t_eje;
							max_torque_ang = Math.abs(d.get(i, 1));
						}
						
												
						i++;
					}
					
					/** Aquí finaliza una extensión */
					
					int final_ext = rowsDLrn;
					
					//Calculo el torque medio de la extensión
					torque_medio_ext_rep = 0;
					if(tot_elem_ext_rep != 0){
						torque_medio_ext_rep = tot_torque_ext_rep/tot_elem_ext_rep;
					}
					
					//Calculo la varianza muestral del torque en la extensión
					torque_desv_medio_ext_rep = 0;
					for(int j= start_ext; j < final_ext; j++){
						torque_desv_medio_ext_rep = torque_desv_medio_ext_rep + Math.pow((dLrnRep.get(j, 3)-torque_medio_ext_rep),2);
					}
					if((tot_elem_ext_rep-1) != 0){
						torque_desv_medio_ext_rep = torque_desv_medio_ext_rep/(tot_elem_ext_rep-1);
					}
					
					//Verifico que no sea la primera repetición
					if(tot_rep_eje != 1){
						
						//Calculo el elemento actual de diferencia entre máximos y lo codifico con primos 
						//(Teo fundamental de la aritmética y le aplico logaritmo)
						sec_dif_max = sec_dif_max + primes[tot_rep_eje-2]*(old_sec_max - max_torque_rep);
						sec_dif_max_ang = sec_dif_max_ang + primes[tot_rep_eje-2]*(old_sec_ang_max - max_torque_ang_rep);
											
						old_sec_max = max_torque_rep;
						old_sec_ang_max = max_torque_ang_rep;
						
					}else{
						  old_sec_max = max_torque_rep;
						  old_sec_ang_max = max_torque_ang_rep;
					}
					
					/** Aquí estoy dentro de la flexión de la repetición */
					
					tot_elem_flex_rep = 0;
					tot_torque_flex_rep = 0;
					t_flex_rep = 0;
					old_t_flex_rep = 0;
					
					int start_flex = rowsDLrn;
					
					while((i< d.rows()) && (d.get(i, 2) <= 0) && (d.get(i, 0) != -3535)){
						
						tot_elem_flex_rep++;
						tot_elem_flex++;
						rowsDLrn++;
						
						// Key
						dLrnRep.set(rowsDLrn-1, 0, rowsDLrn);
						// Valor absoluto del tiempo
						dLrnRep.set(rowsDLrn-1, 1, Math.abs(d.get(i, 0)));
						// Valor absoluto del angulo
						dLrnRep.set(rowsDLrn-1, 2, Math.abs(d.get(i, 1)));
						// Torque
						dLrnRep.set(rowsDLrn-1, 3, d.get(i, 2));
						
						// Necesito los torques de todas las flexiones para calcular su desviación global
						auxFlex.set(tot_elem_flex-1, 0, d.get(i, 2));
						
						tot_torque_flex_rep = tot_torque_flex_rep + d.get(i, 2);
						tot_torque_flex = tot_torque_flex + d.get(i, 2);
						
						if(tot_elem_flex_rep != 1){
							
							t_flex_rep = t_flex_rep + Math.abs(Math.abs(d.get(i, 0)) - old_t_flex_rep);
							t_eje = t_eje + Math.abs(Math.abs(d.get(i, 0)) - old_t_flex_rep);
							old_t_flex_rep = Math.abs(d.get(i, 0));
							
							
						}else{
								old_t_flex_rep = Math.abs(d.get(i, 0));
						}
						
						if(d.get(i, 2) < min_torque_rep){
							
							min_torque_rep = d.get(i, 2);
							min_torque_ang_rep = Math.abs(d.get(i, 1));
							min_t_tor_rep = t_flex_rep;
						}
						
						if(d.get(i, 2) < min_torque){
							
							min_torque = d.get(i, 2);
							t_min_eje = t_eje;
							min_torque_ang = Math.abs(d.get(i, 1));
						}
						
												
						i++;
					}
					
					/** Aquí finaliza la flexión */
					
					int final_flex = rowsDLrn;
					
					//Calculo el torque medio de la flexión
					torque_medio_flex_rep = 0;
					if(tot_elem_flex_rep != 0){
						torque_medio_flex_rep = tot_torque_flex_rep/tot_elem_flex_rep;
					}
					
					//	Calculo la varianza muestral del torque en la flexion
					torque_desv_medio_flex_rep = 0;
					for(int j= start_flex; j < final_flex; j++){
						torque_desv_medio_flex_rep = torque_desv_medio_flex_rep + Math.pow((dLrnRep.get(j, 3)-torque_medio_flex_rep),2);
					}
					if((tot_elem_flex_rep-1) != 0){
						torque_desv_medio_flex_rep = torque_desv_medio_flex_rep/(tot_elem_flex_rep-1);
					}
					
					// Verifico que no sea la primera repetición
					if(tot_rep_eje != 1){
						
						//Calculo el elemento actual de diferencia entre mínimos y lo codifico con primos 
						//(Teo fundamental de la aritmética y le aplico logaritmo)
						sec_dif_min = sec_dif_min +  primes[tot_rep_eje-2]*(old_sec_min - min_torque_rep);
						sec_dif_min_ang = sec_dif_min_ang + primes[tot_rep_eje-2]*(old_sec_ang_min - min_torque_ang_rep);
												
						old_sec_min = min_torque_rep;
						old_sec_ang_min = min_torque_ang_rep;
						
					}else{
						  old_sec_min = min_torque_rep;
						  old_sec_ang_min = min_torque_ang_rep;
					}
					
					
					// Final de secuencia de valores de una repetición
					dSecRep.set(rowsDSecRep-1, 2, dSecRep.get(rowsDSecRep-1, 1) + (tot_elem_ext_rep + tot_elem_flex_rep - 1));
					
					//Label
					dSecRep.set(rowsDSecRep-1, 3, label);
					//Tiempo total de una repetición
					t_total_rep = t_ext_rep + t_flex_rep;
					dSecRep.set(rowsDSecRep-1, 4, t_total_rep);
					//Tiempo total de la extensión en la repetición
					dSecRep.set(rowsDSecRep-1, 5, t_ext_rep);
					//Tiempo total de la flexión en la repetición
					dSecRep.set(rowsDSecRep-1, 6, t_flex_rep);
					//Máximo de la extensión en la repetición
					dSecRep.set(rowsDSecRep-1, 7, max_torque_rep);
					//Angulo del máximo de la extensión en la repetición
					dSecRep.set(rowsDSecRep-1, 8, max_torque_ang_rep);
					//Mínimo de la flexión en la repetición
					dSecRep.set(rowsDSecRep-1, 9, min_torque_rep);
					//Angulo del mínimo de la flexión en la repetición
					dSecRep.set(rowsDSecRep-1, 10, min_torque_ang_rep);
					// Tiempo en llegar al máximo en la extensión
					dSecRep.set(rowsDSecRep-1, 11, max_t_tor_rep);
					// Tiempo en llegar al mínimo en la flexión
					dSecRep.set(rowsDSecRep-1, 12, min_t_tor_rep);
					// Torque medio en la extensión
					dSecRep.set(rowsDSecRep-1, 13, torque_medio_ext_rep);
					// Desviación muestral del torque medio en la extensión
					dSecRep.set(rowsDSecRep-1, 14, torque_desv_medio_ext_rep);
					// Torque medio en la flexión
					dSecRep.set(rowsDSecRep-1, 15, torque_medio_flex_rep);
					// Desviación muestral del torque medio en la flexión
					dSecRep.set(rowsDSecRep-1, 16, torque_desv_medio_flex_rep);
					
					tot_t_max = tot_t_max + max_t_tor_rep;
					tot_t_min = tot_t_min + min_t_tor_rep;
					
					tot_t_ext = tot_t_ext + t_ext_rep;
					tot_t_flex = tot_t_flex + t_flex_rep;
					
					tot_t_rep = tot_t_rep + t_total_rep;
				}
				
				/** Aquí finaliza el ejercicio */
				
				int final_rep = rowsDSecRep;
				
				// Final de la secuencia de repeticiones de un ejercicio
				dSecEje.set(rowsDSecEje-1, 2, (dSecEje.get(rowsDSecEje-1,1)  + (tot_rep_eje - 1)));
				// Cantidad de repeticiones del ejercicio
				dSecEje.set(rowsDSecEje-1, 6, tot_rep_eje);
				//Secuencia de diferencias de máximos del ejercicio codificada en un solo número
				dSecEje.set(rowsDSecEje-1, 7, sec_dif_max);
				// Secuencia de diferencias de ángulos de máximos del ejercicio codificada en un solo número
				dSecEje.set(rowsDSecEje-1, 8, sec_dif_max_ang);
				// Secuencia de diferencias de mínimos del ejercicio codificada en un solo número
				dSecEje.set(rowsDSecEje-1, 9, sec_dif_min);
				// Secuencia de diferencias de ángulos de mínimos del ejercicio codificada en un solo número
				dSecEje.set(rowsDSecEje-1, 10, sec_dif_min_ang);
				// Tiempo total del ejercicio
				dSecEje.set(rowsDSecEje-1, 11, t_eje);
				// Máximo torque del ejercicio
				dSecEje.set(rowsDSecEje-1, 12, max_torque);
				// Angulo del máximo torque del ejercicio
				dSecEje.set(rowsDSecEje-1, 13, max_torque_ang);
				// Tiempo del máximo torque del ejercicio
				dSecEje.set(rowsDSecEje-1, 14, t_max_eje);
				// Mínimo torque del ejercicio
				dSecEje.set(rowsDSecEje-1, 15, min_torque);
				// Angulo del mínimo torque del ejercicio
				dSecEje.set(rowsDSecEje-1, 16, min_torque_ang);
				// Tiempo del mínimo torque del ejercicio
				dSecEje.set(rowsDSecEje-1, 17, t_min_eje);
				
				
				tot_t_med_max = 0;
				tot_t_med_min = 0;
				tot_t_med_ext = 0;
				tot_t_med_flex = 0;
				tot_t_med_rep = 0;
				
				if(tot_rep_eje != 0){
					
					//Tiempo medio de llegar al máximo en las extensiones de las distintas repeticiones
					tot_t_med_max = tot_t_max/tot_rep_eje;
					
					//Tiempo medio de llegar al mínimo en las flexiones de las distintas repeticiones
					tot_t_med_min = tot_t_min/tot_rep_eje;
					
					//Tiempo medio de las extensiones en un ejercicio
					tot_t_med_ext = tot_t_ext/tot_rep_eje;
					
					//Tiempo medio de las flexiones en un ejercicio
					tot_t_med_flex = tot_t_flex/tot_rep_eje;
					
					//Tiempo medio de las repeticiones en un ejercicio
					tot_t_med_rep = tot_t_rep/tot_rep_eje;
				}
				
				
				tot_torque_med_ext = 0;
				
				if(tot_elem_ext != 0){
					
					//Torque medio en las extensiones de las distintas repeticiones
					tot_torque_med_ext = tot_torque_ext/tot_elem_ext;
				}
				
				
				tot_torque_med_flex = 0;
				
				if(tot_elem_flex != 0){
					
					//Torque medio en las flexiones de las distintas repeticiones
					tot_torque_med_flex = tot_torque_flex/tot_elem_flex;
				}
				
							
				tot_t_desv_max = 0;
				tot_t_desv_min = 0;
				tot_t_desv_ext = 0;
				tot_t_desv_flex = 0;
				tot_t_desv_rep = 0;
				
				for(int j=start_rep; j < final_rep; j++ ){
					
					//Desviación estándar muestral del tiempo medio en llegar al máximo de las distintas extensiones
					tot_t_desv_max = tot_t_desv_max + Math.pow((dSecRep.get(j, 11)-tot_t_med_max),2);
					
					//Desviación estándar muestral del tiempo medio en llegar al mínimo de las distintas flexiones
					tot_t_desv_min = tot_t_desv_min + Math.pow((dSecRep.get(j, 12)-tot_t_med_min),2);
					
					//Desviación estandar muestral del tiempo de las extensiones en un ejercicio
					tot_t_desv_ext = tot_t_desv_ext + Math.pow((dSecRep.get(j, 5)-tot_t_med_ext),2);
					
					//Desviación estandar muestral del tiempo de las flexiones en un ejercicio
					tot_t_desv_flex = tot_t_desv_flex + Math.pow((dSecRep.get(j, 6)-tot_t_med_flex),2);
					
					//Desviacion estandar muestral del tiempo de las repeticiones en un ejercicio
					tot_t_desv_rep = tot_t_desv_rep + Math.pow((dSecRep.get(j, 4)-tot_t_med_rep),2);
				}
				
				
				tot_torque_desv_ext = 0;
				
                //Desviación estándar muestral del torque medio de las distintas extensiones
				for(int j=0; j < tot_elem_ext; j++){
					tot_torque_desv_ext = tot_torque_desv_ext + Math.pow((auxExt.get(j, 0) - tot_torque_med_ext),2);
				}
				
				
				tot_torque_desv_flex = 0;
				
				//Desviación estándar muestral del torque medio de las distintas flexiones
				for(int j=0; j < tot_elem_flex; j++){
					tot_torque_desv_flex = tot_torque_desv_flex + Math.pow((auxFlex.get(j, 0) - tot_torque_med_flex ),2);
				}
				
				
				if((tot_rep_eje-1) != 0){
					
					//Desviación estándar muestral del tiempo medio en llegar al máximo de las distintas extensiones
					tot_t_desv_max = tot_t_desv_max/(tot_rep_eje-1);
					
					//Desviación estándar muestral del tiempo medio en llegar al mínimo de las distintas flexiones
					tot_t_desv_min = tot_t_desv_min/(tot_rep_eje-1);
					
					//Desviación estandar muestral del tiempo de las extensiones en un ejercicio
					tot_t_desv_ext = tot_t_desv_ext/(tot_rep_eje-1);
					
					//Desviación estandar muestral del tiempo de las flexiones en un ejercicio
					tot_t_desv_flex = tot_t_desv_flex/(tot_rep_eje-1);
					
					//Desviacion estandar muestral del tiempo de las repeticiones en un ejercicio
					tot_t_desv_rep = tot_t_desv_rep/(tot_rep_eje-1);
				}
				
				
				if((tot_elem_ext-1) != 0){
					
					//Desviación estándar muestral del torque medio de las distintas extensiones
					tot_torque_desv_ext = tot_torque_desv_ext/(tot_elem_ext-1);
				}
				
				
				if((tot_elem_flex-1) != 0){
					
					//Desviación estándar muestral del torque medio de las distintas flexiones
					tot_torque_desv_flex = tot_torque_desv_flex/(tot_elem_flex-1);
				}
				
				
				// Tiempo medio de las repeticiones en llegar al máximo
				dSecEje.set(rowsDSecEje-1, 18, tot_t_med_max);
				// Desviación estándar muestral del tiempo medio de las repeticiones en llegar al máximo
				dSecEje.set(rowsDSecEje-1, 19, tot_t_desv_max);
				// Tiempo medio de las repeticiones en llegar al mínimo
				dSecEje.set(rowsDSecEje-1, 20, tot_t_med_min);
				// Desviación estándar muestral del tiempo medio de las repeticiones en llegar al mínimo
				dSecEje.set(rowsDSecEje-1, 21, tot_t_desv_min);
				// Torque medio en las extensiones de las distintas repeticiones
				dSecEje.set(rowsDSecEje-1, 22, tot_torque_med_ext);
				// Desviación estándar muestral del torque medio de las distintas extensiones
				dSecEje.set(rowsDSecEje-1, 23, tot_torque_desv_ext);
				// Torque medio en las flexiones de las distintas repeticiones
				dSecEje.set(rowsDSecEje-1, 24, tot_torque_med_flex);
				// Desviación estándar muestral del torque medio de las distintas flexiones
				dSecEje.set(rowsDSecEje-1, 25, tot_torque_desv_flex);
				//Tiempo medio de las extensiones en un ejercicio
				dSecEje.set(rowsDSecEje-1, 26, tot_t_med_ext);
				//Desviación estandar muestral del tiempo de las extensiones en un ejercicio
				dSecEje.set(rowsDSecEje-1, 27, tot_t_desv_ext);
				//Tiempo medio de las flexiones en un ejercicio
				dSecEje.set(rowsDSecEje-1, 28, tot_t_med_flex);
				//Desviación estandar muestral del tiempo de las flexiones en un ejercicio
				dSecEje.set(rowsDSecEje-1, 29, tot_t_desv_flex);
				//Tiempo medio de las repeticiones en un ejercicio
				dSecEje.set(rowsDSecEje-1, 30, tot_t_med_rep);
				//Desviacion estandar muestral del tiempo de las repeticiones en un ejercicio
				dSecEje.set(rowsDSecEje-1, 31, tot_t_desv_rep);
			}
			
			
			GenericFile gen2 = new GenericFile();
			
					
			//Genera a partir del dSecEje un SEC file con las 18 features para el algoritmo genético
			DoubleMatrix2D featureVec18 = new DenseDoubleMatrix2D(rowsDSecEje, 18);
			int amountRows = 0;
			while( amountRows < rowsDSecEje){
				featureVec18.set(amountRows, 0, dSecEje.get(amountRows, 7));
				featureVec18.set(amountRows, 1, dSecEje.get(amountRows, 8));
				featureVec18.set(amountRows, 2, dSecEje.get(amountRows, 9));
				featureVec18.set(amountRows, 3, dSecEje.get(amountRows, 10));
				featureVec18.set(amountRows, 4, dSecEje.get(amountRows, 12));
				featureVec18.set(amountRows, 5, dSecEje.get(amountRows, 13));
				featureVec18.set(amountRows, 6, dSecEje.get(amountRows, 14));
				featureVec18.set(amountRows, 7, dSecEje.get(amountRows, 15));
				featureVec18.set(amountRows, 8, dSecEje.get(amountRows, 16));
				featureVec18.set(amountRows, 9, dSecEje.get(amountRows, 17));
				featureVec18.set(amountRows, 10, dSecEje.get(amountRows, 18));
				featureVec18.set(amountRows, 11, dSecEje.get(amountRows, 20));
				featureVec18.set(amountRows, 12, dSecEje.get(amountRows, 22));
				featureVec18.set(amountRows, 13, dSecEje.get(amountRows, 24));
				featureVec18.set(amountRows, 14, dSecEje.get(amountRows, 19));
				featureVec18.set(amountRows, 15, dSecEje.get(amountRows, 21));
				featureVec18.set(amountRows, 16, dSecEje.get(amountRows, 23));
				featureVec18.set(amountRows, 17, dSecEje.get(amountRows, 25));
				amountRows++;
			}
			gen2.setColumns(18);
			gen2.setRows(rowsDSecEje);
			gen2.setData(featureVec18);
			gen2.save(fileSecfeatureVec18);
			
			// Genera a partir del dSecEje un SEC file con las 26 features para el algoritmo genético
			DoubleMatrix2D featureVec26 = new DenseDoubleMatrix2D(rowsDSecEje, 26);
			amountRows = 0;
			while( amountRows < rowsDSecEje){
				featureVec26.set(amountRows, 0, dSecEje.get(amountRows, 6));
				featureVec26.set(amountRows, 1, dSecEje.get(amountRows, 7));
				featureVec26.set(amountRows, 2, dSecEje.get(amountRows, 8));
				featureVec26.set(amountRows, 3, dSecEje.get(amountRows, 9));
				featureVec26.set(amountRows, 4, dSecEje.get(amountRows, 10));
				featureVec26.set(amountRows, 5, dSecEje.get(amountRows, 11));
				featureVec26.set(amountRows, 6, dSecEje.get(amountRows, 12));
				featureVec26.set(amountRows, 7, dSecEje.get(amountRows, 13));
				featureVec26.set(amountRows, 8, dSecEje.get(amountRows, 14));
				featureVec26.set(amountRows, 9, dSecEje.get(amountRows, 15));
				featureVec26.set(amountRows, 10, dSecEje.get(amountRows, 16));
				featureVec26.set(amountRows, 11, dSecEje.get(amountRows, 17));
				featureVec26.set(amountRows, 12, dSecEje.get(amountRows, 18));
				featureVec26.set(amountRows, 13, dSecEje.get(amountRows, 19));
				featureVec26.set(amountRows, 14, dSecEje.get(amountRows, 20));
				featureVec26.set(amountRows, 15, dSecEje.get(amountRows, 21));
				featureVec26.set(amountRows, 16, dSecEje.get(amountRows, 22));
				featureVec26.set(amountRows, 17, dSecEje.get(amountRows, 23));
				featureVec26.set(amountRows, 18, dSecEje.get(amountRows, 24));
				featureVec26.set(amountRows, 19, dSecEje.get(amountRows, 25));
				featureVec26.set(amountRows, 20, dSecEje.get(amountRows, 26));
				featureVec26.set(amountRows, 21, dSecEje.get(amountRows, 27));
				featureVec26.set(amountRows, 22, dSecEje.get(amountRows, 28));
				featureVec26.set(amountRows, 23, dSecEje.get(amountRows, 29));
				featureVec26.set(amountRows, 24, dSecEje.get(amountRows, 30));
				featureVec26.set(amountRows, 25, dSecEje.get(amountRows, 31));
				amountRows++;
			}
			gen2.setColumns(26);
			gen2.setRows(rowsDSecEje);
			gen2.setData(featureVec26);
			gen2.save(fileSecfeatureVec26);
			
			
		}
		catch (IOException e)
        {
    		System.out.println("error processing the file: " + e.getMessage());

        }
	}
	
	
	static public void FormatBioFile (String fileInfo, String fileInfo2, int col){
		
		GenericFile gen;
		gen = new GenericFile();
				
		try{
			
			gen.loadWithoutHeader(fileInfo,col);
			DoubleMatrix2D d = gen.getData();
			
			System.out.println("");
			for(int i =0; i < d.rows(); i++){
				
				for(int j=0; j < d.columns(); j++){
					System.out.print(d.get(i, j));
					System.out.print(" ");
				}
				System.out.println("");
				
			}
			
			gen.save(fileInfo2);
			
		}
		catch (IOException e)
        {
    		System.out.println("error processing the file: " + e.getMessage());

        }
		
		
	}
	
	
	/**
	 * 
	 * 
	 * @param fileInfo
	 * 				   The name and path of the file with the features of the training items.
	 * 
	 * @param col
	 * 			  The amount of colums in the file with the features.
	 * 
	 * @param names
	 * 				A list with the name of each feature.
	 * 
	 * @return
	 * 			The mapping from the name of each feature to his average real value in the training items.
	 * 
	 */
	static public Map<String, Double> calculateAverageFeatures (String fileInfo, int col, 
			                                                    Collection<String> names){
		
		GenericFile gen;
		DoubleMatrix1D res;
		Map<String, Double> result = null;
		double avg;
		int pos;
		String s;
		Iterator<String> it;
		
		if (names.size() == (col-1)){
		
			gen = new GenericFile();
			
			/** The decimal sepatator is the character "." */
			gen.setLocale(Locale.US);
			
			res = new DenseDoubleMatrix1D (col-1);
			result = new ConcurrentHashMap<String, Double>();
				
			try{
			
				gen.load(fileInfo);
			
				DoubleMatrix2D d = gen.getData();
				
				/** The last column has the label: 0 = NORMAL, 1 = INJURY. */
				for(int i =0; i < (d.columns()-1); i++){
					avg = 0;
					for(int j=0; j < d.rows(); j++){
						avg = avg + d.get(j, i);
					}
					avg = avg/d.rows();
					res.set(i, avg);	
				}
			}
			catch (IOException e)
			{
				System.out.println("error processing the file: " + e.getMessage());

			}
			
			pos = 0;
			it = names.iterator();
			while (it.hasNext()){
				s = it.next();
				result.put(s, new Double(res.get(pos)));
				pos++;
			}
		}
		
		return result;
	}
	


}
