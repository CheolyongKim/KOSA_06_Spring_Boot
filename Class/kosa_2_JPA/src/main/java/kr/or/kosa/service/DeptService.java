package kr.or.kosa.service;

import java.util.List;

import org.springframework.stereotype.Service;

import kr.or.kosa.model.Dept;
import kr.or.kosa.repository.DeptRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DeptService {
	private final DeptRepository deptRepository;
	
	public List<Dept> findAll(){
		return deptRepository.findAll();
	}
	
	public Dept findById(int deptno) {
		return deptRepository.findById(deptno).orElse(null);
	}
	
	public void save(Dept dept) {
		deptRepository.save(dept);
	}
	
	public void delete(int deptno) {
		deptRepository.deleteById(deptno);
	}
}
