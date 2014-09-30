package com.devuger.common.validation;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.devuger.common.entities.User;
import com.devuger.common.support.base.BaseService;
import com.devuger.util.HelloCheckUtil;

public class SignupValidator extends BaseService implements Validator {

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
    String username = user.getUsername();
    String hashedPasswrd = user.getHashedPassword();
    String hashedPasswordConfirm = user.getHashedPasswordConfirm();
    boolean serviceTerms = user.isServiceTerms();
    boolean userInfoTerms = user.isUserInfoTerms();
		
    if (email == null || !HelloCheckUtil.isValidEmail(email))
      errors.rejectValue("email", null, "이메일 주소를 입력해주세요.");

    if (username == null || username.length() < 1)
      errors.rejectValue("username", null, "사용자명을 2글자이상 입력해주세요.");

    if (hashedPasswrd == null || hashedPasswrd.length() < 5)
      errors.rejectValue("hashedPassword", null, "비밀번호를 6자리이상 입력해주세요.");

    if (hashedPasswordConfirm == null || hashedPasswordConfirm.length() < 5)
      errors.rejectValue("hashedPasswordConfirm", null, "비밀번호를 6자리이상 입력해주세요.");

    if (serviceTerms == false)
      errors.rejectValue("serviceTerms", null, "서비스이용약관에 대해 동의해주세요.");

    if (userInfoTerms == false)
      errors.rejectValue("userInfoTerms", null, "개인정보취급방침에 대해 동의해주세요.");
          
	}

}
