/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.qrcode.dao;

import com.thinkgem.jeesite.common.persistence.TreeDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.qrcode.entity.QrcodeSubject;

/**
 * 物品分类生成DAO接口
 * @author Murphy
 * @version 2018-01-21
 */
@MyBatisDao
public interface QrcodeSubjectDao extends TreeDao<QrcodeSubject> {
	
}