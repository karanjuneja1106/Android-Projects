package android.timetable;

import android.app.DownloadManager;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.timetable.database.TimetableBaseHelper;
import android.timetable.database.TimetableCursorWrapper;
import android.timetable.database.TimetableDbSchema;
import android.timetable.database.TimetableDbSchema.TimeTable;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Created by Karan on 7/4/2016.
 */
public class Days {
    private static Days sDays;
    private List<Day> mDayList;
    private SQLiteDatabase mDatabase;
    public static Days get(Context packagename){
        if(sDays==null)
            sDays=new Days(packagename);
        return sDays;
    }
    public Days(Context packagename){
        mDatabase = new TimetableBaseHelper(packagename.getApplicationContext()).getWritableDatabase();
        mDayList=new ArrayList<>();
        for(int i=2;i<7;i++)
        {
            Day day=new Day(i);
            TimetableCursorWrapper cursorWrapper=queryTimeTable(day.getDayTitle(),null,null);
            try{
                cursorWrapper.moveToFirst();
                while (!cursorWrapper.isAfterLast()){
                    day.addLectures(cursorWrapper.getLecture());
                    cursorWrapper.moveToNext();
                }
            }
            finally {
                cursorWrapper.close();
            }
            mDayList.add(day);
        }
    }

    public List<Day> getDayList() {
        return mDayList;
    }
    public Day getDay(int daycode)
    {
        for(Day day:mDayList){
            if(day.getDayCode()==daycode)
                return day;
        }
        return null;
    }
    public Day getDay(String title)
    {
        for(Day day:mDayList){
            if(day.getDayTitle().equals(title))
                return day;
        }
        return null;
    }
    public void addLecture(LectureDetails lec,String dayTitle){
        ContentValues values= getContentValues(lec);
        mDatabase.insert(dayTitle, null, values);
    }
    public void deleteLecture(String LecNo,String dayTitle){
       int t= mDatabase.delete(dayTitle,TimeTable.Cols.LECTURE_NUM+" = ?",new String[]{LecNo});

    }
    public void updateLecture(LectureDetails lec,String dayTitle) {
        String LecNum=""+lec.getLectureNumber();
        ContentValues values= getContentValues(lec);
        mDatabase.update(dayTitle, values,
                TimeTable.Cols.LECTURE_NUM + " = ?",
                new String[] { LecNum});
    }
    private static ContentValues getContentValues(LectureDetails lec) {

        ContentValues values = new ContentValues();
        values.put(TimeTable.Cols.LECTURE_NUM,""+lec.getLectureNumber());
        values.put(TimeTable.Cols.SUBJECT,lec.getLectureTitle());
        values.put(TimeTable.Cols.TIME,lec.getLectureTimings());
        return values;
    }
    private TimetableCursorWrapper queryTimeTable(String tableName, String whereClause, String[] whereArgs) {
        Cursor cursor = mDatabase.query(
                tableName,
                null,
                whereClause,
                whereArgs,
                null,
                null,
                TimeTable.Cols.LECTURE_NUM+" ASC"
        );
        return new TimetableCursorWrapper(cursor);
    }
}
