/*
package com.exavalu.agentportal;
import com.exavalu.agentportal.controller.PolicyController;
import com.exavalu.agentportal.model.GWINPolicy.BasicPolicy;
import com.exavalu.agentportal.model.GWINPolicy.Insured;
import com.exavalu.agentportal.model.GWINPolicy.PolicyListItem;
import com.exavalu.agentportal.model.GWINPolicy.PolicyMini;
import com.exavalu.agentportal.model.Login;
//import com.exavalu.agentportal.model.Policy;
import com.exavalu.agentportal.model.GWINPolicy.Policy;
import com.exavalu.agentportal.service.LoginService;
import com.exavalu.agentportal.service.PolicyService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;


@SpringBootTest
@AutoConfigureMockMvc
class AgentPortalApplicationTests {
	@Autowired
	PolicyController con;
	@MockBean
	PolicyService policyService;
	@MockBean
	LoginService  loginService;
	@Autowired
	private MockMvc mockMvc;


	@Test
	void contextLoads() {
	}

	*/
/*@Test
	public void getPolicyByIdTest() throws Exception
	{
        Policy policy = buildPolicy();
		when(policyService.getPolicyByID(any())).thenReturn(policy);
		this.mockMvc.perform(get("/policy/"+100))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.systemId").value("100"))
		        .andExpect(jsonPath("$.updateUser").value("pquintos"))
				.andExpect(jsonPath("$.basicPolicy").exists())
				.andExpect(jsonPath("$.basicPolicy.policyNumber", is("XL00200000")))
				.andExpect(jsonPath("$.basicPolicy.carrierCd", is("TIC")));
     }
*//*

	@Test
	public void getPolicyByIdTest() throws Exception
	{
        Policy policy = buildPolicy();
		when(policyService.getPolicyByID(any())).thenReturn(policy);
		this.mockMvc.perform(get("/policy/COM00200199"))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.policyListItems[0].policyMini").exists())
				.andExpect(jsonPath("$.policyListItems[0].policyMini.basicPolicy.subTypeCd", is("CA")))
				.andExpect(jsonPath("$.policyListItems[0].policyMini.insured.indexName", is("test fleet exp")));

     }


	@Test
	public void verifyLoginTest() throws Exception
	{
		Login login = buildLogin();
		String statuscd = "Active";
		ObjectMapper map =new ObjectMapper();
		String jsonString = map.writeValueAsString(login);
		when(loginService.validateCrdentials(any())).thenReturn(statuscd);
		this.mockMvc.perform(post("/login")
					.contentType(MediaType.APPLICATION_JSON)
					.content(jsonString))
				    .andDo(print())
				    .andExpect(status().isOk());
		             assertEquals("Active",statuscd);
    }

	public Policy buildPolicy()
	{
		*/
/*Policy policy =new Policy();
		BasicPolicy basicPolicy = new BasicPolicy();
		policy.setSystemId("100");
		policy.setCustomerRef("2264");
		policy.setUpdateUser("pquintos");
		policy.setUpdateCount("15");

		basicPolicy.setPolicyNumber("XL00200000");
		basicPolicy.setStatusCd("Expired");
		basicPolicy.setCarrierCd("TIC");
		policy.setBasicPolicy(basicPolicy);*//*

		Policy policy = new Policy();
		List<PolicyListItem> policyListItem = new ArrayList();
		PolicyListItem policyListItem1 = new PolicyListItem();

		PolicyMini policyMini1 = new PolicyMini();
		BasicPolicy basicPolicy1 = new BasicPolicy();
		Insured insured1 = new Insured();
		basicPolicy1.setSubTypeCd("CA");
		insured1.setIndexName("test fleet exp");
		policyMini1.setBasicPolicy(basicPolicy1);
		policyMini1.setInsured(insured1);
		policyListItem1.setPolicyMini(policyMini1);
		policyListItem.add(policyListItem1);
		policy.setPolicyListItems(policyListItem);

		return policy;

	}

	public Login buildLogin(){
		Login login = new Login();
		login.setUserName("Sundar");
		return login;
	}

}
*/
