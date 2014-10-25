package com.devuger.common.services;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.WritableByteChannel;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.web.multipart.MultipartFile;

import com.devuger.common.entities.Attachment;
import com.devuger.common.entities.User;
import com.devuger.common.support.base.BaseService;
import com.devuger.util.HelloThumbnail;
import com.devuger.util.HelloUtil;

@Service
public class AttachmentService extends BaseService {

  // Thread buffer size
  private static final int BUFF_SIZE = 1024 * 1024;

  // 파일 다운로드
  public void download(HttpServletResponse response, Attachment attachment) throws IOException {
    Assert.notNull(attachment, "첨부 파일이 없습니다.");

    int cacheAge = 60 * 10;
    long expiry = new Date().getTime() + cacheAge * 1000;
    FileInputStream in = null;
    ServletOutputStream out = null;

    // download
    String headerFileNm = "\"" + new String(attachment.getName().getBytes(), "iso8859-1") + "\"";
    response.setHeader("Content-Type", attachment.getContentType());
    response.setHeader("Content-Length", String.valueOf(attachment.getSize()));
    response.setHeader("Content-Disposition", "filename=" + headerFileNm);
    response.setHeader("Cache-Control", "max-age=" + cacheAge);
    response.setDateHeader("Expires", expiry);

    String attachmentPath = HelloUtil.getAttachmentPath(attachment.getCreatedOn(), attachment.getId());

    in = new FileInputStream(attachmentPath);
    out = response.getOutputStream();

    FileChannel cin = in.getChannel();
    byte[] buffer = new byte[BUFF_SIZE];
    ByteBuffer byteBuffer = ByteBuffer.wrap(buffer);

    try {
      for (int length = 0; (length = cin.read(byteBuffer)) != -1;) {
        out.write(buffer, 0, length);
        byteBuffer.clear();
      }
    } catch (IOException e) {
      // TODO Auto-generated catch block
      this.getLogger().error(e.getMessage());
    } finally {
      if (in != null)
        in.close();
      if (out != null)
        out.close();
    }
  }

  /**
   * Thumbnail 생성
   * 
   * @param response
   * @param attachment2
   * @param size
   * @throws IOException
   */
  // @Cacheable(value="attachmentCache",
  // key="#attachment.id.toString().concat('-').concat(#width).concat('-').concat(#height)")
  public void thumbnail(HttpServletResponse response, Attachment attachment, int width, int height, boolean ratio) throws IOException {

    Assert.notNull(attachment, "이미 삭제된 파일입니다.");
    String attachmentDirPath = HelloUtil.getAttachmentDirPath(attachment.getCreatedOn());
    String attachmentPath = HelloUtil.getAttachmentPath(attachment.getCreatedOn(), attachment.getId());
    String attachmentThumbnailDirPath = attachmentDirPath + "/thumbnail";
    String attachmentThumbnailPath = String.format("%s/thumbnail/%s_%d_%d", attachmentDirPath, attachment.getId().toString(), width, height);

    // 원본 파일이 존재하는지 체크
    File file = new File(attachmentPath);
    Assert.isTrue(file.exists(), "해당 파일이 없습니다.");

    BufferedImage bimg = ImageIO.read(file);
    int srcWidth = bimg.getWidth();
    int srcHeight = bimg.getHeight();

    attachment.setWidth(srcWidth);
    attachment.setHeight(srcHeight);
    attachmentRepository.save(attachment);

    // thubnail path 체크
    File thumbnailfile = new File(attachmentThumbnailDirPath);
    if (!thumbnailfile.isDirectory())
      Assert.isTrue(thumbnailfile.mkdirs(), "파일 생성을 실패 하였습니다.");

    // 섬네일 생성된게 없으면 생성 하기
    File thumbnailFile = new File(attachmentThumbnailPath);
    if (!thumbnailFile.exists()) {
      if (ratio)
        HelloThumbnail.thumbnailCreate(file, attachmentThumbnailPath, width);
      else
        HelloThumbnail.thumbnailCreate(file, attachmentThumbnailPath, width, height);
    }

    // Set Header
    int cacheAge = 60 * 10;
    long expiry = new Date().getTime() + cacheAge * 1000;

    response.setHeader("Content-Type", "image/jpg");
    response.setHeader("Cache-Control", "max-age=" + cacheAge);
    response.setDateHeader("Expires", expiry);

    write(response, attachmentThumbnailPath);
  }

