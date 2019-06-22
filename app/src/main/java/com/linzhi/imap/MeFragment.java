package com.linzhi.imap;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class MeFragment extends Fragment {


    public MeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView=inflater.inflate(R.layout.fragment_me, container, false);

        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Button button1=getActivity().findViewById(R.id.me_money);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity().getApplicationContext(),MeMoneyActivity.class);
                startActivity(intent);
                /*if (v.getId()==R.id.me_money){
                    Intent intent=new Intent(getActivity().getApplicationContext(),MeMoneyActivity.class);
                    startActivity(intent);
                }*/
            }
        });
        Button button2=getActivity().findViewById(R.id.me_photo);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity().getApplicationContext(),MePhotoActivity.class);
                startActivity(intent);
            }
        });
        Button button3=getActivity().findViewById(R.id.me_card);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity().getApplicationContext(),MeCardActivity.class);
                startActivity(intent);
            }
        });
        Button button4=getActivity().findViewById(R.id.me_setting);
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity().getApplicationContext(),MeSettingActivity.class);
                startActivity(intent);
            }
        });
    }

}
