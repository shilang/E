package com.cloud.erp.service.common;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cloud.erp.dao.RecProceedsDao;
import com.cloud.erp.dao.SalesOrderDao;
import com.cloud.erp.dao.common.BaseDao;
import com.cloud.erp.entities.SettleItem;
import com.cloud.erp.entities.table.RecProceeds;
import com.cloud.erp.entities.table.SalesOrder;
import com.cloud.erp.utils.Commons;
import com.cloud.erp.utils.Constants;
import com.cloud.erp.utils.Reflect;

/**
 * 
 * @author Bollen
 *
 */
@Service("checkService")
public class CheckServiceSupport implements CheckService {
	
	private static final String METHOD_RESULT = "result";
	private static final String METHOD_CHECKER = "checker";
	private static final String METHOD_CHECK_DATE = "checkDate";
	//private static final String METHOD_CHILDREN = "children";
	private static final String METHOD_CHANGE_REASON ="changeReason";
	
	@SuppressWarnings("rawtypes")
	@Autowired
	private BaseDao baseDao;
	
	@Autowired
	private RecProceedsDao recProceedsDao;
	
	@Autowired
	private SalesOrderDao salesOrderDao;
	
	@SuppressWarnings("unchecked")
	private boolean updateStatus(Object master, int expect) {
		
		Reflect.invokeSetMethod(master, METHOD_RESULT, expect);
		if (Constants.RESULT_CHECK_OK == expect) {
			Reflect.invokeSetMethod(master, METHOD_CHECKER, Commons.getCurrentUser().getUserId());
			Reflect.invokeSetMethod(master, METHOD_CHECK_DATE, new Date());
		}
		baseDao.update(master);
		return true;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public boolean commit(Class<?> clazz, Integer id) {
		Object master = baseDao.get(clazz, id);
	    return updateStatus(master, Constants.RESULT_CHECK_PENDING);
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean check(Class<?> clazz, Integer id) {
		Object master = baseDao.get(clazz, id);
	    return updateStatus(master, Constants.RESULT_CHECK_OK);
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean changeCommit(Class<?> clazz, Integer id) {
		Object master = baseDao.get(clazz, id);
	    return updateStatus(master, Constants.RESULT_CHECK_CHANGE);
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean cancelCheck(Class<?> clazz, Integer id, String cancelReason) {
		//get persistance object
		Object master = baseDao.get(clazz, id);
		
		//cancel result flag
		Reflect.invokeSetMethod(master, METHOD_RESULT, Constants.RESULT_NONE);
		
		//cancel check content
		Reflect.invokeSetMethodAllowNull(master, METHOD_CHECKER, Integer.class, (Integer)null);
		Reflect.invokeSetMethodAllowNull(master, METHOD_CHECK_DATE, Date.class, (Date)null);
		
		//update cancel reason
		SimpleDateFormat simpleDateFormat  = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String separator = simpleDateFormat.format(new Date()) + "----------";
		
		String reason = (String) Reflect.invokeGetMethod(master, METHOD_CHANGE_REASON);
		if(reason == null){
			reason = "";
		}
		
		StringBuffer sBuffer = new StringBuffer();
		sBuffer.append(separator);
		sBuffer.append("\n");
		sBuffer.append(cancelReason);
		sBuffer.append("\n\n");
		sBuffer.append(reason);
		
		Reflect.invokeSetMethod(master, METHOD_CHANGE_REASON, sBuffer.toString());
		
		//update object
		baseDao.update(master);
		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean updateSettleInfo(Class<?> clazz, Integer id, boolean isDelete) {
		
		RecProceeds recProceeds = (RecProceeds) baseDao.get(clazz, id);
		
		if(null == recProceeds){
			return false;
		}
		
		String sourceBillNo = recProceeds.getSourceBillNo();
		
		if(null == sourceBillNo || "".equals(sourceBillNo)){
			return false;
		}
		
		SettleItem settleItem = recProceedsDao.mergeSettleAmount(sourceBillNo, 
				recProceeds.getInterId());
		
		double settleAmount = settleItem.getSettleAmount();
		double bankCost = settleItem.getBankCost();
		double amount = settleItem.getAmount();
		
		if(!isDelete){
			settleAmount += Math.abs(recProceeds.getSettleAmount());
			bankCost += recProceeds.getBankCost();
		}
				
		Integer settleStatus = Constants.SETTLE_STATUS_PART;
		if((settleAmount + bankCost) >= amount){
			settleStatus = Constants.SETTLE_STATUS_FULL;
		}
		
		SalesOrder salesOrder = salesOrderDao.findByBillNo(sourceBillNo);
		salesOrder.setSettleAmount(settleAmount);
		salesOrder.setBankCost(bankCost);
		salesOrder.setSettleCurrency(recProceeds.getSettleCurrency());
		salesOrder.setSettleStatus(settleStatus);
		salesOrderDao.update(salesOrder);
		return true;
	}

}
