package com.example.myrest.validator;

import com.example.myrest.model.TB_Board;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.thymeleaf.util.StringUtils;

@Component
public class BoardValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz){
        return TB_Board.class.equals(clazz);
    }

    @Override
    public void validate(Object obj, Errors errors){
        TB_Board b= (TB_Board) obj;
        if(StringUtils.isEmpty(b.getContent())){
            errors.rejectValue("content", "key","내용을 입력하세요");
        }
    }

}
