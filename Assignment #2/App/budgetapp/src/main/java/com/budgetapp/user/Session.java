package com.budgetapp.user;

import java.util.Date;

public class Session {
    private int sessionId;
    private int userId ;
    private Date startTime ;
    private Date endTime ;
    private boolean isActive ;

    // The Constructor
    public Session(int sessionId, int userId, Date startTime, Date endTime, boolean isActive) {
        this.sessionId = sessionId;
        this.userId = userId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.isActive = isActive;
    }
    // The getter functions
    public int getSessionId() {
        return sessionId;
    }
    public int getUserId() {
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
    public void setSessionId(int sessionId) {
        this.sessionId = sessionId;
    }
    public void setUserId(int userId) {
        this.userId = userId;
    }
    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }
    public void setActive(boolean isActive) {
        this.isActive = isActive;
    }
    public boolean checkSession() {
        Date now = new Date(); // gets current time
        if (now.after(startTime) && now.before(endTime)) {
            isActive = false;
            // The session is invalid
            return false;
        } else {
            isActive = true;
            return true;
        }
    }

}
