package com.example.loginsystem;


import com.example.loginsystem.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/sign_up")
    public String registerForm(Model model) {
        model.addAttribute("user", new User());
        Iterable<User> users = userRepository.findAll();
        model.addAttribute("users",users);
        return "sign_up";
    }

    @GetMapping("/users")
    public String registerxForm(Model model) {
        model.addAttribute("user", new User());
        Iterable<User> users = userRepository.findAll();
        model.addAttribute("users",users);
        return "users";
    }

    @GetMapping("/sign_in")
    public String signin(){
        return "sign_in";
    }


    @PostMapping("/sign_up")
    public @ResponseBody RedirectView registerSubmit(
            @RequestParam String username, @RequestParam String email, @RequestParam String password, @RequestParam String confirm_password) {
        User u = new User();
        u.setUsername(username);
        u.setEmail(email);
        u.setPassword(password);
        u.setConfirm_password(confirm_password);
        if(password.equals(confirm_password)){
        userRepository.save(u);}

        return new RedirectView("/sign_up");
    }

    @GetMapping("users/{id}/delete")
    public @ResponseBody RedirectView deleteUser(@PathVariable("id") Integer id){
        userRepository.deleteById(id);
        return new RedirectView("/users");
    }


    @GetMapping("/users/{id}/edit")
    public String edit(@PathVariable("id") Integer id,Model m){
        User u = userRepository.findById(id).get();
        m.addAttribute("user",u);
        return "edit";
    }


    @PostMapping("/users/{id}/update")
    public @ResponseBody RedirectView updateUser(@PathVariable("id") Integer id, @RequestParam String username,@RequestParam String email,@RequestParam String password,@RequestParam String confirm_password){
        User u = userRepository.findById(id).get();
        u.setId(id);
        u.setUsername(username);
        u.setEmail(email);
        u.setPassword(password);
        u.setPassword(confirm_password);
        if(password.equals(confirm_password)){
            userRepository.save(u);}

        return new RedirectView("/users");
    }

    /*@GetMapping("/login")
    public String subm(){
        return "hello";
    }*/


}
