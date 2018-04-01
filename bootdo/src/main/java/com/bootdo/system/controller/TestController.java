package com.bootdo.system.controller;

import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;
import com.bootdo.system.domain.TestDO;
import com.bootdo.system.service.TestService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2018-03-31 11:22:34
 */
 
@Controller
@RequestMapping("/system/test")
public class TestController {
	@Autowired
	private TestService testService;
	
	@GetMapping()
	@RequiresPermissions("system:test:test")
	String Test(){
	    return "system/test/test";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("system:test:test")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<TestDO> testList = testService.list(query);
		int total = testService.count(query);
		PageUtils pageUtils = new PageUtils(testList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("system:test:add")
	String add(){
	    return "system/test/add";
	}

	@GetMapping("/edit/{uid}")
	@RequiresPermissions("system:test:edit")
	String edit(@PathVariable("uid") Integer uid,Model model){
		TestDO test = testService.get(uid);
		model.addAttribute("test", test);
	    return "system/test/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("system:test:add")
	public R save( TestDO test){
		if(testService.save(test)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("system:test:edit")
	public R update( TestDO test){
		testService.update(test);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("system:test:remove")
	public R remove( Integer uid){
		if(testService.remove(uid)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("system:test:batchRemove")
	public R remove(@RequestParam("ids[]") Integer[] uids){
		testService.batchRemove(uids);
		return R.ok();
	}

	@GetMapping("/listTest")
	String listTest(){
		return "system/test/listTest";
	}
}
