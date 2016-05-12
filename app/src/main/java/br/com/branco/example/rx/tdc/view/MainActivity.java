package br.com.branco.example.rx.tdc.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;

import br.com.branco.example.rx.tdc.R;
import br.com.branco.example.rx.tdc.presenter.LectureListPresenter;

public class MainActivity extends BaseActivity{

    private static final String TAG = "MainActivity";

    private LectureListPresenter presenter;
    private AddFilterClickListener addFilterClickListener;
    private SelectFilterListener selectFilterListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new LectureListPresenter();
    }

    @Override
    protected void click() {
        this.addFilterClickListener.onClick();
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.initialize(this);
    }

    @Override
    public void showLoading() {
        Log.d(TAG, "showLoading");
    }

    @Override
    public void hideLoading() {
        Log.d(TAG, "hideLoading");
    }

    @Override
    public void showFilter(String filter) {
        tvFilters.setText("Filtros: " + filter);
    }

    @Override
    public void showFilterSelectionWith(ArrayList<CharSequence> filters) {
        FilterDialog filterDialog = new FilterDialog();
        filterDialog.setSelectFilterListener(this.selectFilterListener);

        Bundle bundle = new Bundle();
        bundle.putCharSequenceArrayList(Constants.Extra.FILTERS, filters);
        bundle.putString(Constants.Extra.TITLE, getString(R.string.filter_days_title));
        filterDialog.setArguments(bundle);

        FragmentManager manager = getSupportFragmentManager();
        filterDialog.show(manager, "");
    }

    @Override
    public void setAddFilterClickListener(AddFilterClickListener addFilterClickListener) {
        this.addFilterClickListener = addFilterClickListener;
    }

    @Override
    public void setSelectFilterListener(SelectFilterListener selectFilterListener) {
        this.selectFilterListener = selectFilterListener;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            goToNotReactiveActivity();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void goToNotReactiveActivity(){
        Intent intent = new Intent(this, NoReactiveActivity.class);
        startActivity(intent);
    }

}
