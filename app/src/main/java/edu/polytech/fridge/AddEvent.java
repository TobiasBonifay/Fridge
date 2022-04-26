package edu.polytech.fridge;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import org.joda.time.DateTime;
import android.content.Intent;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.text.format.DateUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class AddEvent extends AppCompatActivity {
    EditText title;
    EditText location;
    EditText description;
    EditText begintime;
    Button addEvent;

    public AddEvent(){

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_event);

        title =findViewById(R.id.etTitle);
        location=findViewById(R.id.etLocation);
        description=findViewById(R.id.etDescription);
        addEvent=findViewById(R.id.btnAdd);
        begintime=findViewById(R.id.etBeginTime);

        addEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!title.getText().toString().isEmpty() && !location.getText().toString().isEmpty() && !description
                        .getText().toString().isEmpty()) {
                    String s = begintime.getText().toString();
                    DateTime startTime = DateTime.parse(s);



                    Intent intent = new Intent(Intent.ACTION_INSERT);
                    intent.setData(CalendarContract.Events.CONTENT_URI);
                    intent.putExtra(CalendarContract.Events.TITLE, title.getText().toString());
                    intent.putExtra(CalendarContract.Events.EVENT_LOCATION, location.getText().toString());
                    intent.putExtra(CalendarContract.Events.DESCRIPTION, description.getText().toString());
                    intent.putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME , startTime.getMillis());

                    intent.putExtra(CalendarContract.Events.ALL_DAY, "false");

                    if(intent.resolveActivity(getPackageManager()) != null){
                        startActivity(intent);
                    }else{
                        Toast.makeText(AddEvent.this, "There is no app that support this action", Toast.LENGTH_SHORT).show();
                    }



                }else{
                    Toast.makeText(AddEvent.this, "Please fill all the fields",
                            Toast.LENGTH_SHORT).show();
                }

            }
        });


    }
}