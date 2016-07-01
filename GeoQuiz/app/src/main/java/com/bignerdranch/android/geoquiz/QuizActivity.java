package com.bignerdranch.android.geoquiz;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;


public class QuizActivity extends AppCompatActivity {
    private Button mExitButton;
    private Button mTrueButton;
    private Button mFalseButton;
    private ImageButton mNext;
    private ImageButton mBack;
    private Button mCheatButton;
    private boolean mIsCheater;

    public static final int REQUEST_CODE_CHEAT=0;
    private TextView mQuestionTextView;
    private int mCurrentIndex=0;
    public static final String TAG="QuizActivity";
    public static final String KEY_INDEX="Index";
    public static final String KEY_ARRAY="Array";
    private question[] mQuestionBank=new question[]{new question(R.string.q1,true),new question(R.string.q2,true),new question(R.string.q3,true),new question(R.string.q4,true),new question(R.string.q5,false),
    new question(R.string.q6,false),new question(R.string.q7,true),new question(R.string.q8,false),new question(R.string.q9,true),new question(R.string.q10,true)
    };
    private int len=mQuestionBank.length;
    private boolean [] mCheat=new boolean[len];


    private void UpdateQuestion(){

        int Question=mQuestionBank[mCurrentIndex].getTextResId();
        mQuestionTextView.setText(Question);
    }
    private void checkAnswer(boolean guess){
        boolean answerIsTrue=mQuestionBank[mCurrentIndex].isAnswerTrue();
        int messageResId=0;
        if(mCheat[mCurrentIndex])
        {
            Toast.makeText(QuizActivity.this, R.string.judgment_toast, Toast.LENGTH_SHORT).show();

        }
        else{if(guess==answerIsTrue)
        {
            messageResId=R.string.correct_toast;
        }
        else
        {
            messageResId=R.string.incorrect_toast;
        }
        Toast.makeText(QuizActivity.this,messageResId,Toast.LENGTH_SHORT).show();;}
    }
    @Override
    public void onStart(){
        super.onStart();
        Log.d(TAG,"onStart() called");
    }
    @Override
    public void onPause(){
        super.onPause();
        Log.d(TAG,"onPause() called");
    }
    public void onResume(){
        super.onResume();
        Log.d(TAG,"onResume() called");
    }
    @Override
    public void onStop(){
        super.onStop();
        Log.d(TAG,"onStop() called");
    }
    @Override
    public void onDestroy(){
        super.onDestroy();
        Log.d(TAG,"onDestroy() called");
    }
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState){
        super.onSaveInstanceState(savedInstanceState);
        Log.i(TAG,"onSaveInstanceState");
        savedInstanceState.putInt(KEY_INDEX,mCurrentIndex);
        savedInstanceState.putBooleanArray(KEY_ARRAY,mCheat);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        if(savedInstanceState!=null)
        {
            mCheat=savedInstanceState.getBooleanArray(KEY_ARRAY);
            mCurrentIndex=savedInstanceState.getInt(KEY_INDEX);
        }
        setContentView(R.layout.activity_quiz);
        mQuestionTextView=(TextView)findViewById(R.id.question_text_view);
        Log.d(TAG, "onCreate(Bundle called)");
        mBack=(ImageButton)findViewById(R.id.back);
        mBack.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                mCurrentIndex--;
                if(mCurrentIndex<0)
                {mCurrentIndex+=mQuestionBank.length;}
                mIsCheater=false;
                UpdateQuestion();
            }
        });
        mNext=(ImageButton)findViewById(R.id.next);
        mNext.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                mCurrentIndex=(mCurrentIndex+1)%mQuestionBank.length;
                mIsCheater=false;
                UpdateQuestion();
            }
        });
        mQuestionTextView.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
             mCurrentIndex=(mCurrentIndex+1)%mQuestionBank.length;
                mIsCheater=false;
                UpdateQuestion();
            }
        });
        mExitButton =(Button)findViewById(R.id.exit_button);
        mExitButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v)
            {System.exit(0);}
        });
        mTrueButton=(Button)findViewById(R.id.true_button);
        mTrueButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
               checkAnswer(true);
            }
        });
        mCheatButton=(Button)findViewById(R.id.cheat_button) ;
        mCheatButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                boolean answerIsTrue=mQuestionBank[mCurrentIndex].isAnswerTrue();
                Intent i=CheatActivity.newIntent(QuizActivity.this,answerIsTrue);
                startActivityForResult(i,REQUEST_CODE_CHEAT);
            }
        });
        mFalseButton=(Button)findViewById(R.id.false_button);
        mFalseButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                checkAnswer(false);
            }
        });

        UpdateQuestion();

    }
    @Override
    protected void onActivityResult(int requestCode,int resultCode,Intent data){
        if(resultCode!= Activity.RESULT_OK)
            return;
        if(requestCode==REQUEST_CODE_CHEAT){
            if(data==null){
                return;
            }
            mIsCheater=CheatActivity.WasAnswerShown(data);
            mCheat[mCurrentIndex]=mIsCheater;
        }
    }

}
