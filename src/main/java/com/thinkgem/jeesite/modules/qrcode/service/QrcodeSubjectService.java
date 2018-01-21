/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.qrcode.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.service.TreeService;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.qrcode.entity.QrcodeSubject;
import com.thinkgem.jeesite.modules.qrcode.dao.QrcodeSubjectDao;

/**
 * 物品分类生成Service
 * @author Murphy
 * @version 2018-01-21
 */
@Service
@Transactional(readOnly = true)
public class QrcodeSubjectService extends TreeService<QrcodeSubjectDao, QrcodeSubject> {

	public QrcodeSubject get(String id) {
		return super.get(id);
	}
	
	public List<QrcodeSubject> findList(QrcodeSubject qrcodeSubject) {
		if (StringUtils.isNotBlank(qrcodeSubject.getParentIds())){
			qrcodeSubject.setParentIds(","+qrcodeSubject.getParentIds()+",");
		}
		return super.findList(qrcodeSubject);
	}
	
	@Transactional(readOnly = false)
	public void save(QrcodeSubject qrcodeSubject) {
		super.save(qrcodeSubject);
	}
	
	@Transactional(readOnly = false)
	public void delete(QrcodeSubject qrcodeSubject) {
		super.delete(qrcodeSubject);
	}
	
}