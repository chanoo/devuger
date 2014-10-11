package com.devuger.common.services;

import java.util.Date;

import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.devuger.common.entities.User;
import com.devuger.common.support.base.BaseService;

@Service
public class UserService extends BaseService {
 
  /**
   * 사용자 로그인
   * 
   * @param email 이메일
   * @param hashedPasswrd 비밀번호
   * @return 사용자정보
   */
  public User signin(String email, String hashedPasswrd, String ip) {    

    Assert.hasText(email, "이메일주소를 입력 해 주십시오.");
    Assert.hasText(hashedPasswrd, "비밀번호를 입력 해 주십시오.");
    
    User user = userRepository.findByEmail(email);
    Assert.notNull(user, "이메일주소 또는 비밀번호가 틀렸습니다.");
    hashedPasswrd = passwordEncoder.encodePassword(hashedPasswrd, user.getId().toString());
    this.getLogger().debug("hashedPasswrd : " + hashedPasswrd);
    Assert.isTrue(hashedPasswrd.equals(user.getHashedPassword()), "이메일주소 또는 비밀번호가 틀렸습니다.");

    // 접속 시간, 아이피 업데이트
    user.setLastSigninDate(new Date());
    user.setLastSigninIp(ip);
    return userRepository.save(user);
  }

  /**
   * 사용자 가입
   * 
   * @param user 사용자 정보
   * @return 사용자 정보
   */
  public User signup(User user) {
    // TODO Auto-generated method stub
    Assert.notNull(user, "가입 정보가 없습니다.");
    
    return userRepository.save(user);
  }

  /**
   * 회원정보 가져오기
   * 
   * @param id 회원 PK
   * @return
   */
  public User get(Long id) {
    // TODO Auto-generated method stub
    Assert.notNull(id, "사용자를 선택 해주세요.");
    return userRepository.findOne(id);
  }
}
