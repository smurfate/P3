package com.itland.irecruitment.Requests;

/**
 * Created by Saad on 5/9/2018.
 */

public class GetItemsRequest {
/*
    {
        "OrderByOptions": {},
        "MinCreateDate": "2018-05-09T15:45:16.002Z",
            "MaxCreateDate": "2018-05-09T15:45:16.002Z",
            "CategoryId": 0,
            "ListingId": 0,
            "DealerId": "string",
            "Status": 0,
            "SearchQuery": "string",
            "IncludeHistoryOnly": true,
            "IncludeFavoritesOnly": true,
            "OrderBy": "string",
            "Page": 0,
            "PageLength": 0,
            "SortDirection": 0,
            "ElementsToBeSkipedCount": 0
    }
*/
    public String MinCreateDate;
    public String MaxCreateDate;
    public Integer CategoryId;
    public Integer ListingId;
    public String DealerId;
    public Integer Status;
    public Boolean IncludeHistoryOnly;
    public Boolean IncludeFavoritesOnly;
    public String OrderBy;
    public String SearchQuery;
    public Integer Page;
    public Integer PageLength;
    public Integer SortDirection;
    public Integer ElementsToBeSkipedCount;
}
