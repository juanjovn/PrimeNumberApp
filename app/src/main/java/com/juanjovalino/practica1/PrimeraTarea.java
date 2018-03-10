package com.juanjovalino.practica1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class PrimeraTarea extends AppCompatActivity implements View.OnClickListener {
    final int MAXIMO =999999; //máxima tamaño array permitido
    final int tope = 15485863; //mayor número primo para no pasarnos y hacer cálculos innecesarios
    int salto=10000; //incremento en la criba de Eratóstenes
    int[] primos = new int[MAXIMO]; //array para almacenar los primos que vamos calculando y
                                    //aprovecharlos en futuros cálculos
    int pos=0; //posición que introduce el usuario
    int maxAnterior=2; //almacenamos el ultimo primo más alto para copiar los nuevos a partir de él
    Calculos cal = new Calculos(); //instanciamos un objeto de la clase donde tenemos almacenados los cálculos

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        primos [0] = 2;
        primos [1] = 3;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_primera_tarea);
        Button boton = (Button) findViewById(R.id.button);
        boton.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        EditText edNum = (EditText) findViewById(R.id.editText);
        TextView resultado = (TextView) findViewById(R.id.tResultado);
        boolean flag=false;

        if(edNum.getText().length()==0)
            resultado.setText("NO HAS INTRODUCIDO NADA!");
        else if (Integer.parseInt(edNum.getText().toString()) <= 0)
            resultado.setText("INTRODUCE UNA POSICIÓN VÁLIDA!");
        else if (Integer.parseInt(edNum.getText().toString()) <= maxAnterior) {
            //Si entra aquí el número ya lo teníamos calculado, simplemente hay que acceder a la posicion del array indicada
            pos = Integer.parseInt(edNum.getText().toString());
            resultado.setText("El primo número "+pos+" es el "+Integer.toString(primos[pos-1]));
        }
        else {
            pos = Integer.parseInt(edNum.getText().toString());
            //Repetimos la criba incrementando el salto hasta que encontramos la posicion y guardamos los numeros nuevos en el array primos
            do {
                if (cal.compruebaPrimos(cal.eratostenes(salto), pos)) {
                    int[] aux = cal.grabaPrimos(cal.eratostenes(salto));
                    for(int i=maxAnterior; i<aux.length; i++){
                        primos[i]=aux[i];
                    }
                    flag=true; //para salir del bucle cuando encontramos primo en posicion

                } else if ((tope-salto)<salto) //Controla el salto para no pasarnos del tope
                    salto = tope;
                        else salto=salto+salto;
            }while(!flag);
            resultado.setText("El primo número "+pos+" es el "+Integer.toString(primos[pos-1]));
            maxAnterior=pos;

        }



    }
}
