/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.qrcode.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.qrcode.entity.QrcodeMain;

/**
 * 物品数据生成DAO接口
 * @author Murphy
 * @version 2018-01-21
 */
@MyBatisDao
public interface QrcodeMainDao extends CrudDao<QrcodeMain> {
	
}