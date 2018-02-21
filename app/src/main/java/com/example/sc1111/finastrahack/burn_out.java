package com.example.sc1111.finastrahack;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.FirebaseApp;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;
import java.util.List;
import java.util.ArrayList;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

public class burn_out extends NavigationActivity {

    Spinner fac_choice;
    String spinnerValue;
    String graphValue;
    List<String> list_spinner = new ArrayList<>();
    private ArrayList<XYValue> xyValueArray;
    GraphView graph;
    LineGraphSeries<DataPoint> xySeries = new LineGraphSeries<>();
    FirebaseDatabase database = FirebaseDatabase.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_burn_out);
        setView();

        fac_choice = (Spinner) findViewById(R.id.fac_choice);
        DatabaseReference myRef = database.getReference("faculties");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                spinnerValue = (String) dataSnapshot.getValue(String.class);
                String[] string_list = spinnerValue.split(",");
                for(int i = 0; i<string_list.length; i++){
                    list_spinner.add((String)string_list[i]);
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("tag", "Failed to read value.", error.toException());
            }
        });

        list_spinner.add("Other");

        ArrayAdapter <String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, list_spinner);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        fac_choice.setAdapter(adapter);


        fac_choice.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String item = adapterView.getItemAtPosition(i).toString();
                Toast.makeText(burn_out.this, item, Toast.LENGTH_SHORT).show();
                Log.i("ITEM NAME", item.toString());
                if(item.toString().equals("Art")){
                    DatabaseReference artsGraphRef = database.getReference("artsGraph");

                    artsGraphRef.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            graphValue = dataSnapshot.getValue(String.class);
                            String[] number_list = graphValue.split(",");

                            graph = (GraphView) findViewById(R.id.graph);
                            xyValueArray = new ArrayList<>();

                            for(int i = 0; i<number_list.length-1;i+=2){
                                double x = Double.parseDouble(number_list[i]);
                                double y = Double.parseDouble(number_list[i+1]);
                                xyValueArray.add(new XYValue(x,y));
                            }


                            createGraph();
                        }

                        @Override
                        public void onCancelled(DatabaseError error) {
                            // Failed to read value
                            Log.w("tag", "Failed to read value.", error.toException());
                        }
                    });
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });



    }

    private void createGraph() {
        xyValueArray = sortArray(xyValueArray);
        for(int i = 0; i<xyValueArray.size();i++){
            double x = xyValueArray.get(i).getX();
            double y = xyValueArray.get(i).getY();
            xySeries.appendData(new DataPoint(x, y), true, 1000);

        }
        graph.getViewport().setYAxisBoundsManual(true);
        graph.getViewport().setMaxX(xyValueArray.get(xyValueArray.size()-1).getX());
        graph.getViewport().setMaxY(xyValueArray.get(0).getY());

        graph.addSeries(xySeries);


    }

    private ArrayList<XYValue> sortArray(ArrayList<XYValue> array){
        /*
        //Sorts the xyValues in Ascending order to prepare them for the PointsGraphSeries<DataSet>
         */
        int factor = Integer.parseInt(String.valueOf(Math.round(Math.pow(array.size(),2))));
        int m = array.size() - 1;
        int count = 0;


        while (true) {
            m--;
            if (m <= 0) {
                m = array.size() - 1;
            }
            try {
                //print out the y entrys so we know what the order looks like
                //Log.d(TAG, "sortArray: Order:");
                //for(int n = 0;n < array.size();n++){
                //Log.d(TAG, "sortArray: " + array.get(n).getY());
                //}
                double tempY = array.get(m - 1).getY();
                double tempX = array.get(m - 1).getX();
                if (tempX > array.get(m).getX()) {
                    array.get(m - 1).setY(array.get(m).getY());
                    array.get(m).setY(tempY);
                    array.get(m - 1).setX(array.get(m).getX());
                    array.get(m).setX(tempX);
                } else if (tempX == array.get(m).getX()) {
                    count++;
                } else if (array.get(m).getX() > array.get(m - 1).getX()) {
                    count++;
                }
                //break when factorial is done
                if (count == factor) {
                    break;
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                        e.getMessage();
                break;
            }
        }
        return array;
    }

}

