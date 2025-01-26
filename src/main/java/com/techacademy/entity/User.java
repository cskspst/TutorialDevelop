package com.techacademy.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
// 開始:追加:Lesson 18 Chapter 10
// 開始:追加:Lesson 18 Chapter 11
import jakarta.persistence.OneToOne;
import jakarta.persistence.PreRemove;
// 終了:追加:Lesson 18 Chapter 11
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
// 終了:追加:Lesson 18 Chapter 10
// 開始:追加:Lesson 18 Chapter 11
import org.springframework.transaction.annotation.Transactional;
// 終了:追加:Lesson 18 Chapter 11

import lombok.Data;

@Data
@Entity
@Table(name = "user")
public class User {

    /** 性別用の列挙型 */
    public static enum Gender {
        男性, 女性
    }

    /** 主キー。自動生成 */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /** 名前。20桁。null不許可 */
    @Column(length = 20, nullable = false)
    // 開始:追加:Lesson 18 Chapter 10
    @NotEmpty
    @Length(max=20)
    // 終了:追加:Lesson 18 Chapter 10
    private String name;

    /** 性別。2桁。列挙型 */
    @Column(length = 2)
    @Enumerated(EnumType.STRING)
    // 開始:追加:Lesson 18 Chapter 10
    @NotNull
    // 終了:追加:Lesson 18 Chapter 10
    private Gender gender;

    /** 年齢 */
    // 開始:追加:Lesson 18 Chapter 10
    @Min(0)
    @Max(120)
    // 終了:追加:Lesson 18 Chapter 10
    private Integer age;

    /** メールアドレス。50桁。null許可 */
    @Column(length = 50)
    // 開始:追加:Lesson 18 Chapter 10
    @Email
    @Length(max=50)
    // 終了:追加:Lesson 18 Chapter 10
    private String email;

    // 開始:追加:Lesson 18 Chapter 11
    @OneToOne(mappedBy="user")
    private Authentication authentication;

    /** レコードが削除される前に行う処理 */
    @PreRemove
    @Transactional
    private void preRemove() {
        // 認証エンティティからuserを切り離す
        // [カリキュラムから]
        //  認証エンティティ(Authentication.java)でリレーションが定義されているため、
        //  テーブルに外部キー制約が設定される
        //  このためUserレコードを削除しようとするとエラーになる
        //  これを回避する前に削除する前(@PrRemoveアノテーション)に認証エンティティから
        //  userを切り離している
        if (authentication != null) {
            authentication.setUser(null);
        }
    }
    // 終了:追加:Lesson 18 Chapter 11

}
