package fr.dawan.rs.client.controller;

import java.io.ByteArrayInputStream;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.json.Json;
import javax.json.JsonObjectBuilder;
import javax.json.stream.JsonParser;
import javax.json.stream.JsonParser.Event;

public class JsonTools {

	public static <T> String toJsonStream(T obj) throws IllegalArgumentException, IllegalAccessException {
		JsonObjectBuilder objectBuilder = Json.createObjectBuilder();
		Class<? extends Object> clazz = obj.getClass();
		Field[] declaredFields = clazz.getDeclaredFields();
		List<Field> list = Arrays.asList(declaredFields);
		list.stream().map(f -> fieldToJSON(f, obj, objectBuilder)).collect(Collectors.toList());
		return objectBuilder.build().toString();
	}

	public static Void fieldToJSON(Field f, Object obj, JsonObjectBuilder objectBuilder) {
		try {
			f.setAccessible(true);
			objectBuilder.add(f.getName(), f.get(obj).toString());
		} catch (IllegalArgumentException | IllegalAccessException e) {

		}
		return null;
	}

	private static <T> String prettyArrayPrinter(T[] tab) {
		return Arrays.asList(tab).stream().map(String::valueOf).collect(Collectors.joining(","));

	}

	public static <T> T fromJson(String jsonStr, Class<T> clazz) throws InstantiationException, IllegalAccessException {
		Field[] fields = clazz.getDeclaredFields();
		T instance = clazz.newInstance();
		ByteArrayInputStream bis = new ByteArrayInputStream(jsonStr.getBytes());
		try (JsonParser parser = Json.createParser(bis)) {
			while (parser.hasNext()) {
				Event event = parser.next();
				if (event == Event.KEY_NAME) {
					String key = parser.getString();
					for (Field field : fields) {
						if (field.getName().equals(key)) {
							if (field.getType() == String.class) {
								parser.next();
								field.setAccessible(true);
								field.set(instance, parser.getString());
							}
						}
					}

				}

			}
		}

		return instance;
	}

}
