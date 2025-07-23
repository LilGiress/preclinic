package com.medecineWebApp.Configuration.utilis;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.medecineWebApp.Configuration.models.role.Permission;

import java.io.IOException;

public class PermissionDeserializer extends JsonDeserializer<Permission> {
    @Override
    public Permission deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JacksonException {
        return switch (p.getText().toUpperCase()) {
            case "READ" -> new Permission(true, false, false, false, false, false, false, false, false, false, false, false);
            case "WRITE" -> new Permission(false, true, false, false, false, false, false, false, false, false, false, false);
            case "CREATE" -> new Permission(false, false, true, false, false, false, false, false, false, false, false, false);
            case "DELETE" -> new Permission(false, false, false, true, false, false, false, false, false, false, false, false);
            case "IMPORT" -> new Permission(false, false, false, false, true, false, false, false, false, false, false, false);
            case "EXPORT" -> new Permission(false, false, false, false, false, true, false, false, false, false, false, false);
            case "APPROVE" -> new Permission(false, false, false, false, false, false, true, false, false, false, false, false);
            case "VALIDATE" -> new Permission(false, false, false, false, false, false, false, true, false, false, false, false);
            case "ASSIGN" -> new Permission(false, false, false, false, false, false, false, false, true, false, false, false);
            case "GENERATE_REPORT" -> new Permission(false, false, false, false, false, false, false, false, false, true, false, false);
            case "ACTIVATE" -> new Permission(false, false, false, false, false, false, false, false, false, false, true, false);
            default -> new Permission(false, false, false, false, false, false, false, false, false, false, false, false);
        };
    }
}