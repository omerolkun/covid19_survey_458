package com.example.covid19_survey_cs458;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
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
    //radio issue
    RadioGroup input_gender_group;
    RadioButton input_gender_button;
    //Date variable
    EditText input_day;
    EditText input_month;
    EditText input_year;
    String str_day;
    String str_month;
    String str_year;
    //Chane field variable
    EditText change_field;


    String[] arraySpinner = new String[]{
            "Select City...", "ADANA", "ADIYAMAN", "AFYONKARAHİSAR", "AĞRI", "AMASYA", "ANKARA",
            "ANTALYA", "ARTVİN", "AYDIN", "BALIKESİR", "BİLECİK", "BİNGÖL", "BİTLİS", "BOLU", "BURDUR",
            "BURSA", "ÇANAKKALE", "ÇANKIRI", "ÇORUM", "DENİZLİ", "DİYARBAKIR", "EDİRNE", "ELAZIĞ",
            "ERZİNCAN", "ERZURUM", "ESKİŞEHİR", "GAZİANTEP", "GİRESUN", "GÜMÜŞHANE", "HAKKARİ",
            "HATAY", "ISPARTA", "MERSİN", "İSTANBUL", "İZMİR", "KARS", "KASTAMONU", "KAYSERİ",
            "KIRKLARELİ", "KIRŞEHİR", "KOCAELİ", "KONYA", "KÜTAHYA", "MALATYA", "MANİSA",
            "KAHRAMANMARAŞ", "MARDİN", "MUĞLA", "MUŞ", "NEVŞEHİR", "NİĞDE", "ORDU", "RİZE", "SAKARYA",
            "SAMSUN", "SİİRT", "SİNOP", "SİVAS", "TEKİRDAĞ", "TOKAT", "TRABZON", "TUNCELİ", "ŞANLIURFA",
            "UŞAK", "VAN", "YOZGAT", "ZONGULDAK", "AKSARAY", "BAYBURT", "KARAMAN", "KIRIKKALE",
            "BATMAN", "ŞIRNAK", "BARTIN", "ARDAHAN", "IĞDIR", "YALOVA", "KARABÜK", "KİLİS", "OSMANİYE",
            "DÜZCE"
    };


    String str_input_name_surname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //name surname variable
        input_name_surname = (EditText) findViewById(R.id.input_name_surname);

        //change field value
        change_field = (EditText) findViewById(R.id.input_changes);

        //value of city
        s = (Spinner) findViewById(R.id.input_city);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, arraySpinner);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        s.setAdapter(adapter);

        //date issue
        input_day = (EditText) findViewById(R.id.input_birth_date);
        input_month = (EditText) findViewById(R.id.input_birth_date2);
        input_year = (EditText) findViewById(R.id.input_birth_date4);

        //day field
        input_day.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable s) {
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {
                if (s.length() == 2) {
                    input_month.setText("");
                    input_month.requestFocus();
                }
            }
        });


        //month field

        input_month.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable s) {
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {
                if (s.length() == 2) {
                    input_year.setText("");
                    input_year.requestFocus();
                } else if (s.length() == 0) {
                    input_day.requestFocus();
                }
            }
        });
        //year field
        input_year.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable s) {
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {
                if (s.length() == 0) {
                    //input_year.setText("");
                    input_month.requestFocus();
                }
            }
        });


        //endof date issue


    }

    //check the string input is number or not
    private Boolean number_regex(String dates) {
        Pattern p = Pattern.compile("[0-9]+");
        Matcher m;
        Boolean result = true;

        m = p.matcher(dates);
        result = m.matches();
        if (result == false) {
            return false;
        }
        return true;
    }

    //check total length of name
    private String check_len(String name) {
        String result = "";
        if (name.length() > 25) {
            return "The name and surname is too long!\n";
        }
        if (name.length() < 4) {
            return "The name and surname is too short!\n";
        }
        return result;
    }

    //check names and surname separately
    private Boolean valid_names_length(List<String> names) {
        //Boolean result = true;
        int size_list = names.size();
        for (int i = 0; i < size_list - 1; ++i) {
            if (names.get(i).length() < 3) {
                return false;
            }
        }
        return true;
    }

    //check regex for name and surname
    private Boolean check_regex(List<String> names) {
        Pattern p = Pattern.compile("[a-zA-Z]*");
        int size_names = names.size();
        Matcher m;
        Boolean result;
        for (int i = 0; i < size_names; i++) {
            m = p.matcher(names.get(i));
            result = m.matches();
            if (result == false) {
                return false;
            }
        }

        return true;
    }

    //check whether surname exists
    private Boolean missing_surname(String s) {
        if (s.contains(" ") == false) {
            return true;
        } else {
            return false;
        }

    }

    //check city is selected or not
    private Boolean city_check(String city) {
        String non_select = "Select City...";
        if (city.equals(non_select)) {
            return true;
        }
        return false;
    }

    //check radio button
    private Boolean check_gender(int radio_id) {
        if (radio_id == -1) {
            return false;
        } else {
            return true;
        }
    }

    //check day date
    private Boolean check_day(String day_par) {
        if (number_regex(day_par) == false) {
            return false;
        }
// YEMEGE GITMEDEN ONCE YAZ
        Integer result;
        if (day_par.length() == 0) {
            result = 0;
        } else {
            result = Integer.parseInt(day_par);
        }
        if (result < 1 || result > 31) {
            return false;
        }
        return true;
    }


    //feb29
    private  boolean checkDateValidity(String dayS, String monthS, String yearS){
        int day = Integer.parseInt(dayS);
        int month = Integer.parseInt(monthS);
        int year = Integer.parseInt(yearS);

        if(day > 31 || day < 1 || month < 1 || month > 12 || year > 2010 || year < 1930)
            return false;

        if(day <= 28)
            return true;

        int maxDay = 0;

        switch(month) {
            case 1:
                maxDay = 31;
                break;
            case 2:
                if(year % 4 == 0)
                    maxDay = 29;
                else
                    maxDay = 28;
                break;
            case 3:
                maxDay = 31;
                break;
            case 4:
                maxDay = 30;
                break;
            case 5:
                maxDay = 31;
                break;
            case 6:
                maxDay = 30;
                break;
            case 7:
                maxDay = 31;
                break;
            case 8:
                maxDay = 31;
                break;
            case 9:
                maxDay = 30;
                break;
            case 10:
                maxDay = 31;
                break;
            case 11:
                maxDay = 30;
                break;
            case 12:
                maxDay = 31;
                break;
        }

        if(day > maxDay)
            return false;
        return true;

    }




    //endof feb29

    //check month date
    private Boolean check_month(String month) {
        if (number_regex(month) == false) {
            return false;
        }

        Integer result;

        result = Integer.parseInt(month);
        if (result < 1 || result > 12) {
            return false;
        }
        return true;
    }

    //check year
    private Boolean check_year(String year) {
        if (number_regex(year) == false) {
            return false;
        }
        Integer result = Integer.parseInt(year);
        if (result < 1930 || result > 2010) {
            return false;
        }
        return true;
    }

    //check change field
    private Boolean check_change_field(String changes) {
        if (changes.length() < 10 || changes.length() > 100) {
            return false;
        }
        return true;
    }

    public void createAlertDialog(View v) {
        AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
        alertDialog.setTitle("Result of Submission");
        Integer count_invalid_fields = 0;
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
        input_gender_group = (RadioGroup) findViewById(R.id.radio_group);
        //int radioButtonID = 0;
        int radioButtonID = input_gender_group.getCheckedRadioButtonId();
        if (input_gender_group.getCheckedRadioButtonId() != -1) {
            input_gender_button = (RadioButton) findViewById(radioButtonID);
            String str_gender = input_gender_button.getText().toString();
        } else {

        }
        //variable whether gender is selected or not
        Boolean bool_gender_select = check_gender(radioButtonID);

        //4.date values
        str_day = input_day.getText().toString();
        str_month = input_month.getText().toString();
        str_year = input_year.getText().toString();
        Boolean bool_day = check_day(str_day);
        Boolean bool_month = check_month(str_month);
        Boolean bool_year = check_year(str_year);

        //5 change field value
        String str_change_field = change_field.getText().toString();
        Boolean bool_change_field = check_change_field(str_change_field);


        //Alert message after the button is clicked
        String result_message = "";
        //Alert message after the button is clicked

        //first check length and add its message to result message
        result_message += check_len(str_input_name_surname);
        //second check is if input has blank (this means there is a surname)
        if (bool_regex_name_surname == false) {
            result_message += "Name surname must contain only letters\n";
            count_invalid_fields += 1;

        }
        //third check is missing surname
        if (bool_missing_surname == true) {
            result_message += "You need to write surname too!\n";
            count_invalid_fields += 1;
        }

        //fourth select city or not
        if (bool_city_selection == true) {
            result_message += "You need to select your city!\n";
            count_invalid_fields += 1;
        }

        //fifth select male or female gender
        if (bool_gender_select == false) {
            result_message += "You need to select your gender!\n";
            count_invalid_fields += 1;
        }
        //sixth select a valid date
        //day
        if (bool_day == false) {
            result_message += "Invalid day!\n";
            count_invalid_fields += 1;
        }
        //month
        if (bool_month == false) {
            result_message += "Invalid month!\n";
            count_invalid_fields += 1;
        }
        //year
        if (bool_year == false) {
            result_message += "Invalid year!\n";
            count_invalid_fields += 1;
        }

        if(bool_day && bool_month && bool_year)
        {
            if(!checkDateValidity(str_day, str_month, str_year))
            {
                result_message += "Invalid Date!\n";
                count_invalid_fields += 1;
            }
        }
        //change fields
        if (bool_change_field == false) {
            result_message += "Changes input is invalid\n";
            count_invalid_fields += 1;
        }

        if(count_invalid_fields == 0){
            result_message = "Submission is valid\n";
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