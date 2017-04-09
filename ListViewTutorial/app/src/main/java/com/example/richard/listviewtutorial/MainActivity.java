package com.example.richard.listviewtutorial;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    protected List<HashMap<String, String>> UnitListArray;
    protected SimpleAdapter MyListAdapter;
    protected Button addButton;
    protected EditText addEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView unitList = (ListView) this.findViewById(R.id.listview);
        UnitListArray = new ArrayList<>();
        String[] colHEAD = new String[]{"CODE", "UNITS", "SEMESTER"};
        int[] dataCell = new int[]{R.id.UnitCode, R.id.UnitName, R.id.Semester};

        HashMap<String, String> map = new HashMap<>();
        map.put("CODE", "FIT5046");
        map.put("UNITS", "Mobile and distributed Computing");
        map.put("SEMESTER", "Sem1 2017");
        UnitListArray.add(map);

        HashMap<String, String> map2 = new HashMap<>();
        map2.put("CODE", "FIT5211");
        map2.put("UNITS", "Algorithm and Dara Structure");
        map2.put("SEMESTER", "Sem1 2017");
        UnitListArray.add(map2);

        MyListAdapter = new SimpleAdapter(this, UnitListArray, R.layout.list_view, colHEAD, dataCell);
        unitList.setAdapter(MyListAdapter);

        addButton = (Button) this.findViewById(R.id.button);
        addEditText = (EditText) this.findViewById(R.id.editText);

        initAddUnitListeners();
    }

    protected void initAddUnitListeners() {
        this.addButton.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                String newUnit = addEditText.getText().toString();
                if(newUnit.equals("")){
                    sendToast("ENTER TEXT FIRST");
                }
                String[] unitsArray = newUnit.split(",");
                HashMap<String, String> map = new HashMap<String, String>();
                map.put("CODE", unitsArray[0]);
                map.put("UNITS", unitsArray[1]);
                map.put("SEMESTER", unitsArray[2]);
                addMap(map);
            }
        });
    }

    private void addMap(HashMap<String, String> map) {
        UnitListArray.add(map);
    }

    protected void sendToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }
}
