package br.com.branco.example.rx.tdc.service;

import java.util.List;

import br.com.branco.example.rx.tdc.model.Lecture;
import rx.Observable;

/**
 * Created by guilhermebranco on 4/30/16.
 */
public interface ReactiveLectureService {

    Observable<List<Lecture>> getLectures();
}
