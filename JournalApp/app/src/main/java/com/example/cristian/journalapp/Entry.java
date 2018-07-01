package com.example.cristian.journalapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Entry extends AppCompatActivity {
    EditText entry;
    DatabaseHelper mdatabaseHelper;
    Button save;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entry);
        //declaration
        entry = (EditText) findViewById(R.id.entry);
        save =(Button)findViewById(R.id.save);
        mdatabaseHelper = new DatabaseHelper(this);

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
