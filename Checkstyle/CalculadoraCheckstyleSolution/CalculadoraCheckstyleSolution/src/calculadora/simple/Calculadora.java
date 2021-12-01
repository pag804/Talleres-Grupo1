package calculadora.simple;

import java.util.Scanner;

/**
 *  Clase Calculadora.
 */
public class Calculadora {

  /**
   * Main de la clase Calculadora.
   * 
   *
   * @param args argumentos de entrada.
   */
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    
    double c = 0.0;
    
    System.out.print("Introduce el primer n�mero \n");
    double a = in.nextDouble();
    System.out.print("Introduce el segundo n�mero  \n");
    double b = in.nextDouble();
    System.out.print("Elige la operaci�n  " 
        + "\n1.Suma" 
        + "\n2.Resta" 
        + "\n3.Multiplicaci�n" 
        + "\n4.Divisi�n"
        + "\n5.Potencia"
        + "\n#Por favor, introduce el n�mero de la operaci�n a realizar \n");
    double somethin = in.nextDouble();
    double addition = 1;
    double subtraction = 2;
    double multiplication = 3;
    double division = 4;
    if (somethin == addition) {
      c = a + b;  
      System.out.println(a + " + " + b + " = " + c);  
    } else if (somethin == subtraction) {
      c = a - b;  
      System.out.println(a + " - " + b + " = " + c);
    } else if (somethin == multiplication) {
      c = a * b;
      System.out.println(a + " * " + b + " = " + c);
    } else if (somethin == division) {
      c = a / b;
      System.out.println(a + " / " + b + " = " + c);
    }
    in.close();
  }

}
