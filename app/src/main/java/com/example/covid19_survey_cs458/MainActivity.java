package com.example.covid19_survey_cs458;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
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
    Spinner s;
    RadioGroup input_gender_group;
    RadioButton input_gender;

    String[] arraySpinner = new String[] {
            "Select City...", "ADANA","ADIYAMAN","AFYONKARAHİSAR","AĞRI","AMASYA","ANKARA",
            "ANTALYA","ARTVİN","AYDIN","BALIKESİR","BİLECİK","BİNGÖL","BİTLİS","BOLU","BURDUR",
            "BURSA","ÇANAKKALE","ÇANKIRI","ÇORUM","DENİZLİ","DİYARBAKIR","EDİRNE","ELAZIĞ",
            "ERZİNCAN","ERZURUM","ESKİŞEHİR","GAZİANTEP","GİRESUN","GÜMÜŞHANE","HAKKARİ",
            "HATAY","ISPARTA","MERSİN","İSTANBUL","İZMİR","KARS","KASTAMONU","KAYSERİ",
            "KIRKLARELİ","KIRŞEHİR","KOCAELİ","KONYA","KÜTAHYA","MALATYA","MANİSA",
            "KAHRAMANMARAŞ","MARDİN","MUĞLA","MUŞ","NEVŞEHİR","NİĞDE","ORDU","RİZE","SAKARYA",
            "SAMSUN","SİİRT","SİNOP","SİVAS","TEKİRDAĞ","TOKAT","TRABZON","TUNCELİ","ŞANLIURFA",
            "UŞAK","VAN","YOZGAT","ZONGULDAK","AKSARAY","BAYBURT","KARAMAN","KIRIKKALE",
            "BATMAN","ŞIRNAK","BARTIN","ARDAHAN","IĞDIR","YALOVA","KARABÜK","KİLİS","OSMANİYE",
            "DÜZCE"
    };


    String str_input_name_surname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        input_name_surname = (EditText) findViewById(R.id.input_name_surname);

        //value of city
        s = (Spinner) findViewById(R.id.input_city);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, arraySpinner);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        s.setAdapter(adapter);

        // value of genders
        input_gender_group = (RadioGroup) findViewById(R.id.radio_group);
        int x = input_gender_group.getCheckedRadioButtonId();
        input_gender = (RadioButton) findViewById(x);
        //String gendertext = input_gender.getText().toString();


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

    //check city is selected or not
    private Boolean city_check(String city){
        String non_select = "Select City...";
        if (city.equals(non_select)){
            return true;
        }
        return false;
    }




    public void createAlertDialog(View v) {
        AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
        alertDialog.setTitle("Alert");
        //variables and their display result
        //1.name and surname variables
        str_input_name_surname = input_name_surname.getText().toString();
        //To List to check names and surname separately
        List<String> myList = new ArrayList<String>(Arrays.asList(str_input_name_surname.split(" ")));
        Boolean bool_names_length = valid_names_length(myList);

        //check if the name input contains all letters
        Boolean bool_regex_name_surname = check_regex(myList);
        //variable to check missing surname
        Boolean bool_missing_surname = missing_surname(str_input_name_surname);



        //2.Spinner variables
        String str_spinner_item = s.getSelectedItem().toString();
        Boolean bool_city_selection = city_check(str_spinner_item);


        //3.RadioButton variables
        //String str_gender = input_gender.getText().toString();


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
            result_message += "You need to write surname too!\n";
        }

        //fourth select city or not
        if (bool_city_selection == true){
            result_message += "You need to select your city!\n";
        }






        alertDialog.setMessage(str_spinner_item);
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        alertDialog.show();
    }


}