package org.demo.service.impl;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.demo.entity.User;
import org.demo.repository.UserRepository;
import org.demo.service.UserService;
import org.demo.vo.PageVo;
import org.demo.vo.UserVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements UserService {

	private @Autowired UserRepository userRepository;
	
	@Override
	public void testUserCreate() {
		User user = new User();
		user.setUsername(new Random().nextInt(100) + "");
		user.setPassword("123");
		userRepository.save(user);
	}

	public void testUserUpdate() {
		User user = userRepository.findOne(2L);
		user.setPassword(new Random().nextInt(100)+"");
		userRepository.save(user);
	}
	
	public PageVo<UserVo> queryPage(Pageable pageable) {
		PageVo<UserVo> pageVo = new PageVo<>();
		List<UserVo> userVos = new ArrayList<>();
		
		Page<User> users = userRepository.findAll(pageable);
		for(User user : users) {
			UserVo userVo = new UserVo();
			BeanUtils.copyProperties(user, userVo);
			userVos.add(userVo);
		}
		pageVo.setTotalAmount(users.getTotalElements());
		pageVo.setContent(userVos);
		pageVo.setTotalPages(users.getTotalPages());
		pageVo.setCurrentPage(pageable.getPageNumber());
		return pageVo;
	}
}
