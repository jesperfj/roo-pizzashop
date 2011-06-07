package com.example.roo.pizzashop.web;

import com.example.roo.pizzashop.domain.Topping;
import org.springframework.roo.addon.web.mvc.controller.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RooWebScaffold(path = "toppings", formBackingObject = Topping.class)
@RequestMapping("/toppings")
@Controller
public class ToppingController {
}
