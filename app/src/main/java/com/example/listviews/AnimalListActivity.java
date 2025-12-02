package com.example.listviews;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AnimalListActivity extends AppCompatActivity {

    private ListView animalListView;
    private Button animalButton;
    private NotificationManager notificationManager;
    private static final String CHANNEL_ID = "ANIMAL_CHANNEL";

    // 动物名称数组（中文）
    private String[] animalNames = {"狮子", "老虎", "猴子", "狗", "猫", "大象"};

    // 动物图片资源ID数组
    private int[] animalImages = {
            R.drawable.lion,
            R.drawable.tiger,
            R.drawable.monkey,
            R.drawable.dog,
            R.drawable.cat,
            R.drawable.elephant
    };

    // 当前选中的动物索引
    private int selectedAnimalIndex = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animal_list);

        initViews();
        setupListView();
        setupNotificationChannel();
        setClickListeners();

        // 调试信息
        Toast.makeText(this, "应用已启动，请点击动物头像", Toast.LENGTH_SHORT).show();
    }

    private void initViews() {
        animalListView = findViewById(R.id.animalListView);
        animalButton = findViewById(R.id.animalButton);

        // 初始隐藏按钮
        animalButton.setVisibility(View.GONE);
    }

    private void setupListView() {
        // 创建数据源
        List<Map<String, Object>> dataList = new ArrayList<>();

        for (int i = 0; i < animalNames.length; i++) {
            Map<String, Object> map = new HashMap<>();
            map.put("name", animalNames[i]);
            map.put("image", animalImages[i]);
            dataList.add(map);
        }

        // 创建SimpleAdapter
        SimpleAdapter adapter = new SimpleAdapter(
                this,
                dataList,
                R.layout.list_item,
                new String[]{"name", "image"},
                new int[]{R.id.animalName, R.id.animalImage}
        );

        animalListView.setAdapter(adapter);
    }

    private void setupNotificationChannel() {
        notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(
                    CHANNEL_ID,
                    "动物通知频道",
                    NotificationManager.IMPORTANCE_DEFAULT
            );
            channel.setDescription("显示动物选择通知");
            notificationManager.createNotificationChannel(channel);
        }
    }

    private void setClickListeners() {
        // 列表项点击事件 - 修复点击响应
        animalListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // 更新选中的动物索引
                selectedAnimalIndex = position;

                // 显示点击响应 - 修复Toast显示
                String message = "你点击了" + animalNames[position] + "动物";
                Toast.makeText(AnimalListActivity.this, message, Toast.LENGTH_SHORT).show();

                // 更新按钮显示
                updateAnimalButton();

                // 显示按钮
                animalButton.setVisibility(View.VISIBLE);
            }
        });

        // 动物按钮点击事件
        animalButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectedAnimalIndex != -1) {
                    // 显示按钮点击响应
                    String message = "查看" + animalNames[selectedAnimalIndex] + "详情";
                    Toast.makeText(AnimalListActivity.this, message, Toast.LENGTH_SHORT).show();

                    // 发送通知
                    sendNotification(animalNames[selectedAnimalIndex]);
                }
            }
        });
    }

    private void updateAnimalButton() {
        if (selectedAnimalIndex == -1) return;

        // 设置按钮文本
        animalButton.setText("查看" + animalNames[selectedAnimalIndex] + "详情");

        // 设置按钮左侧图标
        try {
            Drawable icon = ContextCompat.getDrawable(this, animalImages[selectedAnimalIndex]);
            if (icon != null) {
                icon.setBounds(0, 0, 60, 60);
                animalButton.setCompoundDrawables(icon, null, null, null);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void sendNotification(String animalName) {
        try {
            // 创建PendingIntent
            Intent intent = new Intent(this, AnimalListActivity.class);
            PendingIntent pendingIntent = PendingIntent.getActivity(
                    this,
                    0,
                    intent,
                    PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE
            );

            // 构建通知
            NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID)
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setContentTitle(animalName + "详情")
                    .setContentText("您查看了" + animalName + "的信息")
                    .setContentIntent(pendingIntent)
                    .setAutoCancel(true)
                    .setPriority(NotificationCompat.PRIORITY_DEFAULT);

            // 显示通知
            notificationManager.notify(animalName.hashCode(), builder.build());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}