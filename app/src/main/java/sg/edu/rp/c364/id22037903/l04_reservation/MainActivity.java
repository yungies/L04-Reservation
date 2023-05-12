package sg.edu.rp.c364.id22037903.l04_reservation;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

public class MainActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button buttonDisplayDate = findViewById(R.id.buttonDisplayDate);
        Button buttonDisplayTime = findViewById(R.id.buttonDisplayTime);
        Button buttonReset = findViewById(R.id.buttonReset);
        Button buttonConfirm = findViewById(R.id.button);
        TextView textViewDisplay = findViewById(R.id.textViewDisplay);
        CheckBox smokeCheckbox = findViewById(R.id.Smoke);
        CheckBox nonSmokeCheckbox = findViewById(R.id.NonSmoke);
        TimePicker tpTime = findViewById(R.id.timePicker);

        buttonDisplayDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePicker datePicker = findViewById(R.id.datePicker);
                int year = datePicker.getYear();
                int month = datePicker.getMonth() + 1;
                int day = datePicker.getDayOfMonth();
                String date = year + "-" + month + "-" + day;
                textViewDisplay.setText("Selected Date: " + date);
            }
        });

        buttonDisplayTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePicker timePicker = findViewById(R.id.timePicker);
                int hour = timePicker.getCurrentHour();
                int minute = timePicker.getCurrentMinute();
                String time = hour + ":" + minute;
                textViewDisplay.setText("Selected Time: " + time);
            }
        });

        buttonReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePicker timePicker = findViewById(R.id.timePicker);
                timePicker.setCurrentHour(19);
                timePicker.setCurrentMinute(30);
                DatePicker datePicker = findViewById(R.id.datePicker);
                datePicker.updateDate(2020, 5,1);

                textViewDisplay.setText("TextView");
            }
        });

        buttonConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText editTextName = findViewById(R.id.editTextText4);
                EditText editTextPhone = findViewById(R.id.editTextPhone2);
                EditText editTextSize = findViewById(R.id.editTextNumber);
                String name = editTextName.getText().toString();
                String phone = editTextPhone.getText().toString();
                String size = editTextSize.getText().toString();

                DatePicker datePicker = findViewById(R.id.datePicker);
                int year = datePicker.getYear();
                int month = datePicker.getMonth() + 1;
                int day = datePicker.getDayOfMonth();
                TimePicker timePicker = findViewById(R.id.timePicker);
                int hour = timePicker.getCurrentHour();
                int minute = timePicker.getCurrentMinute();

                boolean isSmokeChecked = smokeCheckbox.isChecked();
                boolean isNonSmokeChecked = nonSmokeCheckbox.isChecked();

                StringBuilder cfmMsg = new StringBuilder();
                cfmMsg.append("Name: ").append(name).append("\n");
                cfmMsg.append("Phone: ").append(phone).append("\n");
                cfmMsg.append("Size: ").append(size).append("\n");
                cfmMsg.append("Date: ").append(year).append("-").append(month).append("-").append(day).append("\n");
                cfmMsg.append("Time: ").append(hour).append(":").append(minute).append("\n");
                cfmMsg.append("Smoke: ").append(isSmokeChecked).append("\n");
                cfmMsg.append("Non-Smoke: ").append(isNonSmokeChecked);

                Toast.makeText(MainActivity.this, cfmMsg.toString(), Toast.LENGTH_SHORT).show();
            }
        });

        // challenge not working
        tpTime.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                if (hourOfDay < 8 || (hourOfDay == 20 && minute > 0)) {
                    // If the selected time is before 8AM or after 8:59PM, set it to 8PM
                    tpTime.setCurrentHour(20); // Set the hour to 8PM
                    tpTime.setCurrentMinute(0); // Set the minute to 0
                    Toast.makeText(MainActivity.this, "Reservation time must be between 8AM and 8:59PM", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
