package vn.edu.techkids.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    public static final int DL = 100;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btn = (Button) this.findViewById(R.id.button);
        final EditText editText = (EditText) this.findViewById(R.id.editText);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String rs = editText.getText().toString();
                Bundle bundle = new Bundle();
                bundle.putString("DU_LIEU", rs);
                Intent intent = new Intent(MainActivity.this, Main2Activity.class);
                intent.putExtra("goitin", bundle);
                MainActivity.this.startActivityForResult(intent, 1);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

    }
}
