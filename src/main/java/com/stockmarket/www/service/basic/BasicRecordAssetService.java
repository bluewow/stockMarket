package com.stockmarket.www.service.basic;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stockmarket.www.dao.RecordAssetDao;
import com.stockmarket.www.entity.RecordAsset;
import com.stockmarket.www.service.RecordAssetService;

@Service
public class BasicRecordAssetService implements RecordAssetService {

	@Autowired
	private RecordAssetDao recordAssetDao; 

	@Override
	public List<RecordAsset> getRecordAssetList(int memberId) {
		return recordAssetDao.getList(memberId);
	}

}
