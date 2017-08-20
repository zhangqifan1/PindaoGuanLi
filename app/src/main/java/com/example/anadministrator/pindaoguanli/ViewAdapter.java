package com.example.anadministrator.pindaoguanli;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 张祺钒
 * on2017/8/19.
 */

//RecyclerView.Adapter<?>里写的是优化继承ViewHolder的帮助类
public class ViewAdapter extends RecyclerView.Adapter<ViewAdapter.MyViewHolder> {
    private Context mContext;
    /**
     * 特别注意一下mList后面一定要默认赋值,不然运行的时候会报错,因为我们默认上面的RecyclerView是没有添加数据的
     */
    private List<String> mList = new ArrayList<>();
    private MyItemOnClickListener myItemOnClickListener;

    public ViewAdapter(Context context) {
        this.mContext = context;
    }

    //传数组的方法
    public void setMessage(List<String> list) {
        this.mList = list;
    }

    //写个方法把接口传过来
    public void setMyItemOnClickListener(MyItemOnClickListener myItemOnClickListener) {
        this.myItemOnClickListener = myItemOnClickListener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //打气筒填充我们自定义的message.xml的试图
        View view = View.inflate(mContext, R.layout.message, null);
        //优化
        final MyViewHolder holder = new MyViewHolder(view);
        //监听
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //调用我们自定义接口的方法
                myItemOnClickListener.onClick(v, (Integer) v.getTag());
            }
        });
        return holder;
    }
    public interface MyItemOnClickListener  {
        void onClick(View view,int position);
    }
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        MyViewHolder viewHolder = holder;
        viewHolder.itemView.setTag(position);
        viewHolder.textView.setText(mList.get(position));
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    //ViewHolder优化
    static class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView textView;

        public MyViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.textView);
        }
    }
}