package br.com.raiana.raianapereirafbexample.appparte2accelerometer;

import android.database.Cursor;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Spinner;

import br.com.raiana.raianapereirafbexample.appparte2accelerometer.DB.DBHelper;
import br.com.raiana.raianapereirafbexample.appparte2accelerometer.DB.NodeController;

public class DBActivity extends AppCompatActivity {

    static Spinner nodes;
    static Cursor cursor;
    static String[] fieldNames;
    static int[] idViews;
    NodeController controller;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_db);

        nodes = (Spinner) findViewById(R.id.spinner);
        controller = new NodeController(this);

        fillAdapter();
    }

    public void fillAdapter(){
        cursor = controller.selectAll();
        fieldNames = new String[]{DBHelper.NODE_ID_COLUMN, DBHelper.NODE_CONTENT_COLUMN,
                DBHelper.NODE_PARENTID_COLUMN};
        idViews = new int[]{R.id.txtIDNode, R.id.txtContentNode,
                R.id.txtNodeParent};
        SimpleCursorAdapter adaptador = new SimpleCursorAdapter(this,
                R.layout.spinner_layout, cursor, fieldNames, idViews, 0);
        nodes.setAdapter(adaptador);
    }
}
