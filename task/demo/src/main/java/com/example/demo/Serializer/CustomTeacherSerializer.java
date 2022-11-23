package com.example.demo.Serializer;

import com.example.demo.Student.Student;
import com.example.demo.Teacher.Teacher;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CustomTeacherSerializer extends StdSerializer<List<Teacher>> {

    public CustomTeacherSerializer() {
        this(null);
    }

    public CustomTeacherSerializer(Class<List<Teacher>> t) {
        super(t);
    }

    @Override
    public void serialize(
            List<Teacher> teachers,
            JsonGenerator generator,
            SerializerProvider provider)
            throws IOException, JsonProcessingException {
        generator.writeStartArray();
        List<Teacher> ts = new ArrayList<>();
        for (Teacher t : teachers) {
            generator.writeStartObject();

            generator.writeFieldName("id");
            generator.writeNumber(t.getId());

            generator.writeFieldName("name");
            generator.writeString(t.getName());

            generator.writeFieldName("surname");
            generator.writeString(t.getSurname());

            generator.writeFieldName("age");
            generator.writeNumber(t.getAge());

            generator.writeFieldName("email");
            generator.writeString(t.getEmail());

            generator.writeFieldName("subject");
            generator.writeString(t.getSubject());

            generator.writeEndObject();
        }
        generator.writeEndArray();
    }
}