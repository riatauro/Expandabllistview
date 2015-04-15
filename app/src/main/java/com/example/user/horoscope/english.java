package com.example.user.horoscope;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class english extends Activity implements
        AdapterView.OnItemClickListener {

    public static final String[] titles = {"AXN",
    "Comedy Central",
            "Star Movies",
            "Star World",
            "HBO",
            "Food Food",
            "WB",
            "World Movies",
            "VH1"
};


   /* public static final String[] descriptions = new String[] {
            "Countries in Asia",
            "Countries in Africa" };*/

    public static final Integer[] images ={R.drawable.i3,
            R.drawable.i7,
            R.drawable.i6,
            R.drawable.i4,
            R.drawable.i8,
            R.drawable.i9,
            R.drawable.i5,
            R.drawable.i2,
            R.drawable.i1
    };
    public static final Integer[] codes={890,888};

    ListView listView;
    List<MyAdapter1> rowItems;



    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_view);



        rowItems = new ArrayList<>();
        for (int i = 0; i < titles.length; i++) {
            MyAdapter1 item = new MyAdapter1(images[i], titles[i]);
            rowItems.add(item);
        }

        listView = (ListView) findViewById(R.id.list);
        CustomListViewAdapter adapter = new CustomListViewAdapter(this,
                R.layout.row_layout, rowItems);



        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);



    }




    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        showCustomDialog(position);


    }

    protected void showCustomDialog(final int position) {
        // TODO Auto-generated method stub
        final Dialog dialog = new Dialog(english.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.customdialog);

        final EditText editText = (EditText)dialog.findViewById(R.id.editText1);
        Button button = (Button)dialog.findViewById(R.id.button1);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                // TODO Auto-generated method stub


                EditText edit=(EditText)dialog.findViewById(R.id.editText1);
                String text=edit.getText().toString();
                int i=Integer.parseInt(text);
                Intent intent;
                intent = new Intent(getApplicationContext(),Shows1.class);
                intent.putExtra("pos",position);
                intent.putExtra("Tag",i);
                startActivity(intent);
            }


        });






        dialog.show();

    }


}
