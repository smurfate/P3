package com.itland.irecruitment;

import com.google.gson.Gson;
import com.itland.irecruitment.Responses.BannersListResponse;
import com.itland.irecruitment.Responses.GeneralListResponse;
import com.itland.irecruitment.entities.Banner;
import com.itland.irecruitment.entities.Zone;

import org.junit.Test;

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
                "      \"ImageUrl\": \"string\",\n" +
                "      \"IsOk\": true,\n" +
                "      \"Message\": {\n" +
                "        \"Type\": \"string\",\n" +
                "        \"Content\": \"string\"\n" +
                "      }\n" +
                "    }\n" +
                "  ],\n" +
                "  \"IsOk\": true,\n" +
                "  \"Message\": {\n" +
                "    \"Type\": \"string\",\n" +
                "    \"Content\": \"string\"\n" +
                "  }\n" +
                "}";
        GeneralListResponse lst = gson.fromJson(s, GeneralListResponse.class);
        List<Banner> lstss = lst.Items();
        Banner b = lstss.get(0);
    }
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }
}