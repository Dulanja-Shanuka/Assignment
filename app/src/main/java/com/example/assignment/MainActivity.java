package com.example.assignment;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.InputFilter;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    EditText textCardName,textCardNumber,textMonth,textYear,textCcv;
    RadioButton radioButton1,radioButton2, radioButton;
    RadioGroup radioGroup;
    Button buttonSave;



    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        textCardName=findViewById(R.id.edit_card_name);
        textCardNumber=findViewById(R.id.edit_card_number);
        textMonth=findViewById(R.id.edit_month);
        textYear=findViewById(R.id.edit_year);
        textCcv=findViewById(R.id.text_ccv);
        radioButton1=findViewById(R.id.radio_checkout);
        radioButton2=findViewById(R.id.radio_payhere);
        radioGroup=findViewById(R.id.radio_group);
        buttonSave=findViewById(R.id.button_save);




        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertData();


            }
        });



    }

    private void insertData() {
        String cname=textCardName.getText().toString().trim();
        String cnumber=textCardNumber.getText().toString().trim();
        String cmonth=textMonth.getText().toString().trim();
        String cyear=textYear.getText().toString().trim();
        String cccv=textCcv.getText().toString().trim();

        if(cname.isEmpty()){
            Toast.makeText(this, "Enter name on card", Toast.LENGTH_SHORT).show();
            return;
        }
        else if(cnumber.isEmpty()){
            Toast.makeText(this,"Enter card number",Toast.LENGTH_SHORT).show();
            return;
        }
        else if(cmonth.isEmpty()){
            Toast.makeText(this,"Enter month",Toast.LENGTH_SHORT);
            return;
        }
        else if(cyear.isEmpty()){
            Toast.makeText(this,"Enter year",Toast.LENGTH_SHORT);
            return;
        }
        else if(cccv.isEmpty()){
            Toast.makeText(this,"Enter CCV",Toast.LENGTH_SHORT).show();

            return;
        }
        else
            {
                StringRequest request=new StringRequest(Request.Method.POST, "https://url.php",
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                if (response.equalsIgnoreCase("Data inserted")) {
                                    Toast.makeText(MainActivity.this, "Data inserted", Toast.LENGTH_SHORT).show();
                                }
                                else
                                    {
                                        Toast.makeText(MainActivity.this,response,Toast.LENGTH_SHORT).show();
                                    }



                            }
                        },
                        new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity.this,error.getMessage(),Toast.LENGTH_SHORT).show();

                    }
                }
                ){
                    @Nullable
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String ,String > params= new HashMap<String ,String>();
                        params.put("cname",cname);
                        params.put("cnumber",cnumber);
                        params.put("cmonth",cmonth);
                        params.put("cyear",cyear);
                        params.put("cccv",cccv);



                        return super.getParams();
                    }
                };


                RequestQueue requestQueue= Volley.newRequestQueue(MainActivity.this);
                requestQueue.add(request);
            }




    }
    public  void checkButton(View v){
        int radioId=radioGroup.getCheckedRadioButtonId();
        radioButton=findViewById(radioId);
        Toast.makeText(this,"Selected Radio button"+radioButton.getText(),Toast.LENGTH_SHORT).show();
    }



    /*public void validation(View v){

        EditText cccv = new EditText(this);
        int maxLength = 3;
        cccv.setFilters(new InputFilter[] {new InputFilter.LengthFilter(maxLength)});

    }*/
}