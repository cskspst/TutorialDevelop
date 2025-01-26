package com.techacademy.service;

import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.techacademy.entity.Authentication;
import com.techacademy.repository.AuthenticationRepository;

// [カリキュラムから]
// このクラスは認証処理に使用するユーザ情報を作成する
// 大まかな流れ;
// usernameをもとに認証リポジトリからエンティティを検索する
// 対象のエンティティが存在しない場合は例外を投げる
// 存在すれば認証エンティティに含まれるUserエンティティをもとにUserDetailオブジェクトを作成して返す
// なお返す先はSpring Secutiryである
@Service
public class UserDetailService implements UserDetailsService {
    private final AuthenticationRepository authenticationRepository;

    public UserDetailService(AuthenticationRepository repository) {
        this.authenticationRepository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Authentication> authentication = authenticationRepository.findById(username);

        if(!authentication.isPresent()) {
            throw new UsernameNotFoundException("Exception:Username not found.");
        }
        return new UserDetail(authentication.get().getUser());
    }
}
