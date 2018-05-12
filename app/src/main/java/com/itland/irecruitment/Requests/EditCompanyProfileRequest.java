package com.itland.irecruitment.Requests;

import com.itland.irecruitment.entities.AbstractEntity;

/**
 * Created by Saad on 5/10/2018.
 */

public class EditCompanyProfileRequest {
/*
    {
        "Id": 0,
            "ArLogoUrl": "string",
            "EnLogoUrl": "string",
            "ArName": "string",
            "EnName": "string",
            "CityId": 0,
            "ArAddress": "string",
            "EnAddress": "string",
            "IndustryId": 0,
            "ArAbout": "string",
            "EnAbout": "string",
            "CommercialRegister": "string",
            "POBox": 0,
            "PersonalDetailsTitleId": 0,
            "PersonalDetailsFirstName": "string",
            "PersonalDetailsLastName": "string",
            "PersonalDetailsposition": "string"
    }
*/
    public Integer Id;
    public String ArLogoUrl;
    public String EnLogoUrl;
    public String ArName;
    public String EnName;
    public Integer CityId;
    public String ArAddress;
    public String EnAddress;
    public Integer IndustryId;
    public String ArAbout;
    public String EnAbout;
    public String CommercialRegister;
    public Integer POBox;
    public Integer PersonalDetailsTitleId;
    public String PersonalDetailsFirstName;
    public String PersonalDetailsLastName;
    public String PersonalDetailsposition;
}
