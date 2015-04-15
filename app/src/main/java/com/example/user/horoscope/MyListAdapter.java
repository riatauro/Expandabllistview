package com.example.user.horoscope;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class MyListAdapter extends BaseExpandableListAdapter {

    private Context context;
    private ArrayList<Continent> continentList;
    private ArrayList<Continent> originalList;

    public MyListAdapter(Context context, ArrayList<Continent> continentList) {
        this.context = context;
        this.continentList = new ArrayList<Continent>();
        this.continentList.addAll(continentList);
        this.originalList = new ArrayList<Continent>();
        this.originalList.addAll(continentList);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        ArrayList<Country> countryList = continentList.get(groupPosition).getCountryList();
        return countryList.get(childPosition);
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild,
                             View view, ViewGroup parent) {

        Country country = (Country) getChild(groupPosition, childPosition);
        if (view == null) {
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(R.layout.chiled_row, null);
        }


        if (groupPosition == 0) {

            if (childPosition == 0) {
                ImageView image1 = (ImageView) view.findViewById(R.id.image2);
                image1.setImageResource(R.drawable.china);
            }
            if (childPosition == 1) {
                ImageView image1 = (ImageView) view.findViewById(R.id.image2);
                image1.setImageResource(R.drawable.india);
            }
            if (childPosition == 2) {
                ImageView image1 = (ImageView) view.findViewById(R.id.image2);
                image1.setImageResource(R.drawable.japan);
            }
            if (childPosition == 3) {
                ImageView image1 = (ImageView) view.findViewById(R.id.image2);
                image1.setImageResource(R.drawable.philippines);
            }
            if (childPosition == 4) {
                ImageView image1 = (ImageView) view.findViewById(R.id.image2);
                image1.setImageResource(R.drawable.russia);
            }
            if (childPosition == 5) {
                ImageView image1 = (ImageView) view.findViewById(R.id.image2);
                image1.setImageResource(R.drawable.singapore);
            }
            if (childPosition == 6) {
                ImageView image1 = (ImageView) view.findViewById(R.id.image2);
                image1.setImageResource(R.drawable.thailand);
            }
            if (childPosition == 7) {
                ImageView image1 = (ImageView) view.findViewById(R.id.image2);
                image1.setImageResource(R.drawable.vietnam);
            }


        }
        if (groupPosition == 1) {
            if (childPosition == 0) {
                ImageView image1 = (ImageView) view.findViewById(R.id.image2);
                image1.setImageResource(R.drawable.art1);
            }
            if (childPosition == 1) {
                ImageView image1 = (ImageView) view.findViewById(R.id.image2);
                image1.setImageResource(R.drawable.buisness);
            }
            if (childPosition == 2) {
                ImageView image1 = (ImageView) view.findViewById(R.id.image2);
                image1.setImageResource(R.drawable.health);
            }
            if (childPosition == 3) {
                ImageView image1 = (ImageView) view.findViewById(R.id.image2);
                image1.setImageResource(R.drawable.it);
            }
            if (childPosition == 4) {
                ImageView image1 = (ImageView) view.findViewById(R.id.image2);
                image1.setImageResource(R.drawable.jobs);
            }
            if (childPosition == 5) {
                ImageView image1 = (ImageView) view.findViewById(R.id.image2);
                image1.setImageResource(R.drawable.music);
            }
            if (childPosition == 6) {
                ImageView image1 = (ImageView) view.findViewById(R.id.image2);
                image1.setImageResource(R.drawable.politic);
            }
            if (childPosition == 7) {
                ImageView image1 = (ImageView) view.findViewById(R.id.image2);
                image1.setImageResource(R.drawable.tech);
            }
            if (childPosition == 8) {
                ImageView image1 = (ImageView) view.findViewById(R.id.image2);
                image1.setImageResource(R.drawable.sport);
            }
        }
        if(groupPosition==2)
        {
            ImageView image1 = (ImageView) view.findViewById(R.id.image2);
            image1.setImageResource(R.drawable.screen);
        }

            TextView name = (TextView) view.findViewById(R.id.name);


            name.setText(country.getName().trim());


        return view;
    }


    @Override
    public int getChildrenCount(int groupPosition) {

        ArrayList<Country> countryList = continentList.get(groupPosition).getCountryList();
        return countryList.size();

    }

    @Override
    public Object getGroup(int groupPosition) {
        return continentList.get(groupPosition);
    }

    @Override
    public int getGroupCount() {
        return continentList.size();
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isLastChild, View view,
                             ViewGroup parent) {

        Continent continent = (Continent) getGroup(groupPosition);
        if (view == null) {
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(R.layout.child_group, null);
        }


        if(groupPosition==0)
        {
            ImageView image1=(ImageView)view.findViewById(R.id.image1);
            image1.setImageResource(R.drawable.asia);
        }
        if(groupPosition==1)
        {
            ImageView image1=(ImageView)view.findViewById(R.id.image1);
            image1.setImageResource(R.drawable.cat);

        }
        if(groupPosition==2)
        {
            ImageView image1=(ImageView)view.findViewById(R.id.image1);
            image1.setImageResource(R.drawable.screen);

        }
        TextView heading = (TextView) view.findViewById(R.id.heading);
        heading.setText(continent.getName().trim());

        return view;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    public void filterData(String query){

        query = query.toLowerCase();
        Log.v("MyListAdapter", String.valueOf(continentList.size()));
        continentList.clear();

        if(query.isEmpty()){
            continentList.addAll(originalList);
        }
        else {

            for(Continent continent: originalList){

                ArrayList<Country> countryList = continent.getCountryList();
                ArrayList<Country> newList = new ArrayList<Country>();
                for(Country country: countryList){
                    if(country.getName().toLowerCase().contains(query) ||
                            country.getName().toLowerCase().contains(query)){
                        newList.add(country);
                    }
                }
                if(newList.size() > 0){
                    Continent nContinent = new Continent(continent.getName(),newList);
                    continentList.add(nContinent);
                }
            }
        }

        Log.v("MyListAdapter", String.valueOf(continentList.size()));
        notifyDataSetChanged();

    }

}