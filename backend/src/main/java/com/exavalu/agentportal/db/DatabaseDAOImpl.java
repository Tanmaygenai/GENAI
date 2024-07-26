package com.exavalu.agentportal.db;

import com.exavalu.agentportal.model.LossNoticeList;
import com.exavalu.agentportal.repository.QuoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Repository
@Transactional
public class DatabaseDAOImpl implements DatabaseDAO {
    static Logger logger = LogManager.getLogger(DatabaseDAOImpl.class);
    @PersistenceContext
    private EntityManager manager;
    @Autowired
    private QuoteRepository quoteRepository;

    String str = "admin";
    @Override
    public boolean updateSubmissionStatus(String indicationId, String quoteNumber, String submissionStatus, String submissionDate) {
        try {
            logger.info("in updateSubmissionStatus indicationId: %s quoteNumber:%s submissionStatus:%s submissionDate:%s" ,indicationId , quoteNumber , submissionStatus , submissionDate);
            int noOfRows = quoteRepository.updateQuoteSubmissionData(submissionStatus, quoteNumber, submissionDate, indicationId);
            logger.info("noOfRows::: %d" , noOfRows);
            if (noOfRows == 1) {
                return true;
            }else {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }

    public List<Object[]> getQuickIndicationDetails(String startDt, String endDt, String product,String userName, String userRole) {
        List<Object[]> list = null;
        StringBuilder query = new StringBuilder();
  
        //check userRole
        boolean adminRole = false;
        if(userRole.equals(str) && !userRole.isEmpty()){
          adminRole = true;
      }
        
        query.append("SELECT * FROM (SELECT quote_number, insured_name, state_cd, product, premium, effective_date,class_code, liability_limit,submission_date, indication_date,user_name FROM quote where status = '1' and quote_number != ''"+ 
        "UNION "+ 
        "SELECT concat('INDICATION-',id),insured_name, state_cd, product, premium,effective_date, class_code,liability_limit,submission_date,indication_date,user_name FROM quote where status = '1' and quote_number = '' and submission_status='No')as U where U.indication_date between '" + startDt + "' and '" + endDt + "'");
        if (product != null && !product.equals(""))
            query.append("and U.product='" + product + "' ");
        if(adminRole==false){
              query.append(" and U.user_name='"+userName+"'");
       }
        try {
            logger.info("Inside getQuickIndicationDetails");
            Query q = manager.createNativeQuery(query.toString());
            list = q.getResultList();
        } catch (Exception e) {
            logger.info(e);
        }
        return list;
    }

    public List<Object[]> getQuoteSubmissionPremiumDetails(String startDt, String endDt, String product,String userName,String userRole) {
        List<Object[]> list = null;
        StringBuilder query = new StringBuilder();

        //check userRole
        boolean adminRole = false;
        if(userRole.equals(str) && !userRole.isEmpty()){
            adminRole = true;
        }

        query.append("SELECT quote_number,  product, premium, effective_date," +
                "submission_date, accounting_month " +
                "FROM quote where status = '1' and submission_date between '" + startDt + "' and '" + endDt + "'");
        if (product != null && !product.equals("")) {
            query.append(" and product='" + product + "' ");
        }
        if(adminRole==false){
            query.append(" and user_name='"+userName+"'");
        }
        try {
            logger.info("Inside getQuoteSubmissionPremiumDetails");
            Query q = manager.createNativeQuery(query.toString());
            list = q.getResultList();
        } catch (Exception e) {
        	logger.info(e);
        }
        return list;
    }

    public List<Object[]> getLossNoticeReports(String startDt, String endDt, String userName,String userRole) {
        List<Object[]> list = null;
        StringBuilder query = new StringBuilder();

        //check userRole
        boolean adminRole = false;
        if(userRole.equals(str) && !userRole.isEmpty()){
            adminRole = true;
        }

        query.append("SELECT reported_date, loss_notice_number, loss_notice_data_json," +
                "user_name " +
                "FROM lossnotice where reported_date between '" + startDt + "' and '" + endDt + "'");
        
        if(adminRole==false){
            query.append(" and user_name='"+userName+"'");
        }
        try {
            logger.info("Inside getLossReportDetails");
            Query q = manager.createNativeQuery(query.toString());
            list = q.getResultList();
        } catch (Exception e) {
        	logger.info(e);
        }
        return list;
    }
    
    public List<Object> getUserRole(String userName){
        List<Object> list = null;
        StringBuilder query = new StringBuilder();
        query.append("SELECT user_role " +
                "FROM user where user_name='" + userName + "'");
        try {
            logger.info("Inside getUserRole");
            Query q = manager.createNativeQuery(query.toString());
            list = q.getResultList();
        } catch (Exception e) {
        	logger.info(e);
        }
        return list;
    }

}