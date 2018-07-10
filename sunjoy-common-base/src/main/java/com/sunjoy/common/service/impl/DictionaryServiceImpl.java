package com.sunjoy.common.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.sunjoy.common.dao.entity.Dictionary;
import com.sunjoy.common.service.IDictionaryService;

/**
 *
 * @author liuganchao<740033486@qq.com>
 * @date 2018年7月5日
 */
@Service(value="dictionaryService")
public class DictionaryServiceImpl implements IDictionaryService{

	@Override
	public List<Dictionary> getDictionaries(String typeCode) {
		List<Dictionary> dictionaries=null;
		switch(typeCode){
			case "SCHOOL":
				dictionaries=getSchools();
				break;
			case "COURSE-GRADE":
				dictionaries=getCourseGrades();
				break;
			case "COURSE-PHASE":
				dictionaries=getCoursePhases();
				break;
		}
		// TODO Auto-generated method stub
		return dictionaries;
	}

	private List<Dictionary>  getSchools(){
		List<Dictionary> dictionaries=new ArrayList<Dictionary>();
		
		return dictionaries;
	}
	
	private List<Dictionary>  getCourseGrades(){
		List<Dictionary> dictionaries=new ArrayList<Dictionary>();
		String[] items=new String[]{"幼儿班","基础班","提高班","精英班"};
		
		for(int i=0;i<items.length;i++){
			Dictionary dict=new Dictionary();
			dict.setItemCode(String.valueOf(i+1));
			dict.setItemName(items[i]);
			dict.setTypeCode("COURSE-GRADE");
			dict.setTypeName("课程等级");
		}
		return dictionaries;
	}
	
	private List<Dictionary>  getCoursePhases(){
		List<Dictionary> dictionaries=new ArrayList<Dictionary>();
		String[] items=new String[]{"第一阶段","第二阶段","第三阶段","第四阶段"};
		for(int i=0;i<items.length;i++){
			Dictionary dict=new Dictionary();
			dict.setItemCode(String.valueOf(i+1));
			dict.setItemName(items[i]);
			dict.setTypeCode("COURSE-PHASE");
			dict.setTypeName("课程阶段");
		}
		return dictionaries;
	}
}
