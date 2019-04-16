package com.nagarro.persistence.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;

import com.nagarro.persistence.R;
import com.nagarro.persistence.database.AppDatabase;
import com.nagarro.persistence.databinding.ActivityMainBinding;
import com.nagarro.persistence.utils.DatabaseInitializer;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getName();
    EditText txtFirstName, txtLastName, txtAge,etID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        txtFirstName = (EditText) findViewById(R.id.txtFirstName);
        txtLastName = (EditText) findViewById(R.id.txtLastName);
        txtAge = (EditText) findViewById(R.id.txtAge);
        etID=(EditText)findViewById(R.id.etID);
        activityMainBinding.clickHereBtn.setOnClickListener(view ->
                DatabaseInitializer.populateAsync(txtFirstName.getText().toString(), txtLastName.getText().toString(), Integer.parseInt(txtAge.getText().toString()), AppDatabase.getAppDatabase(this))
        );
        activityMainBinding.cleickHereDelete.setOnClickListener(view ->
        DatabaseInitializer.populateAsyncdelte(Integer.parseInt(etID.getText().toString().trim()),AppDatabase.getAppDatabase(this))
        );
    }

    @Override
    protected void onDestroy() {
        AppDatabase.destroyInstance();
        super.onDestroy();
    }
}
