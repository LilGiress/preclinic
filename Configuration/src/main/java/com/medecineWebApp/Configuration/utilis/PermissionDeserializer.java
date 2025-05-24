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
        // Récupérer le texte du JSON (ex: "READ", "WRITE", etc.)
        String module = p.getText();


        // Définir les permissions en fonction du module
        return switch (module.toUpperCase()) {
            case "READ" -> new Permission(module, true, false, false, false, false, false, false, false, false, false, false, false);
            case "WRITE" -> new Permission(module, false, true, false, false, false, false, false, false, false, false, false, false);
            case "CREATE" -> new Permission(module, false, false, true, false, false, false, false, false, false, false, false, false);
            case "DELETE" -> new Permission(module, false, false, false, true, false, false, false, false, false, false, false, false);
            case "IMPORT" -> new Permission(module, false, false, false, false, true, false, false, false, false, false, false, false);
            case "EXPORT" -> new Permission(module, false, false, false, false, false, true, false, false, false, false, false, false);
            case "APPROVE" -> new Permission(module, false, false, false, false, false, false, true, false, false, false, false, false);
            case "VALIDATE" -> new Permission(module, false, false, false, false, false, false, false, true, false, false, false, false);
            case "ASSIGN" -> new Permission(module, false, false, false, false, false, false, false, false, true, false, false, false);
            case "GENERATE_REPORT" -> new Permission(module, false, false, false, false, false, false, false, false, false, true, false, false);
            case "ACTIVATE" -> new Permission(module, false, false, false, false, false, false, false, false, false, false, true, false);
            default ->  new Permission(module, false, false, false, false, false, false, false, false, false, false, false); // Retour d'une permission par défaut
                // Gestion des permissions inconnues


        };
    }
}
