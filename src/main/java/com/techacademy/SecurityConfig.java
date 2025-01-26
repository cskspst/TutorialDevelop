package com.techacademy;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
// [カリキュラムから]設定用のクラスを示すアノテーション
public class SecurityConfig {
    /** 認証・認可設定 */
    @Bean
    // [カリキュラムから]DIコンテナの登録対象にするアノテーション
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.formLogin(login -> login       // ログインに関する設定
            .loginProcessingUrl("/login")       // ユーザー名・パスワードの送信先
            .loginPage("/login")                // ログイン画面
            .defaultSuccessUrl("/user/list")    // ログイン成功時のリダイレクト先
            .failureUrl("/login?error")         // ログイン失敗時のリダイレクト先
            .permitAll()                        // ログイン画面は未ログインでアクセス可とする
        ).logout(logout -> logout           // ログアウトに関する設定
            .logoutSuccessUrl("/login")         // ログアウト後のリダイレクト先
        ).authorizeHttpRequests(auth -> auth//認可(リソース別のアクセス制御)に関する設定
            .requestMatchers(PathRequest.toStaticResources().atCommonLocations())
                .permitAll()                    // static配下のリソース(=cssなど)は未ログインでアクセス可
            .anyRequest().authenticated()       // それ以外はログイン必要
        );
        return http.build();
    }

    /** ハッシュ化したパスワードの比較に使用する */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
