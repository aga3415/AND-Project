package al.taskmasterprojinz;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.view.MenuItem;

/**
 * Created by Agnieszka on 2015-05-09.
 */
public class Menu extends ActionBarActivity {

    @Override
    public boolean onCreateOptionsMenu(android.view.Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.my_menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch (id) {


            case R.id.help :
                //przejscie do aktywnosci z pomocą
                help();
                break;


        }

        return super.onOptionsItemSelected(item);
    }


    public void help(){
        Intent help = new Intent (getApplicationContext(), HelpView.class);
        startActivity(help);
    }


}
