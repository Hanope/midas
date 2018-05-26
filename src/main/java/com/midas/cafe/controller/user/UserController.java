package com.midas.cafe.controller.user;

import com.midas.cafe.common.Crypt;
import com.midas.cafe.common.DateUtil;
import com.midas.cafe.common.StrUtil;
import com.midas.cafe.model.LoginVO;
import com.midas.cafe.model.User;
import com.midas.cafe.model.UserReservation;
import com.midas.cafe.service.DeptService;
import com.midas.cafe.service.MenuService;
import com.midas.cafe.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * User: kimkm
 * Date: 2018-05-26
 * Time: 오후 4:00
 */
@Controller
@RequestMapping("/user")
public class UserController
{
	@Autowired
	private UserService userService;
	@Autowired
	private MenuService menuService;
	@Autowired
	private DeptService deptService;

	@GetMapping("/login")
	public String loginGet()
	{
		return "/user/loginForm";
	}

	@PostMapping("/login")
	public String loginPost(Model model, LoginVO login, HttpSession session){
		String id=login.getId();
		try{
			String pwd = userService.selectPwById(id);

			if(pwd != null && Crypt.encrypt(login.getPassword()).equals(pwd)){
//			if(pwd != null && login.getPassword().equals(pwd)){
				User userInfo= userService.selectUserById(id);
				login.setId(userInfo.getId());//세션에 로그인정보 할당
				login.setName(userInfo.getName());
				login.setRole(userInfo.getRole());
				session.setAttribute("login",login);
				String notiMsg = userService.getCompleteReserveOrderNotifyMessage(login.getId());
				session.setAttribute("notify", notiMsg.equals("") ? null : notiMsg);
			}else{
				model.addAttribute("loginFailMsg",Boolean.TRUE);
				return "redirect:/";
			}
		}catch(Exception e){
			throw new RuntimeException(e);
		}
		String dest=(String)session.getAttribute("dest");//접근페이지로이동
		if(dest!=null){

			return "redirect:"+dest;
		}
		return "redirect:/";
	}

	@GetMapping("/join")
	public ModelAndView joinGet() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/user/joinForm");
		modelAndView.addObject("depts", deptService.findAllDept());
		return modelAndView;
	}

	@PostMapping("/join")
	public String joinPost(User user)
	{
		user.setRegDate(DateUtil.currentTimestamp());//등록날짜
		System.out.println(user.toString());
		try
		{
			userService.joinUser(user);
		}
		catch (Exception e)
		{
		}
		return "/index";
	}

	@GetMapping("/logout")
	public String logout(HttpSession session){
		if(session!=null)
			session.invalidate();
		return "redirect:/";
	}

	@GetMapping("/myPage")
	public String myPageGet(Model model, HttpSession session){
		LoginVO login=(LoginVO)session.getAttribute("login");
		User user=userService.selectUserById(login.getId());
		user.setBirthday(user.getBirthday().substring(0,10));
		model.addAttribute("user",user);
		return "/user/myPage";
	}

	@PostMapping("/myPage")
	public String myPagePost(User user,Model model, HttpSession session){
		LoginVO login=(LoginVO)session.getAttribute("login");
		String id=login.getId();
		user.setId(id);
		try{
			userService.updateUserInfo(user);
			model.addAttribute("changeSuccessMsg",Boolean.TRUE);
		}catch(Exception e){
			throw new RuntimeException(e);
		}

		return "/user/myPage";
	}

	@GetMapping("/myReserve")
	public ModelAndView myReserveGet(){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/user/myReserve");

		return modelAndView;
	}

	@PostMapping("/myReserve")
	public String myReservePost(User user){

		return "/user/myReserve";
	}


	@GetMapping("/reservation")
	public ModelAndView reservationView(HttpSession session)
	{
		LoginVO user = (LoginVO)session.getAttribute("login");
		ModelAndView modelAndView = new ModelAndView();
		List<UserReservation> list = userService.getAllReservation(user.getId());

		modelAndView.addObject("list", list);
		modelAndView.setViewName("/user/reservation_list");
		return modelAndView;
	}

	@GetMapping("/reservation/add")
	public ModelAndView reservationAddView(HttpSession session)
	{
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("menus", menuService.findAllMenu());
		modelAndView.setViewName("/user/reservation_add");
		return modelAndView;
	}

	@PostMapping("/reservation/add")
	public String reservationAdd(HttpSession session, @RequestParam String reservedt, @RequestParam String str, @RequestParam String note)
	{
		LoginVO user = (LoginVO)session.getAttribute("login");
		List<String> list = StrUtil.splitToList(str, ",");
		userService.addReservation(user.getId(), reservedt, note, list);
		return "redirect:/user/reservation";
	}

	@PostMapping("/reservation/cancel")
	public String cancelReservation(@RequestParam String code)
	{
		userService.cancelReservation(code);
		return "redirect:/user/reservation";
	}
}
