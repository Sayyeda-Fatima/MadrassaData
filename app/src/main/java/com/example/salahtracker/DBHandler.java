package com.example.salahtracker;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class DBHandler   extends SQLiteOpenHelper {

        private Context context;

        private static final String DATABASE_NAME = "Salah.db";
        private static final String TABLE_NAME = "Namaz";

        private static final String COLUMN_PID = "pid";
        private static final String COLUMN_NAMAZNAME= "namazName";

        private static final String COLUMN_DATE = "date";
        private static final String COLUMN_RAKHAT = "rakhat";
        private static final String COLUMN_JAMAT = "jamat";
        private static final String COLUMN_NAFAL = "nafal";


        public DBHandler( Context context) {
            super(context, DATABASE_NAME, null, 1);
            this.context=context;
        }

        @Override
        public void onCreate(SQLiteDatabase db) {

            String sql = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + "("
                    + COLUMN_PID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + COLUMN_NAMAZNAME + " TEXT,"
                    + COLUMN_DATE + " TEXT,"
                    + COLUMN_RAKHAT + " INTEGER,"
                    + COLUMN_JAMAT + " TEXT,"
                    + COLUMN_NAFAL + " INTEGER"
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
            values.put(COLUMN_RAKHAT, namaz.rakhat);
            values.put(COLUMN_JAMAT, namaz.jamat);
            values.put(COLUMN_NAFAL,namaz.nafal );
            long result= db.insert(TABLE_NAME, null, values);
            db.close();
            if(result == -1){
                //Toast.makeText(this.context,  namaz.date, Toast.LENGTH_SHORT).show();

                return false;}
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
        public  boolean updateData(Namaz namaz) {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(COLUMN_NAMAZNAME, namaz.namazName);
//            values.put(COLUMN_DATE, namaz.date);
            values.put(COLUMN_RAKHAT, namaz.rakhat);
            values.put(COLUMN_JAMAT, namaz.jamat);
            values.put(COLUMN_NAFAL,namaz.nafal );

            long result = db.update(TABLE_NAME, values, "namazName=?", new String[]{namaz.namazName});
            db.close();

            if (result == -1) {

                return false;
            } else {
                return true;
            }
        }
    ////delete
        public  boolean deleteData(String rollNo) {
            SQLiteDatabase db = this.getWritableDatabase();
            long result =db.delete(TABLE_NAME, COLUMN_NAMAZNAME + " = ?", new String[] {rollNo});
            db.close();
            if (result == -1) {

                return false;
            } else {
                return true;
            }
        }
    }

