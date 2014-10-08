package com.devuger.common.support.base;

import org.springframework.beans.factory.annotation.Autowired;

import com.devuger.common.repositories.AttachmentRepository;
import com.devuger.common.repositories.CommentRepository;
import com.devuger.common.repositories.DeviceRepository;
import com.devuger.common.repositories.FeedRepository;
import com.devuger.common.repositories.UserRepository;

public class BaseService extends BaseObject {

  @Autowired protected AttachmentRepository attachmentRepository;
  @Autowired protected DeviceRepository deviceRepository;
  @Autowired protected CommentRepository commentRepository;
  @Autowired protected FeedRepository feedRepository;
  @Autowired protected UserRepository userRepository;

}