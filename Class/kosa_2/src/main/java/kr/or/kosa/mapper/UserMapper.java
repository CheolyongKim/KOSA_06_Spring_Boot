package kr.or.kosa.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.or.kosa.model.User;

/*
인터페이스가 많아서 매번 설정하기 힘들면
@Mapper 


@SpringBootApplication
@MapperScan("kr.or.kosa.mapper")  걸어놓으면  mapper 안에 인터페이스 mapper 로 사용
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }
}

*/

@Mapper
public interface UserMapper {
  
	//Mapper.xml 
	//1. namespace 동일  namespace="kr.or.kosa.mapper.UserMapper"
	//2. 함수이름과 id 동일 
	
	List<User> selectAll();
	User selectById(Long id);
	void insert(User user);
	void update(User user);
	void delete(Long id);
}
