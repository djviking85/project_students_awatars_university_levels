package ru.hogwarts.school.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.hogwarts.school.model.*;
import ru.hogwarts.school.service.AvatarService;
import ru.hogwarts.school.service.StudentService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collection;
import java.util.List;


@RestController
@RequestMapping("/student")

public class StudentController {
    @Autowired
    private AvatarService avatarService;

    public StudentController(StudentService studentService) {
        this.studientService = studentService;
    }

    private final StudentService studientService;

    @GetMapping("{id}")
    public Student getStudentById(@PathVariable Long id) {
        return studientService.findStudent(id);
    }

    @PostMapping

    public Student addStudent(@RequestBody Student student) {
        return studientService.addStudent(student);
    }

//    @GetMapping("/getAll")
//    public ResponseEntity<Collection<Student>> getAllStudent() {
//
//        return ResponseEntity.ok(studientService.getAllStudent());
//    }

    @PutMapping
    public ResponseEntity<Student> updateStudent(@RequestBody Student student) {
        Student foundStudent = studientService.updateStudent(student);
        if (foundStudent == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.ok(foundStudent);

    }

    @DeleteMapping("{id}")
    public ResponseEntity deleteStudent(@PathVariable long id) {
        studientService.deleteStudent(id);
        return ResponseEntity.ok().build();
    }

    //    добавляем в 3.2 дз
    @GetMapping("/get-all")
    public ResponseEntity<Collection<Student>> getAllStudents(@RequestParam(required = false) String name,
                                                              @RequestParam(required = false) Integer age) {
        if (name != null && !name.isBlank())
            return ResponseEntity.ok(studientService.getAllByName(name));
        if (age != null)
            return ResponseEntity.ok(studientService.getAllByAge(age));
        return ResponseEntity.ok(studientService.getAllStudent());
    }

    @GetMapping("/get-count")
    public long getCount() {
        return studientService.getCount();
    }

    @GetMapping("/average-age")
    public double getAverageAge() {
        return studientService.getAverageAge();
    }

    @GetMapping("/last-five")
    public List<Student> getLastFiveStudent() {
        return studientService.getLastFiveStudent();
    }
//3.15 домашка
    //    метод чтоб загружать файлы
//    @PostMapping(value = "{id}/avatar", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
//    public ResponseEntity<String> upLoadAvatar(@PathVariable Long id, @RequestParam MultipartFile cover) throws IOException {
//        if (cover.getSize() >= 1024 * 300) {
//            return ResponseEntity.badRequest().body("File is to BIG");
//        }
//
//        avatarService.uploadAvatar(id, cover);
//        return ResponseEntity.ok().build();
//    }
//
//    //    2 метода - 1 превью загружает уменьшенную версию, 2 - ориганал в гет методах ниже
//    @GetMapping(value = "{id}/avatar/preview")
//    public ResponseEntity<byte[]> downloadAvatar(@PathVariable Long id) {
//        Avatar avatar = avatarService.findAvatarCover(id);
//
////        работа с заголовками
//        HttpHeaders headers = new HttpHeaders();
////        заголовок что за тип данных возрвщается - медиатайп
//        headers.setContentType(MediaType.parseMediaType(avatar.getMediaType()));
////        заголовок длины контента (сколько загружено, сколько всего и определить сколько осталось)
//        headers.setContentLength(avatar.getData().length);
//
////        указываем статус - окей, указываем заголовки о правильности данных и сами данные массивы байт
//        return ResponseEntity.status(HttpStatus.OK).headers(headers).body(avatar.getData());
//    }
//
//    @GetMapping(value = "{id}/cover")
////    тперь 2ой метод
//    public void downloadAvatar (@PathVariable Long id, HttpServletResponse response) throws IOException {
////        получаем инфу об обложке
//        Avatar avatar = avatarService.findAvatarCover(id);
//
////        получаем путь к файлу  и метод оф получаем путь в виделе обьекта Патх
//        Path path = Path.of(avatar.getFilePath());
//
////        так же обьявляем переменные на вход и выход
////                берем класс файлс вызываем метод стрим и забираем по одному байту
//        try   (InputStream is = Files.newInputStream(path);
////               берем обьект респонс и вызываем пакет оаут оф стрим
//
//               OutputStream os = response.getOutputStream();) {
//
////            покажет что все у нах хорошо
//            response.setStatus(200);
//
////            заголовки по типу контента и длине контента
//            response.setContentType(avatar.getMediaType());
//            response.setContentLength((int) avatar.getFileSize());
//
////            вызываем метод трансферт ту на сверер - из жесткого диска и отправляем в браузер пользователя
//            is.transferTo(os);
//        }
@GetMapping("/get-All-With-A")
public List<String> getAllStudentsWithASorted() {
    return studientService.nameStudentWithAnameSorted();
}
 @GetMapping("/average-age-stream")
public Double getAllAverageAge() {
    return studientService.getAllAverageAge();
}
 @GetMapping("/more-long-name")
public String getLongNameFaculty() {
    return studientService.getLongNameFaculty();
}
@GetMapping("/step-4")
public Integer step4() {
    return studientService.step4();
}
@GetMapping("/print-All-Not-Synchr")
public void printAll() {
     studientService.printAllnotSynh();
}
@GetMapping("/print-All-Synchr")
public void printAllSynchr() {
     studientService.printAllsynchronize();
}



}


