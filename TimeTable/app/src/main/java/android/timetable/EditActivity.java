package android.timetable;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import java.util.Calendar;


public class EditActivity extends AppCompatActivity{
    private int dayCode;
    private int REQUEST_CODE=1;
    private String KEY_DAY_CODE="day_code";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);
        Calendar calendar= Calendar.getInstance();
        dayCode=calendar.get(Calendar.DAY_OF_WEEK);
        if(dayCode>6||dayCode<2)
            dayCode=2;
        if(savedInstanceState==null){
            Intent i=new Intent(EditActivity.this,MenuDaysActivity.class);
            startActivityForResult(i,REQUEST_CODE);
        }
        else
            dayCode=savedInstanceState.getInt(KEY_DAY_CODE);
        setTitle(Days.get(EditActivity.this).getDay(dayCode).getDayTitle());
        FragmentManager fm=getSupportFragmentManager();
        Fragment fragment=fm.findFragmentById(R.id.fragment_container);
        if(fragment==null)
        {
            fragment=new EditFragment();
            fm.beginTransaction().add(R.id.fragment_container,fragment).commit();
        }
    }
    @Override
    protected void onActivityResult(int requestcode,int resultcode,Intent data){
        if(requestcode!=REQUEST_CODE)
        {
            return;
        }
        if(resultcode!=Activity.RESULT_OK)
        {
            finish();
            return;
        }
        dayCode=data.getIntExtra(MenuDaysActivity.EXTRA_SELECTED_DAY,0);
        setTitle(Days.get(EditActivity.this).getDay(dayCode).getDayTitle());
    }
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putInt(KEY_DAY_CODE,dayCode);
    }
}
