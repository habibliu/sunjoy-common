package com.sunjoy.common.service;

import java.util.List;

import com.sunjoy.common.dao.dto.DictionaryDto;
import com.sunjoy.common.dao.entity.Dictionary;

/**
 *
 * @author liuganchao<740033486@qq.com>
 * @date 2018年7月5日
 */
public interface IDictionaryService {
	/**
	 * 根据字典类型查询其明细项目
	 * @param typeCode
	 * @return
	 */
	List<Dictionary> getDictionaries(String typeCode);
	
	/**
	 * 增加数据字典项目
	 * @param dict
	 */
	Dictionary addDictionary(DictionaryDto dict);
}
