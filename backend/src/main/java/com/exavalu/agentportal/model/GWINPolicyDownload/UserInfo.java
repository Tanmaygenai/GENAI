
package com.exavalu.agentportal.model.GWINPolicyDownload;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "systemId",
    "id",
    "updateCount",
    "updateUser",
    "updateTimestamp",
    "loginId",
    "typeCd",
    "superUserInd",
    "password",
    "supervisor",
    "emailAddr",
    "lastName",
    "firstName",
    "startWork",
    "concurrentSessions",
    "statusCd",
    "roles",
    "passwordInfo",
    "defaultLanguageCd",
    "lastLogonDt",
    "lastLogonTm",
    "version",
    "branchCd",
    "beanStats",
    "userTaskControl",
    "partyInfo",
    "changeInfo",
    "managedGroups",
    "_links"
})
@Generated("jsonschema2pojo")
public class UserInfo {

    @JsonProperty("systemId")
    private String systemId;
    @JsonProperty("id")
    private String id;
    @JsonProperty("updateCount")
    private Integer updateCount;
    @JsonProperty("updateUser")
    private String updateUser;
    @JsonProperty("updateTimestamp")
    private String updateTimestamp;
    @JsonProperty("loginId")
    private String loginId;
    @JsonProperty("typeCd")
    private String typeCd;
    @JsonProperty("superUserInd")
    private Boolean superUserInd;
    @JsonProperty("password")
    private String password;
    @JsonProperty("supervisor")
    private String supervisor;
    @JsonProperty("emailAddr")
    private String emailAddr;
    @JsonProperty("lastName")
    private String lastName;
    @JsonProperty("firstName")
    private String firstName;
    @JsonProperty("startWork")
    private String startWork;
    @JsonProperty("concurrentSessions")
    private String concurrentSessions;
    @JsonProperty("statusCd")
    private String statusCd;
    @JsonProperty("roles")
    private List<Role> roles = new ArrayList<Role>();
    @JsonProperty("passwordInfo")
    private PasswordInfo passwordInfo;
    @JsonProperty("defaultLanguageCd")
    private String defaultLanguageCd;
    @JsonProperty("lastLogonDt")
    private String lastLogonDt;
    @JsonProperty("lastLogonTm")
    private String lastLogonTm;
    @JsonProperty("version")
    private String version;
    @JsonProperty("branchCd")
    private String branchCd;
    @JsonProperty("beanStats")
    private List<BeanStat> beanStats = new ArrayList<BeanStat>();
    @JsonProperty("userTaskControl")
    private UserTaskControl userTaskControl;
    @JsonProperty("partyInfo")
    private List<PartyInfo> partyInfo = new ArrayList<PartyInfo>();
    @JsonProperty("changeInfo")
    private List<ChangeInfo> changeInfo = new ArrayList<ChangeInfo>();
    @JsonProperty("managedGroups")
    private List<ManagedGroup> managedGroups = new ArrayList<ManagedGroup>();
    @JsonProperty("_links")
    private List<Link> links = new ArrayList<Link>();
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("systemId")
    public String getSystemId() {
        return systemId;
    }

    @JsonProperty("systemId")
    public void setSystemId(String systemId) {
        this.systemId = systemId;
    }

    public UserInfo withSystemId(String systemId) {
        this.systemId = systemId;
        return this;
    }

    @JsonProperty("id")
    public String getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(String id) {
        this.id = id;
    }

    public UserInfo withId(String id) {
        this.id = id;
        return this;
    }

    @JsonProperty("updateCount")
    public Integer getUpdateCount() {
        return updateCount;
    }

    @JsonProperty("updateCount")
    public void setUpdateCount(Integer updateCount) {
        this.updateCount = updateCount;
    }

    public UserInfo withUpdateCount(Integer updateCount) {
        this.updateCount = updateCount;
        return this;
    }

    @JsonProperty("updateUser")
    public String getUpdateUser() {
        return updateUser;
    }

    @JsonProperty("updateUser")
    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    public UserInfo withUpdateUser(String updateUser) {
        this.updateUser = updateUser;
        return this;
    }

