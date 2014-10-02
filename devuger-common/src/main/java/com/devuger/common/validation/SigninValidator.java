package com.devuger.common.validation;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.devuger.common.entities.User;
import com.devuger.common.support.base.BaseService;
import com.devuger.util.HelloCheckUtil;

public class SigninValidator extends BaseService implements Validator {

  @Override
  public boolean supports(Class<?> clazz) {
    // TODO Auto-generated method stub
    return User.class.isAssignableFrom(clazz);
  }

  @Override
  public void validate(Object target, Errors errors) {
    // TODO Auto-generated method stub

    User user = (User) target;

    String email = user.getEmail();
    String hashedPasswrd = user.getHashedPassword();

    if (email == null || !HelloCheckUtil.isValidEmail(email))
      errors.rejectValue("email", null, null, "이메일 주소를 입력해주세요.");

    if (hashedPasswrd == null || hashedPasswrd.length() < 5)
      errors.rejectValue("hashedPassword", null, null, "비밀번호를 6자리이상 입력해주세요.");
  }

}