  private void write(HttpServletResponse response, String path) throws IOException {

    FileInputStream fin = null;
    FileChannel inputChannel = null;
    WritableByteChannel outputChannel = null;

    try {
      fin = new FileInputStream(path);
      inputChannel = fin.getChannel();

      outputChannel = Channels.newChannel(response.getOutputStream());
      inputChannel.transferTo(0, fin.available(), outputChannel);
    } catch (Exception e) {
      throw e;
    } finally {
      try {
        if (fin != null)
          fin.close();
        if (inputChannel.isOpen())
          inputChannel.close();
        if (outputChannel.isOpen())
          outputChannel.close();
      } catch (Exception e) {
        fin.close();
        inputChannel.close();
        outputChannel.close();
      }
    }
  }

  @CacheEvict(value = "attachmentCache", allEntries = true)
  public Attachment assign(Long id, Long tablePk) {
    Attachment attachment = get(id);
    Assert.notNull(attachment, "존재하지 않는 파일입니다.");
    attachment.setActive(true);
    return attachmentRepository.save(attachment);
  }

  // 파일 업로드
  public Attachment upload(User user, Attachment attachment, String tableName, String tableColumn) throws NoSuchAlgorithmException, IOException {
    return upload(user, attachment, tableName, tableColumn, null);
  }

  // 파일 업로드
  public Attachment upload(User user, Attachment attachment, String tableName, String tableColumn, Long tablePk) throws NoSuchAlgorithmException, IOException {
    Assert.notNull(tableName, "올바른 위치를 선택 해주세요. (1)");
    Assert.notNull(tableColumn, "올바른 위치를 선택 해주세요. (2)");
    Assert.notNull(attachment.getAttachmentData(), "파일을 업로드 해주세요.");
    Assert.isTrue(!attachment.getAttachmentData().isEmpty(), "파일을 업로드 해주세요.");

    InputStream is = attachment.getAttachmentData().getInputStream();
    String contentType = attachment.getAttachmentData().getContentType();
    String name = attachment.getAttachmentData().getOriginalFilename();
    long size = attachment.getAttachmentData().getSize();

    Assert.isTrue(contentType.contains("jpeg") || contentType.contains("png") || contentType.contains("gif"), "JPG, PNG, GIF 이미지 파일만 업로드가능합니다.");

    Date today = new Date();
    attachment.setId(null);
    attachment.setName(name);
    attachment.setSize(size);
    attachment.setContentType(contentType);
    attachment.setDownloads(0);
    attachment.setActive(false);
    attachment.setCreatedOn(today);
    attachment.setCreatedBy(user);
    attachment = attachmentRepository.save(attachment);

    String attachmentDirPath = HelloUtil.getAttachmentDirPath(today);

    // 파일 쓰기
    File file = new File(attachmentDirPath);
    if (!file.isDirectory())
      Assert.isTrue(file.mkdirs(), "파일 생성을 실패 하였습니다.");

    file = new File(attachmentDirPath, attachment.getId().toString());

    OutputStream os = new FileOutputStream(file);
    byte[] buffer = new byte[4096];
    int bytesRead;
    while ((bytesRead = is.read(buffer)) != -1) {
      os.write(buffer, 0, bytesRead);
    }
    os.close();

    is = new FileInputStream(file);
    // MD5 Digest
    String digest = HelloUtil.getMD5Sum(is);
    this.getLogger().info("digest: " + digest);
    is.close();

    attachment.setDigest(digest);
    attachment = attachmentRepository.save(attachment);

    return attachment;
  }

  public Attachment upload(User user, URLConnection urlConnection, InputStream is, int sort, int width, int height) throws IOException {
    Assert.notNull(urlConnection, "파일을 업로드 해주세요.");

    URL url = urlConnection.getURL();
    String fileURL = url.getFile();
    String name = "";
    String disposition = urlConnection.getHeaderField("Content-Disposition");

    if (disposition != null) {
      // extracts file name from header field
      int index = disposition.indexOf("filename=");
      if (index > 0) {
        name = disposition.substring(index + 10, disposition.length() - 1);
      }
    } else {
      // extracts file name from URL
      name = fileURL.substring(fileURL.lastIndexOf("/") + 1, fileURL.length());
    }

    String contentType = urlConnection.getContentType();
    long size = urlConnection.getContentLength();

    // MD5 Digest
    String digest = null;
    try {
      digest = HelloUtil.getMD5Sum(is);
      this.getLogger().info("digest: " + digest);
    } catch (NoSuchAlgorithmException e) {
      // TODO Auto-generated catch block
      this.getLogger().error(e.getMessage());
    } finally {
      is.reset();
    }

    Attachment srcAttachment = attachmentRepository.findByDigest(digest);
    if (srcAttachment != null)
      return srcAttachment;

    Assert.isTrue(contentType.contains("jpeg") || contentType.contains("png") || contentType.contains("gif"), "JPG, PNG, GIF 이미지 파일만 업로드가능합니다.");

    Date today = new Date();
    Attachment attachment = new Attachment();
    attachment.setId(null);
    attachment.setName(name);
    attachment.setSize(size);
    attachment.setContentType(contentType);
    attachment.setDigest(digest);
    attachment.setDownloads(0);
    attachment.setActive(false);
    attachment.setSort(sort);
    attachment.setWidth(width);
    attachment.setHeight(height);
    attachment.setCreatedOn(today);
    attachment.setCreatedBy(user);
    attachment = attachmentRepository.save(attachment);

    String attachmentDirPath = HelloUtil.getAttachmentDirPath(today);

    // 파일 쓰기
    File file = new File(attachmentDirPath);
    if (!file.isDirectory())
      Assert.isTrue(file.mkdirs(), "파일 생성을 실패 하였습니다.");

    file = new File(attachmentDirPath, attachment.getId().toString());

    OutputStream os = new FileOutputStream(file);
    byte[] buffer = new byte[4096];
    int bytesRead;
    while ((bytesRead = is.read(buffer)) != -1) {
      os.write(buffer, 0, bytesRead);
    }
    os.flush();
    os.close();

    return attachment;
  }

