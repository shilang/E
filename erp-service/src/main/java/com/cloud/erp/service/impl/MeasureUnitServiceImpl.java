/**
 * @Title:  MeasureUnitServiceImpl.java
 * @Package:  com.cloud.erp.service.impl
 * @Description:  
 * Copyright:  Copyright(C) 2015
 * @author:  bollen bollen@live.cn
 * @date:  2015年5月21日 下午3:59:06
 * @version:  v1.0
 *
 * History:
 * Date		Author		Version
 * ---------------------------------------------
 * <reasons>
 */
package com.cloud.erp.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cloud.erp.dao.MeasureUnitDao;
import com.cloud.erp.entities.table.MeasureUnit;
import com.cloud.erp.service.MeasureUnitService;
import com.cloud.erp.utils.PageUtil;

/**
 * @ClassName MeasureUnitServiceImpl
 * @Description 
 * @author bollen bollen@live.cn
 * @date 2015年5月21日 下午3:59:06
 *
 */
@Service("measureUnitService")
public class MeasureUnitServiceImpl implements MeasureUnitService {

	@Autowired
	private MeasureUnitDao measureUnitDao;

	@Override
	public List<MeasureUnit> findMeasureUnits() {
		return measureUnitDao.findAll(null, null);
	}

	@Override
	public long getCount() {
		return measureUnitDao.getCount(null);
	}

	@Override
	public List<MeasureUnit> findAll(Map<String, Object> params,
			PageUtil pageUtil) {
		return measureUnitDao.findAll(params, pageUtil);
	}

	@Override
	public long getCount(Map<String, Object> params) {
		return measureUnitDao.getCount(params);
	}

	@Override
	public MeasureUnit get(Integer id) {
		return measureUnitDao.get(id);
	}

	@Override
	public void update(MeasureUnit master) {
		measureUnitDao.update(master);
	}

	@Override
	public boolean persistence(MeasureUnit master) throws Exception {
		return measureUnitDao.persistence(master);
	}

	@Override
	public boolean deleteToUpdate(Integer pid) {
		return measureUnitDao.deleteToUpdate(pid);
	}

}
