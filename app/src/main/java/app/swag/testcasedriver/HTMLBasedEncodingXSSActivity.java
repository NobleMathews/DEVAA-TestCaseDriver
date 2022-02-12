package app.swag.testcasedriver;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;

public class HTMLBasedEncodingXSSActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_h_t_m_l_based_encoding_x_s_s);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent intent = getIntent();
        Intent mIntent = new Intent();
        mIntent.putExtra("bla", "bla");
        mIntent.setClassName(intent.getStringExtra("packageName"), intent.getStringExtra("className"));
        mIntent.setData(Uri.parse(intent.getStringExtra("uri1")));
        startActivity(mIntent);
//        Log.e("TestResponse",Boolean.toString(result));
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }
}