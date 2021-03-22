package com.lambdaschool.schools.models;

public class ValidationError {

   private String fieldname;

   private String message;

   public String getFieldname() {
      return fieldname;
   }

   public String getMessage() {
      return message;
   }

   public void setMessage(String message) {
      this.message = message;
   }

   public void setFieldname(String fieldname) {
      this.fieldname = fieldname;
   }
}
