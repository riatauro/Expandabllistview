package com.example.user.horoscope;
import android.app.Activity;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.SearchView;

import java.util.ArrayList;

public class MainActivity extends Activity implements
        SearchView.OnQueryTextListener, SearchView.OnCloseListener{

    private SearchView search;
    private MyListAdapter listAdapter;
    private ExpandableListView myList;
    private ArrayList<Continent> continentList = new ArrayList<Continent>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        search = (SearchView) findViewById(R.id.search);
        search.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        search.setIconifiedByDefault(false);
        search.setOnQueryTextListener(this);
        search.setOnCloseListener(this);

        //display the list
        displayList();

        //expand all Groups
        expandAll();


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    //method to expand all groups
    private void expandAll() {
        int count = listAdapter.getGroupCount();
        for (int i = 0; i < count; i++){
            myList.expandGroup(i);
        }
    }

    //method to expand all groups
    private void displayList() {

        //display the list
        loadSomeData();

        //get reference to the ExpandableListView
        myList = (ExpandableListView) findViewById(R.id.expandableList);
        //create the adapter by passing your ArrayList data
        listAdapter = new MyListAdapter(MainActivity.this, continentList);
        //attach the adapter to the list
        myList.setAdapter(listAdapter);
        myList.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                if (groupPosition == 0) {


                    Intent intent;
                    intent = new Intent(getApplicationContext(), News.class);
                    Bundle b = new Bundle();
                    b.putInt("id", childPosition);
                    intent.putExtras(b);


                    startActivity(intent);
                }
                if (groupPosition == 1) {


                    Intent intent;
                    intent = new Intent(getApplicationContext(), Categories.class);
                    Bundle b = new Bundle();
                    b.putInt("id1", childPosition);
                    intent.putExtras(b);


                    startActivity(intent);
                }
                if(groupPosition==2)
                {
                    Intent intent=new Intent(getApplicationContext(),Another.class);
                    startActivity(intent);
                }
                return false;
            }
        });



    }

    private void loadSomeData() {

        ArrayList<Country> countryList = new ArrayList<Country>();
        Country country = new Country("CHINA");
        countryList.add(country);
        country = new Country("INDIA");
        countryList.add(country);
        country = new Country("JAPAN");
        countryList.add(country);
        country = new Country("PHILIPINES");
        countryList.add(country);
        country = new Country("RUSSIA");
        countryList.add(country);
        country = new Country("SINGAPORE");
        countryList.add(country);
        country = new Country("THAILAND");
        countryList.add(country);
        country = new Country("VIETNAM");
        countryList.add(country);

        Continent continent = new Continent("COUNTRIES",countryList);
        continentList.add(continent);

        countryList = new ArrayList<Country>();
        country = new Country("ARTS");
        countryList.add(country);
        country = new Country("BUISNESS");
        countryList.add(country);
        country = new Country("HEALTH");
        countryList.add(country);
        country = new Country("INFORMATION TECHNOLOGY");
        countryList.add(country);
        country = new Country("JOBS");
        countryList.add(country);
        country = new Country("MUSIC");
        countryList.add(country);
        country = new Country("POLITIC");
        countryList.add(country);
        country = new Country("TECHNOLOGY");
        countryList.add(country);

        country = new Country("SPORTS");
        countryList.add(country);


        continent = new Continent("CATEGORIES",countryList);
        continentList.add(continent);

        countryList = new ArrayList<Country>();
        country = new Country("HINDI ENTERTAINMENT");
        countryList.add(country);
        country = new Country("NEWS");
        countryList.add(country);
        country = new Country("ENGLISH ENTERTAINMENT");
        countryList.add(country);
        continent = new Continent("TV SHOWS",countryList);
        continentList.add(continent);




    }

    @Override
    public boolean onClose() {
        listAdapter.filterData("");
        expandAll();
        return false;
    }

    @Override
    public boolean onQueryTextChange(String query) {
        listAdapter.filterData(query);
        expandAll();
        return false;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        listAdapter.filterData(query);
        expandAll();
        return false;
    }
}