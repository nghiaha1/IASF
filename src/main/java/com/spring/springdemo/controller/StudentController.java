package com.spring.springdemo.controller;

import com.spring.springdemo.entity.Student;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;



/**
 * /v1/students         | GET           | return list student
 * /v1/students         | POST          | create new student
 * /v1/students/id      | PUT           | edit student
 * /v1/students/id      | DELETE        | remove student
 * /v1/students/id      | GET           | find student by id
 *
 * */

@RestController
@RequestMapping(path = "api/v1/students")
public class StudentController {
    private static List<Student> list;

    public StudentController() {
        list = new ArrayList<>();
        list.add(Student.builder().id(1).fullName("ABC").email("ABC@gmail.com").phone("1234567891").address("Hanoi").build());
        list.add(Student.builder().id(2).fullName("ABCD").email("ABCD@gmail.com").phone("1234567892").address("Hanoi").build());
        list.add(Student.builder().id(3).fullName("ABCDE").email("ABCDE@gmail.com").phone("1234567893").address("Hanoi").build());
        list.add(Student.builder().id(4).fullName("ABCDEF").email("ABCDEF@gmail.com").phone("1234567894").address("Hanoi").build());
        list.add(Student.builder().id(5).fullName("ABCDEFG").email("ABCDEFG@gmail.com").phone("1234567895").address("Hanoi").build());
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<Student> findAll() {
        return list;
    }

    @RequestMapping(method = RequestMethod.POST)
    public Student save(@RequestBody Student student) {
        list.add(student);
        return student;
    }

    @RequestMapping(path = "{id}", method = RequestMethod.PUT)
    public Student update(@PathVariable int id, @RequestBody Student updateStudent){
        int foundIndex = -1;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getId() == id) {
                foundIndex = i;
                break;
            }
        }
        if (foundIndex == -1) {
            return null;
        }
        Student existingStudent = list.get(foundIndex);
        existingStudent.setFullName(updateStudent.getFullName());
        existingStudent.setEmail(updateStudent.getEmail());
        existingStudent.setPhone(updateStudent.getPhone());
        existingStudent.setAddress(updateStudent.getAddress());
        existingStudent.setDob(updateStudent.getDob());
        existingStudent.setStatus(updateStudent.getStatus());
        return existingStudent;
    }

    @RequestMapping(path = "{id}", method = RequestMethod.GET)
    public Student findById(@PathVariable int id) {
        int foundIndex = -1;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getId() == id) {
                foundIndex = i;
                break;
            }
        }
        if (foundIndex == -1) {
            return null;
        }
        return list.get(foundIndex);
    }

    @RequestMapping(path = "{id}", method = RequestMethod.DELETE)
    public boolean deleteById(@PathVariable int id){
        int foundIndex = -1;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getId() == id) {
                foundIndex = i;
                break;
            }
        }
        if (foundIndex == -1) {
            return false;
        }
        list.remove(foundIndex);
        return true;
    }
}
