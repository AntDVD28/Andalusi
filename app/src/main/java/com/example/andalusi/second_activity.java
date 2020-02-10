package com.example.andalusi;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.LayoutAnimationController;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;



public class second_activity extends AppCompatActivity {

    private Toolbar toolbar;
    private Button bt1, bt2, bt3, bt4, bt5, bt6, bt7, bt8;
    private RadioGroup rg1, rg2, rg3, rg5, rg6, rg7, rg8;
    private CheckBox cb41, cb42, cb43, cb44;
    private LinearLayout ll1, ll2, ll3, ll4, ll5, ll6, ll7, ll8;
    private ProgressBar pb;


    //Variables globales dónde guardaremps la información
    private int edad;
    private String genero;
    private String provincia;
    private int array_respuestas[] = new int[8];
    private int opcion = 0; //Variable auxiliar que utilizaré para guardar la opción del radiobutton
    private int array_enteros[] = new int[4]; //Array auxiliar que utilizaré para guardar los checkbox marcados

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_activity);

        toolbar = findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);

       //Rescato la información enviada y la guardo en las variables globales
        Bundle datos = getIntent().getExtras();

        edad = datos.getInt("edad");
        genero = datos.getString("genero");
        provincia = datos.getString("provincia");

        //Barra de progreso
        pb = (ProgressBar) findViewById(R.id.pb1);
        pb.setMax(8);
        pb.setProgress(1);

        //Layout
        ll1 = findViewById(R.id.ll1);
        ll2 = findViewById(R.id.ll2);
        ll3 = findViewById(R.id.ll3);
        ll4 = findViewById(R.id.ll4);
        ll5 = findViewById(R.id.ll5);
        ll6 = findViewById(R.id.ll6);
        ll7 = findViewById(R.id.ll7);
        ll8 = findViewById(R.id.ll8);
        ll2.setVisibility(View.INVISIBLE);
        ll3.setVisibility(View.INVISIBLE);
        ll4.setVisibility(View.INVISIBLE);
        ll5.setVisibility(View.INVISIBLE);
        ll6.setVisibility(View.INVISIBLE);
        ll7.setVisibility(View.INVISIBLE);
        ll8.setVisibility(View.INVISIBLE);

        //****************************************************************************************//
        //                                  B U T T O N S                                         //
        //****************************************************************************************//
        bt1 = findViewById(R.id.bt1);
        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(opcion>0){
                    //Guardar la opcion seleccionada en el array y poner la variable opcion a a cero
                    array_respuestas[0] = opcion;
                    opcion = 0;
                    //oculto el primer layout
                    ll1.setVisibility(View.INVISIBLE);
                    //hago una animación pasándole como parámetro el segundo layaout
                    animar(ll2);
                    ll2.setVisibility(View.VISIBLE);
                    //volvemos a renderizar la barra de progreso
                    pb = (ProgressBar) findViewById(R.id.pb2);
                    pb.setMax(8);
                    pb.setProgress(2);
                }else {
                    Toast.makeText(getApplicationContext(),R.string.no_opcion, Toast.LENGTH_LONG).show();
                }
            }
        });
        bt2 = findViewById(R.id.bt2);
        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(opcion>0){
                    //Guardar la opcion seleccionada en el array y poner la variable opcion a a cero
                    array_respuestas[1] = opcion;
                    opcion = 0;
                    //oculto el segundo layout
                    ll2.setVisibility(View.INVISIBLE);
                    //hago una animación pasándole como parámetro el tercer layaout
                    animar(ll3);
                    ll3.setVisibility(View.VISIBLE);
                    //volvemos a renderizar la barra de progreso
                    pb = (ProgressBar) findViewById(R.id.pb3);
                    pb.setMax(8);
                    pb.setProgress(3);
                }else {
                    Toast.makeText(getApplicationContext(),R.string.no_opcion, Toast.LENGTH_LONG).show();
                }
            }
        });
        bt3 = findViewById(R.id.bt3);
        bt3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(opcion>0){
                    //Guardar la opcion seleccionada en el array y poner la variable opcion a a cero
                    array_respuestas[2] = opcion;
                    opcion = 0;
                    //oculto el tercer layout
                    ll3.setVisibility(View.INVISIBLE);
                    //hago una animación pasándole como parámetro el cuarto layaout
                    animar(ll4);
                    ll4.setVisibility(View.VISIBLE);
                    //volvemos a renderizar la barra de progreso
                    pb = (ProgressBar) findViewById(R.id.pb4);
                    pb.setMax(8);
                    pb.setProgress(4);
                }else {
                    Toast.makeText(getApplicationContext(),R.string.no_opcion, Toast.LENGTH_LONG).show();
                }
            }
        });
        bt4 = findViewById(R.id.bt4);
        bt4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!cb41.isChecked() && !cb42.isChecked() && !cb43.isChecked() && !cb44.isChecked()){
                    Toast.makeText(getApplicationContext(),R.string.no_checked, Toast.LENGTH_LONG).show();
                }else {
                    //Debo de recorrer el array de enteros y construir un número para lo cual me ayudo de un String
                    //Al seleccionar el primer checkbox obtendremos en el array el valor 1, en caso contrario el valor 0
                    //Para el segundo, si lo marcamos obtendremos un 2, 0 en caso contrario
                    //Para el tercero, si lo marcamos obtendremos un 3, 0 en caso contrario
                    //Para el cuarto , si lo marcamos obtendremos un 4, 0 en caso contrario
                    //Si por ejemplo marcamos el primer y cuarto checkbox deberemos de obtener el número 14
                    String cadena="";
                    for(int i = 0; i < array_enteros.length; i++) {
                        //Sólo guardamos el valor en el string si es distinto de 0
                        if(array_enteros[i]!=0){
                            cadena += array_enteros[i];
                        }

                    }
                    opcion = Integer.parseInt(cadena);
                    //Guardar las opciones seleccionadas en el array y poner la variable opcion a a cero
                    array_respuestas[3] = opcion;
                    opcion = 0;
                    //oculto el cuarto layout
                    ll4.setVisibility(View.INVISIBLE);
                    //hago una animación pasándole como parámetro el quinto layaout
                    animar(ll5);
                    ll5.setVisibility(View.VISIBLE);
                    //volvemos a renderizar la barra de progreso
                    pb = (ProgressBar) findViewById(R.id.pb5);
                    pb.setMax(8);
                    pb.setProgress(5);
                }
            }
        });
        bt5 = findViewById(R.id.bt5);
        bt5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(opcion>0){
                    //Guardar la opcion seleccionada en el array y poner la variable opcion a a cero
                    array_respuestas[4] = opcion;
                    opcion = 0;
                    //oculto el quinto layout
                    ll5.setVisibility(View.INVISIBLE);
                    //hago una animación pasándole como parámetro el sexto layaout
                    animar(ll6);
                    ll6.setVisibility(View.VISIBLE);
                    //volvemos a renderizar la barra de progreso
                    pb = (ProgressBar) findViewById(R.id.pb6);
                    pb.setMax(8);
                    pb.setProgress(6);
                }else {
                    Toast.makeText(getApplicationContext(),R.string.no_opcion, Toast.LENGTH_LONG).show();
                }
            }
        });
        bt6 = findViewById(R.id.bt6);
        bt6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(opcion>0){
                    //Guardar la opcion seleccionada en el array y poner la variable opcion a a cero
                    array_respuestas[5] = opcion;
                    opcion = 0;
                    //oculto el sexto layout
                    ll6.setVisibility(View.INVISIBLE);
                    //hago una animación pasándole como parámetro el séptimo layaout
                    animar(ll7);
                    ll7.setVisibility(View.VISIBLE);
                    //volvemos a renderizar la barra de progreso
                    pb = (ProgressBar) findViewById(R.id.pb7);
                    pb.setMax(8);
                    pb.setProgress(7);
                }else {
                    Toast.makeText(getApplicationContext(),R.string.no_opcion, Toast.LENGTH_LONG).show();
                }
            }
        });
        bt7 = findViewById(R.id.bt7);
        bt7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(opcion>0){
                    //Guardar la opcion seleccionada en el array y poner la variable opcion a a cero
                    array_respuestas[6] = opcion;
                    opcion = 0;
                    //oculto el séptimo layout
                    ll7.setVisibility(View.INVISIBLE);
                    //hago una animación pasándole como parámetro el octavo layaout
                    animar(ll8);
                    ll8.setVisibility(View.VISIBLE);
                    //volvemos a renderizar la barra de progreso
                    pb = (ProgressBar) findViewById(R.id.pb8);
                    pb.setMax(8);
                    pb.setProgress(8);
                }else {
                    Toast.makeText(getApplicationContext(),R.string.no_opcion, Toast.LENGTH_LONG).show();
                }
            }
        });
        bt8 = findViewById(R.id.bt8);
        bt8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(opcion>0){
                    //Guardar la opcion seleccionada en el array y poner la variable opcion a a cero
                    array_respuestas[7] = opcion;
                    opcion = 0;
                    //Envío toda la información a la pantalla de confirmación
                    Intent i = new Intent(getApplicationContext(), third_activity.class);

                    i.putExtra("edad", edad);
                    i.putExtra("genero", genero);
                    i.putExtra("provincia", provincia);
                    i.putExtra("array_respuestas", array_respuestas);

                    startActivity(i);

                }else {
                    Toast.makeText(getApplicationContext(),R.string.no_opcion, Toast.LENGTH_LONG).show();
                }
            }
        });

        //****************************************************************************************//
        //                                  C H E C K B O X                                       //
        //****************************************************************************************//
        cb41 = (CheckBox) findViewById(R.id.cb41);
        cb41.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(buttonView.isChecked()){
                    array_enteros[0] = 1;
                }else {
                    array_enteros[0] = 0;
                }
            }
        });

        cb42 = (CheckBox) findViewById(R.id.cb42);
        cb42.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(buttonView.isChecked()){
                    array_enteros[1] = 2;
                }else {
                    array_enteros[1] = 0;
                }
            }
        });

        cb43 = (CheckBox) findViewById(R.id.cb43);
        cb43.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(buttonView.isChecked()){
                    array_enteros[2] = 3;
                }else {
                    array_enteros[2] = 0;
                }
            }
        });

        cb44 = (CheckBox) findViewById(R.id.cb44);
        cb44.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(buttonView.isChecked()){
                    array_enteros[3] = 4;
                }else {
                    array_enteros[3] = 0;
                }
            }
        });



        //****************************************************************************************//
        //                                  R A D I O G R O U P S                                 //
        //****************************************************************************************//
        rg1 = (RadioGroup) findViewById(R.id.rg1);
        rg1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                if(checkedId == R.id.rb11){
                    opcion = 1;
                }else if(checkedId == R.id.rb12){
                    opcion = 2;
                }else if(checkedId == R.id.rb13){
                    opcion = 3;
                }else if(checkedId == R.id.rb14){
                    opcion = 4;
                }
            }
        });
        rg2 = (RadioGroup) findViewById(R.id.rg2);
        rg2.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                if(checkedId == R.id.rb21){
                    opcion = 1;
                }else if(checkedId == R.id.rb22){
                    opcion = 2;
                }else if(checkedId == R.id.rb23){
                    opcion = 3;
                }else if(checkedId == R.id.rb24){
                    opcion = 4;
                }
            }
        });
        rg3 = (RadioGroup) findViewById(R.id.rg3);
        rg3.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                if(checkedId == R.id.rb31){
                    opcion = 1;
                }else if(checkedId == R.id.rb32){
                    opcion = 2;
                }else if(checkedId == R.id.rb33){
                    opcion = 3;
                }else if(checkedId == R.id.rb34){
                    opcion = 4;
                }
            }
        });
        rg5 = (RadioGroup) findViewById(R.id.rg5);
        rg5.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                if(checkedId == R.id.rb51){
                    opcion = 1;
                }else if(checkedId == R.id.rb52){
                    opcion = 2;
                }else if(checkedId == R.id.rb53){
                    opcion = 3;
                }else if(checkedId == R.id.rb54){
                    opcion = 4;
                }
            }
        });
        rg6 = (RadioGroup) findViewById(R.id.rg6);
        rg6.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                if(checkedId == R.id.rb61){
                    opcion = 1;
                }else if(checkedId == R.id.rb62){
                    opcion = 2;
                }else if(checkedId == R.id.rb63){
                    opcion = 3;
                }else if(checkedId == R.id.rb64){
                    opcion = 4;
                }
            }
        });
        rg7 = (RadioGroup) findViewById(R.id.rg7);
        rg7.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                if(checkedId == R.id.rb71){
                    opcion = 1;
                }else if(checkedId == R.id.rb72){
                    opcion = 2;
                }else if(checkedId == R.id.rb73){
                    opcion = 3;
                }else if(checkedId == R.id.rb74){
                    opcion = 4;
                }
            }
        });
        rg8 = (RadioGroup) findViewById(R.id.rg8);
        rg8.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                if(checkedId == R.id.rb81){
                    opcion = 1;
                }else if(checkedId == R.id.rb82){
                    opcion = 2;
                }else if(checkedId == R.id.rb83){
                    opcion = 3;
                }else if(checkedId == R.id.rb84){
                    opcion = 4;
                }
            }
        });


    }

    //*******************************************************************************************//
    //                                      A N I M A C I Ó N                                    //
    //*******************************************************************************************//
    private void animar(LinearLayout ll)
    {
        AnimationSet set = new AnimationSet(true);
        Animation animation = null;

        //desde la esquina inferior derecha a la superior izquierda
        animation = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 1.0f, Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF, 1.0f, Animation.RELATIVE_TO_SELF, 0.0f);

        //duración en milisegundos
        animation.setDuration(700);
        set.addAnimation(animation);
        LayoutAnimationController controller = new LayoutAnimationController(set, 0.25f);

        ll.setLayoutAnimation(controller);
        ll.startAnimation(animation);
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
