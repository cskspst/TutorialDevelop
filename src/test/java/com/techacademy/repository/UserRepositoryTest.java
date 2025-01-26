package com.techacademy.repository;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.techacademy.entity.User;

@SpringBootTest
@ExtendWith(SpringExtension.class)
class UserRepositoryTest {
    private final UserRepository userRepository;

    @Autowired
    UserRepositoryTest(UserRepository repository) {
        this.userRepository = repository;
    }

    @Test
    void testFindById() {
        User user = userRepository.findById(1).get();
        // [カリキュラムから]
        //  assertEquals : 2つの引数の値が等しいか否かを判定するメソッド
        //  ここではfindById(1)で検索して返された値が想定される値(id=1, name="キラメキ太郎")を判定する
        assertEquals(1, user.getId());
        assertEquals("キラメキ太郎", user.getName());
        // fail("まだ実装されていません");
    }

}
