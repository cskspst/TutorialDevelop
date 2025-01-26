package com.techacademy.controller;

// 開始:追加:Lesson 18 Chapter 9
import java.util.Set;
// 終了:追加:Lesson 18 Chapter 9
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
// 開始:追加:Lesson 18 Chapter 10
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
// 終了:追加:Lesson 18 Chapter 10
import org.springframework.web.bind.annotation.GetMapping;
// 開始:追加:Lesson 18 Chapter 7
import org.springframework.web.bind.annotation.ModelAttribute;
// 終了:追加:Lesson 18 Chapter 7
// 開始:追加:Lesson 18 Chapter 8
import org.springframework.web.bind.annotation.PathVariable;
// 終了:追加:Lesson 18 Chapter 8
// 開始:追加:Lesson 18 Chapter 7
import org.springframework.web.bind.annotation.PostMapping;
// 終了:追加:Lesson 18 Chapter 7
import org.springframework.web.bind.annotation.RequestMapping;
// 開始:追加:Lesson 18 Chapter 9
import org.springframework.web.bind.annotation.RequestParam;
// 終了:追加:Lesson 18 Chapter 9
// 開始:追加:Lesson 18 Chapter 7
import com.techacademy.entity.User;
// 終了:追加:Lesson 18 Chapter 7
import com.techacademy.service.UserService;

@Controller
@RequestMapping("user")
public class UserController {
    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    /** 一覧画面を表示 */
    @GetMapping("/list")
    public String GetList(Model model) {
        // 全件検索結果をModelに登録
        model.addAttribute("userlist", service.getUserList());
        // user/list.htmlに遷移
        return "user/list";
    }

    // 開始:追加:Lesson 18 Chapter 7
    /** User登録画面を表示 */
    @GetMapping("/register")
    public String getRegister(@ModelAttribute User user) {
    // [カリキュラムから]
    // @ModelAttributeアノテーションにより自動的にModelにインスタンスが登録される
    // アノテーションを付与せずに以下のように記述することと同じ
    // public String getRegister(model.addAttribute("user",user) {
        // User登録画面に遷移
        return "user/register";
    }

    /** User登録処理 */
    @PostMapping("/register")
    // 開始:変更:Lesson 18 Chapter 10
    public String postRegister(@Validated User user, BindingResult res, Model model) {
    // [カリキュラムから]
    // @Validatedアノテーションによりuserエンティティに基づいた入力チェックが行われる
    // 結果はBindingResult resに格納される
        if(res.hasErrors()) {
            // エラーあり
            return getRegister(user);
        }
    //    public String postRegister(User user) {
    //    // [カリキュラムから]
    //    // 引数にエンティティを指定することでHTMLのformの項目値が引数のuserの
    //    // 各属性としてセットされた状態で受け取ることができる
    // 終了:変更:Lesson 18 Chapter 10
        // User登録
        service.saveUser(user);
        // 一覧画面にリダイレクト
        return "redirect:/user/list";
    }
    // 終了:追加:Lesson 18 Chapter 7

    // 開始:追加:Lesson 18 Chapter 8
    /** User更新画面を表示 */
    @GetMapping("/update/{id}/")
    public String getUser(@PathVariable("id") Integer id, Model model) {
        // Modelに登録
        model.addAttribute("user",service.getUser(id));
        // User更新画面に遷移
        return "user/update";
    }

    /** User更新処理 */
    @PostMapping("/update/{id}/")
    public String postUser(User user) {
        // User登録
        service.saveUser(user);
        // 一覧画面にリダイレクト
        return "redirect:/user/list";
    }
    // 終了:追加:Lesson 18 Chapter 8

    // 開始:追加:Lesson 18 Chapter 9
    /** User削除処理 */
    @PostMapping(path="list", params="deleteRun")
    public String deleteRun(@RequestParam(name="idck") Set<Integer> idck, Model model) {
        // Userを一括削除
        service.deleteUser(idck);
        // 一覧画面にリダイレクト
        return "redirect:/user/list";
    }
    // 終了:追加:Lesson 18 Chapter 9
}
