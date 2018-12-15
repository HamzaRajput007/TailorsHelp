package com.example.m.a1stonnewandroid;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.net.CacheRequest;
import java.util.ArrayList;
import java.util.List;
import android.view.ViewGroup.LayoutParams;


public class MainActivity extends AppCompatActivity {

    LayoutInflater controlInflater = null;
    ListView listView;
    Button searchButton;
    ArrayList<String> customerList = new ArrayList<String>();
    Button addCustomerBtn;
    EditText searchCustomerET;
    static SQLiteDatabase zainStitchDb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

         /* ------------------------
           Inflating the OverRelayed
           Add Customer button
           Layout
        ------------------------*/
        controlInflater = LayoutInflater.from(getBaseContext());
        View viewControl = controlInflater.inflate(R.layout.add, null);
        LayoutParams layoutParamsControl
                = new LayoutParams(LayoutParams.FILL_PARENT,
                LayoutParams.FILL_PARENT);
        this.addContentView(viewControl, layoutParamsControl);

        /* ------------------------
            Finding Views
        --------------------------*/
        searchCustomerET = findViewById(R.id.searchCustomerId);
        listView = findViewById(R.id.customersLisrView);
        searchButton  = findViewById(R.id.SearchBtn);
        addCustomerBtn = findViewById(R.id.btnAddCustomer);


         /* ------------------------
               OnClickEvents
        ------------------------*/

        //Search Button OnClick Listner
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Cursor c = zainStitchDb.rawQuery("SELECT * from Customer WHERE Name = '23'", null);
                    ArrayList<String> customerList = new ArrayList<String>();
                    int idIndex = c.getColumnIndex("CustID");
                    int nameIndex = c.getColumnIndex("Name");

                    c.moveToFirst();
                    while (c != null) {
                        Customer customer = new Customer();
                        customer.CustID = c.getString(idIndex);
                        customer.Name = c.getString(nameIndex);
                        String combinedIdAndName = customer.CustID + "           " + customer.Name;
                        customerList.add(combinedIdAndName);
                        c.moveToNext();
                        if (c.isLast()) {
                            c.getPosition();
                            break;
                        }
//                Toast.makeText(this, Integer.toString(c.getCount()), Toast.LENGTH_SHORT).show();
                    }

                    LoadListViewItems(customerList);

                }catch (Exception e){
                    Toast.makeText(MainActivity.this, "Exception While Selection", Toast.LENGTH_SHORT).show();
                }
            }
        });

        //Add Customer Button Click Listner
        addCustomerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mainToAddCustomer = new Intent(MainActivity.this,AddCustomer.class);
                startActivity(mainToAddCustomer);
            }
        });

        //ListView Click Listner
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long Id) {
                Toast.makeText(MainActivity.this, "Item" + String.valueOf(position) + "Clicked", Toast.LENGTH_SHORT).show();
            }
        });
        /* ------------------------
               Here
        WE Create Our DataBase
        ------------------------*/
        try{
            zainStitchDb =MainActivity.this.openOrCreateDatabase("TailerDb",MODE_PRIVATE,null);
            zainStitchDb.execSQL("CREATE TABLE IF NOT EXISTS Customer (CustID INTEGER PRIMARY KEY," +
                    " Name TEXT," +
                    "Phone_Number TEXT," +
                    " Lambai TEXT," +
                    " Bazu TEXT," +
                    "Teera TEXT," +
                    " Chaati TEXT, " +
                    "Ghaira TEXT, " +
                    "Gala TEXT, " +
                    "Shalwar TEXT, " +
                    "Pancha TEXT, " +
                    "Kaf TEXT, " +
                    "Kandha TEXT)");


        /* ------------------------
        Selecting Records from DataBase
        And Iterating Through them
        ------------------------*/
                Cursor c = zainStitchDb.rawQuery("SELECT * from Customer", null);
                int idIndex = c.getColumnIndex("CustID");
                int nameIndex = c.getColumnIndex("Name");

                c.moveToFirst();
                while (c != null) {
                    Customer customer = new Customer();
                    customer.CustID = c.getString(idIndex);
                    customer.Name = c.getString(nameIndex);
                    String combinedIdAndName = customer.CustID + "           " + customer.Name;
                    customerList.add(combinedIdAndName);
                    c.moveToNext();
                    if (c.isLast()) {
                        c.getPosition();
                        break;
                    }
//                Toast.makeText(this, Integer.toString(c.getCount()), Toast.LENGTH_SHORT).show();
                }



                LoadListViewItems(customerList);

        }catch (Exception e){
            Toast.makeText(this, "Exception Creating the Database!", Toast.LENGTH_SHORT).show();
        }

        /* ------------------------
               Here
        WE Initialize Our ListView
        ------------------------*/


    }

    /* ------------------------
     Funciton for initializin
     our listview
     ------------------------*/
    public void LoadListViewItems(ArrayList<String> customerList){
        try {

            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item,customerList);
            Toast.makeText(this, Integer.toString(customerList.size()), Toast.LENGTH_SHORT).show();
            listView.setAdapter(arrayAdapter);
        }catch (NullPointerException e){
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

}
