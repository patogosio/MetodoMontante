import java.util.Scanner;
import java.util.Arrays;

/**
 * Ésta clase es una aplicación donde se piden datos de un arreglo y a dicho
 * arreglo se le hace el método de Montante
 * */
public class MetodoMontante {

	/**
	 * Montante es una clase privada que contiene la implementación del método
	 * de resolución de sistemas de ecuaciones de Montante. Su única función
	 * pública es metodo, la cual efectúa el método en el arreglo y despliega
	 * el arreglo resultante en pantalla.
	 * */
	private static class Montante {
		private static float sistema[][];

		/**
		 * Imprime en pantalla el resultado de la ejecución del método de
		 * Montante en el arreglo que se indicó.
		 *
		 * @param arr el arreglo al cual se le quiere aplicar el método de
		 *            Montante.
		 */
		public static void metodo(float[][] arr) {
			sistema = arr;
			float pivAnt = 1;

			for (int i = 0; i < sistema.length; i++) {
				diagonalDeterminante(i);
				primerCuadrante(i, pivAnt);
				cuartoCuadrante(i, pivAnt);
				columnaACeros(i);
				pivAnt = sistema[i][i];
			}

			// despliega la matriz
			//for (int i = 0; i < sistema.length; i++)
			//	System.out.println(Arrays.toString(sistema[i]));
			encuentraSolucion();
		}

		/**
		 * Convierte los números que se encuentran antes del índice, dentro de
		 * la diagonal determinante, en el determinante.
		 *
		 * @param indice es la posición donde se encuentra el determinante en
		 *               el momento en que se llama a ésta función
		 */
		private static void diagonalDeterminante(int indice) {
			for (int i = 0; i < indice; i++)
				sistema[i][i] = sistema[indice][indice];
		}

		/**
		 * Efectúa las operaciones establecidas por el método de Montante en el
		 * primer cuadrante de la matriz.
		 *
		 * @param indice es la posición donde se encuentra el determinante en
		 *               el momento en que se llama a ésta función
		 * @param pivAnt es el pivote anterior.
		 */
		private static void primerCuadrante(int indice, float pivAnt) {
			for (int i = 0; i < indice; i++) {
				for (int j = indice + 1; j < sistema[0].length; j++) {
					sistema[i][j] = (((sistema[i][indice] * sistema[indice][j]) - 
						(sistema[indice][indice] * sistema[i][j])) * -1) / pivAnt;
				}
			}
		}

		/**
		 * Efectúa las operaciones establecidas por el método de Montante en el
		 * cuarto cuadrante de la matriz.
		 *
		 * @param indice es la posición donde se encuentra el determinante en
		 *               el momento en que se llama a ésta función
		 * @param pivAnt es el pivote anterior.
		 */
		private static void cuartoCuadrante(int indice, float pivAnt) {
			for (int i = indice + 1; i < sistema.length; i++) {
				for (int j = indice + 1; j < sistema[0].length; j++) {
					sistema[i][j] = ((sistema[indice][indice] * sistema[i][j]) - 
						(sistema[i][indice] * sistema[indice][j])) / pivAnt;
				}
			}
		}

		/**
		 * Convierte los números que se encuentran en la columna indicada en
		 * ceros.
		 *
		 * @param indice es la posición donde se encuentra el determinante en
		 *               el momento en que se llama a ésta función
		 */
		private static void columnaACeros(int indice) {
			float determinante = sistema[indice][indice];

			for (int i = 0; i < sistema.length; i++) 
				sistema[i][indice] = 0;

			sistema[indice][indice] = determinante;
		}

		/**
		 * Analiza la matriz resultante y busca el resultado del método.
		 * Despliega si el sistema de ecuaciones tiene solución, un número
		 * infinito de soluciones, o no tiene solución.
		 */
		private static void encuentraSolucion() {
			boolean infinito = false;
			boolean sinSolucion = false;
			float as[] = new float [sistema.length];

			for (int i = 0; i < sistema.length; i++) {
				if( sistema[i][i] == 0 ) {
					if (sistema[i][sistema[0].length - 1] == 0) {
						infinito = true;
					} else {
						sinSolucion = true;
					}
				} else {
					as[i] = sistema[i][sistema[0].length - 1] / sistema[i][i];
				}
			}

			if( sinSolucion == true) {
				System.out.println("* El sistema no tiene solución");
			} else if ( infinito == true) {
				System.out.println("* El sistema tiene un número infinito de soluciones");
			} else {
				for (int i = 0; i < as.length; i++) 
				System.out.println("a" + (i+1) + " = " + as[i]);
			}
		}
	}

	public static void main (String [] args) {
		Scanner input = new Scanner(System.in);
		int numCol = 0;
		int numRen = 0;
		float [][] arr;

		System.out.println("* ¿Cuantas ecuaciones?");
		numRen = input.nextInt();

		System.out.println("* ¿Cuantas incógnitas?");
		numCol = input.nextInt() + 1;

		if (numRen != (numCol - 1)) {
			System.out.println("* El número de incógnitas debe ser igual al " + 
					"número de ecuaciones");
			return;
		}

		arr = new float [numRen][numCol];

		System.out.println("* Inserte las ecuaciones:");

		for (int i = 0; i < numRen; i++) {
			int j;
			System.out.println("* inserte ecuación " + (i + 1) + ":");
			for (j = 0; j < numCol; j++) 
				arr[i][j] = input.nextFloat();
			// Despliega la ecuación
			System.out.print("* ");
			for (j = 0; j < numCol - 2; j++) 
				System.out.print(arr[i][j] + " X" + (j+1) + " + ");
			System.out.print(arr[i][j] + " X" + (j+1) + " = " + arr[i][j+1] + "\n");
		}

		System.out.println("* Gracias:");

		// Se ejecuta el método de montante
		Montante.metodo(arr);
	}
}
