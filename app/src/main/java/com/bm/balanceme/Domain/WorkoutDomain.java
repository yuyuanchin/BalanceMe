package com.bm.balanceme.Domain;

public class WorkoutDomain {
    private long id;
    private String workoutTitle;
    private String difficulty;
    private String duration;
    private int workoutImg;

    public WorkoutDomain() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getWorkoutTitle() {
        return workoutTitle;
    }

    public void setWorkoutTitle(String workoutTitle) {
        this.workoutTitle = workoutTitle;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public int getWorkoutImg() {
        return workoutImg;
    }

    public void setWorkoutImg(int workoutImg) {
        this.workoutImg = workoutImg;
    }

    public WorkoutDomain(String workoutTitle, String difficulty, String duration, int workoutImg) {
        this.workoutTitle = workoutTitle;
        this.difficulty = difficulty;
        this.duration= duration;
        this.workoutImg = workoutImg;
    }


}
