package com.example.salahtracker;

public class Namaz {
   public int pid;
    public String studentName;
    public  int sabaq;
    public  int sabqi;
    public  int manzil;

    public Namaz(String _name, int sabqiNo, int manzilNo, int sabaqNo) {
        this.studentName =_name;
        this.sabaq =sabaqNo;
        this.sabqi =sabqiNo;
        this.manzil =manzilNo;

    }

}
