package ru.hogwarts.school.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.hogwarts.school.model.Avatar;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.AvatarRepository;

import javax.imageio.ImageIO;
import javax.transaction.Transactional;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static java.nio.file.StandardOpenOption.CREATE_NEW;

@Service
@Transactional

public class AvatarService {
    private final Logger logger = LoggerFactory.getLogger(AvatarService.class);
    @Value("avatars")
    private String avatarDir;
    private final StudentService studentService;
    private AvatarRepository avatarRepository;

    public AvatarService(StudentService studentService, AvatarRepository avatarRepository) {
        this.studentService = studentService;
        this.avatarRepository = avatarRepository;
    }
    public void uploadAvatar (Long avatarId, MultipartFile file) throws IOException {
        logger.info("We was upload our Avatar!");
        Student student = studentService.findStudent(avatarId);

        Path filePath = Path.of(avatarDir, avatarId + "." + getExtension(file.getOriginalFilename()));

//        метод который проверит папку и если ее нет то он ее создаст
        Files.createDirectories(filePath.getParent());

//        метод который удаляет файл если он существует (если к примеру более новая версия файла)
        Files.deleteIfExists(filePath);

//        мы открываем поток данныйх и потом его закрываем
        try (
//            ОТКУДА ЧЕРПАТЬ   открываем поток и читаем из нашего файла, который был загружен пользователем
                InputStream is = file.getInputStream();
//         КУДА ЧЕРПАТЬ        создаем пустой файл в который будем записывать и передаем пути и в этот пустой файл будем записывать
                OutputStream os  = Files.newOutputStream(filePath, CREATE_NEW);
//          СКОЛЬКО ИНФОРМАЦИИ БРАТЬ ЗА РАЗ      и чтоб не черпать чайной ложкой эти файлы - мы создаем буферезированные потоки и сколько байт за раз (1024) мы будем забирать
                BufferedInputStream bis = new BufferedInputStream(is, 1024);
//          СКОЛЬКО ИНФОРМАЦИИ ЗАПИСЫВАТЬ ЗА РАЗ      точно такую же операцияю делаем запись по буферу 1024
                BufferedOutputStream bos = new BufferedOutputStream(os, 1024);
        ) {
//            передача информации из вВХОДНОГО потока в ВЫХОДНОЙ поток bis -> bos
            bis.transferTo(bos);
        }
//        если загружэаем книгу - сначала изем по айди если нет создаем
        Avatar avatar = findAvatarCover(avatarId);
//        ДАННЫЕ ДЛЯ СОХРАНЕНИЯ НА ЛОКАЛЬНЫЙ ДИСК
//        указываем книгу где загружаем обложку
        avatar.setStudent(student);
//        указываем путь к файлу который созранили на диске
        avatar.setFilePath(filePath.toString());
//        указываем его размер
        avatar.setFileSize(file.getSize());
//        указываем тип контент
        avatar.setMediaType(file.getContentType());
//        --------------------------------------

//         в этом методе иметь копию уменьшенную в размере до 100 пикселей которая будет храниться в базе данных
//        и мы получаем массив байт и записываем в переменную ПРЕВЬЮ и записывает в базу данных

        avatar.setData(generateImagePreview(filePath));

        avatarRepository.save(avatar);
    }
    private String getExtension (String fileName) {
        return fileName.substring(fileName.lastIndexOf(".") + 1);
    }
    public Avatar findAvatarCover(Long avatarId) {
        logger.info("We found our avatar!");
        return avatarRepository.findAvatarById(avatarId).orElse(new Avatar());
    }
    public byte [] generateImagePreview(Path filePath) throws IOException {
        try (InputStream is = Files.newInputStream(filePath);
             BufferedInputStream bis = new BufferedInputStream(is, 1024);
             ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
            BufferedImage image = ImageIO.read(bis);

            int height = image.getHeight() / (image.getWidth() / 100);
            BufferedImage preview = new BufferedImage(100, height, image.getType());
            Graphics2D graphics = preview.createGraphics();
            graphics.drawImage(image, 0, 0, 100, height, null);
            graphics.dispose();

            ImageIO.write(preview, getExtension(filePath.getFileName().toString()), baos);
            return baos.toByteArray();
        }
    }

    public List<Avatar> getAll(int page, int size) {
        logger.info("We want to find all avatars!");
        PageRequest pageRequest = PageRequest.of(page, size);
        return avatarRepository.findAll(pageRequest).getContent();

    }

}
