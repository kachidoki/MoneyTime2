package com.kachidoki.ma.moneytime2.Main.Fragment.Host;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.kachidoki.ma.moneytime2.Model.Task.Task;
import com.kachidoki.ma.moneytime2.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by mayiwei on 2017/2/17.
 */

public class ViewHolderDay extends HostBaseViewHolder {


    @BindView(R.id.holder_host_color_day)
    ImageView holderHostColorDay;
    @BindView(R.id.holder_host_day)
    TextView holderHostDay;

    public ViewHolderDay(ViewGroup viewGroup) {
        super(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.viewholder_host_recycler_day, viewGroup, false));
        ButterKnife.bind(this, itemView);
    }


    @Override
    public void bind(Task task) {
        holderHostDay.setText(task.month()+"月"+task.day()+"日");
        holderHostColorDay.setImageResource(R.drawable.icon_round_black);
    }
}
