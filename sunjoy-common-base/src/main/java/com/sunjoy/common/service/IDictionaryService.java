package com.sunjoy.common.service;

import java.util.List;

import com.sunjoy.common.dao.entity.Dictionary;

/**
 *
 * @author liuganchao<740033486@qq.com>
 * @date 2018年7月5日
 */
public interface IDictionaryService {
	
	List<Dictionary> getDictionaries(String typeCode);
}
