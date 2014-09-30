package com.devuger.common.support.base;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional(rollbackFor=RuntimeException.class, propagation=Propagation.REQUIRED)
public class BaseController extends BaseService {
}
