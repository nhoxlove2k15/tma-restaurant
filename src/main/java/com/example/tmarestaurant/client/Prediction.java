package com.example.tmarestaurant.client;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import javax.annotation.Generated;

@Generated("jsonschema2pojo")
public class Prediction {

    @SerializedName("label")
    @Expose
    private String label;
    @SerializedName("score")
    @Expose
    private Double score;

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "Prediction{" +
                "label='" + label + '\'' +
                ", score=" + score +
                '}';
    }
}