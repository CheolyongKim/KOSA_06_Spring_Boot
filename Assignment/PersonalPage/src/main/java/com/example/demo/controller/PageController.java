package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

    // 1. 메인 페이지 (브라우저에 주소만 입력하고 들어왔을 때, 기본적으로 자기소개 페이지로 이동)
    @GetMapping("/")
    public String index() {
        return "redirect:/about"; // localhost:8080/ 으로 접속하면 /about 으로 리다이렉트합니다.
    }

    // 2. 자기소개 페이지 요청 처리
    @GetMapping("/about")
    public String aboutPage() {
        // src/main/resources/templates/about.html 파일을 찾아 화면에 띄웁니다.
        return "about"; 
    }

    // 3. 경력 페이지 요청 처리
    @GetMapping("/career")
    public String careerPage() {
        // src/main/resources/templates/career.html 파일을 찾아 화면에 띄웁니다.
        return "career";
    }

    // 4. 사는 곳 페이지 요청 처리
    @GetMapping("/location")
    public String locationPage() {
        // src/main/resources/templates/location.html 파일을 찾아 화면에 띄웁니다.
        return "location";
    }
}