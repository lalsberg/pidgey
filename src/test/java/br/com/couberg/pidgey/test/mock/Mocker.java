package br.com.couberg.pidgey.test.mock;

import java.util.ArrayList;
import java.util.List;

import br.com.couberg.pidgey.test.model.ObjectWithListOfObjects;
import br.com.couberg.pidgey.test.model.ObjectWithListWithoutMany;
import br.com.couberg.pidgey.test.model.ObjectWithMandatory;
import br.com.couberg.pidgey.test.model.ObjectWithManyWithoutRepeated;
import br.com.couberg.pidgey.test.model.ObjectWithObject;
import br.com.couberg.pidgey.test.model.ObjectWithObjectWithoutAnything;
import br.com.couberg.pidgey.test.model.ObjectWithStringAndListOfObjectsWithMandatory;
import br.com.couberg.pidgey.test.model.ObjectWithoutDefaultConstructor;
import br.com.couberg.pidgey.test.model.ObjectWithoutId;

public class Mocker {
	
	public static ObjectWithStringAndListOfObjectsWithMandatory 
			getObjectObjectWithStringAndListOfObjectsWithMandatory() {
		
		ObjectWithStringAndListOfObjectsWithMandatory object = new 
				ObjectWithStringAndListOfObjectsWithMandatory();
		
		object.setQuantidade("0003");
		
		List<ObjectWithMandatory> objectsWithId = new ArrayList<ObjectWithMandatory>();
		for(int i = 1; i < 4; i++) {
			ObjectWithMandatory objectWithId = new ObjectWithMandatory();
			objectWithId.setIdPerfil(String.valueOf(i));
			objectWithId.setDescricao(null);
			objectWithId.setNome("PERFIL_" + i);
			objectWithId.setIdSistema(String.valueOf(i));
			objectWithId.setNomeSistema("SISTEMA_" + i);
			objectsWithId.add(objectWithId);
		}
		
		object.setRoles(objectsWithId);
		return object;
	}
	
	public static String getStringObjectWithStringAndListOfObjectsWithMandatory() {
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < 1716; i++) {
			sb.append(" ");
		}
		sb.append("0003"); //quantidade
		
