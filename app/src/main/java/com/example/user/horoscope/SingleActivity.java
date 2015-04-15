package com.example.user.horoscope;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ShareActionProvider;
import android.widget.TextView;

public class SingleActivity extends Activity implements
        AdapterView.OnItemClickListener {

    TextView t1,t2;
    String text;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.single);
       Intent intent=getIntent();

      text =intent.getStringExtra("TAG");
        t1=(TextView)findViewById(R.id.text1);
        t1.setText(text);

        t2=(TextView)findViewById(R.id.text2);


    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        MenuItem shareitem=(MenuItem)menu.findItem(R.id.action_share);
        ShareActionProvider mshare=(ShareActionProvider)shareitem.getActionProvider();
        Intent mshareIntent=new Intent(Intent.ACTION_SEND);
        mshareIntent.setAction(Intent.ACTION_SEND);
        mshareIntent.setType("text/plain");
        mshareIntent.putExtra(Intent.EXTRA_TEXT,text);
        mshare.setShareIntent(mshareIntent);
        return true;
    }

}

