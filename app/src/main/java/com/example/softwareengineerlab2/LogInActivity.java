package com.example.softwareengineerlab2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class LogInActivity extends AppCompatActivity {
    public Button loginButton;
    public EditText loginname, loginPass;
    public boolean isSucess = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        loginname = (EditText) findViewById(R.id.login_name);
        loginPass = (EditText) findViewById(R.id.login_password);
        loginButton = (Button) findViewById(R.id.loginButton);


        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AuthUser authUser = new AuthUser();
                authUser.execute("");
            }
        });
    }

    public class AuthUser extends AsyncTask<String, String, String> {
        String userName = loginname.getText().toString().toLowerCase();
        String userPass = loginPass.getText().toString();
        String Address;
        String loggedUserName;
        String retMessage;
        ProgressDialog loading;

        protected void onPreExecute() {
            loading = ProgressDialog.show(LogInActivity.this, "Authenticating...", null, true, true);
        }

        protected void onPostExecute(String retMessage) {
            loading.dismiss();
            if (isSucess == true) {
                SQLiteDBHelper sqLiteDBHelper = new SQLiteDBHelper(getApplicationContext());
                SQLiteDatabase sqLiteDatabase = sqLiteDBHelper.getWritableDatabase();
                sqLiteDBHelper.addAccount( userPass, userName, Address, sqLiteDatabase);
                new userInfo(userPass, userName, Address);
                sqLiteDBHelper.close();


                Intent intent = new Intent(getApplicationContext(), BankAccount.class);
                startActivity(intent);
            } else {
                Toast.makeText(getApplicationContext(), "" + retMessage, Toast.LENGTH_LONG).show();
            }
        }

        protected String doInBackground(String... params) {
            Connection con = DBConnect.getConnection();
            if (con == null) {
                retMessage = "Unable To Connect To The Server 1";
            } else {
                String loginQuery = "SELECT * FROM bank.customer WHERE Id='" + userPass + "' and name ='" + userName + "'";
                try {
                    Statement stmt = con.createStatement();
                    ResultSet rs = stmt.executeQuery(loginQuery);
                    if (rs.next()) {
                        retMessage = "Login Sucessfull";
                        isSucess = true;
                        Address = rs.getString("Address");
                        //userFullName = rs.getString("userFullName");
                    } else {
                        loginQuery = "SELECT * FROM bank.customer WHERE Id='" + userPass + "' and name ='" + userName + "'";
                        stmt = con.createStatement();
                        rs = stmt.executeQuery(loginQuery);
                        if (rs.next()) {
                            retMessage = "Login Sucessful";
                            loggedUserName = rs.getString("userName");
                            Address = rs.getString("Address");
                            isSucess = true;
                        } else {
                            retMessage = "Invalid Email or Password";
                        }
                    }
                    con.close();
                } catch (Exception ex) {
                    retMessage = "Something is Wrong in the database";
                }
            }

            return retMessage;
        }
    }
}
