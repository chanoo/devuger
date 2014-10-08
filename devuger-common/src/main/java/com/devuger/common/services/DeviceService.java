package com.devuger.common.services;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.devuger.common.entities.Device;
import com.devuger.common.support.base.BaseService;
import com.devuger.common.support.code.DeviceOs;
import com.devuger.common.support.util.PushUtil;
import com.google.android.gcm.server.Constants;
import com.google.android.gcm.server.Message;
import com.google.android.gcm.server.Result;
import com.google.android.gcm.server.Sender;
import com.notnoop.apns.APNS;
import com.notnoop.apns.ApnsDelegate;
import com.notnoop.apns.ApnsNotification;
import com.notnoop.apns.ApnsService;
import com.notnoop.apns.DeliveryError;

@Service
public class DeviceService extends BaseService {

  private final static String CERT_PASSWORD = "bbuzzart2014";
  private final static String API_KEY = "AIzaSyCC2phv_qCWIr5yzYS6nqupPs5ZrQoB48w";

  public Device add(Device device) {

    Assert.notNull(device, "OS를 선택 해주세요.");
    if (device.getOs() == DeviceOs.IPHONE) {
      Assert.hasText(device.getToken(), "아이폰 Token이 필요합니다.");
    } else if (device.getOs() == DeviceOs.ANDROID) {
      Assert.hasText(device.getToken(), "안드로이드 Registration 아이디가 필요합니다.");
    }

    Date date = new Date();

    Device existDevice = deviceRepository.findByCreatedByAndOs(device.getCreatedBy(), device.getOs());

    if (existDevice == null) {
      device.setCreatedOn(date);
      return deviceRepository.save(device);
    }

    return existDevice;
  }

  public void push(Device device) throws IOException {
    Assert.notNull(device, "푸시 정보가 업습니다.");

    String token = device.getToken();
    String message = device.getMessage();

    Assert.hasText(token, "디바이스토큰이 없습니다.");
    Assert.hasText(message, "전달할 메시지를 입력 해주세요.");

    switch (device.getOs()) {
    case IPHONE:

      final ApnsDelegate delegate = new ApnsDelegate() {

        @Override
        public void notificationsResent(int resendCount) {
          // TODO Auto-generated method stub
          getLogger().info("notificationResent " + resendCount);
        }

        @Override
        public void messageSent(ApnsNotification message, boolean resent) {
          // TODO Auto-generated method stub
          getLogger().info("Sent message " + message + ", Resent: " + resent);
        }

        @Override
        public void messageSendFailed(ApnsNotification message, Throwable e) {
          // TODO Auto-generated method stub
          getLogger().error("Failed message " + message);
        }

        @Override
        public void connectionClosed(DeliveryError e, int messageIdentifier) {
          // TODO Auto-generated method stub
          getLogger().info("Closed connection: " + messageIdentifier + ", deliveryError " + e.toString());
        }

        @Override
        public void cacheLengthExceeded(int newCacheLength) {
          // TODO Auto-generated method stub
          getLogger().info("cacheLengthExceeded " + newCacheLength);
        }
      };
      
      InputStream certStream = PushUtil.class.getClassLoader().getResourceAsStream("certificate.p12");

      final ApnsService apnsService = APNS.newService().withAppleDestination(true).withCert(certStream, CERT_PASSWORD).withDelegate(delegate).withSandboxDestination().build();
      final String payload = APNS.newPayload().alertBody(message).customField("alert", "good").sound("Printer.aiff").build();
      apnsService.start();
      final ApnsNotification goodMsg = apnsService.push(token, payload);
      this.getLogger().info("Message id: " + goodMsg.getIdentifier());

      final Map<String, Date> inactiveDevices = apnsService.getInactiveDevices();
      for (final Entry<String, Date> ent : inactiveDevices.entrySet()) {
        this.getLogger().error("Inactive " + ent.getKey() + " at date " + ent.getValue());
      }

      break;

    default:

      Sender sender = new Sender(API_KEY);
      Message msg = new Message.Builder().addData("message", message).build();
      Result result = sender.send(msg, token, 5);
      if (result.getMessageId() != null) {

        this.getLogger().debug("푸시 성공!!!");

      } else {

        String error = result.getErrorCodeName(); // 에러 내용 받기
        Assert.isTrue(!Constants.ERROR_INTERNAL_SERVER_ERROR.equals(error), "구글 서버에러입니다.");

      }

      break;
    }

  }
}
