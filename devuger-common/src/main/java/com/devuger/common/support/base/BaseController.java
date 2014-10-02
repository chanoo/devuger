package com.devuger.common.support.base;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.devuger.common.services.FeedService;
import com.devuger.common.services.UserService;

@Transactional(rollbackFor=RuntimeException.class, propagation=Propagation.REQUIRED)
public class BaseController extends BaseService {
  
  @Autowired protected FeedService feedService;
  @Autowired protected UserService userService;
}
