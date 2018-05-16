package com.itland.employer.entities;

import com.itland.employer.abstracts.AbstractEntity;

/**
 * Created by Saad on 5/14/2018.
 */

public class Language extends AbstractEntity {
/*
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
*/
    public Integer Id;
    public String LanguageName;
    public String ReadingLevel;
    public String WritingLevel;
    public String SpeakingLevel;
}
