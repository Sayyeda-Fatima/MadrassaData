package com.example.salahtracker;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHandler   extends SQLiteOpenHelper {

        private Context context;

        private static final String DATABASE_NAME = "Salah.db";
        private static final String TABLE_NAME = "Namaz";

        private static final String COLUMN_ID = "pid";
        private static final String COLUMN_NAMAZNAME= "namazName";

        private static final String COLUMN_DATE = "date";
        private static final String COLUMN_NOOFRAKHAT = "noOfRakhat";
        private static final String COLUMN_JAMAT = "jamat";
        private static final String COLUMN_NAFAL = "nafal";
        private static final String COLUMN_TAHAJUD = "tahajud";

        public DBHandler( Context context) {
            super(context, DATABASE_NAME, null, 1);
            this.context=context;
        }

        @Override
        public void onCreate(SQLiteDatabase db) {

            String sql = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + "("
                    + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + COLUMN_NAMAZNAME + " TEXT,"
                    + COLUMN_DATE + " TEXT,"
                    + COLUMN_NOOFRAKHAT + "INTEGER,"
                    + COLUMN_JAMAT + " TEXT,"
                    + COLUMN_NAFAL + " INTEGER,"
                    + COLUMN_TAHAJUD + " TEXT"

                    + ")";
            db.execSQL(sql);

        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            String sql = "DROP TABLE IF EXISTS " + TABLE_NAME;
            db.execSQL(sql);
            onCreate(db);
        }


        public  boolean insertNamaz(Namaz namaz) {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(COLUMN_NAMAZNAME, namaz.namazName);
            values.put(COLUMN_DATE, namaz.date);
            values.put(COLUMN_NOOFRAKHAT, namaz.noOfRakhat);
            values.put(COLUMN_JAMAT, namaz.jamat);
            values.put(COLUMN_NAFAL,namaz.nafal );
            values.put(COLUMN_TAHAJUD,namaz.tahajud);


            long result= db.insert(TABLE_NAME, null, values);
            db.close();
            if(result == -1)
                return false;
            else
                return true;
        }
        public Cursor ReadAllData() {
            SQLiteDatabase db = this.getWritableDatabase();
            Cursor res=null;
            if(db!=null){
                res = db.rawQuery("select * from "+TABLE_NAME,null);
            }
            return res;
        }
//        public  boolean updateData(Student student) {
//            SQLiteDatabase db = this.getWritableDatabase();
//            ContentValues values = new ContentValues();
//            values.put(COLUMN_NAME, student.getName());
//            values.put(COLUMN_ROLLNO, student.getRollNo());
//            values.put(COLUMN_SABQ, student.getSabq());
//            values.put(COLUMN_SABQI, student.getSabqi());
//            values.put(COLUMN_MANZIL, student.getManzil());
//
//            long result = db.update(TABLE_NAME, values, "rollNo=?", new String[]{student.getRollNo()});
//            db.close();
//
//            if (result == -1) {
//
//                return false;
//            } else {
//                return true;
//            }
//        }
    ////delete
//        public  boolean deleteData(String rollNo) {
//            SQLiteDatabase db = this.getWritableDatabase();
//            long result =db.delete(TABLE_NAME, COLUMN_ROLLNO + " = ?", new String[] {rollNo});
//            db.close();
//            if (result == -1) {
//
//                return false;
//            } else {
//                return true;
//            }
//        }
    }

