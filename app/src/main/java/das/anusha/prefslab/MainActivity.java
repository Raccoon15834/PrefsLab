package das.anusha.prefslab;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView txt1, txt2;
    Button btn1, btn2;
    GridLayout scrn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txt1 = findViewById(R.id.txtOne);
        txt2 = findViewById(R.id.txtTwo);
        btn1 = findViewById(R.id.btnOne);
        btn2 = findViewById(R.id.btnTwo);
        scrn = findViewById(R.id.wholeScrn);
        View.OnClickListener click4 = new View.OnClickListener() {
            @Override
            public void onClick(View myView) {
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
    }
}