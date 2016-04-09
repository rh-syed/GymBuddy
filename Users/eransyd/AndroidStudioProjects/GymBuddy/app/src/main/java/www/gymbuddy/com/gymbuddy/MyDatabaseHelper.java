package www.gymbuddy.com.gymbuddy;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.Date;

public class MyDatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME="UserInfo";

    public MyDatabaseHelper(Context context) {

        super(context, DATABASE_NAME, null, 1);

    }

    @Override

    public void onCreate(SQLiteDatabase database) {

        database.execSQL("CREATE TABLE users (_id INTEGER PRIMARY KEY AUTOINCREMENT, firstName TEXT, lastName TEXT," +
                " dateOfBirth TEXT,gender TEXT, email TEXT, password TEXT, phonenumber INTEGER, homeGym TEXT, homeGymLocation TEXT);");

    }

    public void addUser(String firstName,String lastName, String dob, String gender, String email,
                        String password, String phoneNumber, String homeGym, String homeGymLocation)

    {

        ContentValues values=new ContentValues(2);

        values.put("firstName", firstName);
        values.put("lastName", lastName);
        values.put("dateOfBirth", dob);
        values.put("gender", gender);
        values.put("email", email);
        values.put("password", password);
        values.put("phonenumber", phoneNumber);
        values.put("homeGym", homeGym);
        values.put("homeGymLocation", homeGymLocation);

        getWritableDatabase().insert("users", "name", values);

    }

    @Override

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS friends");

        onCreate(db);

    }

}