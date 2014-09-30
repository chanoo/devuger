package com.devuger.util;

import java.util.HashMap;

import org.springframework.util.Assert;

import com.allat.util.AllatAPI;

public class TextMessageUtil {

	private static String RET_CD_SUCCESS = "0000";
	
	public static String send(String phoneNo, String message)
	{
		Assert.hasText(phoneNo, "수신인 번호를 입력 해주세요.");
		Assert.hasText(message, "문자 메시지를 입력 해주세요.");
		
		String sMemberId	= "8hlabs";
		String sMemberKey	= "c57b1b69cdffad7c13aa86e23e0c72eb";
		String sMsgCd		= "SMS_1010";
		String sSendNo		= "070-7583-3833";
		String sRecvNo		= phoneNo;
		String sContent		= message;
		String sCutYn		= "Y";
		String sTestYn		= "Y";

		// InputParam Setting
		HashMap<String, String> hashMap = new HashMap<String, String>();
		hashMap.put("member_id", sMemberId);
		hashMap.put("member_key", sMemberKey);
		hashMap.put("msg_cd", sMsgCd);
		hashMap.put("send_no", sSendNo);
		hashMap.put("recv_no", sRecvNo);
		hashMap.put("content", sContent);
		hashMap.put("cut_yn", sCutYn);
		hashMap.put("test_yn", sTestYn);

		// 올앳과 통신 후 결과값 받기
		AllatAPI api = new AllatAPI();
		HashMap<String, String> OutputParam = api.reqCall(hashMap);

		// 결과코드
		String sRetCd = OutputParam.get("ret_cd");
		// 결과메시지
		String sRetMsg = OutputParam.get("ret_msg");

		// 결과에 대한 처리
		Assert.isTrue(sRetCd.equals(RET_CD_SUCCESS), sRetMsg);
		
		return sRetMsg;
	}
}
