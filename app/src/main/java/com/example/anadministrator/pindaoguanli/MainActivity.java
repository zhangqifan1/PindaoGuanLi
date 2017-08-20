package com.example.anadministrator.pindaoguanli;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView_up;
    private RecyclerView recyclerView_down;
    private List<String> listUp = new ArrayList<>();
    private List<String> listDown = new ArrayList<>();
    private ViewAdapter adapterUp;
    private ViewAdapter adapterDown;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //添加数据
        initData();
        initView();
    }
    public void initData(){
        //添加数据
        for (int i = 0; i < 15; i++) {
            //默认的我们只给下面的RecyclerView添加了数据
            listDown.add("频道管理" + i);
        }
    }

    private void initView() {
        recyclerView_up = (RecyclerView) findViewById(R.id.recyclerView_up);
        recyclerView_down = (RecyclerView) findViewById(R.id.recyclerView_down);

        initUp();
        initDown();
    }

    private void initUp() {
        //创建适配器
        adapterUp = new ViewAdapter(MainActivity.this);
        //添加数据,我们默认创建的时候是空的
        adapterUp.setMessage(listUp);
        recyclerView_up.setAdapter(adapterUp);
        //RecyclerView显示样式
        GridLayoutManager gridLayoutManagerUp = new GridLayoutManager(this, 3);
        recyclerView_up.setLayoutManager(gridLayoutManagerUp);
        //点击监听
        adapterUp.setMyItemOnClickListener(new ViewAdapter.MyItemOnClickListener() {
            @Override
            public void onClick(View view, int position) {
                Log.e("Up", "上" + position);

                //下面的RecyclerView添加当前点击的条目信息并刷新数组
                listDown.add(listUp.get(position));
                adapterDown.setMessage(listDown);
                adapterDown.notifyDataSetChanged();

                //从当前数组移除数据并刷新数组
                listUp.remove(position);
                adapterUp.setMessage(listUp);
                adapterUp.notifyDataSetChanged();
            }
        });
    }

    private void initDown() {
        adapterDown = new ViewAdapter(MainActivity.this);
        adapterDown.setMessage(listDown);
        recyclerView_down.setAdapter(adapterDown);
        GridLayoutManager gridLayoutManagerDown = new GridLayoutManager(this, 3);
        recyclerView_down.setLayoutManager(gridLayoutManagerDown);
        adapterDown.setMyItemOnClickListener(new ViewAdapter.MyItemOnClickListener() {
            @Override
            public void onClick(View view, int position) {
                Log.e("Down", "下" + position);


                //下面的RecyclerView添加当前点击的条目信息并刷新数组
                listUp.add(listDown.get(position));
                adapterUp.setMessage(listUp);
                adapterUp.notifyDataSetChanged();

                //从当前数组移除数据并刷新数组
                listDown.remove(position);
                adapterDown.setMessage(listDown);
                adapterDown.notifyDataSetChanged();
            }
        });
    }
}
