package com.lambdaschool.schools.services;

import com.lambdaschool.schools.models.Instructor;

public interface InstructorService {

   Instructor getInstructorByID(long id);
   Instructor getInstructorWithAdviceById(long id);

}
