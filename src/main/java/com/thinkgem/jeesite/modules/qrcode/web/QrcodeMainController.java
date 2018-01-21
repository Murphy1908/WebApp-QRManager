/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.qrcode.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.qrcode.entity.QrcodeMain;
import com.thinkgem.jeesite.modules.qrcode.service.QrcodeMainService;

/**
 * 物品数据生成Controller
 * @author Murphy
 * @version 2018-01-21
 */
@Controller
@RequestMapping(value = "${adminPath}/qrcode/qrcodeMain")
public class QrcodeMainController extends BaseController {

	@Autowired
	private QrcodeMainService qrcodeMainService;
	
	@ModelAttribute
	public QrcodeMain get(@RequestParam(required=false) String id) {
		QrcodeMain entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = qrcodeMainService.get(id);
		}
		if (entity == null){
			entity = new QrcodeMain();
		}
		return entity;
	}
	
	@RequiresPermissions("qrcode:qrcodeMain:view")
	@RequestMapping(value = {"list", ""})
	public String list(QrcodeMain qrcodeMain, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<QrcodeMain> page = qrcodeMainService.findPage(new Page<QrcodeMain>(request, response), qrcodeMain); 
		model.addAttribute("page", page);
		return "modules/qrcode/qrcodeMainList";
	}

	@RequiresPermissions("qrcode:qrcodeMain:view")
	@RequestMapping(value = "form")
	public String form(QrcodeMain qrcodeMain, Model model) {
		model.addAttribute("qrcodeMain", qrcodeMain);
		return "modules/qrcode/qrcodeMainForm";
	}

	@RequiresPermissions("qrcode:qrcodeMain:edit")
	@RequestMapping(value = "save")
	public String save(QrcodeMain qrcodeMain, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, qrcodeMain)){
			return form(qrcodeMain, model);
		}
		qrcodeMainService.save(qrcodeMain);
		addMessage(redirectAttributes, "保存物品数据成功");
		return "redirect:"+Global.getAdminPath()+"/qrcode/qrcodeMain/?repage";
	}
	
	@RequiresPermissions("qrcode:qrcodeMain:edit")
	@RequestMapping(value = "delete")
	public String delete(QrcodeMain qrcodeMain, RedirectAttributes redirectAttributes) {
		qrcodeMainService.delete(qrcodeMain);
		addMessage(redirectAttributes, "删除物品数据成功");
		return "redirect:"+Global.getAdminPath()+"/qrcode/qrcodeMain/?repage";
	}

}