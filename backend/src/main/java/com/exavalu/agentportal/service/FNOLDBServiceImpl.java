package com.exavalu.agentportal.service;

import com.exavalu.agentportal.db.DatabaseDAO;
import com.exavalu.agentportal.model.FNOL;
import com.exavalu.agentportal.model.Login;
import com.exavalu.agentportal.model.db.LossNotice;
import com.exavalu.agentportal.repository.FNOLRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

@Service
@Qualifier("db")
public class FNOLDBServiceImpl implements FNOLService {
	private static final Logger logger = LogManager.getLogger(FNOLDBServiceImpl.class);
	@Autowired
	private LoginService loginService;
	@Autowired
	private FNOLRepo repo;

	@Override
	public String generateLossNoticeNumber(FNOL fnolDetails, String username,String decryptedFnolDetails) {
		logger.debug("Entering FNOLDBServiceImpl generateLossNoticeNumber method, with username = " + username + " , "
				+ " fnolDetails =" + fnolDetails);
		String reportedDt = String.valueOf(java.time.LocalDate.now());
		LossNotice fnol = new LossNotice();
		fnol.setReportedDt(reportedDt);
		fnol.setLossNoticeDataJson(decryptedFnolDetails.toString());
		fnol.setUserName(username);
		List<LossNotice> lossNoticeList = repo.findAll(Sort.by(Sort.Direction.DESC, "id"));
		if (!lossNoticeList.isEmpty()) {
			LossNotice lossNotice = lossNoticeList.get(0);
			int sequenceNumber = lossNotice.getId() + 1;
			String lossNoticeNumber = "LN-" + sequenceNumber;
			fnol.setLossNoticeNumber(lossNoticeNumber);
			repo.save(fnol);
			return lossNoticeNumber;
		} else {
			int sequenceNumber = 1;
			String lossNoticeNumber = "LN-" + sequenceNumber;
			fnol.setLossNoticeNumber(lossNoticeNumber);
			repo.save(fnol);
			return lossNoticeNumber;
		}

	}

	@Override
	public Long getLNCount(String username, String role) {
		logger.debug(
				"Entering FNOLDBServiceImpl getLNCount method, with username = " + username + " , " + "role = " + role);
		if (role.contains("admin")) {
			return repo.count();
		} else {
			return repo.countByUserName(username);
		}
	}

}
