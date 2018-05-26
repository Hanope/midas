package com.midas.cafe.repository.reservation;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ReservationDao {

  @Autowired
  private JdbcTemplate jdbcTemplate;

  public List<Map<String, Object>> findIngReservation() {
    StringBuilder query = new StringBuilder();
    query.append("SELECT mr.code, mr.loginid, mr.create_dt,  mr.reserve_dt, mr.status, mr.description, mr.end_date,");
    query.append(" mr.adm_cancel_rs, mrd.amount, mcm.name,  mrs.status as status_name FROM mi_rsr as mr");
    query.append(" INNER JOIN mi_rsr_detail as mrd");
    query.append(" ON mr.code = mrd.code");
    query.append(" INNER JOIN mi_cafe_menu as mcm");
    query.append(" ON menucode = mcm.code");
    query.append(" INNER JOIN mi_rsr_status mrs");
    query.append(" ON mr.status = mrs.code");
    query.append(" WHERE mr.status IN (0, 1, 2)");
    query.append(" ORDER BY create_dt");

    return jdbcTemplate.queryForList(query.toString());
  }

  public List<Map<String, Object>> findAll() {
    StringBuilder query = new StringBuilder();
    query.append("SELECT mr.code, mr.loginid, mr.create_dt,  mr.reserve_dt, mr.status, mr.description, mr.end_date,");
    query.append(" mr.adm_cancel_rs, mrd.amount, mcm.name,  mrs.status as status_name FROM mi_rsr as mr");
    query.append(" INNER JOIN mi_rsr_detail as mrd");
    query.append(" ON mr.code = mrd.code");
    query.append(" INNER JOIN mi_cafe_menu as mcm");
    query.append(" ON menucode = mcm.code");
    query.append(" INNER JOIN mi_rsr_status mrs");
    query.append(" ON mr.status = mrs.code");
    query.append(" ORDER BY create_dt");

    return jdbcTemplate.queryForList(query.toString());
  }

  public int updateStatus(int reservationCode, int reservStatus) {
    String query = "UPDATE mi_rsr SET status = ? WHERE code = ?";
    return jdbcTemplate.update(query, reservStatus, reservationCode);
  }

  public int createNotification(int reservationCode) {
    String query = "INSERT INTO mi_notify_log(rsr_code, confirm) VALUES(?, ?)";
    return jdbcTemplate.update(query, reservationCode, 0);
  }
}
