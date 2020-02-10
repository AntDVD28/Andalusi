package com.example.andalusi;


import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class third_activity extends AppCompatActivity {

    private Toolbar toolbar;

    private Button btEnviar, btVolver;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.third_activity);

        toolbar = findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);


        //Rescato la información enviada y la guardo en las variables globales
        Bundle datos = getIntent().getExtras();

        final int edad = datos.getInt("edad");
        final String genero = datos.getString("genero");
        final String provincia = datos.getString("provincia");
        final int array_respuestas[] = datos.getIntArray("array_respuestas");


        TextView tvRedad = (TextView) findViewById(R.id.tvRedad);
        tvRedad.setText("" + edad);

        TextView tvRgenero = (TextView) findViewById(R.id.tvRgenero);
        tvRgenero.setText(genero);
        TextView tvRprovincia = (TextView) findViewById(R.id.tvRprovincia);
        tvRprovincia.setText(provincia);

        TextView tvR1 = (TextView) findViewById(R.id.tvR1);
        tvR1.setText(muestraLetra(array_respuestas[0]));
        TextView tvR2 = (TextView) findViewById(R.id.tvR2);
        tvR2.setText(muestraLetra(array_respuestas[1]));
        TextView tvR3 = (TextView) findViewById(R.id.tvR3);
        tvR3.setText(muestraLetra(array_respuestas[2]));
        TextView tvR4 = (TextView) findViewById(R.id.tvR4);
        tvR4.setText(muestraLetraCheckBox(array_respuestas[3]));
        TextView tvR5 = (TextView) findViewById(R.id.tvR5);
        tvR5.setText(muestraLetra(array_respuestas[4]));
        TextView tvR6 = (TextView) findViewById(R.id.tvR6);
        tvR6.setText(muestraLetra(array_respuestas[5]));
        TextView tvR7 = (TextView) findViewById(R.id.tvR7);
        tvR7.setText(muestraLetra(array_respuestas[6]));
        TextView tvR8 = (TextView) findViewById(R.id.tvR8);
        tvR8.setText(muestraLetra(array_respuestas[7]));


        //****************************************************************************************//
        //                                  B U T T O N S                                         //
        //****************************************************************************************//
        btVolver = (Button) findViewById(R.id.btVolver);
        btVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(i);
            }
        });

        btEnviar = (Button) findViewById(R.id.btEnviar);
        btEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String provincia_sin = eliminarAcentos(provincia);
                //http://andared.com/iesaguadulce/pmdm/tarea2.php?edad=23&genero=mujer&provincia=almeria&test1=4&test2=1&test3=1&test4=2&test5=3&test6=3&test7=1&test8=4
                Uri uri = Uri.parse("http://andared.com/iesaguadulce/pmdm/tarea2.php?edad="+edad+"&genero="+genero+"&provincia="+provincia_sin+"&test1="+array_respuestas[0]+"&test2="+array_respuestas[1]+"&test3="+array_respuestas[2]+"&test4="+array_respuestas[3]+"&test5="+array_respuestas[4]+"&test6="+array_respuestas[5]+"&test7="+array_respuestas[6]+"&test8="+array_respuestas[7]);
                Intent i = new Intent(Intent.ACTION_VIEW, uri);
                try {
                    startActivity(i);
                    Toast.makeText(getApplicationContext(),R.string.datosEnviados, Toast.LENGTH_LONG).show();

                }catch(ActivityNotFoundException anfe){

                    Toast.makeText(getApplicationContext(),R.string.datosNoEnviados, Toast.LENGTH_LONG).show();
                }


            }
        });

    }

    //Método auxiliar para eliminar los acentos antes de efectuar el envio por http
    public static String eliminarAcentos(String str) {

        final String ORIGINAL = "ÁáÉéÍíÓóÚúÑñÜü";
        final String REEMPLAZO = "AaEeIiOoUuNnUu";

        if (str == null) {
            return null;
        }
        char[] array = str.toCharArray();
        for (int indice = 0; indice < array.length; indice++) {
            int pos = ORIGINAL.indexOf(array[indice]);
            if (pos > -1) {
                array[indice] = REEMPLAZO.charAt(pos);
            }
        }
        return new String(array);
    }


    //Método auxiliar para mostrar al usuario las respuestas a, b, c, d en lugar de 1, 2, 3 ,4 (Radiobuttons)
    public String muestraLetra(int i) {

        String letra;

        if (i == 1) {
            letra = "a";
        } else if (i == 2) {
            letra = "b";
        } else if (i == 3) {
            letra = "c";
        } else {
            letra = "d";
        }

        return letra;
    }
    //Método auxiliar para mostrar al usuario las respuestas a, b, c, d en lugar de 1, 2, 3, 4 (Checkbox)
    //Tenemos 10 posibles soluciones
    public String muestraLetraCheckBox(int i){
        String letra="";

        //Obtengo el número de digitos del número pasado como argumento, esta información la necesito para saber cuantas veces deberá iterar nuestro bucle
        int num_digitos = Integer.toString(i).length();

        //Necesito operar sobre el número invertido para que posteriormente mostrar las letras ordenadas
        int numero = invertirNumero(i);

        int num;
        for(int j=0; j<=num_digitos; j++){
            num = numero % 10;
            numero = numero / 10;
            if(num == 1){
                letra = letra + "a ";
            }else if(num == 2){
                letra = letra + "b ";
            }else if(num == 3){
                letra = letra + "c ";
            }else if(num == 4){
                letra = letra + "d ";
            }

        }
        return letra;
    }

    //Función auxiliar para invertir un número
    public static int invertirNumero(int n) {

        int num = 0;
        while(n > 0) {
            num = num*10 + n%10;
            n /= 10;
        }
        return num;


    }


    //*******************************************************************************************//
    //                              M E N U   ( T O O L B A R )                                  //
    //*******************************************************************************************//
    @Override public boolean onCreateOptionsMenu(Menu mimenu){

        getMenuInflater().inflate(R.menu.menu, mimenu);

        return true;

    }

    @Override public boolean onOptionsItemSelected(MenuItem opcion_menu){


        int id = opcion_menu.getItemId();

        if( id==R.id.ayuda ){

            Intent i = new Intent(getApplicationContext(), ayuda.class);

            startActivity(i);

            return true;
        }
        if( id==R.id.info ){

            Intent i = new Intent(getApplicationContext(), info.class);

            startActivity(i);

            return true;
        }
        if( id==R.id.salir ){

            /*Finaliza la actividad actual así como todas las actividades que partan de ella.
              A diferencia de finish que sólo cerraría la actividad actual*/
            finishAffinity();
        }
        return super.onOptionsItemSelected(opcion_menu);
    }

}//Fin de la clase
