package com.example.asmtrangsuc2.configurations;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

	@Bean
	public ModelMapper getModelMapper() {
		ModelMapper mapper = new ModelMapper();

		mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STANDARD);

		return mapper;
	}

}
