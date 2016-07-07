package android.timetable;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Date;

/**
 * Created by Karan on 7/5/2016.
 */
public class EditItemActivity extends AppCompatActivity{
    private EditText mLecNo,mLecTiming,mLecTitle;
    private Button mSaveButton;
    private int dayCode=new Date().getDay();
    private int mCurrentIndex;
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);
        dayCode=getIntent().getIntExtra(EditFragment.EXTRA_DAY_CODE,0);
        mCurrentIndex=getIntent().getIntExtra(EditFragment.EXTRA_CURRENT_INDEX,0);
        setTitle(Days.get(EditItemActivity.this).getDay(dayCode).getDayTitle());
        FragmentManager fm=getSupportFragmentManager();
        Fragment fragment=fm.findFragmentById(R.id.fragment_container);
        if(fragment==null)
        {
            fragment=EditItemFragment.newInstance(dayCode,mCurrentIndex);
            fm.beginTransaction().add(R.id.fragment_container,fragment).commit();
        }

    }
}
