package yass.jouao.labx.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.databind.ObjectMapper;

import yass.jouao.labx.DTOs.AnalysisDTO;
import yass.jouao.labx.serviceImpl.AnalysisImpl;

@RestController
@RequestMapping("/analysis")
public class AnalysisController {

	@Autowired
	private ObjectMapper objectMapper;
	@Autowired
	private AnalysisImpl analysisImpl;

	@PostMapping("/add")
	public ResponseEntity<?> addAnalysis(
			@RequestBody @JsonView(AnalysisDTO.saveAnalysis.class) AnalysisDTO analysisDTO) {
		try {
			AnalysisDTO aDTO = analysisImpl.addAnalysisService(analysisDTO);
			String json = objectMapper.writerWithView(AnalysisDTO.viewAnalysis.class).writeValueAsString(aDTO);
			return new ResponseEntity<>(json, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