    @JsonProperty("updateTimestamp")
    public String getUpdateTimestamp() {
        return updateTimestamp;
    }

    @JsonProperty("updateTimestamp")
    public void setUpdateTimestamp(String updateTimestamp) {
        this.updateTimestamp = updateTimestamp;
    }

    public UserInfo withUpdateTimestamp(String updateTimestamp) {
        this.updateTimestamp = updateTimestamp;
        return this;
    }

    @JsonProperty("loginId")
    public String getLoginId() {
        return loginId;
    }

    @JsonProperty("loginId")
    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    public UserInfo withLoginId(String loginId) {
        this.loginId = loginId;
        return this;
    }

    @JsonProperty("typeCd")
    public String getTypeCd() {
        return typeCd;
    }

    @JsonProperty("typeCd")
    public void setTypeCd(String typeCd) {
        this.typeCd = typeCd;
    }

    public UserInfo withTypeCd(String typeCd) {
        this.typeCd = typeCd;
        return this;
    }

    @JsonProperty("superUserInd")
    public Boolean getSuperUserInd() {
        return superUserInd;
    }

    @JsonProperty("superUserInd")
    public void setSuperUserInd(Boolean superUserInd) {
        this.superUserInd = superUserInd;
    }

    public UserInfo withSuperUserInd(Boolean superUserInd) {
        this.superUserInd = superUserInd;
        return this;
    }

    @JsonProperty("password")
    public String getPassword() {
        return password;
    }

    @JsonProperty("password")
    public void setPassword(String password) {
        this.password = password;
    }

    public UserInfo withPassword(String password) {
        this.password = password;
        return this;
    }

    @JsonProperty("supervisor")
    public String getSupervisor() {
        return supervisor;
    }

    @JsonProperty("supervisor")
    public void setSupervisor(String supervisor) {
        this.supervisor = supervisor;
    }

    public UserInfo withSupervisor(String supervisor) {
        this.supervisor = supervisor;
        return this;
    }

    @JsonProperty("emailAddr")
    public String getEmailAddr() {
        return emailAddr;
    }

    @JsonProperty("emailAddr")
    public void setEmailAddr(String emailAddr) {
        this.emailAddr = emailAddr;
    }

    public UserInfo withEmailAddr(String emailAddr) {
        this.emailAddr = emailAddr;
        return this;
    }

    @JsonProperty("lastName")
    public String getLastName() {
        return lastName;
    }

    @JsonProperty("lastName")
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public UserInfo withLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    @JsonProperty("firstName")
    public String getFirstName() {
        return firstName;
    }

    @JsonProperty("firstName")
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public UserInfo withFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    @JsonProperty("startWork")
    public String getStartWork() {
        return startWork;
    }

    @JsonProperty("startWork")
    public void setStartWork(String startWork) {
        this.startWork = startWork;
    }

    public UserInfo withStartWork(String startWork) {
        this.startWork = startWork;
        return this;
    }

    @JsonProperty("concurrentSessions")
    public String getConcurrentSessions() {
        return concurrentSessions;
    }

    @JsonProperty("concurrentSessions")
    public void setConcurrentSessions(String concurrentSessions) {
        this.concurrentSessions = concurrentSessions;
    }

    public UserInfo withConcurrentSessions(String concurrentSessions) {
        this.concurrentSessions = concurrentSessions;
        return this;
    }

    @JsonProperty("statusCd")
    public String getStatusCd() {
        return statusCd;
    }

    @JsonProperty("statusCd")
    public void setStatusCd(String statusCd) {
        this.statusCd = statusCd;
    }

    public UserInfo withStatusCd(String statusCd) {
        this.statusCd = statusCd;
        return this;
    }

    @JsonProperty("roles")
    public List<Role> getRoles() {
        return roles;
    }

    @JsonProperty("roles")
    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public UserInfo withRoles(List<Role> roles) {
        this.roles = roles;
        return this;
    }

    @JsonProperty("passwordInfo")
    public PasswordInfo getPasswordInfo() {
        return passwordInfo;
    }

