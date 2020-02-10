package com.example.andalusi;


import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class primary_activity extends AppCompatActivity {

    private Toolbar toolbar;
    private Spinner spGenero;
    private Spinner spProvincia;
    private EditText etEdad;
    private Button btContinuar;

    //Variables Globales donde guardaremos la información
    private String opcion_genero;
    private String opcion_provincia;
    private int edad;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.primary_activity);

        spGenero = (Spinner) findViewById(R.id.spGenero);
        spProvincia = (Spinner) findViewById(R.id.spProvincia);
        etEdad = (EditText) findViewById(R.id.etEdad);
        btContinuar = (Button) findViewById(R.id.btContinuar);

        toolbar = findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);

        //****************************************************************************************//
        //                                    S P I N N E R S                                     //
        //****************************************************************************************//

        //Creamos el adaptador, el contenido del mismo lo cargamos de un recurso
        ArrayAdapter<CharSequence> adaptador1 = ArrayAdapter.createFromResource(this,R.array.combo_generos,android.R.layout.simple_spinner_item);

        //Asociamos el adaptador a la vista
        spGenero.setAdapter(adaptador1);

        spGenero.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                opcion_genero = parent.getItemAtPosition(position).toString();

            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });


        //Creamos el adaptador, el contenido del mismo lo cargamos de un recurso
        ArrayAdapter<CharSequence> adaptador2 = ArrayAdapter.createFromResource(this,R.array.combo_provincias,android.R.layout.simple_spinner_item);

        //Asociamos el adaptador a la vista
        spProvincia.setAdapter(adaptador2);

        spProvincia.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                opcion_provincia = parent.getItemAtPosition(position).toString();

            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        //****************************************************************************************//
        //                            B U T T O N   C O N T I N U A R                             //
        //****************************************************************************************//

        btContinuar = (Button) findViewById(R.id.btContinuar);

        btContinuar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Validación de la edad
                String cadena_edad = etEdad.getText().toString();

                if (cadena_edad.length() == 0){

                    Toast.makeText(getApplicationContext(), R.string.no_edad, Toast.LENGTH_LONG).show();
                    return;

                }else{

                    edad = Integer.parseInt(cadena_edad);

                    if(edad < 8) {

                        Toast.makeText(getApplicationContext(), R.string.muy_joven, Toast.LENGTH_LONG).show();

                        return;

                    }else if (edad > 146) {

                        Toast.makeText(getApplicationContext(), R.string.muy_viejo, Toast.LENGTH_LONG).show();

                        return;
                    }
                }
                //Validación del género
                if(opcion_genero.equals("- Seleccionar -")) {

                    Toast.makeText(getApplicationContext(), R.string.no_genero, Toast.LENGTH_LONG).show();

                    return;

                }

                //Validación de la provincia
                if(opcion_provincia.equals("- Seleccionar -")){

                    Toast.makeText(getApplicationContext(), R.string.no_provincia, Toast.LENGTH_LONG).show();

                    return;

                }
                //Llegado a este punto habremos validado todos los campos
                Intent i = new Intent(getApplicationContext(), second_activity.class);

                i.putExtra("edad", edad);
                i.putExtra("genero", opcion_genero);
                i.putExtra("provincia", opcion_provincia);

                startActivity(i);

            }
        });

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





}
