package com.budgetapp.user;

import java.util.Date;

public class Session {
    private String sessionId;
    private String userId ;
    private Date startTime ;
    private Date endTime ;
    private boolean isActive ;

    // The Constructor
    public Session(String sessionId, String userId, Date startTime, Date endTime ) {
        this.sessionId = sessionId;
        this.userId = userId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.isActive = true ;
    }
    // The getter functions
    public String getSessionId() {
        return sessionId;
    }
    public String getUserId() {
        return userId;
    }
    public Date getStartTime() {
        return startTime;
    }
    public Date getEndTime() {
        return endTime;
    }
    public boolean isActive() {
        return isActive;
    }
    // The setter functions :
    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }
    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }
    public void endSession() {
        this.isActive = false ;
    }
    public void checkSessionIfExpired() {
        Date now = new Date(); // gets current time
        if (now.after(startTime) && now.before(endTime)) {
            isActive = false;
            // The session is invalid
        } else {
            isActive = true;
        }
    }
}
