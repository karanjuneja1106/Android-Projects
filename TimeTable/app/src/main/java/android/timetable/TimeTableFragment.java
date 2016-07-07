package android.timetable;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.*;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Calendar;
import java.util.List;

/**
 * Created by Karan on 7/4/2016.
 */
public class TimeTableFragment extends Fragment {
    private RecyclerView mRecyclerView;
    private ItemAdapter mAdapter;
    private int dayCode= Calendar.getInstance().get(Calendar.DAY_OF_WEEK);
    private String mTitle;
    public static int REQUEST_CODE=1;
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view= inflater.inflate(R.layout.fragment_recycler_view,container,false);
        mRecyclerView=(RecyclerView)view.findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        Day day=Days.get(getActivity()).getDay(dayCode);
        updateUI(day);
        return view;
    }
    private void updateUI(Day day){
        List<LectureDetails> mLecs=day.getLectures();

        mAdapter=new ItemAdapter(mLecs);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();

    }

    @Override
    public void onResume(){
        super.onResume();
        mTitle=(String)getActivity().getTitle();
        Day day=Days.get(getActivity()).getDay(mTitle);
        updateUI(day);
    }
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.fragment_time_table, menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_item_days:
                Intent intent =new Intent(getActivity(),MenuDaysActivity.class);
                getActivity().startActivityForResult(intent,REQUEST_CODE);
                return true;
            case R.id.edit_time_table:
                Intent i =new Intent(getActivity(),EditActivity.class);
                getActivity().startActivity(i);
                return true;
            case R.id.add_time_table:
                Intent j =new Intent(getActivity(),AddItemActivity.class);
                getActivity().startActivity(j);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    private class ItemHolder extends RecyclerView.ViewHolder{
        private TextView mTitleTextView;
        private TextView mTimingTextView;

        private LectureDetails mLectureDetails;
        public ItemHolder(View itemView){
            super(itemView);
            mTitleTextView=(TextView) itemView.findViewById(R.id.list_item_time_table_lec_details);
            mTimingTextView=(TextView)itemView.findViewById(R.id.list_item_time_table_lec_time);
        }
        public void bindItem(LectureDetails lectureDetails){
            mLectureDetails=lectureDetails;
            mTitleTextView.setText(mLectureDetails.getLectureTitle());
            mTimingTextView.setText(mLectureDetails.getLectureTimings());
        }
    }
    private class ItemAdapter extends RecyclerView.Adapter<ItemHolder> {
        private List<LectureDetails> mLectureDetailsList;

        public ItemAdapter(List<LectureDetails> lectures) {
            mLectureDetailsList = lectures;
        }

        @Override
        public ItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View view = layoutInflater.inflate(R.layout.list_time_table, parent, false);
            return new ItemHolder(view);
        }

        @Override
        public int getItemCount() {
            return mLectureDetailsList.size();
        }

        @Override
        public void onBindViewHolder(ItemHolder holder, int position) {
            LectureDetails mLec = mLectureDetailsList.get(position);
            holder.bindItem(mLec);
        }
    }

}
