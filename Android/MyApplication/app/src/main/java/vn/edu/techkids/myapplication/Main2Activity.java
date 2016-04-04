package vn.edu.techkids.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        TextView txt = (TextView)this.findViewById(R.id.textView);
        Intent intent = getIntent();
        Bundle b = intent.getBundleExtra("goitin");
        txt.setText(b.getString("DU_LIEU"));
        Button btn = (Button)this.findViewById(R.id.button2);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
