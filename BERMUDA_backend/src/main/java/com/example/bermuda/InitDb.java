package com.example.bermuda;

import com.example.bermuda.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;

@Component
@RequiredArgsConstructor
public class InitDb {

//    private final InitService initService;
//
//
//    @PostConstruct
//    public void init() {
//        initService.dbInit1();
//    }
//
//    @Component
//    @Transactional
//    @RequiredArgsConstructor
//    static class InitService {
//
//        private final EntityManager em;
//        private final PasswordEncoder passwordEncoder;
//
//        public void dbInit1(){
//            User yh = User.builder()
//                    .nickName("dydgh142")
//                    .emailAuth(true)
//                    .userId("dydgh142")
//                    .userPw(passwordEncoder.encode("dydgh142"))
//                    .build();
//
//            User hg = User.builder()
//                    .nickName("nutalrnond98")
//                    .emailAuth(true)
//                    .userId("yongdori98")
//                    .userPw(passwordEncoder.encode("wjdgusrn98"))
//                    .build();
//
//            User jh = User.builder()
//                    .nickName("wogns98")
//                    .emailAuth(true)
//                    .userId("wognsdl98")
//                    .userPw(passwordEncoder.encode("wogns98"))
//                    .build();
//
//            em.persist(yh);
//            em.persist(hg);
//            em.persist(jh);
//
//        }
//    }
}
