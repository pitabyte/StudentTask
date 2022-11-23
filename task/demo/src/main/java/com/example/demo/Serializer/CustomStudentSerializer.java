package com.example.demo.Serializer;

import com.example.demo.Student.Student;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CustomStudentSerializer extends StdSerializer<List<Student>> {

    public CustomStudentSerializer() {
        this(null);
    }

    public CustomStudentSerializer(Class<List<Student>> t) {
        super(t);
    }

    @Override
    public void serialize(
            List<Student> students,
            JsonGenerator generator,
            SerializerProvider provider)
            throws IOException, JsonProcessingException {

        generator.writeStartArray();
        List<Student> studs = new ArrayList<>();
        for (Student s : students) {
            generator.writeStartObject();

            generator.writeFieldName("id");
            generator.writeNumber(s.getId());

            generator.writeFieldName("name");
            generator.writeString(s.getName());

            generator.writeFieldName("surname");
            generator.writeString(s.getSurname());

            generator.writeFieldName("age");
            generator.writeNumber(s.getAge());

            generator.writeFieldName("email");
            generator.writeString(s.getEmail());

            generator.writeFieldName("major");
            generator.writeString(s.getMajor());

            generator.writeEndObject();

        }

        generator.writeEndArray();
    }
}