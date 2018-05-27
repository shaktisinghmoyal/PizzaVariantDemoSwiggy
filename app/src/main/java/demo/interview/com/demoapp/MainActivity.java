package demo.interview.com.demoapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import demo.interview.com.demoapp.ui.variants.VariantsFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, VariantsFragment.newInstance())
                    .commitNow();
        }
    }
}
