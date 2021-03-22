package com.lambdaschool.schools.controllers;

import com.lambdaschool.schools.models.AdviceSearchResult;
import com.lambdaschool.schools.models.AdviceSlip;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

@RestController
@RequestMapping(value = "/advices")
public class OtherAPIs
{
   /*
    * Creates the object that is needed to do a client side Rest API call.
    * We are the client getting data from a remote API.
    * We can share this template among endpoints
    */
   private RestTemplate restTemplate = new RestTemplate();

   @GetMapping(value = "/test")
   public ResponseEntity<?> getAdvice()
   {
      // we need to tell our RestTemplate what format to expect
      MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
      // a couple of common formats
      // converter.setSupportedMediaTypes(Collections.singletonList(MediaType.TEXT_HTML));
      // converter.setSupportedMediaTypes(Collections.singletonList(MediaType.APPLICATION_JSON));
      // or we can accept all formats! Easiest but least secure
      converter.setSupportedMediaTypes(Collections.singletonList(MediaType.ALL));
      restTemplate.getMessageConverters().add(converter);

      // create the url to access the API
      String requestURL = "https://api.adviceslip.com/advice";
      // create the responseType expected. Notice the IssPositionReturnData is the data type we are expecting back from the API!
      ParameterizedTypeReference<AdviceSlip> responseType = new ParameterizedTypeReference<>()
      {
      };

      // create the response entity. do the get and get back information
      ResponseEntity<AdviceSlip> responseEntity = restTemplate.exchange(requestURL,
              HttpMethod.GET,
              null,
              responseType);
      // we want to return the Iss_position data. From the data that gets returned in the body,
      // get the Iss_position data only and return it.
      // putting the data into its own object first, prevents the data from being reported to client inside of
      // an embedded. So the response will look more like our clients are use to!
      var data = responseEntity.getBody();
      return new ResponseEntity<>(data,
              HttpStatus.OK);
   }


   @GetMapping(value = "/test2/{term}")
   public ResponseEntity<?> searchAdvice(@PathVariable String term)
   {
      // we need to tell our RestTemplate what format to expect
      MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
      // a couple of common formats
      // converter.setSupportedMediaTypes(Collections.singletonList(MediaType.TEXT_HTML));
      // converter.setSupportedMediaTypes(Collections.singletonList(MediaType.APPLICATION_JSON));
      // or we can accept all formats! Easiest but least secure
      converter.setSupportedMediaTypes(Collections.singletonList(MediaType.ALL));
      restTemplate.getMessageConverters().add(converter);

      // create the url to access the API
      String requestURL = "https://api.adviceslip.com/advice/search/" + term.trim();
      // create the responseType expected. Notice the IssPositionReturnData is the data type we are expecting back from the API!
      ParameterizedTypeReference<AdviceSearchResult> responseType = new ParameterizedTypeReference<>()
      {
      };

      // create the response entity. do the get and get back information
      ResponseEntity<AdviceSearchResult> responseEntity = restTemplate.exchange(requestURL,
              HttpMethod.GET,
              null,
              responseType);
      // we want to return the Iss_position data. From the data that gets returned in the body,
      // get the Iss_position data only and return it.
      // putting the data into its own object first, prevents the data from being reported to client inside of
      // an embedded. So the response will look more like our clients are use to!
      var data = responseEntity.getBody();
      return new ResponseEntity<>(data,
              HttpStatus.OK);
   }
}
