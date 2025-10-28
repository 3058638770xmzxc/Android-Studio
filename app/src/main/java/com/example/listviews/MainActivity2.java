package com.example.listviews;

import android.os.Bundle;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity2 extends AppCompatActivity {

    private ListView listView;
    private SimpleAdapter adapter;
    private ActionMode actionMode;
    private List<Integer> selectedItems = new ArrayList<>();

    // 数据源 - 与图片完全匹配
    private List<Map<String, Object>> dataList = new ArrayList<>();
    private String[] itemNames = {"One", "Two", "Three", "Four", "Five"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        initListView();
        setupActionMode();
    }

    private void initListView() {
        listView = findViewById(R.id.listView);

        // 初始化数据源 - 与图片完全匹配
        initData();

        // 创建适配器 - 使用list_item2布局
        adapter = new SimpleAdapter(
                this,
                dataList,
                R.layout.list_item2,
                new String[]{"name"},
                new int[]{R.id.itemName}
        );

        listView.setAdapter(adapter);

        // 设置多选模式
        listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);
    }

    private void initData() {
        // 清空现有数据
        dataList.clear();

        // 添加新数据 - 与图片完全匹配
        for (int i = 0; i < itemNames.length; i++) {
            Map<String, Object> map = new HashMap<>();
            map.put("name", itemNames[i]);
            dataList.add(map);
        }
    }

    private void setupActionMode() {
        // 设置多选模式监听器
        listView.setMultiChoiceModeListener(new AbsListView.MultiChoiceModeListener() {
            @Override
            public boolean onCreateActionMode(ActionMode mode, Menu menu) {
                // 加载ActionMode菜单 - 只保留删除功能
                mode.getMenuInflater().inflate(R.menu.context_menu, menu);
                actionMode = mode;
                updateActionModeTitle();
                return true;
            }

            @Override
            public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
                return false;
            }

            @Override
            public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
                int itemId = item.getItemId();

                if (itemId == R.id.action_delete) {
                    // 删除选中的项目
                    deleteSelectedItems();
                    mode.finish();
                    return true;
                }
                return false;
            }

            @Override
            public void onDestroyActionMode(ActionMode mode) {
                // 清除选中状态
                clearSelection();
                actionMode = null;
            }

            @Override
            public void onItemCheckedStateChanged(ActionMode mode, int position,
                                                  long id, boolean checked) {
                // 更新选中项目极简列表
                if (checked) {
                    if (!selectedItems.contains(position)) {
                        selectedItems.add(position);
                    }
                } else {
                    selectedItems.remove(Integer.valueOf(position));
                }

                updateActionModeTitle();
            }
        });

        // 设置长按启动ActionMode
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                listView.setItemChecked(position, true);
                return true;
            }
        });

        // 设置点击事件
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (actionMode != null) {
                    // 在ActionMode模式下，点击切换选中状态
                    listView.setItemChecked(position, !listView.isItemChecked(position));
                } else {
                    // 正常模式下，点击显示详情
                    Toast.makeText(MainActivity2.this,
                            "Clicked: " + itemNames[position], Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void updateActionModeTitle() {
        if (actionMode != null) {
            int count = selectedItems.size();
            // 与图片完全匹配的标题格式
            if (count == 1) {
                actionMode.setTitle("1 selected");
            } else {
                actionMode.setTitle(count + " selected");
            }
        }
    }

    private void deleteSelectedItems() {
        if (selectedItems.isEmpty()) {
            Toast.makeText(this, "No items selected", Toast.LENGTH_SHORT).show();
            return;
        }

        // 对选中的索引进行排序（从大到小），这样删除时不会影响其他索引
        Collections.sort(selectedItems, Collections.reverseOrder());

        // 从数据源中删除选中的项目
        for (int position : selectedItems) {
            if (position < dataList.size()) {
                dataList.remove(position);
            }
        }

        // 通知适配器数据已更改
        adapter.notifyDataSetChanged();

        Toast.makeText(this, "Deleted " + selectedItems.size() + " items",
                Toast.LENGTH_SHORT).show();

        // 清除选中状态
        clearSelection();
    }

    private void clearSelection() {
        selectedItems.clear();
        for (int i = 0; i < listView.getCount(); i++) {
            listView.setItemChecked(i, false);
        }
    }
}