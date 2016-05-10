package www.gymbuddy.com.gymbuddy;

import android.app.DatePickerDialog;
import android.content.Context;
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
import android.widget.Toast;

import com.firebase.client.Firebase;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;


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
        Firebase.setAndroidContext(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //HOME GYM TYPE SPINNERS
        Spinner homeGymSpinner = (Spinner) findViewById(R.id.gymType_spinner);
        ArrayAdapter<CharSequence> homeGymAdapter = ArrayAdapter.createFromResource(this, R.array.home_gym_array, android.R.layout.simple_spinner_item);
        homeGymAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        homeGymSpinner.setAdapter(homeGymAdapter);
        homeGymSpinner.setOnItemSelectedListener(new MyOnItemSelectedListener());


        //HOME GYM LOCATION
        //Spinner homeGymLocationSpinner = (Spinner) findViewById(R.id.gymLocation_spinner);
        //homeGymLocationSpinner.setOnItemSelectedListener(new MyOnItemSelectedListener());



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
                EditText userName = (EditText) findViewById(R.id.editUserName);


                String firstNameValue = firstName.getText().toString();
                String lastNameValue = lastName.getText().toString();
                String birthDateValue = birthDate.getText().toString();
                String genderValue = genderRadio.getText().toString();
                String emailValue = email.getText().toString();
                String passwordValue = password.getText().toString();
                String  phoneNoValue = phoneNo.getText().toString();
                String userNameValue = userName.getText().toString();

                Firebase ref = new Firebase("https://flickering-torch-8392.firebaseio.com/GymBuddy");

                Firebase userRef = ref.child("users").child(userNameValue);


                userRef.child("firstName").setValue(firstNameValue);
                userRef.child("lastName").setValue(lastNameValue);
                userRef.child("dob").setValue(birthDateValue);
                userRef.child("gender").setValue(genderValue);
                userRef.child("email").setValue(emailValue);
                userRef.child("password").setValue(passwordValue);
                userRef.child("phoneNumber").setValue(phoneNoValue);
                userRef.child("homeGym").setValue(HOME_GYM);
                userRef.child("homeGymLocation").setValue(HOME_GYM_LOCATION);



                /*
                Map<String, String> usersMap = new HashMap<String, String>();
                usersMap.put("firstName", firstNameValue);
                usersMap.put("lastName", lastNameValue);
                usersMap.put("dob", birthDateValue);
                usersMap.put("gender", genderValue);
                usersMap.put("userName", userNameValue);
                usersMap.put("email", emailValue);
                usersMap.put("password", passwordValue);
                usersMap.put("phoneNumber", firstNameValue);
                usersMap.put("lastName", phoneNoValue);
                usersMap.put("homeGym", HOME_GYM);
                usersMap.put("homeGymLocation", HOME_GYM_LOCATION);


                Map<String, Map<String, String>> users = new HashMap<String, Map<String, String>>();
                users.put(userNameValue, usersMap);
                userRef.setValue(users);
                */
            }
        });




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


            Spinner homeGymLocationSpinner = (Spinner) findViewById(R.id.gymLocation_spinner);

            if(gymSelection.contentEquals("GoodLife")) {

                ArrayAdapter<CharSequence> homeGymLocationAdapter = ArrayAdapter.createFromResource(MainActivity.this,
                        R.array.goodlife_location, android.R.layout.simple_spinner_item);
                homeGymLocationAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                homeGymLocationAdapter.notifyDataSetChanged();
                homeGymLocationSpinner.setAdapter(homeGymLocationAdapter);
                homeGymLocationSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view,
                                               int position, long id) {
                        Spinner homeGymLocationSpinner = (Spinner) findViewById(R.id.gymLocation_spinner);
                        String gymLocationSelection= String.valueOf(homeGymLocationSpinner.getSelectedItem());
                        HOME_GYM_LOCATION = gymLocationSelection;
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }

                });
            }
            else if(gymSelection.contentEquals("YMCA")) {

                ArrayAdapter<CharSequence> homeGymLocationAdapter = ArrayAdapter.createFromResource(MainActivity.this,
                        R.array.YMCA_locations, android.R.layout.simple_spinner_item);
                homeGymLocationAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                homeGymLocationAdapter.notifyDataSetChanged();
                homeGymLocationSpinner.setAdapter(homeGymLocationAdapter);
                homeGymLocationSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view,
                                               int position, long id) {
                        Spinner homeGymLocationSpinner = (Spinner) findViewById(R.id.gymLocation_spinner);
                        String gymLocationSelection= String.valueOf(homeGymLocationSpinner.getSelectedItem());
                        HOME_GYM_LOCATION = gymLocationSelection;
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }

                });


            }
            else if(gymSelection.contentEquals("University Gym")) {

                ArrayAdapter<CharSequence> homeGymLocationAdapter = ArrayAdapter.createFromResource(MainActivity.this,
                        R.array.university_locations, android.R.layout.simple_spinner_item);
                homeGymLocationAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                homeGymLocationAdapter.notifyDataSetChanged();
                homeGymLocationSpinner.setAdapter(homeGymLocationAdapter);
                homeGymLocationSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view,
                                               int position, long id) {
                        Spinner homeGymLocationSpinner = (Spinner) findViewById(R.id.gymLocation_spinner);
                        String gymLocationSelection= String.valueOf(homeGymLocationSpinner.getSelectedItem());
                        HOME_GYM_LOCATION = gymLocationSelection;
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }

                });


            }
            else {
                Context context = getApplicationContext();
                CharSequence text = "Please select your Gym!";
                int duration = Toast.LENGTH_SHORT;

                Toast toast = Toast.makeText(context, text, duration);
                toast.show();

            }







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
