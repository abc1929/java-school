package com.lambdaschool.schools.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AdviceSearchResult {


   private ArrayList<Slip> slips;

   public ArrayList<Slip> getSlips() {
      return slips;
   }

   public void setSlips(ArrayList<Slip> slips) {
      this.slips = slips;
   }

   @JsonIgnoreProperties(ignoreUnknown = true)
   public static class Slip {


      private long id;
      private String advice;
      private String date;

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


      public String getDate() {
         return date;
      }

      public void setDate(String date) {
         this.date = date;
      }
   }
}




