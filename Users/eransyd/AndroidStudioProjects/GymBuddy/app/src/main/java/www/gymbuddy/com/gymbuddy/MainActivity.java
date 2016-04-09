package www.gymbuddy.com.gymbuddy;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;



public class MainActivity extends AppCompatActivity implements View.OnClickListener {

private EditText dobPicker;
private DatePickerDialog dobPickerDialog;
private SimpleDateFormat dateFormatter;
    public String HOME_GYM;
    public String HOME_GYM_LOCATION;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


        //HOME GYM TYPE SPINNERS
        Spinner homeGymSpinner = (Spinner) findViewById(R.id.gymType_spinner);
        ArrayAdapter<CharSequence> homeGymAdapter = ArrayAdapter.createFromResource(this, R.array.home_gym_array, android.R.layout.simple_spinner_item);
        homeGymAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        homeGymSpinner.setAdapter(homeGymAdapter);
        homeGymSpinner.setOnItemSelectedListener(new MyOnItemSelectedListener());


        //HOME GYM LOCATION
        Spinner homeGymLocationSpinner = (Spinner) findViewById(R.id.gymLocation_spinner);



        /***DATE OF BIRTH PICKER**/
        dateFormatter = new SimpleDateFormat("dd-MM-yyyy", Locale.US);

        findViewsById();

        setDateTimeField();

        //***DATABASE****//





        Button button= (Button) findViewById(R.id.signup);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                EditText firstName = (EditText) findViewById(R.id.editFirstName);
                EditText lastName = (EditText) findViewById(R.id.editLastName);
                EditText birthDate = (EditText) findViewById(R.id.dobPicker);
                RadioGroup genderGroup = (RadioGroup) findViewById(R.id.radioSex);
                int genderId = genderGroup.getCheckedRadioButtonId();
                RadioButton genderRadio = (RadioButton) findViewById(genderId);
                EditText email = (EditText) findViewById(R.id.editEmail);
                EditText password = (EditText) findViewById(R.id.editPassword);
                EditText phoneNo = (EditText) findViewById(R.id.phoneNoEdit);


                String firstNameValue = firstName.getText().toString();
                String lastNameValue = lastName.getText().toString();
                String birthDateValue = birthDate.getText().toString();
                String genderValue = genderRadio.getText().toString();
                String emailValue = email.getText().toString();
                String passwordValue = password.getText().toString();
                String  phoneNoValue = phoneNo.getText().toString();

                MyDatabaseHelper databaseHelper = new MyDatabaseHelper(MainActivity.this);

                databaseHelper.addUser(firstNameValue,lastNameValue, birthDateValue,genderValue, emailValue,passwordValue,phoneNoValue,
                                           HOME_GYM, HOME_GYM_LOCATION );
            }
        });

        MyDatabaseHelper databaseHelper = new MyDatabaseHelper(this);


    }

    private void findViewsById() {
        dobPicker = (EditText) findViewById(R.id.dobPicker);
        dobPicker.setInputType(InputType.TYPE_NULL);
        dobPicker.requestFocus();
    }

    private void setDateTimeField() {
        dobPicker.setOnClickListener(this);


        Calendar newCalendar = Calendar.getInstance();
        dobPickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                dobPicker.setText(dateFormatter.format(newDate.getTime()));
            }

        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

    }

    @Override
    public void onClick(View v) {
        if(v == dobPicker) {
            dobPickerDialog.show();
        }
    }

    /**CLASS CONTAINING THE LISTENERS FOR SPINNERS**/
    public class MyOnItemSelectedListener implements OnItemSelectedListener{


        public void onItemSelected(AdapterView<?> parent, View v, int pos,long id)
        {
            Spinner homeGymSpinner = (Spinner) findViewById(R.id.gymType_spinner);

            //setting spinner2 based on spinner1
            String gymSelection= String.valueOf(homeGymSpinner.getSelectedItem());

            HOME_GYM = gymSelection;

            if(gymSelection.contentEquals("GoodLife")) {

                Spinner homeGymLocationSpinner = (Spinner) findViewById(R.id.gymLocation_spinner);
                ArrayAdapter<CharSequence> homeGymLocationAdapter = ArrayAdapter.createFromResource(MainActivity.this,
                        R.array.goodlife_location, android.R.layout.simple_spinner_item);
                homeGymLocationAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                homeGymLocationAdapter.notifyDataSetChanged();
                homeGymLocationSpinner.setAdapter(homeGymLocationAdapter);

            }
            else if(gymSelection.contentEquals("YMCA")) {
                Spinner homeGymLocationSpinner = (Spinner) findViewById(R.id.gymLocation_spinner);
                ArrayAdapter<CharSequence> homeGymLocationAdapter = ArrayAdapter.createFromResource(MainActivity.this,
                        R.array.YMCA_locations, android.R.layout.simple_spinner_item);
                homeGymLocationAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                homeGymLocationAdapter.notifyDataSetChanged();
                homeGymLocationSpinner.setAdapter(homeGymLocationAdapter);
            }
            else if(gymSelection.contentEquals("University Gym")) {
                Spinner homeGymLocationSpinner = (Spinner) findViewById(R.id.gymLocation_spinner);
                ArrayAdapter<CharSequence> homeGymLocationAdapter = ArrayAdapter.createFromResource(MainActivity.this,
                        R.array.university_locations, android.R.layout.simple_spinner_item);
                homeGymLocationAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                homeGymLocationAdapter.notifyDataSetChanged();
                homeGymLocationSpinner.setAdapter(homeGymLocationAdapter);
            }
            else {}

            Spinner homeGymLocationSpinner = (Spinner) findViewById(R.id.gymLocation_spinner);
            String gymLocationSelection= String.valueOf(homeGymLocationSpinner.getSelectedItem());
            HOME_GYM_LOCATION = gymLocationSelection;


        }

        public void onNothingSelected(AdapterView<?> arg0) {
            // TODO Auto-generated method stub

        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }




}
