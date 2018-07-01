package com.example.cristian.journalapp;
//Copyright 2015 Ribot Ltd.
//
//        Licensed under the Apache License, Version 2.0 (the "License");
//        you may not use this file except in compliance with the License.
//        You may obtain a copy of the License at
//
//        http://www.apache.org/licenses/LICENSE-2.0
//
//        Unless required by applicable law or agreed to in writing, software
//        distributed under the License is distributed on an "AS IS" BASIS,
//        WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
//        See the License for the specific language governing permissions and
//        limitations under the License.
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomArrayAdapter extends ArrayAdapter<CustomList> {

    private static final String LOG_TAG = CustomArrayAdapter.class.getSimpleName();


    public CustomArrayAdapter(Activity context, ArrayList<CustomList> name) {

        super(context, 0, name);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if the existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }

        CustomList currentName = getItem(position);


        TextView nameTextView = (TextView) listItemView.findViewById(R.id.entry);

        nameTextView.setText(currentName.getVersionName());

        ImageButton iconView = (ImageButton) listItemView.findViewById(R.id.btnSave);

        iconView.setImageResource(currentName.getImageResourceId());

        ImageButton delete = (ImageButton) listItemView.findViewById(R.id.btnDelete);

        delete.setImageResource(currentName.getImageResourceId());

        return listItemView;
    }
}
