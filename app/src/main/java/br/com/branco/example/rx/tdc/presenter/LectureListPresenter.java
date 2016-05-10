package br.com.branco.example.rx.tdc.presenter;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import br.com.branco.example.rx.tdc.model.Lecture;
import br.com.branco.example.rx.tdc.service.ReactiveLectureInMemoryService;
import br.com.branco.example.rx.tdc.service.ReactiveLectureService;
import br.com.branco.example.rx.tdc.view.LectureListView;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by guilhermebranco on 5/4/16.
 */
public class LectureListPresenter implements LectureListView.AddFilterClickListener, LectureListView.SelectFilterListener{

    private static final String TAG = "LectureListPresenter";

    private ReactiveLectureService reactiveLectureService;
    private LectureListView lectureListView;

    public LectureListPresenter(){
        this.reactiveLectureService = new ReactiveLectureInMemoryService();
    }

    public void initialize(LectureListView lectureListView){
        this.lectureListView = lectureListView;
        this.lectureListView.setAddFilterClickListener(this);
        this.lectureListView.setSelectFilterListener(this);
        this.loadLectures();
    }

    public void loadLectures(){
        getLectures()
                .subscribe(subscriber());
    }

    public void loadLecturesByDay(final String day){
        lectureListView.showFilter(day);
        getLectures()
                .flatMap(new Func1<List<Lecture>, Observable<Lecture>>() {
                    @Override
                    public Observable<Lecture> call(List<Lecture> lectures) {
                        return Observable.from(lectures);
                    }
                })
                .filter(new Func1<Lecture, Boolean>() {
                    @Override
                    public Boolean call(Lecture lecture) {
                        return lecture.getDay().equalsIgnoreCase(day);
                    }
                })
                .toList()
                .subscribe(subscriber());
    }

    private Observable<List<Lecture>> getLectures(){
        return reactiveLectureService
                .getLectures()
                .doOnSubscribe(showLoading())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    private Subscriber subscriber(){
        return new Subscriber<List<Lecture>>() {
            @Override
            public void onCompleted() {
                Log.d(TAG, "onCompleted");
                hideLoading();
            }

            @Override
            public void onError(Throwable e) {
                Log.e(TAG, "onError: " + e.getMessage());
            }

            @Override
            public void onNext(List<Lecture> lectures) {
                Log.d(TAG, "onNext");
                LectureListPresenter.this.lectureListView.renderItems(lectures);
            }
        };
    }

    public Action0 showLoading(){
        return new Action0() {
            @Override
            public void call() {
                lectureListView.showLoading();
            }
        };
    }

    public void hideLoading(){
        this.lectureListView.hideLoading();
    }

    @Override
    public void onClick() {
        getLectures()
                .flatMap(new Func1<List<Lecture>, Observable<Lecture>>() {
                    @Override
                    public Observable<Lecture> call(List<Lecture> lectures) {
                        return Observable.from(lectures);
                    }
                }).flatMap(new Func1<Lecture, Observable<String>>() {
                    @Override
                    public Observable<String> call(Lecture lecture) {
                        return Observable.just(lecture.getDay());
                    }
                }).map(new Func1<String, CharSequence>() {
                    @Override
                    public CharSequence call(String filter) {
                        return filter;
                    }
                })
                .distinct()
                .toList()
                .subscribe(new Subscriber<List<CharSequence>>() {
                    @Override
                    public void onCompleted() {
                        Log.d(TAG, "onCompleted");
                    }

                    @Override
                    public void onError(Throwable error) {
                        Log.d(TAG, "onError: " + error.getMessage());
                    }

                    @Override
                    public void onNext(List<CharSequence> filters) {
                        Log.d(TAG, "onNext");
                        LectureListPresenter.this.lectureListView.showFilterSelectionWith((ArrayList<CharSequence>) filters);
                    }
                });
    }

    @Override
    public void onSelectedFilter(String filter) {
        loadLecturesByDay(filter);
        this.lectureListView.showFilter(filter);
    }
}
