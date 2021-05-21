package com.kh.spring.demo.model.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kh.spring.demo.controller.DemoController;
import com.kh.spring.demo.model.dao.DemoDao;
import com.kh.spring.demo.model.vo.Dev;

/**
 * 사용자 요청 하나당 이를 처리하는 Controller 메소드(Handler)가 하나씩 존재한다.
 *
 */
@Service
public class DemoServiceImpl implements DemoService {

	@Autowired
	private DemoDao demoDao;

	/**
	 * Spring AOP기술을 이용해서 Session객체 관리(시작 ~ 끝), 트랜잭션 처리
	 */
	@Override
	public int insertDev(Dev dev) {
		// 1. SqlSession객체 생성
		// 2. dao업무요청
		// 3. transaction처리
		// 4. SqlSession반납
		return demoDao.insertDev(dev);
	}

	@Override
	public List<Dev> selectDevList() {
		return demoDao.selectDevList();
	}

	@Override
	public Dev selectOneDev(int no) {
		return demoDao.selectOneDev(no);
	}

	@Override
	public int updateDev(Dev dev) {
		return demoDao.updateDev(dev);
	}

	@Override
	public int deleteDev(int no) {
		return demoDao.deleteDev(no);
	}
	
	
	
	
}
