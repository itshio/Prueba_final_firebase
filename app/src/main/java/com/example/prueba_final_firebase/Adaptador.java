package com.example.prueba_final_firebase;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by DIDACT on 27/02/2018.
 */

public class Adaptador extends ArrayAdapter<Empleado> {

    ArrayList<Empleado> clasereserva;
    Context c;

    public Adaptador (Context c, ArrayList<Empleado> reservado){

        super(c, R.layout.item_empleados, reservado);
        this.c =c;
        this.clasereserva = reservado;



    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater= LayoutInflater.from(getContext());
        View item = inflater.inflate(R.layout.item_empleados,null);


        TextView tv_dni=(TextView)item.findViewById(R.id.item_dni);
        tv_dni.setText(clasereserva.get(position).getDNI());


        TextView tv_nombre=(TextView)item.findViewById(R.id.item_nombre);
        tv_nombre.setText(clasereserva.get(position).getNombre());

        TextView tv_profesion=(TextView)item.findViewById(R.id.item_profesion);
        tv_profesion.setText(clasereserva.get(position).getProfesion());




        return item;
    }
}


