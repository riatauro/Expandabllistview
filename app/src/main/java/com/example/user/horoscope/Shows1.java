package com.example.user.horoscope;

import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by User on 4/14/2015.
 */
public class Shows1 extends ListActivity{

    private ProgressDialog pDialog;


    private static String url;



    // JSON Node names
    private  static final  String TAG_ARTICLES="listOfShows";
    private static final String TAG_PUBLISH_DATE = "showTitle";
    private static final String TAG_SOURCE = "showTime";
//    private static final String TAG_SUMMARY = "summary";
//    private static final String TAG_TITLE = "title";

    // contacts JSONArray
    JSONArray articles = null;

    // Hashmap for ListView
    ArrayList<HashMap<String, String>> articleList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        // URL to get contacts JSON






        super.onCreate(savedInstanceState);
        setContentView(R.layout.news);
        Intent intent=getIntent();
        int pos=intent.getIntExtra("pos",0);
        int text=intent.getIntExtra("Tag",0);
        if(pos==0) {
            url = "http://indian-television-guide.appspot.com/indian_television_guide?channel=axn&date=" + text;
        }
        if(pos==1)
        {
            url = "http://indian-television-guide.appspot.com/indian_television_guide?channel=comedy-central&date=" + text;
        }
        if(pos==2)
        {
            url = "http://indian-television-guide.appspot.com/indian_television_guide?channel=star-movies&date=" + text;
        }
        if(pos==3)
        {
            url = "http://indian-television-guide.appspot.com/indian_television_guide?channel=star-world&date=" + text;
        }
        if(pos==4)
        {
            url = "http://indian-television-guide.appspot.com/indian_television_guide?channel=hbo&date=" + text;
        }
        if(pos==5)
        {
            url = "http://indian-television-guide.appspot.com/indian_television_guide?channel=food-food&date=" + text;
        }
        if(pos==6)
        {
            url = "http://indian-television-guide.appspot.com/indian_television_guide?channel=wb&date=" + text;
        }
        if(pos==7)
        {
            url = "http://indian-television-guide.appspot.com/indian_television_guide?channel=world-movies&date=" + text;
        }
        if(pos==8)
        {
            url = "http://indian-television-guide.appspot.com/indian_television_guide?channel=vh1&date=" + text;
        }





        articleList = new ArrayList<HashMap<String, String>>();

        ListView lv = getListView();

        // Listview on item click listener
/*        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // getting values from selected ListItem
                String date = ((TextView) view.findViewById(R.id.publish_date))
                        .getText().toString();
                String source = ((TextView) view.findViewById(R.id.src))
                        .getText().toString();
                String desc = ((TextView) view.findViewById(R.id.summary))
                        .getText().toString();
                String ttle = ((TextView) view.findViewById(R.id.title))
                        .getText().toString();


                // Starting single contact activity
                Intent in = new Intent(getApplicationContext(),SingleActivity.class);
              //  in.putExtra(TAG_PUBLISH_DATE, date);
             //   in.putExtra(TAG_SOURCE, source);
                in.putExtra(TAG_SUMMARY, desc);
                in.putExtra(TAG_TITLE,ttle);
                startActivity(in);

            }
        });*/

        // Calling async task to get json
        new GetContacts().execute();

    }








    /**
     * Async task class to get json by making HTTP call
     * */
    private class GetContacts extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Showing progress dialog
            pDialog = new ProgressDialog(Shows1.this);
            pDialog.setMessage("Please wait...");
            pDialog.setCancelable(false);
            pDialog.show();

        }

        @Override
        protected Void doInBackground(Void... arg0) {
            // Creating service handler class instance
            ServiceHandler sh = new ServiceHandler();

            // Making a request to url and getting response
            String jsonStr = sh.makeServiceCall(url, ServiceHandler.GET);

            Log.d("Response: ", "> " + jsonStr);

            if (jsonStr != null) {
                try {
                    JSONObject jsonObj = new JSONObject(jsonStr);

                    // Getting JSON Array node
                    articles = jsonObj.getJSONArray(TAG_ARTICLES);

                    // looping through All Contacts
                    for (int i = 0; i < articles.length(); i++) {
                        JSONObject c = articles.getJSONObject(i);

                        String id = c.getString(TAG_PUBLISH_DATE);
                        String name = c.getString(TAG_SOURCE);
//                        String email = c.getString(TAG_SUMMARY);
//                        String ttle = c.getString(TAG_TITLE);

                        HashMap<String, String> contact = new HashMap<String, String>();

                        // adding each child node to HashMap key => value
                        contact.put(TAG_PUBLISH_DATE, id);
                        contact.put(TAG_SOURCE, name);
//                        contact.put(TAG_SUMMARY, email);
//                        contact.put(TAG_TITLE,ttle);

                        // adding contact to contact list
                        articleList.add(contact);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } else {
                Log.e("ServiceHandler", "Couldn't get any data from the url");
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            // Dismiss the progress dialog
            if (pDialog.isShowing())
                pDialog.dismiss();
            /**
             * Updating parsed JSON data into ListView
             * */
            ListAdapter adapter = new SimpleAdapter(
                    Shows1.this, articleList,
                    R.layout.row, new String[] { TAG_PUBLISH_DATE, TAG_SOURCE,
            }, new int[] { R.id.publish_date,
                    R.id.src });

            setListAdapter(adapter);
        }

    }



}
