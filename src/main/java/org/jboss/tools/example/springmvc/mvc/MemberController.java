package org.jboss.tools.example.springmvc.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/")
public class MemberController {

    @RequestMapping(method = RequestMethod.GET)
    public String displaySortedMembers() {
//        model.addAttribute("newMember", new Member());
//        model.addAttribute("members", memberDao.findAllOrderedByName());
        return "index";
    }


}
