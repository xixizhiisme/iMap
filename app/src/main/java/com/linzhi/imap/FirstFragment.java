package com.linzhi.imap;


import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Vibrator;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import static android.content.Context.SENSOR_SERVICE;
import static android.content.Context.VIBRATOR_SERVICE;



/**
 * A simple {@link Fragment} subclass.
 */
public class FirstFragment extends Fragment {
    private SensorManager sensorManager;
    private Vibrator vibrator;
    private static String strs[]={"石头","剪刀","布"};
    private static int pics[]={R.mipmap.shitou,R.mipmap.jiandao,R.mipmap.hand};
    private static final int SENSOR_SHAKE=15;
    private TextView text;
    private ImageView img;

    public FirstFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView= inflater.inflate(R.layout.fragment_first, container, false);
        text=rootView.findViewById(R.id.txtlabel);
        img=rootView.findViewById(R.id.imageView);
        sensorManager= (SensorManager) getActivity().getSystemService(SENSOR_SERVICE);
        vibrator= (Vibrator) getActivity().getSystemService(VIBRATOR_SERVICE);
        return rootView;
    }
    @Override
    public void onResume() {
        super.onResume();
        if (sensorManager!=null){
            sensorManager.registerListener(sensorEventListener,sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),SensorManager.SENSOR_DELAY_NORMAL);
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        if (sensorManager!=null){
            sensorManager.unregisterListener(sensorEventListener);
        }
    }

    //重力感应监听
    private SensorEventListener sensorEventListener=new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent event) {
            //传感器信息改变时使用该方法
            float[] values=event.values;
            float x=values[0];
            float y=values[1];
            float z=values[2];
            int medumValue=15;
            if (Math.abs(x)>medumValue||Math.abs(y)>medumValue||Math.abs(z)>medumValue){
                vibrator.vibrate(200);
                Message msg=new Message();
                msg.what=SENSOR_SHAKE;
                handler.sendMessage(msg);
            }
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {

        }
    };
    Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 15:
                    java.util.Random r=new java.util.Random();
                    int num=Math.abs(r.nextInt())%3;
                    text.setText(strs[num]);
                    img.setImageResource(pics[num]);
                    break;
            }
        }
    };
}
