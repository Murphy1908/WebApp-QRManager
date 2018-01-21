/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.qrcode.web;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.qrcode.entity.QrcodeSubject;
import com.thinkgem.jeesite.modules.qrcode.service.QrcodeSubjectService;

/**
 * 物品分类生成Controller
 * @author Murphy
 * @version 2018-01-21
 */
@Controller
@RequestMapping(value = "${adminPath}/qrcode/qrcodeSubject")
public class QrcodeSubjectController extends BaseController {

	@Autowired
	private QrcodeSubjectService qrcodeSubjectService;
	
	@ModelAttribute
	public QrcodeSubject get(@RequestParam(required=false) String id) {
		QrcodeSubject entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = qrcodeSubjectService.get(id);
		}
		if (entity == null){
			entity = new QrcodeSubject();
		}
		return entity;
	}
	
	@RequiresPermissions("qrcode:qrcodeSubject:view")
	@RequestMapping(value = {"list", ""})
	public String list(QrcodeSubject qrcodeSubject, HttpServletRequest request, HttpServletResponse response, Model model) {
		List<QrcodeSubject> list = qrcodeSubjectService.findList(qrcodeSubject); 
		model.addAttribute("list", list);
		return "modules/qrcode/qrcodeSubjectList";
	}

	@RequiresPermissions("qrcode:qrcodeSubject:view")
	@RequestMapping(value = "form")
	public String form(QrcodeSubject qrcodeSubject, Model model) {
		if (qrcodeSubject.getParent()!=null && StringUtils.isNotBlank(qrcodeSubject.getParent().getId())){
			qrcodeSubject.setParent(qrcodeSubjectService.get(qrcodeSubject.getParent().getId()));
			// 获取排序号，最末节点排序号+30
			if (StringUtils.isBlank(qrcodeSubject.getId())){
				QrcodeSubject qrcodeSubjectChild = new QrcodeSubject();
				qrcodeSubjectChild.setParent(new QrcodeSubject(qrcodeSubject.getParent().getId()));
				List<QrcodeSubject> list = qrcodeSubjectService.findList(qrcodeSubject); 
				if (list.size() > 0){
					qrcodeSubject.setSort(list.get(list.size()-1).getSort());
					if (qrcodeSubject.getSort() != null){
						qrcodeSubject.setSort(qrcodeSubject.getSort() + 30);
					}
				}
			}
		}
		if (qrcodeSubject.getSort() == null){
			qrcodeSubject.setSort(30);
		}
		model.addAttribute("qrcodeSubject", qrcodeSubject);
		return "modules/qrcode/qrcodeSubjectForm";
	}

	@RequiresPermissions("qrcode:qrcodeSubject:edit")
	@RequestMapping(value = "save")
	public String save(QrcodeSubject qrcodeSubject, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, qrcodeSubject)){
			return form(qrcodeSubject, model);
		}
		qrcodeSubjectService.save(qrcodeSubject);
		addMessage(redirectAttributes, "保存物品分类成功");
		return "redirect:"+Global.getAdminPath()+"/qrcode/qrcodeSubject/?repage";
	}
	
	@RequiresPermissions("qrcode:qrcodeSubject:edit")
	@RequestMapping(value = "delete")
	public String delete(QrcodeSubject qrcodeSubject, RedirectAttributes redirectAttributes) {
		qrcodeSubjectService.delete(qrcodeSubject);
		addMessage(redirectAttributes, "删除物品分类成功");
		return "redirect:"+Global.getAdminPath()+"/qrcode/qrcodeSubject/?repage";
	}

	@RequiresPermissions("user")
	@ResponseBody
	@RequestMapping(value = "treeData")
	public List<Map<String, Object>> treeData(@RequestParam(required=false) String extId, @RequestParam(required=false) String type, HttpServletResponse response) {
		List<Map<String, Object>> mapList = Lists.newArrayList();
		QrcodeSubject qrcodeSubject = new QrcodeSubject();
		if( "1".equals(type) ){
			qrcodeSubject.setType(type);
		}		
		List<QrcodeSubject> list = qrcodeSubjectService.findList(qrcodeSubject);
		for (int i=0; i<list.size(); i++){
			QrcodeSubject e = list.get(i);
			if (StringUtils.isBlank(extId) || (extId!=null && !extId.equals(e.getId()) && e.getParentIds().indexOf(","+extId+",")==-1)){
				Map<String, Object> map = Maps.newHashMap();
				map.put("id", e.getId());
				map.put("pId", e.getParentId());
				map.put("name", e.getName());
				mapList.add(map);
			}
		}
		return mapList;
	}
	
}