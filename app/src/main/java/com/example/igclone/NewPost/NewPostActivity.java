package com.example.igclone.NewPost;

import android.os.Build;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import com.example.igclone.NewPost.Adapters.NewPostActivityAdapter;
import com.example.igclone.R;

public class NewPostActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    ViewPager viewPager;
    TextView toolbarTitle;

    String tabTitles[] = {"PHOTO", "VIDEO"};

    Spinner spinner;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        }

        setContentView(R.layout.activity_new_post);

        viewPager = findViewById(R.id.viewPager);

        spinner = findViewById(R.id.spinner);

        toolbarTitle = findViewById(R.id.title);

        setUpViewPager();

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageScrolled(final int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {

                if(i!=0)
                {
                    spinner.setVisibility(View.GONE);
                    toolbarTitle.setVisibility(View.VISIBLE);
                    toolbarTitle.setText(tabTitles[i-1]);
                }
                else
                {
                    toolbarTitle.setVisibility(View.GONE);
                    spinner.setVisibility(View.VISIBLE);
                }

            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });

        setUpSpinner();

        spinner.setOnItemSelectedListener(this);

    }

    private void setUpViewPager() {
        NewPostActivityAdapter adapter = new NewPostActivityAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);
    }

    private void setUpSpinner()
    {
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.planets_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        System.out.println(position);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
