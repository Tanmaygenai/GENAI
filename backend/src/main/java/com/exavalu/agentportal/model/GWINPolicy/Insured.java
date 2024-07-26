
package com.exavalu.agentportal.model.GWINPolicy;
import java.util.ArrayList;
import java.util.List;
public class Insured {
    private String indexName;
    private List<PartyInfo> partyInfo = new ArrayList<PartyInfo>();
    public String getIndexName() {
        return indexName;
    }
    public void setIndexName(String indexName) {
        this.indexName = indexName;
    }
    public List<PartyInfo> getPartyInfo() {
        return partyInfo;
    }
    public void setPartyInfo(List<PartyInfo> partyInfo) {
        this.partyInfo = partyInfo;
    }

}
