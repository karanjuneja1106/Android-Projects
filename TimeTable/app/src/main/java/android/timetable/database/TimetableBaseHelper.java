package android.timetable.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v4.app.Fragment;
import android.timetable.database.TimetableDbSchema.TimeTable;

/**
 * Created by Karan on 7/5/2016.
 */
public class TimetableBaseHelper extends SQLiteOpenHelper{
    private static final int VERSION = 1;
    private static final String DATABASE_NAME="timetable.db";
    private static final String COLUMNS="(" +
            " _id integer primary key autoincrement, " +
            TimeTable.Cols.LECTURE_NUM + " unique, "+TimeTable.Cols.TIME+", "+TimeTable.Cols.SUBJECT+")";
    private static final String MONDAY="Monday";
    private static final String TUESDAY="Tuesday";
    private static final String WEDNESDAY="Wednesday";
    private static final String THURSDAY="Thursday";
    private static final String FRIDAY="Friday";

    public TimetableBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " +MONDAY+COLUMNS);
        db.execSQL("create table " +TUESDAY+COLUMNS);
        db.execSQL("create table " +WEDNESDAY+COLUMNS);
        db.execSQL("create table " +THURSDAY+COLUMNS);
        db.execSQL("create table " +FRIDAY+COLUMNS);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}
