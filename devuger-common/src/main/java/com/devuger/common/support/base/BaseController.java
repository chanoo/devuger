package com.devuger.common.support.base;

import org.springframework.beans.factory.annotation.Autowired;

import com.devuger.common.services.AttachmentService;
import com.devuger.common.services.CommentService;
import com.devuger.common.services.DeviceService;
import com.devuger.common.services.FeedService;
import com.devuger.common.services.LikeService;
import com.devuger.common.services.UserService;

//@Transactional(rollbackFor=RuntimeException.class, propagation=Propagation.REQUIRED)
public class BaseController extends BaseService {
  
  @Autowired protected AttachmentService attachmentService;
  @Autowired protected CommentService commentService;
  @Autowired protected DeviceService deviceService;
  @Autowired protected FeedService feedService;
  @Autowired protected LikeService likeService;
  @Autowired protected UserService userService;
}
