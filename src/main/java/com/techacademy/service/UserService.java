package com.techacademy.service;

import java.util.List;
// 開始:追加:Lesson 18 Chapter 9
import java.util.Set;
// 終了:追加:Lesson 18 Chapter 9
import org.springframework.stereotype.Service;
// 開始:追加:Lesson 18 Chapter 7
import org.springframework.transaction.annotation.Transactional;
// 終了:追加:Lesson 18 Chapter 7

import com.techacademy.entity.User;
import com.techacademy.repository.UserRepository;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository repository) {
        this.userRepository = repository;
    }

    /** 全件を検索して返す */
    public List<User> getUserList(){
        // リポジトリのfindAllメソッドを呼び出す
        return userRepository.findAll();
    }

    // 開始:追加:Lesson 18 Chapter 8
    /** Userを1件検索して返す */
    public User getUser(Integer id) {
        return userRepository.findById(id).get();
    }
    // 終了:追加:Lesson 18 Chapter 8

    // 開始:追加:Lesson 18 Chapter 7
    /** Userの登録を行う */
    @Transactional
    public User saveUser(User user) {
        return userRepository.save(user);
    }
    // 終了:追加:Lesson 18 Chapter 7

    // 開始:追加:Lesson 18 Chapter 9
    @Transactional
    public void deleteUser(Set<Integer> idck) {
    // [カリキュラムから]
    // 削除するUserは複数選択できるためそのコレクションSet idckを
    // for eachで１件ずつ取り出してdeleteByIdメソッドで削除する
        for(Integer id : idck) {
            userRepository.deleteById(id);
        }
    }
    // 終了:追加:Lesson 18 Chapter 9

}
