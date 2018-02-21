package com.example.sc1111.finastrahack;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LandingPageActivity extends NavigationActivity {
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    String loanInfoValue;
    TextView balance;
    TextView header;
    Button btn_pay;
    DatabaseReference myRef;
    String name="";
    Double loanBalance = 0.0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing_page);
        setView();
        btn_pay = findViewById(R.id.btn_pay);
        myRef = database.getReference("loanInfor");

        btn_pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                myRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        loanInfoValue = (String) dataSnapshot.getValue(String.class);
                        String[] string_list = loanInfoValue.split(",");
                        name = string_list[0];
                        loanBalance = Double.parseDouble(string_list[1]);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
                loanBalance = (double)((double)loanBalance - 100);
                Log.i("balanceYO", loanBalance.toString());
                Log.i("balanceYO", name);
                if(name!=null && loanBalance>0){
                    myRef.setValue(name+","+loanBalance.toString());
                }
            }
        });





        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                loanInfoValue = (String) dataSnapshot.getValue(String.class);
                String[] string_list = loanInfoValue.split(",");
                balance = findViewById(R.id.bal);
                header = findViewById(R.id.welcome);
                header.setText("Welcome, "+string_list[0]);
                balance.setText("$"+Double.parseDouble(string_list[1]));
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("tag", "Failed to read value.", error.toException());
            }
        });
    }

    //TODO: better implementation
    private String getName(){
        return "John Smith";
    }

    //TODO: better implementation
    private String getLoanAmt(){
        float loanamt = 40000;
        return "$"+ String.valueOf(loanamt);
    }
}
