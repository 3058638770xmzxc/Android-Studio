# HelloWorld

一个简单的Android HelloWorld应用，用于演示Android应用开发的基本流程。

## 功能介绍

- 显示"Hello World!"文本
- 响应式设计，适配不同屏幕尺寸

## 技术栈

- **开发语言**: Java
- **开发工具**: Android Studio
- **构建工具**: Gradle
- **最低SDK版本**: API 24 (Android 7.0)
- **目标SDK版本**: API 34 (Android 14)

## 项目结构

```
HelloWorld/
├── app/
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/com/example/helloworld/
│   │   │   │   └── MainActivity.java     # 主活动类
│   │   │   ├── res/                     # 资源文件
│   │   │   │   ├── drawable/            # 可绘制资源
│   │   │   │   ├── layout/              # 布局文件
│   │   │   │   │   └── activity_main.xml # 主布局
│   │   │   │   ├── mipmap-*/            # 图标资源
│   │   │   │   └── values/              # 配置值
│   │   │   └── AndroidManifest.xml      # 应用清单
│   │   └── test/                        # 测试代码
│   └── build.gradle.kts                 # 模块构建配置
├── gradle/                              # Gradle配置
├── build.gradle.kts                     # 项目构建配置
└── settings.gradle.kts                  # 项目设置
```

## 如何运行

### 前置条件

- 安装 [Android Studio](https://developer.android.com/studio)
- 配置Android开发环境
- 拥有Android设备或模拟器

### 步骤

1. 克隆项目到本地
   ```bash
   git clone https://github.com/your-username/HelloWorld.git
   ```

2. 打开Android Studio，选择"Open an existing project"

3. 导航到项目目录，点击"OK"

4. 等待Gradle同步完成

5. 连接Android设备或启动模拟器

6. 点击运行按钮（绿色三角形）或使用快捷键 `Shift + F10`

## 截图
