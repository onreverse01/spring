package com.kh.spring.demo.model.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kh.spring.demo.model.service.DemoService;
import com.kh.spring.demo.model.vo.Dev;

/**
 * 사용자 요청 하나당 이를 처리하는 Controller 메소드(Handler)가 하나씩 존재한다.
 *
 */
@Repository
public class DemoDaoImpl implements DemoDao {

	@Autowired
	private SqlSessionTemplate session;
	
	@Override
	public int insertDev(Dev dev) {
		return session.insert("demo.insertDev", dev);
	}

	@Override
	public List<Dev> selectDevList() {
		return session.selectList("demo.selectDevList");
	}

	@Override
	public Dev selectOneDev(int no) {
		return session.selectOne("demo.selectOneDev", no);
	}

	@Override
	public int updateDev(Dev dev) {
		return session.update("demo.updateDev", dev);
	}

	@Override
	public int deleteDev(int no) {
		return session.delete("demo.deleteDev", no);
	}
	
	
}
