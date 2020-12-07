package sam.io.capstoneapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import sam.io.capstoneapp.Adapters.AdapterSample;

public class SwipeSample extends AppCompatActivity {
    private RecyclerView recyclerView;
    private AdapterSample adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swipe_sample);
        setupList();
    }

    private void setupList() {
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new AdapterSample(this, createList(20));
        recyclerView.setAdapter(adapter);
    }

    private List<String> createList(int n) {
        List<String> list = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            list.add("View " + i);
        }

        return list;
    }
}