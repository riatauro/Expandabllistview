package com.example.user.horoscope;

import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class  News extends ListActivity{

    private ProgressDialog pDialog;


    private static String url;



    // JSON Node names
    private  static final  String TAG_ARTICLES="articles";
    private static final String TAG_PUBLISH_DATE = "publish_date";
    private static final String TAG_SOURCE = "source";
    private static final String TAG_SUMMARY = "summary";
    private static final String TAG_TITLE = "title";

    // contacts JSONArray
    JSONArray articles = null;

    // Hashmap for ListView
    ArrayList<HashMap<String, String>> articleList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        // URL to get contacts JSON
        Bundle b=this.getIntent().getExtras();
        int value=b.getInt("id");
        if(value==0) {

            url = "http://api.feedzilla.com/v1/categories/19/subcategories/888/articles.json?count=10";
        }
        else if(value==1)
        {
            url="http://api.feedzilla.com/v1/categories/19/subcategories/890/articles.json?count=10";

        }
        else if(value==2)
        {
            url="http://api.feedzilla.com/v1/categories/19/subcategories/892/articles.json?count=10";

        }
        else if(value==3)
        {
            url="http://api.feedzilla.com/v1/categories/19/subcategories/901/articles.json?count=10";

        }
        else if(value==4)
        {
            url="http://api.feedzilla.com/v1/categories/19/subcategories/902/articles.json?count=10";

        }
        else if(value==5)
        {
            url="http://api.feedzilla.com/v1/categories/19/subcategories/903/articles.json?count=10";

        }
        else if(value==6)
        {
            url="http://api.feedzilla.com/v1/categories/19/subcategories/906/articles.json?count=10";
        }
        else if(value==7)
        {
            url="http://api.feedzilla.com/v1/categories/19/subcategories/907/articles.json?count=10";
        }




        super.onCreate(savedInstanceState);
        setContentView(R.layout.news);
        TextView t1=(TextView)findViewById(R.id.text1);
        if(value==0) {

            t1.setText("CHINA");
        }
        else if(value==1)
        {
            t1.setText("INDIA");
        }
        else if(value==2)
        {
            t1.setText("JAPAN");
        }
        else if(value==3)
        {
            t1.setText("PHILIPPINES");
        }
        else if(value==4)
        {
            t1.setText("RUSSIA");

        }
        else if(value==5)
        {
            t1.setText("SINGAPORE");

        }
        else if(value==6)
        {
            t1.setText("THAILAND");
        }
        else if(value==7) {
            t1.setText("VIETNAM");
        }


        articleList = new ArrayList<HashMap<String, String>>();

        ListView lv = getListView();


        // Listview on item click listener
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // getting values from selected ListItem
             //   String date = ((TextView) view.findViewById(R.id.publish_date))
               //         .getText().toString();
                //String source = ((TextView) view.findViewById(R.id.src))
                    //    .getText().toString();
                String desc = ((TextView) view.findViewById(R.id.summary))
                        .getText().toString();
                //String ttle = ((TextView) view.findViewById(R.id.title))
                    //    .getText().toString();


                // Starting single contact activity
                Intent in = new Intent(getApplicationContext(),SingleActivity.class);
             //   in.putExtra(TAG_PUBLISH_DATE, date);
             //   in.putExtra(TAG_SOURCE, source);
                in.putExtra("TAG", desc);

              //  in.putExtra(TAG_TITLE,ttle);
                startActivity(in);

            }
        });


        // Calling async task to get json
        new GetContacts().execute();

    }

//    @Override
//    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//        String desc = ((TextView) view.findViewById(R.id.summary))
//                        .getText().toString();
//
//        Intent intent=new Intent(this,SingleActivity.class);
//        intent.putExtra("TAG_SUMMARY",desc);
//        startActivity(intent);
//
//    }


    /**
     * Async task class to get json by making HTTP call
     * */
    private class GetContacts extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Showing progress dialog
            pDialog = new ProgressDialog(News.this);
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
                        String email = c.getString(TAG_SUMMARY);
                        String ttle = c.getString(TAG_TITLE);

                        HashMap<String, String> contact = new HashMap<String, String>();

                        // adding each child node to HashMap key => value
                        contact.put(TAG_PUBLISH_DATE, id);
                        contact.put(TAG_SOURCE, name);
                        contact.put(TAG_SUMMARY, email);
                        contact.put(TAG_TITLE,ttle);

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
                    News.this, articleList,
                    R.layout.row, new String[] { TAG_PUBLISH_DATE, TAG_SOURCE,
                    TAG_SUMMARY }, new int[] { R.id.publish_date,
                    R.id.src, R.id.summary });

            setListAdapter(adapter);
        }

    }


}