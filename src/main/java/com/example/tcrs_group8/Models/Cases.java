package com.example.tcrs_group8.Models;

import java.util.Date;

public class Cases {
    private int caseID;
    private int userID;
    private String officerName;
    private String officerNotes;
    private Date offenceDate;
    private int offenceNumber;
    private String pastOffences;

    // Constructor
    public Cases(int caseID, int userID, String officerName, String officerNotes, Date offenceDate, int offenceNumber, String pastOffences) {
        this.caseID = caseID;
        this.userID = userID;
        this.officerName = officerName;
        this.officerNotes = officerNotes;
        this.offenceDate = offenceDate;
        this.offenceNumber = offenceNumber;
        this.pastOffences = pastOffences;
    }

    // Getters and setters
    public int getCaseID() {
        return caseID;
    }

    public void setCaseID(int caseID) {
        this.caseID = caseID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getOfficerName() {
        return officerName;
    }

    public void setOfficerName(String officerName) {
        this.officerName = officerName;
    }

    public String getOfficerNotes() {
        return officerNotes;
    }

    public void setOfficerNotes(String officerNotes) {
        this.officerNotes = officerNotes;
    }

    public Date getOffenceDate() {
        return offenceDate;
    }

    public void setOffenceDate(Date offenceDate) {
        this.offenceDate = offenceDate;
    }

    public int getOffenceNumber() {
        return offenceNumber;
    }

    public void setOffenceNumber(int offenceNumber) {
        this.offenceNumber = offenceNumber;
    }

    public String getPastOffences() {
        return pastOffences;
    }

    public void setPastOffences(String pastOffences) {
        this.pastOffences = pastOffences;
    }

    // toString method for debugging purposes
    @Override
    public String toString() {
        return "Case{" +
                "caseID=" + caseID +
                ", userID=" + userID +
                ", officerName='" + officerName + '\'' +
                ", officerNotes='" + officerNotes + '\'' +
                ", offenceDate=" + offenceDate +
                ", offenceNumber=" + offenceNumber +
                ", pastOffences='" + pastOffences + '\'' +
                '}';
    }
}
