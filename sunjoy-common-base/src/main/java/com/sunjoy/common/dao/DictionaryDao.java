package com.sunjoy.common.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.sunjoy.common.dao.entity.Dictionary;
import com.sunjoy.common.dao.mapper.DictionaryMapper;
import com.sunjoy.framework.dao.BaseDao;
import com.sunjoy.framework.exception.CommonException;
/**
 * 数据字典DAO
 * @author habibliu
 *
 */
@Repository
public class DictionaryDao extends BaseDao<DictionaryMapper, Dictionary>{

	@Override
	protected void setMapperClass() {
		super.setMapperClass(DictionaryMapper.class);
	}

	@Override
	protected void setEntityClass() {
		super.setEntityClass(Dictionary.class);
	}

	public List<Dictionary> getDictionariesByType(String typeCode){
		return this.getMapper().queryDictionariesByType(typeCode);
	}
	
	public void insert(Dictionary dict) {
		int count= this.getMapper().insertDictionary(dict);
		if(count==0) {
			throw new CommonException("保存数据字典失败！");
		}
	}
	
	public String getMaxItemCode(String typeCode) {
		 return this.getMapper().getMaxItemCode(typeCode);
	}
	
	public Integer getMaxSequence(String typeCode) {
		return this.getMapper().getMaxSequence(typeCode);
	}
}
