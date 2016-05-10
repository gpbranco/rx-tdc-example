package br.com.branco.example.rx.tdc.model;

/**
 * Created by guilhermebranco on 4/30/16.
 */
public class Lecture {

    private String name;
    private String description;
    private String speaker;
    private String day;
    private String hour;
    private Track track;

    public String getName(){
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getSpeaker() {
        return speaker;
    }

    public String getDay() {
        return day;
    }

    public String getHour() {
        return hour;
    }

    public Track getTrack() {
        return track;
    }

    public static class Builder{
        private Lecture lecture;

        public Builder(){
            this.lecture = new Lecture();
        }

        public Lecture build(){
            return lecture;
        }

        public Builder name(String name){
            this.lecture.name = name;
            return this;
        }

        public Builder description(String description){
            this.lecture.description = description;
            return this;
        }

        public Builder speaker(String speaker){
            this.lecture.speaker = speaker;
            return this;
        }

        public Builder day(String day){
            this.lecture.day = day;
            return this;
        }

        public Builder hour(String hour){
            this.lecture.hour = hour;
            return this;
        }

        public Builder track(Track track){
            this.lecture.track = track;
            return this;
        }
    }

}