package com.lambdaschool.schools.services;


import com.lambdaschool.schools.exceptions.ResourceNotFoundException;
import com.lambdaschool.schools.models.Instructor;
import com.lambdaschool.schools.repositories.InstructorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InstructorServiceImpl implements InstructorService{


   @Autowired
   InstructorRepository instructorRepository;

   @Override
   public Instructor getInstructorByID(long id) {


      return instructorRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Instructor id " + id + " not found!"));

   }


   @Override
   public Instructor getInstructorWithAdviceById(long id){

      return instructorRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Instructor id " + id + " not found!"));
   }


}
