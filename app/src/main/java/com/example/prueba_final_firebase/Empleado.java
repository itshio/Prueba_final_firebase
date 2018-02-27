package com.example.prueba_final_firebase;

import android.os.Parcel;
import android.os.Parcelable;
import android.view.View;

/**
 * Created by DIDACT on 27/02/2018.
 */

public class Empleado implements Parcelable{

    String nombre;
    String DNI;
    String profesion;

    public static final Parcelable.Creator<Empleado> CREATOR = new Parcelable.Creator<Empleado>(){
        public Empleado createFromParcel(Parcel in) {
            return new Empleado(in);
        }
        public Empleado[] newArray(int size){
            return new Empleado[size];
        }
    };

    public Empleado() {
    }

    public Empleado(String nombre, String DNI, String profesion) {
        this.nombre = nombre;
        this.DNI = DNI;
        this.profesion = profesion;
    }

    public Empleado (Parcel parcel){

        ReadFromParcel(parcel);
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDNI() {
        return DNI;
    }

    public void setDNI(String DNI) {
        this.DNI = DNI;
    }

    public String getProfesion() {
        return profesion;
    }

    public void setProfesion(String profesion) {
        this.profesion = profesion;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeString(this.nombre);
        dest.writeString(this.DNI);
        dest.writeString(this.profesion);


    }

    public void ReadFromParcel (Parcel parcel){

        this.nombre= parcel.readString();
        this.DNI= parcel.readString();
        this.profesion= parcel.readString();
    }
}
