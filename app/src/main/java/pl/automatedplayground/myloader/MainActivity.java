package pl.automatedplayground.myloader;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import pl.automatedplayground.myloader.example.MainContainerFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        MainContainerFragment fragment = new MainContainerFragment();
//        getSupportFragmentManager().beginTransaction().replace(R.id.test2,fragment).commit();
    }

}
