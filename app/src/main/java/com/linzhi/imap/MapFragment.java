package com.linzhi.imap;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.baidu.mapapi.SDKInitializer;

import com.baidu.mapapi.map.MapView;



/**
 * A simple {@link Fragment} subclass.
 */
public class MapFragment extends Fragment {
    private MapView mapView=null;

    public MapFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        SDKInitializer.initialize(getActivity().getApplicationContext());
        View rootView= inflater.inflate(R.layout.fragment_map, container, false);
        mapView=rootView.findViewById(R.id.mapTextView);
        return rootView;
    }

    public void onResume(){
        super.onResume();
        mapView.onResume();
    }
    public void onPause(){
        super.onPause();
        mapView.onPause();
    }
    public void onDestroy(){
        super.onDestroy();
        mapView.onDestroy();
    }

}
