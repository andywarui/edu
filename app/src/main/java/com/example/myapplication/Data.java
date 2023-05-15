package com.example.myapplication;

public class Data {
    private String assignmentsDue;
    private String courseID;
    private String courseName;
    private String isAssessmentSubmission;
    private String isLecturerPostsLectureNotes;
    private String lecturerName;
    private String studentID;
    private String studentName;
    private String studentSignedUpInformation;

    public Data() {
    }
    public Data(String assignmentsDue, String courseID, String courseName, String isAssessmentSubmission,
                String isLecturerPostsLectureNotes, String lecturerName, String studentID, String studentName, String studentSignedUpInformation) {
        this.assignmentsDue = assignmentsDue;
        this.courseID = courseID;
        this.courseName = courseName;
        this.isAssessmentSubmission=isAssessmentSubmission;
        this.isLecturerPostsLectureNotes = isLecturerPostsLectureNotes;
        this.lecturerName=lecturerName;
        this.studentID = studentID;
        this.studentName = studentName;
        this.studentSignedUpInformation = studentSignedUpInformation;
    }

    public String getStudentID() {
        return studentID;
    }

    public String getStudentName() {
        return studentName;
    }

    public String getCourseID() {
        return courseID;
    }

    public String getCourseName() {
        return courseName;
    }

    public String getLecturerName() {
        return lecturerName;
    }

    public String getIsLecturerPostsLectureNotes() {
        return isLecturerPostsLectureNotes;
    }

    public String getAssignmentsDue() {
        return assignmentsDue;
    }

    public String getIsAssessmentSubmission() {
        return isAssessmentSubmission;
    }

    public String getStudentSignedUpInformation() {
        return studentSignedUpInformation;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public void setCourseID(String courseID) {
        this.courseID = courseID;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public void setLecturerName(String lecturerName) {
        this.lecturerName = lecturerName;
    }

    public void setIsLecturerPostsLectureNotes(String isLecturerPostsLectureNotes) {
        this.isLecturerPostsLectureNotes = isLecturerPostsLectureNotes;
    }

    public void setAssignmentsDue(String assignmentsDue) {
        this.assignmentsDue = assignmentsDue;
    }

    public void setIsAssessmentSubmission(String isAssessmentSubmission) {
        this.isAssessmentSubmission = isAssessmentSubmission;
    }

    public void setStudentSignedUpInformation(String studentSignedUpInformation) {
        this.studentSignedUpInformation = studentSignedUpInformation;
    }
}
