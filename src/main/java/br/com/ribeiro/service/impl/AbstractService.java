package br.com.ribeiro.service.impl;

import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;

public abstract class AbstractService {

	public ExampleMatcher getDefaultMatcher() {
		return ExampleMatcher.matching().withIgnoreNullValues().withIgnoreCase()
				.withStringMatcher(StringMatcher.CONTAINING);
	}

	public ExampleMatcher getDefaultMatcherAny() {
		return ExampleMatcher.matchingAny().withIgnoreNullValues().withIgnoreCase()
				.withStringMatcher(StringMatcher.CONTAINING);
	}

	public ExampleMatcher getDefaultMatcherAll() {
		return ExampleMatcher.matchingAll().withIgnoreNullValues().withIgnoreCase()
				.withStringMatcher(StringMatcher.CONTAINING);
	}
}
