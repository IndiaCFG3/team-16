package com.shreyanshjain.tiss;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CourseRecyclerViewAdapter extends RecyclerView.Adapter<CourseRecyclerViewAdapter.ViewHolder> {

    private static final String TAG = "RecyclerViewAdapter";

    //vars
    private ArrayList<String> marray1 = new ArrayList<>();
    private ArrayList<String> marray2 = new ArrayList<>();
    private ArrayList<String> marray3 = new ArrayList<>();
    private Context mContext,context;

    public CourseRecyclerViewAdapter(Context context, ArrayList<String> array1, ArrayList<String> array2, ArrayList<String> array3) {
        marray1 = array1;
        marray2 = array2;
        marray3 = array3;
        mContext = context;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.regist_course_cv, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        Log.d(TAG, "onBindViewHolder: called.");


            holder.tv1.setText(marray1.get(position));
            holder.tv2.setText(marray2.get(position));
            holder.tv3.setText(marray3.get(position));


/*            if(marray1.get(position).equals("1.")){
            holder.winpic.setImageResource(R.drawable.ic_first);
        }
        else if(marray1.get(position).equals("2.")){
            holder.winpic.setImageResource(R.drawable.ic_second);
        }
        else if(marray1.get(position).equals("3.")){
            holder.winpic.setImageResource(R.drawable.ic_third);
        }*/


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "RANK:"+marray1.get(position), Toast.LENGTH_SHORT).show();
            }
        });


    }

    @Override
    public int getItemCount() {
        return marray1.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView tv1,tv2,tv3;
        ImageView imgCourse;


        public ViewHolder(View itemView) {
            super(itemView);
            context = itemView.getContext();
            imgCourse = itemView.findViewById(R.id.imgCourse);
            tv1 = itemView.findViewById(R.id.CourseNameTV);
        }
    }
}
