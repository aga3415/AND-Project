package al.taskmasterprojinz;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.Toast;

import Database.DbAdapter;
import PreparingData.CurrentCreatingTask;
import PreparingData.PrepareListOfTask;

/**
 * Created by Agnieszka on 2015-05-11.
 */
public class MyTasksForDate extends TasksListViewPattern {

    static MyTasksForDate instance;

    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        initList();
        listAdapter.notifyDataSetChanged();
        instance = this;

    }

    protected void onResume(){
        super.onResume();
        initList();
        listAdapter.notifyDataSetChanged();
    }

    protected void onRestart(){
        super.onRestart();
        initList();
        listAdapter.notifyDataSetChanged();
    }

    private void initList(){

        prepTask = PrepareListOfTask.getInstance(this);
        expListView = (ExpandableListView) findViewById(R.id.expandableListView);

        newTask = CurrentCreatingTask.getInstance();
        if(newTask.getDate_plan_exec().isEmpty()) {
            listAdapter = prepTask.todayTomorrowInFutureTaskLists();
            header_task_list.setVisibility(View.GONE);
        }
        else{
            listAdapter = prepTask.tasksForGivenDate(newTask.getDate_plan_exec());
            System.out.println("ZAINICJOWANO LIST ADAPTERA");

        }

        expListView.setAdapter(listAdapter);
        if (listAdapter.canExpandFirstGroup()){
            expListView.expandGroup(0); //mozna rozwijac tylko wtedy kiedy lista nie jest pusta
        }

    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        if(item.getTitle().equals(res.getString(R.string.remove_task_menu_item1))){
            function2(item.getItemId());
        }
        else if(item.getTitle().equals(res.getString(R.string.remove_task_menu_item2))){
            function1(item.getItemId());
        }
        else {return false;}
        return true;
    }
    public void function1(int id){
        Toast.makeText(this, res.getString(R.string.removed_completed_task), Toast.LENGTH_SHORT).show();
        DbAdapter db = DbAdapter.getInstance(getApplicationContext());
        db.deleteCompletedTasks();

        initList();
        listAdapter.notifyDataSetChanged();
    }

    public void function2(int id){
        Toast.makeText(this, res.getString(R.string.removed_all_task), Toast.LENGTH_SHORT).show();
        DbAdapter db = DbAdapter.getInstance(getApplicationContext());
        db.deleteAllTasks();

        initList();
        listAdapter.notifyDataSetChanged();
    }




}
