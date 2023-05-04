package ru.hogwarts.school.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.hogwarts.school.model.Avatar;
import ru.hogwarts.school.service.AvatarService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

@RestController
@RequestMapping("avatar")

public class AvatarController {
    private final AvatarService avatarService;

    public AvatarController(AvatarService avatarService) {
        this.avatarService = avatarService;


    }

    @PostMapping(value = "{id}/avatar", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> upLoadAvatar(@PathVariable Long id, @RequestParam MultipartFile cover) throws IOException {
        if (cover.getSize() >= 1024 * 300) {
            return ResponseEntity.badRequest().body("File is to BIG");
        }

        avatarService.uploadAvatar(id, cover);
        return ResponseEntity.ok().build();
    }

    //    2 метода - 1 превью загружает уменьшенную версию, 2 - ориганал в гет методах ниже
    @GetMapping(value = "{id}/avatar/preview")
    public ResponseEntity<byte[]> downloadAvatar(@PathVariable Long id) {
        Avatar avatar = avatarService.findAvatarCover(id);

//        работа с заголовками
        HttpHeaders headers = new HttpHeaders();
//        заголовок что за тип данных возрвщается - медиатайп
        headers.setContentType(MediaType.parseMediaType(avatar.getMediaType()));
//        заголовок длины контента (сколько загружено, сколько всего и определить сколько осталось)
        headers.setContentLength(avatar.getData().length);

//        указываем статус - окей, указываем заголовки о правильности данных и сами данные массивы байт
        return ResponseEntity.status(HttpStatus.OK).headers(headers).body(avatar.getData());
    }

    @GetMapping(value = "{id}/cover")
//    тперь 2ой метод
    public void downloadAvatar(@PathVariable Long id, HttpServletResponse response) throws IOException {
//        получаем инфу об обложке
        Avatar avatar = avatarService.findAvatarCover(id);

//        получаем путь к файлу  и метод оф получаем путь в виделе обьекта Патх
        Path path = Path.of(avatar.getFilePath());

//        так же обьявляем переменные на вход и выход
//                берем класс файлс вызываем метод стрим и забираем по одному байту
        try (InputStream is = Files.newInputStream(path);
//               берем обьект респонс и вызываем пакет оаут оф стрим

             OutputStream os = response.getOutputStream();) {

//            покажет что все у нах хорошо
            response.setStatus(200);

//            заголовки по типу контента и длине контента
            response.setContentType(avatar.getMediaType());
            response.setContentLength((int) avatar.getFileSize());

//            вызываем метод трансферт ту на сверер - из жесткого диска и отправляем в браузер пользователя
            is.transferTo(os);
        }
    }
    @GetMapping
    public List<Avatar> getAll(@RequestParam int page, @RequestParam int size) {
        return avatarService.getAll(page, size);
    }
}
