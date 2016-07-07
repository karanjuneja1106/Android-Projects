package android.timetable;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import java.util.Calendar;


public class TimeTableActivity extends AppCompatActivity{
    private int dayCode;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);
        Calendar calendar= Calendar.getInstance();
        dayCode=calendar.get(Calendar.DAY_OF_WEEK);
        setTitle(Days.get(TimeTableActivity.this).getDay(dayCode).getDayTitle());
        FragmentManager fm=getSupportFragmentManager();
        Fragment fragment=fm.findFragmentById(R.id.fragment_container);
        if(fragment==null)
        {
            fragment=new TimeTableFragment();
            fm.beginTransaction().add(R.id.fragment_container,fragment).commit();
        }
        }
    @Override
    protected void onActivityResult(int requestcode,int resultcode,Intent data){
        if(requestcode!=TimeTableFragment.REQUEST_CODE||resultcode==Activity.RESULT_CANCELED)
        {
            return;
        }
        setTitle(Days.get(TimeTableActivity.this).getDay(data.getIntExtra(MenuDaysActivity.EXTRA_SELECTED_DAY,0)).getDayTitle());
    }

}
