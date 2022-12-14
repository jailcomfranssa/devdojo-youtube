package br.com.devdojo.youtube.endpoint;


import br.com.devdojo.youtube.error.CustomErrorType;
import br.com.devdojo.youtube.model.Student;
import br.com.devdojo.youtube.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentEndpoint {


    private DateUtil dateUtil;
    @Autowired
    public StudentEndpoint(DateUtil dateUtil){
        this.dateUtil = dateUtil;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> listAll() {
        System.out.println(dateUtil.formatLocalDateTimeToDatabaseStyle(LocalDateTime.now()));
        return new ResponseEntity<>(Student.studentsList, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/{id}")
    public ResponseEntity<?> getStudentById(@PathVariable("id") int id) {
        System.out.println(dateUtil.formatLocalDateTimeToDatabaseStyle(LocalDateTime.now()));
        Student student = new Student();
        student.setId(id);
        int index = Student.studentsList.indexOf(student);
        if (index == -1){
            return new ResponseEntity<>(new  CustomErrorType("Student not found"),HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(Student.studentsList.get(index), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> save(@RequestBody Student student) {
        Student.studentsList.add(student);
        return new  ResponseEntity<>(student,HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public ResponseEntity<?> delete(@RequestBody Student student) {
        Student.studentsList.remove(student);
        return new  ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<?> update(@RequestBody Student student) {
        Student.studentsList.remove(student);
        Student.studentsList.add(student);
        return new  ResponseEntity<>(student,HttpStatus.OK);
    }
}
