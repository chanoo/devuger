package com.devuger.common.support.code;

public enum UserStatusEnum {
	NORMAL("정상"),
	BLOCKED("차단"),
	CLOSED("탈퇴");

	private String desc;
	private String name;

	private UserStatusEnum(String name) {
		this.name = name;
		this.desc = name;
	}

	public String getDesc() {
		return desc;
	}

	@Override
	public String toString() {
		return name;
	}
}
