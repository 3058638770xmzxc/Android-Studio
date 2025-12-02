package com.example.listviews;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

public class XmlMenuActivity extends AppCompatActivity {
    private TextView menuText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xml_menu);
        menuText = findViewById(R.id.menu_text);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.menu_font_small) {
            menuText.setTextSize(10);
            return true;
        } else if (id == R.id.menu_font_medium) {
            menuText.setTextSize(16);
            return true;
        } else if (id == R.id.menu_font_large) {
            menuText.setTextSize(20);
            return true;
        } else if (id == R.id.menu_normal) {
            Toast.makeText(this, "普通菜单项被点击", Toast.LENGTH_SHORT).show();
            return true;
        } else if (id == R.id.menu_color_red) {
            menuText.setTextColor(ContextCompat.getColor(this, R.color.red));
            return true;
        } else if (id == R.id.menu_color_black) {
            menuText.setTextColor(ContextCompat.getColor(this, R.color.black));
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }
}