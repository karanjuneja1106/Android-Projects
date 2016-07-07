package android.timetable;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Date;

/**
 * Created by Karan on 7/5/2016.
 */
public class AddItemActivity extends AppCompatActivity{
    private EditText mLecNo,mLecTiming,mLecTitle;
    private Button mAddButton;
    private int dayCode=new Date().getDay();
    private int REQUEST_CODE=1;
    private String KEY_DAY_CODE="day_code";
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_table_item);
       if(savedInstanceState==null){
           Intent i=new Intent(AddItemActivity.this,MenuDaysActivity.class);
           startActivityForResult(i,REQUEST_CODE);
       }
        else
            dayCode=savedInstanceState.getInt(KEY_DAY_CODE);
        mLecNo=(EditText)findViewById(R.id.lec_number);
        mLecTiming=(EditText)findViewById(R.id.lec_time);
        mLecTitle=(EditText)findViewById(R.id.lec_title);
        mAddButton=(Button)findViewById(R.id.lec_add);
        setTitle(Days.get(AddItemActivity.this).getDay(dayCode).getDayTitle());
        mLecNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mLecNo.setText(""+(Days.get(AddItemActivity.this).getDay(dayCode).getLectures().size()+1));
            }
        });
        mLecTiming.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus)
                mLecTiming.setText("");
            }
        });
        mLecTitle.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus)
                    mLecTitle.setText("");
            }
        });
        mAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mLecNo.getText().toString().equals("")||mLecTiming.getText().toString().equals("")||mLecTitle.getText().toString().equals(""))
                {
                    Toast.makeText(AddItemActivity.this,"Enter Valid Lecture Details",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Day day=Days.get(AddItemActivity.this).getDay(getTitle().toString());
                    boolean res=day.addLecture(Integer.parseInt(mLecNo.getText().toString()),mLecTiming.getText().toString(),mLecTitle.getText().toString());
                    if(res)
                    {
                        Toast.makeText(AddItemActivity.this,"Lecture Added Successfully",Toast.LENGTH_SHORT).show();
                        LectureDetails lec=new LectureDetails();
                        lec.setLectureNumber(Integer.parseInt(mLecNo.getText().toString()));
                        lec.setLectureTimings(mLecTiming.getText().toString());
                        lec.setLectureTitle(mLecTitle.getText().toString());
                        Days.get(AddItemActivity.this).addLecture(lec,getTitle().toString());
                        finish();
                    }
                    else
                        Toast.makeText(AddItemActivity.this,"Lecture with same lec number already exists",Toast.LENGTH_SHORT).show();

                }
            }
        });
    }
    @Override
    protected void onActivityResult(int requestcode,int resultcode,Intent data){
        if(resultcode!= Activity.RESULT_OK)
        {
            finish();
            return;
        }
        if(REQUEST_CODE==requestcode)
        {
            dayCode=data.getIntExtra(MenuDaysActivity.EXTRA_SELECTED_DAY,0);
        }
        setTitle(Days.get(AddItemActivity.this).getDay(dayCode).getDayTitle());
    }
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putInt(KEY_DAY_CODE,dayCode);
    }
}
