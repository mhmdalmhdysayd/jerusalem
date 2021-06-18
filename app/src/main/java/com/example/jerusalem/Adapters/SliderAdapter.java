package com.example.cov19.Adapters;

import android.content.Context;
import android.content.res.AssetManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.airbnb.lottie.LottieAnimationView;
import com.airbnb.lottie.RenderMode;
import com.example.cov19.R;

import java.util.ArrayList;
import java.util.List;

public class SliderAdapter extends PagerAdapter {

    Context context;
    LayoutInflater layoutInflater;
    ArrayList<String> path;

    public SliderAdapter(Context context, LayoutInflater layoutInflater) {
        this.context = context;
        this.layoutInflater = layoutInflater;
    }
    public SliderAdapter(Context context , ArrayList<String> path) {
        this.context = context;
        this.path = path;
    }

    int[] slide_image = {

            R.drawable.appco, R.drawable.appculold , R.drawable.appculoud
    };

    String[] slide_headings = {
            "Cleaning Hands","Wear Your Mask","Social Spacing"
    };
    String[] slideDescs = {
            " Wash your hands thoroughly immediately \n after entering the home to prevent the transmission of infection","Wear a muzzle to reduce transmission","Avoid shaking hands with others \n to prevent cross infection"
    };


    @Override
    public int getCount() {
        return slide_headings.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.slide_layout, container ,false);
       // ImageView slideImageView = view.findViewById(R.id.slide_image);
        LottieAnimationView lottieAnimationView = view.findViewById(R.id.anamtinSlider);
        TextView slideHeading = view.findViewById(R.id.slide_heading);
       // TextView slideDesc = view.findViewById(R.id.slide_desc);
      //  slideImageView.setImageResource(slide_image[position]);
        lottieAnimationView.setAnimation(path.get(position));
        slideHeading.setText(slide_headings[position]);
      //  slideDesc.setText(slideDescs[position]);
        container.addView(view);
        return view;

    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
