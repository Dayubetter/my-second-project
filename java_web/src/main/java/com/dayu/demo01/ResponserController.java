package com.dayu.demo01;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class ResponserController {

    /**
     * 方式一：HttpServletResponse  设置响应数据
     * @param response
     * @throws IOException
     */
    @RequestMapping("/response")
    public void response(HttpServletResponse  response) throws IOException {
        // 1.设置响应状态码
        response.setStatus(HttpServletResponse.SC_OK);
        // 2.设置响应头
        response.setHeader("name","dayu");
        response.setHeader("Content-Type","text/html;charset=utf-8");
        // 3. 设置响应体
        response.getWriter().write("<h1>hello world</h1>");
    }

    /**
     * 方式二：ResponseEntity  spring中提供的方式
     * @return
     */
    @RequestMapping("/response2")
    public ResponseEntity<String> response2(){
        return ResponseEntity
                .status(HttpServletResponse.SC_OK) // 状态码
                .header("name","dayu")  // 响应头
                .body("<h1>hello world</h1>"); // 响应体
    }
}
