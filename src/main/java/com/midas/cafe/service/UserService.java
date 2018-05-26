package com.midas.cafe.service;

import com.midas.cafe.common.StrUtil;
import com.midas.cafe.model.LoginVO;
import com.midas.cafe.model.Result;
import com.midas.cafe.model.SearchCriteria;
import com.midas.cafe.model.User;
import com.midas.cafe.model.UserReservation;
import com.midas.cafe.repository.menu.MenuDao;
import com.midas.cafe.repository.user.UserDao;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sun.applet.resources.MsgAppletViewer;

/**
 * User: kimkm
 * Date: 2018-05-26
 * Time: 오후 4:01
 */
@Service
public class UserService
{
	@Autowired
	private UserDao userDao;

	public int joinUser(User user) throws Exception
	{
		return userDao.insertUser(user);
	}

	public void cancelReservation(String code)
	{
		userDao.updateUserCancel(code);
	}

    public String selectPwById(String id){ return userDao.selectPwById(id); }

    public User selectUserById(String id){ return userDao.selectUserById(id); }

    public String getCompleteReserveOrderNotifyMessage(String loginID)
    {
	    List<Map<String,Object>> list = userDao.getCompleteReserveOrder(loginID);
	    String msg = "";
	    for (Map<String,Object> map : list)
	    {
	    	if(msg.length() != 0)
			    msg += ",";
		    String name = (String) map.get("cafe_name");
		    String amount = map.get("amount") + "";
		    msg += (name + " " + amount + "개");
	    }
	    if(msg.length() != 0)
		    msg += " 가 준비완료 되었습니다.";
	    return msg;
    }

    public int updateUserInfo(User user) throws Exception
    {return userDao.updateUser(user);}

    public Result notifyOff(String loginID)
    {
	    return new Result(true, userDao.notifyOff(loginID));
    }
	public List<UserReservation> getAllReservation(String loginID)
	{
		return userDao.selectReservation(loginID);
	}

	public int updateUser(User user) {
		return userDao.updateUser2(user);
	}

	public Result getAllReservationDetail(String reservationCode) {
		List<Map<String,Object>> list = userDao.selectReservationDetail(reservationCode);
		return new Result(true, list);
	}

	public void addReservation(String loginID, String reserveDt, String description, List<String> detail)
	{
		userDao.insertReservation(loginID, reserveDt, description);
		Integer reserveIdx = userDao.getLastInsertID();
		for(String str : detail)
		{
			List<String> list = StrUtil.splitToList(str, ":");
			String code = list.get(0);
			String amount = list.get(1);
			userDao.insertReservationDetail(reserveIdx, code, amount);
		}
	}

	public List<Map<String, Object>> findAllUsersCoupon(String userId) {
		return userDao.findAllUsersCoupon(userId);
	}

	public List<Map<String, Object>> findAllUser() {
		return userDao.findAllUser();
	}

	public int delete(String userId) {
		return userDao.delete(userId);
	}

	public Result getNotification(LoginVO user) {
		return new Result(true, userDao.findNotification(user.getId()));
	}

	public List<Map<String,Object>> selectAllPurchase(String id){return userDao.selectPurchaseList(id);}

	public List<UserReservation> getAllReservationMon(String loginID, SearchCriteria cri)
	{
		return userDao.selectReservationMon(loginID,cri);
	}
}
