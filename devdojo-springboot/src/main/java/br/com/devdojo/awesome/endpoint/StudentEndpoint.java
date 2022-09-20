package br.com.devdojo.awesome.endpoint;

import br.com.devdojo.awesome.model.Student;
import br.com.devdojo.awesome.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentEndpoint {


    private DateUtil dateUtil;

    @Autowired
    public StudentEndpoint(DateUtil dateUtil){
        this.dateUtil = dateUtil;
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<Student> listAll() {
        System.out.println(dateUtil.formatLocalDateTimeToDatabaseStyle(LocalDateTime.now()));
        return Arrays.asList(new Student("Jailson"), new Student("Maria"), new Student("Jose"));
    }
}
