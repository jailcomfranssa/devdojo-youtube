package br.com.devdojo.youtube.endpoint;


import br.com.devdojo.youtube.model.Student;
import br.com.devdojo.youtube.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentEndpoint {

    @Autowired
    private DateUtil dateUtil;

    @RequestMapping(method = RequestMethod.GET, path = "/list")
    public List<Student> listAll() {
        System.out.println(dateUtil.formatLocalDateTimeToDatabaseStyle(LocalDateTime.now()));
        return Arrays.asList(new Student("Jailson"), new Student("Maria"), new Student("Jose"));
    }
}
