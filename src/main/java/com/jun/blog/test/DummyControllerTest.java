package com.jun.blog.test;

import com.jun.blog.model.RoleType;
import com.jun.blog.model.User;
import com.jun.blog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.function.Supplier;

@RestController
public class DummyControllerTest {

    @Autowired // DI
    private UserRepository userRepository;

    @GetMapping("/dummy/user")
    public List<User> list(){
        return userRepository.findAll();
    }

    //http://localhost:8000/blog/dummy/user/3(요청)
    //{id} 주소로 파라미터를 전달 받을 수 있음.
    @GetMapping("/dummy/user/{id}")
    public User detail(@PathVariable int id){

//        람다식
//        User user = userRepository.findById(id).orElseThrow(()->{
//            return new IllegalArgumentException("해당 사용자는 없습니다");
//        });

        User user = userRepository.findById(id).orElseThrow(new Supplier<IllegalArgumentException>() {

            @Override
            public IllegalArgumentException get() {
                return new IllegalArgumentException("해당 유저는 없습니다. id : "+id);
            }
        });
        return user;
    }

    //http://localhost:8000/blog/dummy/join(요청)
    //http의 body에 username, password, email 데이터를 가지고(요청)
    @PostMapping("/dummy/join")
    public String join(User user){
        System.out.println("id : "+user.getId());
        System.out.println("username : "+user.getUsername());
        System.out.println("password : "+user.getPassword());
        System.out.println("email : "+user.getEmail());
        System.out.println("role : "+user.getRole());
        System.out.println("createDate : "+user.getCreateDate());

        user.setRole(RoleType.USER);
        userRepository.save(user);
        return "회원가입이 완료되었습니다";
    }

}
