package com.example.user.horoscope;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.ParseException;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

public class Shows2 extends Activity {

    ArrayList<Actors> actorsList;

    String url;

    ActorAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        Intent intent=getIntent();
        int pos=intent.getIntExtra("pos",0);
        int text=intent.getIntExtra("Tag",0);
        if(pos==0) {
            url = "http://indian-television-guide.appspot.com/indian_television_guide?channel=colors&date=" + text;
        }
        if(pos==1)
        {
            url = "http://indian-television-guide.appspot.com/indian_television_guide?channel=sony-tv-hd&date=" + text;
        }
        if(pos==2)
        {
            url = "http://indian-television-guide.appspot.com/indian_television_guide?channel=zee-tv&date=" + text;
        }
        if(pos==3)
        {
            url = "http://indian-television-guide.appspot.com/indian_television_guide?channel=star-plus-hd&date=" + text;
        }
        if(pos==4)
        {
            url = "http://indian-television-guide.appspot.com/indian_television_guide?channel=life-ok&date=" + text;
        }
        if(pos==5)
        {
            url = "http://indian-television-guide.appspot.com/indian_television_guide?channel=sony-max&date=" + text;
        }
        if(pos==6)
        {
            url = "http://indian-television-guide.appspot.com/indian_television_guide?channel=zee-cinema&date=" + text;
        }
        if(pos==7)
        {
            url = "http://indian-television-guide.appspot.com/indian_television_guide?channel=9xm&date=" + text;
        }
        if(pos==8)
        {
            url = "http://indian-television-guide.appspot.com/indian_television_guide?channel=channel-[v]&date=" + text;
        }
        if(pos==9)
        {
            url = "http://indian-television-guide.appspot.com/indian_television_guide?channel=mtv&date=" + text;
        }


        actorsList = new ArrayList<Actors>();
        new JSONAsyncTask().execute(url);

        ListView listview = (ListView)findViewById(R.id.list);
        adapter = new ActorAdapter(getApplicationContext(), R.layout.row1, actorsList);

        listview.setAdapter(adapter);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position,
                                    long id) {
                // TODO Auto-generated method stub
                Toast.makeText(getApplicationContext(), actorsList.get(position).getName(), Toast.LENGTH_LONG).show();
            }
        });
    }


    class JSONAsyncTask extends AsyncTask<String, Void, Boolean> {

        ProgressDialog dialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog = new ProgressDialog(Shows2.this);
            dialog.setMessage("Loading, please wait");
            dialog.show();
            dialog.setCancelable(false);
        }

        @Override
        protected Boolean doInBackground(String... urls) {
            try {

                //------------------>>
                HttpGet httppost = new HttpGet(urls[0]);
                HttpClient httpclient = new DefaultHttpClient();
                HttpResponse response = httpclient.execute(httppost);

                // StatusLine stat = response.getStatusLine();
                int status = response.getStatusLine().getStatusCode();

                if (status == 200) {
                    HttpEntity entity = response.getEntity();
                    String data = EntityUtils.toString(entity);


                    JSONObject jsono = new JSONObject(data);
                    JSONArray jarray = jsono.getJSONArray("listOfShows");

                    for (int i = 0; i < jarray.length(); i++) {
                        JSONObject object = jarray.getJSONObject(i);

                        Actors actor = new Actors();

                        actor.setName(object.getString("showTitle"));
                        actor.setDescription(object.getString("showTime"));

                        actor.setImage(object.getString("showThumb"));

                        actorsList.add(actor);
                    }
                    return true;
                }

                //------------------>>

            } catch (ParseException e1) {
                e1.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return false;
        }

        protected void onPostExecute(Boolean result) {
            dialog.cancel();
            adapter.notifyDataSetChanged();
            if(result == false)
                Toast.makeText(getApplicationContext(), "Unable to fetch data from server", Toast.LENGTH_LONG).show();

        }
    }






}

