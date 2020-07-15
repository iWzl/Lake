package com.upuphub.lake.skylake.bean;

import java.beans.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;
import java.util.stream.Stream;

public class JavaBeans {
    public static void main(String[] args) throws Exception {
        Person person = new Person();
        BeanInfo javaBean = Introspector.getBeanInfo(Person.class,Object.class);
        Map<String,PropertyDescriptor> propertyDescriptorMap = new HashMap<>();
        Stream.of(javaBean.getPropertyDescriptors()).forEach(propertyDescriptor -> {
            if(propertyDescriptor.getPropertyType() == List.class){
                propertyDescriptor.setPropertyEditorClass(StringToListPropertyEditor.class);
            }
            if(propertyDescriptor.getPropertyType() == Integer.class || propertyDescriptor.getPropertyType() == int.class){
                propertyDescriptor.setPropertyEditorClass(StringToIntPropertyEditor.class);
            }
            if(propertyDescriptor.getPropertyType() == String.class){
                propertyDescriptor.setPropertyEditorClass(StringToStringPropertyEditor.class);
            }
            propertyDescriptorMap.put(propertyDescriptor.getName(),propertyDescriptor);
        });
        Map<String, String> profileMap = new HashMap<>();
        profileMap.put("tags","a,b,c,d,e,f,g");
        profileMap.put("name","hello");
        profileMap.put("age","11");
        profileMap.put("flag","2");
        profileMap.forEach((key,value)->{
            PropertyDescriptor propertyDescriptor = propertyDescriptorMap.get(key);
            PropertyEditor propertyEditor =  propertyDescriptor.createPropertyEditor(person);
            propertyEditor.addPropertyChangeListener(new BeanPropertyChangeListener(propertyDescriptor,person));
            propertyEditor.setAsText(value);
        });

        System.out.println(person);
    }



    public static class StringToStringPropertyEditor extends PropertyEditorSupport {
        @Override
        public void setAsText(String text) throws IllegalArgumentException {
            setValue(text);
        }
    }


    public static class StringToListPropertyEditor extends PropertyEditorSupport {
        @Override
        public void setAsText(String text) throws IllegalArgumentException {
            List<String> value = Arrays.asList(text.split(","));
            setValue(value);
        }
    }

    public static class StringToIntPropertyEditor extends PropertyEditorSupport {
        @Override
        public void setValue(Object value) {
            super.setValue(value);
        }

        @Override
        public void setAsText(String text) throws IllegalArgumentException {
            setValue(Integer.parseInt(text));
        }
    }

    public static class BeanPropertyChangeListener implements PropertyChangeListener{
        private PropertyDescriptor propertyDescriptor;
        private Object obj;

        public BeanPropertyChangeListener(PropertyDescriptor propertyDescriptor, Object obj) {
            this.propertyDescriptor = propertyDescriptor;
            this.obj = obj;
        }

        @Override
        public void propertyChange(PropertyChangeEvent evt) {
            PropertyEditor source = (PropertyEditor) evt.getSource();
            Method writeMethod = propertyDescriptor.getWriteMethod();
            try {
                writeMethod.invoke(obj, source.getValue());
            } catch (IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
        }
    }
}
