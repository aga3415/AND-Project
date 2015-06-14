package al.taskmasterprojinz;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ImageButton;

import DataModel.MyDate;
import PreparingData.CurrentCreatingTask;

/**
 * Created by Agnieszka on 2015-06-15.
 */
public class DatePickerDialog{

    public static void showDatePickerDialog(final Context context, final Activity activity){
        LayoutInflater li = LayoutInflater.from(context);
        View datePickerView = li.inflate(R.layout.activity_get_date, null);

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(new ContextThemeWrapper(activity, R.style.AppDialogTheme));//new AlertDialog.Builder(this);

        alertDialogBuilder.setView(datePickerView);

        final DatePicker datePicker = (DatePicker) datePickerView.findViewById(R.id.datePicker);
        final ImageButton cancel = (ImageButton) datePickerView.findViewById(R.id.clear_task_button);
        final ImageButton addDate = (ImageButton) datePickerView.findViewById(R.id.save_task_button);

        final AlertDialog alertDialog = alertDialogBuilder.create();

        alertDialog.show();

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.cancel();
            }
        });

        addDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyDate plan_exec = new MyDate(datePicker.getDayOfMonth(),
                        datePicker.getMonth() + 1,
                        datePicker.getYear());

                CurrentCreatingTask newTask = CurrentCreatingTask.getInstance();
                newTask.setDate_plan_exec(plan_exec);
                if(CurrentCreatingTask.getInstance().getDate_plan_exec() != null ){
                    Intent tasksForDate = new Intent(context, MyTasksForDate.class);
                    activity.startActivity(tasksForDate);
                }


            }
        });
    }
}
