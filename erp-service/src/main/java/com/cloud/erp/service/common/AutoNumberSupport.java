package com.cloud.erp.service.common;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cloud.erp.dao.exception.NumberIncrementException;

@Service("autoNumber")
public class AutoNumberSupport<T> extends NumberService<T> implements AutoNumber<T> {

	@Override
	public String getNumber(List<T> list) {
		return super.getNumber(list);
	}

	@Override
	public boolean isThisType(String number, int classId) {
		return super.isThisType(number, classId);
	}

	@Override
	public void increment(Integer classId) throws NumberIncrementException {
		super.increment(classId);
	}

}
