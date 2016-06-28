package com.metroinfrasys.ottoapp;

import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.otto.Bus;
import com.squareup.otto.Produce;
import com.squareup.otto.Subscribe;
import com.squareup.otto.ThreadEnforcer;

public class MainActivity extends AppCompatActivity {

    public static Bus bus;
    private Button button;
    private TextView textView;
    private String getDate = "Wrong Date";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().add(R.id.container, new PlaceholderFragment()).commit();
        }


        button = (Button) findViewById(R.id.SendDatefromActivity);
        textView = (TextView) findViewById(R.id.date_format);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TestData t = new TestData();
                t.dateupdate = textView.getText().toString();
                bus.post(t);
            }
        });

        bus = new Bus(ThreadEnforcer.MAIN);
        bus.register(this);
    }



    @Subscribe
    public void updateDateFromActivit(String s){

        if (textView == null){
            throw  new NullPointerException("Null Pointer Exception.");
        }
        textView.setText(s);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {

            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public class TestData {
        public String message;
        public String dateupdate;
    }



    public static class PlaceholderFragment extends Fragment {

        private TextView text;

        public PlaceholderFragment() {
        }

        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_layout, container, false);
            AppCompatButton button = (AppCompatButton) rootView.findViewById(R.id.fragmentbutton);
            text = (TextView) rootView.findViewById(R.id.dateFormat);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String getDate = text.getText().toString();
                    bus.post(getDate);

                }
            });

            bus.register(this);
            return rootView;
        }


        @Subscribe
        public void updateDateFromActivit(MainActivity.TestData testData){

            if (text == null){
                throw  new NullPointerException("Null Pointer Exception.");
            }
            text.setText(testData.dateupdate);
        }

    }

/*
    @Produce
    public String produceEvent() {
        return "Starting up";
    }*/


}
