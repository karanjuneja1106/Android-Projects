package android.timetable.database;

import android.database.Cursor;
import android.database.CursorWrapper;
import android.timetable.LectureDetails;
import android.timetable.database.TimetableDbSchema.TimeTable;

/**
 * Created by Karan on 7/6/2016.
 */
public class TimetableCursorWrapper extends CursorWrapper {
    public TimetableCursorWrapper(Cursor cursor){
        super(cursor);
    }
    public LectureDetails getLecture(){
        int lecNum=getInt(getColumnIndex(TimeTable.Cols.LECTURE_NUM));
        String lecTimings=getString(getColumnIndex(TimeTable.Cols.TIME));
        String lecTitle=getString(getColumnIndex(TimeTable.Cols.SUBJECT));
        LectureDetails lec=new LectureDetails();
        lec.setLectureNumber(lecNum);
        lec.setLectureTimings(lecTimings);
        lec.setLectureTitle(lecTitle);
        return lec;
    }
}
