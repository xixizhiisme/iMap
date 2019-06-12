package com.linzhi.imap;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private Fragment mFragments[];
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;

    private TextView mTextMessage;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            fragmentTransaction=fragmentManager.beginTransaction().hide(mFragments[0]).hide(mFragments[1]).hide(mFragments[2]);
            switch (item.getItemId()) {
                case R.id.navigation_map:
                    fragmentTransaction.show(mFragments[0]).commit();
                    return true;
                case R.id.navigation_func:
                    fragmentTransaction.show(mFragments[1]).commit();
                    return true;
                case R.id.navigation_me:
                    fragmentTransaction.show(mFragments[2]).commit();
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        mFragments=new Fragment[3];
        fragmentManager=getSupportFragmentManager();
        mFragments[0]=fragmentManager.findFragmentById(com.linzhi.imap.R.id.fragment_map);
        mFragments[1]=fragmentManager.findFragmentById(com.linzhi.imap.R.id.fragment_func);
        mFragments[2]=fragmentManager.findFragmentById(com.linzhi.imap.R.id.fragment_me);
        fragmentTransaction=fragmentManager.beginTransaction().hide(mFragments[0]).hide(mFragments[1]).hide(mFragments[2]);
        fragmentTransaction.show(mFragments[0]).commit();
    }

}
