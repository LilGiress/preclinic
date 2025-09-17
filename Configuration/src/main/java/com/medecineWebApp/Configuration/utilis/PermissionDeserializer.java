package com.medecineWebApp.Configuration.utilis;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.medecineWebApp.Configuration.models.role.ActionPermission;
import com.medecineWebApp.Configuration.models.role.Permission;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PermissionDeserializer extends JsonDeserializer<Permission> {

    @Override
    public Permission deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        JsonNode node = p.getCodec().readTree(p);

        Permission permission = new Permission();

        // Lire les champs simples du module
        permission.setLabel(node.has("label") ? node.get("label").asText() : null);
        permission.setDescription(node.has("description") ? node.get("description").asText() : null);
        permission.setSelected(node.has("isSelected") && node.get("isSelected").asBoolean(false));
        permission.setDisabled(node.has("disabled") && node.get("disabled").asBoolean(false));

        // Lire la liste des actions
        List<ActionPermission> actions = new ArrayList<>();
        if (node.has("actions") && node.get("actions").isArray()) {
            for (JsonNode actionNode : node.get("actions")) {
                ActionPermission action = new ActionPermission();
                action.setLabel(actionNode.has("label") ? actionNode.get("label").asText() : null);
                action.setSelected(actionNode.has("isSelected") && actionNode.get("isSelected").asBoolean(false));
                action.setDisabled(actionNode.has("disabled") && actionNode.get("disabled").asBoolean(false));
                action.setPermission(permission); // lien vers le module parent
                actions.add(action);
            }
        }

        permission.setActions(actions);

        return permission;
    }
}