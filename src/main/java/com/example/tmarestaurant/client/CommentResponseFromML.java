package com.example.tmarestaurant.client;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import javax.annotation.Generated;
import java.util.List;

//@Data
@Generated("jsonschema2pojo")
public class CommentResponseFromML {

    @SerializedName("predictions")
    @Expose
    private List<Prediction> predictions = null;

    public List<Prediction> getPredictions() {
        return predictions;
    }

    public void setPredictions(List<Prediction> predictions) {
        this.predictions = predictions;
    }

    @Override
    public String toString() {
        return "CommentResponseFromML{" +
                "predictions=" + predictions +
                '}';
    }
}
