package io.github.alicankustemur.person.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import io.github.alicankustemur.person.base.Common;
import io.github.alicankustemur.person.service.EnvironmentService;

@Service
@PropertySources({ @PropertySource(Common.APPLICATION_PROP_PATH) })
public class EnvironmentServiceImpl implements EnvironmentService {

	@Autowired
	private Environment environment;

	public String getProperty(String key) {
		return environment.getRequiredProperty(key);
	}

}
