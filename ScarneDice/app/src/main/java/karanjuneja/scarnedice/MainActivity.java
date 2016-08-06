package karanjuneja.scarnedice;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private TextView tvCurrentScore,tvTotalScore,tvComputerScore,tvStatus;
    private Button holdbtn,resetbtn,rollbtn;
    private ImageView ivDiceFace;

    private String KEY_TURN="turn",KEY_CURRENT_SCORE="current.score",KEY_TOTAL_SCORE="total.score",KEY_CURRENT_SCORE_COMP="current.score.comp",KEY_TOTAL_SCORE_COMP="total.score.comp";
    private int[] diceFace={R.drawable.dice1,R.drawable.dice2,R.drawable.dice3,R.drawable.dice4,R.drawable.dice5,R.drawable.dice6};
    private int currentScore,userOverallScore,computerOverallScore,computerCurrentScore;
    @Override
    public void onSaveInstanceState(Bundle savedInstance){
        super.onSaveInstanceState(savedInstance);
        savedInstance.putInt(KEY_CURRENT_SCORE,currentScore);
        savedInstance.putInt(KEY_CURRENT_SCORE_COMP,computerCurrentScore);
        savedInstance.putInt(KEY_TOTAL_SCORE,userOverallScore);
        savedInstance.putInt(KEY_TOTAL_SCORE_COMP,computerOverallScore);
        savedInstance.putBoolean(KEY_TURN,rollbtn.isEnabled());
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvCurrentScore=(TextView)findViewById(R.id.tvCurrentScore);
        tvTotalScore=(TextView)findViewById(R.id.tvTotalScore);
        tvComputerScore=(TextView)findViewById(R.id.tvComputerScore);
        tvStatus=(TextView)findViewById(R.id.tvStatus);
        holdbtn=(Button)findViewById(R.id.holdbtn);
        resetbtn=(Button)findViewById(R.id.resetbtn);
        rollbtn=(Button)findViewById(R.id.rollbtn);
        ivDiceFace=(ImageView)findViewById(R.id.ivDiceFace);
        holdbtn.setOnClickListener(this);
        holdbtn.setEnabled(false);
        rollbtn.setOnClickListener(this);
        resetbtn.setOnClickListener(this);
        if(savedInstanceState!=null){
            currentScore=savedInstanceState.getInt(KEY_CURRENT_SCORE);
            computerCurrentScore=savedInstanceState.getInt(KEY_CURRENT_SCORE_COMP);
            userOverallScore=savedInstanceState.getInt(KEY_TOTAL_SCORE);
            computerOverallScore=savedInstanceState.getInt(KEY_TOTAL_SCORE_COMP);
            tvTotalScore.setText("Your overall score:"+userOverallScore);
            tvComputerScore.setText("Computer's overall score:"+computerOverallScore);
            if(savedInstanceState.getBoolean(KEY_TURN))
            {
                tvCurrentScore.setEnabled(true);
                tvCurrentScore.setText("Current turn score:"+currentScore);
            }
        }
        Toast.makeText(this,"Your turn",Toast.LENGTH_SHORT).show();
    }
    @Override
    public void onClick(View v){
        if(v.equals(resetbtn))
            reset();
        else if(v.equals(rollbtn))
        {
            roll();
        }
        else {
            Toast.makeText(this,"Computer's turn",Toast.LENGTH_SHORT).show();
            compTurn();
        }
        check();
    }

    private void reset() {
        tvCurrentScore.setVisibility(View.INVISIBLE);
        tvTotalScore.setText("Your overall score:0");
        tvComputerScore.setText("Computer's overall score:0");
        tvStatus.setText("Status");
        currentScore=0;
        computerOverallScore=0;
        userOverallScore=0;
        computerCurrentScore=0;
        holdbtn.setEnabled(false);
        rollbtn.setEnabled(true);
        ivDiceFace.setImageResource(diceFace[0]);
    }

    private void check(){

        if(userOverallScore>=100) {
            Toast.makeText(this, "You won", Toast.LENGTH_SHORT).show();
            over();
        }
        else if(computerOverallScore>=100){
            Toast.makeText(this,"Computer won",Toast.LENGTH_SHORT).show();
            over();
        }

    }

    private void over() {
        tvStatus.setText("Game over");
        holdbtn.setEnabled(false);
        rollbtn.setEnabled(false);
    }

    private void roll(){
        holdbtn.setEnabled(true);
        tvCurrentScore.setVisibility(View.VISIBLE);
        int temp=(int)(Math.random()*6);
        if(temp==0)
        {
            currentScore=0;
            tvCurrentScore.setText("Current turn score:"+currentScore);
            Toast.makeText(this,"Rolled:"+(temp+1),Toast.LENGTH_SHORT).show();
            Toast.makeText(this,"Computer's turn",Toast.LENGTH_SHORT).show();
            compTurn();
        }
        else
        {
            currentScore+=temp+1;
            tvCurrentScore.setText("Current turn score:"+currentScore);
            Toast.makeText(this,"Rolled:"+(temp+1),Toast.LENGTH_SHORT).show();
            ivDiceFace.setImageResource(diceFace[temp]);
        }
    }
    private void compTurn(){
        userOverallScore+=currentScore;
        currentScore=0;
        holdbtn.setEnabled(false);
        tvTotalScore.setText("Your current score:"+userOverallScore);
        tvCurrentScore.setVisibility(View.INVISIBLE);
        rollbtn.setEnabled(false);
        Random random=new Random();
        Boolean turn=true;
        while(turn){
            int temp=(int)(Math.random()*6);
            if(temp==0)
            {
                ivDiceFace.setImageResource(diceFace[temp]);
                rollbtn.setEnabled(true);
                computerCurrentScore=0;
                Toast.makeText(this,"Rolled:"+(temp+1),Toast.LENGTH_SHORT).show();
                Toast.makeText(this,"Your turn",Toast.LENGTH_SHORT).show();
                return;
            }
            else
            {
                computerCurrentScore=temp+1;
                computerOverallScore+=computerCurrentScore;
                ivDiceFace.setImageResource(diceFace[temp]);
                Toast.makeText(this,"Rolled:"+(temp+1),Toast.LENGTH_SHORT).show();
            }
            turn=random.nextBoolean();
        }
        computerCurrentScore=0;
        tvComputerScore.setText("Computer overall score:"+computerOverallScore);
        rollbtn.setEnabled(true);
        Toast.makeText(this,"Your turn",Toast.LENGTH_SHORT).show();
    }
}
