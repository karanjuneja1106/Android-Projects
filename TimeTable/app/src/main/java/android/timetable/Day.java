package android.timetable;



import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Karan on 7/4/2016.
 */
public class Day {
    private String mDayTitle;
    private int mDayCode;

    private List<LectureDetails> mLectures;
    public Day(int daycode){

        switch (daycode){
            case 2:mDayTitle="Monday";
                mDayCode=daycode;
                break;
            case 3:mDayTitle="Tuesday";
                mDayCode=daycode;
                break;
            case 4:mDayTitle="Wednesday";
                mDayCode=daycode;
                break;
            case 5:mDayTitle="Thursday";
                mDayCode=daycode;
                break;
            case 6:mDayTitle="Friday";
                mDayCode=daycode;
                break;
        }
        mLectures=new ArrayList<>();

    }

    public String getDayTitle() {
        return mDayTitle;
    }

    public List<LectureDetails> getLectures() {
        return mLectures;
    }

    public boolean addLecture(int lecNo,String timings,String title){
        for(LectureDetails Lec:mLectures)
        {
            if(Lec.getLectureNumber()==lecNo)
            return  false;
        }
        LectureDetails LecDet=new LectureDetails();
        LecDet.setLectureTimings(timings);
        LecDet.setLectureNumber(lecNo);
        LecDet.setLectureTitle(title);
        if(mLectures.size()>lecNo-1)
        mLectures.add(lecNo-1,LecDet);
        else
        mLectures.add(LecDet);
        return true;
    }
    public void addLectures(LectureDetails lec){
        mLectures.add(lec);
    }
    public int getDayCode() {
        return mDayCode;
    }
}
