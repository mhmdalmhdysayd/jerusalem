package com.example.cov19.Models;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class Symptoms {
    String duration_of_illness;
    List<String> less_symptoms;
    List<String> serious_symptoms;
    String cov_19;
    List<String> most_symptoms;

    public Symptoms() {
    }

    public Symptoms(String duration_of_illness, List<String> less_symptoms, List<String> serious_symptoms, String cov_19, List<String> most_symptoms) {
        this.duration_of_illness = duration_of_illness;
        this.less_symptoms = less_symptoms;
        this.serious_symptoms = serious_symptoms;
        this.cov_19 = cov_19;
        this.most_symptoms = most_symptoms;
    }

    public String getDuration_of_illness() {
        return duration_of_illness;
    }

    public void setDuration_of_illness(String duration_of_illness) {
        this.duration_of_illness = duration_of_illness;
    }

    public List<String> getLess_symptoms() {
        return less_symptoms;
    }

    public void setLess_symptoms(List<String> less_symptoms) {
        this.less_symptoms = less_symptoms;
    }

    public List<String> getSerious_symptoms() {
        return serious_symptoms;
    }

    public void setSerious_symptoms(List<String> serious_symptoms) {
        this.serious_symptoms = serious_symptoms;
    }

    public String getCov_19() {
        return cov_19;
    }

    public void setCov_19(String cov_19) {
        this.cov_19 = cov_19;
    }

    public List<String> getMost_symptoms() {
        return most_symptoms;
    }

    public void setMost_symptoms(List<String> most_symptoms) {
        this.most_symptoms = most_symptoms;
    }
}
