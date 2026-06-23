package kr.or.kosa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.or.kosa.mapper.UserMapper;
import kr.or.kosa.model.User;


@Service
public class UserService {
	
	//UserMapp 의존
	
	@Autowired
	private UserMapper userMapper; //setter 주입 , constructor 주입 , member field 도 주입 가능
	
	public List<User> getAllUsers(){
		return userMapper.selectAll(); //@Mapper 연결 (주입)
	}
	public User getUserById(long id) {
		return userMapper.selectById(id);
	}
	
	public void createUser(User user) {
		userMapper.insert(user);
	}
	
	public void updateUser(User user) {
		userMapper.update(user);
	}
	
	public void deleteUser(long id) {
		userMapper.delete(id);
	}
}
