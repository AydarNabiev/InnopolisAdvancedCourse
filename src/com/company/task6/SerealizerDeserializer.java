package com.company.task6;

import java.io.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

public class SerealizerDeserializer {

    void serialize(Object object, String file) {
        File output = new File(file);
        Map<String, Object> objectMap = new LinkedHashMap<>();
        Class objectClass = object.getClass();
        //objectMap.put("Class", objectClass.toString());
        //objectMap.put("Class", objectClass.getSimpleName());
        objectMap.put("Class", objectClass.getName());
        Field[] fields = objectClass.getDeclaredFields();
        for (Field field : fields) {
            try {
                Field objectField = objectClass.getDeclaredField(field.getName());
                System.out.println("Нашли поле " + objectField);
                objectField.setAccessible(true);
                Object fieldValue = objectField.get(object);
                System.out.println("Достали значение " + fieldValue);
                if (fieldValue != null) {



                    //objectMap.put(objectField.toString(), fieldValue.toString());
                    objectMap.put(objectField.getType().toString().replaceFirst("class ", ""), fieldValue.toString());
                } else {
                    objectMap.put(objectField.toString(), null);
                }
            } catch (NoSuchFieldException | IllegalAccessException e) {
                System.out.println("Поля " + field + " нет в данном классе.");
            }
        }
        System.out.println(objectMap);
        try (FileWriter fileWriter = new FileWriter(output)) {
            for (Map.Entry<String, Object> entry : objectMap.entrySet()) {
                if (entry.getValue() != null && entry.getValue().getClass().equals(LinkedHashMap.class)) {
                    Map<String, Object> insideMap = (LinkedHashMap) entry.getValue();
                    fileWriter.write(entry.getKey() + System.lineSeparator());
                    for (Map.Entry<String, Object> insideEntry : insideMap.entrySet()) {

                        fileWriter.write(insideEntry.getKey() + " : " + insideEntry.getValue() + System.lineSeparator());


                    }
                    fileWriter.write("End of " + entry.getKey());
                } else {
                    fileWriter.write(entry.getKey() + ":" + entry.getValue() + System.lineSeparator());

                }
            }
        } catch (IOException e) {
            System.out.println("Ошибка ввода-вывода при записи данных объекта в файл.");
        }
    }

    Object deSerialize(String file) {
        String line;
        Map<String, Object> mapFromFile = new LinkedHashMap<>();

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
            Class<?> myClass = Class.forName(mapFromFile.get("Class").toString());
            //Constructor<?> constructor = myClass.getConstructor(String.class);
            Constructor constructor = myClass.getDeclaredConstructors()[0];

            mapFromFile.remove("Class");

            List<Object> values = new ArrayList<>();
            for (Map.Entry<String, Object> entry : mapFromFile.entrySet()) {
                String parameterClassName = entry.getKey();
                //Class<?> fieldClass = Class.forName(parameterClassName);
                Class<?> fieldClass = determineClassFromFile(parameterClassName);
                Object object = entry.getValue();

                values.add(determineObjectForConstructor(fieldClass, object));
            }

            Object object = constructor.newInstance(values);
        } catch (ClassNotFoundException e) {
            System.out.println("Не найден класс " + mapFromFile.get("Class"));
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        return null;
    }

    private Object determineObjectForConstructor(Class<?> fieldClass, Object object) {
        if (fieldClass.equals("int")) {
            return (int)object;
        } else if(fieldClass.equals("boolean")) {
            return (boolean)object;
        }
        return (String) object;

    }

    private Class<?> determineClassFromFile(String parameterClassName) {
        switch (parameterClassName) {
            case "boolean":
                return boolean.class;
            case "byte":
                return byte.class;
            case "short":
                return short.class;
            case "int":
                return int.class;
            case "long":
                return long.class;
            case "float":
                return float.class;
            case "double":
                return double.class;
            case "char":
                return char.class;
            case "void":
                return void.class;
            default:
                //String fqn = parameterClassName.contains(".") ? parameterClassName : "java.lang.".concat(parameterClassName);
                try {
                    //return Class.forName(fqn);
                    return Class.forName(parameterClassName);
                } catch (ClassNotFoundException ex) {
                    throw new IllegalArgumentException("Class not found: " + parameterClassName);
                }
        }

    }


}