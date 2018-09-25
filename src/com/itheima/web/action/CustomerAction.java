package com.itheima.web.action;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.itheima.domain.Customer;
import com.itheima.domain.Dict;
import com.itheima.domain.PageBean;
import com.itheima.service.CustomerService;
import com.itheima.utils.FastJsonUtil;
import com.itheima.utils.UploadUtils;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.util.ValueStack;

public class CustomerAction extends ActionSupport implements ModelDriven<Customer>{

	private static final long serialVersionUID = 1L;
	
	private CustomerService customerservice;
	public void setCustomerservice(CustomerService cus) {
		this.customerservice = cus;
	}
	
	//记得手动 new 一个对象
	private Customer customer = new Customer();
	public Customer getModel() {
		return customer;
	}
	
	//属性驱动的方式
	//当前页，默认值是1
	private Integer pageCode = 1;
	public void setPageCode(Integer pageCode) {
		if (pageCode == null) {
			pageCode = 1;
		}
		this.pageCode = pageCode;
	}

	//每页显示的数据的条数
	private Integer pageSize = 4;
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	/**
	 * 分页查询方法
	 * @return
	 */
	public String findByPage() {
		//调用service业务层
		DetachedCriteria criteria = DetachedCriteria.forClass(Customer.class);
		
		//拼接查询的条件
		String cust_name = customer.getCust_name();
		
		if(cust_name != null && !cust_name.trim().isEmpty()) {
			
			//模糊查询，输入客户名称的查询的条件
			criteria.add(Restrictions.like("cust_name", "%"+cust_name+"%"));
		}
		
		//获取不同属性对应在字典表中的Id号
		Dict source = customer.getSource();
		if(source != null && !source.getDict_id().trim().isEmpty()) {
			criteria.add(Restrictions.eq("source.dict_id", source.getDict_id()));
		}
		
		Dict industry = customer.getIndustry();
		if(industry != null && !industry.getDict_id().trim().isEmpty()) {
			criteria.add(Restrictions.eq("industry.dict_id", industry.getDict_id()));
		}
		
		Dict level = customer.getLevel();
		if(level != null && !level.getDict_id().trim().isEmpty()) {
			criteria.add(Restrictions.eq("level.dict_id", level.getDict_id()));
		}
		
		//查询
		PageBean<Customer> page = customerservice.findByPage(pageCode,pageSize,criteria);
		
		//压栈
		ValueStack vs = ActionContext.getContext().getValueStack();
		//栈顶是map<"page",page对象>
		vs.set("page", page);
		
		return "page";
	}
	
	/**
	 * 初始化到添加页面（add.jsp）
	 * @return
	 */
	public String initAddUI() {
		return "initAddUI";
	}
	
	public String initUpdate(){
		customer = customerservice.findById(customer.getCust_id());
		
		ValueStack vs = ActionContext.getContext().getValueStack();
		vs.set("customer", customer);
		return "initUpdate";
	}
	
	/**
	 *	文件的上传，需要在CustomerAction类中定义成员的属性，命名是有规则的
	 *	private File upload		//表示要上传的文件
	 *	private String uploadFileName	//表示是上传文件的名称(没有中文乱码)
	 *	private String uploadContentType	//表示上传文件的MIME类型
	 *	提供set方法，拦截器就注入值了
	 */
	
	//表示要上传的文件
	private File upload;
	//表示是上传文件的名称(没有中文乱码)
	private String uploadFileName;
	//表示上传文件的MIME类型
	private String uploadContentType;
	public void setUpload(File upload) {
		this.upload = upload;
	}

	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}

	public void setUploadContentType(String uploadContentType) {
		this.uploadContentType = uploadContentType;
	}

	/**
	 * 保存客户的方法
	 * @return
	 * @throws IOException 
	 */
	public String save() throws IOException {
		//做文件的上传,说明用户选择上传的文件
		if(uploadFileName != null) {
			//打印
			System.out.println("文件名称"+uploadFileName);
			System.out.println("文件类型"+uploadContentType);
			
			//处理文件名
			String uuidname = UploadUtils.getUUIDName(uploadFileName);
			//文件保存路径	D:\\apache-tomcat-7.0.52\\webapps\\upload
			String path = "D:\\apache-tomcat-7.0.52\\webapps\\upload";
			File file = new File(path+"\\"+uuidname);
			//简单方式
			FileUtils.copyFile(upload, file);
			//把上传的文件路径保存到客户列表中
			customer.setFilepath(path+"\\"+uuidname);
			
		}
		
		customerservice.add(customer);
		return "save";
	}
	
	/**
	 * 	删除用户，先通过cust_id 获取到用户信息，获取上传的文件路径
	 * @return
	 */
	public String delete() {
		//通过cust_id 获取到用户信息
		customer = customerservice.findById(customer.getCust_id());
		//获取上传的文件路径
		String path = customer.getFilepath();
		//删除数据库中的用户
		customerservice.delete(customer);
		//删除上传的文件
		File file = new File(path);
		//判断文件是否存在
		if (file.exists()) {
			file.delete();
		}
		
		return "delete";
	}
	
	public String update() throws IOException{
		if(uploadFileName != null) {
			String oldfilepath = customer.getFilepath();
			if(oldfilepath != null && !oldfilepath.trim().isEmpty()){
				File f = new File(oldfilepath);
				f.delete();
			}
			
			//打印
			System.out.println("文件名称"+uploadFileName);
			System.out.println("文件类型"+uploadContentType);
			
			//处理文件名
			String uuidname = UploadUtils.getUUIDName(uploadFileName);
			//文件保存路径	D:\\apache-tomcat-7.0.52\\webapps\\upload
			String path = "D:\\apache-tomcat-7.0.52\\webapps\\upload";
			File file = new File(path+"\\"+uuidname);
			//简单方式
			FileUtils.copyFile(upload, file);
			//把上传的文件路径保存到客户列表中
			customer.setFilepath(path+"\\"+uuidname);
		}
		System.out.println(customer);
		customerservice.update(customer);
		
		return "update";
	}
	
	public String findAll(){
		List<Customer> list = customerservice.findAll();
		// 转换成json
		String jsonString = FastJsonUtil.toJSONString(list);
		HttpServletResponse response = ServletActionContext.getResponse();
		FastJsonUtil.write_json(response, jsonString);
		
		return null;
		
	}
}
