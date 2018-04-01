package com.bootdo.system.service;

import com.bootdo.system.domain.TestDO;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2018-03-31 11:22:34
 */
public interface TestService {
	
	TestDO get(Integer uid);
	
	List<TestDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(TestDO test);
	
	int update(TestDO test);
	
	int remove(Integer uid);
	
	int batchRemove(Integer[] uids);
}
