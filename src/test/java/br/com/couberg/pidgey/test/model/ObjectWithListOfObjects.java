package br.com.couberg.pidgey.test.model;

import java.util.List;

import br.com.couberg.pidgey.annotation.Many;
import br.com.couberg.pidgey.annotation.PField;

public class ObjectWithListOfObjects {
	
	@Many(repeated = 3)
	@PField(size = 259, clazz = ObjectWithoutId.class)
	private List<ObjectWithoutId> listObjectWithoutId;
	
}