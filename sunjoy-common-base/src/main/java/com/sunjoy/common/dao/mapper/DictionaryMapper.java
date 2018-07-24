package com.sunjoy.common.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.sunjoy.common.dao.entity.Dictionary;

public interface DictionaryMapper {

	List<Dictionary> queryDictionariesByType(@Param("typeCode") String typeCode);
	/**
	 * 新增数据字典项目
	 * @param dict
	 * @return
	 */
	int insertDictionary(Dictionary dict);
	/**
	 * 取指定typeCode的最大的ItemCode
	 * @param typeCode
	 * @return
	 */
	String getMaxItemCode(@Param("typeCode") String typeCode);
	/**
	 * 取指定typeCode的最大seq
	 * @param typeCode
	 * @return
	 */
	Integer getMaxSequence(@Param("typeCode") String typeCode);
}
