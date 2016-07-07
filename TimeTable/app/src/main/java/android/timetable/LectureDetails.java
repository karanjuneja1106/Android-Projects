package android.timetable;

/**
 * Created by Karan on 7/4/2016.
 */
public class LectureDetails {
    private String mLectureTimings;
    private String mLectureTitle;
    private int mLectureNumber;
    public String getLectureTimings() {
        return mLectureTimings;
    }

    public void setLectureTimings(String lectureTimings) {
        mLectureTimings = lectureTimings;
    }

    public String getLectureTitle() {
        return mLectureTitle;
    }

    public void setLectureTitle(String lectureTitle) {
        mLectureTitle = lectureTitle;
    }

    public int getLectureNumber() {
        return mLectureNumber;
    }

    public void setLectureNumber(int lectureNumber) {
        mLectureNumber = lectureNumber;
    }
}
