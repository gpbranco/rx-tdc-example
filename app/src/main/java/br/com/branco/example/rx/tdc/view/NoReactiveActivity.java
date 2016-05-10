package br.com.branco.example.rx.tdc.view;


import android.os.AsyncTask;

import java.util.ArrayList;
import java.util.List;

import br.com.branco.example.rx.tdc.model.Lecture;
import br.com.branco.example.rx.tdc.service.InMemoryLectureService;
import br.com.branco.example.rx.tdc.service.LectureService;

/**
 * Created by guilhermebranco on 5/5/16.
 */
public class NoReactiveActivity extends BaseActivity {

    private LectureService lectureService;

    @Override
    protected void onResume() {
        super.onResume();
        lectureService = new InMemoryLectureService();
        new LectureAsyncTask().execute();
    }

    @Override
    protected void click() {

    }

    @Override
    public void showFilter(String filter) {

    }

    @Override
    public void showFilterSelectionWith(ArrayList<CharSequence> filters) {

    }

    @Override
    public void setAddFilterClickListener(AddFilterClickListener addFilterClickListener) {

    }

    @Override
    public void setSelectFilterListener(SelectFilterListener selectFilterListener) {

    }

    private class LectureAsyncTask extends AsyncTask<String, Void, List<Lecture>>{

        @Override
        protected void onPreExecute() {
            showLoading();
        }

        @Override
        protected List<Lecture> doInBackground(String... strings) {
            return lectureService.getLectures();
        }

        @Override
        protected void onPostExecute(List<Lecture> lectures) {
            hideLoading();
            renderItems(lectures);
        }
    }
}
