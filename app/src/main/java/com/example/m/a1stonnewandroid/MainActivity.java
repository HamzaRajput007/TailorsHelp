package com.example.m.a1stonnewandroid;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
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
import android.widget.Toolbar;


public class MainActivity extends AppCompatActivity {

    ContentValues customerContentValues = new ContentValues();
    android.support.v7.widget.Toolbar toolbar ;
    LayoutInflater controlInflater = null;
    ListView listView;
    Button searchButton;
    ArrayList<String> customerList = new ArrayList<String>();
    Button addCustomerBtn;
    EditText searchCustomerET;
    String onlyId;
    static SQLiteDatabase zainStitchDb;

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.addCustomerBtnId){

            Intent mainToAddCustomer = new Intent(MainActivity.this,AddCustomer.class);
            startActivity(mainToAddCustomer);

        }
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_add_customer,menu);
        return true;
    }

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        /* ------------------------
          Initialzing the ToolBar
        ------------------------*/
        toolbar = findViewById(R.id.toolBarId);
        this.setSupportActionBar(toolbar);


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
                    String[] selectArgs = new String[1];
                    selectArgs[0]= "1111";
                    String selectCommand = "SELECT * FROM Customer WHERE CustId = ?";
                    Cursor cursor = zainStitchDb.rawQuery(selectCommand,selectArgs);

                    Toast.makeText(MainActivity.this, String.valueOf(cursor.getCount()), Toast.LENGTH_SHORT).show();

                    cursor.moveToFirst();
                    int idIndex = cursor.getColumnIndex("CustID");
                    int nameIndex = cursor.getColumnIndex("Name");

                    String id = cursor.getString(idIndex);
                    String name = cursor.getString(nameIndex);

                    Toast.makeText(MainActivity.this, id + "    " + name, Toast.LENGTH_LONG).show();
                    Intent main2Show = new Intent(MainActivity.this,ShowRecords.class);
                } catch (Exception e) {
                    Toast.makeText(MainActivity.this, "Exception Search " + e.getMessage(), Toast.LENGTH_SHORT).show();
                }


                /*try {
                    Cursor c = zainStitchDb.rawQuery("SELECT * from Customer WHERE Name = 'hamza'", null);
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
                    Toast.makeText(MainActivity.this, "Exception While Selection" + e.getMessage(), Toast.LENGTH_SHORT).show();
                }*/
                if(zainStitchDb.equals(null))
                {
                    ContentValues customerContentValues = new ContentValues();
                    customerContentValues.put("Name","Demo");
                    customerContentValues.put("Phone_Number","0001");
                    customerContentValues.put("Lambai","0001");
                    customerContentValues.put("Bazu","0001");
                    customerContentValues.put("Teera","0001");
                    customerContentValues.put("Chaati","0001");
                    customerContentValues.put("Ghaira","0001");
                    customerContentValues.put("Gala","0001");
                    customerContentValues.put("Shalwar","0001");
                    customerContentValues.put("Pancha","0001");
                    customerContentValues.put("Kaf","0001");
                    customerContentValues.put("Kandha","0001");

                    try{
                        MainActivity.zainStitchDb.insert("Customer",null,customerContentValues);
                        Toast.makeText(MainActivity.this, "Record Added Successfully!", Toast.LENGTH_SHORT).show();
                    }catch (Exception e){
                        Toast.makeText(MainActivity.this, "Exception adding 1st Record!", Toast.LENGTH_SHORT).show();
                    }
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

                String[] recordId = getRecordId(Id);
                onlyId = recordId[0];
                Intent mainToShowRecord = new Intent(MainActivity.this,ShowRecords.class);

                mainToShowRecord.putExtra("ID",onlyId);
                startActivity(mainToShowRecord);


                //Toast.makeText(MainActivity.this, "Item" + String.valueOf(position + 1) + "Clicked", Toast.LENGTH_SHORT).show();
            }
        });

        //ListView LongClick Listner working as to delete Record
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, final long l) {

                try {
                    new AlertDialog.Builder(MainActivity.this)
                            .setIcon(android.R.drawable.ic_menu_delete)
                            .setTitle("DELETE CUSTOMER")
                            .setMessage("Are You Sure You Want to DELETE Customer : " + String.valueOf(i+1))
                            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                @RequiresApi(api = Build.VERSION_CODES.N)
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    //customerList.clear();
                                    /*zainStitchDb.execSQL( "DELETE FROM Customer WHERE Name = 'Nasir'");*/


                                    String[] JustId = getRecordId(l);

                                    try {
                                        int deleted = zainStitchDb.delete("Customer", "CustID = ?" , JustId);
                                        selectDbRecords();
                                        LoadListViewItems(customerList);
                                        if (deleted != 0){
                                            Toast.makeText(MainActivity.this, "Record Deleted Successfully", Toast.LENGTH_SHORT).show();
                                        }
                                        else
                                        {
                                            Toast.makeText(MainActivity.this, "Unable to Delete " , Toast.LENGTH_SHORT).show();
                                        }
                                    } catch (Exception e) {
                                        Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                                    }


                                   // Cursor UserRecord = getRecordById(Integer.valueOf(CropedName[0]));

                                   // Toast.makeText(MainActivity.this, "" , Toast.LENGTH_SHORT).show();
                                    //zainStitchDb.delete("Customer", "Where ",null);
                                   /* selectDbRecords();
                                    LoadListViewItems(customerList);*/
                                }
                            })
                            .setNegativeButton("No",null)
                            .show();


                }catch (Exception e)
                {
                    Toast.makeText(MainActivity.this, "Delete Exception : " + e.getMessage(), Toast.LENGTH_SHORT).show();
                }

                return false;
            }
        });


        /* ------------------------
          Creation of Database
        ------------------------*/
        try{
            zainStitchDb =MainActivity.this.openOrCreateDatabase("TailerDb",MODE_PRIVATE,null);
            zainStitchDb.execSQL("CREATE TABLE IF NOT EXISTS Customer (CustID INTEGER(4) PRIMARY KEY ," +
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
        Check whether the db is not
        empty . if so then add a demo
        record into it.
        ------------------------*/
        if(zainStitchDb.equals(null))
        {
            ContentValues customerContentValues = new ContentValues();
            customerContentValues.put("Name","Demo");
            customerContentValues.put("Phone_Number","0001");
            customerContentValues.put("Lambai","0001");
            customerContentValues.put("Bazu","0001");
            customerContentValues.put("Teera","0001");
            customerContentValues.put("Chaati","0001");
            customerContentValues.put("Ghaira","0001");
            customerContentValues.put("Gala","0001");
            customerContentValues.put("Shalwar","0001");
            customerContentValues.put("Pancha","0001");
            customerContentValues.put("Kaf","0001");
            customerContentValues.put("Kandha","0001");

            try{
                MainActivity.zainStitchDb.insert("Customer",null,customerContentValues);
                Toast.makeText(MainActivity.this, "Record Added Successfully!", Toast.LENGTH_SHORT).show();
            }catch (Exception e){
                Toast.makeText(this, "Exception adding 1st Record!", Toast.LENGTH_SHORT).show();
            }

            String[] JustId = new String[1] ;
            JustId[0] = "null";

            try {
                int deleted = zainStitchDb.delete("Customer", "CustID = ?" , null);
                selectDbRecords();
                LoadListViewItems(customerList);
                if (deleted != 0){
                    Toast.makeText(MainActivity.this, "Records Deleted Successfully", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(MainActivity.this, "Unable to Delete ", Toast.LENGTH_SHORT).show();
                }
            } catch (Exception e) {
                Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }

        }
        /* ------------------------
        Function for Selecting
        Records from DataBase And
        Iterating Through them
        ------------------------*/
            selectDbRecords();
        /* ------------------------
        Function for Loading
        Selected DbRecords
        To ListView
        ------------------------*/
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
        Function for Loading
        Selected DbRecords
        To ListView
        ------------------------*/
    public void LoadListViewItems(ArrayList<String> customerList1){
        try {

            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item,customerList);
            Toast.makeText(this, Integer.toString(customerList1.size()), Toast.LENGTH_SHORT).show();
            listView.setAdapter(arrayAdapter);
        }catch (NullPointerException e){
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
    /* ------------------------
           Function for Selecting
           Records from DataBase And
           Iterating Through them
           ------------------------*/
    public void selectDbRecords(){
        try {
            Cursor c = zainStitchDb.rawQuery("SELECT * from Customer", null);
            Toast.makeText(this, "Cursor Records = "+String.valueOf(c.getCount()), Toast.LENGTH_SHORT).show();
            int idIndex = c.getColumnIndex("CustID");
            int nameIndex = c.getColumnIndex("Name");

            customerList.clear();
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
        }catch (Exception e){
            Toast.makeText(this, "Selection Fialed : " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
    public String[] getRecordId(Long l){

        String tempCust ;
        tempCust = customerList.get((int) (long) l);
        String [] CropedName = tempCust.split("     ");
        String[] JustId = new String[1] ;
        JustId[0] = CropedName[0];
        return JustId;
    }

}
