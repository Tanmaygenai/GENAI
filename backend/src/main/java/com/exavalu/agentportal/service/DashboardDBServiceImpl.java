package com.exavalu.agentportal.service;

import com.exavalu.agentportal.model.DashboardChart;
import com.exavalu.agentportal.model.QuoteList;
import com.exavalu.agentportal.model.db.Quote;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import java.util.ArrayList;
import java.util.List;

@Service
@Qualifier("db")
public class DashboardDBServiceImpl implements DashboardService {
	@Autowired
	private RestTemplate restTemplate;
	private static final Logger logger = LogManager.getLogger(DashboardDBServiceImpl.class);
	@Autowired
	private EntityManager entityManager;
	String str="admin";

	@Override
	public List<Quote> monthlySubmissionReport(String userName,String roleType) {
		logger.debug("Entering DashboardDBServiceImpl monthlySubmissionReport method, with userName = " + userName);
		List<Quote> quoteList = null;
		Session currentSession = entityManager.unwrap(Session.class);
		if(roleType.equals(str))
		{
			Query<Quote> query = currentSession.createQuery(
				"select accountingMonth as month,count(case indicationStatus when 'Yes' then 1 else null end) as indications, count(case submissionStatus when 'Yes' then 1 else null end) as submissions, count(case submissionStatus when 'No' then 1 else null end) as pending from QuoteEntity where status ='1' and indication_date between indicationDate and CURDATE()group by accountingMonth order by effectiveDate");
			quoteList = query.list();
		}
		else
		{
			Query<Quote> query = currentSession.createQuery(
					"select accountingMonth as month,count(case indicationStatus when 'Yes' then 1 else null end) as indications, count(case submissionStatus when 'Yes' then 1 else null end) as submissions, count(case submissionStatus when 'No' then 1 else null end) as pending from QuoteEntity where status ='1' and indication_date between indicationDate and CURDATE() and user_name = :userName group by accountingMonth order by effectiveDate");
				query.setParameter("userName", userName);
				quoteList = query.list();
		}
		return quoteList;
	}

	@Override
	public List<Quote> monthlyPremiumReport(String userName,String roleType) {
		logger.debug("Entering DashboardDBServiceImpl monthlyPremiumReport method, with userName = " + userName);
		Session currentSession = entityManager.unwrap(Session.class);
		List<Quote> premiumList=null;
		if(roleType.equals(str)){
			Query<Quote> query = currentSession.createQuery(
					"select accountingMonth ,sum(case product when 'Commercial Auto' then (premium) else null end) as auto_premium, sum(case product when 'Commercial Property' then (premium) else null end) as prop_premium, sum(case product when 'Commercial Excess Liability' then (premium) else null end) as liab_premium from QuoteEntity where status = '1' and indication_date between indicationDate and CURDATE() group by accounting_Month order by effectiveDate");
			premiumList = query.list();
		}
		
		else {
			Query<Quote> query = currentSession.createQuery(
					"select accountingMonth ,sum(case product when 'Commercial Auto' then (premium) else null end) as auto_premium, sum(case product when 'Commercial Property' then (premium) else null end) as prop_premium, sum(case product when 'Commercial Excess Liability' then (premium) else null end) as liab_premium from QuoteEntity where status = '1' and indication_date between indicationDate and CURDATE() and user_name = :userName group by accounting_Month order by effectiveDate");
			query.setParameter("userName", userName);
			premiumList = query.list();
		}
		return premiumList;
	}

	@Override
	public List<Quote> totalPremiumReport(String userName,String roleType) {
		logger.debug("Entering DashboardDBServiceImpl totalPremiumReport method, with userName = " + userName);
		Session currentSession = entityManager.unwrap(Session.class);
		List<Quote> premiumList=null;
		if(roleType.equals(str)){
			Query<Quote> query = currentSession.createQuery(
					"select product,sum(premium) from QuoteEntity where status = '1' and submissionStatus = 'Yes' group by product");
			premiumList = query.list();
		}
		
		else {
			Query<Quote> query = currentSession.createQuery(
					"select product,sum(premium) from QuoteEntity where status = '1' and submissionStatus = 'Yes' and user_name = :userName group by product");
			query.setParameter("userName", userName);
			premiumList = query.list();
		}
		return premiumList;
	}
	
	@Override
	public DashboardChart getData(String username) {
		logger.debug("Entering DashboardDBServiceImpl getData method");
        // TODO Auto-generated method stub
        DashboardChart response = new DashboardChart();
        Session currentSession = entityManager.unwrap(Session.class);

       
        Query query = currentSession.createQuery("select count(*) from QuoteEntity where submissionStatus = 'Yes' and user_name=:username");
        query.setParameter("username", username);
        Query query1 = currentSession.createQuery("select count(*) from QuoteEntity where indicationStatus = 'Yes' and user_name=:username");
        query1.setParameter("username", username);
		Query query2 = currentSession.createQuery("select count(*) from PolicyDetails where creationStatus = 'Yes' and username=:username");
        query2.setParameter("username", username);
        System.out.println();
        response.setApplications(0);
        response.setIndications((int) (long) query1.getSingleResult());
        response.setPolicy((int) (long)query2.getSingleResult());
        response.setQuotes((int) (long) query.getSingleResult());
        response.setSubmissions(0);
        return response;
	}

	@Override
	public List<QuoteList> getQuote(String userName) {
		logger.debug("Entering OpenWorkDBServiceImpl getItems method, with userName = " + userName );
		Session currentSession = entityManager.unwrap(Session.class);
		List<QuoteList> quotes;
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Quote> quote = builder.createQuery(Quote.class);
		Root<Quote> root = quote.from(Quote.class);
			quotes = currentSession.createQuery("from QuoteList Where status = 1 and submissionStatus='Yes' ").list();
		return quotes;
	}
}
