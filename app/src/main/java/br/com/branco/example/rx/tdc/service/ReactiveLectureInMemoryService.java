package br.com.branco.example.rx.tdc.service;

import java.util.List;

import br.com.branco.example.rx.tdc.model.Lecture;
import rx.Observable;

/**
 * Created by guilhermebranco on 5/4/16.
 */
public class ReactiveLectureInMemoryService implements ReactiveLectureService {

    @Override
    public Observable<List<Lecture>> getLectures() {
        return Observable.just(new InMemoryLectureService().getLectures());
    }
}
