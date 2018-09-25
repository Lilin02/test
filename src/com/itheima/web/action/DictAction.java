package com.itheima.web.action;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.itheima.domain.Dict;
import com.itheima.service.DictService;
import com.itheima.utils.FastJsonUtil;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class DictAction extends ActionSupport implements ModelDriven<Dict>{

	private static final long serialVersionUID = 1L;
	
	private Dict dict = new Dict();
	public Dict getModel() {
		return dict;
	}
	
	private DictService dictService;
	public void setDictService(DictService dictService) {
		this.dictService = dictService;
	}
	
	/**
	 * 通过查询type_code的值，查询客户的级别，或者客户的来源
	 * @return
	 */
	public String findByCode() {
		//调用业务
		List<Dict> list = dictService.findByCode(dict.getDict_type_code());
		String jsonString = FastJsonUtil.toJSONString(list);
		
		//把Json字符串写回到浏览器
		HttpServletResponse response = ServletActionContext.getResponse();
		FastJsonUtil.write_json(response, jsonString);
		
		return NONE;
	}

}
