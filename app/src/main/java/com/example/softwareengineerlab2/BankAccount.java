package com.example.softwareengineerlab2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class BankAccount extends AppCompatActivity {
    public Button loginButton;
    public TextView Amount, Name;
    public boolean isSucess = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (UserInfo.checkUserExistance(UserInfo.USER_NAME, UserInfo.USER_PASS) != true) {
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
        }
        setContentView(R.layout.activity_bank_account);
       Name = (TextView) findViewById(R.id.Name);
        Amount = (TextView) findViewById(R.id.Amount);
        loginButton = (Button) findViewById(R.id.GetData);

Name.setText("Welcome " + UserInfo.USER_NAME + " to your Bank Account! ");
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fetchItem();
            }
        });
    }

    public void fetchItem() {
        try {

            String amount = Amount.getText().toString();
            Connection conn = DBConnect.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs, rs1;
            String Query = "SELECT * FROM  bank.account WHERE Customer_id=" + UserInfo.USER_PASS;
            rs = stmt.executeQuery(Query);
            if (!rs.next()) {
                onBackPressed();
            } else {

                Amount.setText("Account balance "+"" + rs.getString("amount"));

            }
            conn.close();
        } catch (Exception ex) {
            Toast.makeText(this, "" + ex.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}