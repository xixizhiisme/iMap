package com.linzhi.imap;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class SecondFragment extends Fragment {
    TextView score_A;
    TextView score_B;

    public SecondFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView= inflater.inflate(R.layout.fragment_second, container, false);
        score_A = (TextView) rootView.findViewById(R.id.score_A);
        score_B = (TextView) rootView.findViewById(R.id.score_B);
        return rootView;
    }
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        String scorea=score_A.getText().toString();
        String scoreb=score_B.getText().toString();
        outState.putString("score_A",scorea);
        outState.putString("score_B",scoreb);
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Button button1=getActivity().findViewById(R.id.btn_score_1_A);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(v.getId()==R.id.btn_score_1_A){
                    String oldScore = (String) score_A.getText();
                    int newScore = Integer.parseInt(oldScore) + 1;
                    score_A.setText(""+newScore);
                }
            }
        });
        Button button12=getActivity().findViewById(R.id.btn_score_1_B);
        button12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(v.getId()==R.id.btn_score_1_B){
                    String oldScore = (String) score_B.getText();
                    int newScore = Integer.parseInt(oldScore) + 1;
                    score_B.setText(""+newScore);
                }
            }
        });
        Button button2=getActivity().findViewById(R.id.btn_score_2_A);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(v.getId()==R.id.btn_score_2_A){
                    String oldScore = (String) score_A.getText();
                    int newScore = Integer.parseInt(oldScore) + 2;
                    score_A.setText(""+newScore);
                }
            }
        });
        Button button22=getActivity().findViewById(R.id.btn_score_2_B);
        button22.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(v.getId()==R.id.btn_score_2_B){
                    String oldScore = (String) score_B.getText();
                    int newScore = Integer.parseInt(oldScore) + 2;
                    score_B.setText(""+newScore);
                }
            }
        });
        Button button3=getActivity().findViewById(R.id.btn_score_3_A);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(v.getId()==R.id.btn_score_3_A){
                    String oldScore = (String) score_A.getText();
                    int newScore = Integer.parseInt(oldScore) + 3;
                    score_A.setText(""+newScore);
                }
            }
        });
        Button button32=getActivity().findViewById(R.id.btn_score_3_B);
        button32.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(v.getId()==R.id.btn_score_3_B){
                    String oldScore = (String) score_B.getText();
                    int newScore = Integer.parseInt(oldScore) + 3;
                    score_B.setText(""+newScore);
                }
            }
        });
        Button button4=getActivity().findViewById(R.id.btn_reset);
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(v.getId()==R.id.btn_reset){
                    score_A.setText("0");
                    score_B.setText("0");
                }
            }
        });
    }
}
