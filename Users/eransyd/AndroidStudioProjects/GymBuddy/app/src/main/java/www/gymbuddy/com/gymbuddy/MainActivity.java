package www.gymbuddy.com.gymbuddy;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Spinner;
import android.widget.ArrayAdapter;
import android.widget.AdapterView;
import android.util.Log;
import android.widget.AdapterView.OnItemSelectedListener;

public class MainActivity extends AppCompatActivity {

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

        Spinner staticSpinner = (Spinner) findViewById(R.id.gymType_spinner);
        Spinner dynamicSpinner = (Spinner) findViewById(R.id.gymLocation_spinner);



        // Create an ArrayAdapter using the string array and a default spinner
        ArrayAdapter<CharSequence> staticAdapter = ArrayAdapter
                .createFromResource(this, R.array.home_gym_array,
                        android.R.layout.simple_spinner_item);

        // Specify the layout to use when the list of choices appears
        staticAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        staticSpinner.setAdapter(staticAdapter);

        String selection = staticSpinner.getSelectedItem().toString();

        if (selection == "GoodLife")
        {
            ArrayAdapter<CharSequence>  dynamicAdapter  = ArrayAdapter
                    .createFromResource(this, R.array.goodlife_location,
                            android.R.layout.simple_spinner_item);

            dynamicAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

            dynamicSpinner.setAdapter(dynamicAdapter);


        }
        else if (selection == "YMCA")
        {
            ArrayAdapter<CharSequence>  dynamicAdapter  = ArrayAdapter
                    .createFromResource(this, R.array.YMCA_locations,
                            android.R.layout.simple_spinner_item);

            dynamicAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

            dynamicSpinner.setAdapter(dynamicAdapter);

        }
        else if (selection == "University")
        {
            ArrayAdapter<CharSequence>  dynamicAdapter  = ArrayAdapter
                    .createFromResource(this, R.array.university_locations,
                            android.R.layout.simple_spinner_item);
            dynamicAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

            dynamicSpinner.setAdapter(dynamicAdapter);

        }
        else {};

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
