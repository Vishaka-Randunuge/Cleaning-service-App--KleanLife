package com.example.kleanlife;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class Database extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "KleanLife.db";

    private static final String TABLE1 = "User";

    private static final String TABLE2 = "PropertyDetails";

    private static final String TABLE3 = "Publish";

    private static final String TABLE4 = "Review";

    public Database(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

        String table1 = "CREATE TABLE "+TABLE1+"(name TEXT primary key, pass TEXT, " +
                                                "validityQuestion TEXT, response TEXT, userForm TEXT)";
        String table2 = "CREATE TABLE "+TABLE2+"(propertyID TEXT primary key, name TEXT," +
                                                " roomCount TEXT, bathroomCount TEXT, flooringType TEXT, location TEXT, image blob)";
        String table3 = "CREATE TABLE "+TABLE3+"(name TEXT primary key, propertyID TEXT, roomCount TEXT," +
                                                " bathroomCOunt TEXT, flooringType TEXT, location TEXT, image blob, cost TEXT, date TEXT)";
        String table4 = "CREATE TABLE "+TABLE4+"(reviewGiver TEXT, review TEXT, reviewReceiver TEXT, date TEXT)";
        db.execSQL(table1);
        db.execSQL(table2);
        db.execSQL(table3);
        db.execSQL(table4);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS "+TABLE1);

        db.execSQL("DROP TABLE IF EXISTS "+TABLE2);

        db.execSQL("DROP TABLE IF EXISTS "+TABLE3);

        db.execSQL("DROP TABLE IF EXISTS "+TABLE4);



        onCreate(db);
    }

    public boolean insertUser(String name, String pass, String validityQuestion, String response, String userForm) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("pass", pass);
        contentValues.put("validityQuestion", validityQuestion);
        contentValues.put("response", response);
        contentValues.put("userForm", userForm);

        long result = sqLiteDatabase.insert(TABLE1, null, contentValues);

        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    public boolean insertReview(String reviewGiver, String review, String reviewReceiver, String date) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("reviewGiver", reviewGiver);
        contentValues.put("review", review);
        contentValues.put("reviewReceiver", reviewReceiver);
        contentValues.put("date", date);

        long result = sqLiteDatabase.insert(TABLE4, null, contentValues);

        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    public ArrayList<Publish> getPublish() {

        SQLiteDatabase db = this.getReadableDatabase();

        ArrayList<Publish> arrayList = new ArrayList<>();

        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE3, null);
        while (cursor.moveToNext()){
            String user = cursor.getString(0);

            String pID = cursor.getString(1);

            String rooms = cursor.getString(2);

            String bathrooms = cursor.getString(3);

            String floor = cursor.getString(4);

            String loc = cursor.getString(5);

            byte[] img = cursor.getBlob(6);

            String cos = cursor.getString(7);

            String dt = cursor.getString(8);

            Publish publish = new Publish(user, pID, rooms, bathrooms, floor, loc, img, cos, dt);
            arrayList.add(publish);
        }

        return arrayList;

    }

    public ArrayList<Review> getrev(String reviewReceiver) {
        SQLiteDatabase db = this.getReadableDatabase();

        ArrayList<Review> arrayList = new ArrayList<>();

        Cursor cursor = db.rawQuery("SELECT * FROM review WHERE reviewReceiver=?", new String[] {reviewReceiver});

        while (cursor.moveToNext()) {
            String giver = cursor.getString(0);

            String rev = cursor.getString(1);

            String receiver = cursor.getString(2);

            String date = cursor.getString(3);

            Review review1 = new Review(giver, rev, receiver, date);
            arrayList.add(review1);
        }
        return arrayList;
    }

    public int deleteProperty(String name) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        return sqLiteDatabase.delete(TABLE2,"name = ?", new String[] {name});
    }

    public int deletePublish(String name) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        return sqLiteDatabase.delete(TABLE3,"name = ?", new String[] {name});
    }

    public boolean insertPropertyDetails(String propertyID, String name, String roomCount, String bathroomCount, String flooringType, String location, byte[] image) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        contentValues.put("propertyID", propertyID);

        contentValues.put("name", name);

        contentValues.put("roomCount", roomCount);

        contentValues.put("bathroomCount", bathroomCount);

        contentValues.put("flooringType", flooringType);

        contentValues.put("location", location);

        contentValues.put("image", image);

        long result = sqLiteDatabase.insert(TABLE2, null, contentValues);

        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    public boolean insertPublish(String name, String propertyID, String roomCount, String bathroomCount, String flooringType, String location, byte[] image, String cost, String date) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("name", name);

        contentValues.put("propertyID", propertyID);

        contentValues.put("roomCount", roomCount);

        contentValues.put("bathroomCount", bathroomCount);

        contentValues.put("flooringType", flooringType);

        contentValues.put("location", location);

        contentValues.put("image", image);

        contentValues.put("cost", cost);

        contentValues.put("date", date);

        long result = sqLiteDatabase.insert(TABLE3, null, contentValues);

        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    public boolean checkProperty (String name) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select * from PropertyDetails where name = ?", new String [] {name});

        if (cursor.getCount() > 0) {
            return true;
        } else {
            return false;
        }
    }

    public boolean checkPublish (String name) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select * from Publish where name = ?", new String [] {name});

        if (cursor.getCount() > 0) {
            return true;
        } else {
            return false;
        }
    }

    public Cursor viewProperty (String name) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select * from PropertyDetails where name=?", new String[] {name});
        return cursor;
    }

    public boolean checkName (String name) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select * from User where name = ?", new String [] {name});

        if (cursor.getCount() > 0) {
            return true;
        } else {
            return false;
        }
    }

    public boolean checkNamePass (String name, String pass) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select * from User where name= ? and pass= ?", new String[] {name, pass});

        if (cursor.getCount() > 0) {
            return true;
        } else {
            return false;
        }
    }

    public Cursor viewPass (String name, String response, String validityQuestion) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select * from User where name=? and response=? and validityQuestion=?", new String[] {name, response, validityQuestion});
        return cursor;
    }

    public Cursor viewUserForm (String name) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select * from User where name=?", new String[] {name});
        return cursor;
    }
}
