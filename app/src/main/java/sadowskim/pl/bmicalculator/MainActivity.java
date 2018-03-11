package sadowskim.pl.bmicalculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    final static int bmiNorm = 25;

    double weight = 0;
    double height = 0;
    double bmi = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView resultField = findViewById(R.id.result_field);
        final EditText weightField = findViewById(R.id.weight_field);
        final EditText heightField = findViewById(R.id.height_field);

        weightField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(s.length() > 0){
                    weight = Double.parseDouble(s.toString());
                    height = heightField.getText().toString().equals("") ? 0 : 0.01 * Double.parseDouble(heightField.getText().toString());

                    if(height > 0){
                        bmi = weight / (height * height);
                        resultField.setText(String.format("%.2f", bmi));

                        if(bmi > bmiNorm)
                            Toast.makeText(getApplicationContext(), R.string.exercise_more_toast_msg, Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        heightField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(s.length() > 0){
                    height = 0.01 * Double.parseDouble(s.toString());
                    weight = weightField.getText().toString().equals("") ? 0 : Double.parseDouble(weightField.getText().toString());

                    if(height > 0){
                        bmi = weight / (height * height);
                        resultField.setText(String.format("%.2f", bmi));

                        if(bmi > bmiNorm)
                            Toast.makeText(getApplicationContext(), R.string.exercise_more_toast_msg, Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();

        //TODO: save current weight, height and bmi to CSV file in internal memory
//        String baseDir = android.os.Environment.getExternalStorageDirectory().getAbsolutePath();
//        String fileName = "AnalysisData.csv";
//        String filePath = baseDir + File.separator + fileName;
//        File f = new File(filePath );
//        CSVWriter writer;
//// File exist
//        if(f.exists() && !f.isDirectory()){
//            mFileWriter = new FileWriter(filePath , true);
//            writer = new CSVWriter(mFileWriter);
//        }
//        else {
//            writer = new CSVWriter(new FileWriter(filePath));
//        }
//        String[] data = {"Ship Name","Scientist Name", "...",new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").formatter.format(date)});
//
//        writer.writeNext(data);
//
//        writer.close();
    }

    //TODO: restore saved values on next launch
//    https://stackoverflow.com/questions/151777/saving-android-activity-state-using-save-instance-state
}
