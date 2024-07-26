
package com.exavalu.agentportal.model.GWINRecentViewed;

import java.util.ArrayList;
import java.util.List;

public class RecentViewedItems {

    private List<RecentlyViewed> recentlyViewed = new ArrayList<RecentlyViewed>();

    public List<RecentlyViewed> getRecentlyViewed() {
        return recentlyViewed;
    }

    public void setRecentlyViewed(List<RecentlyViewed> recentlyViewed) {
        this.recentlyViewed = recentlyViewed;
    }
}
