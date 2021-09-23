package vn.techmaster.productmanager.controller;

import vn.techmaster.productmanager.exception.*;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static final String ERROR_500 = "error/500";
    private static final String ERROR_404 = "error/404";
    private static final String ERROR_400 = "error/400";
    private static final String MESSAGE = "message";

    @ExceptionHandler(BadRequestException.class)
    public String hanlderBadRequestException(BadRequestException ex, WebRequest webRequest, Model model) {
        model.addAttribute(MESSAGE, "BAD request");
        return ERROR_400;
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public String hanlderResourceNotFoundException(ResourceNotFoundException ex, WebRequest webRequest, Model model) {
        model.addAttribute(MESSAGE, "Không tìm thấy tài nguyên yêu cầu");
        return ERROR_404;
    }

    @ExceptionHandler(Exception.class)
    public String hanlderException(Exception ex, WebRequest webRequest, Model model) {
        model.addAttribute(MESSAGE, "Lỗi ko xác định");
        return ERROR_500;
    }
}
