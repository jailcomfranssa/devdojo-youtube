package br.com.devdojo.awesome.endpoint;

import br.com.devdojo.awesome.model.Student;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentEndpoint {

    @RequestMapping(method = RequestMethod.GET, path = "/list")
    public List<Student> listAll() {
        return Arrays.asList(new Student("Jailson"), new Student("Maria"), new Student("Jose"));
    }
}
