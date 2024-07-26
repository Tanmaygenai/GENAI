package com.exavalu.agentportal.carrier.guidewire;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.Base64;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;
import org.springframework.web.client.HttpServerErrorException;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.InputSource;

import com.exavalu.agentportal.model.MappingEntry;
import com.exavalu.agentportal.model.XmlMappingConfig;
import com.exavalu.agentportal.model.DRCExcessQuickIndication.QuickIndication;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class GuidewireRequestMapper {

	private static final Logger logger = LogManager.getLogger(GuidewireRequestMapper.class);

	public String convertJsonToXml(Object jsonObject) throws Exception {
		XmlMapper xmlMapper = new XmlMapper();
		return xmlMapper.writeValueAsString(jsonObject);
	}

	public String newSubmission(String res, String accountNumber, QuickIndication quickIndicationDetails,
			String newSubmissionGWURL, String auth) throws IOException {
		logger.info("Entering GuidewireRequestMapper newSubmission method");
		try {

			byte[] authHeader = Base64.getEncoder().encode(auth.getBytes());

			OkHttpClient client = new OkHttpClient().newBuilder().build();
			MediaType mediaType = MediaType.parse("text/plain");
			RequestBody body = RequestBody.create(mediaType, res);
			String[] effDate=quickIndicationDetails.getApplication().getPolicyInfo().getEffectiveDt().split("-");
			
			Request request = new Request.Builder().url(newSubmissionGWURL).method("POST", body)
//				.addHeader("effectiveDate", quickIndicationDetails.getApplication().getPolicyInfo().getEffectiveDt())
					.addHeader("effectiveDate", effDate[1]+"/"+effDate[2]+"/"+effDate[0])
					.addHeader("accountNumber", accountNumber)
					.addHeader("producerCode",
							quickIndicationDetails.getApplication().getInsuredInfo().getProducerCode())
					.addHeader("product", "BusinessAuto")
					.addHeader("quoteType", quickIndicationDetails.getApplication().getPolicyInfo().getQuoteType())
					.addHeader("termType", quickIndicationDetails.getApplication().getPolicyInfo().getTermType())
					.addHeader("Content-Type", "text/plain")
					.addHeader("Authorization", "Basic " + new String(authHeader)).build();

			Response response = client.newCall(request).execute();
			JSONObject jsonObject = new JSONObject(response.body().string());
			logger.info("Exiting GuidewireRequestMapper newSubmission method with totalCost: {}",
					jsonObject.toString());
			logger.info("Exiting GuidewireRequestMapper newSubmission method with totalCost: {}",
					jsonObject.getString("totalCost"));
			JsonObject resultJson = new JsonObject();

			resultJson.addProperty("fullTermAmount", jsonObject.getString("totalCost"));
			resultJson.addProperty("transactionIdGW", jsonObject.getString("transactionNumber"));
			return resultJson.toString();
		} catch (HttpServerErrorException.InternalServerError e) {
			logger.error("Inside GuidewireRequestMapper newSubmission method: {}", e.getMessage());
		}
		return "";
	}

	public String getAccountNumber(QuickIndication quickIndicationDetails, String accountCreateGWURL, String auth)
			throws IOException {
		byte[] authHeader = Base64.getEncoder().encode(auth.getBytes());

		JSONObject requestBody = new JSONObject();
		requestBody.put("firstName", quickIndicationDetails.getApplication().getInsuredInfo().getCommercialName());
		requestBody.put("lastName", "Test");
		requestBody.put("name", quickIndicationDetails.getApplication().getInsuredInfo().getOrganization());
		requestBody.put("address1", quickIndicationDetails.getApplication().getLocations().get(0).getAddr().getAddr1());
		requestBody.put("addressType", quickIndicationDetails.getApplication().getLocations().get(0).getAddressType());
		requestBody.put("city", quickIndicationDetails.getApplication().getLocations().get(0).getAddr().getCity());
		requestBody.put("country", "US");
		requestBody.put("zipCode",
				quickIndicationDetails.getApplication().getLocations().get(0).getAddr().getPostalCode());
		requestBody.put("state", quickIndicationDetails.getApplication().getLocations().get(0).getAddr().getState());
		requestBody.put("orgType", "individual");
		requestBody.put("contactSubtype", "Company");

		OkHttpClient client = new OkHttpClient().newBuilder().build();
		MediaType mediaType = MediaType.parse("application/json");
		RequestBody body = RequestBody.create(mediaType, requestBody.toString());
		Request request = new Request.Builder().url(accountCreateGWURL).method("PUT", body)
				.addHeader("Authorization", "Basic " + new String(authHeader))
				.addHeader("Content-Type", "application/json").build();
		Response response = client.newCall(request).execute();
		JSONObject jsonObject = new JSONObject(response.body().string());

		return jsonObject.getString("accountNumber");
	}

	public String transformXml(String sourceXml) throws Exception {
		String resultXml = "<?xml version=\"1.0\"?><PolicyPeriod xmlns=\"http://guidewire.com/pc/gx/gw.webservice.pc.pc1000.gxmodel.policyperiodmodel\" xmlns:ns0=\"http://guidewire.com/pc/gx/gw.webservice.pc.pc1000.gxmodel.accountcontactrolemodel\" xmlns:ns1=\"http://guidewire.com/pc/gx/gw.webservice.pc.pc1000.gxmodel.accountlocationmodel\" xmlns:ns10=\"http://guidewire.com/pc/gx/gw.webservice.pc.pc1000.gxmodel.modifiermodel\" xmlns:ns11=\"http://guidewire.com/pc/gx/gw.webservice.pc.pc1000.gxmodel.periodanswermodel\" xmlns:ns12=\"http://guidewire.com/pc/gx/gw.webservice.pc.pc1000.gxmodel.policycontactrolemodel\" xmlns:ns13=\"http://guidewire.com/pc/gx/gw.webservice.pc.pc1000.gxmodel.policylocationmodel\" xmlns:ns14=\"http://guidewire.com/pc/gx/gw.webservice.pc.pc1000.gxmodel.ratefactormodel\" xmlns:ns2=\"http://guidewire.com/pc/gx/gw.webservice.pc.pc1000.gxmodel.addressmodel\" xmlns:ns3=\"http://guidewire.com/pc/gx/gw.webservice.pc.pc1000.gxmodel.bajurisdictionmodel\" xmlns:ns4=\"http://guidewire.com/pc/gx/gw.webservice.pc.pc1000.gxmodel.businessautolinemodel\" xmlns:ns5=\"http://guidewire.com/pc/gx/gw.webservice.pc.pc1000.gxmodel.businessvehiclemodel\" xmlns:ns6=\"http://guidewire.com/pc/gx/gw.webservice.pc.pc1000.gxmodel.clausemodel\" xmlns:ns7=\"http://guidewire.com/pc/gx/gw.webservice.pc.pc1000.gxmodel.commercialdrivermodel\" xmlns:ns8=\"http://guidewire.com/pc/gx/gw.webservice.pc.pc1000.gxmodel.contactmodel\" xmlns:ns9=\"http://guidewire.com/pc/gx/gw.webservice.pc.pc1000.gxmodel.covtermmodel\"><BaseState>CA</BaseState><BusinessAutoLine><ns4:BALineCoverages><ns4:Entry><ns6:CovTerms><ns6:Entry><ns9:DisplayValue>1M</ns9:DisplayValue><ns9:PatternCode>BAOwnedLiabilityLimit</ns9:PatternCode><ns9:ValueTypeName>Package</ns9:ValueTypeName></ns6:Entry></ns6:CovTerms><ns6:Pattern><ns6:PublicID>BAOwnedLiabilityCov</ns6:PublicID></ns6:Pattern></ns4:Entry></ns4:BALineCoverages><ns4:Drivers><ns4:Entry><ns7:DateOfBirth>2000-12-10T00:00:00+05:30</ns7:DateOfBirth><ns7:FirstName>Second</ns7:FirstName><ns7:LastName>Driver</ns7:LastName><ns7:LicenseNumber>C121098</ns7:LicenseNumber><ns7:LicenseState>Aomori</ns7:LicenseState><ns7:SeqNumber>2</ns7:SeqNumber></ns4:Entry></ns4:Drivers><ns4:Fleet>NonFleet</ns4:Fleet><ns4:Jurisdictions><ns4:Entry><ns3:State>AZ</ns3:State></ns4:Entry></ns4:Jurisdictions><ns4:PolicyType>BA</ns4:PolicyType><ns4:Vehicles><ns4:Entry><ns5:CostNew>15000.00 usd</ns5:CostNew><ns5:Coverages><ns5:Entry><ns6:CovTerms><ns6:Entry><ns9:DisplayValue>1000</ns9:DisplayValue><ns9:PatternCode>BAComprehensiveDdct</ns9:PatternCode><ns9:ValueTypeName>Option</ns9:ValueTypeName></ns6:Entry></ns6:CovTerms><ns6:Pattern><ns6:PublicID>BAComprehensiveCov</ns6:PublicID></ns6:Pattern></ns5:Entry><ns5:Entry><ns6:CovTerms><ns6:Entry><ns9:DisplayValue>500</ns9:DisplayValue><ns9:PatternCode>BACollisionDeduct</ns9:PatternCode><ns9:ValueTypeName>Option</ns9:ValueTypeName></ns6:Entry></ns6:CovTerms><ns6:Pattern><ns6:PublicID>BACollisionCov</ns6:PublicID></ns6:Pattern></ns5:Entry></ns5:Coverages><ns5:Industry>Truckers</ns5:Industry><ns5:IndustryUse>contractother</ns5:IndustryUse><ns5:LeaseOrRent>false</ns5:LeaseOrRent><ns5:Location><ns5:LocationNum>1</ns5:LocationNum></ns5:Location><ns5:Make>Mazda</ns5:Make><ns5:Model>MPV</ns5:Model><ns5:PrimaryUse>Service</ns5:PrimaryUse><ns5:VehicleClassCode>005603</ns5:VehicleClassCode><ns5:VehicleCondition>false</ns5:VehicleCondition><ns5:VehicleRadius>LessThan50Miles</ns5:VehicleRadius><ns5:VehicleSizeClass>HeavyTruckTractor</ns5:VehicleSizeClass><ns5:VehicleType>Commercial</ns5:VehicleType><ns5:Vin>1GCHK23193F138392</ns5:Vin><ns5:Year>2004</ns5:Year></ns4:Entry></ns4:Vehicles></BusinessAutoLine></PolicyPeriod>";
		Gson gson = new Gson();
		String mapping = "{\r\n" + "    \"mappings\": [\r\n" + "        {\r\n"
				+ "            \"sourceXPath\": \"/QuickIndication/application/policyInfo/controllingState\",\r\n"
				+ "            \"resultXPath\": \"/PolicyPeriod/BaseState\"\r\n" + "        },\r\n" + "		{\r\n"
				+ "            \"sourceXPath\": \"/QuickIndication/application/insuranceLine/insuranceLine/coverages/coverages/limits/limits/value\",\r\n"
				+ "            \"resultXPath\": \"/PolicyPeriod/BusinessAutoLine/BALineCoverages/Entry/CovTerms/Entry/DisplayValue\"\r\n"
				+ "        },\r\n" + "		{\r\n"
				+ "            \"sourceXPath\": \"/QuickIndication/application/drivers/drivers/birthDt\",\r\n"
				+ "            \"resultXPath\": \"/PolicyPeriod/BusinessAutoLine/Drivers/Entry/DateOfBirth\"\r\n"
				+ "        },\r\n" + "		{\r\n"
				+ "            \"sourceXPath\": \"/QuickIndication/application/drivers/drivers/givenName\",\r\n"
				+ "            \"resultXPath\": \"/PolicyPeriod/BusinessAutoLine/Drivers/Entry/FirstName\"\r\n"
				+ "        },\r\n" + "		{\r\n"
				+ "            \"sourceXPath\": \"/QuickIndication/application/drivers/drivers/surname\",\r\n"
				+ "            \"resultXPath\": \"/PolicyPeriod/BusinessAutoLine/Drivers/Entry/LastName\"\r\n"
				+ "        },\r\n" + "		{\r\n"
				+ "            \"sourceXPath\": \"/QuickIndication/application/drivers/drivers/DriverLicenseNumber\",\r\n"
				+ "            \"resultXPath\": \"/PolicyPeriod/BusinessAutoLine/Drivers/Entry/LicenseNumber\"\r\n"
				+ "        },\r\n" + "		{\r\n"
				+ "            \"sourceXPath\": \"/QuickIndication/application/drivers/drivers/licenseState\",\r\n"
				+ "            \"resultXPath\": \"/PolicyPeriod/BusinessAutoLine/Drivers/Entry/LicenseState\"\r\n"
				+ "        },\r\n" + "		{\r\n"
				+ "            \"sourceXPath\": \"/QuickIndication/application/insuranceLine/insuranceLine/risks/risks/vehicleInfo/fleet\",\r\n"
				+ "            \"resultXPath\": \"/PolicyPeriod/BusinessAutoLine/Fleet\"\r\n" + "        },\r\n"
				+ "		{\r\n"
				+ "            \"sourceXPath\": \"/QuickIndication/application/policyInfo/controllingState\",\r\n"
				+ "            \"resultXPath\": \"/PolicyPeriod/BusinessAutoLine/Jurisdictions/Entry/State\"\r\n"
				+ "        },\r\n" + "		{\r\n"
				+ "            \"sourceXPath\": \"/QuickIndication/application/insuranceLine/insuranceLine/risks/risks/vehicleInfo/costNew\",\r\n"
				+ "            \"resultXPath\": \"/PolicyPeriod/BusinessAutoLine/Vehicles/Entry/CostNew\"\r\n"
				+ "        },\r\n" + "		{\r\n"
				+ "            \"sourceXPath\": \"/QuickIndication/application/ProductType\",\r\n"
				+ "            \"resultXPath\": \"/PolicyPeriod/BusinessAutoLine/Vehicles/Entry/Industry\"\r\n"
				+ "        },	\r\n" + "		{\r\n"
				+ "            \"sourceXPath\": \"/QuickIndication/application/insuranceLine/insuranceLine/risks/risks/vehicleInfo/manufacturer\",\r\n"
				+ "            \"resultXPath\": \"/PolicyPeriod/BusinessAutoLine/Vehicles/Entry/Make\"\r\n"
				+ "        },\r\n" + "		{\r\n"
				+ "            \"sourceXPath\": \"/QuickIndication/application/insuranceLine/insuranceLine/risks/risks/vehicleInfo/model\",\r\n"
				+ "            \"resultXPath\": \"/PolicyPeriod/BusinessAutoLine/Vehicles/Entry/Model\"\r\n"
				+ "        },\r\n" + "		{\r\n"
				+ "            \"sourceXPath\": \"/QuickIndication/application/insuranceLine/insuranceLine/risks/risks/vehicleInfo/PrimaryUse\",\r\n"
				+ "            \"resultXPath\": \"/PolicyPeriod/BusinessAutoLine/Vehicles/Entry/PrimaryUse\"\r\n"
				+ "        },		\r\n" + "		{\r\n"
				+ "            \"sourceXPath\": \"/QuickIndication/application/insuranceLine/insuranceLine/risks/risks/vehicleInfo/vehicleconditionwhenpurchased\",\r\n"
				+ "            \"resultXPath\": \"/PolicyPeriod/BusinessAutoLine/Vehicles/Entry/VehicleCondition\"\r\n"
				+ "        },\r\n" + "		{\r\n"
				+ "            \"sourceXPath\": \"/QuickIndication/application/insuranceLine/insuranceLine/risks/risks/vehicleInfo/Radiusofoperation\",\r\n"
				+ "            \"resultXPath\": \"/PolicyPeriod/BusinessAutoLine/Vehicles/Entry/VehicleRadius\"\r\n"
				+ "        },\r\n" + "		{\r\n"
				+ "            \"sourceXPath\": \"/QuickIndication/application/insuranceLine/insuranceLine/risks/risks/vehicleInfo/sizeclass\",\r\n"
				+ "            \"resultXPath\": \"/PolicyPeriod/BusinessAutoLine/Vehicles/Entry/VehicleSizeClass\"\r\n"
				+ "        },\r\n" + "		{\r\n"
				+ "            \"sourceXPath\": \"/QuickIndication/application/insuranceLine/insuranceLine/risks/risks/vehicleInfo/VehicleType\",\r\n"
				+ "            \"resultXPath\": \"/PolicyPeriod/BusinessAutoLine/Vehicles/Entry/VehicleType\"\r\n"
				+ "        },\r\n" + "		{\r\n"
				+ "            \"sourceXPath\": \"/QuickIndication/application/insuranceLine/insuranceLine/risks/risks/vehicleInfo/vin\",\r\n"
				+ "            \"resultXPath\": \"/PolicyPeriod/BusinessAutoLine/Vehicles/Entry/Vin\"\r\n"
				+ "        },\r\n" + "		{\r\n"
				+ "            \"sourceXPath\": \"/QuickIndication/application/insuranceLine/insuranceLine/risks/risks/vehicleInfo/modelYear\",\r\n"
				+ "            \"resultXPath\": \"/PolicyPeriod/BusinessAutoLine/Vehicles/Entry/Year\"\r\n"
				+ "        },\r\n" + "		{\r\n"
				+ "            \"sourceXPath\": \"/QuickIndication/application/insuranceLine/insuranceLine/risks/risks/vehicleInfo/ComprehensiveDeductible\",\r\n"
				+ "            \"resultXPath\": \"/PolicyPeriod/BusinessAutoLine/Vehicles/Entry/Coverages/Entry/CovTerms/Entry/DisplayValue\"\r\n"
				+ "        },\r\n" + "		{\r\n"
				+ "            \"sourceXPath\": \"/QuickIndication/application/insuranceLine/insuranceLine/risks/risks/vehicleInfo/VehicleCode\",\r\n"
				+ "            \"resultXPath\": \"/PolicyPeriod/BusinessAutoLine/Vehicles/Entry/VehicleClassCode\"\r\n"
				+ "        }\r\n" + "    ]\r\n" + "}";
		XmlMappingConfig mappingConfig = gson.fromJson(mapping, XmlMappingConfig.class);
		Document sourceDocument = parseXml(sourceXml);

		Document resultDocument = parseXml(resultXml);

		XPathFactory xPathFactory = XPathFactory.newInstance();
		XPath xPath = xPathFactory.newXPath();
		for (MappingEntry mappingEntry : mappingConfig.getMappings()) {
			String sourceXPath = mappingEntry.getSourceXPath();
			String resultXPath = mappingEntry.getResultXPath();
			XPathExpression sourceExpression = xPath.compile(sourceXPath);

			Node sourceNode = (Node) sourceExpression.evaluate(sourceDocument, XPathConstants.NODE);

			if (sourceNode != null) {
				Node importedNode = resultDocument.importNode(sourceNode, true);
				XPathExpression resultExpression = xPath.compile(resultXPath);
				Node resultNode = (Node) resultExpression.evaluate(resultDocument, XPathConstants.NODE);

				if (resultNode != null) {
					resultNode.setTextContent(sourceNode.getTextContent());
				}
			}
		}

		String transformedXml = convertDocumentToString(resultDocument);
		return transformedXml;
	}

	public Document parseXml(String xml) throws Exception {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		InputSource source = new InputSource(new StringReader(xml));
		return builder.parse(source);
	}

	public String convertDocumentToString(Document document) throws Exception {
		TransformerFactory tf = TransformerFactory.newInstance();
		Transformer transformer = tf.newTransformer();
		transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");

		StringWriter writer = new StringWriter();
		transformer.transform(new DOMSource(document), new StreamResult(writer));
		return writer.getBuffer().toString();
	}

	public String issueNewSubmission(String quoteId, String issueSubmissionGWURL, String auth) throws IOException {

		byte[] authHeader = Base64.getEncoder().encode(auth.getBytes());
		String result = quoteId.replaceAll("^\"|\"$", "");
		
		OkHttpClient client = new OkHttpClient().newBuilder().build();
		MediaType mediaType = MediaType.parse("text/plain");
		RequestBody body = RequestBody.create(mediaType, result);
		Request request = new Request.Builder().url(issueSubmissionGWURL).method("POST", body)
				.addHeader("Content-Type", "text/plain").addHeader("Authorization", "Basic " + new String(authHeader))
				.build();

		Response response = client.newCall(request).execute();

		return response.body().string();
	}
}
