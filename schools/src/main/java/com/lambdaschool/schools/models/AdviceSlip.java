package com.lambdaschool.schools.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AdviceSlip {

   private Slip slip;

   public Slip getSlip() {
      return slip;
   }

   public void setSlip(Slip slip) {
      this.slip = slip;
   }

   public static class Slip {


      private long id;
      private String advice;

      public long getId() {
         return id;
      }

      public void setId(long id) {
         this.id = id;
      }

      public String getAdvice() {
         return advice;
      }

      public void setAdvice(String advice) {
         this.advice = advice;
      }
   }
}




