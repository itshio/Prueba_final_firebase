package com.example.prueba_final_firebase;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    static final String EXTRA_EMPLEADO = "EMPLEADO";

    ListView lvprincipal;

    DatabaseReference dbRef;
    ValueEventListener valueEventListener;

    ArrayList<Empleado>lista_empleados = new ArrayList<Empleado>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvprincipal=(ListView)findViewById(R.id.main_list);


        cargarDatosFirebase();



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

    private void cargarListview (DataSnapshot dataSnapshot){

        lista_empleados.add(dataSnapshot.getValue(Empleado.class));

        Adaptador adaptador_empleado = new Adaptador(this,lista_empleados);
        lvprincipal.setAdapter(adaptador_empleado);

        lvprincipal.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {



                Empleado c = ((Empleado)parent.getItemAtPosition(position));
                String dni= c.getDNI();
                String nombre= c.getNombre();
                String profesion= c.getProfesion();

                Empleado empleados= new Empleado(nombre,dni,profesion);
                Intent form_empleado =new Intent(getApplicationContext(),FormularioActivity.class);
                form_empleado.putExtra(EXTRA_EMPLEADO,empleados);

                startActivity(form_empleado);


            }
        });

        lvprincipal.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                Empleado c = ((Empleado)parent.getItemAtPosition(position));


                String dni = c.getDNI();

                dbRef = FirebaseDatabase.getInstance().getReference().child("empleados");

                dbRef.child(dni).removeValue(new DatabaseReference.CompletionListener() {
                    @Override
                    public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                        if (databaseError != null){

                            Toast.makeText(getApplicationContext(),"no se pudo eliminar correctamente",Toast.LENGTH_LONG).show();


                        }else{

                            Toast.makeText(getApplicationContext(),"eliminado correctamente",Toast.LENGTH_LONG).show();


                        }
                    }
                });
                return true;
            }
        });



    }

    private void cargarDatosFirebase (){


        dbRef = FirebaseDatabase.getInstance().getReference().child("empleados");

        valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                lista_empleados.clear();
                for (DataSnapshot empleadosdatasnapchot: dataSnapshot.getChildren()){
                    cargarListview(empleadosdatasnapchot);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };
        dbRef.addValueEventListener(valueEventListener);



    }
}
