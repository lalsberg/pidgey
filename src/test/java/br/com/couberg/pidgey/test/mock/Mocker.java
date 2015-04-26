package br.com.couberg.pidgey.test.mock;

import java.util.ArrayList;
import java.util.List;

import br.com.couberg.pidgey.test.model.ObjectWithId;
import br.com.couberg.pidgey.test.model.ObjectWithListWithoutMany;
import br.com.couberg.pidgey.test.model.ObjectWithManyWithoutRepeated;
import br.com.couberg.pidgey.test.model.ObjectWithStringAndListOfObjectsWithId;
import br.com.couberg.pidgey.test.model.ObjectWithoutDefaultConstructor;
import br.com.couberg.pidgey.test.model.ObjectWithoutId;

public class Mocker {
	
	public static ObjectWithStringAndListOfObjectsWithId 
			getObjectObjectWithStringAndListOfObjectsWithId() {
		
		ObjectWithStringAndListOfObjectsWithId object = new 
				ObjectWithStringAndListOfObjectsWithId();
		
		object.setQuantidade("0003");
		
		List<ObjectWithId> objectsWithId = new ArrayList<ObjectWithId>();
		for(int i = 1; i < 4; i++) {
			ObjectWithId objectWithId = new ObjectWithId();
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
	
	public static String getStringObjectWithStringAndListOfObjectsWithId() {
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
		
		List<ObjectWithId> objectsWithId = new ArrayList<ObjectWithId>();
		for(int i = 1; i < 4; i++) {
			ObjectWithId objectWithId = new ObjectWithId();
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
	
	
}