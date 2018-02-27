package com.example.prueba_final_firebase;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    static final String EXTRA_EMPLEADO = "EMPLEADO";

    ListView lvprincipal;
    ArrayList<Empleado>lista_empleados = new ArrayList<Empleado>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvprincipal=(ListView)findViewById(R.id.main_list);
        Adaptador adaptador_empleado = new Adaptador(this,lista_empleados);
        lvprincipal.setAdapter(adaptador_empleado);

        caragrempleado();

        lvprincipal.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {



                Empleado c = ((Empleado)parent.getItemAtPosition(position));
                String dni= c.getDNI();
                String nombre= c.getDNI();
                String profesion= c.getDNI();

                Empleado empleados= new Empleado(nombre,dni,profesion);
                Intent form_empleado =new Intent(getApplicationContext(),FormularioActivity.class);
                form_empleado.putExtra(EXTRA_EMPLEADO,empleados);








            }
        });

    }

    public void click_nuevo (View view){

        Intent nuevo = new Intent(getApplicationContext(),FormularioActivity.class);
        startActivity(nuevo);


    }

    private void caragrempleado (){

        lista_empleados.add(new Empleado("juan","36425984W","Pintor"));
        lista_empleados.add(new Empleado("Ataulfo","36333984W","Conserje"));
        lista_empleados.add(new Empleado("Eustaquio","36421074T","Pintor"));
        lista_empleados.add(new Empleado("Wilfredo","41925984W","Administrativo"));
    }
}
