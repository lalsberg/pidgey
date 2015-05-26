package br.com.pidgey.test.mock;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.pidgey.test.model.ObjectWithListOfObjects;
import br.com.pidgey.test.model.ObjectWithListWithoutMany;
import br.com.pidgey.test.model.ObjectWithMandatory;
import br.com.pidgey.test.model.ObjectWithManyWithoutRepeated;
import br.com.pidgey.test.model.ObjectWithObject;
import br.com.pidgey.test.model.ObjectWithObjectWithoutAnything;
import br.com.pidgey.test.model.ObjectWithStringAndListOfObjectsWithMandatory;
import br.com.pidgey.test.model.ObjectWithoutDefaultConstructor;
import br.com.pidgey.test.model.ObjectWithoutId;
import br.com.pidgey.test.model.types.ObjectWithBooleanPrimitive;
import br.com.pidgey.test.model.types.ObjectWithBooleanWrapper;
import br.com.pidgey.test.model.types.ObjectWithDate;
import br.com.pidgey.test.model.types.ObjectWithDoublePrimitive;
import br.com.pidgey.test.model.types.ObjectWithDoubleWrapper;
import br.com.pidgey.test.model.types.ObjectWithIntegerPrimitive;
import br.com.pidgey.test.model.types.ObjectWithIntegerWrapper;
import br.com.pidgey.test.model.types.ObjectWithListOfIntegers;
import br.com.pidgey.test.model.types.ObjectWithListOfStrings;
import br.com.pidgey.test.model.types.ObjectWithLongPrimitive;
import br.com.pidgey.test.model.types.ObjectWithLongWrapper;

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
	
	public static ObjectWithoutId getObjectWithoutStringLongerThanSize() {
		ObjectWithoutId request = new ObjectWithoutId();
		
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < 300; i++) { //nomePerfil longer (300) than size (255)
			sb.append(" ");
		}
		request.setNomePerfil(sb.toString());
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
	
	public static String getStringObjectWithListOfString() {
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < 3; i++) {
			sb.append("ELEMENT   ");
		}
		return sb.toString();
	}
	
	public static String getStringObjectWithListOfIntegers() {
		StringBuilder sb = new StringBuilder();
		for(int i = 1; i < 4; i++) {
			sb.append("000000000" + i);
		}
		return sb.toString();
	}
	
	public static ObjectWithListOfIntegers getObjectWithListOfIntegers() {
		ObjectWithListOfIntegers request = new ObjectWithListOfIntegers();
		List<Integer> list = new ArrayList<Integer>();
		list.add(1);
		list.add(2);
		list.add(3);
		request.setList(list);
		return request;
	}
	
	public static ObjectWithListOfStrings getObjectWithListOfString() {
		ObjectWithListOfStrings request = new ObjectWithListOfStrings();
		List<String> list = new ArrayList<String>();
		list.add("ELEMENT");
		list.add("ELEMENT");
		list.add("ELEMENT");
		request.setTheList(list);
		return request;
	}
	
	public static ObjectWithLongPrimitive getObjectWithLongPrimitive() {
		ObjectWithLongPrimitive request = new ObjectWithLongPrimitive();
		request.setNomePerfil("NOMEPERFIL");
		request.setIdSistema(1l);
		return request;
	}
	
	public static String getStringObjectWithLongOrIntPrimitiveOrWrapper() {
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
	
	public static String getResponseStringObjectWithNullLongOrIntPrimitiveOrWrapper() {
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < 1716; i++) {
			sb.append(" ");
		}
		
		sb.append("NOMEPERFIL"); //nomeperfil
		
		for(int i = 0; i < 245; i++) { //nomePerfil fill
			sb.append(" ");
		}
		
		sb.append("    "); //idSistema
		
		return sb.toString();
	}
	
	public static String getRequestStringObjectWithNullLongOrIntPrimitive() {
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < 1716; i++) {
			sb.append(" ");
		}
		
		sb.append("NOMEPERFIL"); //nomeperfil
		
		for(int i = 0; i < 245; i++) { //nomePerfil fill
			sb.append(" ");
		}
		
		sb.append("0000"); //idSistema. the default value of int and long is 0.
		
		return sb.toString();
	}
	
	public static String getStringObjectWithDoublePrimitiveOrWrapper() {
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < 1716; i++) {
			sb.append(" ");
		}
		
		sb.append("NOMEPERFIL"); //nomeperfil
		
		for(int i = 0; i < 245; i++) { //nomePerfil fill
			sb.append(" ");
		}
		
		sb.append("1050"); //idSistema
		
		return sb.toString();
	}
	
	public static ObjectWithLongWrapper getObjectWithLongWrapper() {
		ObjectWithLongWrapper request = new ObjectWithLongWrapper();
		request.setNomePerfil("NOMEPERFIL");
		request.setIdSistema(1l);
		return request;
	}
	
	public static ObjectWithIntegerPrimitive getObjectWithIntegerPrimitive() {
		ObjectWithIntegerPrimitive request = new ObjectWithIntegerPrimitive();
		request.setNomePerfil("NOMEPERFIL");
		request.setIdSistema(1);
		return request;
	}
	
	public static ObjectWithIntegerWrapper getObjectWithIntegerWrapper() {
		ObjectWithIntegerWrapper request = new ObjectWithIntegerWrapper();
		request.setNomePerfil("NOMEPERFIL");
		request.setIdSistema(1);
		return request;
	}
	
	public static ObjectWithIntegerWrapper getObjectWithNullIntegerWrapper() {
		ObjectWithIntegerWrapper request = new ObjectWithIntegerWrapper();
		request.setNomePerfil("NOMEPERFIL");
		request.setIdSistema(null);
		return request;
	}
	
	public static ObjectWithIntegerPrimitive getObjectWithNullIntegerPrimitive() {
		ObjectWithIntegerPrimitive request = new ObjectWithIntegerPrimitive();
		request.setNomePerfil("NOMEPERFIL");
		return request;
	}
	
	public static ObjectWithDoublePrimitive getObjectWithDoublePrimitive() {
		ObjectWithDoublePrimitive request = new ObjectWithDoublePrimitive();
		request.setNomePerfil("NOMEPERFIL");
		request.setIdSistema(10.50);
		return request;
	}
	
	public static ObjectWithDoubleWrapper getObjectWithDoubleWrapper() {
		ObjectWithDoubleWrapper request = new ObjectWithDoubleWrapper();
		request.setNomePerfil("NOMEPERFIL");
		request.setIdSistema(10.50);
		return request;
	}
	
	public static String getStringObjectWithBooleanPrimitiveOrWrapperTrue() {
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < 1716; i++) {
			sb.append(" ");
		}
		
		sb.append("NOMEPERFIL"); //nomeperfil
		
		for(int i = 0; i < 245; i++) { //nomePerfil fill
			sb.append(" ");
		}
		
		sb.append("true "); //theBoolean
		
		return sb.toString();
	}
	
	public static String getStringObjectWithBooleanPrimitiveOrWrapperFalse() {
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < 1716; i++) {
			sb.append(" ");
		}
		
		sb.append("NOMEPERFIL"); //nomeperfil
		
		for(int i = 0; i < 245; i++) { //nomePerfil fill
			sb.append(" ");
		}
		
		sb.append("false"); //theBoolean
		
		return sb.toString();
	}
	
	public static String getStringObjectWithDate() {
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < 1716; i++) {
			sb.append(" ");
		}
		
		sb.append("NOMEPERFIL"); //nomeperfil
		
		for(int i = 0; i < 245; i++) { //nomePerfil fill
			sb.append(" ");
		}
		
		sb.append("2015-05-12"); //theDate
		
		return sb.toString();
	}
	
	public static ObjectWithBooleanPrimitive getObjectWithBooleanPrimitiveTrue() {
		ObjectWithBooleanPrimitive request = new ObjectWithBooleanPrimitive();
		request.setNomePerfil("NOMEPERFIL");
		request.setTheBoolean(true);
		return request;
	}
	
	public static ObjectWithBooleanPrimitive getObjectWithBooleanPrimitiveFalse() {
		ObjectWithBooleanPrimitive request = new ObjectWithBooleanPrimitive();
		request.setNomePerfil("NOMEPERFIL");
		request.setTheBoolean(false);
		return request;
	}
	
	public static ObjectWithBooleanWrapper getObjectWithBooleanWrapperTrue() {
		ObjectWithBooleanWrapper request = new ObjectWithBooleanWrapper();
		request.setNomePerfil("NOMEPERFIL");
		request.setTheBoolean(true);
		return request;
	}
	
	public static ObjectWithBooleanWrapper getObjectWithBooleanWrapperFalse() {
		ObjectWithBooleanWrapper request = new ObjectWithBooleanWrapper();
		request.setNomePerfil("NOMEPERFIL");
		request.setTheBoolean(false);
		return request;
	}
	
	public static ObjectWithDate getObjectWithDate() {
		ObjectWithDate request = new ObjectWithDate();
		request.setNomePerfil("NOMEPERFIL");
		
		String datePattern="yyyy-MM-dd";
		SimpleDateFormat dateFormat = new SimpleDateFormat(datePattern);
		String dateStr = "2015-05-12";
		
		Date date = null;
		try {
			date = dateFormat.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		request.setDate(date);
		return request;
	}
	
}