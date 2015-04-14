package com.example.user.horoscope;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;

public class SingleActivity extends Activity implements
        AdapterView.OnItemClickListener {

    TextView t1,t2;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.single);
       Intent intent=getIntent();

     String text =intent.getStringExtra("TAG");
        t1=(TextView)findViewById(R.id.text1);
        t1.setText(text);

        t2=(TextView)findViewById(R.id.text2);


    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }
}

