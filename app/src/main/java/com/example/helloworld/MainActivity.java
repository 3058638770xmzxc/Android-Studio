package com.example.helloworld;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    // 定义变量来保存UI元素
    private TextView helloText;
    private Button clickButton;
    private int clickCount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); // 设置布局文件

        // 初始化UI元素
        helloText = findViewById(R.id.helloTextView);
        clickButton = findViewById(R.id.clickButton);

        // 设置按钮点击事件
        clickButton.setOnClickListener(v -> {
            clickCount++; // 增加点击计数
            helloText.setText("Hello World!\n点击次数: " + clickCount);

            // 显示一个短暂的提示消息
            Toast.makeText(MainActivity.this, "你好! 这是第 " + clickCount + " 次点击", Toast.LENGTH_SHORT).show();
        });
    }
}