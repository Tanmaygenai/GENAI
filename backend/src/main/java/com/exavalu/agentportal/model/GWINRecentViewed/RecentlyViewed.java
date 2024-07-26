
package com.exavalu.agentportal.model.GWINRecentViewed;

public class RecentlyViewed {

    private String ref;
    private String typeCd;
    private String owner;
    private String status;
    private String effectiveDt;
    private String insuredName;
    private String product;

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    public RecentlyViewed withRef(String ref) {
        this.ref = ref;
        return this;
    }

    public String getTypeCd() {
        return typeCd;
    }

    public void setTypeCd(String typeCd) {
        this.typeCd = typeCd;
    }

    public RecentlyViewed withTypeCd(String typeCd) {
        this.typeCd = typeCd;
        return this;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public RecentlyViewed withOwner(String owner) {
        this.owner = owner;
        return this;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public RecentlyViewed withStatus(String status) {
        this.status = status;
        return this;
    }

    public String getEffectiveDt() {
        return effectiveDt;
    }

    public void setEffectiveDt(String effectiveDt) {
        this.effectiveDt = effectiveDt;
    }

    public RecentlyViewed withEffectiveDt(String effectiveDt) {
        this.effectiveDt = effectiveDt;
        return this;
    }

    public String getInsuredName() {
        return insuredName;
    }

    public void setInsuredName(String insuredName) {
        this.insuredName = insuredName;
    }

    public RecentlyViewed withInsuredName(String insuredName) {
        this.insuredName = insuredName;
        return this;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public RecentlyViewed withProduct(String product) {
        this.product = product;
        return this;
    }
}
