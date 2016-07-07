package android.timetable;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by Karan on 7/3/2016.
 */
public class MenuDaysActivity extends AppCompatActivity {
    private TextView mMonTV,mTueTV,mWedTV,mThursTV,mFriTV;
    public static final String EXTRA_SELECTED_DAY="android.timetable.selected_day";
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_days);
        mMonTV=(TextView)findViewById(R.id.monday);
        mTueTV=(TextView)findViewById(R.id.tuesday);
        mWedTV=(TextView)findViewById(R.id.wednesday);
        mThursTV=(TextView)findViewById(R.id.thursday);
        mFriTV=(TextView)findViewById(R.id.friday);
        mMonTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setSelectedDay(2);
            }
        });
        mTueTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setSelectedDay(3);
            }
        });
        mWedTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setSelectedDay(4);
            }
        });
        mThursTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setSelectedDay(5);
            }
        });
        mFriTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setSelectedDay(6);
            }
        });
    }
    private void setSelectedDay(int selectedDay) {
        Intent i=new Intent();
        i.putExtra(EXTRA_SELECTED_DAY,selectedDay);
        setResult(RESULT_OK,i);
        finish();
    }
}
