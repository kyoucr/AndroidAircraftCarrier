package com.footprint.androidaircraftcarrier.ui;

import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.footprint.androidaircraftcarrier.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liquanmin on 15/7/13.
 */
public class RVAdapter extends RecyclerView.Adapter<RVAdapter.ViewHolder> {
    private List<RecyleItem> list;

    public RVAdapter() {
        list = getRecyleList();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_recyle_item, viewGroup, false);
        ViewHolder vh = new ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        viewHolder.textView.setText(list.get(i).desc);
        viewHolder.imageView.setImageURI(Uri.parse(list.get(i).imgUrl));
        viewHolder.imageView.setMinimumHeight(500 + (int)(500 * Math.random()));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView textView;
        public SimpleDraweeView imageView;

        public ViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.textView);
            imageView = (SimpleDraweeView) itemView.findViewById(R.id.imageView);
        }
    }

    private ArrayList<RecyleItem> getRecyleList() {
        ArrayList<RecyleItem> list = new ArrayList<>();

        RecyleItem recyleItem = new RecyleItem("AAAAA", "http://img1.imgtn.bdimg.com/it/u=1726807899,3234539240&fm=11&gp=0.jpg");
        list.add(recyleItem);

        recyleItem = new RecyleItem("BBBBB", "http://easyread.ph.126.net/BcZ32nqULDrIdjUrRe3axQ==/7916944415360525869.jpg");
        list.add(recyleItem);

        recyleItem = new RecyleItem("CCCCC", "http://ah.12530.com/light/file/singer/1000014389.2.1.jpg");
        list.add(recyleItem);

        recyleItem = new RecyleItem("DDDDD", "http://p2.img.cctvpic.com/20121122/images/1353573837233_1353573837233_r.jpg");
        list.add(recyleItem);

        recyleItem = new RecyleItem("EEEEE", "http://www.hinews.cn/pic/0/12/41/75/12417558_082653.jpg");
        list.add(recyleItem);

        recyleItem = new RecyleItem("FFFFF", "http://jjcb.jjxw.cn/resfile/2015-07-01/A16/p3_s.jpg");
        list.add(recyleItem);

        recyleItem = new RecyleItem("GGGGG", "http://photocdn.sohu.com/20150402/mp9182484_1427966882958_5_th_fv23.jpeg");
        list.add(recyleItem);

        recyleItem = new RecyleItem("HHHHH", "http://img4q.duitang.com/uploads/item/201505/30/20150530182633_mdGXj.thumb.700_0.jpeg");
        list.add(recyleItem);

        recyleItem = new RecyleItem("KKKKK", "http://www.people.com.cn/mediafile/pic/20141017/35/9237618693560758611.jpg");
        list.add(recyleItem);

        recyleItem = new RecyleItem("LLLLL", "http://www.hinews.cn/pic/0/12/62/33/12623370_100499.jpg");
        list.add(recyleItem);
        return list;
    }
}