		for(int a = 1; a < 4; a++) { //lista de roles
			sb.append("000").append(a); //idperfil
			
			for(int i = 0; i < 255; i++) {
				sb.append(" "); //descricao
			}
			
			sb.append("PERFIL_").append(a); //nome
			for(int i = 0; i < 247; i++) {
				sb.append(" "); //nome fill
			}
			
			sb.append("000").append(a); //idsistema
			
			sb.append("SISTEMA_").append(a);
			for(int i = 0; i < 246; i++) {
				sb.append(" "); //nome fill
			}
		}
		return sb.toString();
	}
	
	public static ObjectWithStringAndListOfObjectsWithMandatory 
		getObjectWithStringAndListOfObjectsWithMandatoryBindingNullvalue() {
		
		ObjectWithStringAndListOfObjectsWithMandatory object = new 
				ObjectWithStringAndListOfObjectsWithMandatory();
		
		object.setQuantidade("0003");
		
		List<ObjectWithMandatory> objectsWithId = new ArrayList<ObjectWithMandatory>();
		
		//the mandatory field came null, so the list must break and be empty
		
//		for(int i = 1; i < 4; i++) {
//			ObjectWithMandatory objectWithId = new ObjectWithMandatory();
//			objectWithId.setIdPerfil(String.valueOf(i));
//			objectWithId.setDescricao(null);
//			objectWithId.setNome("PERFIL_" + i);
//			objectWithId.setIdSistema(String.valueOf(i));
//			objectWithId.setNomeSistema("SISTEMA_" + i);
//			objectsWithId.add(objectWithId);
//		}
		
		object.setRoles(objectsWithId);
		return object;
	}
		
	
	public static String getStringObjectWithStringAndListOfObjectsWithMandatoryBindingNullvalue() {
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < 1716; i++) {
			sb.append(" ");
		}
		sb.append("0003"); //quantidade
		
		for(int a = 1; a < 4; a++) { //lista de roles
			sb.append("000").append(a); //idperfil
			
			for(int i = 0; i < 255; i++) {
				sb.append(" "); //descricao
			}
			
//			sb.append("PERFIL_").append(a); //nome
			for(int i = 0; i < 255; i++) { //setting nullvalue.
				sb.append(" "); //nome fill
			}
			
			sb.append("000").append(a); //idsistema
			
			sb.append("SISTEMA_").append(a);
			for(int i = 0; i < 246; i++) {
				sb.append(" "); //nome fill
			}
		}
		return sb.toString();
	}
	
	public static ObjectWithoutId getObjectWithoutId() {
		ObjectWithoutId request = new ObjectWithoutId();
		request.setNomePerfil("NOMEPERFIL");
		request.setIdSistema("1");
		return request;
	}
	
	public static String getStringObjectWithoutId() {
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < 1716; i++) {
			sb.append(" ");
		}
		
		sb.append("NOMEPERFIL"); //nomeperfil
		
		for(int i = 0; i < 245; i++) { //nomePerfil fill
			sb.append(" ");
		}
		
		sb.append("0001"); //idSistema
		
		return sb.toString();
	}
	
	public static ObjectWithListWithoutMany getObjectWithListWithoutMany() {
		
		ObjectWithListWithoutMany request = new ObjectWithListWithoutMany();
		
		request.setNomePerfil("perfil_teste");
		List<String> idSistemas = new ArrayList<String>();
		idSistemas.add("1");
		request.setIdSistema(idSistemas);
		
		return request;
	}
	
	public static ObjectWithManyWithoutRepeated getObjectWithManyWithoutRepeated() {

		ObjectWithManyWithoutRepeated object = new ObjectWithManyWithoutRepeated();
		
		object.setQuantidade("0003");
		
		List<ObjectWithMandatory> objectsWithId = new ArrayList<ObjectWithMandatory>();
		for(int i = 1; i < 4; i++) {
			ObjectWithMandatory objectWithId = new ObjectWithMandatory();
			objectWithId.setIdPerfil(String.valueOf(i));
			objectWithId.setDescricao("");
			objectWithId.setNome("PERFIL_" + i);
			objectWithId.setIdSistema(String.valueOf(i));
			objectWithId.setNomeSistema("SISTEMA_" + i);
			objectsWithId.add(objectWithId);
		}
		
		object.setRoles(objectsWithId);
		return object;
	}
	
	
	public static ObjectWithoutDefaultConstructor 
			getObjectWithoutDefaultConstructor() {
		ObjectWithoutDefaultConstructor request = new 
				ObjectWithoutDefaultConstructor("NOMEPERFIL", "1");
		return request;
	}
	
	public static String getStringObjectWithoutDefaultConstructor() {
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < 1716; i++) {
			sb.append(" ");
		}
		sb.append("NOMEPERFIL"); //nomeperfil
		for(int i = 0; i < 245; i++) { //nomePerfil fill
			sb.append(" ");
		}
		sb.append("0001"); //idSistema
		return sb.toString();
	}
	
	public static ObjectWithObject getObjectWithNullObject() {
		ObjectWithObject request = new ObjectWithObject();
		return request;
	}
	
	public static String getStringObjectWithNullObject() {
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < 1975; i++) {
			sb.append(" ");
		}
		return sb.toString();
	}
	
	public static ObjectWithObjectWithoutAnything getObjectWithNullObjectWithoutAnything() {
		ObjectWithObjectWithoutAnything request = new ObjectWithObjectWithoutAnything();
		return request;
	}
	
	public static String getStringObjectWithNullObjectWithoutAnything() {
		return "";
	}
	
	public static ObjectWithListOfObjects getObjectWithNullListOfObjects() {
		ObjectWithListOfObjects request = new ObjectWithListOfObjects();
		return request;
	}
	
	public static String getStringObjectWithNullListOfObjects() {
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < 1716; i++) {
			sb.append(" ");
		}
		for(int i = 0; i < 777; i++) { //the null list
			sb.append(" ");
		}
		return sb.toString();
	}
	
	public static String getStringObjectWithListOfStringBindingNullvalueOnSecondElement() {
		StringBuilder sb = new StringBuilder();
		sb.append("ELEMENTONE"); //first element
		for(int i = 0; i < 2; i++) { //next 2 elements
			sb.append("          ");
		}
		return sb.toString();
	}
	
}