package com.internshipManagement.ongc.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "INTERNS")
public class Interns {

    @Id
    @Column(name = "ID", updatable = false, nullable = false, length = 36)
    private String internId;

    @Column(name = "NAME", nullable = false, length = 100)
    private String name;

    @Column(name = "FATHER_NAME", length = 100)
    private String fatherName;

    @Column(name = "EMAIL", nullable = false, length = 100, unique = true)
    private String email;

    @Column(name = "PHONE", nullable = false, length = 20)
    private String phone;

    @Column(name = "ALTERNATE_PHONE", length = 20)
    private String alternatePhone;

    @Column(name = "DATE_OF_BIRTH")
    private Date dateOfBirth;

    @Column(name = "GENDER", length = 10)
    private String gender;

    @Column(name = "ADDRESS")
    private String address;

    @Column(name = "CITY", length = 50)
    private String city;

    @Column(name = "STATE", length = 50)
    private String state;

    @Column(name = "PINCODE", length = 20)
    private String pincode;

    @Column(name = "COLLEGE", nullable = false, length = 100)
    private String college;

    @Column(name = "UNIVERSITY", length = 100)
    private String university;

    @Column(name = "COURSE", nullable = false, length = 50)
    private String course;

    @Column(name = "BRANCH", nullable = false, length = 50)
    private String branch;

    @Column(name = "CURRENT_YEAR", length = 20)
    private String currentYear;

    @Column(name = "CURRENT_SEMESTER")
    private Integer currentSemester;

    @Column(name = "CURRENT_CGPA", precision = 3, scale = 2)
    private BigDecimal currentCgpa;

    @Column(name = "LAST_SEMESTER_SGPA", precision = 3, scale = 2)
    private BigDecimal lastSemesterSgpa;

    @Column(name = "EXPECTED_GRADUATION")
    private Date expectedGraduation;

    @Column(name = "INTERNSHIP_TYPE", nullable = false, length = 20)
    private String internshipType;

    @Column(name = "START_DATE", nullable = false)
    private Date startDate;

    @Column(name = "END_DATE", nullable = false)
    private Date endDate;

    @Column(name = "PROJECT", length = 100)
    private String project;

    @Column(name = "PROJECT_DOMAIN", nullable = false, length = 100)
    private String projectDomain;

    @Column(name = "PREFERRED_DEPARTMENT", nullable = false, length = 100)
    private String preferredDepartment;

    @Column(name = "BIODATA_FILE_NAME", length = 255)
    private String biodataFileName;

    @Column(name = "BIODATA_FILE_URL", length = 255)
    private String biodataFileUrl;

    @Column(name = "BIODATA_UPLOADED_AT")
    private Timestamp biodataUploadedAt;

    @Column(name = "QUICK_NOTES")
    private String quickNotes;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ASSIGNED_MENTOR_ID")
    private UserAccount assignedMentor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CURRENT_MENTOR_ID")
    private UserAccount currentMentor;

    @Column(name = "STATUS", nullable = false, length = 20)
    private String status;

    @Column(name = "RECOMMENDED_FOR_CERTIFICATE", nullable = false)
    private Boolean recommendedForCertificate;

    @Column(name = "CREATED_AT", nullable = false)
    private Timestamp createdAt;

    @Column(name = "LAST_UPDATED", nullable = false)
    private Timestamp lastUpdated;

    @PrePersist
    protected void onCreate() {
        Timestamp now = new Timestamp(System.currentTimeMillis());
        this.createdAt = now;
        this.lastUpdated = now;
        if (this.internId == null) {
            this.internId = java.util.UUID.randomUUID().toString();
        }
    }

    @PreUpdate
    protected void onUpdate() {
        this.lastUpdated = new Timestamp(System.currentTimeMillis());
    }

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

    public UserAccount getAssignedMentor() {
        return assignedMentor;
    }

    public void setAssignedMentor(UserAccount assignedMentor) {
        this.assignedMentor = assignedMentor;
    }

    public UserAccount getCurrentMentor() {
        return currentMentor;
    }

    public void setCurrentMentor(UserAccount currentMentor) {
        this.currentMentor = currentMentor;
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
