package android.calculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private TextView mValue,mAnswer,mOperation;
    private Button mButton[]=new Button[17];
    private double i=0,j=0;
    private String op;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mValue=(TextView)findViewById(R.id.val);
        mAnswer=(TextView)findViewById(R.id.ans);
        mOperation=(TextView)findViewById(R.id.op);
        mButton[0]=(Button)findViewById(R.id.zero);
        mButton[1]=(Button)findViewById(R.id.one);
        mButton[2]=(Button)findViewById(R.id.two);
        mButton[3]=(Button)findViewById(R.id.three);
        mButton[4]=(Button)findViewById(R.id.four);
        mButton[5]=(Button)findViewById(R.id.five);
        mButton[6]=(Button)findViewById(R.id.six);
        mButton[7]=(Button)findViewById(R.id.seven);
        mButton[8]=(Button)findViewById(R.id.eight);
        mButton[9]=(Button)findViewById(R.id.nine);
        mButton[10]=(Button)findViewById(R.id.plus);
        mButton[11]=(Button)findViewById(R.id.minus);
        mButton[12]=(Button)findViewById(R.id.divide);
        mButton[13]=(Button)findViewById(R.id.multiply);
        mButton[14]=(Button)findViewById(R.id.equals);
        mButton[15]=(Button)findViewById(R.id.point);
        mButton[16]=(Button)findViewById(R.id.del);

        mAnswer.setText("0");
        mButton[16].setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                mValue.setText("");
                mAnswer.setText("");
                mOperation.setText("");
                return true;
            }
        });
        mButton[14].setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                op=(String)mOperation.getText();
                mOperation.setText("");
                String x=(String)mAnswer.getText();
                if(!x.equals(""))
                j=Double.parseDouble(x);
                else
                j=0;
                i=Double.parseDouble((String) mValue.getText());
                mAnswer.setText("");
                if(!op.equals("")&& !x.equals(""))
                {
                    if(op.equals("+"))
                        mValue.setText(""+(i+j));
                    else if(op.equals("-"))
                        mValue.setText(""+(i-j));
                    else if(op.equals("X"))
                        mValue.setText(""+(i*j));
                    else if(i!=0.0 && op.equals("/"))
                        mValue.setText(""+(j/i));
                    else
                        Toast.makeText(MainActivity.this, "Division By 0 not possible", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(MainActivity.this,"Please select an operation/operand missing",Toast.LENGTH_SHORT).show();
                    mAnswer.setText(""+j);
                    mValue.setText(""+(int)i);
                }

            }
        });
        mButton[16].setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                String x=(String)mValue.getText();
                int l=x.length();
                String y="";
                for(int m=0;m<l-1;m++)
                {
                    y=y.concat(Character.toString(x.charAt(m)));
                }
                mValue.setText(y);
            }
        });
        mButton[0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String x=(String)mValue.getText();
                x=x.concat("0");
                mValue.setText(x);
            }
        });
        mButton[1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String x=(String)mValue.getText();
                x=x.concat("1");
                mValue.setText(x);
            }
        });
        mButton[2].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String x=(String)mValue.getText();
                x=x.concat("2");
                mValue.setText(x);
            }
        });
        mButton[3].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String x=(String)mValue.getText();
                x=x.concat("3");
                mValue.setText(x);
            }
        });
        mButton[4].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String x=(String)mValue.getText();
                x=x.concat("4");
                mValue.setText(x);
            }
        });
        mButton[5].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String x=(String)mValue.getText();
                x=x.concat("5");
                mValue.setText(x);
            }
        });
        mButton[6].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String x=(String)mValue.getText();
                x=x.concat("6");
                mValue.setText(x);
            }
        });
        mButton[7].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String x=(String)mValue.getText();
                x=x.concat("7");
                mValue.setText(x);
            }
        });
        mButton[8].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String x=(String)mValue.getText();
                x=x.concat("8");
                mValue.setText(x);
            }
        });
        mButton[9].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String x=(String)mValue.getText();
                x=x.concat("9");
                mValue.setText(x);
            }
        });
        mButton[10].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAnswer.setText(mValue.getText());
                mValue.setText("");
                mOperation.setText("+");
            }
        });
        mButton[11].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mOperation.setText("-");
            }
        });
        mButton[12].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAnswer.setText(mValue.getText());
                mValue.setText("");
                mOperation.setText("/");
            }
        });
        mButton[13].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAnswer.setText(mValue.getText());
                mValue.setText("");
                mOperation.setText("X");
            }
        });
        mButton[15].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String x=(String)mValue.getText();
                int i,l=x.length();
                for(i=0;i<l;i++)
                {
                    if(x.charAt(i)=='.')
                        break;
                }
                if(i==l)
                {
                    x=x.concat(".");
                    mValue.setText(x);
                }
                else
                    Toast.makeText(MainActivity.this,"There is already a decimal point in the value",Toast.LENGTH_SHORT).show();

            }
        });
    }
}
