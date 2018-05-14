package com.itland.irecruitment.entities;

import com.itland.irecruitment.abstracts.AbstractEntity;

import java.util.List;

/**
 * Created by Saad on 5/14/2018.
 */

public class ResumeDetails extends AbstractEntity {
/*
    {
        "Id": 0,
            "Name": "string",
            "Status": "string",
            "PersonalDetails": {
        "ProfilePictureUrl": "string",
                "FirstName": "string",
                "LastName": "string",
                "IsMale": true,
                "Nationality": "string",
                "MilitaryServiceStatus": "string",
                "IsSingle": true,
                "Address": "string",
                "NumberOfDependencies": 0,
                "DateOfBirth": "string",
                "Email": "string",
                "Country": "string",
                "City": "string",
                "PhoneNumber": "string",
                "MobilePhone": "string",
                "DrivingLicenceType": "string",
                "DrivingLicenceExpiryDate": "string",
                "ProfilePrivacy": "string",
                "UploadedResumeUrl": "string",
                "MotherLanguage": "string",
                "IsOk": true,
                "Message": {
            "Type": "string",
                    "Content": "string"
        }
    },
        "JobTarget": {
        "Id": 0,
                "CareerObjectives": "string",
                "Title": "string",
                "JobType": "string",
                "Category": "string",
                "CareerLevel": "string",
                "City": "string",
                "NoticePeriod": "string",
                "DesiredNetSalary": 0,
                "DesiredNetSalaryCurrency": "string",
                "LastMonthSalary": 0,
                "LastMonthSalaryCurrency": "string",
                "IsOk": true,
                "Message": {
            "Type": "string",
                    "Content": "string"
        }
    },
        "WorkExperiences": [
        {
            "Id": 0,
                "CompanyName": "string",
                "JobTitle": "string",
                "StartDate": "string",
                "EndDate": "string",
                "FieldOfWork": "string",
                "YearsOfExperience": 0,
                "IsOk": true,
                "Message": {
            "Type": "string",
                    "Content": "string"
        }
        }
  ],
        "EducationDegrees": [
        {
            "Id": 0,
                "Name": "string",
                "EducationInstitution": "string",
                "DegreeLevel": "string",
                "Major": "string",
                "Rate": 0,
                "Country": "string",
                "City": "string",
                "Description": "string",
                "GraduationYear": 0,
                "IsOk": true,
                "Message": {
            "Type": "string",
                    "Content": "string"
        }
        }
  ],
        "Skills": [
        {
            "Id": 0,
                "Name": "string",
                "Level": "string",
                "YearsOfExperiences": 0,
                "LastUsedYear": 0,
                "LastUsedMonth": "string",
                "IsOk": true,
                "Message": {
            "Type": "string",
                    "Content": "string"
        }
        }
  ],
        "Languages": [
        {
            "Id": 0,
                "LanguageName": "string",
                "ReadingLevel": "string",
                "WritingLevel": "string",
                "SpeakingLevel": "string",
                "IsOk": true,
                "Message": {
            "Type": "string",
                    "Content": "string"
        }
        }
  ],
        "References": [
        {
            "Id": 0,
                "FullName": "string",
                "CompanyName": "string",
                "JobTitle": "string",
                "Phone": "string",
                "OfficeNumber": "string",
                "Email": "string",
                "IsOk": true,
                "Message": {
            "Type": "string",
                    "Content": "string"
        }
        }
  ],
        "IsOk": true,
            "Message": {
        "Type": "string",
                "Content": "string"
    }
    }
*/

    public Integer Id;
    public String Name;
    public String Status;
    public PersonalDetails PersonalDetails;
    public JobTarget JobTarget;
    public List<WorkExperience> WorkExperiences;
    public List<EducationDegree> EducationDegrees;
    public List<Skill> Skills;
    public List<Language> Languages;
    public List<Reference> References;




}
