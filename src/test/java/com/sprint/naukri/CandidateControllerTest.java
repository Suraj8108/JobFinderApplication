package com.sprint.naukri;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import com.dao.CandidateDAO;

import com.model.Candidate;


import com.model.Project;
import com.service.CandidateService;


@SpringBootTest
public class CandidateControllerTest {
	@Autowired
	CandidateDAO candao ;

	@Autowired
	CandidateService service;


	
	 Candidate cand1=new Candidate();
	
	static Candidate cand2 = new Candidate();
	

	//rest controller test
	@BeforeEach
	void setUp() throws Exception {
		candao.deleteAll();
		candao.flush();
		cand1.setAge(21);
		cand1.setCandidateName("Yash");
		
		
	}
	@AfterEach
	void setdown() throws Exception {
		candao.deleteAll();
		candao.flush();
		
	}
	
	@Test
    public void addProfiletest() throws URISyntaxException 
    {
        RestTemplate restTemplate = new RestTemplate();
  
        HttpHeaders headers=new HttpHeaders();
         
         headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
         
         HttpEntity entity=new HttpEntity(cand1,headers);
     
        ResponseEntity<String> result = restTemplate.exchange("http://localhost:9989/addProfile",
                HttpMethod.POST,entity,String.class);
         
        //Verify request succeed
       Assertions.assertEquals(202, result.getStatusCodeValue());
       Assertions.assertEquals("Candidate added successfully", result.getBody());
    }
	
	
	
	
	

	

	
	

	
	@Test
	public void getallcandidates() throws URISyntaxException 
	{
		
		
	    RestTemplate restTemplate = new RestTemplate();
	   
	    
		HttpHeaders headers=new HttpHeaders();
	     
		 headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
	     
		 HttpEntity<List<Candidate>> entity=new HttpEntity<>(headers);
	 
		ResponseEntity<List>  result = restTemplate.exchange("http://localhost:9989/getallcandidates",
                HttpMethod.GET,entity,List.class);
		
		List<Candidate> temp = candao.findAll();

		//Assertions.assertEquals(temp,result.getBody());
		
		//assertTrue(temp.equals(result.getBody()));



		Assertions.assertEquals(temp.toString(),result.getBody().toString());
		
	    //Verify request succeed
	    Assertions.assertEquals(200, result.getStatusCodeValue());
	 
	 		
	}
	
//	@Test
//	public void getbyidTest() throws URISyntaxException {
//		cand1.setAge(21);
//		cand1.setCandidateName("Yash");
//		List<Project> projectlist=new ArrayList<>();
////		projectlist.add(new Project());
//		cand1.setProjectList(projectlist);
//
//		candao.save(cand1);
//		
//	//System.out.println(cand1);
//		RestTemplate template = new RestTemplate();
//		
//		 final String url="http://localhost:9989/findcandidatebyid/"+""+cand1.getCandidateId();
//	      URI uri=new URI(url);
//	      ResponseEntity<Candidate> response = template.getForEntity(uri,Candidate.class);
//	     List<HttpStatus> expected = new ArrayList<>();
//	     expected.add(HttpStatus.FOUND);
//	     expected.add(HttpStatus.FORBIDDEN);
//	     Assertions.assertTrue(expected.contains(response.getStatusCode())) ;
//	    
//	    Assertions.assertEquals(cand1.toString(),response.getBody().toString());
//	}
//	
//	@Test
//	public void updateCandidateTest() throws URISyntaxException 
//	{
//	   
//	
//		
//		RestTemplate template=new RestTemplate();
//        template.setRequestFactory(new HttpComponentsClientHttpRequestFactory());
//	   
//	    
//		HttpHeaders headers=new HttpHeaders();
//	     
//		 headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
//	     
//		 HttpEntity<Candidate> entity=new HttpEntity<>(cand1,headers);
//	 
//		ResponseEntity<String>  result = template.exchange("http://localhost:9989/updatecandidate",
//                HttpMethod.PATCH,entity,String.class);
//		
//		
//		
//		
//		
//	    Assertions.assertEquals(202, result.getStatusCodeValue());
//		   Assertions.assertEquals("Candidate updated successfully", result.getBody());
//
//	 		
//	}
//	
//	
//	@Test
//	public void deleteCandidateTest() throws URISyntaxException 
//	{
//	    
//		candao.save(cand1);
//		
//		RestTemplate restTemplate = new RestTemplate();
//	   
//	    
//		HttpHeaders headers=new HttpHeaders();
//	     
//		 headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
//	     
//		 HttpEntity<Candidate> entity=new HttpEntity<>(cand1,headers);
//	 
//		ResponseEntity<String>  result = restTemplate.exchange("http://localhost:9989/deletecandidate",
//                HttpMethod.DELETE,entity,String.class);
//
//		
//		//Assertions.assertEquals(temp,result.getBody());
//		
//		//assertTrue(temp.equals(result.getBody()));
//		
//		//Assertions.assertEquals(temp.toString(),result.getBody().toString());
//		
//
//			
//
//	    //Verify request succeed
//		
//	    Assertions.assertEquals(202, result.getStatusCodeValue());
//		   Assertions.assertEquals("Candidate delete successfully", result.getBody());
//
//	 		
//	}
//	@Test
//	public void deletebyidTest() {
//		 candao.save(cand1);
//		
//		RestTemplate restTemplate = new RestTemplate();
//	   
//	    
//		HttpHeaders headers=new HttpHeaders();
//	     
//		 headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
//	     
//		 HttpEntity<Candidate> entity=new HttpEntity<>(cand1,headers);
//	 
//		ResponseEntity<String>  result = restTemplate.exchange("http://localhost:9989/deletecandidate/"+""+cand1.getCandidateId(),
//                HttpMethod.DELETE,entity,String.class);
//		   Assertions.assertEquals("Candidate delete successfully", result.getBody());
//
//		
//	}
//	
	

	

}

//repeated

//@Test
//public void addProfiletest() throws URISyntaxException 
//{
//    RestTemplate restTemplate = new RestTemplate();
//     
////  final String baseUrl = "http://localhost:9989/addcandidate";
////  URI uri = new URI(baseUrl);
//    
//    
//    
//    HttpHeaders headers=new HttpHeaders();
//     
//     headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
//     
//     HttpEntity entity=new HttpEntity(cand1,headers);
// 
//    ResponseEntity<String> result = restTemplate.exchange("http://localhost:9989/addprofile",
//            HttpMethod.POST,entity,String.class);
//     
//    //Verify request succeed
//   Assertions.assertEquals(202, result.getStatusCodeValue());
//   Assertions.assertEquals("Candidate added successfully", result.getBody());
//}
//

