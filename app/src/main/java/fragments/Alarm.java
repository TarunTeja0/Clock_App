package fragments;

import static android.app.Activity.RESULT_OK;
import static android.content.Context.ALARM_SERVICE;

//import static com.example.clockapp.MainActivity.times;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.clockapp.DisplayAlarmActivity;
import com.example.clockapp.R;
import com.example.clockapp.SetActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Calendar;

import ModelClass.Time;
import adapters.RecyclerViewAdapter;


public class Alarm extends Fragment {

    private View view;
    private final int REQ_CODE= 100;

    public static ArrayList<Time> times = new ArrayList<>();

    private RecyclerView recyclerView;
    private RecyclerViewAdapter recyclerViewAdapter;
    private FloatingActionButton button;
    private Intent intent;
    int hour, min;
    private String fileName = "myArrayListData.txt";





    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_alarm, container, false);
        recyclerView = view.findViewById(R.id.recyclerViewAlarm);
        button = view.findViewById(R.id.buttonAlarm);


        getDataFromFile();







        recyclerViewAdapter = new RecyclerViewAdapter(getContext(),times);
        recyclerView.setAdapter(recyclerViewAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(getContext(), SetActivity.class);
                startForResult.launch(intent);
            }
        });



        return view;
    }

    private void getDataFromFile() {
    }

    private ActivityResultLauncher<Intent>  startForResult = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
        @Override
        public void onActivityResult(ActivityResult result) {
            if(result != null && result.getResultCode() == 100){
                if(result.getData() != null ){
                    int hour = result.getData().getIntExtra("hr", 0);
                    int min = result.getData().getIntExtra("min", 0);
                    Log.d("min",String.valueOf(min));
                    Log.d("hour",String.valueOf(hour));

                    try {
                        File directory = getContext().getExternalFilesDir(null); // Get app-specific directory
                        File file = new File(directory, fileName); // if no file named fileName Create file in the directory
                        FileOutputStream fileOutputStream = new FileOutputStream(file);
                        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream);
                        Gson gson = new Gson();
                        times.add(new Time(hour,min));
                        String json = gson.toJson(times);
                        outputStreamWriter.write(json);
                        outputStreamWriter.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    //setting calendar with hours and minutes
                    Calendar calendar = Calendar.getInstance();
                    calendar.setTimeInMillis(System.currentTimeMillis());
                    calendar.set(Calendar.HOUR_OF_DAY, hour); // Replace alarmHour with the desired hour
                    calendar.set(Calendar.MINUTE,min); // Replace alarmMinute with the desired minute

                    //setting alarm
                    AlarmManager alarmManager = (AlarmManager) getContext().getSystemService(ALARM_SERVICE);
                    Intent intent2 = new Intent(getContext(), DisplayAlarmActivity.class);
                    PendingIntent pendingIntent = PendingIntent.getBroadcast(getContext(),0, intent2,0);
                    alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),pendingIntent);



                    recyclerViewAdapter.notifyDataSetChanged();
                }
            }
        }
    });

    public void saveDataIntoFile(){
        //todo
    }


}