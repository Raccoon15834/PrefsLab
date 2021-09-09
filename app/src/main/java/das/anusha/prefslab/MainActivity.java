package das.anusha.prefslab;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {
    TextView txt1, txt2;
    Button btn1, btn2;
    GridLayout scrn;
    SeekBar toggle;
    SharedPreferences.Editor myPrefsEditor;
    String prefTag = "das.anusha.prefslab.savedVals";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SharedPreferences myPrefs= getSharedPreferences(prefTag, Context.MODE_PRIVATE);
        myPrefsEditor = myPrefs.edit();

        txt1 = (TextView) findViewById(R.id.txtOne);
        txt2 = (TextView) findViewById(R.id.txtTwo);
        btn1 = findViewById(R.id.btnOne);
        btn2 = findViewById(R.id.btnTwo);
        scrn = findViewById(R.id.wholeScrn);
        toggle = findViewById(R.id.fontToggle);
        View.OnClickListener click4 = new TextView.OnClickListener() {
            @Override
            public void onClick(View myView) {
                Log.d("clicker","view was clicked");
                TextView vwtxt = (TextView) myView;
                int currNum = Integer.parseInt(vwtxt.getText().toString());
                vwtxt.setText(""+(currNum++));

            }
        };

        txt1.setOnClickListener(click4);
        txt2.setOnClickListener(click4);
        btn1.setOnClickListener(click4);
        btn2.setOnClickListener(click4);

        scrn.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                txt1.setText(R.string.defNum);
                txt2.setText(R.string.defNum);
                btn1.setText(R.string.defNum);
                btn2.setText(R.string.defNum);
                return false;
            }
        });
        toggle.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                txt1.setTextSize(i); txt2.setTextSize(i); btn1.setTextSize(i); btn2.setTextSize(i);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                String sizeKey = "sizex4";
                Snackbar togglePopUp = Snackbar.make(scrn, R.string.snackBarTxt, Snackbar.LENGTH_SHORT);
                togglePopUp.show();
                togglePopUp.setAction(R.string.undoAction, new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        txt1.setTextSize(10); txt2.setTextSize(10); btn1.setTextSize(10); btn2.setTextSize(10);
                        SharedPreferences myPrefs = getSharedPreferences(prefTag, Context.MODE_PRIVATE);
                        myPrefs.getInt(sizeKey, 40);
                    }
                });

                myPrefsEditor.putInt(sizeKey, seekBar.getProgress());
                myPrefsEditor.apply();
                //TODO fix 4view listener, long click listener, shared pref undo action
                //TODO stylize snack pop up https://material.io/components/snackbars/android#anatomy-and-key-properties
                //TODO stylize seek bar https://www.zoftino.com/android-seekbar-and-custom-seekbar-examples
                //TODO toast clicks per sec
            }
        });
    }
}

//snackbars
//text label + colored action (4-10 sec)
//solid or translucent container elevated
//right above navigation, shouldn't hit sides
