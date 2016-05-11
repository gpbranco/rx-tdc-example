package br.com.branco.example.rx.tdc.service;

import br.com.branco.example.rx.tdc.model.Lecture;
import rx.Observable;

/**
 * Created by guilhermebranco on 5/4/16.
 */
public class ReactiveLectureInMemoryService implements ReactiveLectureService {

    @Override
    public Observable<Lecture> getLectures() {
        return Observable.from(new InMemoryLectureService().getLectures());
    }
}
