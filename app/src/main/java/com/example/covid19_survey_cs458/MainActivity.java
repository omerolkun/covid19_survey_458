package com.example.covid19_survey_cs458;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {
    //variables (inputs of the app)
    EditText input_name_surname;
    EditText input_birth_date;
    Spinner input_city;


    String str_input_name_surname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        input_name_surname = (EditText) findViewById(R.id.input_name_surname);


    }
    //check total length of name
    private String check_len(String name){
        String result = "";
        if (name.length() >25){
            return "The name and surname is too long!\n";
        }
        if (name.length() < 4){
            return "The name and surname is too short!\n";
        }
        return result;
    }

    //check names and surname separately
    private Boolean valid_names_length(List<String> names){
        //Boolean result = true;
        int size_list = names.size();
        for (int i = 0; i < size_list - 1; ++i){
            if (names.get(i).length() < 3){
                return false;
            }
        }
        return true;
    }

    //check regex for name and surname
    private Boolean check_regex(List <String> names){
        Pattern p = Pattern.compile("[a-zA-Z]*");
        int size_names = names.size();
        Matcher m;
        Boolean result ;
        for (int i = 0; i< size_names;i++){
            m = p.matcher(names.get(i));
            result = m.matches();
            if (result == false){
                return false;
            }
        }

        return true;
    }
    //check whether surname exists
    private Boolean missing_surname(String s){
        if (s.contains(" ")== false){
            return true;
        }
        else{
            return false;
        }

    }




    public void createAlertDialog(View v) {
        AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
        alertDialog.setTitle("Alert");
        //variables and their display result
        str_input_name_surname = input_name_surname.getText().toString();
        //To List to check names and surname separately
        List<String> myList = new ArrayList<String>(Arrays.asList(str_input_name_surname.split(" ")));
        Boolean bool_names_length = valid_names_length(myList);

        //check if the name input contains all letters
        Boolean bool_regex_name_surname = check_regex(myList);
        //variable to check missing surname
        Boolean bool_missing_surname = missing_surname(str_input_name_surname);

        //Alert message after the button is clicked
        String result_message = "";
        //Alert message after the button is clicked

        //first check length and add its message to result message
        result_message += check_len(str_input_name_surname);
        //second check is if input has blank (this means there is a surname)
        if (bool_regex_name_surname == false){
            result_message += "Name surname must contain only letters\n";
        }
        //third check is missing surname
        if(bool_missing_surname == true){
            result_message += "You need to write surname too!";
        }






        alertDialog.setMessage(result_message);
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        alertDialog.show();
    }


}