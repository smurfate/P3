package com.itland.irecruitment;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.itland.irecruitment.entities.City;
import com.itland.irecruitment.entities.Zone;

import org.junit.Test;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void GsonTest()
    {
        Gson gson = new Gson();
        String s = "{\n" +
                "  \"Items\": [\n" +
                "    {\n" +
                "      \"Id\": 0,\n" +
                "      \"ArName\": \"string\",\n" +
                "      \"EnName\": \"string\",\n" +
                "      \"IsActive\": true,\n" +
                "      \"CountryName\": \"string\"\n" +
                "    }\n" +
                "  ],\n" +
                "  \"IsOk\": true,\n" +
                "  \"Message\": {\n" +
                "    \"Type\": \"string\",\n" +
                "    \"Content\": \"string\"\n" +
                "  }\n" +
                "}";
//        Type collectionType = new TypeToken<GeneralListResponse<City>>(){}.getType();
//        GeneralListResponse lst = gson.fromJson(s, collectionType);
//        List<City> lstss = lst.Items;
//        City b = lstss.get(0);
    }
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }
}