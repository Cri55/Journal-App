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
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Entry extends AppCompatActivity {
    EditText entry;
    com.tabian.saveanddisplaysql.DatabaseHelper mdatabaseHelper;
    Button save;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entry);
        //declaration
        entry = (EditText) findViewById(R.id.entry);
        save =(Button)findViewById(R.id.save);
        mdatabaseHelper = new com.tabian.saveanddisplaysql.DatabaseHelper(this);

        save.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                String newEntry = entry.getText().toString();
                if(entry.length()!=0){
                    AddData(newEntry);
                    entry.setText("");
//                Intent showlist = new Intent(Entry.this, ListFragment.class);
//                startActivity(showlist);
                }else{
                    toastMessage("type something first");
                }

            }
        });
    }

    public void AddData(String newEntry){
        boolean insertData = mdatabaseHelper.addData(newEntry);
        if(insertData){
            toastMessage("Data Successfully Inserted");
        }   else{
            toastMessage("Something went wrong");
        }
    }
    private void toastMessage(String message){
        Toast.makeText(this,message, Toast.LENGTH_SHORT).show();
    }

}
