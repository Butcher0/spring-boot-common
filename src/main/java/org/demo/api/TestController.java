package org.demo.api;

import org.demo.service.UserService;
import org.demo.vo.PageVo;
import org.demo.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api")
public class TestController {
	
	private @Autowired StringRedisTemplate stringRedisTemplate;
	private @Autowired UserService userService;
	
    
	@RequestMapping(value = "/audit/date", method = RequestMethod.GET)
	public void testAuditDateUpdate() {
		userService.testUserCreate();
		userService.testUserUpdate();
	}
	
	//http://localhost:8080/api/user/query?&page=0&size=5&sort=username,desc
	@RequestMapping(value = "/user/query", method = RequestMethod.GET)
	public PageVo<UserVo> testQueryPage(Pageable page) {
		return userService.queryPage(page);
	}
	
	@RequestMapping(value = "/cache", method = RequestMethod.GET)
	public boolean testCache() {
		stringRedisTemplate.opsForValue().set("aidan", "A");
		return stringRedisTemplate.opsForValue().get("aidan").equals("A");
	}
}
