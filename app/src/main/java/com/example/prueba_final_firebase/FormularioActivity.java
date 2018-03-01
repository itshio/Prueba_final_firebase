package com.example.prueba_final_firebase;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class FormularioActivity extends AppCompatActivity {

    EditText etdni,etnombre,etprofesion;
    private DatabaseReference dbRef;
    private ValueEventListener valueEventListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario);

        etdni=(EditText)findViewById(R.id.et_formulario_dni);
        etnombre=(EditText)findViewById(R.id.et_formulario_nombre);
        etprofesion=(EditText)findViewById(R.id.et_formulario_profesion);


        Bundle bundempleado = getIntent().getExtras();

        if(bundempleado!=null){

            Empleado empleados = bundempleado.getParcelable(MainActivity.EXTRA_EMPLEADO);
            etdni.setText(empleados.getDNI());
            etnombre.setText(empleados.getNombre());
            etprofesion.setText(empleados.getProfesion());



        }else{






        }



    }

    public void click_insertar (View view){



        String nombre = etnombre.getText().toString();
        String dni = etdni.getText().toString();
        String profesion = etprofesion.getText().toString();


        if( nombre.equals("")||dni.equals("")||profesion.equals("")){

            Toast.makeText(this,"Debes rellenar todos los campos",Toast.LENGTH_LONG).show();


        }else{


            Empleado nuevoJugador = new Empleado(nombre,dni,profesion);
            dbRef = FirebaseDatabase.getInstance().getReference().child("empleados");

            // STRING NUEVA CLASE

            dbRef.child(dni).setValue(nuevoJugador, new DatabaseReference.CompletionListener() {
                @Override
                public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {

                    if (databaseError == null){

                        Toast.makeText(getApplicationContext(),"agregado correctamente",Toast.LENGTH_LONG).show();




                    }else{

                        Toast.makeText(getApplicationContext(),"no se puede agregar",Toast.LENGTH_LONG).show();


                    }


                    limpiar();

                }
            });





        }




    }

    public void click_modificar (View view){


        String nombre = etnombre.getText().toString();
        String dni = etdni.getText().toString();
        String profesion = etprofesion.getText().toString();


        if( nombre.equals("")||dni.equals("")||profesion.equals("")){

            Toast.makeText(this,"Debes rellenar todos los campos",Toast.LENGTH_LONG).show();


        }else{


            Empleado nuevoempleado = new Empleado(nombre,dni,profesion);
            dbRef = FirebaseDatabase.getInstance().getReference().child("empleados");

            // STRING NUEVA CLASE

            dbRef.child(dni).setValue(nuevoempleado, new DatabaseReference.CompletionListener() {
                @Override
                public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {

                    if (databaseError == null){

                        Toast.makeText(getApplicationContext(),"cambiado correctamente",Toast.LENGTH_LONG).show();




                    }else{

                        Toast.makeText(getApplicationContext(),"no se pudo modificar",Toast.LENGTH_LONG).show();


                    }

                }
            });





        }


    }

    private void limpiar (){

        etnombre.setText("");
        etdni.setText("");
        etprofesion.setText("");



    }

    public void clickeliminar (View view) {

        String dni = etdni.getText().toString();

        dbRef = FirebaseDatabase.getInstance().getReference().child("empleados");

        dbRef.child(dni).removeValue(new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                if (databaseError != null){

                    Toast.makeText(getApplicationContext(),"eliminado correctamente",Toast.LENGTH_LONG).show();
                    limpiar();

                }else{

                    Toast.makeText(getApplicationContext(),"no se pudo eliminar correctamente",Toast.LENGTH_LONG).show();


                }
            }
        });

        limpiar();

        dbRef.addValueEventListener(valueEventListener);




    }
}
