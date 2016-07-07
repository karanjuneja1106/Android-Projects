package android.timetable;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


/**
 * Created by Karan on 7/5/2016.
 */
public class EditItemFragment extends Fragment {
    private EditText mLecNo,mLecTiming,mLecTitle;
    private Button mSaveButton;
    private int dayCode;
    private boolean isDeleted=false;
    private int mCurrentIndex;
    public static Fragment newInstance(int dayCode,int currentIndex){
        Bundle args=new Bundle();
        args.putInt(EditFragment.EXTRA_DAY_CODE,dayCode);
        args.putInt(EditFragment.EXTRA_CURRENT_INDEX,currentIndex);
        Fragment fragment=new EditItemFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.fragment_edit, menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.delete:
                AlertDialog dialog=new AlertDialog.Builder(getActivity()).setTitle("DELETE").setMessage("Are you sure you want to delete?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Days.get(getActivity()).getDay(dayCode).getLectures().remove(mCurrentIndex);
                                Days.get(getActivity()).deleteLecture(mLecNo.getText().toString(),Days.get(getActivity()).getDay(dayCode).getDayTitle());
                                Toast.makeText(getActivity(),"Lecture Deleted",Toast.LENGTH_SHORT).show();
                                isDeleted=true;
                                getActivity().finish();
                            }
                        })
                        .setNegativeButton("Cancel",null)
                        .create();
                dialog.show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view= inflater.inflate(R.layout.activity_time_table_item,container,false);
        mCurrentIndex=getArguments().getInt(EditFragment.EXTRA_CURRENT_INDEX);
        dayCode=getArguments().getInt(EditFragment.EXTRA_DAY_CODE);
        mLecNo=(EditText)view.findViewById(R.id.lec_number);
        mLecTiming=(EditText)view.findViewById(R.id.lec_time);
        mLecTitle=(EditText)view.findViewById(R.id.lec_title);
        mSaveButton=(Button)view.findViewById(R.id.lec_add);
        mLecNo.setText(""+Days.get(getActivity()).getDay(dayCode).getLectures().get(mCurrentIndex).getLectureNumber());
        mLecNo.setEnabled(false);
        mLecTiming.setText(Days.get(getActivity()).getDay(dayCode).getLectures().get(mCurrentIndex).getLectureTimings());
        mLecTitle.setText(Days.get(getActivity()).getDay(dayCode).getLectures().get(mCurrentIndex).getLectureTitle());
        mSaveButton.setText("Save");
        getActivity().setTitle(Days.get(getActivity()).getDay(dayCode).getDayTitle()+" Lecture: "+Days.get(getActivity()).getDay(dayCode).getLectures().get(mCurrentIndex).getLectureNumber());
        mSaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Days.get(getActivity()).getDay(dayCode).getLectures().remove(mCurrentIndex);
                if(mLecNo.getText().toString().equals("")||mLecTiming.getText().toString().equals("")||mLecTitle.getText().toString().equals(""))
                {
                    Toast.makeText(getActivity(),"Enter Valid Lecture Details",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Day day=Days.get(getActivity()).getDay(dayCode);
                    day.addLecture(Integer.parseInt(mLecNo.getText().toString()),mLecTiming.getText().toString(),mLecTitle.getText().toString());
                    Toast.makeText(getActivity(),"Lecture Edited Successfully",Toast.LENGTH_SHORT).show();
                    getActivity().finish();
                }
            }
        });

        return view;
    }
    @Override
    public void onPause() {
        super.onPause();
        if(!isDeleted){
            LectureDetails lec=new LectureDetails();
            lec.setLectureNumber(Integer.parseInt(mLecNo.getText().toString()));
            lec.setLectureTitle(mLecTitle.getText().toString());
            lec.setLectureTimings(mLecTiming.getText().toString());
            String dayTitle=Days.get(getActivity()).getDay(dayCode).getDayTitle();
            Days.get(getActivity()).updateLecture(lec,dayTitle);
        }
    }
}
