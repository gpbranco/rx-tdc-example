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


    public static final String QUARTA_FEIRA = "Quarta-Feira";
    public static final String QUINTA_FEIRA = "Quinta-Feira";
    public static final String SEXTA_FEIRA = "Sexta-Feira";
    public static final String SABADO = "Sábado";

    public static final Track MOBILE = new Track("Mobile");
    public static final Track ANDROID = new Track("Android");
    public static final Track ARQUITETURA = new Track("Arquitetura");
    public static final Track IOS = new Track("iOS");
    public static final Track DOT_NET = new Track(".NET");
    public static final Track JAVA = new Track("Java");
    public static final Track CLOUD_COMPUTING = new Track("Cloud Computing");
    public static final Track XAMARIN = new Track("Xamarin");

    @Override
    public List<Lecture> getLectures() {
        List<Lecture> lectures = new ArrayList<>();
        lectures.add(newLecture("Integração em Nuvem Hibridas", QUARTA_FEIRA, "09:30", CLOUD_COMPUTING));
        lectures.add(newLecture("Dockers in the sky with Diamonds", QUARTA_FEIRA, "10:10", CLOUD_COMPUTING));
        lectures.add(newLecture("Inovação na Escala da AWS ", QUARTA_FEIRA, "14:10", CLOUD_COMPUTING));
        lectures.add(newLecture("Trazendo mais produtividade no desenvolvimento de layouts em Xamarin.Forms com Gorilla", QUARTA_FEIRA, "10:10", XAMARIN));
        lectures.add(newLecture("Xamarin Forms e os problemas que você deve evitar", QUARTA_FEIRA, "13:10", XAMARIN));
        lectures.add(newLecture("Como manter a qualidade dos nossos Aplicativos?", QUARTA_FEIRA, "14:10", XAMARIN));
        lectures.add(newLecture("Padrões de Arquitetura de Alto Desempenho e Confiabilidade", QUINTA_FEIRA, "09:30", ARQUITETURA));
        lectures.add(newLecture("Arquitetura: Débito técnico zero", QUINTA_FEIRA, "10:30", ARQUITETURA));
        lectures.add(newLecture("Escalando para os Primeiros Milhões de Usuários ", QUINTA_FEIRA, "14:30", ARQUITETURA));
        lectures.add(newLecture("Mais e mais Builds Simultâneas no Snap CI", QUINTA_FEIRA, "17:30", ARQUITETURA));
        lectures.add(newLecture("Notification Center Widgets", QUINTA_FEIRA, "10:30", IOS));
        lectures.add(newLecture("ReactiveCocoa - Abordagem reactive no iOS", QUINTA_FEIRA, "11:30", IOS));
        lectures.add(newLecture("3D Touch", QUINTA_FEIRA, "11:30", IOS));
        lectures.add(newLecture("tvOS - Showcasing ECHO", QUINTA_FEIRA, "11:30", IOS));
        lectures.add(newLecture("Android Nativo x Híbrido? Quando? Quanto? Por que?", SEXTA_FEIRA, "09:30", ANDROID));
        lectures.add(newLecture("Melhora de performance em aplicativos Android", SEXTA_FEIRA, "10:30", ANDROID));
        lectures.add(newLecture("Entrando no mundo nativo com o Android NDK", SEXTA_FEIRA, "14:30", ANDROID));
        lectures.add(newLecture("Introdução à Reactive Programming no Android", SEXTA_FEIRA, "17:30", ANDROID));
        lectures.add(newLecture("Desmistificando o .NET Core", SEXTA_FEIRA, "17:30", DOT_NET));
        lectures.add(newLecture("Docker Container Development ? Como se preparar para o futuro", SEXTA_FEIRA, "17:30", DOT_NET));
        lectures.add(newLecture("Firmata e o Arduíno com C#, tão fácil que parece brincadeira", SEXTA_FEIRA, "17:30", DOT_NET));
        lectures.add(newLecture("Parse (.com) morreu e agora?", SABADO, "09:30", MOBILE));
        lectures.add(newLecture("Aplicações Híbridas com Intel XDK e Ionic", SABADO, "10:30", MOBILE));
        lectures.add(newLecture("Mobilizando seus dados com Datazen", SABADO, "14:30", MOBILE));
        lectures.add(newLecture("Smart App Monetization Strategies for Startups", SABADO, "17:30", MOBILE));
        lectures.add(newLecture("Criando aplicações Java para nuvem com Spring Boot", SABADO, "11:30", JAVA));
        lectures.add(newLecture("Gradle: o melhor amigo no build do seu software.", SABADO, "14:10", JAVA));
        lectures.add(newLecture("Kotlin 1.0: Evolua seu código Java", SABADO, "15:10", JAVA));

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