    @JsonProperty("passwordInfo")
    public void setPasswordInfo(PasswordInfo passwordInfo) {
        this.passwordInfo = passwordInfo;
    }

    public UserInfo withPasswordInfo(PasswordInfo passwordInfo) {
        this.passwordInfo = passwordInfo;
        return this;
    }

    @JsonProperty("defaultLanguageCd")
    public String getDefaultLanguageCd() {
        return defaultLanguageCd;
    }

    @JsonProperty("defaultLanguageCd")
    public void setDefaultLanguageCd(String defaultLanguageCd) {
        this.defaultLanguageCd = defaultLanguageCd;
    }

    public UserInfo withDefaultLanguageCd(String defaultLanguageCd) {
        this.defaultLanguageCd = defaultLanguageCd;
        return this;
    }

    @JsonProperty("lastLogonDt")
    public String getLastLogonDt() {
        return lastLogonDt;
    }

    @JsonProperty("lastLogonDt")
    public void setLastLogonDt(String lastLogonDt) {
        this.lastLogonDt = lastLogonDt;
    }

    public UserInfo withLastLogonDt(String lastLogonDt) {
        this.lastLogonDt = lastLogonDt;
        return this;
    }

    @JsonProperty("lastLogonTm")
    public String getLastLogonTm() {
        return lastLogonTm;
    }

    @JsonProperty("lastLogonTm")
    public void setLastLogonTm(String lastLogonTm) {
        this.lastLogonTm = lastLogonTm;
    }

    public UserInfo withLastLogonTm(String lastLogonTm) {
        this.lastLogonTm = lastLogonTm;
        return this;
    }

    @JsonProperty("version")
    public String getVersion() {
        return version;
    }

    @JsonProperty("version")
    public void setVersion(String version) {
        this.version = version;
    }

    public UserInfo withVersion(String version) {
        this.version = version;
        return this;
    }

    @JsonProperty("branchCd")
    public String getBranchCd() {
        return branchCd;
    }

    @JsonProperty("branchCd")
    public void setBranchCd(String branchCd) {
        this.branchCd = branchCd;
    }

    public UserInfo withBranchCd(String branchCd) {
        this.branchCd = branchCd;
        return this;
    }

    @JsonProperty("beanStats")
    public List<BeanStat> getBeanStats() {
        return beanStats;
    }

    @JsonProperty("beanStats")
    public void setBeanStats(List<BeanStat> beanStats) {
        this.beanStats = beanStats;
    }

    public UserInfo withBeanStats(List<BeanStat> beanStats) {
        this.beanStats = beanStats;
        return this;
    }

    @JsonProperty("userTaskControl")
    public UserTaskControl getUserTaskControl() {
        return userTaskControl;
    }

    @JsonProperty("userTaskControl")
    public void setUserTaskControl(UserTaskControl userTaskControl) {
        this.userTaskControl = userTaskControl;
    }

    public UserInfo withUserTaskControl(UserTaskControl userTaskControl) {
        this.userTaskControl = userTaskControl;
        return this;
    }

    @JsonProperty("partyInfo")
    public List<PartyInfo> getPartyInfo() {
        return partyInfo;
    }

    @JsonProperty("partyInfo")
    public void setPartyInfo(List<PartyInfo> partyInfo) {
        this.partyInfo = partyInfo;
    }

    public UserInfo withPartyInfo(List<PartyInfo> partyInfo) {
        this.partyInfo = partyInfo;
        return this;
    }

    @JsonProperty("changeInfo")
    public List<ChangeInfo> getChangeInfo() {
        return changeInfo;
    }

    @JsonProperty("changeInfo")
    public void setChangeInfo(List<ChangeInfo> changeInfo) {
        this.changeInfo = changeInfo;
    }

    public UserInfo withChangeInfo(List<ChangeInfo> changeInfo) {
        this.changeInfo = changeInfo;
        return this;
    }

    @JsonProperty("managedGroups")
    public List<ManagedGroup> getManagedGroups() {
        return managedGroups;
    }

    @JsonProperty("managedGroups")
    public void setManagedGroups(List<ManagedGroup> managedGroups) {
        this.managedGroups = managedGroups;
    }

