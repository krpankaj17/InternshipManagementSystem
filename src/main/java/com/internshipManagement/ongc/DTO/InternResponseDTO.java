package com.internshipManagement.ongc.DTO;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;

public class InternResponseDTO {

    private String internId;
    private String name;
    private String fatherName;
    private String email;
    private String phone;
    private String alternatePhone;
    private Date dateOfBirth;
    private String gender;
    private String address;
    private String city;
    private String state;
    private String pincode;
    private String college;
    private String university;
    private String course;
    private String branch;
    private String currentYear;
    private Integer currentSemester;
    private BigDecimal currentCgpa;
    private BigDecimal lastSemesterSgpa;
    private Date expectedGraduation;
    private String internshipType;
    private Date startDate;
    private Date endDate;
    private String project;
    private String projectDomain;
    private String preferredDepartment;
    private String biodataFileName;
    private String biodataFileUrl;
    private Timestamp biodataUploadedAt;
    private String quickNotes;
    private String assignedMentorId;
    private String assignedMentorName;
    private String currentMentorId;
    private String currentMentorName;
    private String status;
    private Boolean recommendedForCertificate;
    private Timestamp createdAt;
    private Timestamp lastUpdated;

    // Getters and Setters

    public String getInternId() {
        return internId;
    }

    public void setInternId(String internId) {
        this.internId = internId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFatherName() {
        return fatherName;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAlternatePhone() {
        return alternatePhone;
    }

    public void setAlternatePhone(String alternatePhone) {
        this.alternatePhone = alternatePhone;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

    public String getCollege() {
        return college;
    }

    public void setCollege(String college) {
        this.college = college;
    }

    public String getUniversity() {
        return university;
    }

    public void setUniversity(String university) {
        this.university = university;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getCurrentYear() {
        return currentYear;
    }

    public void setCurrentYear(String currentYear) {
        this.currentYear = currentYear;
    }

    public Integer getCurrentSemester() {
        return currentSemester;
    }

    public void setCurrentSemester(Integer currentSemester) {
        this.currentSemester = currentSemester;
    }

    public BigDecimal getCurrentCgpa() {
        return currentCgpa;
    }

    public void setCurrentCgpa(BigDecimal currentCgpa) {
        this.currentCgpa = currentCgpa;
    }

    public BigDecimal getLastSemesterSgpa() {
        return lastSemesterSgpa;
    }

    public void setLastSemesterSgpa(BigDecimal lastSemesterSgpa) {
        this.lastSemesterSgpa = lastSemesterSgpa;
    }

    public Date getExpectedGraduation() {
        return expectedGraduation;
    }

    public void setExpectedGraduation(Date expectedGraduation) {
        this.expectedGraduation = expectedGraduation;
    }

    public String getInternshipType() {
        return internshipType;
    }

    public void setInternshipType(String internshipType) {
        this.internshipType = internshipType;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public String getProjectDomain() {
        return projectDomain;
    }

    public void setProjectDomain(String projectDomain) {
        this.projectDomain = projectDomain;
    }

    public String getPreferredDepartment() {
        return preferredDepartment;
    }

    public void setPreferredDepartment(String preferredDepartment) {
        this.preferredDepartment = preferredDepartment;
    }

    public String getBiodataFileName() {
        return biodataFileName;
    }

    public void setBiodataFileName(String biodataFileName) {
        this.biodataFileName = biodataFileName;
    }

    public String getBiodataFileUrl() {
        return biodataFileUrl;
    }

    public void setBiodataFileUrl(String biodataFileUrl) {
        this.biodataFileUrl = biodataFileUrl;
    }

    public Timestamp getBiodataUploadedAt() {
        return biodataUploadedAt;
    }

    public void setBiodataUploadedAt(Timestamp biodataUploadedAt) {
        this.biodataUploadedAt = biodataUploadedAt;
    }

    public String getQuickNotes() {
        return quickNotes;
    }

    public void setQuickNotes(String quickNotes) {
        this.quickNotes = quickNotes;
    }

    public String getAssignedMentorId() {
        return assignedMentorId;
    }

    public void setAssignedMentorId(String assignedMentorId) {
        this.assignedMentorId = assignedMentorId;
    }

    public String getAssignedMentorName() {
        return assignedMentorName;
    }

    public void setAssignedMentorName(String assignedMentorName) {
        this.assignedMentorName = assignedMentorName;
    }

    public String getCurrentMentorId() {
        return currentMentorId;
    }

    public void setCurrentMentorId(String currentMentorId) {
        this.currentMentorId = currentMentorId;
    }

    public String getCurrentMentorName() {
        return currentMentorName;
    }

    public void setCurrentMentorName(String currentMentorName) {
        this.currentMentorName = currentMentorName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Boolean getRecommendedForCertificate() {
        return recommendedForCertificate;
    }

    public void setRecommendedForCertificate(Boolean recommendedForCertificate) {
        this.recommendedForCertificate = recommendedForCertificate;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public Timestamp getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(Timestamp lastUpdated) {
        this.lastUpdated = lastUpdated;
    }
}
