package com.example.staff_precyapp.Model;

public class UpComingEventsModel {
    String name;
    String when;
    String where;

    public UpComingEventsModel(String name, String when, String where) {
        this.name = name;
        this.when = when;
        this.where = where;
    }

    public String getName() {
        return name;
    }

    public String getWhen() {
        return when;
    }

    public String getWhere() {
        return where;
    }
}
