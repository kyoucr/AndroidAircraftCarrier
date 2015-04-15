package com.footprint.androidaircraftcarrier.main;

/**
 * Created by liquanmin on 15/4/15.
 */
public class ActivityItem {
    private String description;
    private String dataUrl;

    public ActivityItem(String description, String dataUrl) {
        this.description = description;
        this.dataUrl = dataUrl;
    }

    public String getDataUrl() {
        return dataUrl;
    }

    public void setDataUrl(String dataUrl) {
        this.dataUrl = dataUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
