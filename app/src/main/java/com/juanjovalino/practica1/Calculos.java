package com.juanjovalino.practica1;

/**
 * En esta clase implementaremos los cálculos necesarios para hallar los primos
 */

public class Calculos {

    //Método que devuelve un array de boolean en el cual están a true
    //los índices de los números primos hallados con la criba de Eratóstenes
    //hasta el limite que se le pasa como parámetro
    boolean[] eratostenes(int ls){
        boolean[] primostenes= new boolean[ls+1];
        for (int i=2; i<ls; i++) primostenes[i]=true;
        for(int factor=2; factor*factor <=ls; factor++) {
            if (primostenes[factor]) {
                for (int j = factor; j * factor <= ls; j++) primostenes[factor * j] = false;
            }
        }
        return primostenes;
    }

    //Devuelve un array de enteros con los primos que le pasamos despues de realizar la criba
    int[] grabaPrimos (boolean[] vPrimos){
        int cont=0; //cuenta el numero de primos para dar tamaño al array auxiliar
        int c=0; //variable de control que indica el indice a escribir en el array auxiliar
        for(int i=0; i< vPrimos.length;i++){
            if(vPrimos[i]==true)
                cont++;
        }
        int[]primos = new int[cont];

        for(int i=0; i<vPrimos.length;i++){
            if(vPrimos[i]==true) {
                primos[c]=i;
                c++;
            }
        }
        return primos;
    }
    //Metodo para comprobar si en el array está nuestra posición buscada
    boolean compruebaPrimos (boolean[] vPrimos, int n){
        int cont=0;
        for(int i=0;i<vPrimos.length;i++){
            if(vPrimos[i] == true)
                cont++;
        }
        if(cont<n) //Si el contador de primos es menos que la posicion devolvemos false
            return false;
        else return true;
    }

}
