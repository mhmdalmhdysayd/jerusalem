package com.example.cov19.Ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.example.cov19.Adapters.SliderAdapter;
import com.example.cov19.R;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ViewPager mSlideViewPager;
    LinearLayout mDotlayout;

    SliderAdapter sliderAdapter;

    TextView[] mDots;

    Button mNextBtn , btn_skip;
    Button mBackBtn;

    int mCurrentPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       // LottieAnimationView lottieAnimationView = findViewById(R.id.anamtin);

        AssetManager assetManager = getAssets();
        List<String> files = null;
        try {
            files = Arrays.asList(assetManager.list(""));
            ArrayList<String> path = new ArrayList<>();
            path.add(files.get(2));
            path.add(files.get(3));
            path.add(files.get(4));
            sliderAdapter = new SliderAdapter(this , path);
            //   lottieAnimationView.setAnimation(files.get(2));

                Log.d("AssetFiles" , files.get(4));

        } catch (IOException e) {
            e.printStackTrace();
        }

        mSlideViewPager = findViewById(R.id.slidViewPager);
        mDotlayout = findViewById(R.id.dotsLayout);

        mSlideViewPager.setAdapter(sliderAdapter);

        mNextBtn = findViewById(R.id.nextBtn);
        mBackBtn = findViewById(R.id.prevBtn);
        btn_skip = findViewById(R.id.btn_skip);


        addDotsIndicator(0);

        mSlideViewPager.addOnPageChangeListener(viewListener);


        btn_skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this , LoginActivity.class);
                startActivity(intent);
            }
        });

        mNextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mSlideViewPager.setCurrentItem(mCurrentPage + 1);

            }
        });

        mBackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mSlideViewPager.setCurrentItem(mCurrentPage - 1);
            }
        });

    }

    public  void addDotsIndicator(int position){

        mDots = new TextView[3];
        mDotlayout.removeAllViews();
        for (int i = 0; i < mDots.length ; i++) {
            mDots[i] = new TextView(this);
            mDots[i].setText(Html.fromHtml("&#8226;"));
            mDots[i].setTextSize(35);
            mDots[i].setTextColor(getResources().getColor(R.color.colorTransparenWhite));
            mDotlayout.addView(mDots[i]);
        }
        if(mDots.length > 0){
            mDots[position].setTextColor(getResources().getColor(R.color.colorPrimary));
        }
    }

    ViewPager.OnPageChangeListener viewListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            addDotsIndicator(position);
            mCurrentPage = position;

            if(position == 0){
                mNextBtn.setEnabled(true);
                mBackBtn.setEnabled(false);
                mBackBtn.setVisibility(View.INVISIBLE);

                mNextBtn.setText("Next");
                mBackBtn.setText("");


            } else if(position == mDots.length - 1){
                mNextBtn.setEnabled(true);
                mBackBtn.setEnabled(true);
                mBackBtn.setVisibility(View.VISIBLE);

                mNextBtn.setText("Finish");
                mBackBtn.setText("Back");
            }else{
                mNextBtn.setEnabled(true);
                mBackBtn.setEnabled(true);
                mBackBtn.setVisibility(View.VISIBLE);

                mNextBtn.setText("Next");
                mBackBtn.setText("Back");

            }

            if(position == mDots.length - 1){

                mNextBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(mNextBtn.getText().equals("Finish")){
                            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                            startActivity(intent);
                        }
                    }
                });
            }else {
                mNextBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mSlideViewPager.setCurrentItem(mCurrentPage + 1);

                    }
                });

            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };
}