  public Attachment upload(User user, MultipartFile multipartFile, int sort, String comment) throws IOException {
    Assert.notNull(multipartFile, "파일을 업로드 해주세요.");
    Assert.isTrue(!multipartFile.isEmpty(), "파일을 업로드 해주세요.");

    InputStream is = multipartFile.getInputStream();
    String contentType = multipartFile.getContentType();
    String name = multipartFile.getOriginalFilename();
    long size = multipartFile.getSize();

    Assert.isTrue(contentType.contains("jpeg") || contentType.contains("png") || contentType.contains("gif"), "JPG, PNG, GIF 이미지 파일만 업로드가능합니다.");

    Date today = new Date();
    Attachment attachment = new Attachment();
    attachment.setId(null);
    attachment.setName(name);
    attachment.setSize(size);
    attachment.setContentType(contentType);
    attachment.setDownloads(0);
    attachment.setActive(false);
    attachment.setSort(sort);
    attachment.setCreatedOn(today);
    attachment.setCreatedBy(user);
    attachment = attachmentRepository.save(attachment);

    String attachmentDirPath = HelloUtil.getAttachmentDirPath(today);

    // 파일 쓰기
    File file = new File(attachmentDirPath);
    if (!file.isDirectory())
      Assert.isTrue(file.mkdirs(), "파일 생성을 실패 하였습니다.");

    file = new File(attachmentDirPath, attachment.getId().toString());

    OutputStream os = new FileOutputStream(file);
    byte[] buffer = new byte[4096];
    int bytesRead;
    while ((bytesRead = is.read(buffer)) != -1) {
      os.write(buffer, 0, bytesRead);
    }
    os.close();

    is = new FileInputStream(file);
    // MD5 Digest
    String digest = null;
    try {
      digest = HelloUtil.getMD5Sum(is);
      this.getLogger().info("digest: " + digest);
    } catch (NoSuchAlgorithmException e) {
      // TODO Auto-generated catch block
      this.getLogger().error(e.getMessage());
    }
    is.close();

    attachment.setDigest(digest);
    attachment = attachmentRepository.save(attachment);

    return attachment;
  }

  @Cacheable(value = "attachmentCache", key = "#id")
  public Attachment get(Long id) {
    return attachmentRepository.findOne(id);
  }

  public static String getDigest(InputStream is, MessageDigest md, int byteArraySize) throws NoSuchAlgorithmException, IOException {

    md.reset();
    byte[] bytes = new byte[byteArraySize];
    int numBytes;
    while ((numBytes = is.read(bytes)) != -1) {
      md.update(bytes, 0, numBytes);
    }
    byte[] digest = md.digest();
    String result = new String(digest.toString());
    return result;
  }

  // 파일 삭제
  public void delete(User createdBy, Long id) {
    Attachment attachment = attachmentRepository.findOne(id);
    Assert.notNull(attachment, "이미 삭제된 파일입니다.");
    Assert.isTrue(createdBy.equals(attachment.getCreatedBy()), "삭제 권한이 없습니다.");

    // DB부터 삭제 한다. 왜냐 롤백이 가능하기 때문에..
    // 파일은 삭제되고 DB 에러나면 파일은 롤백이 안된다.
    attachmentRepository.delete(id);

    String attachmentPath = HelloUtil.getAttachmentPath(attachment.getCreatedOn(), id);
    File file = new File(attachmentPath);
    file.deleteOnExit();
  }
}
