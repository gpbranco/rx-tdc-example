package br.com.branco.example.rx.tdc.service;

import br.com.branco.example.rx.tdc.model.Lecture;
import rx.Observable;

/**
 * Created by guilhermebranco on 4/30/16.
 */
public interface ReactiveLectureService {

    Observable<Lecture> getLectures();
}
