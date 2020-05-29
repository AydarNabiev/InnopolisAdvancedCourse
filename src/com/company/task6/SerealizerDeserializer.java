package com.company.task6;

import java.io.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

public class SerealizerDeserializer {
    static int counter = 100;
    private static final Set<String> CLASSES = new HashSet<>(Arrays.asList("java.lang.String", "boolean", "byte", "short", "int", "long", "float", "double"));

    void serialize(Object object, String file) {
        File output = new File(file);
        Map<String, Object> objectMap = new LinkedHashMap<>();
        Class<?> objectClass = object.getClass();
        objectMap.put("Class", objectClass.getName());
        for (Field field : objectClass.getDeclaredFields()) {
            try {
                Field objectField = objectClass.getDeclaredField(field.getName());
                if (CLASSES.contains(objectField.getType().toString().replaceFirst("class ", ""))) {
                    System.out.println("Нашли поле примитивного типа либо типа String " + objectField);
                    objectField.setAccessible(true);
                    Object fieldValue = objectField.get(object);
                    System.out.println("Достали значение " + fieldValue);
                    objectMap.put(counter + objectField.getType().toString().replaceFirst("class ", ""), fieldValue.toString());
                } else {
                    System.out.println("Нашли поле ссылочного типа " + objectField);
                    List<String> refTypeFields = new LinkedList<>();
                    objectField.setAccessible(true);
                    Object fieldValueObject = objectField.get(object);
                    System.out.println("Достали значение-объект " + fieldValueObject);
                    Class<?> refFieldClass = fieldValueObject.getClass();
                    for (Field refField: refFieldClass.getDeclaredFields()) {
                        try {
                            Field refObjectField = refFieldClass.getDeclaredField(refField.getName());
                            refObjectField.setAccessible(true);
                            Object refFieldValue = refObjectField.get(fieldValueObject);
                            refTypeFields.add(refFieldValue.toString());
                        } catch (NoSuchFieldException e) {
                            System.out.println("Такое поле отсутствует");
                        } catch (SecurityException e) {
                            System.out.println("Исключение безопасности");
                        }
                    }
                    String refValues = refTypeFields.toString().replaceAll("\\]", "")
                            .replaceFirst("\\[", "").replaceAll(",", ";").replaceAll(" ", "");
                    objectMap.put(counter + objectField.getType().toString().replaceFirst("class ", ""), refValues);
                }
                counter++;
            } catch (NoSuchFieldException | IllegalAccessException e) {
                System.out.println("Поля " + field + " нет в данном классе.");
            }
        }
        try (FileWriter fileWriter = new FileWriter(output)) {
            for (Map.Entry<String, Object> entry : objectMap.entrySet()) {
                fileWriter.write(entry.getKey() + ":" + entry.getValue() + System.lineSeparator());
            }
        } catch (IOException e) {
            System.out.println("Ошибка ввода-вывода при записи данных объекта в файл.");
        }
    }

    Object deSerialize(String file) {
        Object object = null;
        String line;
        Map<String, String> mapFromFile = new LinkedHashMap<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(":", 2);
                if (parts.length >= 2)
                {
                    String key = parts[0];
                    String value = parts[1];
                    mapFromFile.put(key, value);
                } else {
                    System.out.println("ignoring line: " + line);
                }
            }
            for (String key : mapFromFile.keySet())
            {
                System.out.println(key + ":" + mapFromFile.get(key));
            }
            reader.close();
        } catch (FileNotFoundException e) {
            System.out.println("Не найден файл " + file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(mapFromFile);
        try {
            Class<?> myClass = Class.forName(mapFromFile.get("Class"));
            Constructor<?> constructor = myClass.getDeclaredConstructors()[0];
            mapFromFile.remove("Class");
            List<Object> values = new ArrayList<>();
            for (Map.Entry<String, String> entry : mapFromFile.entrySet()) {
                String parameterClassName = entry.getKey().substring(3);
                String fieldObject = entry.getValue();
                values.add(determineObjectForConstructor(parameterClassName, fieldObject));
            }
            object = constructor.newInstance(values.toArray());
        } catch (ClassNotFoundException e) {
            System.out.println("Не найден класс " + mapFromFile.get("Class"));
        } catch (IllegalAccessException e) {
            System.out.println("Ошибка доступа при рефлексии");
        } catch (InstantiationException e) {
            System.out.println("Ошибка создания экземпляра класса");
        } catch (InvocationTargetException e) {
            System.out.println("Обернутая ошибка при создании экземпляра класса");
        }
        return object;
    }

    private Object determineObjectForConstructor(String parameterClassName, String fieldObject) throws ClassNotFoundException, IllegalAccessException, InstantiationException, InvocationTargetException {
        switch (parameterClassName) {
            case "boolean":
                return Boolean.parseBoolean(fieldObject);
            case "byte":
                return Byte.parseByte(fieldObject);
            case "short":
                return Short.parseShort(fieldObject);
            case "int":
                return Integer.parseInt(fieldObject);
            case "long":
                return Long.parseLong(fieldObject);
            case "float":
                return Float.parseFloat(fieldObject);
            case "double":
                return Double.parseDouble(fieldObject);
            case "java.lang.String":
            case "class java.lang.String":
                return fieldObject;
        }
        return getReferenceTypeObject(parameterClassName, fieldObject);
    }

    private Object getReferenceTypeObject(String parameterClassName, String fieldObject) throws ClassNotFoundException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Class<?> myClass = Class.forName(parameterClassName);
        Constructor<?> constructor = myClass.getDeclaredConstructors()[0];
        List<String> valuesForParameters = Arrays.asList(fieldObject.split("\\s*;\\s*"));
        Class<?>[] parameters = constructor.getParameterTypes();
        List<Object> referenceTypeConstructorValues = new ArrayList<>();
        for (int i = 0; i < valuesForParameters.size(); i++) {
            referenceTypeConstructorValues.add(determineObjectForConstructor(parameters[i].toString(), valuesForParameters.get(i)));
        }
        return constructor.newInstance(referenceTypeConstructorValues.toArray());
    }
}