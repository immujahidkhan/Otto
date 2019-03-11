package com.madeincanada.otto;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.otto.Bus;
import com.squareup.otto.Produce;
import com.squareup.otto.Subscribe;
import com.squareup.otto.ThreadEnforcer;

public class SecondActivity extends AppCompatActivity {

    TextView text;
    public static Bus bus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        text = findViewById(R.id.text);
        bus = new Bus(ThreadEnforcer.MAIN);
    }

    @Subscribe
    public void getMessage(String message) {
        Toast.makeText(SecondActivity.this, message, Toast.LENGTH_SHORT).show();
    }

    @Produce
    public String produceEvent() {
        return "Starting up";
    }

    @Override
    protected void onStart() {
        super.onStart();
        bus.register(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        bus.unregister(this);
    }
}