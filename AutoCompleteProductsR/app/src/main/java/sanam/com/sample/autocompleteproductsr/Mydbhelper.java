package sanam.com.sample.autocompleteproductsr;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class Mydbhelper extends SQLiteOpenHelper {
    public Mydbhelper(Context context) {
        super(context, "products.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = ("CREATE TABLE PRODUCTS(ID INTEGER PRIMARY KEY AUTOINCREMENT,PRODUCTNAME TEXT)");
        db.execSQL(query);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS PRODUCTS");
        onCreate(db);


    }
    public void Addproducts(String productname) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("PRODUCTNAME", productname);
        db.insert("PRODUCTS", null, values);
        db.close();
    }
    public ArrayList dbtoarray(){
        ArrayList arrayList = new ArrayList();
        SQLiteDatabase db =getReadableDatabase();
         String query ="SELECT * FROM PRODUCTS WHERE 1";
        Cursor c = db.rawQuery(query,null);
        c.moveToFirst();
        while (!c.isAfterLast()){
            if (c.getString(c.getColumnIndex("PRODUCTNAME"))!=null){
                arrayList.add(c.getString(c.getColumnIndex("PRODUCTNAME")));
                c.moveToNext();
            }
        }
        db.close();
        return arrayList;
        }
        }