    public UserInfo withManagedGroups(List<ManagedGroup> managedGroups) {
        this.managedGroups = managedGroups;
        return this;
    }

    @JsonProperty("_links")
    public List<Link> getLinks() {
        return links;
    }

    @JsonProperty("_links")
    public void setLinks(List<Link> links) {
        this.links = links;
    }

    public UserInfo withLinks(List<Link> links) {
        this.links = links;
        return this;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    public UserInfo withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(UserInfo.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("systemId");
        sb.append('=');
        sb.append(((this.systemId == null)?"<null>":this.systemId));
        sb.append(',');
        sb.append("id");
        sb.append('=');
        sb.append(((this.id == null)?"<null>":this.id));
        sb.append(',');
        sb.append("updateCount");
        sb.append('=');
        sb.append(((this.updateCount == null)?"<null>":this.updateCount));
        sb.append(',');
        sb.append("updateUser");
        sb.append('=');
        sb.append(((this.updateUser == null)?"<null>":this.updateUser));
        sb.append(',');
        sb.append("updateTimestamp");
        sb.append('=');
        sb.append(((this.updateTimestamp == null)?"<null>":this.updateTimestamp));
        sb.append(',');
        sb.append("loginId");
        sb.append('=');
        sb.append(((this.loginId == null)?"<null>":this.loginId));
        sb.append(',');
        sb.append("typeCd");
        sb.append('=');
        sb.append(((this.typeCd == null)?"<null>":this.typeCd));
        sb.append(',');
        sb.append("superUserInd");
        sb.append('=');
        sb.append(((this.superUserInd == null)?"<null>":this.superUserInd));
        sb.append(',');
        sb.append("password");
        sb.append('=');
        sb.append(((this.password == null)?"<null>":this.password));
        sb.append(',');
        sb.append("supervisor");
        sb.append('=');
        sb.append(((this.supervisor == null)?"<null>":this.supervisor));
        sb.append(',');
        sb.append("emailAddr");
        sb.append('=');
        sb.append(((this.emailAddr == null)?"<null>":this.emailAddr));
        sb.append(',');
        sb.append("lastName");
        sb.append('=');
        sb.append(((this.lastName == null)?"<null>":this.lastName));
        sb.append(',');
        sb.append("firstName");
        sb.append('=');
        sb.append(((this.firstName == null)?"<null>":this.firstName));
        sb.append(',');
        sb.append("startWork");
        sb.append('=');
        sb.append(((this.startWork == null)?"<null>":this.startWork));
        sb.append(',');
        sb.append("concurrentSessions");
        sb.append('=');
        sb.append(((this.concurrentSessions == null)?"<null>":this.concurrentSessions));
        sb.append(',');
        sb.append("statusCd");
        sb.append('=');
        sb.append(((this.statusCd == null)?"<null>":this.statusCd));
        sb.append(',');
        sb.append("roles");
        sb.append('=');
        sb.append(((this.roles == null)?"<null>":this.roles));
        sb.append(',');
        sb.append("passwordInfo");
        sb.append('=');
        sb.append(((this.passwordInfo == null)?"<null>":this.passwordInfo));
        sb.append(',');
        sb.append("defaultLanguageCd");
        sb.append('=');
        sb.append(((this.defaultLanguageCd == null)?"<null>":this.defaultLanguageCd));
        sb.append(',');
        sb.append("lastLogonDt");
        sb.append('=');
        sb.append(((this.lastLogonDt == null)?"<null>":this.lastLogonDt));
        sb.append(',');
        sb.append("lastLogonTm");
        sb.append('=');
        sb.append(((this.lastLogonTm == null)?"<null>":this.lastLogonTm));
        sb.append(',');
        sb.append("version");
        sb.append('=');
        sb.append(((this.version == null)?"<null>":this.version));
        sb.append(',');
        sb.append("branchCd");
        sb.append('=');
        sb.append(((this.branchCd == null)?"<null>":this.branchCd));
        sb.append(',');
        sb.append("beanStats");
        sb.append('=');
        sb.append(((this.beanStats == null)?"<null>":this.beanStats));
        sb.append(',');
        sb.append("userTaskControl");
        sb.append('=');
        sb.append(((this.userTaskControl == null)?"<null>":this.userTaskControl));
        sb.append(',');
        sb.append("partyInfo");
        sb.append('=');
        sb.append(((this.partyInfo == null)?"<null>":this.partyInfo));
        sb.append(',');
        sb.append("changeInfo");
        sb.append('=');
        sb.append(((this.changeInfo == null)?"<null>":this.changeInfo));
        sb.append(',');
        sb.append("managedGroups");
        sb.append('=');
        sb.append(((this.managedGroups == null)?"<null>":this.managedGroups));
        sb.append(',');
        sb.append("links");
        sb.append('=');
        sb.append(((this.links == null)?"<null>":this.links));
        sb.append(',');
        sb.append("additionalProperties");
        sb.append('=');
        sb.append(((this.additionalProperties == null)?"<null>":this.additionalProperties));
        sb.append(',');
        if (sb.charAt((sb.length()- 1)) == ',') {
            sb.setCharAt((sb.length()- 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }

    @Override
    public int hashCode() {
        int result = 1;
        result = ((result* 31)+((this.lastName == null)? 0 :this.lastName.hashCode()));
        result = ((result* 31)+((this.updateCount == null)? 0 :this.updateCount.hashCode()));
        result = ((result* 31)+((this.loginId == null)? 0 :this.loginId.hashCode()));
        result = ((result* 31)+((this.startWork == null)? 0 :this.startWork.hashCode()));
        result = ((result* 31)+((this.roles == null)? 0 :this.roles.hashCode()));
        result = ((result* 31)+((this.beanStats == null)? 0 :this.beanStats.hashCode()));
        result = ((result* 31)+((this.password == null)? 0 :this.password.hashCode()));
        result = ((result* 31)+((this.changeInfo == null)? 0 :this.changeInfo.hashCode()));
        result = ((result* 31)+((this.links == null)? 0 :this.links.hashCode()));
        result = ((result* 31)+((this.id == null)? 0 :this.id.hashCode()));
        result = ((result* 31)+((this.branchCd == null)? 0 :this.branchCd.hashCode()));
        result = ((result* 31)+((this.systemId == null)? 0 :this.systemId.hashCode()));
        result = ((result* 31)+((this.concurrentSessions == null)? 0 :this.concurrentSessions.hashCode()));
        result = ((result* 31)+((this.defaultLanguageCd == null)? 0 :this.defaultLanguageCd.hashCode()));
        result = ((result* 31)+((this.lastLogonDt == null)? 0 :this.lastLogonDt.hashCode()));
        result = ((result* 31)+((this.updateUser == null)? 0 :this.updateUser.hashCode()));
        result = ((result* 31)+((this.statusCd == null)? 0 :this.statusCd.hashCode()));
        result = ((result* 31)+((this.version == null)? 0 :this.version.hashCode()));
        result = ((result* 31)+((this.userTaskControl == null)? 0 :this.userTaskControl.hashCode()));
        result = ((result* 31)+((this.updateTimestamp == null)? 0 :this.updateTimestamp.hashCode()));
        result = ((result* 31)+((this.passwordInfo == null)? 0 :this.passwordInfo.hashCode()));
        result = ((result* 31)+((this.managedGroups == null)? 0 :this.managedGroups.hashCode()));
        result = ((result* 31)+((this.firstName == null)? 0 :this.firstName.hashCode()));
        result = ((result* 31)+((this.partyInfo == null)? 0 :this.partyInfo.hashCode()));
        result = ((result* 31)+((this.typeCd == null)? 0 :this.typeCd.hashCode()));
        result = ((result* 31)+((this.superUserInd == null)? 0 :this.superUserInd.hashCode()));
        result = ((result* 31)+((this.emailAddr == null)? 0 :this.emailAddr.hashCode()));
        result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
        result = ((result* 31)+((this.supervisor == null)? 0 :this.supervisor.hashCode()));
        result = ((result* 31)+((this.lastLogonTm == null)? 0 :this.lastLogonTm.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof UserInfo) == false) {
            return false;
        }
        UserInfo rhs = ((UserInfo) other);
        return (((((((((((((((((((((((((((((((this.lastName == rhs.lastName)||((this.lastName!= null)&&this.lastName.equals(rhs.lastName)))&&((this.updateCount == rhs.updateCount)||((this.updateCount!= null)&&this.updateCount.equals(rhs.updateCount))))&&((this.loginId == rhs.loginId)||((this.loginId!= null)&&this.loginId.equals(rhs.loginId))))&&((this.startWork == rhs.startWork)||((this.startWork!= null)&&this.startWork.equals(rhs.startWork))))&&((this.roles == rhs.roles)||((this.roles!= null)&&this.roles.equals(rhs.roles))))&&((this.beanStats == rhs.beanStats)||((this.beanStats!= null)&&this.beanStats.equals(rhs.beanStats))))&&((this.password == rhs.password)||((this.password!= null)&&this.password.equals(rhs.password))))&&((this.changeInfo == rhs.changeInfo)||((this.changeInfo!= null)&&this.changeInfo.equals(rhs.changeInfo))))&&((this.links == rhs.links)||((this.links!= null)&&this.links.equals(rhs.links))))&&((this.id == rhs.id)||((this.id!= null)&&this.id.equals(rhs.id))))&&((this.branchCd == rhs.branchCd)||((this.branchCd!= null)&&this.branchCd.equals(rhs.branchCd))))&&((this.systemId == rhs.systemId)||((this.systemId!= null)&&this.systemId.equals(rhs.systemId))))&&((this.concurrentSessions == rhs.concurrentSessions)||((this.concurrentSessions!= null)&&this.concurrentSessions.equals(rhs.concurrentSessions))))&&((this.defaultLanguageCd == rhs.defaultLanguageCd)||((this.defaultLanguageCd!= null)&&this.defaultLanguageCd.equals(rhs.defaultLanguageCd))))&&((this.lastLogonDt == rhs.lastLogonDt)||((this.lastLogonDt!= null)&&this.lastLogonDt.equals(rhs.lastLogonDt))))&&((this.updateUser == rhs.updateUser)||((this.updateUser!= null)&&this.updateUser.equals(rhs.updateUser))))&&((this.statusCd == rhs.statusCd)||((this.statusCd!= null)&&this.statusCd.equals(rhs.statusCd))))&&((this.version == rhs.version)||((this.version!= null)&&this.version.equals(rhs.version))))&&((this.userTaskControl == rhs.userTaskControl)||((this.userTaskControl!= null)&&this.userTaskControl.equals(rhs.userTaskControl))))&&((this.updateTimestamp == rhs.updateTimestamp)||((this.updateTimestamp!= null)&&this.updateTimestamp.equals(rhs.updateTimestamp))))&&((this.passwordInfo == rhs.passwordInfo)||((this.passwordInfo!= null)&&this.passwordInfo.equals(rhs.passwordInfo))))&&((this.managedGroups == rhs.managedGroups)||((this.managedGroups!= null)&&this.managedGroups.equals(rhs.managedGroups))))&&((this.firstName == rhs.firstName)||((this.firstName!= null)&&this.firstName.equals(rhs.firstName))))&&((this.partyInfo == rhs.partyInfo)||((this.partyInfo!= null)&&this.partyInfo.equals(rhs.partyInfo))))&&((this.typeCd == rhs.typeCd)||((this.typeCd!= null)&&this.typeCd.equals(rhs.typeCd))))&&((this.superUserInd == rhs.superUserInd)||((this.superUserInd!= null)&&this.superUserInd.equals(rhs.superUserInd))))&&((this.emailAddr == rhs.emailAddr)||((this.emailAddr!= null)&&this.emailAddr.equals(rhs.emailAddr))))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))))&&((this.supervisor == rhs.supervisor)||((this.supervisor!= null)&&this.supervisor.equals(rhs.supervisor))))&&((this.lastLogonTm == rhs.lastLogonTm)||((this.lastLogonTm!= null)&&this.lastLogonTm.equals(rhs.lastLogonTm))));
    }

}
