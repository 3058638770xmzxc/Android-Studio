package com.example.listviews;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main1);

        // 在Activity创建时显示自定义AlertDialog
        showCustomAlertDialog();
    }

    /**
     * 创建并显示自定义布局的AlertDialog
     */
    private void showCustomAlertDialog() {
        // 创建AlertDialog.Builder对象
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        // 设置对话框标题（可选，因为我们在自定义布局中已经有标题）
        builder.setTitle("Android App");

        // 使用LayoutInflater加载自定义布局
        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_custom, null);

        // 获取自定义布局中的控件
        final EditText etUsername = dialogView.findViewById(R.id.etUsername);
        final EditText etPassword = dialogView.findViewById(R.id.etPassword);
        Button btnCancel = dialogView.findViewById(R.id.btnCancel);
        Button btnSignIn = dialogView.findViewById(R.id.btnSignIn);

        // 使用setView()将自定义布局添加到AlertDialog
        builder.setView(dialogView);

        // 创建AlertDialog对象
        final AlertDialog dialog = builder.create();

        // 设置Cancel按钮点击事件
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity1.this, "Login cancelled", Toast.LENGTH_SHORT).show();
                dialog.dismiss(); // 关闭对话框
            }
        });

        // 设置Sign in按钮点击事件
        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = etUsername.getText().toString().trim();
                String password = etPassword.getText().toString().trim();

                // 简单的输入验证
                if (username.isEmpty()) {
                    etUsername.setError("Please enter username");
                    return;
                }

                if (password.isEmpty()) {
                    etPassword.setError("Please enter password");
                    return;
                }

                // 登录成功处理
                Toast.makeText(MainActivity1.this,
                        "Sign in successful for: " + username, Toast.LENGTH_SHORT).show();
                dialog.dismiss(); // 关闭对话框
            }
        });

        // 设置对话框的取消监听器（点击对话框外部或返回键）
        dialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                Toast.makeText(MainActivity1.this, "Dialog cancelled", Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * 可以在其他事件中调用此方法重新显示对话框
     */
    public void showDialogAgain(View view) {
        showCustomAlertDialog();
    }
}