package com.example.administrator.downloadjsondata;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private Handler mHandler;
    private RecyclerView mRecyclerView;
    private MovieAdapter mAdapter;
    private List<Movie> mList=new ArrayList<>();
    private int number;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mHandler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                if(msg.what == 1){
                    String result = msg.obj.toString();
                    if(result != null && result.length() > 0){
                        parseJsonData(result);
                        mAdapter = new MovieAdapter();
                        mRecyclerView.setAdapter(mAdapter);
                    }

                }
            }
        };

        DownloadTask task = new DownloadTask();
        task.setHandler(mHandler);
        task.execute();



        mRecyclerView=(RecyclerView)findViewById(R.id.id_recyclerview);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));     //设置布局管理器
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this,
                DividerItemDecoration.VERTICAL_LIST));
/*        mAdapter =new MovieAdapter();
        mRecyclerView.setAdapter(mAdapter);*/     //设置adapter
        Log.v("Test","after start sub thread!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");




    }

    private  void parseJsonData(String data) {

        try{
            JSONObject jsonBody=new JSONObject(data);
            JSONObject dataJsonObject=jsonBody.getJSONObject("data");
            JSONArray comingJsonArray=dataJsonObject.getJSONArray("coming");


            for(int i=0;i<comingJsonArray.length();i++){
                JSONObject MovieJsonObject= comingJsonArray.getJSONObject(i);

                Movie movie=new Movie();
                movie.setBoxInfo(MovieJsonObject.getString("boxInfo"));
                movie.setCat(MovieJsonObject.getString("cat"));
                movie.setDir(MovieJsonObject.getString("dir"));
                movie.setId(MovieJsonObject.getInt("id"));
                movie.setNm(MovieJsonObject.getString("nm"));
                movie.setPubDesc(MovieJsonObject.getString("pubDesc"));
                movie.setScm(MovieJsonObject.getString("scm"));
                movie.setDesc(MovieJsonObject.getString("desc"));
                movie.setImg(MovieJsonObject.getString("img"));
                mList.add(movie);
            }

        }catch(Exception e){

        }


    }

    private class MovieAdapter extends RecyclerView.Adapter<MovieHolder>{


        @Override
        public MovieHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            MovieHolder holder = new  MovieHolder(LayoutInflater.from(
                    MainActivity.this).inflate(R.layout.movie_layout, parent,
                    false));
            return holder;
        }

        @Override
        public void onBindViewHolder(MovieHolder holder, int position) {
            holder.mMovieName.setText(mList.get(position).getNm());
            holder.mMovieDirector.setText("导演："+mList.get(position).getDir());
            holder.mMovieLeader.setText(mList.get(position).getDesc());
            holder.mMovieTime.setText(mList.get(position).getPubDesc());

        }

        @Override
        public int getItemCount() {
            return mList.size();

        }
    }

    private class  MovieHolder extends RecyclerView.ViewHolder implements  View.OnClickListener{
        private TextView mMovieName;
        private TextView mMovieDirector;
        private TextView mMovieLeader;
        private TextView mMovieTime;
        private LinearLayout mLinearlayout;

        public MovieHolder(View view){
            super(view);

            mMovieName=view.findViewById(R.id.movie_name);
            mMovieDirector= view.findViewById(R.id.movie_director);
            mMovieLeader= view.findViewById(R.id.movie_leader);
            mMovieTime=view.findViewById(R.id.movie_time);
            mLinearlayout=view.findViewById(R.id.movie_layout);
            mLinearlayout.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            number=getAdapterPosition();
            String image=mList.get(number).getImg();
            String name=mList.get(number).getNm();
            String scm=mList.get(number).getScm();
            String cat=mList.get(number).getCat();
            String time=mList.get(number).getPubDesc();


            Intent i=DetailActivity.newIntent(MainActivity.this,image,name,scm,cat,time);
            startActivity(i);
        }
    }
}

