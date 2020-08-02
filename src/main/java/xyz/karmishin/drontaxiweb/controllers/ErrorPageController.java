package xyz.karmishin.drontaxiweb.controllers;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

public class ErrorPageController implements ErrorController {
    private static final String PATH = "/error";

    @Override
    public String getErrorPath() {
        return PATH;
    }

    @RequestMapping("/error")
    public String error(HttpServletRequest request, Model model) {
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

        if (status != null) {
            Integer statusCode = Integer.valueOf(status.toString());
            model.addAttribute("status", status);
        }
        return "error";

    }
}
