
package com.exavalu.agentportal.model;

import java.util.ArrayList;
import java.util.List;

public class RecentViewedDBItems {

    private List<RecentList> recentlyViewed = new ArrayList<RecentList>();

    public List<RecentList> getRecentlyViewed() {
        return recentlyViewed;
    }

    public void setRecentlyViewed(List<RecentList> recentlyViewed) {
        this.recentlyViewed = recentlyViewed;
    }
}
