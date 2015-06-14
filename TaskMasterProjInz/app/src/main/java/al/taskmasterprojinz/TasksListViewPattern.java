package al.taskmasterprojinz;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ExpandableListView;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

import DataModel.MyDate;
import Database.DbAdapter;
import PreparingData.CurrentCreatingTask;
import PreparingData.PrepareListOfTask;
import PreparingViewsAdapter.ExpandableTaskListAdapter;


public class TasksListViewPattern extends Menu {

    ExpandableTaskListAdapter listAdapter;
    ExpandableListView expListView;

    TextView header_task_list;
    ImageButton calendar, addTask, removeTask, menu;

    PrepareListOfTask prepTask;

    Context context;

    DatePickerDialog datePickerDialog;
    CurrentCreatingTask newTask;

    Resources res;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_llist_vie_pattern);
        res = getApplicationContext().getResources();
        context = getApplicationContext();
        initUIElements();
    }

    private void initUIElements(){

        header_task_list = (TextView) findViewById(R.id.task_list_header_title);

        calendar = (ImageButton) findViewById(R.id.calendar_button);
        addTask = (ImageButton) findViewById(R.id.add_task_button);
        removeTask = (ImageButton) findViewById(R.id.delete_button);

        initOnClickListeners();


    }

    private void initOnClickListeners() {
        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.calendar_button:
                        chooseDate();
                        //pierwszy przycisk po lewej
                        break;
                    case R.id.add_task_button:
                        editNewTask();
                        //znak +
                        break;
                }
            }
        };

        calendar.setOnClickListener(onClickListener);
        addTask.setOnClickListener(onClickListener);

        registerForContextMenu(removeTask);
    }

    private void chooseDate(){

        al.taskmasterprojinz.DatePickerDialog.showDatePickerDialog(context, this);

    }


    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    private void editNewTask(){
        Intent edit_task_activity = new Intent(getApplicationContext(), CreateTask.class);
        startActivity(edit_task_activity);
        int result = 0;
        startActivityForResult(edit_task_activity, result, Bundle.EMPTY);
    }









}
