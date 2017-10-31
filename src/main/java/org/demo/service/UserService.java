package org.demo.service;

import org.demo.vo.PageVo;
import org.demo.vo.UserVo;
import org.springframework.data.domain.Pageable;

public interface UserService {

	void testUserCreate();
	
	void testUserUpdate();
	
	public PageVo<UserVo> queryPage(Pageable pageable);
}
