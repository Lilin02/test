package com.itheima.web.action;


import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

//import com.itheima.domain.Customer;
import com.itheima.domain.User;
import com.itheima.service.UserService;
import com.itheima.utils.FastJsonUtil;
import com.itheima.utils.MD5Utils;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class UserAction extends ActionSupport implements ModelDriven<User>{

	private static final long serialVersionUID = -3413092622818913571L;
	
	private User user = new User();

	//封装实体类对象，需要手动实例化
	public User getModel() {
		return user;
	}

	public UserService userService;
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	private String identify;
	public String getIdentify() {
		return identify;
	}
	public void setIdentify(String identify) {
		this.identify = identify;
	}
	
	/**
	 * 注册功能
	 */
	public String regist() {
//		System.out.println("注册...");
		//接收请求参数
		userService.save(user);
		
		return LOGIN;
	}
	
	/**
	 * 通过登录名判断，该登录名是否存在
	 * @return
	 */
	public String checkCode() {
		System.out.println("2222");
		System.out.println(user.getUser_code());
		//调用业务层，查询
		User u = userService.checkCode(user.getUser_code());
		
		//获取response对象 
		HttpServletResponse response = ServletActionContext.getResponse();
		//设置编码
		response.setContentType("text/html;charset=UTF-8");
		try {
			//输出流
			PrintWriter write = response.getWriter();
			//进行判断
			if(u != null) {
				//说明：登录名查询到用户了，说明登陆已经存在了，不能注册了
				write.print("no");
			}else {
				//说明，不存在登录名，可以注册
				write.print("yes");
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return NONE;
	}
	
	/**
	 *  画图功能，验证码实现
	 */
	public String doImg() throws Exception{
		int width = 120;
		int height = 30;
		System.out.println("进入doImg");
		// 步骤一 绘制一张内存中图片
		BufferedImage bufferedImage = new BufferedImage(width, height,BufferedImage.TYPE_INT_RGB);
 
		// 步骤二 图片绘制背景颜色 ---通过绘图对象
		Graphics graphics = bufferedImage.getGraphics();// 得到画图对象 --- 画笔
		// 绘制任何图形之前 都必须指定一个颜色
		graphics.setColor(getRandColor(200, 250));
		graphics.fillRect(0, 0, width, height);
 
		// 步骤三 绘制边框
		graphics.setColor(Color.WHITE);
		graphics.drawRect(0, 0, width - 1, height - 1);
 
		// 步骤四 四个随机数字
		Graphics2D graphics2d = (Graphics2D) graphics;
		// 设置输出字体
		graphics2d.setFont(new Font("宋体", Font.BOLD, 18));
		
		 String words ="ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890";

		 Random random = new Random();// 生成随机数
		// 定义StringBuffer 
		StringBuffer sb = new StringBuffer();
		// 定义x坐标
		int x = 10;
		for (int i = 0; i < 4; i++) {
			// 随机颜色
			graphics2d.setColor(new Color(20 + random.nextInt(110), 20 + random
					.nextInt(110), 20 + random.nextInt(110)));
			// 旋转 -30 --- 30度
			int jiaodu = random.nextInt(60) - 30;
			// 换算弧度
			double theta = jiaodu * Math.PI / 180;
 
			// 生成一个随机数字
			int index = random.nextInt(words.length()); // 生成随机数 0 到 length - 1
			// 获得字母数字
			char c = words.charAt(index);
			sb.append(c);
			// 将c 输出到图片
			graphics2d.rotate(theta, x, 20);
			graphics2d.drawString(String.valueOf(c), x, 20);
			graphics2d.rotate(-theta, x, 20);
			x += 30;
		}
 
		// 将生成的字母存入到session中
		ServletActionContext.getRequest().getSession().setAttribute("checkcode", sb.toString());
 
		// 步骤五 绘制干扰线
		graphics.setColor(getRandColor(160, 200));
		int x1;
		int x2;
		int y1;
		int y2;
		for (int i = 0; i < 30; i++) {
			x1 = random.nextInt(width);
			x2 = random.nextInt(12);
			y1 = random.nextInt(height);
			y2 = random.nextInt(12);
			graphics.drawLine(x1, y1, x1 + x2, x2 + y2);
		}
 
		// 将上面图片输出到浏览器 ImageIO
		graphics.dispose();// 释放资源
		ImageIO.write(bufferedImage, "jpg", ServletActionContext.getResponse().getOutputStream());
		return NONE;
	}
	/**
	 * 取其某一范围的color
	 * 
	 * @param fc
	 *            int 范围参数1
	 * @param bc
	 *            int 范围参数2
	 * @return Color
	 */
	private Color getRandColor(int fc, int bc) {
		// 取其随机颜色
		Random random = new Random();
		if (fc > 255) {
			fc = 255;
		}
		if (bc > 255) {
			bc = 255;
		}
		int r = fc + random.nextInt(bc - fc);
		int g = fc + random.nextInt(bc - fc);
		int b = fc + random.nextInt(bc - fc);
		return new Color(r, g, b);
	}
	
	/**
	 *  判断验证码是否正确
	 * @return
	 */
	public String checkYan() {
		//获取session中系统生成的验证码
		String yan = (String) ServletActionContext.getRequest().getSession().getAttribute("checkcode");
		//将验证码转化为小写
		String LowYan = yan.toLowerCase();
		System.out.println("开始输出验证码: "+LowYan);
		//获取手动输入的验证码
		String iden = ServletActionContext.getRequest().getParameter("identify");
		this.setIdentify(iden);
		System.out.println("手动输入的验证码： " + this.getIdentify());
		
		//获取response对象 
		HttpServletResponse response = ServletActionContext.getResponse();
		//设置编码
		response.setContentType("text/html;charset=UTF-8");
		try {
			//输出流
			PrintWriter write = response.getWriter();
			//进行判断
			if(LowYan.equals(this.getIdentify())) {
				//说明：登录名查询到用户了，说明登陆已经存在了，不能注册了
				write.print("success");
			}else {
				//说明，不存在登录名，可以注册
				write.print("fail");
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return NONE;
	}
	
	/**
	 * 登录功能
	 * @return
	 */
	public String login() {
		User existUser = userService.login(user);
		//判断，登录名或者密码错误了
		if(existUser == null) {
			
			return LOGIN;
		}else {
			//登录成功
			ServletActionContext.getRequest().getSession().setAttribute("existUser", existUser);
			return "loginOK";
		}
	}	
	
	/**
	 * 安全退出
	 * @return
	 */
	public String exit() {
		ServletActionContext.getRequest().getSession().removeAttribute("existUser");
		return LOGIN;
	}
	
	public String findAll(){
		List<User> list = userService.findAll();
		// 转换成json
		String jsonString = FastJsonUtil.toJSONString(list);
		HttpServletResponse response = ServletActionContext.getResponse();
		FastJsonUtil.write_json(response, jsonString);
		
		return null;
	}
	
	private String oldPassword;
	
	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}
	
	public String getOldPassword() {
		return oldPassword;
	}

	public String edit(){
		return "edit";
	}

	public String MD5Password(){
		
		System.out.println("未加密的旧密码:"+oldPassword);
		this.setOldPassword(MD5Utils.md5(oldPassword));
		
		//获取response对象 
		HttpServletResponse response = ServletActionContext.getResponse();
		//设置编码
		response.setContentType("text/html;charset=UTF-8");

		System.out.println("加密之后的旧密码:"+oldPassword);
		try {
			//输出流
			PrintWriter write = response.getWriter();
			write.print(oldPassword);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return NONE;
	}
	
	public String update(){
		User loginUser = (User) ServletActionContext.getRequest().getSession().getAttribute("existUser");
		user.setUser_password(MD5Utils.md5(user.getUser_password()));
		//System.out.println(user.getUser_id()+"------"+user.getUser_password());
		if(loginUser != null){
			userService.update(user);
			//如果修改成功了 需要让用户重新登陆  并且使用新的密码  老的用户数据没用了  让其失效
			ServletActionContext.getRequest().getSession().removeAttribute("existUser");
		}
		
		return "login";
	}
}
