package com.linzhi.imap;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.baidu.mapapi.SDKInitializer;


/**
 * A simple {@link Fragment} subclass.
 */
public class FuncFragment extends Fragment {


    public FuncFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        SDKInitializer.initialize(getActivity().getApplicationContext());
        View rootView= inflater.inflate(R.layout.fragment_func, container, false);
        ViewPager viewPager=rootView.findViewById(R.id.viewpager);
        MyPageAdapter pageAdapter=new MyPageAdapter(getFragmentManager());
        viewPager.setAdapter(pageAdapter);

        TabLayout tabLayout=rootView.findViewById(R.id.sliding_tabs);
        tabLayout.setupWithViewPager(viewPager);
        return rootView;
    }

}
