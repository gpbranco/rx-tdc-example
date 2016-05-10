package br.com.branco.example.rx.tdc.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import br.com.branco.example.rx.tdc.model.Lecture;
import br.com.branco.example.rx.tdc.model.Track;

/**
 * Created by guilhermebranco on 5/5/16.
 */
public class InMemoryLectureService implements LectureService {


    public static final String QUINTA_FEIRA = "Quinta-Feira";
    public static final String SEXTA_FEIRA = "Sexta-Feira";
    public static final String SABADO = "Sábado";

    public static final Track MOBILE = new Track("Mobile");
    public static final Track ANDROID = new Track("Android");
    public static final Track ARQUITETURA = new Track("Arquitetura");

    @Override
    public List<Lecture> getLectures() {
        List<Lecture> lectures = new ArrayList<>();
        lectures.add(newLecture("Padrões de Arquitetura de Alto Desempenho e Confiabilidade", QUINTA_FEIRA, "09:30", ARQUITETURA));
        lectures.add(newLecture("Arquitetura: Débito técnico zero", QUINTA_FEIRA, "10:30", ARQUITETURA));
        lectures.add(newLecture("Escalando para os Primeiros Milhões de Usuários ", QUINTA_FEIRA, "14:30", ARQUITETURA));
        lectures.add(newLecture("Mais e mais Builds Simultâneas no Snap CI", QUINTA_FEIRA, "17:30", ARQUITETURA));
        lectures.add(newLecture("Android Nativo x Híbrido? Quando? Quanto? Por que?", SEXTA_FEIRA, "09:30", ANDROID));
        lectures.add(newLecture("Melhora de performance em aplicativos Android", SEXTA_FEIRA, "10:30", ANDROID));
        lectures.add(newLecture("Entrando no mundo nativo com o Android NDK", SEXTA_FEIRA, "14:30", ANDROID));
        lectures.add(newLecture("Introdução à Reactive Programming no Android", SEXTA_FEIRA, "17:30", ANDROID));
        lectures.add(newLecture("Parse (.com) morreu e agora?", SABADO, "09:30", MOBILE));
        lectures.add(newLecture("Aplicações Híbridas com Intel XDK e Ionic", SABADO, "10:30", MOBILE));
        lectures.add(newLecture("Mobilizando seus dados com Datazen", SABADO, "14:30", MOBILE));
        lectures.add(newLecture("Smart App Monetization Strategies for Startups", SABADO, "17:30", MOBILE));

        sortByName(lectures);

        return lectures;
    }

    private void sortByName(List<Lecture> lectures) {
        Collections.sort(lectures, new Comparator<Lecture>() {
            @Override
            public int compare(Lecture lecture, Lecture other) {
                return lecture.getName().compareTo(other.getName());
            }
        });
    }

    private Lecture newLecture(String name, String day, String hour, Track track){
        return new Lecture.Builder()
                .name(name)
                .hour(hour)
                .day(day)
                .description(name)
                .speaker(name)
                .track(track)
                .build();
    }
}
