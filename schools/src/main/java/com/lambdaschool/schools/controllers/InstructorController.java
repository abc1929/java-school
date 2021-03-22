package com.lambdaschool.schools.controllers;


import com.lambdaschool.schools.models.AdviceSearchResult;
import com.lambdaschool.schools.models.AdviceSlip;
import com.lambdaschool.schools.models.Instructor;
import com.lambdaschool.schools.services.InstructorService;
import com.lambdaschool.schools.services.StudentService;
import net.bytebuddy.asm.Advice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/instructors")
public class InstructorController {


   @Autowired
   InstructorService instructorService;

   @Autowired
   OtherAPIs otherAPIs;


   @GetMapping(value = "/instructor/{id}",
           produces = {"application/json"})
   public ResponseEntity<?> getInstructorByID(@PathVariable long id){

      return new ResponseEntity<>(instructorService.getInstructorByID(id), HttpStatus.OK);


   }



   @GetMapping(value = "/instructor/{id}/advice",
           produces = {"application/json"})
   public ResponseEntity<?> getInstructorWithAdviceById(@PathVariable long id){

      var advice = otherAPIs.getAdvice().getBody();
      var s = instructorService.getInstructorWithAdviceById(id);
      if(advice instanceof AdviceSlip){

      s.setAdvice(((AdviceSlip) advice).getSlip().getAdvice());
      }
      return new ResponseEntity<>(s, HttpStatus.OK);


   }

   @GetMapping(value = "/instructor/{id}/advice/{term}",
           produces = {"application/json"})
   public ResponseEntity<?> getInstructorWithAdviceSearchById(@PathVariable long id, @PathVariable String term) {
      var advice = otherAPIs.searchAdvice(term).getBody();
      var s = instructorService.getInstructorWithAdviceById(id);
      if(advice instanceof AdviceSearchResult){

         s.setAdvice(((AdviceSearchResult) advice).getSlips().get(0).getAdvice());
      }
      return new ResponseEntity<>(s, HttpStatus.OK);
   }

}
