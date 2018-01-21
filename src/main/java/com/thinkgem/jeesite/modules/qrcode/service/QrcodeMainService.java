/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.qrcode.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.qrcode.entity.QrcodeMain;
import com.thinkgem.jeesite.modules.qrcode.dao.QrcodeMainDao;

/**
 * 物品数据生成Service
 * @author Murphy
 * @version 2018-01-21
 */
@Service
@Transactional(readOnly = true)
public class QrcodeMainService extends CrudService<QrcodeMainDao, QrcodeMain> {

	public QrcodeMain get(String id) {
		return super.get(id);
	}
	
	public List<QrcodeMain> findList(QrcodeMain qrcodeMain) {
		return super.findList(qrcodeMain);
	}
	
	public Page<QrcodeMain> findPage(Page<QrcodeMain> page, QrcodeMain qrcodeMain) {
		return super.findPage(page, qrcodeMain);
	}
	
	@Transactional(readOnly = false)
	public void save(QrcodeMain qrcodeMain) {
		super.save(qrcodeMain);
	}
	
	@Transactional(readOnly = false)
	public void delete(QrcodeMain qrcodeMain) {
		super.delete(qrcodeMain);
	}
	
}