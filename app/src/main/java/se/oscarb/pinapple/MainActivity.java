package se.oscarb.pinapple;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.activeandroid.query.Delete;

import java.util.List;

/*
 * TODO: Add icon
 * TODO: Adjust to landscape/tablet
 * TODO: Check user input
 * TODO: Error message
 * TODO: Keep scroll position on rotation

 */

public class MainActivity extends AppCompatActivity implements AddCodeDialogFragment.AddCodeDialogListener {


    // Fields
    private static final String TAG = MainActivity.class.getSimpleName();

    private List<Code> codeList;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter codeAdapter;
    private RecyclerView.LayoutManager layoutManager;

    private int passcode;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Started from PasscodeActivity?
        Intent fromPasscodeIntent = getIntent();
        passcode = fromPasscodeIntent.getIntExtra(PasscodeActivity.PASSCODE_MESSAGE, -1);

        // TODO: Check earlier in the Activity Lifecycle

        // No passcode entered or not started from PasscodeActivity
        if(passcode == -1) {

            // Start PasscodeActivity
            Intent toPasscodeIntent = new Intent(this, PasscodeActivity.class);
            startActivity(toPasscodeIntent);

        }



        // Initialize fields
        codeList = Code.getAll();


        // Fill codeList



        codeAdapter = new CodeAdapter(codeList, passcode);

        // Initialize RecyclerView
        recyclerView = (RecyclerView) findViewById(R.id.code_list_layout);
        recyclerView.setAdapter(codeAdapter);
        //recyclerView.setHasFixedSize(true); // improves performance
        // Set LayoutManager
        //layoutManager = new GridLayoutManager(this, GridLayoutManager.DEFAULT_SPAN_COUNT);
        //recyclerView.setLayoutManager(layoutManager);

        //recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(getResources().getInteger(R.integer.spanCount), StaggeredGridLayoutManager.VERTICAL));
        //recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        int columnGutterInPixels = getResources().getDimensionPixelSize(R.dimen.gutter);
        recyclerView.addItemDecoration(new CodeCardItemDecoration(columnGutterInPixels));
        //recyclerView.addItemDecoration(new ItemDec);

        // TODO: Add onItemLongClickListener for removal of codes?

        // Passcode entered
        // Toast.makeText(MainActivity.this, passcode, Toast.LENGTH_SHORT).show();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        assert fab != null;
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Open AddCodeDialogFragment
                DialogFragment addCodeDialog = new AddCodeDialogFragment();
                addCodeDialog.show(getSupportFragmentManager(), "addCodeDialog");
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            int codeListSize = codeAdapter.getItemCount();
            new Delete().from(Code.class).execute();
            codeList.clear();
            codeAdapter.notifyItemRangeRemoved(0, codeListSize);

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    // Implemented from AddCodeDialogListener within AddCodeDialogFragment
    @Override
    public void onAddCodeDialogPositiveClick(DialogFragment dialog, String label, int value) {
        //Toast.makeText(MainActivity.this, code.toString(), Toast.LENGTH_SHORT).show();
        int codeListSize = codeAdapter.getItemCount();

        // Save to database
        Crypto crypto = new XorCrypto();
        int encryptedValue = crypto.decrypt(value, passcode);

        Code code = new Code(label, encryptedValue);
        code.save();

        codeList.add(code);
        codeAdapter.notifyItemInserted(codeListSize);

        // Scroll to last card added
        recyclerView.scrollToPosition(codeAdapter.getItemCount() - 1);

        Log.i(TAG, "Encrypted: " + crypto.encrypt(code.getEncryptedValue(), passcode));
        Log.i(TAG, "Encrypted and decrypted: " + crypto.decrypt(crypto.encrypt(code.getEncryptedValue(), passcode), passcode));


    }

    // Implemented from AddCodeDialogListener within AddCodeDialogFragment
    @Override
    public void onAddCodeDialogNegativeClick(DialogFragment dialog) {
        // No action
    }
}
