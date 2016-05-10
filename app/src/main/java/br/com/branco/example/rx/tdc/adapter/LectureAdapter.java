package br.com.branco.example.rx.tdc.adapter;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import br.com.branco.example.rx.tdc.R;
import br.com.branco.example.rx.tdc.model.Lecture;
import br.com.branco.example.rx.tdc.model.Track;
import br.com.branco.example.rx.tdc.service.InMemoryLectureService;

/**
 * Created by guilhermebranco on 5/4/16.
 */
public class LectureAdapter extends RecyclerView.Adapter<LectureAdapter.LectureViewHolder>{

    private Context context;
    private List<Lecture> lectureList;

    public LectureAdapter(Context context, List<Lecture> lectureList) {
        this.context = context;
        this.lectureList = lectureList;
    }

    public void setLectureList(List<Lecture> lectureList) {
        this.lectureList.clear();
        this.lectureList.addAll(lectureList);
    }

    @Override
    public LectureViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(this.context).inflate(R.layout.lecture_item, parent, false);
        LectureViewHolder viewHolder = new LectureViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(LectureViewHolder holder, int position) {
        Lecture lecture = lectureList.get(position);
        holder.tvName.setText(lecture.getName());
        holder.tvTrack.setText(lecture.getTrack().getName());
        holder.tvDetails.setText(lecture.getHour() + " - " + lecture.getDay());
        holder.tvTrack.setBackground(getColoByTrack(lecture.getTrack()));
    }

    @Override
    public int getItemCount() {
        return lectureList.size();
    }

    public class LectureViewHolder extends RecyclerView.ViewHolder{

        private TextView tvName;
        private TextView tvDetails;
        private TextView tvTrack;

        public LectureViewHolder(View itemView) {
            super(itemView);
            tvName = (TextView) itemView.findViewById(R.id.lecture_name);
            tvDetails = (TextView) itemView.findViewById(R.id.lecture_details);
            tvTrack = (TextView) itemView.findViewById(R.id.track);
        }
    }

    private Drawable getColoByTrack(Track track){
        if(InMemoryLectureService.ANDROID.getName().equalsIgnoreCase(track.getName())){
            return context.getResources().getDrawable(R.drawable.layout_bg_green);
        }

        if(InMemoryLectureService.ARQUITETURA.getName().equalsIgnoreCase(track.getName())){
            return context.getResources().getDrawable(R.drawable.layout_bg_red);
        }

        if(InMemoryLectureService.MOBILE.getName().equalsIgnoreCase(track.getName())){
            return context.getResources().getDrawable(R.drawable.layout_bg_blue);
        }

        return context.getResources().getDrawable(R.drawable.layout_bg_blue);
    }

    public static class SimpleDividerItemDecoration extends RecyclerView.ItemDecoration {
        private Drawable mDivider;

        public SimpleDividerItemDecoration(Context context) {
            mDivider = context.getResources().getDrawable(R.drawable.list_divider);
        }

        @Override
        public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
            int left = parent.getPaddingLeft();
            int right = parent.getWidth() - parent.getPaddingRight();

            int childCount = parent.getChildCount();
            for (int i = 0; i < childCount; i++) {
                View child = parent.getChildAt(i);

                RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();

                int top = child.getBottom() + params.bottomMargin;
                int bottom = top + mDivider.getIntrinsicHeight();

                mDivider.setBounds(left, top, right, bottom);
                mDivider.draw(c);
            }
        }
    }
}
