package com.yizhi.student.service.impl;

import com.yizhi.common.utils.PageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.yizhi.student.dao.StudentInfoDao;
import com.yizhi.student.domain.StudentInfoDO;
import com.yizhi.student.service.StudentInfoService;



@Service
public class StudentInfoServiceImpl implements StudentInfoService {

	@Autowired
	private StudentInfoDao studentInfoDao;

	@Override
	public Integer getAll(String name, int tocollegelId, int tomajorId, int classId) {
		return studentInfoDao.getAll(name, tocollegelId, tomajorId, classId);
	}

	@Override
	public StudentInfoDO get(Integer id){
		System.out.println("======service层中传递过来的id参数是：" + id + "======");
		return studentInfoDao.get(id);
	}

	@Override
	public PageUtils list(Map<String, Object> params) {

		int currPage = Integer.parseInt((String) params.get("currPage"));
		int pageSize = Integer.parseInt((String) params.get("pageSize"));
		String name;
		if (params.get("name") == null || params.get("name") == "") {
			name = null;
		} else {
			name = "%" + params.get("name") + "%";
		}
		int tocollegelId;
		int tomajorId;
		int classId;
		if (params.get("tocollegelId") == null || params.get("tocollegelId") == "") {
			tocollegelId = -1;
		} else {
			tocollegelId = Integer.parseInt((String) params.get("tocollegelId"));
		}
		System.out.println("coll==="+tocollegelId);

		if (params.get("tomajorId") == null || params.get("tomajorId") == "") {
			tomajorId = -1;
		} else {
			tomajorId = Integer.parseInt((String) params.get("tomajorId"));
		}
		System.out.println("coll==="+tocollegelId);

		if (params.get("classId") == null || params.get("classId") == "") {
			classId = -1;
		} else {
			classId = Integer.parseInt((String) params.get("classId"));
		}

		int start = (currPage-1) * pageSize;
		List<StudentInfoDO> list = studentInfoDao.list(start,pageSize,name,tocollegelId,tomajorId,classId);
		final Integer all = studentInfoDao.getAll(name, tocollegelId, tomajorId, classId);
		PageUtils pageUtils = new PageUtils();
		pageUtils.setTotal(all);
		pageUtils.setRows(list);
		pageUtils.setTotalPages(all/pageSize);
		return pageUtils;
	}

	//"===================================================================================="


	@Override
	public int count(Map<String, Object> map){
		return studentInfoDao.count(map);
	}
	
	@Override
	public int save(StudentInfoDO studentInfo){
		studentInfo.setAddTime(new Date());
		studentInfo.setAddUserid(1);
		return studentInfoDao.save(studentInfo);
	}
	
	@Override
	public int update(StudentInfoDO studentInfo){
		studentInfo.setEditTime(new Date());
		studentInfo.setEditUserid(1);
		return studentInfoDao.update(studentInfo);
	}
	
	@Override
	public int remove(Integer id){
		return studentInfoDao.remove(id);
	}
	
	@Override
	public int batchRemove(Integer[] ids){
		return studentInfoDao.batchRemove(ids);
	}
	
}
