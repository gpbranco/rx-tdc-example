package br.com.branco.example.rx.tdc.view;

/**
 * Created by guilhermebranco on 5/5/16.
 */

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import br.com.branco.example.rx.tdc.R;
import br.com.branco.example.rx.tdc.adapter.LectureAdapter;
import br.com.branco.example.rx.tdc.model.Lecture;

public abstract class BaseActivity extends AppCompatActivity implements LectureListView{

    private RecyclerView recyclerView;
    protected LectureAdapter lectureAdapter;
    protected TextView tvFilters;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        lectureAdapter = new LectureAdapter(this, new ArrayList<Lecture>());

        tvFilters = (TextView) findViewById(R.id.filter);

        recyclerView = (RecyclerView) findViewById(R.id.lectures);
        recyclerView.setAdapter(lectureAdapter);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new LectureAdapter.SimpleDividerItemDecoration(this));

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                click();
            }
        });
    }

    @Override
    public void renderItems(List<Lecture> lectures) {
        this.lectureAdapter.setLectureList(lectures);
        this.lectureAdapter.notifyDataSetChanged();
    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showLoading() {

    }

    protected abstract void click();

}

