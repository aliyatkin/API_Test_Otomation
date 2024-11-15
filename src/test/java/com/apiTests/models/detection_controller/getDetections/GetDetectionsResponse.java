package com.apiTests.models.detection_controller.getDetections;

public class GetDetectionsResponse {

    private String id;
    private boolean hasImage;
    private String description;
    private Long detectionCreateTime;
    private Long detectionUpdateTime;
    private Location location;
    private Zone zone;
    private boolean updated;
    private String signature;
    private String taskId;
    private String taskStatus;
    private Type type;

    public GetDetectionsResponse(){

    }

    public GetDetectionsResponse(String id, boolean hasImage, String description, Long detectionCreateTime, Long detectionUpdateTime, Location location, Zone zone, boolean updated, String signature, String taskId, String taskStatus, Type type) {
        this.id = id;
        this.hasImage = hasImage;
        this.description = description;
        this.detectionCreateTime = detectionCreateTime;
        this.detectionUpdateTime = detectionUpdateTime;
        this.location = location;
        this.zone = zone;
        this.updated = updated;
        this.signature = signature;
        this.taskId = taskId;
        this.taskStatus = taskStatus;
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isHasImage() {
        return hasImage;
    }

    public void setHasImage(boolean hasImage) {
        this.hasImage = hasImage;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getDetectionCreateTime() {
        return detectionCreateTime;
    }

    public void setDetectionCreateTime(Long detectionCreateTime) {
        this.detectionCreateTime = detectionCreateTime;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Long getDetectionUpdateTime() {
        return detectionUpdateTime;
    }

    public void setDetectionUpdateTime(Long detectionUpdateTime) {
        this.detectionUpdateTime = detectionUpdateTime;
    }

    public Zone getZone() {
        return zone;
    }

    public void setZone(Zone zone) {
        this.zone = zone;
    }

    public boolean isUpdated() {
        return updated;
    }

    public void setUpdated(boolean updated) {
        this.updated = updated;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(String taskStatus) {
        this.taskStatus = taskStatus;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }
}


