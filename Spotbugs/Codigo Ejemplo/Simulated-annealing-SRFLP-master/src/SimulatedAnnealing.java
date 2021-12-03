import java.util.ArrayList;
import java.util.Random;

public class SimulatedAnnealing {



    private int[] generarSolucionRandom(int n){
        int[] randomArray = new int[n];
        /** genera un array ordenado de 0 a n y luego retorna un array aleatorio con esos elementos  */
        ArrayList arrayOrdenado = new ArrayList();
        for(int i = 0;i < n; i++){
            arrayOrdenado.add(i);
        }
        int contador = 0;

        while(arrayOrdenado.size()>0) {
            int size = arrayOrdenado.size();
            int randomNumber = getRandomNumberInRange(0, size-1);
            randomArray[contador] = (int)arrayOrdenado.get(randomNumber);
            arrayOrdenado.remove(randomNumber);
            contador++;
        }
        return randomArray;
    }


    private static int getRandomNumberInRange(int min, int max) {
        /**genera un numero aleatorio entre los valores min y max*/
        if(min == max)
            return min;
        if (min >= max) {
            throw new IllegalArgumentException("max must be greater than min");
        }

        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }

    private int[] swap(int[] array){
        int random1=0,random2=0;
        int[] swapedArray = new int[array.length];

        /** hacemos solo 1 swap de los elementos del array*/
        while(random1 == random2){
            random1 = getRandomNumberInRange(0,array.length-1);
            random2 = getRandomNumberInRange(0,array.length-1);
        }

        for(int i = 0 ; i < array.length; i++ ){
            if(i != random1 && i!= random2)
                swapedArray[i] = array[i];
        }
        swapedArray[random1] = array[random2];
        swapedArray[random2] = array[random1];

        return swapedArray;
    }

    public int[] search(SRFLP srflp,int size,double temperatura, double temperaturaMin,double enfriamento,int[]solucionInicial){
        int iteracion = 1;
        int[] solucionActual;

        if(solucionInicial == null) {
            solucionActual = generarSolucionRandom(size);
        }else{
            solucionActual = solucionInicial;
        }

        double distanciaRecorrida1,distanciaRecorrida2;
        int[] swapedSolution = swap(solucionActual);

        distanciaRecorrida1 = srflp.getObjectiveFunction(solucionActual);

        /** Solucion inicial*/
        System.out.println("Solucion inicial");
        System.out.println();
        imprimirArray(solucionActual,distanciaRecorrida1);


        while (temperatura > temperaturaMin) {
            /** Criterio de metropolis */
            System.out.println("Iteracion "+iteracion);
            distanciaRecorrida2 = srflp.getObjectiveFunction(swapedSolution);
            imprimirArray(swapedSolution,distanciaRecorrida2);
            if (distanciaRecorrida1 < distanciaRecorrida2) {
                if (criterioMetropolis(distanciaRecorrida1, distanciaRecorrida2, temperatura)) {
                    solucionActual = swapedSolution;
                    distanciaRecorrida1 = distanciaRecorrida2;
                }
            } else {
                solucionActual = swapedSolution;
                distanciaRecorrida1 = distanciaRecorrida2;
            }
            temperatura = temperatura * enfriamento;
            iteracion++;
            swapedSolution = swap(solucionActual);
            System.out.println("---------");
        }

        System.out.println("Solucion final ");
        double distanciaFinal = srflp.getObjectiveFunction(solucionActual);
        imprimirArray(solucionActual,distanciaFinal);
        return solucionActual;
    }

    private boolean criterioMetropolis(double d1,double d2,double temperatura){
        double diferencia = d2 - d1;
        double probabilidad = Math.exp(-1*(diferencia/temperatura));
        System.out.println("Criterio metropolis: ");
        System.out.println("Probabilidad "+ probabilidad);
        double random = Math.random();
        System.out.println("Random " + random);
        if(probabilidad > random ) {
            System.out.println("Se acepta");
            return true;
        }
        else {
            System.out.println("No se acepta");
            return false;
        }
    }

    private void imprimirArray(int[] array,double distancia){
        for (int i = 0 ; i < array.length ; i++){
            System.out.print(array[i] + " ");
        }
        System.out.println();
        System.out.println("Distancia recorrida "+distancia);
    }


}
