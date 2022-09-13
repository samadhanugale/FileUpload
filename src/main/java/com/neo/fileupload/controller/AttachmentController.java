package com.neo.fileupload.controller;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.neo.fileupload.entity.Attachment;
import com.neo.fileupload.files.ResponseData;
import com.neo.fileupload.service.AttachmentService;
import lombok.RequiredArgsConstructor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class AttachmentController {
  public AttachmentController(AttachmentService attachmentService) {
    this.attachmentService = attachmentService;
  }

  private AttachmentService attachmentService;

  @PostMapping("/upload")
  public ResponseData uploadFile(@RequestParam("file") MultipartFile file) throws Exception {
    Attachment attachment = attachmentService.saveAttachment(file);
    String downloadURL =
        ServletUriComponentsBuilder.fromCurrentContextPath()
            .path("/download/")
            .path(attachment.getId())
            .toUriString();
    return new ResponseData(
        attachment.getFileName(), downloadURL, file.getContentType(), file.getSize());
  }

  @GetMapping("/download/{fileId}")
  public ResponseEntity<ByteArrayResource> downloadFile(@PathVariable String fileId)
      throws Exception {

    Attachment attachment = attachmentService.getAttachment(fileId);

    return ResponseEntity.ok()
        .contentType(MediaType.parseMediaType(attachment.getFileType()))
        .header(
            HttpHeaders.CONTENT_DISPOSITION,
            "attachment; fileName =\" " + attachment.getFileName() + " ")
        .body(new ByteArrayResource(attachment.getData()));
  }

  @GetMapping("/ping")
  public String sayPong() {
    return "pong";
  }

  @GetMapping("/translate/{toLang}/{text}")
  public String translateText(@PathVariable String text,@PathVariable String toLang) throws Exception {
    // Translator.translate(fromLang, toLang, text);
    Translator translator = new Translator();
    String fromLang= "en";

    String out = translator.translate(fromLang, toLang, text);
    String jsonPayload = new StringBuilder()
            .append("{")
            .append("\"fromLang\":\"")
            .append(fromLang)
            .append("\",")
            .append("\"toLang\":\"")
            .append(toLang)
            .append("\",")
            .append("\"text\":\"")
            .append(out)
            .append("\"")
            .append("}")
            .toString();
    return jsonPayload;
  }

  @GetMapping("/google/{toLang}/{text}")
  public String googleTranslate(@PathVariable String text,@PathVariable String toLang) throws Exception {
    // Translator.translate(fromLang, toLang, text);
    Translate translate = new Translate();
    String fromLang= "en";

    String out = translate.translate(fromLang,toLang,text);
    String jsonPayload = new StringBuilder()
            .append("{")
            .append("\"fromLang\":\"")
            .append(fromLang)
            .append("\",")
            .append("\"toLang\":\"")
            .append(toLang)
            .append("\",")
            .append("\"text\":\"")
            .append(out)
            .append("\"")
            .append("}")
            .toString();
    return jsonPayload;
  }
@GetMapping("/demo/{name}")
public String demoStr(@PathVariable String name){
    return "Hi "+name+" How are you";
}
  @GetMapping("/get_pan")
  public String kycHubAccountVerification(String details){
    try{
      OkHttpClient client = new OkHttpClient().newBuilder().build();

      okhttp3.MediaType mediaType = okhttp3.MediaType.parse("application/json");

      RequestBody body = RequestBody.create(mediaType,"{\n \"account_number\":\"0568101041329\",\n   \"name\":\"Mallesh Fakkirappa Dollin\",\n   \"ifsc\":\"CNRB0000568\"\n}");

      Request request = new Request.Builder()
              .header("Authorization","Bearer fbfcf913-b43a-4f4c-b837-e540cddc2600")
              .url("https://api.dev.kychub.com/kyc/india/v2/verify-bank-account")
              .method("POST",body)
              .build();
      Response response= client.newCall(request).execute();
      return response.body().string();
    }catch (Exception e){
      System.out.println(e.getMessage());
      return "error "+e.getMessage();
    }
  }
}
