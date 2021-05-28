package com.problema2.controller;


import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.problema2.dto.CalcularHoraRequest;
import com.problema2.dto.CalcularHoraResponse;
import com.problema2.dto.CalculoResponse;

@RestController
@RequestMapping("api")
public class CalcularHoraController {

	@PostMapping(path = "/calcularhora",
				consumes = {MediaType.APPLICATION_JSON_VALUE},  
				produces = {MediaType.APPLICATION_JSON_VALUE})
	
	public CalculoResponse Calcular(@RequestBody CalcularHoraRequest calcularHoraRequest) {
		
		CalculoResponse response =  new CalculoResponse();
		CalcularHoraResponse calculo = new CalcularHoraResponse();
		
		try {
			
			String[] HoraMinArray = calcularHoraRequest.getDato1().split(":");
		    //Obtiene los valores enteros.
		    int valorHora = Integer.parseInt(HoraMinArray[0]); //Hora
		    int valorMinutos = Integer.parseInt(HoraMinArray[1]); // Minutos.
		    int valorSegundos = Integer.parseInt(HoraMinArray[2]); // Minutos.
		    		
			SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
	        dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
	
	        Calendar localTime = Calendar.getInstance();
	        localTime.set(Calendar.HOUR, valorHora);
	        localTime.set(Calendar.MINUTE, valorMinutos);
	        localTime.set(Calendar.SECOND, valorSegundos);
	
	        localTime.add(Calendar.HOUR_OF_DAY, (-calcularHoraRequest.getDato2()));
	        //localTime.setTimeZone(TimeZone.getTimeZone("UTC"));
			  
			calculo.setTime(dateFormat.format(localTime.getTime()));
			calculo.setTimezone("UTC");
			
		} catch (Exception e) {
			// TODO: handle exception			
			calculo.setTime("error Request");
			calculo.setTimezone("-");
		}
		
		
		response.setResponse(calculo);
		return response;
	}
	
}
