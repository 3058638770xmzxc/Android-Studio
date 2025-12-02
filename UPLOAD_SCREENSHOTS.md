# 如何上传截图到GitHub

以下是将截图上传到GitHub仓库的详细步骤：

## 1. 准备截图

- 在你的Android设备或模拟器上运行应用
- 截取应用的浅色模式和深色模式界面
- 将截图保存到本地电脑上

## 2. 在项目中创建截图文件夹

在项目根目录下创建一个用于存放截图的文件夹，建议命名为`screenshots`：

```bash
mkdir screenshots
```

## 3. 将截图添加到项目中

将你准备好的截图复制到`screenshots`文件夹中，建议使用清晰的命名，例如：

- `light_mode.png` - 浅色模式截图
- `dark_mode.png` - 深色模式截图

## 4. 更新README.md文件

编辑README.md文件，将图片占位符替换为实际的图片链接。使用相对路径指向你的截图文件：

```markdown
## 截图

### 浅色模式
![浅色模式截图](screenshots/light_mode.png)

### 深色模式
![深色模式截图](screenshots/dark_mode.png)
```

## 5. 提交截图到GitHub

使用以下git命令将截图和更新后的README提交到GitHub：

```bash
# 查看当前状态，确认截图文件已被检测到
git status

# 添加所有更改（包括新的截图文件和更新的README）
git add .

# 提交更改，添加有意义的提交信息
git commit -m "Add screenshots and update README"

# 推送到GitHub远程仓库
git push origin main
```

## 6. 验证截图是否成功上传

- 打开你的GitHub仓库页面
- 导航到README.md文件
- 检查截图是否正确显示

## 注意事项

- 截图大小建议控制在合理范围内，避免过大影响加载速度
- 建议使用PNG格式，保证图片质量
- 确保截图清晰展示应用的主要功能
- 可以根据需要添加更多截图，如不同功能页面的截图

## 示例

如果你的截图已成功上传，README中的图片链接会显示为实际图片，而不是占位符。
