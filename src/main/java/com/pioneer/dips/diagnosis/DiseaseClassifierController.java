package com.pioneer.dips.diagnosis;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

import weka.classifiers.bayes.NaiveBayesUpdateable;

@Configuration
@RestController
@RequestMapping(value = "/api/diagnosis")
public class DiseaseClassifierController {
	
	DiseaseClassifier dc= new DiseaseClassifier();
	static final NaiveBayesUpdateable nb =new NaiveBayesUpdateable();
	static final String file = "dis_sym_dataset_comb.arff";
	
	@CrossOrigin(origins = "http://localhost:8089") 
	@PostMapping("/")
	 public ResponseEntity<?> diagnosis(@RequestBody String t) throws Exception {
		
		ObjectMapper mapper = new ObjectMapper();
		DiagnosisResult diagnosisResult = new DiagnosisResult();
		diagnosisResult = dc.classify(nb, t);
		if(diagnosisResult == null) {
			return ResponseEntity.unprocessableEntity().build();
		}
		String jsonStr = mapper.writeValueAsString(diagnosisResult);
		return ResponseEntity.ok().body(jsonStr);
	  }
	 @Bean
	 void trainClassifier() {

		  try {
			  //Intentionally commented
			//String token = "0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0";
			dc.buildClassifier(nb, file);
			//dc.classify(nb, token);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	  }
	 public static class Token{
			public String token;

			public String getToken() {
				return token;
			}
			
			public Token() {
				super();
			}

			public Token(String token) {
				super();
				this.token = token;
			}


			public void setToken(String token) {
				this.token = token;
			}
		}
}

