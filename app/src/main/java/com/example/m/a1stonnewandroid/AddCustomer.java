package com.example.m.a1stonnewandroid;

import android.content.ContentProviderClient;
import android.content.ContentValues;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.jar.Attributes;

public class AddCustomer extends AppCompatActivity {


    EditText mName;
    EditText mPhone_Number;
    EditText mLambai;
    EditText mBazu;
    EditText mTeera;
    EditText mChaati;
    EditText mGhaira;
    EditText mGala;
    EditText mShalwar;
    EditText mPancha;
    EditText mKaf;
    EditText mKandha;
    EditText mCustID;

    Button AddBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_customer);


        mCustID = findViewById(R.id.IDTvId);
        mName =findViewById(R.id.NameTvId);
        mPhone_Number = findViewById(R.id.PhoneTvId);
        mLambai = findViewById(R.id.LambaiTvId);
        mBazu =findViewById(R.id.BazuTvId);
        mTeera= findViewById(R.id.TeeraTvId);
        mChaati = findViewById(R.id.ChatiTvId);
        mGhaira = findViewById(R.id.GhairaTvId);
        mGala = findViewById(R.id.GalaTvId);
        mShalwar = findViewById(R.id.ShalwarTvId);
        mPancha = findViewById(R.id.PanchaTvId);
        mKaf = findViewById(R.id.KafTvId);
        mKandha = findViewById(R.id.KandhaTvId);



        AddBtn = findViewById(R.id.AddBtnId);
        AddBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*String customerName = String.valueOf(mName.getText());
                String phoneNum = String.valueOf(mPhone_Number.getText());
                String lambai = String.valueOf(mLambai.getText());
                String bazu = String.valueOf(mBazu.getText());
                String teera = String.valueOf(mTeera.getText());
                String chaati = String.valueOf(mChaati.getText());
                String ghaira = String.valueOf(mGhaira.getText());
                String gala = String.valueOf(mGala.getText());
                String shalwar = String.valueOf(mShalwar.getText());
                String pancha = String.valueOf(mPancha.getText());
                String kaf = String.valueOf(mKaf.getText());
                String kandha = String.valueOf(mKandha.getText());*/

                ContentValues customerContentValues = new ContentValues();
                customerContentValues.put("CustID",String.valueOf(mCustID.getText()));
                customerContentValues.put("Name",String.valueOf(mName.getText()));
                customerContentValues.put("Phone_Number",String.valueOf(mPhone_Number.getText()));
                customerContentValues.put("Lambai",String.valueOf(mLambai.getText()));
                customerContentValues.put("Bazu",String.valueOf(mBazu.getText()));
                customerContentValues.put("Teera",String.valueOf(mTeera.getText()));
                customerContentValues.put("Chaati",String.valueOf(mChaati.getText()));
                customerContentValues.put("Ghaira",String.valueOf(mGhaira.getText()));
                customerContentValues.put("Gala",String.valueOf(mGala.getText()));
                customerContentValues.put("Shalwar",String.valueOf(mShalwar.getText()));
                customerContentValues.put("Pancha",String.valueOf(mPancha.getText()));
                customerContentValues.put("Kaf",String.valueOf(mKaf.getText()));
                customerContentValues.put("Kandha",String.valueOf(mKandha.getText()));




                try{
                    /*MainActivity.zainStitchDb.execSQL("INSERT INTO Customer (CustID ,Name ,Phone_Number,Lambai,Bazu," +
                                    "Teera,Chaati,Ghaira,Gala,Shalwar,Pancha ,Kaf ,Kandha )" +
                                    " VALUES (id , customerName, phoneNum, lambai , bazu , teera ,chaati , ghaira, gala ,shalwar , pancha , kaf, kandha)");
*/
                    MainActivity.zainStitchDb.insert("Customer",null,customerContentValues);


                            /*execSQL("INSERT INTO Customer (Name ,Phone_Number,Lambai,Bazu," +
                            "Teera,Chaati,Ghaira,Gala,Shalwar,Pancha ,Kaf ,Kandha )" +
                            " VALUES ( 'Hamza', '03046282866', '5.7' , '5.7' , '5.7' ,'5.7' , '5.7', '5.7' ,'5.7' , '5.7' , '5.7', '5.7')");*/

                    Toast.makeText(AddCustomer.this, "Record Added Successfully!", Toast.LENGTH_SHORT).show();

                    MainActivity object = new MainActivity();
                    //object.LoadListViewItems(object.customerList);

                    Intent AddCustomerToMainActivity = new Intent(AddCustomer.this,MainActivity.class);
                    startActivity(AddCustomerToMainActivity);
//                    Phone_Number.getText(),Lambai.getText(),Bazu.getText(),Teera.getText(),Chaati.getText(),Ghaira.getText(),Gala.getText(),Shalwar.getText(),Pancha.getText(),Kaf.getText()
                }catch (Exception e){
                    Toast.makeText(AddCustomer.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

   /*--------------------------------------------------------------------------
   Mehtod to check Whether the user have enterted the values correctly or not
   ---------------------------------------------------------------------------*/
   public  boolean validateData(){
       if(mPhone_Number.getText().length() == 11
               && mName.getText().length() != 0
               && mBazu.getText().length() != 0
               && mBazu.getText().length() != 0
               && mBazu.getText().length() != 0
               && mBazu.getText().length() != 0
               && mBazu.getText().length() != 0
               && mBazu.getText().length() != 0
               && mBazu.getText().length() != 0
               && mBazu.getText().length() != 0
               && mBazu.getText().length() != 0
               && mChaati.getText().length()!= 0){
           return true;
       }

       return false;
   }
}
