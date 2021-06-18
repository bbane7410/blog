package com.jun.blog.controller.api;

import com.jun.blog.dto.ResponseDto;
import com.jun.blog.model.RoleType;
import com.jun.blog.model.User;
import com.jun.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
public class UserApiController {

    @Autowired
    private UserService userService;

    @Autowired
    private HttpSession session;

    @PostMapping("/api/user")
    public ResponseDto<Integer> save(@RequestBody User user){//username,password,email
        System.out.println("UserApiController : save 호출됨");
        user.setRole(RoleType.USER);
        userService.회원가입(user);
        return new ResponseDto<Integer>(HttpStatus.OK.value(),1);
        //자바오브젝트를 JSON으로 변환하여 리턴
    }

    //
    @PostMapping("/api/user/login")
//    public ResponseDto<Integer> login(@RequestBody User user, HttpSession session){
    public ResponseDto<Integer> login(@RequestBody User user){
        System.out.println("UserApiController : login 호출됨");
        User principal = userService.로그인(user); //principal(접근주체)
        if (principal !=null){
            session.setAttribute("principal",principal);
        }
        return new ResponseDto<Integer>(HttpStatus.OK.value(),1);
    }
}
