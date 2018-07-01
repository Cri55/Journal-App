package com.example.cristian.journalapp;
//
//the list adapter and setting the cursor data should all be done here

import android.app.Fragment;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class ListFragment extends Fragment  {
    private AppCompatActivity mclass;
    private static final String TAG = "ListFragment";
    DatabaseHelper mdatabaseHelper;
    private ListView mListView;
    View myview;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        myview = inflater.inflate(R.layout.list_fragment,container,false);
        return myview;
    }

    //find and set item after view is created


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mListView = (ListView)getView().findViewById(R.id.listview);
        mdatabaseHelper = new DatabaseHelper(getActivity());

        populateListView();
    }

    @Override
    public void onResume() {
        super.onResume();

    }

    //TODO POPULATELISTVIEW METHOD
    private void populateListView(){
        //get data from database and add to list
        Cursor data = mdatabaseHelper.getData();
        ArrayList<String> listjournal = new ArrayList<>();
        while(data.moveToNext()){
            listjournal.add(data.getString(1));
        }
        //list adapter for the entries
        ListAdapter adapter = new ArrayAdapter<>(getActivity(),android.R.layout.simple_list_item_1,listjournal);
        mListView.setAdapter(adapter);

        //set an onItemClickListener to the ListView
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String name = adapterView.getItemAtPosition(i).toString();
                Log.d(TAG, "onItemClick: You Clicked on " + name);

                Cursor data = mdatabaseHelper.getItemID(name);
                //get the id associated with that name
                int itemID = -1;
                while(data.moveToNext()){
                    itemID = data.getInt(0);
                }
                if(itemID > -1){
                    Log.d(TAG, "onItemClick: The ID is: " + itemID);
                    Intent editEntry = new Intent(getActivity(), EditEntry.class);
                    editEntry.putExtra("id",itemID);
                    editEntry.putExtra("name",name);
                    startActivity(editEntry);
                }
                else{
                }
            }
        });


}
}

