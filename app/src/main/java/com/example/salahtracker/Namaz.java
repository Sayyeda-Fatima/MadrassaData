package com.example.salahtracker;

public class Namaz {
   public int pid;
    public String namazName;
    public  String date;
    public  int noOfRakhat;
    public  String jamat;
    public  int nafal;


    public Namaz(String _name, String _date, int _rakhatno, String _jamat, int _nafal) {
        this.namazName=_name;
        this.date=_date;
        this.noOfRakhat=_rakhatno;
        this.jamat=_jamat;
        this.nafal=_nafal;

    }

}
