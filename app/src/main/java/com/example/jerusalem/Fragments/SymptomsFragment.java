package com.example.cov19.Fragments;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.VideoView;

import com.example.cov19.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class SymptomsFragment extends Fragment {
    FirebaseFirestore db;
    VideoView covVideo;
    Uri videoUri ;
    ListView lessList , mostList , seriousList ;
  //  ProgressBar bufferProgress;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_symptoms, container, false);
         db = FirebaseFirestore.getInstance();

        covVideo = view.findViewById(R.id.covVideo);
       // bufferProgress = view.findViewById(R.id.bufferProgress);
        lessList = view.findViewById(R.id.less_symptoms_list);
        mostList = view.findViewById(R.id.most_symptoms_list);
        seriousList = view.findViewById(R.id.serious_symptoms_list);
        startVideo();

         readData();

        return view;
    }

    private void startVideo() {

        videoUri = Uri.parse("https://firebasestorage.googleapis.com/v0/b/cov-19-e6744.appspot.com/o/video%2Fcov_video.mp4?alt=media&token=619966b2-b4d3-4fa5-b6d1-5ea9e7e178e3");
        covVideo.setVideoURI(videoUri);
        covVideo.requestFocus();

        covVideo.setOnInfoListener(new MediaPlayer.OnInfoListener() {
            @Override
            public boolean onInfo(MediaPlayer mediaPlayer, int i, int i1) {

                if(i == mediaPlayer.MEDIA_INFO_BUFFERING_START){
    //                bufferProgress.setVisibility(View.VISIBLE);
                }else {
      //              bufferProgress.setVisibility(View.INVISIBLE);
                }
                return false;
            }
        });

        covVideo.start();
    }

    private void readData() {
        db.collection("symptoms")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Map<String,Object> data = document.getData();

                               String cov = (String) data.get("cov-19");
                               String d = (String) data.get("duration_of_illness");

                                List<String>  less_symptoms = (List<String>) data.get("less_symptoms");
                                List<String> serious_symptoms = (List<String>) data.get("serious_symptoms");
                                List<String>  most_symptoms = (List<String>) data.get("most_symptoms");

                                ArrayAdapter lessAdapter = new ArrayAdapter(getContext(), android.R.layout.simple_list_item_1,less_symptoms);
                                lessList.setAdapter(lessAdapter);

                                ArrayAdapter seriousAdapter = new ArrayAdapter(getContext(), android.R.layout.simple_list_item_1,serious_symptoms);
                                seriousList.setAdapter(seriousAdapter);

                                ArrayAdapter mostAdapter = new ArrayAdapter(getContext(), android.R.layout.simple_list_item_1,most_symptoms);
                                mostList.setAdapter(mostAdapter);

                                Log.d("symptoms", document.getId() + " => " + data.get("most_symptoms"));
                            }
                        } else {
                            Log.w("symptoms", "Error getting documents.", task.getException());
                        }
                    }
                });


    }
}
