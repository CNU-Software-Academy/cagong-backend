package kr.ac.cnu.swacademy.cagong.controller;

import kr.ac.cnu.swacademy.cagong.dto.NoticeSaveRequestDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class NoticeController {

    @GetMapping("/notice/save")
    public String postSave(Model model) {
        model.addAttribute("saveForm", new NoticeSaveRequestDto());
        return "notice/saveForm";
    }
}
