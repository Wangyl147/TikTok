package com.example.tiktok;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    ViewPager2 vp;
    MyAdapter mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        vp = findViewById(R.id.viewPager);
        vp.setOrientation(ViewPager2.ORIENTATION_VERTICAL);
        mAdapter = new MyAdapter(this);
        vp.setAdapter(mAdapter);
        vp.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);
            }

            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                mAdapter.notifyItemChanged(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                super.onPageScrollStateChanged(state);
            }
        });

        getData();
    }

    private void getData(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://beiyou.bytedance.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiService apiService = retrofit.create(ApiService.class);

        //List<VideoItem> videos = new ArrayList<>();

        apiService.getVideos().enqueue(new Callback<List<VideoItem>>() {
            @Override
            public void onResponse(Call<List<VideoItem>> call, Response<List<VideoItem>> response) {
                if(response.body() != null){
                    List<VideoItem> videos = response.body();
                    Log.d("retrofit", response.body().toString());
                    if(videos.size()!=0){
                        mAdapter.setDataSet(videos);
                        mAdapter.notifyDataSetChanged();
                    }
                }
                else
                    Log.d("Retrofit", "no response");
            }

            @Override
            public void onFailure(Call<List<VideoItem>> call, Throwable t) {
                Log.d("retrofit", t.getMessage());
            }
        });

    }

}
